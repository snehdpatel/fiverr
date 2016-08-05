package com.example.sneh.myapplication;

/**
 * Created by starsilver on 17/11/15.
 */
public class task_class {
    private int user_id,task_id,sql_task_id;
    private String cicle_name,building_name,task_name;
    private String date,location,time_start;
    private int done;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getBuilding_name() {
        return building_name;
    }

    public void setBuilding_name(String building_name) {
        this.building_name = building_name;
    }

    public String getCicle_name() {
        return cicle_name;
    }

    public void setCicle_name(String cicle_name) {
        this.cicle_name = cicle_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSql_task_id() {
        return sql_task_id;
    }

    public void setSql_task_id(int sql_task_id) {
        this.sql_task_id = sql_task_id;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }
}
