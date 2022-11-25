import models.Car;
import models.User;
import org.junit.jupiter.api.Test;
import utils.Car.CarUtils;
import utils.Car.RandomTestCar;
import utils.User.RandomTestUser;
import utils.User.UserUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class PostApiWithDb_ {

    @Test
    public void post_addcar() throws IOException, SQLException, ClassNotFoundException {

        Car randomCar = new RandomTestCar();
        Car apiCar = CarUtils.add(randomCar);

        Car dbCar = CarUtils.getCarFromDb(apiCar.getId());
        assertThat(apiCar).isEqualToComparingFieldByField(dbCar);

    }

    @Test
    public void post_addUser() throws IOException, SQLException, ClassNotFoundException {

        User randomUser = new RandomTestUser();
        User apiUser = UserUtils.add(randomUser);

        User dbUser = UserUtils.getUserFromDb(apiUser.getId());
        assertThat(apiUser).isEqualToComparingFieldByField(dbUser);
    }

    @Test
    public void post_buyCar() throws IOException, SQLException, ClassNotFoundException {

        Car randomCar = new RandomTestCar();
        randomCar.setId(CarUtils.add(randomCar).getId());

        User randomUser = new RandomTestUser();
        randomUser.setMoney(randomCar.getPrice());
        randomUser.setId(UserUtils.add(randomUser).getId());

        User userAfterByedCar = UserUtils.buyCar(randomUser, randomCar);
        assertThat(userAfterByedCar.getMoney()).isEqualByComparingTo(BigDecimal.ZERO);

        User dbUser = UserUtils.getUserFromDb(randomUser.getId());
        assertThat(userAfterByedCar).isEqualToComparingFieldByField(dbUser);

    }

}
