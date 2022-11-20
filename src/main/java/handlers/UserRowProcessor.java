package handlers;

import models.Sex;
import models.User;
import org.apache.commons.dbutils.BasicRowProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.LinkedList;
import java.util.List;

public class UserRowProcessor extends BasicRowProcessor {

    @Override
    public List toBeanList(ResultSet rs, Class clazz) {
        try {
            List newlist = new LinkedList();
            while (rs.next()) {
                newlist.add(toBean(rs, clazz));
            }
            return newlist;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Object toBean(ResultSet rs, Class type) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setAge(rs.getInt("age"));
        user.setMoney(rs.getBigDecimal("money"));
        user.setFirstName(rs.getString("first_name"));
        user.setSecondName(rs.getString("second_name"));
        user.setSex(rs.getObject("sex") == null ? null : rs.getBoolean("sex"));
        return user;
    }
}