package models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.http.HttpEntity;
import utils.ApiClient;
import utils.DbClient;
import utils.RestPaths;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Car {

    Integer id;
    String engineType;
    String mark;
    String model;
    BigDecimal price;

    public Car() {
    }

    public void print() {
        System.out.print("Id:" + getId());
        System.out.print(" EngineType:" + getEngineType());
        System.out.print(" Mark:" + getMark());
        System.out.print(" Model:" + getModel());
        System.out.println(" Price:" + getPrice());

    }

    public String getJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
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
                .sendRequestAndGetResponse().getEntity();

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(
                httpEntity.getContent(), new TypeReference<>() {
                });
    }

	public Car add() throws IOException {
		HttpEntity httpEntity = new ApiClient()
				.setHttpMethod(ApiClient.HTTP_METHOD.POST)
				.setPathSegments(RestPaths.addCar)
                .setJson(this.getJson())
				.sendRequestAndGetResponse().getEntity();

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(
				httpEntity.getContent(), new TypeReference<>() {
				});
	}

    public Car(Integer id, String engineType, String mark, String model, BigDecimal price) {
        this.id = id;
        this.engineType = engineType;
        this.mark = mark;
        this.model = model;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return Objects.equals(getId(), car.getId()) && Objects.equals(getEngineType(), car.getEngineType()) && Objects.equals(getMark(), car.getMark()) && Objects.equals(getModel(), car.getModel()) && Objects.equals(getPrice(), car.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEngineType(), getMark(), getModel(), getPrice());
    }
}
