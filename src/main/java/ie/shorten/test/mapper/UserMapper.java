package ie.shorten.test.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
 
import ie.shorten.test.entity.User;
import org.springframework.jdbc.core.RowMapper;
 
public class UserMapper implements RowMapper<User> {
 
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
 
        String userName = rs.getString("Username");
        String password = rs.getString("Password");
 
        return new User(userName, password);
    }
 
}