package com.liumapp.common.oss.connector;

import com.aliyun.oss.OSSClient;
import com.liumapp.common.oss.config.Configure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by liumapp on 9/4/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@Component
public class Connector {

    @Autowired
    private Configure configure;

    private OSSClient client = null;

    public Connector() {

        OSSClient client = new OSSClient(configure.getEndPoint() , configure.getAccessKeyId() , configure.getAccessKeySecret());

    }

    public OSSClient getClient() {
        return client;
    }

}
