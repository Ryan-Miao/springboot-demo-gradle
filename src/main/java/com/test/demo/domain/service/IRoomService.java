package com.test.demo.domain.service;

import com.test.demo.domain.entity.RoomTable;
import com.test.demo.domain.valueobject.RoomRequest.RoomRequest;
import com.test.demo.domain.valueobject.response.BaseResponse;

import java.util.List;

public interface IRoomService {

    BaseResponse<RoomTable> findOne(Integer id);

    BaseResponse<Integer> saveRoom(RoomRequest roomRequest);

    BaseResponse<List<RoomTable>> findList();

    BaseResponse<Boolean> updateRoom(RoomRequest roomRequest);

    BaseResponse<Boolean> delete(Integer id);
}
