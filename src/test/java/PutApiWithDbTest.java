import entities.CarEntity;
import entities.UserEntity;
import models.RandomTestUser;
import models.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class PutApiWithDbTest {

    UserEntity userEntity = new UserEntity();
    CarEntity carEntity = new CarEntity();

    @Test
    public void put_updateUser() throws IOException, SQLException, ClassNotFoundException {

        List<User> dbUsers = userEntity.dbGetAll();
        User updatedUser = dbUsers.get(new Random().nextInt(dbUsers.size()));
        User randomUser = new RandomTestUser();
        randomUser.setId(updatedUser.getId());

        User apiResultUser = userEntity.update(randomUser);
        User dbResultUser = userEntity.dbGet(updatedUser.getId());

        assertThat(apiResultUser).isEqualToComparingFieldByField(randomUser);
        assertThat(dbResultUser).isEqualToComparingFieldByField(randomUser);

    }

}
