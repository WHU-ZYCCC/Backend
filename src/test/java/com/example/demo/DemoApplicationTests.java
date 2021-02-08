package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.Cos.CosOp;
import com.example.demo.data.domain.Signin;
import com.example.demo.data.domain.SigninExample;
import com.example.demo.data.mapper.SigninMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        List<String> list = CosOp.GetAllDownloadUrl();
        for (String s:list) System.out.println(s);
    }

    @Test
    void testStrArrayToJson() {
        String[] testArray = new String[] {"Lhr","and","Lgp"};
        String jsonStr = JSON.toJSONString(testArray);
        System.out.println(jsonStr);
    }
    @Autowired
    SigninMapper signinMapper;
    @Test
    void testSelect() {
        List<Signin> list = signinMapper.selectByExample(new SigninExample());
        for (Signin s:list) System.out.println(s.getName());
    }
}
