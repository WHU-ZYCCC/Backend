package com.example.demo.Img;

import com.alibaba.fastjson.JSON;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.tiia.v20190529.TiiaClient;
import com.tencentcloudapi.tiia.v20190529.models.DetectLabelItem;
import com.tencentcloudapi.tiia.v20190529.models.DetectLabelRequest;
import com.tencentcloudapi.tiia.v20190529.models.DetectLabelResponse;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author dzy
 * @title: ImgOp
 * @projectName demo
 * @description: TODO
 * @date 2021/2/2020:23
 */
public class ImgOp {
    public static String GetImgLable(String imgUrl) {
        try{
            Credential cred = ImgConfig.GetCredential();
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("tiia.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            TiiaClient client = new TiiaClient(cred, new ImgConfig().region, clientProfile);
            DetectLabelRequest req = new DetectLabelRequest();
            req.setImageUrl(imgUrl);
            DetectLabelResponse resp = client.DetectLabel(req);
            return handleLabels(resp);
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
            return "";
        }
    }

    /**
     * 处理返回标签的函数
     * @param resp
     * @return
     */
    public static String handleLabels(DetectLabelResponse resp) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(resp.getLabels()).forEach(di -> {
            sb.append(di.getName());
            sb.append(" ");
        });
        return sb.toString();
    }
}
