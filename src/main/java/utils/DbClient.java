package utils;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DbClient {

    Connection connection;

    public DbClient() {

        try {
            Class.forName("org.postgresql.Driver");
            String db = "jdbc:postgresql://77.50.236.203:4832/pflb_trainingcenter";
            String user = "pflb-at-read";
            String password = "PflbQaTraining2354";
            connection = DriverManager.getConnection(db, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> getList(String sql, BeanListHandler<T> beanListHandler) {
        QueryRunner runner = new QueryRunner();
        List<T> result = null;
        try {
            result = runner.query(connection, sql, beanListHandler);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        DbUtils.closeQuietly(connection);
        return result;
    }

    public <T> List<T> getColumn(String sql) {
        QueryRunner runner = new QueryRunner();
        List<T> result = null;
        try {
            result = runner.query(connection, sql, new ColumnListHandler<T>(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        DbUtils.closeQuietly(connection);
        return result;
    }
}
