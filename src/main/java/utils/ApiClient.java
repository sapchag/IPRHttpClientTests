package utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class ApiClient {

	URIBuilder uriBuilder = new URIBuilder();
	String json;
	HTTP_METHOD httpMethod = HTTP_METHOD.GET;

	public enum HTTP_METHOD {
		GET,
		PUT,
		POST
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

	public ApiClient setRequestJson(String json) throws UnsupportedEncodingException {
		this.json = json;
		return this;
	}

	String getUrl() {
		return uriBuilder.setScheme("http")
				.setHost(EndPoints.ip)
				.setPort(EndPoints.port)
				.toString();
	}

	HttpRequestBase getHttpRequest() {
		HttpRequestBase httpEntity;
		if (httpMethod == HTTP_METHOD.POST) {
			httpEntity = new HttpPost(getUrl());
		} else if (httpMethod == HTTP_METHOD.PUT) {
			httpEntity = new HttpPut(getUrl());
		} else {
			httpEntity = new HttpGet(getUrl());
		}
		return httpEntity;
	}

	public HttpResponse sendRequestAndGetResponse() throws IOException {
		HttpClient httpClient = HttpClients.createDefault();
		HttpRequestBase httpRequest = getHttpRequest();;

		HttpResponse httpResponse = httpClient.execute(httpRequest);
		return httpResponse;
	}

}
