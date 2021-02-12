package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.MyRequestUtil;
import com.example.demo.data.domain.Signin;
import com.example.demo.data.domain.SigninExample;
import com.example.demo.data.mapper.SigninMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public Object getOneDay(@PathVariable("strday") String strday) throws ParseException {
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
        return list;
    }

    @GetMapping("/getToday")
    @ResponseBody
    public Object getToday() throws ParseException {
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        return getOneDay(today);
    }

    @GetMapping("/getAll")
    @ResponseBody
    public Object GetAll() {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        HashMap<String,List<Signin>> map = new HashMap<>();
        List<Signin> list = signinMapper.selectByExample(new SigninExample());
        list.stream().forEach(signin -> {
            String date = format.format(signin.getDate());
            List<Signin> dateList = map.getOrDefault(date,new LinkedList<Signin>());
            dateList.add(signin);
            map.put(date,dateList);
        });
        return map;
    }

    @PostMapping("/sign")
    @ResponseBody
    public Object sign(@RequestParam("name") String name,
                       @RequestParam("number") String number,
                       @RequestParam("longitude") BigDecimal longitude,
                       @RequestParam("latitude") BigDecimal latitude,
                       @RequestParam("date") String date) throws ParseException {
        Signin signin = new Signin();
        signin.setName(name);
        signin.setLatitude(latitude);
        signin.setLongitude(longitude);
        signin.setNumber(number);
        signin.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date));
        signinMapper.insert(signin);
        return signin;
    }

    @GetMapping("/radius")
    @ResponseBody
    public Object Radius() {
        return 70;
    }
}
