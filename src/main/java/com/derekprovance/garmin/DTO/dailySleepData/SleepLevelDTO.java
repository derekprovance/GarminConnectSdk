package com.derekprovance.garmin.DTO.dailySleepData;

import java.util.Date;

public class SleepLevelDTO {
    private Date startGMT;
    private Date endGMT;
    private Integer activityLevel;

    public Date getStartGMT() {
        return startGMT;
    }

    public void setStartGMT(Date startGMT) {
        this.startGMT = startGMT;
    }

    public Date getEndGMT() {
        return endGMT;
    }

    public void setEndGMT(Date endGMT) {
        this.endGMT = endGMT;
    }

    public Integer getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(Integer activityLevel) {
        this.activityLevel = activityLevel;
    }
}
