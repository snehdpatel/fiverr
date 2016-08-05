package com.example.sneh.myapplication;

/**
 * Created by Himanshu on 11/10/2015.
 */
public class notes_file {

    private int sql_notes_id,sql_cicle_id,sql_building_id;
    private int user_id,cicle_id,building_id;
    private int id;
    String notes;
    private int flag;


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


    public int getSql_notes_id() {
        return sql_notes_id;
    }

    public void setSql_notes_id(int sql_notes_id) {
        this.sql_notes_id = sql_notes_id;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    notes_file(int id,String notes)
    {
        this.id=id;
        this.notes=notes;
    }
    notes_file(String notes)
    {
        this.notes=notes;
    }
    notes_file() {}
}