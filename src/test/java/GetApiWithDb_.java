import models.Car;
import models.User;
import org.junit.jupiter.api.Test;
import utils.Car.CarUtils;
import utils.User.UserUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class GetApiWithDb_ {

	@Test
	public void getUsers() throws Exception {
		List<User> apiUsers = UserUtils.getUsersFromApi();
		List<User> dbUsers = UserUtils.getUsersFromDb();

		for (User apiUser : apiUsers) {
			User dbUser = dbUsers.stream().filter(s->s.getId().equals(apiUser.getId()))
							.findFirst().orElseThrow();
			assertThat(apiUser).isEqualToComparingFieldByField(dbUser);
		}
	}

	@Test
	public void getUser() throws SQLException, IOException, ClassNotFoundException {
		List<User> dbUsers = UserUtils.getUsersFromDb();
		User randomDbUser = dbUsers.get(new Random().nextInt(dbUsers.size()));
		User apiUser = UserUtils.getUserFromApi(randomDbUser.getId());

		assertThat(randomDbUser).isEqualToComparingFieldByField(apiUser);
	}

	@Test
	public void getCars() throws SQLException, IOException, ClassNotFoundException {
		List<Car> dbCars = CarUtils.getCarsFromDb();
		List<Car> apiCars = CarUtils.getApiCars();

		for (Car apiCar : apiCars) {
			Car dbCar = dbCars.stream().filter(s->s.getId().equals(apiCar.getId()))
					.findFirst().orElseThrow();
			assertThat(apiCar).isEqualToComparingFieldByField(dbCar);
		}
	}

}
