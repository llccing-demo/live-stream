package com.llccing.livestream.service;

import java.io.*;

public class FileService {
    public static void copyFileUsingSteams(File source, File dest) throws IOException {
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int byteRead;
            while((byteRead = input.read(buf))>0) {
                output.write(buf, 0, byteRead);
            }
        } finally {
            input.close();
            output.close();
        }
    }
}
