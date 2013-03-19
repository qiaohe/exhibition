package cn.mobiledaily.service;

import java.io.IOException;
import java.io.InputStream;

public interface FileService {
    void save(InputStream in, String filename) throws IOException;

    InputStream load(String filename) throws IOException;

    long size(String filename) throws IOException;
}
