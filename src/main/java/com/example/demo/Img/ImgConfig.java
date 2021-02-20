package com.example.demo.Img;

import com.example.demo.Cos.CosConfig;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import com.tencentcloudapi.common.Credential;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dzy
 * @title: ImgConfig
 * @projectName demo
 * @description: TODO
 * @date 2021/2/2020:23
 */
public class ImgConfig {
    public String secretId;
    public String secretKey;
    public String region;

    public ImgConfig(String secretId, String secretKey, String region) {
        this.secretId = secretId;
        this.secretKey = secretKey;
        this.region = region;
    }

    public ImgConfig() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ImgConfig imgConfig = (ImgConfig) context.getBean("ImgConfig");
        this.secretId = imgConfig.secretId;
        this.secretKey = imgConfig.secretKey;
        this.region = imgConfig.region;
    }

    public static Credential credential;
    public static Credential GetCredential() {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            if (credential == null) {
                ImgConfig imgConfig = new ImgConfig();
                credential = new Credential(imgConfig.secretId, imgConfig.secretKey);
            }
            return credential;
        }
        finally {
            lock.unlock();
        }
    }
}
