import com.fasterxml.jackson.databind.ObjectMapper;
import models.Car;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddRestTest {

	@Test
	@Order(1)
	public void post_addcar() throws IOException {

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

		car.print();

	}

	@Test
	@Order(2)
	public void post_addUser() throws IOException {

		String SAMPLE_URL = "http://77.50.236.203:4880/addUser";

		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(SAMPLE_URL);
		request.addHeader("content-type", "application/json");

		JSONObject json = new JSONObject();
		json.put("firstName", "Vasiliy");
		json.put("secondName", "Rubenstein");
		json.put("age", 42);
		json.put("sex", "MALE");
		json.put("money", 1000000);
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

	@Test
	@Order(3)
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