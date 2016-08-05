package com.example.sneh.myapplication;

/**
 * Created by starsilver on 12/11/15.
 */
public class down_payment_class {

    private int down_payment_id,cicle_id,building_id;
    private int sql_down_payment_id,sql_cicle_id,sql_building_id;
    private int user_id;
    private int flag;
    private String worker_name,date;
    private int price;
    private int worker_id;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getSql_building_id() {
        return sql_building_id;
    }

    public void setSql_building_id(int sql_building_id) {
        this.sql_building_id = sql_building_id;
    }

    public int getSql_cicle_id() {
        return sql_cicle_id;
    }

    public void setSql_cicle_id(int sql_cicle_id) {
        this.sql_cicle_id = sql_cicle_id;
    }

    public int getSql_down_payment_id() {
        return sql_down_payment_id;
    }

    public void setSql_down_payment_id(int sql_down_payment_id) {
        this.sql_down_payment_id = sql_down_payment_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(int worker_id) {
        this.worker_id = worker_id;
    }



    public int getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(int building_id) {
        this.building_id = building_id;
    }

    public int getCicle_id() {
        return cicle_id;
    }

    public void setCicle_id(int cicle_id) {
        this.cicle_id = cicle_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDown_payment_id() {
        return down_payment_id;
    }

    public void setDown_payment_id(int down_payment_id) {
        this.down_payment_id = down_payment_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getWorker_name() {
        return worker_name;
    }

    public void setWorker_name(String worker_name) {
        this.worker_name = worker_name;
    }
}