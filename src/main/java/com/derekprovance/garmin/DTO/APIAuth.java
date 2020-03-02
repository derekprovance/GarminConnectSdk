package com.derekprovance.garmin.DTO;

public class APIAuth {
    private String userId;
    private String sessionId;

    public APIAuth(String userId, String sessionId) {
        this.userId = userId;
        this.sessionId = sessionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
