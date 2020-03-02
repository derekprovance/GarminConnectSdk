package com.derekprovance.garmin.DTO.dailySleepData;

import java.util.List;

public class DailySleepData {
    private DailySleepDTO dailySleepDTO;
    private List<SleepMovementDTO> sleepMovement;
    private List<SleepLevelDTO> sleepLevels;

    public DailySleepDTO getDailySleepDTO() {
        return dailySleepDTO;
    }

    public void setDailySleepDTO(DailySleepDTO dailySleepDTO) {
        this.dailySleepDTO = dailySleepDTO;
    }

    public List<SleepMovementDTO> getSleepMovement() {
        return sleepMovement;
    }

    public void setSleepMovement(List<SleepMovementDTO> sleepMovement) {
        this.sleepMovement = sleepMovement;
    }

    public List<SleepLevelDTO> getSleepLevels() {
        return sleepLevels;
    }

    public void setSleepLevels(List<SleepLevelDTO> sleepLevels) {
        this.sleepLevels = sleepLevels;
    }
}
