import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Car;
import models.User;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GetRestTest {

	@Test
	@Order(1)
	public void get_users() throws IOException {

		String SAMPLE_URL = "http://77.50.236.203:4880/users";

		HttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(SAMPLE_URL);
		//Printing the method used
		System.out.println("Request Type: " + httpget.getMethod());
		HttpResponse httpResponse = httpClient.execute(httpget);

		ObjectMapper objectMapper = new ObjectMapper();
		List<User> users = objectMapper.readValue(
				httpResponse.getEntity().getContent(), new TypeReference<>() {
				});

	}

	@Test
	public void get_user_id() throws IOException {

		String SAMPLE_URL = "http://77.50.236.203:4880/user/1";

		HttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(SAMPLE_URL);
		//Printing the method used
		System.out.println("Request Type: " + httpget.getMethod());
		HttpResponse httpResponse = httpClient.execute(httpget);
		ObjectMapper objectMapper = new ObjectMapper();
		User user = objectMapper.readValue(httpResponse.getEntity().getContent(), User.class);
	}

	@Test
	public void get_cars() throws IOException {

		String SAMPLE_URL = "http://77.50.236.203:4880/cars";

		HttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(SAMPLE_URL);
		//Printing the method used
		System.out.println("Request Type: " + httpget.getMethod());
		HttpResponse httpResponse = httpClient.execute(httpget);

		ObjectMapper objectMapper = new ObjectMapper();
		List<Car> cars = objectMapper.readValue(
				httpResponse.getEntity().getContent(), new TypeReference<>() {
				});

	}

}