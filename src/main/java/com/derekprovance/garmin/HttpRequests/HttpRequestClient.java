package com.derekprovance.garmin.HttpRequests;

import com.derekprovance.garmin.DTO.APIAuth;
import org.apache.http.HttpException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpRequestClient {
    private static HttpRequestClient httpRequestClient;
    private CloseableHttpClient httpClient;

    public static HttpRequestClient getInstance() {
        if(httpRequestClient == null) {
            httpRequestClient = new HttpRequestClient();
        }

        return httpRequestClient;
    }

    public HttpRequestClient() {
        httpClient = HttpClients.createDefault();
    }

    public String makeGETRequest(APIAuth apiAuth, String uri) throws IOException, HttpException {
        HttpGet request = new HttpGet(uri);
        addAuthenticationToken(apiAuth, request);

        return processRequest(request);
    }

    public String makePOSTRequest(APIAuth apiAuth, String uri, String jsonBody) throws IOException, HttpException {
        HttpPost post = new HttpPost(uri);
        post.addHeader("content-type", "application/json");
        addAuthenticationToken(apiAuth, post);

        post.setEntity(new StringEntity(jsonBody));

        return processRequest(post);
    }

    private String processRequest(HttpUriRequest request) throws IOException, HttpException {
        String result = "";

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
            if(response.getStatusLine().getStatusCode() != 200) {
                throw new HttpException("Request was unsuccessful " + response.getStatusLine().getStatusCode());
            }

            result = EntityUtils.toString(response.getEntity());
        }

        return result;
    }

    private void addAuthenticationToken(APIAuth apiAuth, HttpUriRequest request) {
        request.setHeader("Cookie", "SESSIONID=" + apiAuth.getSessionId());
    }
}
