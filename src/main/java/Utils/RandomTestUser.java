package Utils;

import models.Sex;
import models.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Random;

public class RandomTestUser extends User {
	public RandomTestUser() {
		setFirstName(StringUtils.capitalize(RandomStringUtils.randomAlphabetic(new Random().nextInt(5) + 3)));
		setSecondName(StringUtils.capitalize(RandomStringUtils.randomAlphabetic(new Random().nextInt(5) + 3)));
		setAge(new Random().nextInt(70) + 18);
		setMoney(new BigDecimal(new Random().nextFloat()));
		//TODO
		//setSex(new Random().nextBoolean());
	}
}
