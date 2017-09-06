package com.liumapp.common.oss.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.liumapp.common.oss.config.Configure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by liumapp on 9/5/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@Component
public class OssUtil {

    @Autowired
    private Configure configure = new Configure();

    private OSSClient ossClient = null;

    private void connect() {
        ossClient = new OSSClient(configure.getEndPoint() , configure.getAccessKeyId() , configure.getAccessKeySecret());
    }

    /**
     *
     OssUtil ossUtil = new OssUtil();
     ossUtil.uploadFile("test/test2.docx" , new File("./data/test0.docx"));
     * @param key
     * @param file
     */
    public void uploadFile(String key , File file) {
        if (ossClient == null) {
            connect();
        }
        try {
            ossClient.putObject(configure.getBucket() , key , file);
        } catch (ClientException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
    }

    /**
     *
     OssUtil ossUtil = new OssUtil();
     ossUtil.downloadFile("test/test0.docx" , new File("./data/download.docx"));
     * @param key
     * @param file
     */
    public void downloadFile(String key , File file) {
        if (ossClient == null) {
            connect();
        }
        try {
            ossClient.getObject(new GetObjectRequest(configure.getBucket() , key) , file);
        } catch (ClientException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
    }

}
