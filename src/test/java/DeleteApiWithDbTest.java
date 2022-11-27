import entities.CarEntity;
import entities.UserEntity;
import models.User;
import org.junit.jupiter.api.Test;
import models.RandomTestUser;

import java.io.IOException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteApiWithDbTest {

    UserEntity userEntity = new UserEntity();
    CarEntity carEntity = new CarEntity();
    @Test
    public void delete_deleteUser() throws IOException, SQLException, ClassNotFoundException {

        User apiUser = userEntity.add(new RandomTestUser());

        userEntity.delete(apiUser.getId());
        User dbResultUser = userEntity.dbGet(apiUser.getId());

        assertThat(dbResultUser).isNull();

    }

}
