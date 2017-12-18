package com.test.demo.domain.dao;

import com.mysql.cj.api.jdbc.Statement;
import com.test.demo.domain.entity.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

@Repository
public class UserDao implements IUserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public Integer insert(UserTable userTable) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection con) -> {
            final String sql = "INSERT INTO user(`username`, `name`, `create_date`, `update_date`) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, userTable.getUsername());
            preparedStatement.setString(2, userTable.getName());
            preparedStatement.setObject(3, new Timestamp(userTable.getCreateDate().getTime()));
            preparedStatement.setObject(4, new Timestamp(userTable.getUpdateDate().getTime()));
            return preparedStatement;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public UserTable getById(Integer id) {
        final String sql = "select `id`,`username`,`name`,`create_date`,`update_date` from user WHERE id=?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new UserTable(rs.getInt("id"),
                rs.getString("username"),
                rs.getString("name"),
                rs.getTimestamp("create_date"),
                rs.getTimestamp("update_date")), id);
    }
}
