import utils.EndPoints;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.mapper.TypeRef;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.specification.RequestSpecification;
import models.User;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.config.RestAssuredConfig.newConfig;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RestAssuredGetTest {

    @BeforeAll
    public void beforeAll() {
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri(EndPoints.host)
                .setPort(EndPoints.port)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.ANY)
                .log(LogDetail.ALL)
                .build();
        RestAssured.requestSpecification = requestSpec;

    }

    @Test
    @Order(1)
    public void get_users() {
        List<User> users = when()
                .get(EndPoints.users)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(new TypeRef<>() {
                });

        for (User user : users) {
            user.print();
        }

    }

    @Test
    @Order(2)
    public void get_user() {
        given()
                .config(newConfig()
                        .jsonConfig(jsonConfig()
                                .numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL)))
                .pathParams("id", 1)
                .when()
                .get(EndPoints.user)
                .then()
                .log().body()
                .statusCode(200)
                .assertThat()
                .body("id", equalTo(1))
                .body("firstName", equalTo("Vasiliy"))
                .body("secondName", equalTo("Rubenstein"))
                .body("age", equalTo(42))
                .body("sex", equalTo("MALE"))
                .body("money", is(equalTo(new BigDecimal("1331140.00"))));
    }


}
