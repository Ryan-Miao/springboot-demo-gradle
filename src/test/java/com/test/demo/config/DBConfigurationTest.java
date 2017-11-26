package com.test.demo.config;

import com.test.demo.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import({Application.class, DBConfiguration.class})
public class DBConfigurationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testSelect(){

        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from room");
        System.out.println(maps);
    }
}