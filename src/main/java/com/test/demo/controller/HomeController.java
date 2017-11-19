package com.test.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ryan on 2017/11/18/0018.
 */
@Controller
public class HomeController {

    @RequestMapping("/home")
    public String index(Model model, String name){
        final Map<String, Object> user = new HashMap<>();
        user.put("name", name);

        model.addAttribute("user", user);
        model.addAttribute("msg", "Hello World!");
        return "home";
    }
}
