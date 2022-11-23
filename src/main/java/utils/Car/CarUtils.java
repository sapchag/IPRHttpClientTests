package utils.Car;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Car;
import models.User;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.http.HttpEntity;
import utils.ApiClient;
import utils.DbClient;
import utils.RestPaths;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class CarUtils {

    public void print(Car car) {
        System.out.print("Id:" + car.getId());
        System.out.print(" EngineType:" + car.getEngineType());
        System.out.print(" Mark:" + car.getMark());
        System.out.print(" Model:" + car.getModel());
        System.out.println(" Price:" + car.getPrice());

    }

    public static String getJson(Car car) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(car);
    }

    public static Car getCarFromDb(int id) throws SQLException, ClassNotFoundException {
        return getCarsFromDb().stream().filter(s -> s.getId() == id).collect(Collectors.toList()).get(0);
    }

    public static List<Car> getCarsFromDb() throws SQLException, ClassNotFoundException {
        String sql = "SELECT car.id, car.mark, car.model, " +
                "car.price, engine_type.type_name as engineType " +
                "FROM car inner join engine_type " +
                "on car.engine_type_id=engine_type.id";
        return new DbClient().getList(sql, new BeanListHandler<>(Car.class));
    }

    public static List<Car> getApiCars() throws IOException {
        HttpEntity httpEntity = new ApiClient()
                .setHttpMethod(ApiClient.HTTP_METHOD.GET)
                .setPathSegments(RestPaths.cars)
                .setResponseCode(200)
                .sendRequestAndGetResponse()
                .getEntity();

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(
                httpEntity.getContent(), new TypeReference<>() {
                });
    }

    public static Car add(Car car) throws IOException {
        HttpEntity httpEntity = new ApiClient()
                .setHttpMethod(ApiClient.HTTP_METHOD.POST)
                .setPathSegments(RestPaths.addCar)
                .setJson(CarUtils.getJson(car))
                .setResponseCode(201)
                .sendRequestAndGetResponse().getEntity();

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(httpEntity.getContent(), Car.class);
    }

}
