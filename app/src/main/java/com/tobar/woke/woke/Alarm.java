package com.tobar.woke.woke;


/**
 * This class represents a Single Alarm
 */
public class Alarm {
    String alarmTime;
    String alarmState;
    String numberSnoozes;
    String snoozeInterval;

    public Alarm(String alarmTime, String alarmState, String numberSnoozes, String snoozeInterval) {

        this.alarmTime = alarmTime;
        this.alarmState = alarmState;
        this.numberSnoozes = numberSnoozes;
        this.snoozeInterval = snoozeInterval;



    }

    public String getAlarmTime() {
        return alarmTime;
    }

    public String getAlarmState() {
        return alarmState;
    }

    public String getNumberSnoozes() {
        return numberSnoozes;
    }

    public String getSnoozeInterval() {
        return snoozeInterval;
    }
}
