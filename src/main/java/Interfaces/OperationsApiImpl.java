package Interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.BaseModel;
import org.apache.http.HttpEntity;
import utils.ApiClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class OperationsApiImpl<T extends BaseModel> implements IOperations<T> {

    String addPath, updatePath, deletePath;
    Class<T> tClass;

    public OperationsApiImpl(String addPath, String updatePath, String deletePath, Class<T> tClass) {
        this.addPath = addPath;
        this.updatePath = updatePath;
        this.deletePath = deletePath;
        this.tClass = tClass;
    }

    public <T extends BaseModel> T add(T entity) {
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

    public <T extends BaseModel> T update(T entity) {
        Object clone = entity.clone();
        ((T) clone).setId(null);

        HttpEntity httpEntity = null;
        try {
            httpEntity = new ApiClient()
                    .setHttpMethod(ApiClient.HTTP_METHOD.PUT)
                    .setPathSegments(updatePath, String.valueOf(entity.getId()))
                    .setJson(new ObjectMapper().writeValueAsString((T) clone))
                    .setResponseCode(202)
                    .sendRequestAndGetResponse()
                    .getEntity();

            return (T) new ObjectMapper().readValue(httpEntity.getContent(), tClass);

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(long id) {
        new ApiClient()
                .setHttpMethod(ApiClient.HTTP_METHOD.PUT)
                .setPathSegments(deletePath, String.valueOf(id))
                .setResponseCode(204)
                .sendRequestAndGetResponse()
                .getEntity();
    }
}
