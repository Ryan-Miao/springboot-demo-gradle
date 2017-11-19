package com.test.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Ryan on 2017/11/14/0014.
 */
@Controller
public class HelloController {


    @CrossOrigin(origins = {"http://corshost:8081"})
    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
        return "{\"hello\":\"world\"}";
    }
}
