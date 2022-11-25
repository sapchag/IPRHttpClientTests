import models.User;
import org.junit.jupiter.api.Test;
import utils.User.RandomTestUser;
import utils.User.UserUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class PutApiWithDb_ {

    @Test
    public void put_updateUser() throws IOException, SQLException, ClassNotFoundException {

        List<User> dbUsers = UserUtils.getUsersFromDb();
        User updatedUser = dbUsers.get(new Random().nextInt(dbUsers.size()));
        User randomUser = new RandomTestUser();

        User apiResultUser = UserUtils.update(randomUser, updatedUser.getId());
        User dbResultUser = UserUtils.getUserFromDb(updatedUser.getId());
        randomUser.setId(updatedUser.getId());

        assertThat(apiResultUser).isEqualToComparingFieldByField(randomUser);
        assertThat(dbResultUser).isEqualToComparingFieldByField(randomUser);

    }

}
