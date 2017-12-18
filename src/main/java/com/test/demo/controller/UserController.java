package com.test.demo.controller;

import com.test.demo.domain.entity.UserTable;
import com.test.demo.domain.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/{id}")
    @ApiOperation("get by id.")
    public UserTable getUserById(@PathVariable Integer id){
        return userService.getById(id);
    }

    @PostMapping
    @ApiOperation("save an user.")
    public Integer insertUser(@RequestBody UserTable userTable){
        return userService.insert(userTable);
    }
}
