package com.guli.oss.utils;

import com.sun.org.apache.xml.internal.security.Init;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstandPropertiesUtils implements InitializingBean {
    //读取配置文件的内容
    @Value("${aliyun.oss.file.endpoint}")
    String endpoint;
    @Value("${aliyun.oss.file.keyid}")
    String keyId;
    @Value("${aliyun.oss.file.keysecret}")
    String keySecret;
    @Value("${aliyun.oss.file.bucketname}")
    String bucketName;
    public static String  END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;


    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endpoint;
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }
}
