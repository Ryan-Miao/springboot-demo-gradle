package com.test.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ryan on 2017/11/16/0016.
 */
@RestController
@RequestMapping("/param")
public class ParamController {

    @GetMapping("/hotels/{htid}/rooms")
    public List<Long> getRooms(
            @PathVariable String htid,
            @RequestParam String langId,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
            @RequestParam(value = "offset", required = false, defaultValue = "1") int offset
    ){
        final Map<String, Object> params = new HashMap<>();
        params.put("hotelId", htid);
        params.put("langId", langId);
        params.put("limit", limit);
        params.put("offset", offset);

        List<Long> roomIds = new ArrayList<>();
        roomIds.add(1L);
        roomIds.add(2L);
        roomIds.add(3L);

        return roomIds;
    }
}
