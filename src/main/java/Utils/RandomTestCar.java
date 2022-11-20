package Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Car;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class RandomTestCar extends Car {
	public RandomTestCar() throws SQLException, ClassNotFoundException {

		String sql = "SELECT type_name FROM engine_type";
		List<String> engineTypes = new DbClient()
				.getColumn(sql);

		setEngineType(engineTypes.get(new Random().nextInt(engineTypes.size())));
		setMark(StringUtils.capitalize(RandomStringUtils.randomAlphabetic(new Random().nextInt(5) + 3)));
		setModel(StringUtils.capitalize(RandomStringUtils.randomAlphabetic(new Random().nextInt(5) + 3)));
		setPrice(new BigDecimal(new Random().nextFloat() * 1000000 + 3000).setScale(2, RoundingMode.HALF_UP));
	}
}
