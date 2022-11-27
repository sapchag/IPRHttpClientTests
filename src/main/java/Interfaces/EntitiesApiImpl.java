package Interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import utils.ApiClient;

import java.io.IOException;
import java.util.List;

public class EntitiesApiImpl<T> implements IEntities {

    String itemPath, itemsPath;
    Class<T> tClass;

    public EntitiesApiImpl(String itemPath, String itemsPath, Class<T> tClass) {
        this.itemPath = itemPath;
        this.itemsPath = itemsPath;
        this.tClass = tClass;
    }

    public T get(long id) {
        HttpEntity httpEntity = new ApiClient()
                .setPathSegments(itemPath, String.valueOf(id))
                .setResponseCode(200)
                .sendRequestAndGetResponse().getEntity();

        try {
            return new ObjectMapper().readValue(httpEntity.getContent(), tClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<T> getAll() {
        HttpEntity httpEntity = new ApiClient()
                .setPathSegments(itemsPath)
                .setResponseCode(200)
                .sendRequestAndGetResponse()
                .getEntity();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(
                    httpEntity.getContent(), objectMapper.getTypeFactory()
                            .constructCollectionType(List.class, tClass));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
