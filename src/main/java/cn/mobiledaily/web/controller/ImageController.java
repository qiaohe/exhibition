package cn.mobiledaily.web.controller;

import cn.mobiledaily.service.FileService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.NoSuchFileException;

@Controller
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private FileService fileService;

    @RequestMapping("/{path}/{name:.+}")
    public void getImage(@PathVariable String path, @PathVariable String name, HttpServletResponse response) {
        String filename = path + "/" + name;
        long size = 0;
        try {
            size = fileService.size(filename);
        } catch (NoSuchFileException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        if (StringUtils.endsWithIgnoreCase(name, ".png")) {
            response.setHeader("Content-Type", "image/png");
        } else if (StringUtils.endsWithIgnoreCase(name, ".jpg") || StringUtils.endsWithIgnoreCase(name, ".jpeg")) {
            response.setHeader("Content-Type", "image/jpeg");
        }
        response.setIntHeader("Content-Length", (int) size);
        try (InputStream in = fileService.load(filename); OutputStream out = response.getOutputStream()) {
            IOUtils.copy(in, out);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
