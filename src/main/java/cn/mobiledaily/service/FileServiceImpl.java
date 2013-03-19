package cn.mobiledaily.service;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.*;

@Service("fileService")
public class FileServiceImpl implements FileService {
    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public void save(InputStream in, String filename) throws IOException {
        Path path = Paths.get(uploadPath, filename);
        if (Files.notExists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }
        try (OutputStream out = Files.newOutputStream(path)) {
            IOUtils.copy(in, out);
        } catch (IOException e) {
            throw e;
        }
    }

    @Override
    public long size(String filename) throws IOException {
        Path path = Paths.get(uploadPath, filename);
        if (Files.notExists(path) || Files.isDirectory(path)) {
            throw new NoSuchFileException(path.toString());
        }
        return Files.size(path);
    }

    @Override
    public InputStream load(String filename) throws IOException {
        Path path = Paths.get(uploadPath, filename);
        if (Files.notExists(path) || Files.isDirectory(path)) {
            throw new NoSuchFileException(path.toString());
        }
        return Files.newInputStream(path);
    }
}
