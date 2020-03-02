package com.derekprovance.garmin;

import com.derekprovance.garmin.DTO.APIAuth;
import com.derekprovance.garmin.DTO.DailyHeartRate;
import com.derekprovance.garmin.DTO.DailyMovementData;
import com.derekprovance.garmin.DTO.DailyUserSummary;
import com.derekprovance.garmin.DTO.dailySleepData.DailySleepData;
import com.derekprovance.garmin.HttpRequests.HttpRequestClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class GarminApiService {
    private final Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, DateTypeAdapter.getDateTypeAdapter()).create();


    private final APIAuth apiAuth;

    public GarminApiService(String userId, String accessToken) {
        this.apiAuth = new APIAuth(userId, accessToken);
    }

    public DailySleepData getDailySleepData(LocalDate date) throws HttpException {
        String endpointUri = RestServiceEndpoints.DAILY_SLEEP_DATA.getUri(apiAuth.getUserId(), date);
        String json = performRequest(endpointUri);

        return gson.fromJson(json, DailySleepData.class);
    }

    public DailyHeartRate getDailyHrData(LocalDate date) throws HttpException {
        String endpointUri = RestServiceEndpoints.DAILY_HR_DATA.getUri(apiAuth.getUserId(), date);
        String json = performRequest(endpointUri);

        return gson.fromJson(json, DailyHeartRate.class);
    }

    public DailyMovementData getDailyMovement(LocalDate date) throws HttpException {
        String endpointUri = RestServiceEndpoints.DAILY_MOVEMENT_DATA.getUri(apiAuth.getUserId(), date);
        String json = performRequest(endpointUri);

        return gson.fromJson(json, DailyMovementData.class);
    }

    public DailyUserSummary getDailyUserSummary(LocalDate date) throws HttpException {
        String endpointUri = RestServiceEndpoints.DAILY_USER_SUMMARY.getUri(apiAuth.getUserId(), date);
        String json = performRequest(endpointUri);

        return gson.fromJson(json, DailyUserSummary.class);
    }

    private String performRequest(String uri) throws HttpException {
        try {
            return HttpRequestClient.getInstance().makeGETRequest(apiAuth, uri);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
