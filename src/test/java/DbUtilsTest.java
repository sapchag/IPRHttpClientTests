import models.User;
import handlers.UserHandler;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DbUtilsTest {
    private Connection connection;

    @BeforeAll
    public void setupDB() throws Exception {
        Class.forName("org.postgresql.Driver");
        String db
                = "jdbc:postgresql://77.50.236.203:4832/pflb_trainingcenter";
        String user = "pflb-at-read";
        String password = "PflbQaTraining2354";
        connection = DriverManager.getConnection(db, user, password);
    }

    @AfterAll
    public void closeBD() {
        DbUtils.closeQuietly(connection);
    }

    @Test
    public void givenResultHandler_whenExecutingQuery_thenExpectedList()
            throws SQLException {
        UserHandler beanListHandler
                = new UserHandler();

        QueryRunner runner = new QueryRunner();
        List<User> users
                = runner.query(connection, "SELECT * FROM person", beanListHandler);

    }
}
