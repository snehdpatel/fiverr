package com.example.sneh.myapplication;

/**
 * Created by starsilver on 12/11/15.
 */
public class returned_expense_class  {
    private int sql_returned_expense_id,sql_cicle_id,sql_building_id;
    private int user_id;
    private int returned_expense_id,cicle_id,building_id;
    private String type,date;
    private int quantity,price;
    private int flag;

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

    public int getSql_returned_expense_id() {
        return sql_returned_expense_id;
    }

    public void setSql_returned_expense_id(int sql_returned_expense_id) {
        this.sql_returned_expense_id = sql_returned_expense_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getReturned_expense_id() {
        return returned_expense_id;
    }

    public void setReturned_expense_id(int returned_expense_id) {
        this.returned_expense_id = returned_expense_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

