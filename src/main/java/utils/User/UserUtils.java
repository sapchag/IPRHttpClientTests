package utils.User;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import handlers.UserHandler;
import models.Car;
import models.User;
import org.apache.http.HttpEntity;
import utils.ApiClient;
import utils.DbClient;
import utils.RestPaths;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserUtils {

    public static void print(User user) {
        System.out.print("Id:" + user.getId());
        System.out.print(" FirstName:" + user.getFirstName());
        System.out.print(" SecondName:" + user.getSecondName());
        System.out.print(" Age:" + user.getAge());
        System.out.print(" Sex:" + user.getSex());
        System.out.println(" Money:" + user.getMoney());
    }

    public static String getJson(User user) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(user);
    }

    public static User getUserFromDb(Integer id) throws SQLException, ClassNotFoundException {
        return new DbClient().getList("SELECT * FROM person where id=" + id.toString(), new UserHandler()).get(0);
    }

    public static List<User> getUsersFromDb() throws SQLException, ClassNotFoundException {
        return new DbClient().getList("SELECT * FROM person", new UserHandler());
    }

    public static User getUserFromApi(long id) throws IOException {
        HttpEntity httpEntity = new ApiClient()
                .setPathSegments(RestPaths.user, String.valueOf(id))
                .setResponseCode(200)
                .sendRequestAndGetResponse().getEntity();

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(httpEntity.getContent(), User.class);
    }

    public static List<User> getUsersFromApi() throws IOException {
        HttpEntity httpEntity = new ApiClient()
                .setPathSegments(RestPaths.users)
                .setResponseCode(200)
                .sendRequestAndGetResponse()
                .getEntity();

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(
                httpEntity.getContent(), new TypeReference<>() {
                });
    }

    public static User add(User user) throws IOException {
        HttpEntity httpEntity = new ApiClient()
                .setHttpMethod(ApiClient.HTTP_METHOD.POST)
                .setPathSegments(RestPaths.addUser)
                .setJson(UserUtils.getJson(user))
                .setResponseCode(201)
                .sendRequestAndGetResponse()
                .getEntity();

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(httpEntity.getContent(), User.class);
    }

    public static User buyCar(User user, Car car) throws IOException {
        HttpEntity httpEntity = new ApiClient()
                .setHttpMethod(ApiClient.HTTP_METHOD.POST)
                .setPathSegments(RestPaths.user, user.getId().toString(), RestPaths.buyCar, car.getId().toString())
                .setResponseCode(200)
                .sendRequestAndGetResponse().getEntity();

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(httpEntity.getContent(), User.class);
    }

}
