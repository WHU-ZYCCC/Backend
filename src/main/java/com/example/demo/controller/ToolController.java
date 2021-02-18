package com.example.demo.controller;

import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.PutObjectResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author dzy
 * @title: ToolController
 * @projectName demo
 * @description: TODO
 * @date 2021/2/821:04
 */
@Controller
@RequestMapping("/tool")
public class ToolController {
    @PostMapping("/upload")
    @ResponseBody
    public Object uploadFile(@RequestParam("id") int id,
                             @RequestParam("file") MultipartFile file) {
        return saveFile(file);
    }

    private Object saveFile(MultipartFile file){
        if (file.isEmpty()){
            return "未选择文件";
        }
        String filename = file.getOriginalFilename(); //获取上传文件原来的名称
        String filePath = System.getProperty("user.dir") + "\\image\\";
        File temp = new File(filePath);
        if (!temp.exists()){
            temp.mkdirs();
        }
        File localFile = new File(filePath+filename);
        try {
            file.transferTo(localFile); //把上传的文件保存至本地
            System.out.println(file.getOriginalFilename()+" 上传成功");
        }catch (IOException e){
            e.printStackTrace();
            return "上传失败";
        }
        return "ok";
    }
}
