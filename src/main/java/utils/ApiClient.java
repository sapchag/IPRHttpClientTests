package utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiClient {

    URIBuilder uriBuilder = new URIBuilder();
    String json;
    HTTP_METHOD httpMethod = HTTP_METHOD.GET;

    Integer responseCode;

    public enum HTTP_METHOD {
        GET,
        PUT,
        POST,
        DELETE
    }

    public ApiClient setPathSegments(String... pathSegment) {
        uriBuilder.setPathSegments(pathSegment);
        return this;
    }

    public ApiClient setHttpMethod(HTTP_METHOD httpMethod) {
        this.httpMethod = httpMethod;
        return this;
    }

    public ApiClient setJson(String json) throws UnsupportedEncodingException {
        this.json = json;
        return this;
    }

    public ApiClient setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
        return this;
    }

    String getUrl() {
        return uriBuilder.setScheme("http")
                .setHost(EndPoints.ip)
                .setPort(EndPoints.port)
                .toString();
    }

    StringEntity getStringEntity(String json) {
        StringEntity stringEntity;
        try {
            stringEntity = new StringEntity(json);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return stringEntity;
    }

    HttpRequestBase getHttpRequest() {
        HttpRequestBase httpRequest;

        if (httpMethod == HTTP_METHOD.POST) {
            HttpPost httpPost = new HttpPost(getUrl());
            if (json != null) {
                httpPost.setEntity(getStringEntity(json));
            }
            httpRequest = httpPost;

        } else if (httpMethod == HTTP_METHOD.PUT) {
            HttpPut httpPut = new HttpPut(getUrl());
            if (json != null) {
                httpPut.setEntity(getStringEntity(json));
            }
            httpRequest = httpPut;

        } else if (httpMethod == HTTP_METHOD.DELETE) {
            httpRequest = new HttpDelete(getUrl());
        } else {
            httpRequest = new HttpGet(getUrl());
        }

        httpRequest.setHeader("Content-type", "application/json");
        return httpRequest;
    }

    public HttpResponse sendRequestAndGetResponse() {
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse httpResponse = null;
        try {

            httpResponse = httpClient.execute(getHttpRequest());
            if (responseCode != null) {
                assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(responseCode);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return httpResponse;
    }
}
