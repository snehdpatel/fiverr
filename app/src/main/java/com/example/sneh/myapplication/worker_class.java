package com.example.sneh.myapplication;

/**
 * Created by starsilver on 5/11/15.
 */
public class worker_class {
    private int sql_worker_id,sql_cicle_id,sql_building_id;
    private int user_id;
    private int worker_id,cicle_id,building_id;
    private String name,address,tel,date_start;
    private int price_per_day;
    private int flag;
    private String last_update;
    private int salary;

    private int Setactive;
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getSql_worker_id() {
        return sql_worker_id;
    }

    public void setSql_worker_id(int sql_worker_id) {
        this.sql_worker_id = sql_worker_id;
    }

    public int getSql_cicle_id() {
        return sql_cicle_id;
    }

    public void setSql_cicle_id(int sql_cicle_id) {
        this.sql_cicle_id = sql_cicle_id;
    }

    public int getSql_building_id() {
        return sql_building_id;
    }

    public void setSql_building_id(int sql_building_id) {
        this.sql_building_id = sql_building_id;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }


    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }


    public int getSetactive() {
        return Setactive;
    }

    public void setSetactive(int setactive) {
        Setactive = setactive;
    }

    public int getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(int worker_id) {
        this.worker_id = worker_id;
    }

    public int getCicle_id() {
        return cicle_id;
    }

    public void setCicle_id(int cicle_id) {
        this.cicle_id = cicle_id;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public int getPrice_per_day() {
        return price_per_day;
    }

    public void setPrice_per_day(int price_per_day) {
        this.price_per_day = price_per_day;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(int building_id) {
        this.building_id = building_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
