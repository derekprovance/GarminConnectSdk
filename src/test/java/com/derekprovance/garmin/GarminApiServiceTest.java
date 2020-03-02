package com.derekprovance.garmin;

import com.derekprovance.garmin.DTO.dailySleepData.DailySleepDTO;
import com.derekprovance.garmin.DTO.dailySleepData.DailySleepData;
import com.derekprovance.garmin.DTO.dailySleepData.SleepLevelDTO;
import com.derekprovance.garmin.DTO.dailySleepData.SleepMovementDTO;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.http.HttpException;
import org.junit.jupiter.api.BeforeEach;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class GarminApiServiceTest {
    private Dotenv dotenv = Dotenv.load();
    private GarminApiService garminApiService = new GarminApiService(dotenv.get("USER_ID"), dotenv.get("ACCESS_TOKEN"));
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
    private LocalDate localDate;

    @BeforeEach
    void setUp() {
        if(dotenv.get("TEST_DATE") == null) {
            throw new IllegalArgumentException("Must set TEST_DATE in .env for testing");
        }

        localDate = LocalDate.parse(dotenv.get("TEST_DATE"));

    }

    @org.junit.jupiter.api.Test
    void getDailySleepData() throws HttpException {
        DailySleepData dailySleepData = garminApiService.getDailySleepData(localDate);

        assertNotNull(dailySleepData);

        DailySleepDTO dailySleepDTO = dailySleepData.getDailySleepDTO();
        assertNotNull(dailySleepDTO);
        assertEquals(dotenv.get("TEST_DATE"), dailySleepData.getDailySleepDTO().getCalendarDate());
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
    void getDailyHrData() {

    }

    @org.junit.jupiter.api.Test
    void getDailyMovement() {

    }

    @org.junit.jupiter.api.Test
    void getUserSummary() {

    }
}