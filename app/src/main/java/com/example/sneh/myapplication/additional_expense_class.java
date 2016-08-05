package com.example.sneh.myapplication;

/**
 * Created by starsilver on 11/11/15.
 */
public class additional_expense_class {

    private int sql_cicle_id,sql_building_id,sql_additional_expense_id;
    private int user_id;
    private int flag;
    private int cicle_id,building_id,additional_expense_id;
    private  String type,designation,date;
    private int quantity,price_per_one;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getSql_additional_expense_id() {
        return sql_additional_expense_id;
    }

    public void setSql_additional_expense_id(int sql_additional_expense_id) {
        this.sql_additional_expense_id = sql_additional_expense_id;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAdditional_expense_id() {
        return additional_expense_id;
    }

    public void setAdditional_expense_id(int additional_expense_id) {
        this.additional_expense_id = additional_expense_id;
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getPrice_per_one() {
        return price_per_one;
    }

    public void setPrice_per_one(int price_per_one) {
        this.price_per_one = price_per_one;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
