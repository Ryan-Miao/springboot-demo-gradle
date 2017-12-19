package com.test.demo.controller;

import com.test.demo.domain.entity.UserTable;
import com.test.demo.domain.service.IUserService;
import com.test.demo.domain.valueobject.response.BaseResponse;
import com.test.demo.domain.valueobject.response.ErrorMsg;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @GetMapping("/{id}")
    @ApiOperation("get by id.")
    public BaseResponse<UserTable> getUserById(@PathVariable Integer id){
        try {
            UserTable user = userService.getById(id);
            return new BaseResponse<>(user);
        } catch (Exception e) {
            LOGGER.error("xxxx, errorCode=1002", e);
            return new BaseResponse<>(new ErrorMsg(1002, "xxxx"));
        }

    }

    @PostMapping
    @ApiOperation("save an user.")
    public BaseResponse<Integer> insertUser(@RequestBody UserTable userTable){
        try {
            Integer insert = userService.insert(userTable);
            return new BaseResponse<>(insert);
        } catch (Exception e) {
            LOGGER.error("xxxx, errorCode=1002", e);
            return new BaseResponse<>(new ErrorMsg(1002, "xxxx"));
        }
    }
}
