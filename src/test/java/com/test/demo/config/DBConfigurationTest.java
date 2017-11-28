package com.test.demo.config;

import com.test.demo.Application;
import com.test.demo.valueobject.Room;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
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

        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from room");
        System.out.println(maps);


        List<Room> query = jdbcTemplate.query("SELECT * FROM room", (rs, rowNum) -> new Room(rs.getInt("id"),
                rs.getString("name"),
                rs.getString("comment"),
                null));
        System.out.println(query);
    }

    private ResultSetExtractor<Room> getRoomResultSetExtractor() {
        return (rs)->{return new Room(rs.getInt("id"), rs.getString("name"), rs.getString("comment"), null);};
    }
}