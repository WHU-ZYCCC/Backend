package com.example.demo.Img;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.tiia.v20190529.TiiaClient;
import com.tencentcloudapi.tiia.v20190529.models.DetectLabelRequest;
import com.tencentcloudapi.tiia.v20190529.models.DetectLabelResponse;

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
            Credential cred = new Credential("11", "");
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("tiia.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            TiiaClient client = new TiiaClient(cred, "ap-shanghai", clientProfile);
            DetectLabelRequest req = new DetectLabelRequest();
            req.setImageUrl("11111111");
            DetectLabelResponse resp = client.DetectLabel(req);
            return resp.toString();
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
            return "";
        }
    }
}
