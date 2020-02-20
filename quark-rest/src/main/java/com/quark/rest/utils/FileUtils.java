package com.quark.rest.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.util.UUID;

// TODO : 修改文件上传到远程服务器
public class FileUtils {

    public static String uploadFile(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String today = LocalDate.now().toString();

            String type = file.getContentType();
            String suffix = "." + type.split("/")[1];
            String userUploadPath = today + "/";
            String fileName = UUID.randomUUID().toString()+suffix;
            // File file_dir = new File(Constants.UPLOAD_PATH + userUploadPath);
            // if (!file_dir.exists()) file_dir.mkdirs();

            File file_dir = new File(Constants.STATIC_URL + userUploadPath);
            if (!file_dir.exists()) file_dir.mkdirs();

            // change
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(Constants.STATIC_URL+ userUploadPath + fileName)));
            stream.write(file.getBytes());
            stream.close();

            return Constants.STATIC_URL+userUploadPath+fileName;
        }
        return null;
    }
}
