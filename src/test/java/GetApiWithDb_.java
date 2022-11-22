import models.Car;
import models.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class GetApiWithDb_ {

	@Test
	public void getUsers() throws Exception {
		List<User> apiUsers = User.getUsersFromApi();
		List<User> dbUsers = User.getUsersFromDb();

		for (User apiUser : apiUsers) {
			User dbUser = dbUsers.stream().filter(s->s.getId().equals(apiUser.getId()))
							.findFirst().orElseThrow();
			assertThat(apiUser).isEqualToComparingFieldByField(dbUser);
		}
	}

	@Test
	public void getUser() throws SQLException, IOException, ClassNotFoundException {
		List<User> dbUsers = User.getUsersFromDb();
		User randomDbUser = dbUsers.get(new Random().nextInt(dbUsers.size()));
		User apiUser = User.getUserFromApi(randomDbUser.getId());

		assertThat(randomDbUser).isEqualToComparingFieldByField(apiUser);
	}

	@Test
	public void getCars() throws SQLException, IOException, ClassNotFoundException {
		List<Car> dbCars = Car.getCarsFromDb();
		List<Car> apiCars = Car.getApiCars();

		for (Car apiCar : apiCars) {
			Car dbCar = dbCars.stream().filter(s->s.getId().equals(apiCar.getId()))
					.findFirst().orElseThrow();
			assertThat(apiCar).isEqualToComparingFieldByField(dbCar);
		}
	}

}
