package cn.mobiledaily.web.controller;

import cn.mobiledaily.common.util.ByteUtil;
import cn.mobiledaily.domain.PushMessage;
import cn.mobiledaily.service.PushMessageService;
import cn.mobiledaily.web.common.DownstreamPusher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("qrcode")
public class QrcodeController {
    public static Map<String, Map<String, String>> USERS = new HashMap<>();
    static {
        Map<String, String> user = new HashMap<>();
        user.put("password", "1");
        user.put("name", "何桥");
        user.put("company", "墨狄（上海）信息科技有限公司");
        user.put("title", "总经理");
        user.put("login", "hq");
        USERS.put("hq", user);
    }

    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private PushMessageService pushMessageService;

    @RequestMapping("get")
    public void getQrcode(String login, String password, HttpServletResponse response) {
        Map<String, String> user = USERS.get(login);
        if (user == null) {
            user = USERS.get("hq");
        }

        Map<String, String> map = new HashMap<>();
        map.put("login", user.get("login"));
        map.put("name", user.get("name"));
        final BarcodeFormat barcodeFormat = BarcodeFormat.QR_CODE;
        MultiFormatWriter writer = new MultiFormatWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.MARGIN, 1);
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        try {
            String json = mapper.writeValueAsString(map);
            ByteBuffer buffer = ByteBuffer.allocate(200);
            buffer.put(json.getBytes("UTF-8"));
            buffer.flip();
            byte[] bs = new byte[buffer.limit()];
            buffer.get(bs);
            String codec = "MD:" + ByteUtil.byte2ascii(bs);

            BitMatrix matrix = writer.encode(codec, barcodeFormat, 700, 700, hints);
            File file = File.createTempFile("qrcode", "png");
            MatrixToImageWriter.writeToFile(matrix, "png", file);

            response.setStatus(200);
//            response.setHeader("Content-Type", "image/png");
            response.setContentType("image/png");
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
//            response.setHeader("Content-Length", String.valueOf(in.available()));
            response.setContentLength(in.available());
            ServletOutputStream out = response.getOutputStream();
            bs = new byte[4096];
            int len = 0;
            while ((len = in.read(bs, 0, bs.length)) > 0) {
                out.write(bs, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("user/{login}")
    public void getUser(@PathVariable String login, HttpServletResponse response) {
        byte[] bs = ByteUtil.ascii2byte(login);
        String json = new String(bs, Charset.forName("UTF-8"));
        try {
            Map<String, String> user = mapper.readValue(json, HashMap.class);
            user = USERS.get(user.get("login"));
            json = mapper.writeValueAsString(user);
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            response.setContentLength(json.getBytes("UTF-8").length);
            response.getWriter().write(json);
            DownstreamPusher.pushShowUpMessage(user.get("name"), user.get("company"));
            final String body = String.format("%s(%s) 抵达会场 %s",
                    user.get("name"), user.get("company"), new DateTime().toString("M月d日 HH:mm"));
            new Thread(new Runnable() {
                @Override
                public void run() {
                    PushMessage message = new PushMessage("抵达通知", body, "");
                    pushMessageService.pushMessage("CCBN", message);
                }
            }).start();
        } catch (Exception e) {

        }
    }
}
