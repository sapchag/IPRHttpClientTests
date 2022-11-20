import Utils.EndPoints;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.specification.RequestSpecification;
import models.User;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.sql.SQLException;

import static io.restassured.RestAssured.given;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.config.RestAssuredConfig.newConfig;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RestAssuredPostEditTest {

	@BeforeAll
	public void beforeAll() {
		RequestSpecification requestSpec = new RequestSpecBuilder()
				.setBaseUri(EndPoints.host)
				.setPort(EndPoints.port)
				.setAccept(ContentType.JSON)
				.setContentType(ContentType.JSON)
				.log(LogDetail.ALL)
				.build();
		RestAssured.requestSpecification = requestSpec;

	}

	@Test
	@Order(1)
	public void post_userBuyCar() throws JsonProcessingException, SQLException, ClassNotFoundException {

		 given()
				.header("Content-type", "application/json")
				.pathParam("userId", 4)
				.pathParam("carId", 99)
				.when()
				.post(EndPoints.userBuyCar)
				.then()
				.log().body()
				.extract()
				.body();


	}

	@Test
	@Order(2)
	public void get_houseSettle() {

		 given()
				.header("Content-type", "application/json")
				.pathParam("houseId", 13)
				.pathParam("userId", 3)
				.when()
				.post(EndPoints.houseSettle)
				.then()
				.log().body()
				.extract()
				.body();
	}


}
