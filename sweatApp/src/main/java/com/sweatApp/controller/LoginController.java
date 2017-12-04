package com.sweatApp.controller;

import com.sweatApp.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wj on 2017/11/29.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private RedisUtil redisUtil;


    @RequestMapping("/login")
    @ResponseBody
    public Map<String,Object> login(String userName,String password){
        System.out.print(userName+password);
        Map<String, Object> map = new HashMap<>();
        map.put("code","0");
        map.put("message","成功");
        return map;
    }
}
