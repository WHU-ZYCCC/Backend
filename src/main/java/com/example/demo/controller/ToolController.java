package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.Cos.CosOp;
import com.example.demo.Img.ImgOp;
import com.example.demo.data.domain.Tool;
import com.example.demo.data.domain.ToolExample;
import com.example.demo.data.mapper.ToolMapper;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    ToolMapper toolMapper;
    @PostMapping("/upload")
    @ResponseBody
    public Object uploadFile(
                             @RequestParam("name") String name,
                             @RequestParam("description") String description,
                             @RequestParam("file") MultipartFile file) {
        String imagekey = saveFile(file);
        Tool tool = new Tool();
        tool.setName(name);
        tool.setDescription(description);
        tool.setImagekey(imagekey);
        String aikey = ImgOp.GetImgLable(CosOp.GetDownloadUrl(imagekey));
        tool.setAikey(aikey);
        return toolMapper.insert(tool);
    }

    private String saveFile(MultipartFile file){
        if (file.isEmpty()){
            return "未选择文件";
        }
        String filename = file.getOriginalFilename(); //获取上传文件原来的名称
        String filePath = System.getProperty("user.dir") + "\\image\\";
        File temp = new File(filePath);
        if (!temp.exists()){
            if(!temp.mkdirs())
                return "创建文件夹失败";
        }
        File localFile = new File(filePath+filename);
        try {
            file.transferTo(localFile); //把上传的文件保存至本地
            System.out.println(file.getOriginalFilename()+" 上传成功");
        }catch (IOException e){
            e.printStackTrace();
            return "上传失败";
        }
        return CosOp.putObject(localFile);
    }
    @GetMapping("/getAll")
    @ResponseBody
    public Object GetAllTool() {
        ToolExample example = new ToolExample();
        return toolMapper.selectByExample(example);
    }
}
