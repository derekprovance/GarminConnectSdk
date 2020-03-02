package com.derekprovance.garmin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

enum RestServiceEndpoints {
    DAILY_SLEEP_DATA("/wellness-service/wellness/dailySleepData/%s?date=%s&nonSleepBufferMinutes=60"),
    DAILY_HR_DATA("/wellness-service/wellness/dailyHeartRate/%s?date=%s"),
    DAILY_MOVEMENT_DATA("/wellness-service/wellness/dailyMovement/%s?calendarDate=%s"),
    DAILY_USER_SUMMARY("/usersummary-service/usersummary/daily/%s?calendarDate=%s");

    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private String path;

    RestServiceEndpoints(String path) {
        this.path = path;
    }

    public String getName() {
        return this.name();
    }

    public String getPath() {
        return path;
    }

    public String getUri(String userId, LocalDate date) {
        String BASE_URL = "https://connect.garmin.com/modern/proxy";
        return BASE_URL + formatEndpoint(userId, date);
    }

    private String formatEndpoint(String userId, LocalDate date) {
        return String.format(path, userId, format.format(date));
    }
}
