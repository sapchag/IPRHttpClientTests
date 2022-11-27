package models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.Objects;

public class User extends BaseModel {

    private String firstName;
    private String secondName;
    private Integer age;
    private String sex;
    private BigDecimal money;

    public User() {
    }

    public User(Long id, String firstName, String secondName, Integer age, String sex, BigDecimal money) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.sex = sex;
        this.money = money;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex == null ? null : sex ? "MALE" : "FEMALE";
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getFirstName(), user.getFirstName()) && Objects.equals(getSecondName(), user.getSecondName()) && Objects.equals(getAge(), user.getAge()) && getSex() == user.getSex() && Objects.equals(getMoney(), user.getMoney());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getSecondName(), getAge(), getSex(), getMoney());
    }

    @Override
    public User clone() {
        return new User(this.id, this.firstName, this.secondName, this.age, this.sex, this.money);
    }
}
