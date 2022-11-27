import entities.CarEntity;
import entities.UserEntity;
import models.Car;
import models.User;
import org.junit.jupiter.api.Test;
import utils.Car.CarUtils;
import models.RandomTestCar;
import models.RandomTestUser;
import utils.User.UserUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class PostApiWithDb_ {

    UserEntity userEntity = new UserEntity();
    CarEntity carEntity = new CarEntity();
    @Test
    public void post_addcar() throws IOException, SQLException, ClassNotFoundException {

        Car randomCar = new RandomTestCar();
        Car apiCar = carEntity.add(randomCar);

        Car dbCar = CarUtils.getCarFromDb(apiCar.getId());
        assertThat(apiCar).isEqualToComparingFieldByField(dbCar);

    }

    @Test
    public void post_addUser() throws IOException, SQLException, ClassNotFoundException {

        User randomUser = new RandomTestUser();
        User apiUser = userEntity.add(randomUser);

        User dbUser = UserUtils.getUserFromDb(apiUser.getId());
        assertThat(apiUser).isEqualToComparingFieldByField(dbUser);
    }

    @Test
    public void post_buyCar() throws IOException, SQLException, ClassNotFoundException {

        Car randomCar = new RandomTestCar();
        randomCar.setId(carEntity.add(randomCar).getId());

        User randomUser = new RandomTestUser();
        randomUser.setMoney(randomCar.getPrice());
        randomUser.setId(userEntity.add(randomUser).getId());

        User userAfterByedCar = UserUtils.buyCar(randomUser, randomCar);
        assertThat(userAfterByedCar.getMoney()).isEqualByComparingTo(BigDecimal.ZERO);

        User dbUser = userEntity.dbGet(randomUser.getId());
        assertThat(userAfterByedCar).isEqualToComparingFieldByField(dbUser);

    }

}
