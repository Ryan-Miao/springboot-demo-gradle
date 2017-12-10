package com.test.demo.domain.dao;

import com.test.demo.domain.entity.RoomTable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
//打开以下注解则启用真实数据库测试，否则启用h2
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class IRoomRepositoryTest {

    @Autowired
    private IRoomRepository roomRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testSave(){
        final RoomTable roomTable = new RoomTable("name1", "comment", new Date(), new Date());
        RoomTable save = roomRepository.save(roomTable);
        System.out.println(save.getId());

        final RoomTable roomTable2 = roomRepository.findOne(1);
        System.out.println(roomTable2);
    }

    @Test
    public void testSelectOne(){
        final RoomTable roomTable = roomRepository.findOne(1);
        System.out.println(roomTable);
    }
}