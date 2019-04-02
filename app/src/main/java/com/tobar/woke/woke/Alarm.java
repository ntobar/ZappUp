package com.tobar.woke.woke;


import java.util.ArrayList;

/**
 * This class represents a Single Alarm
 */
public class Alarm {
    private String alarmTime;
    private boolean alarmState;
    private int numberSnoozes;
    private int snoozeInterval;

    public Alarm(String alarmTime, boolean alarmState, int numberSnoozes, int snoozeInterval) {

        this.alarmTime = alarmTime;
        this.alarmState = alarmState;
        this.numberSnoozes = numberSnoozes;
        this.snoozeInterval = snoozeInterval;



    }



    public String getAlarmTime() {
        return alarmTime;
    }

    public boolean getAlarmState() {
        return alarmState;
    }

    public int getNumberSnoozes() {
        return numberSnoozes;
    }

    public int getSnoozeInterval() {
        return snoozeInterval;
    }
}
