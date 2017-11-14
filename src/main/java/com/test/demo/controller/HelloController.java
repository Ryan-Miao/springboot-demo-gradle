package com.test.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Ryan on 2017/11/14/0014.
 */
@Controller
public class HelloController {

    @GetMapping("/")
    public String index(){
        return hello();
    }

    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
        return "{\"hello\":\"world\"}";
    }
}
