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
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
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

        final String sql = "INSERT INTO room(`name`, `comment`, `create_date`, `update_date`) VALUES (?,?,?,?)";
        final int rs = jdbcTemplate.update(sql,
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
        final String sql = "select `id`,`name`,`comment`,`create_date`,`update_date` from room WHERE id=?";
        final RoomTable roomTable = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new RoomTable(rs.getInt("id"),
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
        final String sql = "select `id`,`name`,`comment`,`create_date`,`update_date` from room WHERE id>? LIMIT 0,2";
        final List<RoomTable> roomTableList = jdbcTemplate.query(sql, (rs, rowNum) -> new RoomTable(rs.getInt("id"),
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
        final String sql = "DELETE FROM room WHERE `id`=?";
        final int update = jdbcTemplate.update(sql, 1);
        Assert.assertEquals(1, update);

        final List<Map<String, Object>> maps = jdbcTemplate.queryForList("select id from room where `id`=?", 1);
        Assert.assertTrue(maps.isEmpty());

        try {
            jdbcTemplate.queryForObject("select id from room where `id`=?", Integer.class, 1);
        } catch (EmptyResultDataAccessException e) {
            System.err.println("Get a null result, the data is not exist in the database."+e.getMessage());
        }

        final int count = jdbcTemplate.queryForObject("select count(*) from room", Integer.class);
        Assert.assertEquals(2, count);
    }

    @Transactional
    @Test
    public void testUpdate(){
        final String sql = "update room set `update_date`=?, `comment`=? where id=?";
        final int update = jdbcTemplate.update(sql, new Object[]{new Date(), "booked", 1});
        Assert.assertEquals(1, update);

        final String getSql = "select `id`,`name`,`comment`,`create_date`,`update_date` from room WHERE id=?";
        final RoomTable roomTable = jdbcTemplate.queryForObject(getSql, (rs, rowNum) -> new RoomTable(rs.getInt("id"),
                rs.getString("name"),
                rs.getString("comment"),
                rs.getTime("create_date"),
                rs.getTime("update_date")), 1);
        System.out.println(roomTable);
        Assert.assertEquals("booked", roomTable.getComment());
    }

    @Transactional
    @Test
    public void testUpdateForDelete(){
        final String sql = "update room set `update_date`=?, `active`=1 where id=?";
        final int update = jdbcTemplate.update(sql, new Object[]{new Date(), 1});
        Assert.assertEquals(1, update);

        final String getSql = "select `active` from room WHERE id=?";
        Integer active = jdbcTemplate.queryForObject(getSql, Integer.class, 1);
        System.out.println(active);
        Assert.assertTrue(active == 1 );
    }


}