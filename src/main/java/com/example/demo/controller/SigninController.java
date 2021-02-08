package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.data.domain.Signin;
import com.example.demo.data.domain.SigninExample;
import com.example.demo.data.mapper.SigninMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author dzy
 * @title: SigninController
 * @projectName demo
 * @description: TODO
 * @date 2021/2/821:04
 */
@Controller
@RequestMapping("/signin")
public class SigninController {
    @Autowired
    SigninMapper signinMapper;
    @GetMapping("/getOneDay/{strday}")
    @ResponseBody
    public String getOneDay(@PathVariable("strday") String strday) throws ParseException {
        Date day = new SimpleDateFormat("yyyy-MM-dd").
                parse(strday);
        Date nextDay = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(day);
        calendar.add(calendar.DATE,1); //把日期往后增加一天,整数  往后推,负数往前移动
        nextDay=calendar.getTime(); //这个时间就是日期往后推一天的结果
        SigninExample example = new SigninExample();
        SigninExample.Criteria criteria = example.createCriteria();
        criteria.andDateBetween(day,nextDay);
        example.or(criteria);

        List<Signin> list = signinMapper.selectByExample(example);
        return JSON.toJSONString(list);
    }
    
    @GetMapping("/getAll")
    @ResponseBody
    public String GetAll() {
        return JSON.toJSONString(signinMapper.selectByExample(new SigninExample()));
    }
}
