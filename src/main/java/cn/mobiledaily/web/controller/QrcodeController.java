package cn.mobiledaily.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
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
        USERS.put("hq", user);
    }

    private ObjectMapper mapper = new ObjectMapper();

    @RequestMapping("get")
    public void getQrcode(String login, String password, HttpServletResponse response) {
        Map<String, String> user = USERS.get(login);
        if (user == null) {
            user = USERS.get("hq");
        }

        final BarcodeFormat barcodeFormat = BarcodeFormat.QR_CODE;
        MultiFormatWriter writer = new MultiFormatWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 1);
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        try {
            String json = mapper.writeValueAsString(user);
            BitMatrix matrix = writer.encode(json, barcodeFormat, 700, 700, hints);
            File file = File.createTempFile("qrcode", "png");
            MatrixToImageWriter.writeToFile(matrix, "png", file);

            response.setStatus(200);
            response.setHeader("Content-Type", "image/png");
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            response.setHeader("Content-Length", String.valueOf(in.available()));
            ServletOutputStream out = response.getOutputStream();
            byte[] bs = new byte[4096];
            int len = 0;
            while ((len = in.read(bs, 0, bs.length)) > 0) {
                out.write(bs, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
