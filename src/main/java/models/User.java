package models;

import Utils.ApiClient;
import Utils.DbClient;
import Utils.EndPoints;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import handlers.UserHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class User {

	private Integer id;
	private String firstName;
	private String secondName;
	private Integer age;
	private Boolean sex;
	private BigDecimal money;

	public User() {
	}

	public static User getUserFromDb(Integer id) throws SQLException, ClassNotFoundException {
		return new DbClient().getList("SELECT * FROM person where id=" + id.toString(), new UserHandler()).get(0);
	}

	public static List<User> getUsersFromDb() throws SQLException, ClassNotFoundException {
		return new DbClient().getList("SELECT * FROM person", new UserHandler());
	}

	public static User getUserFromApi(long id) throws IOException {
		HttpEntity httpEntity = new ApiClient()
				.setPathSegments(EndPoints.user, String.valueOf(id))
				.sendRequestAndGetResponse().getEntity();

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(httpEntity.getContent(), User.class);
	}

	public static List<User> getUsersFromApi() throws IOException {
		HttpEntity httpEntity = new ApiClient()
				.setPathSegments("users")
				.sendRequestAndGetResponse().getEntity();

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(
				httpEntity.getContent(), new TypeReference<>() {
				});
	}

	public void print() {
		System.out.print("Id:" + getId());
		System.out.print(" FirstName:" + getFirstName());
		System.out.print(" SecondName:" + getSecondName());
		System.out.print(" Age:" + getAge());
		System.out.print(" Sex:" + getSex());
		System.out.println(" Money:" + getMoney());
	}

	public User(Integer id, String firstName, String secondName, Integer age, Boolean sex, BigDecimal money) {
		this.id = id;
		this.firstName = firstName;
		this.secondName = secondName;
		this.age = age;
		this.sex = sex;
		this.money = money;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	public void setSex(String sex) {
		this.sex = StringUtils.isEmpty(sex) ? null : sex.equals("MALE") ? true : false;
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
}
