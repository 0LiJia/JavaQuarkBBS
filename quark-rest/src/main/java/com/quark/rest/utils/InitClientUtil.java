package com.quark.rest.utils;

import lombok.extern.slf4j.Slf4j;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;

import java.io.IOException;


@Slf4j
public class InitClientUtil {

    static {
        try {
            String confPath = "fdfs/fdfs_client.conf";
            log.info("init ClientGlobal use the conf [{}]", confPath);
            ClientGlobal.init(confPath);
        } catch (IOException | MyException e) {
            e.printStackTrace();
            log.error("can not init clientGlobal", e);
        }

    }

    public static StorageClient getStorageClient() throws IOException {
        return new StorageClient(new TrackerClient().getConnection(), null);
    }
}
