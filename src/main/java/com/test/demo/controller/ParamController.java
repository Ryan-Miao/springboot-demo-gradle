package com.test.demo.controller;

import com.test.demo.entity.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Ryan on 2017/11/16/0016.
 */
@RestController
@RequestMapping("/param")
public class ParamController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParamController.class);

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

        LOGGER.info("The params is {}", params);

        List<Long> roomIds = new ArrayList<>();
        roomIds.add(1L);
        roomIds.add(2L);
        roomIds.add(3L);

        return roomIds;
    }

    @GetMapping("/hotels/{htid}/rooms/{roomId}")
    public Room getRoomById(
            @PathVariable String htid,
            @PathVariable Integer roomId
    ){
        if (htid.equals("6606")){
            final Room room = new Room();
            room.setComment("None");
            room.setRoomId(roomId);
            room.setRoomName("豪华双人间");

            return room;
        }

        return null;
    }

    @PostMapping("/hotels/{htid}/rooms")
    public Integer addRoom(
           @Valid @RequestBody Room room,
            @RequestHeader(name = "transactionId") String transactionId
    ){
        final Random random = new Random();
        final int id = random.nextInt(10);
        room.setRoomId(id);

        LOGGER.info("Add a room: {}", room);

        return id;
    }

    @GetMapping("/hotels/{htid}/rooms/ids")
    public String getRoomsWithIds(@RequestParam List<Integer> ids){
        String s = ids.toString();
        LOGGER.info(s);
        return s;
    }
}
