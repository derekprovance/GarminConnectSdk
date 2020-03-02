package com.derekprovance.garmin;

import com.derekprovance.garmin.DTO.DailyHeartRate;
import com.derekprovance.garmin.DTO.DailyMovementData;
import com.derekprovance.garmin.DTO.DailyUserSummary;
import com.derekprovance.garmin.DTO.dailySleepData.DailySleepDTO;
import com.derekprovance.garmin.DTO.dailySleepData.DailySleepData;
import com.derekprovance.garmin.DTO.dailySleepData.SleepLevelDTO;
import com.derekprovance.garmin.DTO.dailySleepData.SleepMovementDTO;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeEach;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class GarminApiServiceTest {
    private Dotenv dotenv = Dotenv.load();
    private GarminApiService garminApiService = new GarminApiService(dotenv.get("USER_ID"), dotenv.get("ACCESS_TOKEN"));
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    private LocalDate testDate;

    @BeforeEach
    void setUp() {
        if(dotenv.get("TEST_DATE") == null) {
            throw new IllegalArgumentException("Must set TEST_DATE in .env for testing");
        }

        testDate = LocalDate.parse(dotenv.get("TEST_DATE"));

    }

    @org.junit.jupiter.api.Test
    void getDailySleepData() throws Exception {
        DailySleepData dailySleepData = garminApiService.getDailySleepData(testDate);

        assertNotNull(dailySleepData);

        DailySleepDTO dailySleepDTO = dailySleepData.getDailySleepDTO();
        assertNotNull(dailySleepDTO);
        assertEquals(testDate.format(dateTimeFormatter), simpleDateFormatter.format(dailySleepData.getDailySleepDTO().getCalendarDate()));
        assertNotNull(dailySleepDTO.getSleepStartTimestampGMT());
        assertNotNull(dailySleepDTO.getSleepEndTimestampGMT());

        assertTrue(dailySleepData.getSleepMovement().size() > 0);

        SleepMovementDTO sleepMovementDTO = dailySleepData.getSleepMovement().get(0);
        assertNotNull(sleepMovementDTO.getStartGMT());
        assertNotNull(sleepMovementDTO.getEndGMT());

        assertTrue(dailySleepData.getSleepLevels().size() > 0);
        SleepLevelDTO sleepLevelDTO = dailySleepData.getSleepLevels().get(0);
        assertNotNull(sleepLevelDTO.getActivityLevel());
        assertNotNull(sleepLevelDTO.getEndGMT());
        assertNotNull(sleepLevelDTO.getStartGMT());
    }

    @org.junit.jupiter.api.Test
    void getDailyHrData() throws Exception {
        DailyHeartRate dailyHeartRate = garminApiService.getDailyHrData(testDate);

        assertNotNull(dailyHeartRate);
        assertEquals(testDate.format(dateTimeFormatter), simpleDateFormatter.format(dailyHeartRate.getCalendarDate()));
        assertNotNull(dailyHeartRate.getStartTimestampGMT());
        assertNotNull(dailyHeartRate.getEndTimestampGMT());
        assertNotNull(dailyHeartRate.getStartTimestampLocal());
        assertNotNull(dailyHeartRate.getEndTimestampLocal());
        assertNotNull(dailyHeartRate.getMaxHeartRate());
        assertNotNull(dailyHeartRate.getMinHeartRate());
        assertTrue(dailyHeartRate.getHeartRateValues().length > 0);
    }

    @org.junit.jupiter.api.Test
    void getDailyMovement() throws Exception {
        DailyMovementData dailyMovementData = garminApiService.getDailyMovement(testDate);

        assertNotNull(dailyMovementData);
        assertNotNull(dailyMovementData.getStartTimestampGMT());
        assertNotNull(dailyMovementData.getStartTimestampLocal());
        assertNotNull(dailyMovementData.getEndTimestampGMT());
        assertNotNull(dailyMovementData.getEndTimestampLocal());
        assertTrue(dailyMovementData.getMovementValues().length > 0);
    }

    @org.junit.jupiter.api.Test
    void getUserSummary() throws Exception {
        DailyUserSummary dailyUserSummary = garminApiService.getDailyUserSummary(testDate);

        assertNotNull(dailyUserSummary);
        assertNotNull(dailyUserSummary.getMinHeartRate());
        assertNotNull(dailyUserSummary.getMaxHeartRate());
        assertEquals(testDate.format(dateTimeFormatter), simpleDateFormatter.format(dailyUserSummary.getCalendarDate()));
    }
}