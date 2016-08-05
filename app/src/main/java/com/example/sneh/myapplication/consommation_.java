package com.example.sneh.myapplication;

/**
 * Created by sneh on 16/11/15.
 */
public class consommation_ {

    int sql_cicle_id, sql_building_id, sql_consommation_id;
    int user_id;
    int flag;
    int cicle_id, building_id, consommation_id;
    int quantity, number;
    String designation, food_list, per, date_start, date_end;

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

    public int getSql_consommation_id() {
        return sql_consommation_id;
    }

    public void setSql_consommation_id(int sql_consommation_id) {
        this.sql_consommation_id = sql_consommation_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCicle_id() {
        return cicle_id;
    }

    public void setCicle_id(int cicle_id) {
        this.cicle_id = cicle_id;
    }

    public int getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(int building_id) {
        this.building_id = building_id;
    }

    public int getConsommation_id() {
        return consommation_id;
    }

    public void setConsommation_id(int consommation_id) {
        this.consommation_id = consommation_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getFood_list() {
        return food_list;
    }

    public void setFood_list(String food_list) {
        this.food_list = food_list;
    }

    public String getPer() {
        return per;
    }

    public void setPer(String per) {
        this.per = per;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }
}
