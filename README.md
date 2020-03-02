# Unofficial Garmin Connect SDK

This is an unofficial ConnectSDK. For educational use only. 

I am not affiliated with Garmin, nor do I represent any of Garmin's interests.

### Installing

Download and include a compiled version of the jar into your local project.

End with an example of getting some data out of the system or using it for a little demo

### Usage

Create a new instance of the GarminApiService

```
GarminApiService garminApiService = new GarminApiService("User Id", "Access Token");
```

#### Downloading Daily Sleep Data

```
LocalDate date = LocalDate.parse("2019-10-17");
DailySleepData dailySleepData = getDailySleepData.getDailySleepData(date);
```

#### Downloading Daily HR Data

```
LocalDate date = LocalDate.parse("2019-10-17");
DailyHeartRate dailyHeartRate = getDailySleepData.getDailyHrData(date);
```

#### Downloading Daily Movement

```
LocalDate date = LocalDate.parse("2019-10-17");
DailyMovementData dailyMovementData = getDailySleepData.getDailyMovement(date);
```

#### Downloading Daily User Summary

```
LocalDate date = LocalDate.parse("2019-10-17");
DailyUserSummary dailyUserSummary = getDailySleepData.getDailyUserSummary(date);
```

## Authors

* **Derek Provance** - *Initial work*