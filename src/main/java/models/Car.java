package models;

import java.math.BigDecimal;
import java.util.Objects;

public class Car extends BaseModel {

    String engineType;
    String mark;
    String model;
    BigDecimal price;

    public Car() {
    }

    public Car(Long id, String engineType, String mark, String model, BigDecimal price) {
        this.id = id;
        this.engineType = engineType;
        this.mark = mark;
        this.model = model;
        this.price = price;
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

    @Override
    public Car clone() {
        return new Car(this.id, this.engineType, this.mark, this.model, this.price);
    }
}
