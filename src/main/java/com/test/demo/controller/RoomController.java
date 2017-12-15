package com.test.demo.controller;

import com.test.demo.domain.entity.RoomTable;
import com.test.demo.domain.service.IRoomService;
import com.test.demo.domain.valueobject.RoomRequest.RoomRequest;
import com.test.demo.domain.valueobject.response.BaseResponse;
import com.test.demo.domain.valueobject.response.IApiResponse;
import com.test.demo.domain.valueobject.response.ResponseMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Ryan Miao on 12/14/17.
 */
@Api(value = "Room", description = "Room API")
@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    @Autowired
    private final IRoomService roomService;

    public RoomController(IRoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/{id}")
    @ApiOperation("get by id.")
    public ResponseEntity<IApiResponse<RoomTable>> findById(@PathVariable Integer id) {
        BaseResponse<RoomTable> one = roomService.findOne(id);

        return ResponseMapper.map(one);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("delete by id.")
    public ResponseEntity<IApiResponse<Boolean>> deleteById(@PathVariable Integer id) {
        BaseResponse<Boolean> one = roomService.delete(id);

        return ResponseMapper.map(one);
    }

    @GetMapping("/")
    @ApiOperation("Get all the rooms.")
    public ResponseEntity<IApiResponse<List<RoomTable>>> findList() {
        BaseResponse<List<RoomTable>> list = roomService.findList();

        return ResponseMapper.map(list);
    }

    @PostMapping("/")
    @ApiOperation("Save a room.")
    public ResponseEntity<IApiResponse<Integer>> saveRoom(@RequestBody RoomRequest roomRequest){
        BaseResponse<Integer> id = roomService.saveRoom(roomRequest);

        return ResponseMapper.map(id);
    }

    @PutMapping("/")
    @ApiOperation("Update a room.")
    public ResponseEntity<IApiResponse<Boolean>> updateRoom(@RequestBody RoomRequest roomRequest){
        BaseResponse<Boolean> id = roomService.updateRoom(roomRequest);

        return ResponseMapper.map(id);
    }


}
