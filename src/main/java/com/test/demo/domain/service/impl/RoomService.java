package com.test.demo.domain.service.impl;

import com.google.common.collect.ImmutableMap;
import com.test.demo.domain.dao.IRoomRepository;
import com.test.demo.domain.entity.RoomTable;
import com.test.demo.domain.log.SystemEvent;
import com.test.demo.domain.service.IRoomService;
import com.test.demo.domain.valueobject.RoomRequest.RoomRequest;
import com.test.demo.domain.valueobject.response.BaseResponse;
import com.test.demo.domain.valueobject.response.ErrorMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class RoomService implements IRoomService{

    private final static Logger LOGGER = LoggerFactory.getLogger(RoomService.class);

    @Autowired
    private final IRoomRepository roomRepository;

    public RoomService(IRoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public BaseResponse<RoomTable> findOne(Integer id) {

        try {
            final RoomTable one = roomRepository.findOne(id);
            if (one == null){
                ErrorMsg errorMsg = new ErrorMsg(SystemEvent.FIND_ONE_ROOM_NOT_EXIST.getId(), SystemEvent.FIND_ONE_ROOM_NOT_EXIST.getDetail());
                return new BaseResponse<>(errorMsg);
            }

            return new BaseResponse<>(one);
        } catch (Exception e) {
            Map<String, Object> map = new HashMap<>();
            map.put("event", SystemEvent.FIND_ONE_ROOM_FAILED);
            map.put("errorMsg", e.getMessage());
            map.put("data", ImmutableMap.of("id", id));

            LOGGER.error(map.toString(), e);

            return new BaseResponse<>(new ErrorMsg(SystemEvent.FIND_ONE_ROOM_FAILED.getId(), e.getMessage()));
        }

    }

    @Override
    public BaseResponse<Integer> saveRoom(RoomRequest roomRequest) {

        try {
            final RoomTable save = roomRepository.save(new RoomTable(roomRequest.getName(), roomRequest.getComment(), new Date(), new Date()));
            return new BaseResponse<>(save.getId());
        } catch (Exception e) {
            Map<String, Object> map = new HashMap<>();
            map.put("event", SystemEvent.SAVE_ONE_ROOM_FAILED);
            map.put("errorMsg", e.getMessage());
            map.put("data", roomRequest);

            LOGGER.error(map.toString(), e);

            return new BaseResponse<>(new ErrorMsg(SystemEvent.SAVE_ONE_ROOM_FAILED.getId(), e.getMessage()));
        }

    }
}
