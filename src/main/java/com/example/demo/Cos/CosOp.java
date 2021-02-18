package com.example.demo.Cos;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.*;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author dzy
 * @title: CosOp
 * @projectName demo
 * @description: TODO
 * @date 2021/2/613:30
 */
public class CosOp {
    public static final String bucketName="picture-1304825952";
    public static List<COSObjectSummary> SearchAll() {
        COSClient cosClient = CosConfig.GetCosClient();
        // Bucket的命名格式为 BucketName-APPID ，此处填写的存储桶名称必须为此格式
//        String bucketName = "picture-1304825952";
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
// 设置bucket名称
        listObjectsRequest.setBucketName(bucketName);
// prefix表示列出的object的key以prefix开始
        listObjectsRequest.setPrefix("");
// deliter表示分隔符, 设置为/表示列出当前目录下的object, 设置为空表示列出所有的object
        listObjectsRequest.setDelimiter("/");
// 设置最大遍历出多少个对象, 一次listobject最大支持1000
//        listObjectsRequest.setMaxKeys(1000);
        ObjectListing objectListing = null;
//        do {
            try {
                objectListing = cosClient.listObjects(listObjectsRequest);
            } catch (CosServiceException e) {
                e.printStackTrace();
                return null;
            } catch (CosClientException e) {
                e.printStackTrace();
                return null;
            }
            // common prefix表示表示被delimiter截断的路径, 如delimter设置为/, common prefix则表示所有子目录的路径
            List<String> commonPrefixs = objectListing.getCommonPrefixes();
            // object summary表示所有列出的object列表
            List<COSObjectSummary> cosObjectSummaries = objectListing.getObjectSummaries();
            return cosObjectSummaries;
//            for (COSObjectSummary cosObjectSummary : cosObjectSummaries) {
//                // 文件的路径key
//                String key = cosObjectSummary.getKey();
//                // 文件的etag
//                String etag = cosObjectSummary.getETag();
//                // 文件的长度
//                long fileSize = cosObjectSummary.getSize();
//                // 文件的存储类型
//                String storageClasses = cosObjectSummary.getStorageClass();
//                System.out.println(key);
//            }
//            String nextMarker = objectListing.getNextMarker();
//            listObjectsRequest.setMarker(nextMarker);
//        } while (objectListing.isTruncated());
    }
    public static String GetDownloadUrl(String key) {
        COSClient cosClient = CosConfig.GetCosClient();
//        String bucketName = "picture-1304825952";
        GeneratePresignedUrlRequest req =
                new GeneratePresignedUrlRequest(bucketName, key, HttpMethodName.GET);
// 设置签名过期时间(可选), 若未进行设置, 则默认使用 ClientConfig 中的签名过期时间(1小时)
// 这里设置签名在半个小时后过期
        Date expirationDate = new Date(System.currentTimeMillis() + 60L * 60L * 1000L);
        req.setExpiration(expirationDate);
        URL url = cosClient.generatePresignedUrl(req);
        return url.toString();
    }
    public static List<String> GetAllDownloadUrl() {
        List<String> result = new LinkedList<>();
        List<COSObjectSummary> cosList = SearchAll();
        cosList.stream().forEach(x->result.add(GetDownloadUrl(x.getKey())));
        return result;
    }
    public static PutObjectResult putObject(File file)
            throws CosClientException{
        COSClient cosClient = CosConfig.GetCosClient();
        CosConfig cosConfig = new CosConfig();
        return cosClient.putObject(cosConfig.bucketName, file.getName() , file);
    }
}
