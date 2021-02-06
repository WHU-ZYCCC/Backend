package com.example.demo.Cos;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author dzy
 * @title: CosConfig
 * @projectName demo
 * @description: TODO
 * @date 2021/2/613:25
 */
public class CosConfig {
    public String secretId;
    public String secretKey;
    public String bucketName;
    public String regionName;

    public CosConfig(String secretId, String secretKey, String bucketName, String regionName) {
        this.secretId = secretId;
        this.secretKey = secretKey;
        this.bucketName = bucketName;
        this.regionName = regionName;
    }

    public CosConfig() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CosConfig cosConfig = (CosConfig) context.getBean("CosConfig");
        this.secretId = cosConfig.secretId;
        this.secretKey = cosConfig.secretKey;
        this.bucketName = cosConfig.bucketName;
        this.regionName = cosConfig.regionName;
    }

    private static COSClient cosClient;
    public static COSClient GetCosClient() {
        CosConfig cosConfig = new CosConfig();
        // 存储桶的命名格式为 BucketName-APPID，此处填写的存储桶名称必须为此格式
        COSCredentials cred = new BasicCOSCredentials(cosConfig.secretId, cosConfig.secretKey);
        Region region = new Region(cosConfig.regionName);
        ClientConfig clientConfig = new ClientConfig(region);
        if (cosClient == null) {
            // 生成 cos 客户端。
            cosClient = new COSClient(cred, clientConfig);
        }
        return cosClient;
    }
}
