package com.test.demo.config;

import com.mysql.cj.api.jdbc.Statement;
import com.test.demo.Application;
import com.test.demo.domain.entity.RoomTable;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import({Application.class, DBConfiguration.class})
public class DBConfigurationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testSelect() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM room");
        System.out.println(maps);
    }

    @Transactional
    @Test
    public void testInsert() {
        final RoomTable room = new RoomTable("总统套房", "", new Date(), new Date());

        String sql = "INSERT INTO room(`name`, `comment`, `create_date`, `update_date`) VALUES (?,?,?,?)";
        int rs = jdbcTemplate.update(sql,
                room.getName(), room.getComment(), room.getCreateDate(), room.getUpdateDate());
        System.out.println(rs);
    }

    @Transactional
    @Test
    public void testInsertAndGetKey() {
        final RoomTable room = new RoomTable("Doule Bed", "no", new Date(), new Date());
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        final int update = jdbcTemplate.update((Connection con) -> {
            final String sql = "INSERT INTO room(`name`, `comment`, `create_date`, `update_date`) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, room.getName());
            preparedStatement.setString(2, room.getComment());
            preparedStatement.setObject(3, new Timestamp(room.getCreateDate().getTime()));
            preparedStatement.setObject(4, new Timestamp(room.getUpdateDate().getTime()));
            return preparedStatement;
        }, keyHolder);
        System.out.println("The number of success:"+update);
        System.out.println("The primary key of insert row: "+keyHolder.getKey().intValue());


        final List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM room");
        System.out.println(maps);
    }


    @Test
    public void testSelectOne(){
        String sql = "select `id`,`name`,`comment`,`create_date`,`update_date` from room WHERE id=?";
        RoomTable roomTable = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new RoomTable(rs.getInt("id"),
                rs.getString("name"),
                rs.getString("comment"),
                rs.getTime("create_date"),
                rs.getTime("update_date")), 3);
        System.out.println(roomTable);
        Assert.assertTrue(3== roomTable.getId());
        Assert.assertNotNull(roomTable.getCreateDate());
    }

    @Test
    public void testSelectList(){
        String sql = "select `id`,`name`,`comment`,`create_date`,`update_date` from room WHERE id>? LIMIT 0,2";
        List<RoomTable> roomTableList = jdbcTemplate.query(sql, (rs, rowNum) -> new RoomTable(rs.getInt("id"),
                rs.getString("name"),
                rs.getString("comment"),
                rs.getTime("create_date"),
                rs.getTime("update_date")), 1);

        System.out.println(roomTableList);
        Assert.assertEquals(2, roomTableList.size());
    }


    @Transactional
    @Test
    public void testDelete(){
        //insert first

    }


}