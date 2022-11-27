import models.RandomTestCar;
import models.RandomTestUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Car;
import models.User;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.stream.Stream;

import static models.Sex.FEMALE;
import static models.Sex.MALE;

public class PostApiWithDb {

	private static Stream<Arguments> post_addUser_pair_test() {
		return Stream.of(
				Arguments.of("A", "", 1, new BigDecimal("0"), FEMALE),
				Arguments.of("A", "Petrov", 18, new BigDecimal("-0.01"), MALE),
				Arguments.of("", "", 18, new BigDecimal("0.01"), MALE),
				Arguments.of("", "Petrov", 0, new BigDecimal("0.01"), FEMALE),
				Arguments.of("", "A", 0, new BigDecimal("0"), MALE),
				Arguments.of("", "A", 1, new BigDecimal("-0.01"), FEMALE),
				Arguments.of("Petrov", "Petrov", 0, new BigDecimal("-0.01"), MALE),
				Arguments.of("Petrov", "A", 1, new BigDecimal("0.01"), FEMALE),
				Arguments.of("Petrov", "A", 18, new BigDecimal("0.01"), MALE),
				Arguments.of("Petrov", "", 0, new BigDecimal("0"), FEMALE),
				Arguments.of("A", "A", 18, new BigDecimal("0"), MALE),
				Arguments.of("A", "A", 0, new BigDecimal("-0.01"), FEMALE),
				Arguments.of("A", "", 0, new BigDecimal("0.01"), MALE),
				Arguments.of("A", "Petrov", 1, new BigDecimal("0.01"), FEMALE)
		);
	}

	@Test
	@Order(1)
	public void post_addcar() throws IOException, SQLException, ClassNotFoundException {

		Car randomCar = new RandomTestCar();

		String SAMPLE_URL = "http://77.50.236.203:4880/addCar";

		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(SAMPLE_URL);
		request.addHeader("content-type", "application/json");

		Car car = new Car();
		car.setEngineType("Electric");
		car.setMark("Tesla");
		car.setModel("Model X");
		car.setPrice(new BigDecimal(70000));

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(car);

		StringEntity params = new StringEntity(json);
		request.setEntity(params);

		//Printing the method used
		System.out.println("Request Type: " + request.getMethod());
		HttpResponse httpResponse = httpClient.execute(request);

		car = objectMapper.readValue(httpResponse.getEntity().getContent(), Car.class);

	}

	@ParameterizedTest
	@MethodSource
	@Order(2)
	public void post_addUser_pair_test(String firstName, String secondName, int age, BigDecimal money, Boolean sex) throws IOException {

		String SAMPLE_URL = "http://77.50.236.203:4880/addUser";

		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(SAMPLE_URL);
		request.addHeader("content-type", "application/json");

		User user = new User();
		user.setFirstName(firstName);
		user.setSecondName(secondName);
		user.setAge(age);
		user.setSex(sex);
		user.setMoney(money);

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(user);
		StringEntity params = new StringEntity(json);

		request.setEntity(params);

		//Printing the method used
		System.out.println("Request Type: " + request.getMethod());
		HttpResponse httpResponse = httpClient.execute(request);
		System.out.println("Result: " + EntityUtils.toString(httpResponse.getEntity()));

		Scanner sc = new Scanner(httpResponse.getEntity().getContent());
		System.out.println(httpResponse.getStatusLine());
		while (sc.hasNext()) {
			System.out.println(sc.nextLine());
		}
	}

	@Test
	@Order(3)
	public void post_addUser() throws IOException {

		String SAMPLE_URL = "http://77.50.236.203:4880/addUser";

		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(SAMPLE_URL);
		request.addHeader("content-type", "application/json");

		User user = new RandomTestUser();

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(user);
		StringEntity params = new StringEntity(json);

		request.setEntity(params);

		//Printing the method used
		System.out.println("Request Type: " + request.getMethod());
		HttpResponse httpResponse = httpClient.execute(request);
		System.out.println("Result: " + EntityUtils.toString(httpResponse.getEntity()));

		Scanner sc = new Scanner(httpResponse.getEntity().getContent());
		System.out.println(httpResponse.getStatusLine());
		while (sc.hasNext()) {
			System.out.println(sc.nextLine());
		}
	}

	@Test
	@Order(4)
	public void post_addHouse() throws IOException {

		String SAMPLE_URL = "http://77.50.236.203:4880/addHouse";

		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(SAMPLE_URL);
		request.addHeader("content-type", "application/json");

		JSONObject json = new JSONObject();
		json.put("floorCount", 2);
		json.put("price", 230);

		JSONObject parkingPlace1 = new JSONObject();
		parkingPlace1.put("isWarm", true);
		parkingPlace1.put("isCovered", true);
		parkingPlace1.put("placesCount", 1);
		JSONObject parkingPlace2 = new JSONObject();
		parkingPlace2.put("isWarm", false);
		parkingPlace2.put("isCovered", false);
		parkingPlace2.put("placesCount", 2);

		JSONArray parkingPlaces = new JSONArray();
		parkingPlaces.add(parkingPlace1);
		parkingPlaces.add(parkingPlace2);

		json.put("parkingPlaces", parkingPlaces);

		StringEntity params = new StringEntity(json.toString());
		request.setEntity(params);

		//Printing the method used
		System.out.println("Request Type: " + request.getMethod());
		HttpResponse httpResponse = httpClient.execute(request);
		System.out.println("Result: " + EntityUtils.toString(httpResponse.getEntity()));

		Scanner sc = new Scanner(httpResponse.getEntity().getContent());
		System.out.println(httpResponse.getStatusLine());
		while (sc.hasNext()) {
			System.out.println(sc.nextLine());
		}
	}
}
