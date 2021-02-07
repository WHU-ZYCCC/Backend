package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.Cos.CosOp;
import org.junit.jupiter.api.Test;
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

}
