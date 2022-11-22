import models.Car;
import org.junit.jupiter.api.Test;
import utils.RandomTestCar;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PostApiWithDb_ {

    @Test
    public void post_addcar() throws IOException, SQLException, ClassNotFoundException {

        Car randomCar = new RandomTestCar();
        Car apiCar = randomCar.add();

        Car dbCar = Car.getCarFromDb(apiCar.getId());
        assertThat(apiCar).isEqualToComparingFieldByField(dbCar);

    }

}
