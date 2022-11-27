package Interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import utils.ApiClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class IOperationsApiImpl<T> implements IOperations {

    String addPath, updatePath, deletePath;
    Class<T> tClass;

    public IOperationsApiImpl(String addPath, String updatePath, String deletePath, Class<T> tClass) {
        this.addPath = addPath;
        this.updatePath = updatePath;
        this.deletePath = deletePath;
        this.tClass = tClass;
    }

    public T add(Object entity) {
        HttpEntity httpEntity = null;
        try {
            httpEntity = new ApiClient()
                    .setHttpMethod(ApiClient.HTTP_METHOD.POST)
                    .setPathSegments(addPath)
                    .setJson(new ObjectMapper().writeValueAsString((T) entity))
                    .setResponseCode(201)
                    .sendRequestAndGetResponse().getEntity();
            return (T) new ObjectMapper().readValue(httpEntity.getContent(), tClass);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public T update(Object entity) {
        return (T) entity;
    }

    @Override
    public void delete(long id) {

    }
}
