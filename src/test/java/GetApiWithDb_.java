import entities.CarEntity;
import entities.UserEntity;
import models.Car;
import models.User;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class GetApiWithDb_ {

    UserEntity userEntity = new UserEntity();
    CarEntity carEntity = new CarEntity();

    @Test
    public void getUsers() {

        List<User> apiUsers = userEntity.apiGetAll();
        List<User> dbUsers = userEntity.dbGetAll();

        for (User apiUser : apiUsers) {
            User dbUser = dbUsers.stream().filter(s -> s.getId().equals(apiUser.getId()))
                    .findFirst().orElseThrow();
            assertThat(apiUser).isEqualToComparingFieldByField(dbUser);
        }
    }

    @Test
    public void getUser() {
        List<User> dbUsers = userEntity.dbGetAll();
        User randomDbUser = dbUsers.get(new Random().nextInt(dbUsers.size()));
        User apiUser = userEntity.apiGet(randomDbUser.getId());

        assertThat(randomDbUser).isEqualToComparingFieldByField(apiUser);
    }

    @Test
    public void getCars() {
        List<Car> dbCars = carEntity.dbGetAll();
        List<Car> apiCars = carEntity.apiGetAll();

        for (Car apiCar : apiCars) {
            Car dbCar = dbCars.stream().filter(s -> s.getId().equals(apiCar.getId()))
                    .findFirst().orElseThrow();
            assertThat(apiCar).isEqualToComparingFieldByField(dbCar);
        }
    }

}
