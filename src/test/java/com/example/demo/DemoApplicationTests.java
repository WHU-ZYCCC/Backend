package com.example.demo;

import com.example.demo.Cos.CosOp;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        CosOp.testSearch();
    }

}
