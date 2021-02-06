package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author dzy
 * @title: image
 * @projectName demo
 * @description: TODO
 * @date 2021/2/613:13
 */
@Controller
@RequestMapping(value = "/image")
@ResponseBody
public class image {
    @GetMapping("/test")
    public int test() {
        return 0;
    }
}
