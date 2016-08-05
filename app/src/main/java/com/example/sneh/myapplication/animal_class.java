package com.example.sneh.myapplication;

/**
 * Created by starsilver on 5/11/15.
 */
public class animal_class {
    private int animal_id,cicle_id,building_id;
    private int sql_animal_id,sql_cicle_id,sql_building_id;
    private int user_id;
    private int flag;
    private String type,other;
    private int quantity,price;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getSql_animal_id() {
        return sql_animal_id;
    }

    public void setSql_animal_id(int sql_animal_id) {
        this.sql_animal_id = sql_animal_id;
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

    public int getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(int animal_id) {
        this.animal_id = animal_id;
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

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
