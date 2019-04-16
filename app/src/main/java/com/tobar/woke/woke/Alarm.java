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
    private boolean delete;
    //Fields For MQTT connection
    private int id;
    boolean sense_flag;
    boolean act_flag;


    /**
     * Constructs an {@code Alarm} object.
     * @param alarmTime
     * @param alarmState
     * @param numberSnoozes
     * @param snoozeInterval
     */
    public Alarm(String alarmTime, boolean alarmState, int numberSnoozes, int snoozeInterval) {

        this.alarmTime = alarmTime;
        this.alarmState = alarmState;
        this.numberSnoozes = numberSnoozes;
        this.snoozeInterval = snoozeInterval;
        this.delete = false;

        this.id = 0;
        this.sense_flag = false;
        this.act_flag = false;



    }


   // when sense flag is true activate the mat (turn on)
    // event is undone
    //When mat is  ste[[ed on, mat is unactivated, unclose, mat and event is done.


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

    public boolean isDelete() {
        return delete;
    }

    public void setAlarmState(boolean alarmState) {
        this.alarmState = alarmState;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}
