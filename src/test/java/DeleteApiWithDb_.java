import models.User;
import org.junit.jupiter.api.Test;
import models.RandomTestUser;
import utils.User.UserUtils;

import java.io.IOException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteApiWithDb_ {

    @Test
    public void delete_deleteUser() throws IOException, SQLException, ClassNotFoundException {

        User apiUser = UserUtils.add(new RandomTestUser());

        UserUtils.delete(apiUser.getId());
        User dbResultUser = UserUtils.getUserFromDb(apiUser.getId());

        assertThat(dbResultUser).isNull();

    }

}
