import entities.CarEntity;
import entities.UserEntity;
import models.Car;
import models.User;
import org.junit.jupiter.api.Test;
import models.RandomTestCar;
import models.RandomTestUser;

import static org.assertj.core.api.Assertions.assertThat;

public class PostApiWithDb_ {

    UserEntity userEntity = new UserEntity();
    CarEntity carEntity = new CarEntity();
    @Test
    public void post_addcar() {

        Car randomCar = new RandomTestCar();
        Car apiCar = carEntity.add(randomCar);

        Car dbCar = carEntity.dbGet(apiCar.getId());
        assertThat(apiCar).isEqualToComparingFieldByField(dbCar);

    }

    @Test
    public void post_addUser() {

        User randomUser = new RandomTestUser();
        User apiUser = userEntity.add(randomUser);

        User dbUser = userEntity.dbGet(apiUser.getId());
        assertThat(apiUser).isEqualToComparingFieldByField(dbUser);
    }

}
