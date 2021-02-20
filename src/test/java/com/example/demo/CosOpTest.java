package com.example.demo;

import com.example.demo.Cos.CosOp;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author dzy
 * @title: CosOpTest
 * @projectName demo
 * @description:
 * @date 2021/2/2019:58
 */
@SpringBootTest
public class CosOpTest {
    @Test
    public void testDownUrl() {
        System.out.println(CosOp.GetDownloadUrl("海豚1.jpg"));
    }
}
