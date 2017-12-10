package com.test.demo.domain.dao;

import com.test.demo.domain.entity.RoomTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoomRepository extends JpaRepository<RoomTable, Integer> {

}
