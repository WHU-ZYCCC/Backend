package com.example.demo;

import com.example.demo.Cos.CosOp;
import com.example.demo.Img.ImgOp;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author dzy
 * @title: ImgOpTest
 * @projectName demo
 * @description: TODO
 * @date 2021/2/2020:39
 */
@SpringBootTest
public class ImgOpTest {
    @Test
    public void LableTest() {
        String downloadUrl = CosOp.GetDownloadUrl("海豚1.jpg");
        System.out.println(ImgOp.GetImgLable(downloadUrl));
    }
}
