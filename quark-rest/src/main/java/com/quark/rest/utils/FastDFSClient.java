package com.quark.rest.utils;


import com.quark.common.entity.UploadFile;
import com.quark.rest.exception.FastDFSException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class FastDFSClient {

    public static final String FDFS_FILE_NAME_META = "filename";

    private FastDFSClient() {

    }

    /**
     * 上传文件
     *
     * @param file 上传文件
     * @return
     * @throws IOException
     * @throws FastDFSException
     */
    public String uploadFile(File file) throws IOException, FastDFSException {
        StorageClient storageClient = InitClientUtil.getStorageClient();
        byte[] bytes = FileUtils.readFileToByteArray(file);
        String filename = file.getName();
        NameValuePair filenameMeta = new NameValuePair(FDFS_FILE_NAME_META, filename);

        String[] uploadFile;
        try {
            uploadFile = storageClient.upload_file(bytes, FilenameUtils.getExtension(filename), new NameValuePair[]{filenameMeta});
        } catch (MyException e) {
            e.printStackTrace();
            throw new FastDFSException(e.getMessage());
        }
         String group = uploadFile[0];
         String newFilename = uploadFile[1];
        String accessUrl = getAccessUrl(group, newFilename);
        return accessUrl;
    }

    public boolean deleteFile(String filePath) throws IOException, FastDFSException {
        Objects.requireNonNull(filePath);
        String[] path = getGroupAndFileNameByFilePath(filePath);

        return this.deleteFile(path[0], path[1]);
    }

    public boolean deleteFile(String groupName, String filename) throws IOException, FastDFSException {
        StorageClient storageClient = InitClientUtil.getStorageClient();
        int i;
        try {
            i = storageClient.delete_file(groupName, filename);
        } catch (MyException e) {
            e.printStackTrace();
            throw new FastDFSException(e.getMessage());
        }
        return i == 0;
    }


    private String getAccessUrl(String group, String filename) {
        return ClientGlobal.getG_nginx_web_url() + "/" + group + "/" + filename;
    }

    private String[] getGroupAndFileNameByFilePath(String filePath) throws FastDFSException {
        int pos = filePath.indexOf("/");
        if (pos <= 0 || pos == filePath.length() - 1) {
            throw new FastDFSException(String.format("filePath 格式有误, 有效的路径样式为(group/path) 而当前解析路径为: %s", filePath));
        }

        String groupName = filePath.substring(0, pos); // group name
        String filename = filePath.substring(pos + 1); // file name
        return new String[]{groupName, filename};
    }


    public static FastDFSClient getInstance() {
        return Singleton.INSTANCE.get();
    }


    private enum Singleton {
        INSTANCE;

        private FastDFSClient fastDFSClient;

        Singleton() {
            fastDFSClient = new FastDFSClient();
        }

        FastDFSClient get() {
            return fastDFSClient;
        }
    }
}
