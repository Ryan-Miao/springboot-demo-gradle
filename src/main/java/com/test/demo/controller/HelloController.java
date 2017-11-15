package com.test.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Ryan on 2017/11/14/0014.
 */
@Controller
public class HelloController {


    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
        return "{\"hello\":\"world\"}";
    }
}
