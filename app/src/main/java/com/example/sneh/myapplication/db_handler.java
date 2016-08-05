package com.example.sneh.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by starsilver on 5/11/15.
 */
public class db_handler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="cicle_db";
    private static final String KEY_USER_ID="user_id";
    private static final String KEY_FLAG="flag";
    //table_user_info
    private static final String TABLE_USER_INFO="login_info";
    private static final String KEY_ID="id";
    private static final String KEY_F_NAME="first_name";
    private static final String KEY_L_NAME="last_name";
    private static final String KEY_PASSWORD="user_password";
    private static final String KEY_EMAIL="user_email";

    //table_cicle_info
    private static final String TABLE_CICLE_INFO="cicle_info";
    private static final String KEY_SQL_CICLE_ID="sql_cicle_id";
    private static final String KEY_CICLE_ID="cicle_id";
    private static final String KEY_CICLE_TITLE="cicle_title";
    private static final String KEY_CICLE_OWNER="cicle_owner";
    private static final String KEY_CICLE_LOCATION="cicle_location";
    private static final String KEY_CICLE_FINISH_DATE="cicle_finish_date";
    private static final String KEY_CICLE_START_DATE="cicle_start_date";
    private static final String KEY_CICLE_TYPE="cicle_type";
    private static final String KEY_CICLE_DONE="cicle_done";


    //table building info
    private static final String TABLE_BUILDING_INFO="building_info";
    private static final String KEY_SQL_BUILDING_ID="sql_building_id";
    private static final String KEY_SQL_CICLE_BUILDING_ID="sql_cicle_building_id";
    private static final String KEY_BUILDING_ID="building_id";
    private static final String KEY_BUILDING_CICLE_ID="building_cicle_id";
    private static final String KEY_BUILDING_TITLE="building_title";
    private static final String KEY_BUILDING_CAPACITY="building_capacity";
    private static final String KEY_BUILDING_TYPE="building_type";
    private static final String KEY_BUILDING_OTHER="building_other";


    //table_task_info
    private static final String TABLE_TITLE_INFO="title_info";
    private static final String KEY_TITLE_ID="title_id";
    private static final String KEY_TITLE_TITLE="title_title";
    private static final String KEY_TITLE_DONE="title_done";

    //table equipment info
    private static final String TABLE_EQUIP_INFO="equipment_info";
    private static final String KEY_SQL_EQUIP_ID="sql_equip_id";
    private static final String KEY_SQL_EQUIP_CICLE_ID="sql_equip_cicle_id";
    private static final String KEY_SQL_EQUIP_BUILDING_ID="sql_equip_building_id";
    private static final String KEY_EQUIP_ID="equipment_id";
    private static final String KEY_EQUIP_CICLE_ID="equipment_cicle_id";
    private static final String KEY_EQUIP_BUILDING_ID="equipment_building_id";
    private static final String KEY_EQUIP_TYPE="equipment_type";
    private static final String KEY_EQUIP_DESIGNATION="equipment_designation";
    private static final String KEY_EQUIP_QUANTITY="equipment_quantity";
    private static final String KEY_EQUIP_PRICE="equipment_price";

    //table for image info
    private static final String TABLE_IMAGE_INFO="image_info";
    private static final String KEY_SQL_IMAGE_ID="sql_image_id";
    private static final String KEY_SQL_IMAGE_CICLE_ID="sql_image_cicle_id";
    private static final String KEY_SQL_IMAGE_BUILDING_ID="sql_image_building_id";
    private static final String KEY_IMAGE_ID="image_id";
    private static final String KEY_IMAGE_CICLE_ID="image_cicle_id";
    private static final String KEY_IMAGE_BUILDING_ID="image_building_id";
    private static final String KEY_IMAGE_PATH="image_path";
    private static final String KEY_IMAGE_NAME="image_name";
    private static final String KEY_IMAGE_TYPE = "image_video";

    //table worker info change
    private static final String TABLE_WORKER_INFO="worker_info";
    private static final String KEY_SQL_WORKER_ID="sql_worker_id";
    private static final String KEY_SQL_CICLE_WORKER_ID="sql_cicle_worker_id";
    private static final String KEY_SQL_BUILDING_WORKER_ID="sql_building_worker_id";
    private static final String KEY_WORKER_ID="worker_id";
    private static final String KEY_WORKER_CICLE_ID="worker_cicle_id";
    private static final String KEY_WORKER_BUILDING_ID="worker_building_id";
    private static final String KEY_WORKER_NAME="worker_name";
    private static final String KEY_WORKER_ADDRESS="worker_address";
    private static final String KEY_WORKER_PHONE="worker_phone";
    private static final String KEY_WORKER_SETACTIVE="worker_setactive";
    private static final String KEY_WORKER_START_DATE="worker_date";
    private static final String KEY_WORKER_PRICE="worker_price";
    private static final String KEY_WORKER_TOTAL_SALARY="worker_salary";
    private static final String KEY_WORKER_LAST_DATE="worker_last_date";


    //table animal_info
    private static final String TABLE_ANIMAL_INFO="animal_info";
    private static final String KEY_SQL_ANIMAL_ID="sql_animal_id";
    private static final String KEY_SQL_CICLE_ANIMAL_ID="sql_cicle_id";
    private static final String KEY_SQL_BUILDING_ANIMAL_ID="sq_building_id";
    private static final String KEY_ANIMAL_ID="animal_id";
    private static final String KEY_ANIMAL_CICLE_ID="animal_cicle_id";
    private static final String KEY_ANIMAL_BUILDING_ID="animal_building_id";
    private static final String KEY_ANIMAL_TYPE="animal_type";
    private static final String KEY_ANIMAL_OTHER="animal_other";
    private static final String KEY_ANIMAL_QUANTITY="animal_quantity";
    private static final String KEY_ANIMAL_PRICE="animal_price";

    //table_food_info
    private static final String TABLE_FOOD_INFO="food_info";
    private static final String KEY_SQL_FOOD_ID="sql_food_id";
    private static final String KEY_SQL_CICLE_FOOD_ID="sql_cicle_id";
    private static final String KEY_SQL_BUILDING_FOOD_ID="sql_building_id";
    private static final String KEY_FOOD_ID="food_id";
    private static final String KEY_FOOD_CICLE_ID="food_cicle_id";
    private static final String KEY_FOOD_BUILDING_ID="food_building_id";
    private static final String KEY_FOOD_DATE="food_date";
    private static final String KEY_FOOD_DESIGNATION="food_designation";
    private static final String KEY_FOOD_QUANTITY="food_quantity";
    private static final String KEY_FOOD_PRICE="food_price";

    //table_temp_info
    private static final String TABLE_TEMP_INFO="temp_info";
    private static final String KEY_SQL_TEMP_ID="sql_temp_id";
    private static final String KEY_SQL_TEMP_CICLE_ID="sql_temp_cicle_id";
    private static final String KEY_SQL_TEMP_BUILDING_ID="sql_temp_building_id";
    private static final String KEY_TEMP_ID="temp_id";
    private static final String KEY_TEMP_CICLE_ID="temp_cicle_id";
    private static final String KEY_TEMP_BUILDING_ID="temp_building_id";
    private static final String KEY_TEMP_TEMP="temp_temp";
    private static final String KEY_TEMP_LUX="temp_lux";
    private static final String KEY_TEMP_HUMIDITY="temp_humidity";
    private static final String KEY_TEMP_DATE="temp_date";

    //table death info
    private static final String TABLE_DEATH_INFO="death_info";
    private static final String KEY_SQL_DEATH_ID="sql_death_id";
    private static final String KEY_SQL_CICLE_DEATH_ID="sql_cicle_death_id";
    private static final String KEY_SQL_BUILDING_DEATH_ID="sql_building_death_id";
    private static final String KEY_DEATH_ID="death_id";
    private static final String KEY_DEATH_CICLE_ID="death_cicle_id";
    private static final String KEY_DEATH_BUILDING_ID="death_building_id";
    private static final String KEY_DEATH_NO="death_type";
    private static final String KEY_DEATH_DATE="death_designation";

    //table normal egg info
    private static final String TABLE_NORMAL_EGG_INFO ="normal_egg_info";
    private static final String KEY_SQL_NORMAL_EGG_ID="sql_normal_egg_id";
    private static final String KEY_SQL_NORMAL_EGG_CICLE_ID="sql_normal_egg_cicle_id";
    private static final String KEY_SQL_NORMAL_EGG_BUILDING_ID="sql_normal_egg_building_id";
    private static final String KEY_NORMAL_EGG_ID ="normal_egg_id";
    private static final String KEY_NORMAL_EGG_CICLE_ID ="normal_egg_cicle_id";
    private static final String KEY_NORMAL_EGG_BUILDING_ID ="normal_egg_building_id";
    private static final String KEY_NORMAL_EGG_DATE ="normal_egg_date";
    private static final String KEY_NORAML_EGG_TYPE ="normal_egg_type";
    private static final String KEY_NORAML_EGG_NUMBER ="normal_egg_number";

    //table broken egg info
    private static final String TABLE_BROKEN_EGG_INFO ="broken_egg_info";
    private static final String KEY_SQL_BROKEN_EGG_ID="sql_broken_egg_id";
    private static final String KEY_SQL_BROKEN_EGG_CICLE_ID="sql_broken_egg_cicle_id";
    private static final String KEY_SQL_BROKEN_EGG_BUILDING_ID="sql_broken_egg_building_id";
    private static final String KEY_BROKEN_EGG_ID ="broken_egg_id";
    private static final String KEY_BROKEN_EGG_CICLE_ID ="broken_egg_cicle_id";
    private static final String KEY_BROKEN_EGG_BUILDING_ID ="broken_egg_building_id";
    private static final String KEY_BROKEN_EGG_DATE ="broken_egg_date";
    private static final String KEY_BROKEN_EGG_TYPE ="broken_egg_type";
    private static final String KEY_BROKEN_EGG_NUMBER ="broken_egg_number";


    //table Medical_info
    //table Medical_info
    private static final String TABLE_MEDICAL_INFO="medical_info";
    private static final String KEY_SQL_MEDICAL_ID="sql_medical_id";
    private static final String KEY_SQL_CICLE_MEDICAL_ID="sql_cicle_medical_id";
    private static final String KEY_SQL_BUILDING_MEDICAL_ID="sql_building_medical_id";
    private static final String KEY_MEDICAL_ID="medical_id";
    private static final String KEY_MEDICAL_CICLE_ID="medical_cicle_id";
    private static final String KEY_MEDICAL_BUILDING_ID="medical_building_id";
    private static final String KEY_MEDICAL_OPERATION_TYPE="medical_operation_type";
    private static final String KEY_MEDICAL_OPERATION_NAME="medical_operation_name";
    private static final String KEY_MEDICAL_PRODUCT="medical_product";
    private static final String KEY_MEDICAL_DATE="medical_date";
    private static final String KEY_MEDICAL_COMMENT="medical_comment";
    private static final String KEY_MEDICAL_PRICE="medical_price";


    //table Con_info
    private static final String TABLE_CON_INFO="con_info";
    private static final String KEY_SQL_CON_ID="sql_con_id";
    private static final String KEY_SQL_CICLE_CON_ID="sql_cicle_id";
    private static final String KEY_SQL_BUILDING_CON_ID="sql_building_id";
    private static final String KEY_CON_ID="con_id";
    private static final String KEY_CON_CICLE_ID="con_cicle_id";
    private static final String KEY_CON_BUILDING_ID="con_building_id";
    private static final String KEY_CON_DESIGNATION="con_designation";
    private static final String KEY_CON_FOOD_LIST="con_food_list";
    private static final String KEY_CON_QUANTITY="con_quantity";
    private static final String KEY_CON_NUMBER="con_number";
    private static final String KEY_CON_PER="con_per";
    private static final String KEY_CON_DATE_START="con_date_start";
    private static final String KEY_CON_DATE_END="con_date_end";

    //table_setting_info
    private static final String TABLE_SETTING_INFO="setting_info";
    private static final String KEY_SETTING_NOTIFICATION="setting_notification";
    private static final String KEY_SETTING_COUNTRY="setting_country";
    private static final String KEY_SETTING_MONEY="setting_money_format";

    //table_notes_info
    private static final String TABLE_NOTES_INFO="setting_info";
    private static final String KEY_NOTES_ID="setting_notification";
    private static final String KEY_NOTES_CICLE_ID="setting_country";
    private static final String KEY_NOTES_BUILDING_ID="setting_country";
    private static final String KEY_NOTES="setting_country";


    //table  down_payment_info
    private static final String TABLE_DOWN_PAYMENT_INFO="down_payment_info";
    private static final String KEY_SQL_DOWN_PAYMENT_ID="sql_down_payment_id";
    private static final String KEY_SQL_CICLE_DOWN_PAYMENT_ID="sql_cicle_id";
    private static final String KEY_SQL_BUILDING_DOWN_PAYMENT_ID="sql_building_id";
    private static final String KEY_DOWN_PAYMENT_ID="down_payment_id";
    private static final String KEY_DOWN_PAYMENT_CICLE_ID="down_payment_cicle_id";
    private static final String KEY_DOWN_PAYMENT_BUILDING_ID="down_payment_building_id";
    private static final String KEY_DOWN_PAYMENT_WORKER_NAME="down_payment_worker_name";
    private static final String KEY_DOWN_PAYMENT_DATE="done_payment_date";
    private static final String KEY_DOWN_PAYMENT_PRICE="done_payment_price";

    //table additional Expense Info
    private static final String TABLE_ADDITIONAL_EXPENSE_INFO="additional_expense_info";
    private static final String KEY_SQL_ADDITIONAL_EXPENSE_ID="sql_additional_expense_id";
    private static final String KEY_SQL_CICLE_ADDITIONAL_EXPENSE_ID="sql_cicle_id";
    private static final String KEY_SQL_BUILDING_ADDITIONAL_EXPENSE_ID="sql_building_id";
    private static final String KEY_ADDITIONAL_EXPENSE_ID="additional_expense_id";
    private static final String KEY_ADDITIONAL_EXPENSE_CICLE_ID="additional_expense_cicle_id";
    private static final String KEY_ADDITIONAL_EXPENSE_BUILDING_ID="additional_expense_building_id";
    private static final String KEY_ADDITIONAL_EXPENSE_TYPE="additional_expense_type";
    private static final String KEY_ADDITIONAL_EXPENSE_DESIGNATION="additional_expense_designation";
    private static final String KEY_ADDITIONAL_EXPENSE_DATE="additional_expense_date";
    private static final String KEY_ADDITIONAL_EXPENSE_QUANTITY="additional_expense_quantity";
    private static final String KEY_ADDITIONAL_EXPENSE_PRICE="additional_expense_price";

    //table returned Expense Info
    private static final String TABLE_RETURNED_EXPENSE_INFO="returned_expense_info";
    private static final String KEY_SQL_RETURNED_EXPENSE_ID="sql_returned_expense_id";
    private static final String KEY_SQL_CICLE_RETURNED_EXPENSE_ID="sql_cicle_id";
    private static final String KEY_SQL_BUILDING_RETURNED_EXPENSE_ID="sql_building_id";
    private static final String KEY_RETURNED_EXPENSE_ID="returned_expense_id";
    private static final String KEY_RETURNED_EXPENSE_CICLE_ID="returned_expense_cicle_id";
    private static final String KEY_RETURNED_EXPENSE_BUILDING_ID="returned_expense_building_id";
    private static final String KEY_RETURNED_EXPENSE_TYPE="returned_expense_type";
    private static final String KEY_RETURNED_EXPENSE_DATE="returned_expense_date";
    private static final String KEY_RETURNED_EXPENSE_QUANTITY="returned_expense_quantity";
    private static final String KEY_RETURNED_EXPENSE_PRICE="returned_expense_price";


    //table task_info
    private static final String TABLE_TASK_INFO="task_info";
    private static final String KEY_TASK_ID="task_id";
    private static final String KEY_SQL_TASK_ID="sql_task_id";
    private static final String KEY_TASK_CICLE_NAME="task_cicle_name";
    private static final String KEY_TASK_BUILDING_NAME="task_building_name";
    private static final String KEY_TASK_NAME="task_name";
    private static final String KEY_TASK_DATE="task_date";
    private static final String KEY_TASK_LOCATION="task_location";
    private static final String KEY_TASK_TIME_START="task_time_Start";
    private static final String KEY_TASK_DONE="task_done";






    String sql;



    public  db_handler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){}

    public void onCreateTable(SQLiteDatabase db){
        sql="CREATE TABLE IF NOT EXISTS " + TABLE_USER_INFO + "("
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_F_NAME+ " VARCHAR, "
                + KEY_L_NAME+ " VARCHAR, "
                + KEY_PASSWORD + " VARCHAR, "
                + KEY_EMAIL + " VARCHAR" +
                ")";
        Log.d("table_user_info_sql", sql);
        db.execSQL(sql);
        Log.d("user_info_status","created");

        //comand to create cicle
        sql="CREATE TABLE IF NOT EXISTS " + TABLE_CICLE_INFO + "("
                + KEY_SQL_CICLE_ID + " INTEGER, "
                + KEY_CICLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_USER_ID + " INTEGER, "
                + KEY_CICLE_TITLE+ " VARCHAR, "
                + KEY_CICLE_OWNER + " VARCHAR, "
                + KEY_CICLE_LOCATION + " VARCHAR," +
                KEY_CICLE_START_DATE + " VARCHAR, " +
                KEY_CICLE_FINISH_DATE + " VARCHAR, " +
                KEY_CICLE_TYPE+ " VARCHAR, " +
                KEY_CICLE_DONE+ " INTEGER, " +
                KEY_FLAG+ " INTEGER " +
                ")";
        Log.d("table_user_info_sql", sql);
        db.execSQL(sql);
        Log.d("user_info_status","created");


        sql="CREATE TABLE IF NOT EXISTS " + TABLE_TITLE_INFO + "("
                + KEY_TITLE_ID + " INTEGER PRIMARY KEY, "
                + KEY_TITLE_TITLE+ " VARCHAR, "+
                KEY_TITLE_DONE+ " INTEGER " +
                ")";
        Log.d("table_user_info_sql", sql);
        db.execSQL(sql);
        Log.d("user_info_status", "created");

        sql="CREATE TABLE IF NOT EXISTS " + TABLE_BUILDING_INFO + "("
                + KEY_USER_ID + " INTEGER, "
                + KEY_SQL_BUILDING_ID + " INTEGER, "
                + KEY_SQL_CICLE_BUILDING_ID + " INTEGER, "
                + KEY_BUILDING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_BUILDING_CICLE_ID+ " INTEGER, "
                + KEY_BUILDING_TITLE + " VARCHAR, "
                + KEY_BUILDING_CAPACITY + " INTEGER," +
                KEY_BUILDING_TYPE + " VARCHAR, " +
                KEY_BUILDING_OTHER+ " VARCHAR, " +
                KEY_FLAG+ " INTEGER " +
                ")";
        Log.d("table_building_info_sql", sql);
        db.execSQL(sql);
        Log.d("building_info_status", "created");

        sql="CREATE TABLE IF NOT EXISTS " + TABLE_EQUIP_INFO + "("
                + KEY_SQL_EQUIP_ID + " INTEGER, "
                + KEY_SQL_EQUIP_CICLE_ID + " INTEGER, "
                + KEY_SQL_EQUIP_BUILDING_ID + " INTEGER, "
                + KEY_USER_ID + " INTEGER, "
                + KEY_EQUIP_ID + " INTEGER PRIMARY KEY, "
                + KEY_EQUIP_CICLE_ID+ " INTEGER, "
                + KEY_EQUIP_BUILDING_ID + " INTEGER, "
                + KEY_EQUIP_TYPE + " VARCHAR," +
                KEY_EQUIP_DESIGNATION + " VARCHAR, " +
                KEY_EQUIP_QUANTITY+ " INTEGER, " +
                KEY_EQUIP_PRICE+ " INTEGER, " +
                KEY_FLAG+" INTEGER "+
                ")";
        Log.d("table_equip_info_sql", sql);
        db.execSQL(sql);
        Log.d("equip_info_status", "created");


        sql="CREATE TABLE IF NOT EXISTS " + TABLE_IMAGE_INFO + "("
                + KEY_SQL_IMAGE_ID + " INTEGER, "
                + KEY_SQL_IMAGE_CICLE_ID + " INTEGER, "
                + KEY_SQL_IMAGE_BUILDING_ID + " INTEGER, "
                + KEY_USER_ID + " INTEGER, "
                + KEY_IMAGE_ID + " INTEGER PRIMARY KEY, "
                + KEY_IMAGE_CICLE_ID+ " INTEGER, "
                + KEY_IMAGE_BUILDING_ID + " INTEGER, "
                + KEY_IMAGE_NAME + " VARCHAR," +
                KEY_IMAGE_PATH + " VARCHAR, " +
                KEY_IMAGE_TYPE+" INTEGER, "+
                KEY_FLAG+" INTEGER "+
                ")";
        Log.d("table_image_info_sql", sql);
        db.execSQL(sql);
        Log.d("image_info_status", "created");


        sql="CREATE TABLE IF NOT EXISTS " + TABLE_WORKER_INFO + "("
                + KEY_SQL_WORKER_ID + " INTEGER, "
                + KEY_SQL_CICLE_WORKER_ID + " INTEGER, "
                + KEY_SQL_BUILDING_WORKER_ID + " INTEGER, "
                + KEY_USER_ID + " INTEGER, "
                + KEY_WORKER_ID + " INTEGER PRIMARY KEY, "
                + KEY_WORKER_CICLE_ID+ " INTEGER, "
                + KEY_WORKER_BUILDING_ID + " INTEGER, "
                + KEY_WORKER_NAME + " VARCHAR, " +
                KEY_WORKER_ADDRESS + " VARCHAR, " +
                KEY_WORKER_PHONE+ " VARCHAR, " +
                KEY_WORKER_START_DATE+ " VARCHAR, " +
                KEY_WORKER_SETACTIVE+ " INTEGER, " +
                KEY_WORKER_PRICE+ " INTEGER, " +
                KEY_WORKER_TOTAL_SALARY+ " INTEGER, " +
                KEY_WORKER_LAST_DATE+ " VARCHAR, " +
                KEY_FLAG+ " VARCHAR " +
                ")";


        Log.d("table_worker_info_sql", sql);
        db.execSQL(sql);
        Log.d("equip_worker_status", "created");


        sql="CREATE TABLE IF NOT EXISTS " + TABLE_ANIMAL_INFO + "("
                + KEY_SQL_ANIMAL_ID + " INTEGER, "
                + KEY_SQL_CICLE_ANIMAL_ID + " INTEGER, "
                + KEY_SQL_BUILDING_ANIMAL_ID + " INTEGER, "
                + KEY_USER_ID + " INTEGER, "
                + KEY_ANIMAL_ID + " INTEGER PRIMARY KEY, "
                + KEY_ANIMAL_CICLE_ID+ " INTEGER, "
                + KEY_ANIMAL_BUILDING_ID + " INTEGER, "
                + KEY_ANIMAL_TYPE + " VARCHAR," +
                KEY_ANIMAL_OTHER + " VARCHAR, " +
                KEY_ANIMAL_QUANTITY+ " INTEGER, " +
                KEY_ANIMAL_PRICE+ " INTEGER, " +
                KEY_FLAG+ " INTEGER " +
                ")";
        Log.d("table_animal_info_sql", sql);
        db.execSQL(sql);
        Log.d("animal_info_status","created");

        sql="CREATE TABLE IF NOT EXISTS " + TABLE_FOOD_INFO + "("
                + KEY_SQL_FOOD_ID + " INTEGER, "
                + KEY_SQL_CICLE_FOOD_ID + " INTEGER, "
                + KEY_SQL_BUILDING_FOOD_ID + " INTEGER, "
                + KEY_USER_ID + " INTEGER, "
                + KEY_FOOD_ID + " INTEGER PRIMARY KEY, "
                + KEY_FOOD_CICLE_ID+ " INTEGER, "
                + KEY_FOOD_BUILDING_ID + " INTEGER, "
                + KEY_FOOD_DATE + " VARCHAR," +
                KEY_FOOD_DESIGNATION + " VARCHAR, " +
                KEY_FOOD_QUANTITY+ " INTEGER, " +
                KEY_FOOD_PRICE+ " VARCHAR, " +
                KEY_FLAG+ " INTEGER " +
                ")";
        Log.d("table_food_info_sql", sql);
        db.execSQL(sql);
        Log.d("animal_food_status","created");


        sql="CREATE TABLE IF NOT EXISTS " + TABLE_TEMP_INFO + "("
                + KEY_SQL_TEMP_ID + " INTEGER, "
                + KEY_SQL_TEMP_CICLE_ID + " INTEGER, "
                + KEY_SQL_TEMP_BUILDING_ID + " INTEGER, "
                + KEY_USER_ID + " INTEGER, "
                + KEY_TEMP_ID + " INTEGER PRIMARY KEY, "
                + KEY_TEMP_CICLE_ID+ " INTEGER, "
                + KEY_TEMP_BUILDING_ID + " INTEGER, "
                + KEY_TEMP_TEMP + " INTEGER," +
                KEY_TEMP_LUX + " INTEGER, " +
                KEY_TEMP_HUMIDITY+ " INTEGER, " +
                KEY_TEMP_DATE+ " VARCHAR, " +
                KEY_FLAG+" INTEGER "+
                ")";
        Log.d("table_temp_info_sql", sql);
        db.execSQL(sql);
        Log.d("temp_status","created");

        sql="CREATE TABLE IF NOT EXISTS " + TABLE_DEATH_INFO + "("
                + KEY_SQL_DEATH_ID + " INTEGER, "
                + KEY_SQL_CICLE_DEATH_ID + " INTEGER, "
                + KEY_SQL_BUILDING_DEATH_ID + " INTEGER, "
                + KEY_USER_ID + " INTEGER, "
                + KEY_DEATH_ID + " INTEGER PRIMARY KEY, "
                + KEY_DEATH_CICLE_ID+ " INTEGER, "
                + KEY_DEATH_BUILDING_ID + " INTEGER, "
                + KEY_DEATH_DATE + " VARCHAR," +
                KEY_DEATH_NO + " INTEGER, " +
                KEY_FLAG + " INTEGER " +
                ")";
        Log.d("table_egg_info_sql", sql);
        db.execSQL(sql);
        Log.d("egg_info_status","created");

        sql="CREATE TABLE IF NOT EXISTS " + TABLE_NORMAL_EGG_INFO + "("
                + KEY_SQL_NORMAL_EGG_ID + " INTEGER, "
                + KEY_SQL_NORMAL_EGG_CICLE_ID + " INTEGER, "
                + KEY_SQL_NORMAL_EGG_BUILDING_ID + " INTEGER, "
                + KEY_USER_ID + " INTEGER, "
                + KEY_NORMAL_EGG_ID + " INTEGER PRIMARY KEY, "
                + KEY_NORMAL_EGG_CICLE_ID + " INTEGER, "
                + KEY_NORMAL_EGG_BUILDING_ID + " INTEGER, "
                + KEY_NORMAL_EGG_DATE + " VARCHAR," +
                KEY_NORAML_EGG_NUMBER + " INTEGER, " +
                KEY_NORAML_EGG_TYPE + " VARCHAR, "+
                KEY_FLAG+" INTEGER "+
                ")";
        Log.d("table_egg_info_sql", sql);
        db.execSQL(sql);
        Log.d("gg_info_status","created");

        sql="CREATE TABLE IF NOT EXISTS " + TABLE_BROKEN_EGG_INFO + "("
                + KEY_SQL_BROKEN_EGG_ID + " INTEGER, "
                + KEY_SQL_BROKEN_EGG_CICLE_ID + " INTEGER, "
                + KEY_SQL_BROKEN_EGG_BUILDING_ID + " INTEGER, "
                + KEY_USER_ID + " INTEGER, "
                + KEY_BROKEN_EGG_ID + " INTEGER PRIMARY KEY, "
                + KEY_BROKEN_EGG_CICLE_ID + " INTEGER, "
                + KEY_BROKEN_EGG_BUILDING_ID + " INTEGER, "
                + KEY_BROKEN_EGG_DATE + " VARCHAR," +
                KEY_BROKEN_EGG_NUMBER + " INTEGER, " +
                KEY_BROKEN_EGG_TYPE + " VARCHAR, "+
                KEY_FLAG+" INTEGER "+
                ")";
        Log.d("table_egg_info_sql", sql);
        db.execSQL(sql);
        Log.d("gg_info_status","created");


        sql="CREATE TABLE IF NOT EXISTS " + TABLE_MEDICAL_INFO + "("
                + KEY_SQL_MEDICAL_ID + " INTEGER, " +
                KEY_SQL_CICLE_MEDICAL_ID + " INTEGER, "
                + KEY_SQL_BUILDING_MEDICAL_ID + " INTEGER, "
                + KEY_USER_ID + " INTEGER, "
                + KEY_MEDICAL_ID + " INTEGER PRIMARY KEY, "
                + KEY_MEDICAL_CICLE_ID+ " INTEGER, "
                + KEY_MEDICAL_BUILDING_ID + " INTEGER, "
                + KEY_MEDICAL_OPERATION_TYPE + " VARCHAR," +
                KEY_MEDICAL_OPERATION_NAME + " VARCHAR, " +
                KEY_MEDICAL_DATE+ " VARCHAR, " +
                KEY_MEDICAL_COMMENT+ " VARCHAR, " +
                KEY_MEDICAL_PRODUCT+ " VARCHAR, " +
                KEY_MEDICAL_PRICE+ " INTEGER, " +
                KEY_FLAG+ " INTEGER " +
                ")";
        Log.d("table_medical_info_sql", sql);
        db.execSQL(sql);
        Log.d("animal_medical_status","created");

        sql="CREATE TABLE IF NOT EXISTS " + TABLE_CON_INFO + "("
                + KEY_SQL_CON_ID + " INTEGER, "
                + KEY_SQL_CICLE_CON_ID + " INTEGER, "
                + KEY_SQL_BUILDING_CON_ID + " INTEGER, "
                + KEY_USER_ID + " INTEGER, "
                + KEY_CON_ID + " INTEGER PRIMARY KEY, "
                + KEY_CON_CICLE_ID+ " INTEGER, "
                + KEY_CON_BUILDING_ID + " INTEGER, "
                + KEY_CON_DESIGNATION + " VARCHAR," +
                KEY_CON_FOOD_LIST + " VARCHAR, " +
                KEY_CON_QUANTITY+ " INTEGER, " +
                KEY_CON_NUMBER+ " INTEGER, " +
                KEY_CON_PER+ " VARCHAR, " +
                KEY_CON_DATE_START+ " VARCHAR, " +
                KEY_CON_DATE_END+ " VARCHAR, " +
                KEY_FLAG+ " VARCHAR " +
                ")";
        Log.d("table_con_info_sql", sql);
        db.execSQL(sql);
        Log.d("con_status","created");

        sql="CREATE TABLE IF NOT EXISTS " + TABLE_SETTING_INFO + "("
                + KEY_SETTING_NOTIFICATION+ " INTEGER, "
                + KEY_SETTING_COUNTRY+ " VARCHAR, "
                + KEY_SETTING_MONEY + " VARCHAR"+
                ")";
        Log.d("table_setting_info_sql", sql);
        db.execSQL(sql);
        Log.d("table_setting_status","created");

        sql="CREATE TABLE IF NOT EXISTS " + TABLE_NOTES_INFO + "("
                + KEY_NOTES_ID+ " INTEGER PRIMARY KEY, "
                + KEY_NOTES_CICLE_ID+ " INTEGER, "
                + KEY_NOTES_BUILDING_ID + " INTEGER, "+
                KEY_NOTES+ " TEXT"+
                ")";
        Log.d("table_setting_info_sql", sql);
        db.execSQL(sql);
        Log.d("table_setting_status","created");

        sql="CREATE TABLE IF NOT EXISTS " + TABLE_DOWN_PAYMENT_INFO + "("
                + KEY_SQL_DOWN_PAYMENT_ID+ " INTEGER, "
                + KEY_SQL_CICLE_DOWN_PAYMENT_ID+ " INTEGER, "
                + KEY_SQL_BUILDING_DOWN_PAYMENT_ID+ " INTEGER, "
                + KEY_USER_ID+ " INTEGER, "
                + KEY_DOWN_PAYMENT_ID+ " INTEGER PRIMARY KEY, "
                + KEY_DOWN_PAYMENT_CICLE_ID+ " INTEGER, "
                + KEY_DOWN_PAYMENT_BUILDING_ID + " INTEGER, "+
                KEY_DOWN_PAYMENT_WORKER_NAME + " VARCHAR, "+
                KEY_DOWN_PAYMENT_DATE + " VARCHAR, "+
                KEY_DOWN_PAYMENT_PRICE+ " INTEGER,"+
                KEY_FLAG+ " INTEGER"+
                ")";
        Log.d("table_dp_info_sql", sql);
        db.execSQL(sql);
        Log.d("table_dp_status","created");

        sql="CREATE TABLE IF NOT EXISTS " + TABLE_ADDITIONAL_EXPENSE_INFO + "("
                + KEY_SQL_ADDITIONAL_EXPENSE_ID+ " INTEGER, "
                + KEY_SQL_CICLE_ADDITIONAL_EXPENSE_ID+ " INTEGER, "
                + KEY_SQL_BUILDING_ADDITIONAL_EXPENSE_ID+ " INTEGER, "
                + KEY_USER_ID+ " INTEGER, "
                + KEY_ADDITIONAL_EXPENSE_ID+ " INTEGER PRIMARY KEY, "
                + KEY_ADDITIONAL_EXPENSE_CICLE_ID+ " INTEGER, "
                + KEY_ADDITIONAL_EXPENSE_BUILDING_ID + " INTEGER, "+
                KEY_ADDITIONAL_EXPENSE_TYPE + " VARCHAR, "+
                KEY_ADDITIONAL_EXPENSE_DESIGNATION + " VARCHAR, "+
                KEY_ADDITIONAL_EXPENSE_DATE+ " VARCHAR,"+
                KEY_ADDITIONAL_EXPENSE_QUANTITY+ " INTEGER,"+
                KEY_ADDITIONAL_EXPENSE_PRICE+ " INTEGER,"+
                KEY_FLAG+ " INTEGER"+
                ")";
        Log.d("table_a_expen_info_sql",sql);
        db.execSQL(sql);
        Log.d("table_a_expense_status","created");

        sql="CREATE TABLE IF NOT EXISTS " + TABLE_RETURNED_EXPENSE_INFO + "("
                + KEY_SQL_RETURNED_EXPENSE_ID+ " INTEGER, "
                + KEY_SQL_CICLE_RETURNED_EXPENSE_ID+ " INTEGER, "
                + KEY_SQL_BUILDING_RETURNED_EXPENSE_ID+ " INTEGER, "
                + KEY_USER_ID+ " INTEGER, "
                + KEY_RETURNED_EXPENSE_ID+ " INTEGER PRIMARY KEY, "
                + KEY_RETURNED_EXPENSE_CICLE_ID+ " INTEGER, "
                + KEY_RETURNED_EXPENSE_BUILDING_ID + " INTEGER, "+
                KEY_RETURNED_EXPENSE_TYPE + " VARCHAR, "+
                KEY_RETURNED_EXPENSE_DATE+ " VARCHAR,"+
                KEY_RETURNED_EXPENSE_QUANTITY+ " INTEGER,"+
                KEY_RETURNED_EXPENSE_PRICE+ " INTEGER,"+
                KEY_FLAG+ " INTEGER"+
                ")";
        Log.d("table_R_expen_info_sql",sql);
        db.execSQL(sql);
        Log.d("table_r_expense_status","created");

        sql="CREATE TABLE IF NOT EXISTS " + TABLE_TASK_INFO + "("
                + KEY_TASK_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_USER_ID + " INTEGER, "
                + KEY_SQL_TASK_ID+ " INTEGER, "
                + KEY_TASK_CICLE_NAME + " VARCHAR, "+
                KEY_TASK_BUILDING_NAME + " VARCHAR, "+
                KEY_TASK_NAME+ " VARCHAR, "+
                KEY_TASK_DATE+ " VARCHAR, "+
                KEY_TASK_LOCATION+ " VARCHAR, "+
                KEY_TASK_TIME_START+ " VARCHAR, "+
                KEY_TASK_DONE+ " INTEGER "+
                ")";

        Log.d("table_task_info_sql",sql);
        db.execSQL(sql);
        Log.d("table_task_status","created");

    }

    public void onUpgrade(SQLiteDatabase db,int previous_version,int new_version){

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER_INFO);
        onCreate(db);
    }

    public int check_first_name_exists(String fname){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_USER_INFO+ " WHERE " + KEY_F_NAME + " =?";
        Cursor cursor=db.rawQuery(sql, new String[]{fname});

        if(cursor!=null){
            if(cursor.getCount()==1){
                return 1;
            }
            else
                return -1;


        }
        return -2;
    }

    public int check_last_name_exists(String lname){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_USER_INFO+ " WHERE " + KEY_L_NAME + " =?";
        Cursor cursor=db.rawQuery(sql, new String[]{lname});
        int count;
        if(cursor!=null){
            if(cursor.getCount()==1){
                return 1;
            }
            else
                return -1;


        }
        cursor.close();
        
        return -2;
    }

    public int check_email_exists(String email){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_USER_INFO+" WHERE "+KEY_EMAIL+"=?";
        Cursor cursor =db.rawQuery(sql, new String[]{email});

        if(cursor!=null){
            if(cursor.getCount()==1){
                return 1;
            }
            else
                return -1;


        }
        cursor.close();
        
        return -2;
    }

    public String InsertUser(String fname,String lname,String password,String email){
        int firstname_exists=check_first_name_exists(fname);
        int lastame_exists=check_last_name_exists(lname);
        int email_exists=check_email_exists(email);
        SQLiteDatabase db=getWritableDatabase();
        if((firstname_exists==1 && lastame_exists==1) || email_exists==1){
            return "username/email already exists exists";
        }
        else{
            ContentValues values=new ContentValues();
            values.put(KEY_F_NAME,fname);
            values.put(KEY_L_NAME,lname);
            values.put(KEY_PASSWORD,password);
            values.put(KEY_EMAIL,email);
            db.insert(TABLE_USER_INFO,null,values);
            return "Success";
        }
    }

    public int check_user_exists(String email_id,String password){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_USER_INFO+" WHERE "+KEY_EMAIL+"=? AND "+KEY_PASSWORD+"=?";
        Cursor cursor=db.rawQuery(sql, new String[]{email_id, password});
        if(cursor!=null){
            if(cursor.getCount()==1){
                return 1;
            }
            else
                return -1;
        }
        cursor.close();
        
        return -2;
    }



    //Cicle
    public int CreateCicle(cicle cicle){
        int id=-1;
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_SQL_CICLE_ID,cicle.getSql_cicle_id());
        values.put(KEY_USER_ID,cicle.getUser_id());
        values.put(KEY_CICLE_TITLE,cicle.getTitle());
        values.put(KEY_CICLE_OWNER,cicle.getOwner());
        values.put(KEY_CICLE_LOCATION,cicle.getLocation());
        values.put(KEY_CICLE_START_DATE,cicle.getStart_date());
        values.put(KEY_CICLE_FINISH_DATE,cicle.getFinish_date());
        values.put(KEY_CICLE_TYPE,cicle.getType());
        values.put(KEY_CICLE_DONE,cicle.getDone());
        values.put(KEY_FLAG,cicle.getFlag());
        id=(int)db.insert(TABLE_CICLE_INFO, null, values);
        Log.d("cicle_inserted", cicle.getTitle());
        
        return  id;
        //cursor.close();

    }

    public cicle getCicle(int id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_CICLE_INFO+" WHERE "+KEY_CICLE_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(id)});
        cicle cicle=null;
        if(cursor!=null){
            if(cursor.moveToFirst()){
                Log.d("cicle_found",String.valueOf(cursor.getInt(0)));
                cicle=new cicle();
                cicle.setSql_cicle_id(cursor.getInt(0));
                cicle.setCicle_id(cursor.getInt(1));
                cicle.setUser_id(cursor.getInt(2));
                cicle.setTitle(cursor.getString(3));
                cicle.setOwner(cursor.getString(4));
                cicle.setLocation(cursor.getString(5));
                cicle.setStart_date(cursor.getString(6));
                cicle.setFinish_date(cursor.getString(7));
                cicle.setType(cursor.getString(8));
                cicle.setDone(cursor.getInt(9));
                cicle.setFlag(cursor.getInt(10));
            }
        }
        cursor.close();
        
        return  cicle;

    }


    public cicle get_sql_cicle(int sql_cicle_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_CICLE_INFO+" WHERE "+KEY_SQL_CICLE_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(sql_cicle_id)});
        cicle cicle=null;
        if(cursor!=null){
            if(cursor.moveToFirst()){
                Log.d("cicle_found",String.valueOf(cursor.getInt(0)));
                cicle=new cicle();
                cicle.setSql_cicle_id(cursor.getInt(0));
                cicle.setCicle_id(cursor.getInt(1));
                cicle.setUser_id(cursor.getInt(2));
                cicle.setTitle(cursor.getString(3));
                cicle.setOwner(cursor.getString(4));
                cicle.setLocation(cursor.getString(5));
                cicle.setStart_date(cursor.getString(6));
                cicle.setFinish_date(cursor.getString(7));
                cicle.setType(cursor.getString(8));
                cicle.setDone(cursor.getInt(9));
                cicle.setFlag(cursor.getInt(10));
            }
        }
        cursor.close();
        
        return  cicle;

    }


    public int updateCicle(cicle cicle){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_SQL_CICLE_ID,cicle.getSql_cicle_id());
        values.put(KEY_CICLE_ID,cicle.getCicle_id());
        values.put(KEY_USER_ID,cicle.getUser_id());
        values.put(KEY_CICLE_TITLE,cicle.getTitle());
        values.put(KEY_CICLE_OWNER,cicle.getOwner());
        values.put(KEY_CICLE_LOCATION,cicle.getLocation());
        values.put(KEY_CICLE_START_DATE,cicle.getStart_date());
        values.put(KEY_CICLE_FINISH_DATE,cicle.getFinish_date());
        values.put(KEY_CICLE_TYPE, cicle.getType());
        values.put(KEY_CICLE_DONE, cicle.getDone());
        values.put(KEY_FLAG,cicle.getFlag());
        int id=db.update(TABLE_CICLE_INFO, values, KEY_CICLE_ID + "=?", new String[]{String.valueOf(cicle.getCicle_id())});
        Log.d("cicle_updated", cicle.getTitle());
        
        return id;
        // cursor.close();

    }

    public void deleteCicle(cicle cicle){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_CICLE_INFO, KEY_CICLE_ID + "=?", new String[]{String.valueOf((cicle.getCicle_id()))});
        Log.d("cicle deleted", cicle.getTitle());
        
    }

    public List<cicle> get_all_not_done(int user_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_CICLE_INFO+" WHERE "+KEY_CICLE_DONE+"=? AND "+KEY_USER_ID+"=? AND ("+KEY_FLAG+"=0 OR "+KEY_FLAG+"=1 OR "+KEY_FLAG+"=4)";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(1),String.valueOf(user_id)});
        List<cicle> cicle_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    cicle cicle=new cicle();
                    cicle.setSql_cicle_id(cursor.getInt(0));
                    cicle.setCicle_id(cursor.getInt(1));
                    cicle.setUser_id(cursor.getInt(2));
                    cicle.setTitle(cursor.getString(3));
                    cicle.setOwner(cursor.getString(4));
                    cicle.setLocation(cursor.getString(5));
                    cicle.setStart_date(cursor.getString(6));
                    cicle.setFinish_date(cursor.getString(7));
                    cicle.setType(cursor.getString(8));
                    cicle.setDone(cursor.getInt(9));
                    cicle.setFlag(cursor.getInt(10));
                    cicle_list.add(cicle);
                }while(cursor.moveToNext());
                Log.d("cicle_get_all_done",String.valueOf(cursor.getCount()));
            }
        }
        cursor.close();
        
        return cicle_list;
    }

    public List<cicle> get_all_done(int user_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_CICLE_INFO+" WHERE "+KEY_CICLE_DONE+"=? AND "+KEY_USER_ID+"=?  AND ("+KEY_FLAG+"=0 OR "+KEY_FLAG+"=1 OR "+KEY_FLAG+"=4)";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(2),String.valueOf(user_id)});
        List<cicle> cicle_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    cicle cicle=new cicle();
                    cicle.setSql_cicle_id(cursor.getInt(0));
                    cicle.setCicle_id(cursor.getInt(1));
                    cicle.setUser_id(cursor.getInt(2));
                    cicle.setTitle(cursor.getString(3));
                    cicle.setOwner(cursor.getString(4));
                    cicle.setLocation(cursor.getString(5));
                    cicle.setStart_date(cursor.getString(6));
                    cicle.setFinish_date(cursor.getString(7));
                    cicle.setType(cursor.getString(8));
                    cicle.setDone(cursor.getInt(9));
                    cicle.setFlag(cursor.getInt(10));
                    cicle_list.add(cicle);
                }while(cursor.moveToNext());
                Log.d("cicle_get_all_not_done",String.valueOf(cursor.getCount()));
            }
        }
        cursor.close();
        
        return cicle_list;
    }

    public List<cicle> get_all_cicle(int user_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_CICLE_INFO+" WHERE "+KEY_USER_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(user_id)});
        List<cicle> cicle_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    cicle cicle=new cicle();
                    cicle.setSql_cicle_id(cursor.getInt(0));
                    cicle.setCicle_id(cursor.getInt(1));
                    cicle.setUser_id(cursor.getInt(2));
                    cicle.setTitle(cursor.getString(3));
                    cicle.setOwner(cursor.getString(4));
                    cicle.setLocation(cursor.getString(5));
                    cicle.setStart_date(cursor.getString(6));
                    cicle.setFinish_date(cursor.getString(7));
                    cicle.setType(cursor.getString(8));
                    cicle.setDone(cursor.getInt(9));
                    cicle.setFlag(cursor.getInt(10));
                    cicle_list.add(cicle);
                }while(cursor.moveToNext());
                Log.d("cicle_get_all_not_done",String.valueOf(cursor.getCount()));
            }
        }
        cursor.close();
        
        return cicle_list;
    }



/*
    //Title
    public void CreateTitle(title_ title){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_TITLE_TITLE,title.getTitle());
        values.put(KEY_TITLE_DONE,title.getDone());
        db.insert(TABLE_TITLE_INFO, null, values);
        Log.d("title_inserted",title.getTitle());
    }

    public title_ getTitle(int id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_TITLE_INFO+" WHERE "+KEY_TITLE_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(id)});
        title_ title=new title_();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                Log.d("cicle_found", String.valueOf(cursor.getInt(0)));

                title.setTitle_id(cursor.getInt(0));
                title.setTitle(cursor.getString(1));
                title.setDone(cursor.getInt(2));
            }
        }
        return  title;

    }

    public int updateTitle(title_ title){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_TITLE_ID,title.getTitle_id());
        values.put(KEY_TITLE_TITLE, title.getTitle());
        values.put(KEY_TITLE_DONE, title.getDone());
        int id=db.update(TABLE_TITLE_INFO, values, KEY_TITLE_ID+ "=?", new String[]{String.valueOf(title.getTitle_id())});
        Log.d("title_updated", title.getTitle());
        return id;
    }

    public void deleteTitle(title_ title){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_TITLE_INFO, KEY_TITLE_ID + "=?", new String[]{String.valueOf((title.getTitle_id()))});
        Log.d("title deleted", title.getTitle());
        
    }

    public List<title_> get_all_not_done_title(){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_TITLE_INFO+" WHERE "+KEY_TITLE_DONE+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(1)});
        List<title_> title_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    title_ title=new title_();
                    title.setTitle_id(cursor.getInt(0));
                    title.setTitle(cursor.getString(1));
                    title.setDone(cursor.getInt(2));
                    title_list.add(title);
                }while(cursor.moveToNext());
                Log.d("title_get_all_not_done",String.valueOf(cursor.getCount()));
            }
        }
        return title_list;
    }

    public List<title_> get_all_done_title(){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_TITLE_INFO+" WHERE "+KEY_TITLE_DONE+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(2)});
        List<title_> title_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    title_ title=new title_();
                    title.setTitle_id(cursor.getInt(0));
                    title.setTitle(cursor.getString(1));
                    title.setDone(cursor.getInt(2));
                    title_list.add(title);
                }while(cursor.moveToNext());
                Log.d("title_get_all_done",String.valueOf(cursor.getCount()));
            }
        }
        return title_list;
    }

*/

    //Building
    public void createBuilding(building_class building){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_USER_ID,building.getUser_id());
        values.put(KEY_SQL_BUILDING_ID,building.getSql_building_id());
        values.put(KEY_SQL_CICLE_BUILDING_ID,building.getSql_cicle_id());
        values.put(KEY_BUILDING_CICLE_ID,building.getCicle_id());
        values.put(KEY_BUILDING_TITLE,building.getTitle());
        values.put(KEY_BUILDING_CAPACITY,building.getCapacity());
        values.put(KEY_BUILDING_TYPE,building.getType());
        values.put(KEY_BUILDING_OTHER,building.getOther());
        values.put(KEY_FLAG, building.getFlag());
        Log.d("building_inserted", building.getTitle());
        db.insert(TABLE_BUILDING_INFO, null, values);
        //cursor.close();
        
    }

    public building_class getBuilding(int key){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_BUILDING_INFO+" WHERE "+KEY_BUILDING_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(key)});
        building_class building=null;
        if(cursor!=null){
            if(cursor.moveToFirst()){
                building=new building_class();
                building.setUser_id(cursor.getInt(0));
                building.setSql_building_id(cursor.getInt(1));
                building.setSql_cicle_id(cursor.getInt(2));
                building.setBuilding_id(cursor.getInt(3));
                building.setCicle_id(cursor.getInt(4));
                building.setTitle(cursor.getString(5));
                building.setCapacity(cursor.getInt(6));
                building.setType(cursor.getString(7));
                building.setOther(cursor.getString(8));
                building.setFlag(cursor.getInt(9));
            }

        }
        cursor.close();
        
        return building;
    }

    public building_class getBuildingBYSQL(int sql_building_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_BUILDING_INFO+" WHERE "+KEY_SQL_BUILDING_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(sql_building_id)});
        building_class building=null;
        if(cursor!=null){
            if(cursor.moveToFirst()){
                building=new building_class();
                building.setUser_id(cursor.getInt(0));
                building.setSql_building_id(cursor.getInt(1));
                building.setSql_cicle_id(cursor.getInt(2));
                building.setBuilding_id(cursor.getInt(3));
                building.setCicle_id(cursor.getInt(4));
                building.setTitle(cursor.getString(5));
                building.setCapacity(cursor.getInt(6));
                building.setType(cursor.getString(7));
                building.setOther(cursor.getString(8));
                building.setFlag(cursor.getInt(9));
            }
        }
        cursor.close();
        
        //Log.d("building/sql",String.valueOf(building.getBuilding_id()));
        return building;
    }

    public List<building_class> get_all_building(int cicle_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM  "+TABLE_BUILDING_INFO+" WHERE "+KEY_BUILDING_CICLE_ID+"=? AND "+KEY_FLAG+"!=-1";
        List<building_class> building_list=new ArrayList<>();
        Cursor cursor=db.rawQuery(sql, new String[]{String.valueOf(cicle_id)});
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do {
                    building_class building = new building_class();
                    building.setUser_id(cursor.getInt(0));
                    building.setSql_building_id(cursor.getInt(1));
                    building.setSql_cicle_id(cursor.getInt(2));
                    building.setBuilding_id(cursor.getInt(3));
                    building.setCicle_id(cursor.getInt(4));
                    building.setTitle(cursor.getString(5));
                    building.setCapacity(cursor.getInt(6));
                    building.setType(cursor.getString(7));
                    building.setOther(cursor.getString(8));
                    building.setFlag(cursor.getInt(9));
                    building_list.add(building);
                }while (cursor.moveToNext());
            }
        }
        cursor.close();
        
        Log.d("all_building", String.valueOf(building_list.size()));

        return building_list;
    }

    public int getBuildingCount(int cicle_id){
        SQLiteDatabase db=getReadableDatabase();
        int count=-1;
        sql="SELECT * FROM "+TABLE_BUILDING_INFO+" WHERE "+KEY_BUILDING_CICLE_ID+"=?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(cicle_id)});
        if(cursor!=null){
            count=cursor.getCount();
        }
        cursor.close();
        
        return count;
    }

    public int updateBuilding(building_class building){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_USER_ID,building.getUser_id());
        values.put(KEY_SQL_BUILDING_ID,building.getSql_building_id());
        values.put(KEY_SQL_CICLE_BUILDING_ID,building.getSql_cicle_id());
        values.put(KEY_BUILDING_CICLE_ID,building.getCicle_id());
        values.put(KEY_BUILDING_TITLE,building.getTitle());
        values.put(KEY_BUILDING_CAPACITY,building.getCapacity());
        values.put(KEY_BUILDING_TYPE,building.getType());
        values.put(KEY_BUILDING_OTHER, building.getOther());
        values.put(KEY_FLAG, building.getFlag());
        int count=db.update(TABLE_BUILDING_INFO, values, KEY_BUILDING_ID + "=?", new String[]{String.valueOf(building.getBuilding_id())});
        Log.d("bulding_updated", String.valueOf(building.getBuilding_id()) + "::" + String.valueOf(count));
        //cursor.close();
        
        return count;
    }

    public void deleteBuilding(building_class building){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_BUILDING_INFO, KEY_BUILDING_ID + "=?", new String[]{String.valueOf(building.getBuilding_id())});
        
    }

    public List<building_class> getAllBuilding(int user_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM  "+TABLE_BUILDING_INFO+" WHERE "+KEY_USER_ID+"=?";
        List<building_class> building_list=new ArrayList<>();
        Cursor cursor=db.rawQuery(sql, new String[]{String.valueOf(user_id)});
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do {
                    building_class building = new building_class();
                    building.setUser_id(cursor.getInt(0));
                    building.setSql_building_id(cursor.getInt(1));
                    building.setSql_cicle_id(cursor.getInt(2));
                    building.setBuilding_id(cursor.getInt(3));
                    building.setCicle_id(cursor.getInt(4));
                    building.setTitle(cursor.getString(5));
                    building.setCapacity(cursor.getInt(6));
                    building.setType(cursor.getString(7));
                    building.setOther(cursor.getString(8));
                    building.setFlag(cursor.getInt(9));
                    building_list.add(building);
                }while (cursor.moveToNext());
            }
        }
        cursor.close();
        
        Log.d("all_building_user_id", String.valueOf(building_list.size()));

        return building_list;
    }

    //Equipment
    public void createEquipment(equipment_ equipment){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_SQL_EQUIP_ID,equipment.getSql_equip_id());
        values.put(KEY_SQL_EQUIP_CICLE_ID,equipment.getSql_cicle_id());
        values.put(KEY_SQL_EQUIP_BUILDING_ID,equipment.getSql_building_id());
        values.put(KEY_USER_ID,equipment.getUser_id());
        values.put(KEY_EQUIP_CICLE_ID,equipment.getCicle_id());
        values.put(KEY_EQUIP_BUILDING_ID,equipment.getBuilding_id());
        values.put(KEY_EQUIP_TYPE,equipment.getType());
        values.put(KEY_EQUIP_DESIGNATION,equipment.getDesignation());
        values.put(KEY_EQUIP_QUANTITY,equipment.getQuantity());
        values.put(KEY_EQUIP_PRICE, equipment.getPrice());
        values.put(KEY_FLAG, equipment.getFlag());
        long inserted=db.insert(TABLE_EQUIP_INFO, null, values);
        Log.d("equip_insert", String.valueOf(equipment.getEquip_id()) + ":::" + String.valueOf(inserted));
        //cursor.close();
        
    }

    public equipment_ getEquipment(int equip_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_EQUIP_INFO+" WHERE "+KEY_EQUIP_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(equip_id)});
        equipment_ equipment=new equipment_();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                equipment.setSql_equip_id(cursor.getInt(0));
                equipment.setSql_cicle_id(cursor.getInt(1));
                equipment.setSql_building_id(cursor.getInt(2));
                equipment.setUser_id(cursor.getInt(3));
                equipment.setEquip_id(cursor.getInt(4));
                equipment.setCicle_id(cursor.getInt(5));
                equipment.setBuilding_id(cursor.getInt(6));
                equipment.setType(cursor.getString(7));
                equipment.setDesignation(cursor.getString(8));
                equipment.setQuantity(cursor.getInt(9));
                equipment.setPrice(cursor.getInt(10));
                equipment.setFlag(cursor.getInt(11));
            }
        }
        Log.d("equipment_get_equipment", equipment.getType());
        cursor.close();
        
        return equipment;

    }

    public equipment_ getEquipmentBYSQL(int equip_sql){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_EQUIP_INFO+" WHERE "+KEY_SQL_EQUIP_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(equip_sql)});
        equipment_ equipment=null;
        if(cursor!=null){
            if(cursor.moveToFirst()){
                equipment=new equipment_();
                equipment.setSql_equip_id(cursor.getInt(0));
                equipment.setSql_cicle_id(cursor.getInt(1));
                equipment.setSql_building_id(cursor.getInt(2));
                equipment.setUser_id(cursor.getInt(3));
                equipment.setEquip_id(cursor.getInt(4));
                equipment.setCicle_id(cursor.getInt(5));
                equipment.setBuilding_id(cursor.getInt(6));
                equipment.setType(cursor.getString(7));
                equipment.setDesignation(cursor.getString(8));
                equipment.setQuantity(cursor.getInt(9));
                equipment.setPrice(cursor.getInt(10));
                equipment.setFlag(cursor.getInt(11));
                Log.d("get_equipmentSQL", equipment.getType());
            }
        }
        cursor.close();
        
        return equipment;

    }

    public void deleteEquipment(equipment_ equipment){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_EQUIP_INFO, KEY_EQUIP_ID + "=?", new String[]{String.valueOf(equipment.getEquip_id())});
        Log.d("equipment_del_status", "deleted");
        
    }

    public int updateEquipment(equipment_ equipment){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_SQL_EQUIP_ID,equipment.getSql_equip_id());
        values.put(KEY_SQL_EQUIP_CICLE_ID,equipment.getSql_cicle_id());
        values.put(KEY_SQL_EQUIP_BUILDING_ID,equipment.getSql_building_id());
        values.put(KEY_USER_ID,equipment.getUser_id());
        values.put(KEY_EQUIP_ID,equipment.getEquip_id());
        values.put(KEY_EQUIP_CICLE_ID,equipment.getCicle_id());
        values.put(KEY_EQUIP_BUILDING_ID,equipment.getBuilding_id());
        values.put(KEY_EQUIP_TYPE,equipment.getType());
        values.put(KEY_EQUIP_DESIGNATION,equipment.getDesignation());
        values.put(KEY_EQUIP_QUANTITY,equipment.getQuantity());
        values.put(KEY_EQUIP_PRICE, equipment.getPrice());
        values.put(KEY_FLAG,equipment.getFlag());
        int count=db.update(TABLE_EQUIP_INFO,values,KEY_EQUIP_ID+"=?",new String[]{String.valueOf(equipment.getEquip_id())});
        Log.d("equip_update",String.valueOf(equipment.getEquip_id())+"::::"+String.valueOf(count));
        
        return count;
    }

    public List<equipment_> get_all_equipment(int cicle_id,int building_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_EQUIP_INFO+" WHERE "+KEY_EQUIP_CICLE_ID+"=? AND "+KEY_EQUIP_BUILDING_ID+"=? AND "+KEY_FLAG+"!=-1";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        List<equipment_> equipment_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    equipment_ equipment=new equipment_();
                    equipment.setSql_equip_id(cursor.getInt(0));
                    equipment.setSql_cicle_id(cursor.getInt(1));
                    equipment.setSql_building_id(cursor.getInt(2));
                    equipment.setUser_id(cursor.getInt(3));
                    equipment.setEquip_id(cursor.getInt(4));
                    equipment.setCicle_id(cursor.getInt(5));
                    equipment.setBuilding_id(cursor.getInt(6));
                    equipment.setType(cursor.getString(7));
                    equipment.setDesignation(cursor.getString(8));
                    equipment.setQuantity(cursor.getInt(9));
                    equipment.setPrice(cursor.getInt(10));
                    equipment.setFlag(cursor.getInt(11));
                    equipment_list.add(equipment);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        Log.d("equipment_list", String.valueOf(equipment_list.size()));
        return  equipment_list;
    }

    public List<equipment_> get_all_equipment_by_ids(int cicle_id,int building_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_EQUIP_INFO+" WHERE "+KEY_EQUIP_CICLE_ID+"=? AND "+KEY_EQUIP_BUILDING_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        List<equipment_> equipment_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    equipment_ equipment=new equipment_();
                    equipment.setSql_equip_id(cursor.getInt(0));
                    equipment.setSql_cicle_id(cursor.getInt(1));
                    equipment.setSql_building_id(cursor.getInt(2));
                    equipment.setUser_id(cursor.getInt(3));
                    equipment.setEquip_id(cursor.getInt(4));
                    equipment.setCicle_id(cursor.getInt(5));
                    equipment.setBuilding_id(cursor.getInt(6));
                    equipment.setType(cursor.getString(7));
                    equipment.setDesignation(cursor.getString(8));
                    equipment.setQuantity(cursor.getInt(9));
                    equipment.setPrice(cursor.getInt(10));
                    equipment.setFlag(cursor.getInt(11));
                    equipment_list.add(equipment);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        Log.d("equipment_list", String.valueOf(equipment_list.size()));
        return  equipment_list;
    }

    public List<equipment_> get_all_equipment_by_user_id(int user_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_EQUIP_INFO+" WHERE "+KEY_USER_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(user_id)});
        List<equipment_> equipment_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    equipment_ equipment=new equipment_();
                    equipment.setSql_equip_id(cursor.getInt(0));
                    equipment.setSql_cicle_id(cursor.getInt(1));
                    equipment.setSql_building_id(cursor.getInt(2));
                    equipment.setUser_id(cursor.getInt(3));
                    equipment.setEquip_id(cursor.getInt(4));
                    equipment.setCicle_id(cursor.getInt(5));
                    equipment.setBuilding_id(cursor.getInt(6));
                    equipment.setType(cursor.getString(7));
                    equipment.setDesignation(cursor.getString(8));
                    equipment.setQuantity(cursor.getInt(9));
                    equipment.setPrice(cursor.getInt(10));
                    equipment.setFlag(cursor.getInt(11));
                    equipment_list.add(equipment);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        Log.d("equiplist_user_id",String.valueOf(equipment_list.size()));
        return  equipment_list;
    }



    //Image
    public void createImage(image_class image){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_SQL_IMAGE_ID,image.getSql_image_id());
        values.put(KEY_SQL_IMAGE_CICLE_ID,image.getSql_cicle_id());
        values.put(KEY_SQL_IMAGE_BUILDING_ID,image.getSql_building_id());
        values.put(KEY_USER_ID,image.getUser_id());
        values.put(KEY_IMAGE_CICLE_ID,image.getCicle_id());
        values.put(KEY_IMAGE_BUILDING_ID,image.getBuilding_id());
        values.put(KEY_IMAGE_NAME,image.getName());
        values.put(KEY_IMAGE_PATH,image.getPath());
        values.put(KEY_IMAGE_TYPE,image.getType());
        values.put(KEY_FLAG, image.getFlag());
        long inserted=db.insert(TABLE_IMAGE_INFO, null, values);
        Log.d("image_insert", String.valueOf(image.getImage_id()) + ":::" + String.valueOf(inserted) + String.valueOf(image.getPath()) + String.valueOf(image.getName()) + String.valueOf(image.getCicle_id())
                + String.valueOf(image.getBuilding_id()));
        //cursor.close();
        
    }
    public image_class getImage(int image_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_IMAGE_INFO+" WHERE "+KEY_IMAGE_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(image_id)});
        image_class image=new image_class();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                image.setSql_image_id(cursor.getInt(0));
                image.setSql_cicle_id(cursor.getInt(1));
                image.setSql_building_id(cursor.getInt(2));
                image.setUser_id(cursor.getInt(3));
                image.setImage_id(cursor.getInt(4));
                image.setCicle_id(cursor.getInt(5));
                image.setBuilding_id(cursor.getInt(6));
                image.setName(cursor.getString(7));
                image.setPath(cursor.getString(8));
                image.setType(cursor.getInt(9));
                image.setFlag(cursor.getInt(10));
            }
        }
        cursor.close();
        
        Log.d("image_get_image", image.getName());
        return image;

    }
    public image_class getImageBYSQL(int image_sql){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_IMAGE_INFO+" WHERE "+KEY_SQL_IMAGE_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(image_sql)});
        image_class image=null;
        if(cursor!=null){
            if(cursor.moveToFirst()){
                image=new image_class();
                image.setSql_image_id(cursor.getInt(0));
                image.setSql_cicle_id(cursor.getInt(1));
                image.setSql_building_id(cursor.getInt(2));
                image.setUser_id(cursor.getInt(3));
                image.setImage_id(cursor.getInt(4));
                image.setCicle_id(cursor.getInt(5));
                image.setBuilding_id(cursor.getInt(6));
                image.setName(cursor.getString(7));
                image.setPath(cursor.getString(8));
                image.setType(cursor.getInt(9));
                image.setFlag(cursor.getInt(10));
                Log.d("get_imageSQL", image.getName());
            }
        }
        cursor.close();
        
        return image;
    }
    public void deleteImage(image_class image){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_IMAGE_INFO, KEY_IMAGE_ID + "=?", new String[]{String.valueOf(image.getImage_id())});
        Log.d("image_del_status", "deleted");
        
    }
    public int updateImage(image_class image){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_SQL_IMAGE_ID,image.getSql_image_id());
        values.put(KEY_SQL_IMAGE_CICLE_ID,image.getSql_cicle_id());
        values.put(KEY_SQL_IMAGE_BUILDING_ID,image.getSql_building_id());
        values.put(KEY_USER_ID,image.getUser_id());
        values.put(KEY_IMAGE_CICLE_ID,image.getCicle_id());
        values.put(KEY_IMAGE_BUILDING_ID,image.getBuilding_id());
        values.put(KEY_IMAGE_NAME,image.getName());
        values.put(KEY_IMAGE_PATH,image.getPath());
        values.put(KEY_IMAGE_TYPE,image.getType());
        values.put(KEY_FLAG, image.getFlag());
        int count=db.update(TABLE_IMAGE_INFO,values,KEY_IMAGE_ID+"=?",new String[]{String.valueOf(image.getImage_id())});
        Log.d("image_update",String.valueOf(image.getImage_id())+"::::"+String.valueOf(count)+":::::"+String.valueOf(image.getFlag()));
        
        return count;
    }
    public List<image_class> get_all_image(int cicle_id,int building_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_IMAGE_INFO+" WHERE "+KEY_IMAGE_CICLE_ID+"=? AND "+KEY_IMAGE_BUILDING_ID+"=? AND "+KEY_FLAG+"!=-1";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        List<image_class> image_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    image_class image=new image_class();
                    image.setSql_image_id(cursor.getInt(0));
                    image.setSql_cicle_id(cursor.getInt(1));
                    image.setSql_building_id(cursor.getInt(2));
                    image.setUser_id(cursor.getInt(3));
                    image.setImage_id(cursor.getInt(4));
                    image.setCicle_id(cursor.getInt(5));
                    image.setBuilding_id(cursor.getInt(6));
                    image.setName(cursor.getString(7));
                    image.setPath(cursor.getString(8));
                    image.setType(cursor.getInt(9));
                    image.setFlag(cursor.getInt(10));
                    image_list.add(image);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        Log.d("image_list", String.valueOf(image_list.size()));
        return image_list;
    }
    public List<image_class> get_all_image_by_user_id(int user_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_IMAGE_INFO+" WHERE "+KEY_USER_ID+"=? AND "+KEY_IMAGE_TYPE+"=1";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(user_id)});
        List<image_class> image_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    image_class image=new image_class();
                    image.setSql_image_id(cursor.getInt(0));
                    image.setSql_cicle_id(cursor.getInt(1));
                    image.setSql_building_id(cursor.getInt(2));
                    image.setUser_id(cursor.getInt(3));
                    image.setImage_id(cursor.getInt(4));
                    image.setCicle_id(cursor.getInt(5));
                    image.setBuilding_id(cursor.getInt(6));
                    image.setName(cursor.getString(7));
                    image.setPath(cursor.getString(8));
                    image.setType(cursor.getInt(9));
                    image.setFlag(cursor.getInt(10));
                    image_list.add(image);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        Log.d("image_user_id",String.valueOf(image_list.size()));
        return image_list;
    }




    //Worker
    public int CreateWorker(worker_class worker){
        SQLiteDatabase db=getWritableDatabase();
        int id=-1;
        ContentValues values=new ContentValues();
        values.put(KEY_SQL_WORKER_ID,worker.getSql_worker_id());
        values.put(KEY_SQL_CICLE_WORKER_ID,worker.getSql_cicle_id());
        values.put(KEY_SQL_BUILDING_WORKER_ID,worker.getSql_building_id());
        values.put(KEY_USER_ID,worker.getUser_id());
        values.put(KEY_WORKER_CICLE_ID,worker.getCicle_id());
        values.put(KEY_WORKER_BUILDING_ID,worker.getBuilding_id());
        values.put(KEY_WORKER_NAME,worker.getName());
        values.put(KEY_WORKER_ADDRESS, worker.getAddress());
        values.put(KEY_WORKER_PHONE, worker.getTel());
        values.put(KEY_WORKER_START_DATE, worker.getDate_start());
        values.put(KEY_WORKER_SETACTIVE,worker.getSetactive());
        values.put(KEY_WORKER_PRICE, worker.getPrice_per_day());
        values.put(KEY_WORKER_TOTAL_SALARY,worker.getSalary());
        values.put(KEY_WORKER_LAST_DATE,worker.getLast_update());
        values.put(KEY_FLAG,worker.getFlag());
        id=(int)db.insert(TABLE_WORKER_INFO, null, values);
        Log.d("create_worker_status", String.valueOf(id));
        //cursor.close();
        
        return id;
    }

    public worker_class getWorker(int worker_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_WORKER_INFO+" WHERE "+KEY_WORKER_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(worker_id)});
        worker_class worker=null;
        if(cursor!=null){
            Log.d("db_get_worker",String.valueOf(cursor.getCount()));
            if(cursor.moveToFirst()){
                worker=new worker_class();
                worker.setSql_worker_id(cursor.getInt(0));
                worker.setSql_cicle_id(cursor.getInt(1));
                worker.setSql_building_id(cursor.getInt(2));
                worker.setUser_id(cursor.getInt(3));
                worker.setWorker_id(cursor.getInt(4));
                worker.setCicle_id(cursor.getInt(5));
                worker.setBuilding_id(cursor.getInt(6));
                worker.setName(cursor.getString(7));
                worker.setAddress(cursor.getString(8));
                worker.setTel(cursor.getString(9));
                worker.setDate_start(cursor.getString(10));
                worker.setSetactive(Integer.parseInt(cursor.getString(11)));
                worker.setPrice_per_day(cursor.getInt(12));
                worker.setSalary(cursor.getInt(13));
                worker.setLast_update(cursor.getString(14));
                worker.setFlag(cursor.getInt(15));
            }
        }
        //cursor.close();
        cursor.close();
        
        Log.d("db_get_worker",String.valueOf(worker_id));
        return worker;
    }

    public worker_class getWorkerBySql(int worker_sql_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_WORKER_INFO+" WHERE "+KEY_SQL_WORKER_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(worker_sql_id)});
        worker_class worker=null;
        if(cursor!=null){
            Log.d("db_get_worker",String.valueOf(cursor.getCount()));
            if(cursor.moveToFirst()){
                worker=new worker_class();
                worker.setSql_worker_id(cursor.getInt(0));
                worker.setSql_cicle_id(cursor.getInt(1));
                worker.setSql_building_id(cursor.getInt(2));
                worker.setUser_id(cursor.getInt(3));
                worker.setWorker_id(cursor.getInt(4));
                worker.setCicle_id(cursor.getInt(5));
                worker.setBuilding_id(cursor.getInt(6));
                worker.setName(cursor.getString(7));
                worker.setAddress(cursor.getString(8));
                worker.setTel(cursor.getString(9));
                worker.setDate_start(cursor.getString(10));
                worker.setSetactive(Integer.parseInt(cursor.getString(11)));
                worker.setPrice_per_day(cursor.getInt(12));
                worker.setSalary(cursor.getInt(13));
                worker.setLast_update(cursor.getString(14));
                worker.setFlag(cursor.getInt(15));
            }
        }
        cursor.close();
        
        Log.d("db_get_worker",String.valueOf(worker_sql_id));
        return worker;
    }

    public int updateWorker(worker_class worker) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_SQL_WORKER_ID,worker.getSql_worker_id());
        values.put(KEY_SQL_CICLE_WORKER_ID,worker.getSql_cicle_id());
        values.put(KEY_SQL_BUILDING_WORKER_ID,worker.getSql_building_id());
        values.put(KEY_USER_ID,worker.getUser_id());
        values.put(KEY_WORKER_CICLE_ID,worker.getCicle_id());
        values.put(KEY_WORKER_BUILDING_ID,worker.getBuilding_id());
        values.put(KEY_WORKER_NAME,worker.getName());
        values.put(KEY_WORKER_ADDRESS, worker.getAddress());
        values.put(KEY_WORKER_PHONE, worker.getTel());
        values.put(KEY_WORKER_START_DATE, worker.getDate_start());
        values.put(KEY_WORKER_SETACTIVE,worker.getSetactive());
        values.put(KEY_WORKER_PRICE, worker.getPrice_per_day());
        values.put(KEY_WORKER_TOTAL_SALARY,worker.getSalary());
        values.put(KEY_WORKER_LAST_DATE,worker.getLast_update());
        values.put(KEY_FLAG,worker.getFlag());
        int affected_row=0;
        affected_row=db.update(TABLE_WORKER_INFO,values,KEY_WORKER_ID+"=?",new String[]{String.valueOf(worker.getWorker_id())});
        Log.d("db_worker_update_af_row", String.valueOf(affected_row));
        Log.d("db_worker_update_af_row", String.valueOf(affected_row));
        Log.d("db_worker_update_id",String.valueOf(worker.getWorker_id()));
        //cursor.close();
        
        return affected_row;

    }

    public int deleteWorker(worker_class worker){
        SQLiteDatabase db=getWritableDatabase();
        int affected_row=0;
        affected_row=db.delete(TABLE_WORKER_INFO, KEY_WORKER_ID + "=?", new String[]{String.valueOf(worker.getWorker_id())});
        
        Log.d("db_deleteworker", String.valueOf(affected_row));
        return affected_row;
    }

    public List<worker_class> get_all_active_worker(int cicle_id,int building_id) {

        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_WORKER_INFO+" WHERE "+KEY_WORKER_CICLE_ID+"=? AND "+KEY_WORKER_BUILDING_ID+"=? AND "+KEY_WORKER_SETACTIVE+"=? AND "+KEY_FLAG+"!=-1";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id),String.valueOf(1)});

        List<worker_class> worker_list=new ArrayList<>();
        if(cursor!=null){
            Log.d("worker_list_size",String.valueOf(worker_list.size()));
            if(cursor.moveToFirst()){
                Log.d("db_worker_list_size",String.valueOf(cursor.getCount()));
                do{
                    worker_class worker=new worker_class();
                    worker.setSql_worker_id(cursor.getInt(0));
                    worker.setSql_cicle_id(cursor.getInt(1));
                    worker.setSql_building_id(cursor.getInt(2));
                    worker.setUser_id(cursor.getInt(3));
                    worker.setWorker_id(cursor.getInt(4));
                    worker.setCicle_id(cursor.getInt(5));
                    worker.setBuilding_id(cursor.getInt(6));
                    worker.setName(cursor.getString(7));
                    worker.setAddress(cursor.getString(8));
                    worker.setTel(cursor.getString(9));
                    worker.setDate_start(cursor.getString(10));
                    worker.setSetactive(Integer.parseInt(cursor.getString(11)));
                    worker.setPrice_per_day(cursor.getInt(12));
                    worker.setSalary(cursor.getInt(13));
                    worker.setLast_update(cursor.getString(14));
                    worker.setFlag(cursor.getInt(15));
                    worker_list.add(worker);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        return  worker_list;

    }

    public List<worker_class> get_all_inactive_worker(int cicle_id,int building_id) {

        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_WORKER_INFO+" WHERE "+KEY_WORKER_CICLE_ID+"=? AND "+KEY_WORKER_BUILDING_ID+"=? AND "+KEY_WORKER_SETACTIVE+"=? AND "+KEY_FLAG+"!=-1";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id),String.valueOf(2)});
        List<worker_class> worker_list=new ArrayList<>();
        if(cursor!=null){
            Log.d("worker_list_size",String.valueOf(worker_list.size()));
            if(cursor.moveToFirst()){
                Log.d("db_worker_list_size",String.valueOf(cursor.getCount()));
                do{
                    worker_class worker=new worker_class();
                    worker.setSql_worker_id(cursor.getInt(0));
                    worker.setSql_cicle_id(cursor.getInt(1));
                    worker.setSql_building_id(cursor.getInt(2));
                    worker.setUser_id(cursor.getInt(3));
                    worker.setWorker_id(cursor.getInt(4));
                    worker.setCicle_id(cursor.getInt(5));
                    worker.setBuilding_id(cursor.getInt(6));
                    worker.setName(cursor.getString(7));
                    worker.setAddress(cursor.getString(8));
                    worker.setTel(cursor.getString(9));
                    worker.setDate_start(cursor.getString(10));
                    worker.setSetactive(Integer.parseInt(cursor.getString(11)));
                    worker.setPrice_per_day(cursor.getInt(12));
                    worker.setSalary(cursor.getInt(13));
                    worker.setLast_update(cursor.getString(14));
                    worker.setFlag(cursor.getInt(15));
                    worker_list.add(worker);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        return  worker_list;

    }

    public List<worker_class> get_all_worker(int cicle_id,int building_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_WORKER_INFO+" WHERE "+KEY_WORKER_CICLE_ID+"=? AND "+KEY_WORKER_BUILDING_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        List<worker_class> worker_list=new ArrayList<>();
        if(cursor!=null){
            Log.d("worker_list_size",String.valueOf(worker_list.size()));
            if(cursor.moveToFirst()){
                Log.d("db_worker_list_size", String.valueOf(cursor.getCount()));
                do{
                    worker_class worker=new worker_class();
                    worker.setSql_worker_id(cursor.getInt(0));
                    worker.setSql_cicle_id(cursor.getInt(1));
                    worker.setSql_building_id(cursor.getInt(2));
                    worker.setUser_id(cursor.getInt(3));
                    worker.setWorker_id(cursor.getInt(4));
                    worker.setCicle_id(cursor.getInt(5));
                    worker.setBuilding_id(cursor.getInt(6));
                    worker.setName(cursor.getString(7));
                    worker.setAddress(cursor.getString(8));
                    worker.setTel(cursor.getString(9));
                    worker.setDate_start(cursor.getString(10));
                    worker.setSetactive(Integer.parseInt(cursor.getString(11)));
                    worker.setPrice_per_day(cursor.getInt(12));
                    worker.setSalary(cursor.getInt(13));
                    worker.setLast_update(cursor.getString(14));
                    worker.setFlag(cursor.getInt(15));
                    worker_list.add(worker);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        return  worker_list;
    }

    public List<worker_class> get_all_worker_by_user_id(int user_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_WORKER_INFO+" WHERE "+KEY_USER_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(user_id)});
        List<worker_class> worker_list=new ArrayList<>();
        if(cursor!=null){
            Log.d("worker_list_size",String.valueOf(worker_list.size()));
            if(cursor.moveToFirst()){
                Log.d("db_worker_list_size", String.valueOf(cursor.getCount()));
                do{
                    worker_class worker=new worker_class();
                    worker.setSql_worker_id(cursor.getInt(0));
                    worker.setSql_cicle_id(cursor.getInt(1));
                    worker.setSql_building_id(cursor.getInt(2));
                    worker.setUser_id(cursor.getInt(3));
                    worker.setWorker_id(cursor.getInt(4));
                    worker.setCicle_id(cursor.getInt(5));
                    worker.setBuilding_id(cursor.getInt(6));
                    worker.setName(cursor.getString(7));
                    worker.setAddress(cursor.getString(8));
                    worker.setTel(cursor.getString(9));
                    worker.setDate_start(cursor.getString(10));
                    worker.setSetactive(Integer.parseInt(cursor.getString(11)));
                    worker.setPrice_per_day(cursor.getInt(12));
                    worker.setSalary(cursor.getInt(13));
                    worker.setLast_update(cursor.getString(14));
                    worker.setFlag(cursor.getInt(15));
                    worker_list.add(worker);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        Log.d("workerlist_by_user_id", String.valueOf(worker_list.size()));
        return  worker_list;
    }

    //Animal
    public void createAnimal(animal_class animal){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_SQL_ANIMAL_ID,animal.getSql_animal_id());
        values.put(KEY_SQL_CICLE_ANIMAL_ID,animal.getSql_cicle_id());
        values.put(KEY_SQL_BUILDING_ANIMAL_ID,animal.getSql_building_id());
        values.put(KEY_USER_ID,animal.getUser_id());
        values.put(KEY_ANIMAL_CICLE_ID,animal.getCicle_id());
        values.put(KEY_ANIMAL_BUILDING_ID,animal.getBuilding_id());
        values.put(KEY_ANIMAL_TYPE,animal.getType());
        values.put(KEY_ANIMAL_OTHER,animal.getOther());
        values.put(KEY_ANIMAL_QUANTITY, animal.getQuantity());
        values.put(KEY_ANIMAL_PRICE, animal.getPrice());
        values.put(KEY_FLAG,animal.getFlag());
        long row=0;
        row = db.insert(TABLE_ANIMAL_INFO,null,values);
        Log.d("create_animal_inserted",String.valueOf(row));
        
    }

    public animal_class getAnimal(int animal_id) {
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_ANIMAL_INFO+" WHERE "+KEY_ANIMAL_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(animal_id)});
        animal_class animal=new animal_class();
        if(cursor!=null){
            Log.d("db_animal_get",String.valueOf(cursor.getCount()));
            if(cursor.moveToFirst()){
                animal.setSql_animal_id(cursor.getInt(0));
                animal.setSql_cicle_id(cursor.getInt(1));
                animal.setSql_building_id(cursor.getInt(2));
                animal.setUser_id(cursor.getInt(3));
                animal.setAnimal_id(cursor.getInt(4));
                animal.setCicle_id(cursor.getInt(5));
                animal.setBuilding_id(cursor.getInt(6));
                animal.setType(cursor.getString(7));
                animal.setOther(cursor.getString(8));
                animal.setQuantity(cursor.getInt(9));
                animal.setPrice(cursor.getInt(10));
                animal.setFlag(cursor.getInt(11));
            }
        }
        cursor.close();
        
        return animal;
    }


    public animal_class getAnimalBYSQL(int sql_animal_id) {
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_ANIMAL_INFO+" WHERE "+KEY_SQL_ANIMAL_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(sql_animal_id)});
        animal_class animal=null;
        if(cursor!=null){
            Log.d("db_animal_get", String.valueOf(cursor.getCount()));
            if(cursor.moveToFirst()){
                animal=new animal_class();
                animal.setSql_animal_id(cursor.getInt(0));
                animal.setSql_cicle_id(cursor.getInt(1));
                animal.setSql_building_id(cursor.getInt(2));
                animal.setUser_id(cursor.getInt(3));
                animal.setAnimal_id(cursor.getInt(4));
                animal.setCicle_id(cursor.getInt(5));
                animal.setBuilding_id(cursor.getInt(6));
                animal.setType(cursor.getString(7));
                animal.setOther(cursor.getString(8));
                animal.setQuantity(cursor.getInt(9));
                animal.setPrice(cursor.getInt(10));
                animal.setFlag(cursor.getInt(11));
            }
        }
        cursor.close();
        
        return animal;
    }

    public int deletAnimal(animal_class animal){
        SQLiteDatabase db=getWritableDatabase();
        int affected_row=0;
        affected_row=db.delete(TABLE_ANIMAL_INFO, KEY_ANIMAL_ID + "=?", new String[]{String.valueOf(animal.getAnimal_id())});
        
        return affected_row;
    }

    public List<animal_class> getAllAnimal(int cicle_id,int building_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM " + TABLE_ANIMAL_INFO+" WHERE "+KEY_ANIMAL_CICLE_ID + "=? AND " + KEY_ANIMAL_BUILDING_ID+"=? AND "+KEY_FLAG+"!=-1";
        Cursor cursor = db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        List<animal_class> animal_list=new ArrayList<>();
        if(cursor!=null){
            Log.d("db_animal_list_size",String.valueOf(animal_list.size()));
            if(cursor.moveToFirst()){
                do{
                    animal_class animal=new animal_class();
                    animal.setSql_animal_id(cursor.getInt(0));
                    animal.setSql_cicle_id(cursor.getInt(1));
                    animal.setSql_building_id(cursor.getInt(2));
                    animal.setUser_id(cursor.getInt(3));
                    animal.setAnimal_id(cursor.getInt(4));
                    animal.setCicle_id(cursor.getInt(5));
                    animal.setBuilding_id(cursor.getInt(6));
                    animal.setType(cursor.getString(7));
                    animal.setOther(cursor.getString(8));
                    animal.setQuantity(cursor.getInt(9));
                    animal.setPrice(cursor.getInt(10));
                    animal.setFlag(cursor.getInt(11));
                    animal_list.add(animal);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        return  animal_list;
    }

    public List<animal_class> getAllAnimalByUser_id(int user_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM " + TABLE_ANIMAL_INFO+" WHERE "+KEY_USER_ID + "=?";
        Cursor cursor = db.rawQuery(sql,new String[]{String.valueOf(user_id)});
        List<animal_class> animal_list=new ArrayList<>();
        if(cursor!=null){
            Log.d("db_animal_list_size",String.valueOf(animal_list.size()));
            if(cursor.moveToFirst()){
                do{
                    animal_class animal=new animal_class();
                    animal.setSql_animal_id(cursor.getInt(0));
                    animal.setSql_cicle_id(cursor.getInt(1));
                    animal.setSql_building_id(cursor.getInt(2));
                    animal.setUser_id(cursor.getInt(3));
                    animal.setAnimal_id(cursor.getInt(4));
                    animal.setCicle_id(cursor.getInt(5));
                    animal.setBuilding_id(cursor.getInt(6));
                    animal.setType(cursor.getString(7));
                    animal.setOther(cursor.getString(8));
                    animal.setQuantity(cursor.getInt(9));
                    animal.setPrice(cursor.getInt(10));
                    animal.setFlag(cursor.getInt(11));
                    animal_list.add(animal);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        return  animal_list;
    }

    public int updateAnimal(animal_class animal){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put(KEY_SQL_ANIMAL_ID,animal.getSql_animal_id());
        values.put(KEY_SQL_CICLE_ANIMAL_ID,animal.getSql_cicle_id());
        values.put(KEY_SQL_BUILDING_ANIMAL_ID,animal.getSql_building_id());
        values.put(KEY_USER_ID,animal.getUser_id());
        values.put(KEY_ANIMAL_CICLE_ID,animal.getCicle_id());
        values.put(KEY_ANIMAL_BUILDING_ID,animal.getBuilding_id());
        values.put(KEY_ANIMAL_TYPE,animal.getType());
        values.put(KEY_ANIMAL_OTHER,animal.getOther());
        values.put(KEY_ANIMAL_QUANTITY, animal.getQuantity());
        values.put(KEY_ANIMAL_PRICE, animal.getPrice());
        values.put(KEY_FLAG,animal.getFlag());
        int count=db.update(TABLE_ANIMAL_INFO,values,KEY_ANIMAL_ID+"=?",new String[]{String.valueOf(animal.getAnimal_id())});
        
        Log.d("db_animal_update_count", String.valueOf(count));
        return count;
    }

    public int getAnimalCount(int cicle_id,int building_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_ANIMAL_INFO+" WHERE "+KEY_ANIMAL_CICLE_ID+"=? AND "+KEY_ANIMAL_BUILDING_ID+"=?";
        int count=0;
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        List<animal_class> animal_list=new ArrayList<>();
        if(cursor!=null) {
            Log.d("db_animal_list_size", String.valueOf(animal_list.size()));
            count=cursor.getCount();
        }
        cursor.close();
        
        return count;
    }



    //Settings
    public void insert_setting(setting_class setting) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_SETTING_NOTIFICATION,setting.getNotification());
        values.put(KEY_SETTING_COUNTRY,setting.getCountry());
        values.put(KEY_SETTING_MONEY, setting.getMoney_format());
        db.insert(TABLE_SETTING_INFO, null, values);
        Log.d("table_setting_insert", "inserted");
        //cursor.close();
        
    }

    public setting_class get_all_setting(){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_SETTING_INFO;
        setting_class setting=null;
        Cursor cursor=db.rawQuery(sql, null);
        if(cursor!=null){
            Log.d("setting_get_all",String.valueOf(cursor.getCount()));
            if(cursor.moveToFirst()){
                setting=new setting_class();
                Log.d("setting_all",String.valueOf(cursor.getCount()));
                setting.setNotification(cursor.getString(0));
                setting.setCountry(cursor.getString(1));
                setting.setMoney_format(cursor.getString(2));
            }
        }

        cursor.close();
        
        return setting;
    }

    public int delete_setting(setting_class setting){
        int affected_rows=0;
        SQLiteDatabase db=getWritableDatabase();
        if(setting!=null) {
            affected_rows = db.delete(TABLE_SETTING_INFO, KEY_SETTING_NOTIFICATION + "=? AND " + KEY_SETTING_COUNTRY + "=? AND " + KEY_SETTING_MONEY + "=?", new String[]{setting.getNotification(), setting.getCountry(), setting.getMoney_format()});
        }
        Log.d("setting_del_affected", String.valueOf(affected_rows));
        //cursor.close();
        
        return affected_rows;
    }

    public int countSetting(){
        int count=-2;
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_SETTING_INFO;
        Cursor cursor=db.rawQuery(sql, null);
        if(cursor!=null){
            count=cursor.getCount();
        }
        cursor.close();
        
        return count;
    }



    //Food
    public int createFood(food_class food){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_SQL_FOOD_ID,food.getSql_food_id());
        values.put(KEY_SQL_CICLE_FOOD_ID,food.getSql_cicle_id());
        values.put(KEY_SQL_BUILDING_FOOD_ID,food.getSql_building_id());
        values.put(KEY_USER_ID,food.getUser_id());
        values.put(KEY_FOOD_CICLE_ID,food.getCicle_id());
        values.put(KEY_FOOD_BUILDING_ID,food.getBuilding_id());
        values.put(KEY_FOOD_DATE,food.getDate());
        values.put(KEY_FOOD_DESIGNATION,food.getDesignation());
        values.put(KEY_FOOD_QUANTITY, food.getQuantity());
        values.put(KEY_FOOD_PRICE,food.getPrice());
        values.put(KEY_FLAG,food.getFlag());
        long row=0;
        row=db.insert(TABLE_FOOD_INFO,null,values);
        Log.d("create_food_inserted", String.valueOf(row));
        //cursor.close();
        
        return (int) row;
    }

    public food_class getFood(int food_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_FOOD_INFO+" WHERE "+KEY_FOOD_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(food_id)});
        food_class food=null;
        if(cursor!=null){
            Log.d("db_animal_get",String.valueOf(cursor.getCount()));
            if(cursor.moveToFirst()){
                food=new food_class();
                food.setSql_food_id(cursor.getInt(0));
                food.setSql_cicle_id(cursor.getInt(1));
                food.setSql_building_id(cursor.getInt(2));
                food.setUser_id(cursor.getInt(3));
                food.setFood_id(cursor.getInt(4));
                food.setCicle_id(cursor.getInt(5));
                food.setBuilding_id(cursor.getInt(6));
                food.setDate(cursor.getString(7));
                food.setDesignation(cursor.getString(8));
                food.setQuantity(cursor.getInt(9));
                food.setPrice(cursor.getInt(10));
                food.setFlag(cursor.getInt(11));
            }
        }
        cursor.close();
        
        return food;
    }

    public food_class getFoodBYSQL(int sql_food_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_FOOD_INFO+" WHERE "+KEY_SQL_FOOD_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(sql_food_id)});
        food_class food=null;
        if(cursor!=null){
            Log.d("db_animal_get",String.valueOf(cursor.getCount()));
            if(cursor.moveToFirst()){
                food=new food_class();
                food.setSql_food_id(cursor.getInt(0));
                food.setSql_cicle_id(cursor.getInt(1));
                food.setSql_building_id(cursor.getInt(2));
                food.setUser_id(cursor.getInt(3));
                food.setFood_id(cursor.getInt(4));
                food.setCicle_id(cursor.getInt(5));
                food.setBuilding_id(cursor.getInt(6));
                food.setDate(cursor.getString(7));
                food.setDesignation(cursor.getString(8));
                food.setQuantity(cursor.getInt(9));
                food.setPrice(cursor.getInt(10));
                food.setFlag(cursor.getInt(11));
            }
        }
        cursor.close();
        
        return food;
    }

    public int deleteFood(food_class food){
        SQLiteDatabase db=getWritableDatabase();
        int affected_row=0;
        affected_row=db.delete(TABLE_FOOD_INFO, KEY_FOOD_ID + "=?", new String[]{String.valueOf(food.getFood_id())});
        
        return affected_row;
    }

    public List<food_class> getAllFood(int cicle_id,int building_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_FOOD_INFO+" WHERE "+KEY_FOOD_CICLE_ID+"=? AND "+KEY_FOOD_BUILDING_ID+"=? AND "+KEY_FLAG+"!=-1";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        List<food_class> food_list=new ArrayList<>();
        if(cursor!=null){
            Log.d("db_food_list_size",String.valueOf(food_list.size()));
            if(cursor.moveToFirst()){
                do{
                    food_class food=new food_class();
                    food.setSql_food_id(cursor.getInt(0));
                    food.setSql_cicle_id(cursor.getInt(1));
                    food.setSql_building_id(cursor.getInt(2));
                    food.setUser_id(cursor.getInt(3));
                    food.setFood_id(cursor.getInt(4));
                    food.setCicle_id(cursor.getInt(5));
                    food.setBuilding_id(cursor.getInt(6));
                    food.setDate(cursor.getString(7));
                    food.setDesignation(cursor.getString(8));
                    food.setQuantity(cursor.getInt(9));
                    food.setPrice(cursor.getInt(10));
                    food.setFlag(cursor.getInt(11));
                    food_list.add(food);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        return  food_list;
    }

    public List<food_class> getAllFoodBYIDS(int cicle_id,int building_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_FOOD_INFO+" WHERE "+KEY_FOOD_CICLE_ID+"=? AND "+KEY_FOOD_BUILDING_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        List<food_class> food_list=new ArrayList<>();
        if(cursor!=null){
            Log.d("db_food_list_size",String.valueOf(food_list.size()));
            if(cursor.moveToFirst()){
                do{
                    food_class food=new food_class();
                    food.setSql_food_id(cursor.getInt(0));
                    food.setSql_cicle_id(cursor.getInt(1));
                    food.setSql_building_id(cursor.getInt(2));
                    food.setUser_id(cursor.getInt(3));
                    food.setFood_id(cursor.getInt(4));
                    food.setCicle_id(cursor.getInt(5));
                    food.setBuilding_id(cursor.getInt(6));
                    food.setDate(cursor.getString(7));
                    food.setDesignation(cursor.getString(8));
                    food.setQuantity(cursor.getInt(9));
                    food.setPrice(cursor.getInt(10));
                    food.setFlag(cursor.getInt(11));
                    food_list.add(food);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        return  food_list;
    }

    public List<food_class> getAllFoodBYUSER_ID(int user_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_FOOD_INFO+" WHERE "+KEY_USER_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(user_id)});
        List<food_class> food_list=new ArrayList<>();
        if(cursor!=null){
            Log.d("db_food_list_size",String.valueOf(food_list.size()));
            if(cursor.moveToFirst()){
                do{
                    food_class food=new food_class();
                    food.setSql_food_id(cursor.getInt(0));
                    food.setSql_cicle_id(cursor.getInt(1));
                    food.setSql_building_id(cursor.getInt(2));
                    food.setUser_id(cursor.getInt(3));
                    food.setFood_id(cursor.getInt(4));
                    food.setCicle_id(cursor.getInt(5));
                    food.setBuilding_id(cursor.getInt(6));
                    food.setDate(cursor.getString(7));
                    food.setDesignation(cursor.getString(8));
                    food.setQuantity(cursor.getInt(9));
                    food.setPrice(cursor.getInt(10));
                    food.setFlag(cursor.getInt(11));
                    food_list.add(food);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        return  food_list;
    }

    public int updateFood(food_class food){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_SQL_FOOD_ID,food.getSql_food_id());
        values.put(KEY_SQL_CICLE_FOOD_ID,food.getSql_cicle_id());
        values.put(KEY_SQL_BUILDING_FOOD_ID,food.getSql_building_id());
        values.put(KEY_USER_ID,food.getUser_id());
        values.put(KEY_FOOD_CICLE_ID,food.getCicle_id());
        values.put(KEY_FOOD_BUILDING_ID,food.getBuilding_id());
        values.put(KEY_FOOD_DATE,food.getDate());
        values.put(KEY_FOOD_DESIGNATION,food.getDesignation());
        values.put(KEY_FOOD_QUANTITY, food.getQuantity());
        values.put(KEY_FOOD_PRICE, food.getPrice());
        values.put(KEY_FLAG, food.getFlag());
        Log.d("db_update_food", "db_update_food");
        Log.d("db_update_food_id",String.valueOf(values.getAsString(KEY_FOOD_ID)));
        Log.d("db_update_food_sql",String.valueOf(values.getAsString(KEY_SQL_FOOD_ID)));
        Log.d("db_update_cicle_id",values.getAsString(KEY_FOOD_CICLE_ID));
        Log.d("db_update_build_id",values.getAsString(KEY_FOOD_BUILDING_ID));
        Log.d("db_update_build_sql", values.getAsString(KEY_SQL_BUILDING_FOOD_ID));
        Log.d("db_update_cicle_sql", values.getAsString(KEY_SQL_CICLE_FOOD_ID));
        Log.d("db_update_food_flag", values.getAsString(KEY_FLAG));
        int count=db.update(TABLE_FOOD_INFO, values, KEY_FOOD_ID + "=?", new String[]{String.valueOf(food.getFood_id())});
        
        Log.d("db_animal_update_count", String.valueOf(count));
        return count;
    }

    public int getFoodCount(int cicle_id,int building_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_FOOD_INFO+" WHERE "+KEY_FOOD_CICLE_ID+"=? AND "+KEY_FOOD_BUILDING_ID+"=?";
        int count=0;
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        List<food_class> food_list=new ArrayList<>();
        if(cursor!=null) {
            Log.d("db_food_list_size", String.valueOf(food_list.size()));
            count=cursor.getCount();
        }
        cursor.close();
        
        return count;
    }


    //temp
    public void createTemp(temp_ temp){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_SQL_TEMP_ID,temp.getSql_temp_id());
        values.put(KEY_SQL_TEMP_CICLE_ID,temp.getSql_cicle_id());
        values.put(KEY_SQL_TEMP_BUILDING_ID,temp.getSql_building_id());
        values.put(KEY_USER_ID, temp.getUser_id());
        values.put(KEY_TEMP_CICLE_ID, temp.getCicle_id());
        values.put(KEY_TEMP_BUILDING_ID, temp.getBuilding_id());
        values.put(KEY_TEMP_TEMP, temp.getTemp());
        values.put(KEY_TEMP_LUX, temp.getLux());
        values.put(KEY_TEMP_HUMIDITY, temp.getHumidity());
        values.put(KEY_TEMP_DATE, temp.getDate());
        values.put(KEY_FLAG,temp.getFlag());
        long row=0;
        row=db.insert(TABLE_TEMP_INFO,null,values);
        Log.d("create_temp_inserted", String.valueOf(row));
        //cursor.close();
        
    }

    public temp_ getTemp(int temp_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_TEMP_INFO+" WHERE "+KEY_TEMP_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(temp_id)});
        temp_ temp=new temp_();
        if(cursor!=null){
            Log.d("db_temp_get",String.valueOf(cursor.getCount()));
            if(cursor.moveToFirst()){
                temp.setSql_temp_id(cursor.getInt(0));
                temp.setSql_cicle_id(cursor.getInt(1));
                temp.setSql_building_id(cursor.getInt(2));
                temp.setUser_id(cursor.getInt(3));
                temp.setTemp_id(cursor.getInt(4));
                temp.setCicle_id(cursor.getInt(5));
                temp.setBuilding_id(cursor.getInt(6));
                temp.setTemp(cursor.getInt(7));
                temp.setLux(cursor.getInt(8));
                temp.setHumidity(cursor.getInt(9));
                temp.setDate(cursor.getString(10));
                temp.setFlag(cursor.getInt(11));
            }
        }
        cursor.close();
        
        return temp;
    }

    public temp_ getTempBYSQL(int temp_sql){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM " + TABLE_TEMP_INFO+" WHERE "+KEY_SQL_TEMP_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(temp_sql)});
        temp_ temp=null;
        if(cursor!=null){
            if(cursor.moveToFirst()){
                temp=new temp_();

                temp.setSql_temp_id(cursor.getInt(0));
                temp.setSql_cicle_id(cursor.getInt(1));
                temp.setSql_building_id(cursor.getInt(2));
                temp.setUser_id(cursor.getInt(3));
                temp.setTemp_id(cursor.getInt(4));
                temp.setCicle_id(cursor.getInt(5));
                temp.setBuilding_id(cursor.getInt(6));
                temp.setTemp(cursor.getInt(7));
                temp.setLux(cursor.getInt(8));
                temp.setHumidity(cursor.getInt(9));
                temp.setDate(cursor.getString(10));
                temp.setFlag(cursor.getInt(11));
                Log.d("get_tempSQL", temp.getDate());
            }
        }
        cursor.close();
        
        return temp;

    }

    public int deleteTemp(temp_ temp){
        SQLiteDatabase db=getWritableDatabase();
        int affected_row=0;
        affected_row=db.delete(TABLE_TEMP_INFO, KEY_TEMP_ID + "=?", new String[]{String.valueOf(temp.getTemp_id())});
        
        return affected_row;
    }

    public List<temp_> getAllTemp(int cicle_id,int building_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_TEMP_INFO + " WHERE " + KEY_TEMP_CICLE_ID + "=? AND " + KEY_TEMP_BUILDING_ID+"=? AND "+KEY_FLAG+"!=-1";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        List<temp_> temp_list=new ArrayList<>();
        if(cursor!=null){
            Log.d("db_temp_list_size",String.valueOf(temp_list.size()));
            if(cursor.moveToFirst()){
                do{
                    temp_ temp=new temp_();

                    temp.setSql_temp_id(cursor.getInt(0));
                    temp.setSql_cicle_id(cursor.getInt(1));
                    temp.setSql_building_id(cursor.getInt(2));
                    temp.setUser_id(cursor.getInt(3));
                    temp.setTemp_id(cursor.getInt(4));
                    temp.setCicle_id(cursor.getInt(5));
                    temp.setBuilding_id(cursor.getInt(6));
                    temp.setTemp(cursor.getInt(7));
                    temp.setLux(cursor.getInt(8));
                    temp.setHumidity(cursor.getInt(9));
                    temp.setDate(cursor.getString(10));
                    temp.setFlag(cursor.getInt(11));
                    temp_list.add(temp);
                    Log.d("get_all_temp_id",String.valueOf(cursor.getInt(4)));
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        return  temp_list;
    }

    public int updateTemp(temp_ temp){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_SQL_TEMP_ID,temp.getSql_temp_id());
        values.put(KEY_SQL_TEMP_CICLE_ID,temp.getSql_cicle_id());
        values.put(KEY_SQL_TEMP_BUILDING_ID,temp.getSql_building_id());
        values.put(KEY_USER_ID,temp.getUser_id());
        values.put(KEY_TEMP_CICLE_ID,temp.getCicle_id());
        values.put(KEY_TEMP_BUILDING_ID,temp.getBuilding_id());
        values.put(KEY_TEMP_TEMP,temp.getTemp());
        values.put(KEY_TEMP_LUX,temp.getLux());
        values.put(KEY_TEMP_HUMIDITY, temp.getHumidity());
        values.put(KEY_TEMP_DATE,temp.getDate());
        values.put(KEY_FLAG,temp.getFlag());
        int count=db.update(TABLE_TEMP_INFO, values, KEY_TEMP_ID + "=?", new String[]{String.valueOf(temp.getTemp_id())});
        
        Log.d("db_temp_update_count", String.valueOf(count));
        return count;
    }

    public List<temp_> get_all_temp_by_user_id(int user_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_TEMP_INFO+" WHERE "+KEY_USER_ID+"=?";
        Cursor cursor=db.rawQuery(sql, new String[]{String.valueOf(user_id)});
        List<temp_> temp_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    temp_ temp=new temp_();
                    temp.setSql_temp_id(cursor.getInt(0));
                    temp.setSql_cicle_id(cursor.getInt(1));
                    temp.setSql_building_id(cursor.getInt(2));
                    temp.setUser_id(cursor.getInt(3));
                    temp.setTemp_id(cursor.getInt(4));
                    temp.setCicle_id(cursor.getInt(5));
                    temp.setBuilding_id(cursor.getInt(6));
                    temp.setTemp(cursor.getInt(7));
                    temp.setLux(cursor.getInt(8));
                    temp.setHumidity(cursor.getInt(9));
                    temp.setDate(cursor.getString(10));
                    temp.setFlag(cursor.getInt(11));
                    temp_list.add(temp);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        Log.d("temp_user_id",String.valueOf(temp_list.size()));
        return  temp_list;
    }



    //Medical
    public void createMedical(medical_ medical){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_SQL_MEDICAL_ID,medical.getSql_medical_id());
        values.put(KEY_SQL_CICLE_MEDICAL_ID,medical.getSql_cicle_id());
        values.put(KEY_SQL_BUILDING_MEDICAL_ID,medical.getSql_building_id());
        values.put(KEY_USER_ID,medical.getUser_id());
        //medical_id
        values.put(KEY_MEDICAL_CICLE_ID,medical.getCicle_id());
        values.put(KEY_MEDICAL_BUILDING_ID,medical.getBuilding_id());
        values.put(KEY_MEDICAL_OPERATION_TYPE,medical.getOperation_type());
        values.put(KEY_MEDICAL_OPERATION_NAME,medical.getOperation_name());
        values.put(KEY_MEDICAL_PRODUCT,medical.getProduct());
        values.put(KEY_MEDICAL_DATE, medical.getDate());
        values.put(KEY_MEDICAL_COMMENT,medical.getComment());
        values.put(KEY_MEDICAL_PRICE, medical.getPrice());
        values.put(KEY_FLAG, medical.getFlag());
        db.insert(TABLE_MEDICAL_INFO, null, values);
        Log.d("Medical_insert", "inserted");
        //cursor.close();
        
    }

    public medical_ getMedical(int medical_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_MEDICAL_INFO+" WHERE "+KEY_MEDICAL_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(medical_id)});
        medical_ medical=new medical_();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                medical.setSql_medical_id(cursor.getInt(0));
                medical.setSql_cicle_id(cursor.getInt(1));
                medical.setSql_building_id(cursor.getInt(2));
                medical.setUser_id(cursor.getInt(3));
                medical.setMedical_id(cursor.getInt(4));
                medical.setCicle_id(cursor.getInt(5));
                medical.setBuilding_id(cursor.getInt(6));
                medical.setOperation_type(cursor.getString(7));
                medical.setOperation_name(cursor.getString(8));
                medical.setDate(cursor.getString(9));
                medical.setComment(cursor.getString(10));
                medical.setProduct(cursor.getString(11));
                medical.setPrice(cursor.getInt(12));
                medical.setFlag(cursor.getInt(13));
            }
        }
        cursor.close();
        
        return medical;

    }

    public medical_ getMedicalBySql(int medical_sql){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_MEDICAL_INFO+" WHERE "+KEY_SQL_MEDICAL_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(medical_sql)});
        medical_ medical=null;
        if(cursor!=null){
            if(cursor.moveToFirst()){
                medical=new medical_();
                medical.setSql_medical_id(cursor.getInt(0));
                medical.setSql_cicle_id(cursor.getInt(1));
                medical.setSql_building_id(cursor.getInt(2));
                medical.setUser_id(cursor.getInt(3));
                medical.setMedical_id(cursor.getInt(4));
                medical.setCicle_id(cursor.getInt(5));
                medical.setBuilding_id(cursor.getInt(6));
                medical.setOperation_type(cursor.getString(7));
                medical.setOperation_name(cursor.getString(8));
                medical.setDate(cursor.getString(9));
                medical.setComment(cursor.getString(10));
                medical.setProduct(cursor.getString(11));
                medical.setPrice(cursor.getInt(12));
                medical.setFlag(cursor.getInt(13));
            }
        }
        if(medical==null){
            Log.d("get_medical_sql", "it is null");
        } else {
            Log.d("get_medical_sql",String.valueOf(medical.getMedical_id()));
        }
        cursor.close();
        ;
        return medical;

    }

    public void deleteMedical(medical_ medical){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_MEDICAL_INFO, KEY_MEDICAL_ID + "=?", new String[]{String.valueOf(medical.getMedical_id())});
        Log.d("medical_del_status", "deleted");
        
    }

    public int updateMedical(medical_ medical){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_SQL_MEDICAL_ID,medical.getSql_medical_id());
        values.put(KEY_SQL_CICLE_MEDICAL_ID,medical.getSql_cicle_id());
        values.put(KEY_SQL_BUILDING_MEDICAL_ID,medical.getSql_building_id());
        values.put(KEY_USER_ID,medical.getUser_id());
        values.put(KEY_MEDICAL_CICLE_ID,medical.getCicle_id());
        values.put(KEY_MEDICAL_BUILDING_ID,medical.getBuilding_id());
        values.put(KEY_MEDICAL_OPERATION_TYPE,medical.getOperation_type());
        values.put(KEY_MEDICAL_OPERATION_NAME,medical.getOperation_name());
        values.put(KEY_MEDICAL_DATE, medical.getDate());
        values.put(KEY_MEDICAL_COMMENT,medical.getComment());
        values.put(KEY_MEDICAL_PRODUCT,medical.getProduct());
        values.put(KEY_MEDICAL_PRICE, medical.getPrice());
        values.put(KEY_FLAG, medical.getFlag());
        int count=db.update(TABLE_MEDICAL_INFO,values,KEY_MEDICAL_ID+"=?",new String[]{String.valueOf(medical.getMedical_id())});
        Log.d("medical_update", String.valueOf(count));
        Log.d("medical_update_type", medical.getOperation_type());
//        Log.d("medical_update_des", medical.getOperation_name());
        Log.d("medical_update_medid", String.valueOf(medical.getMedical_id()));

        //cursor.close();
        
        return count;
    }

    public List<medical_> get_all_medical(int cicle_id,int building_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_MEDICAL_INFO+" WHERE "+KEY_MEDICAL_CICLE_ID+"=? AND "+KEY_MEDICAL_BUILDING_ID+"=? AND "+KEY_FLAG+"!=-1";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        List<medical_> medical_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    medical_ medical=new medical_();
                    medical.setSql_medical_id(cursor.getInt(0));
                    medical.setSql_cicle_id(cursor.getInt(1));
                    medical.setSql_building_id(cursor.getInt(2));
                    medical.setUser_id(cursor.getInt(3));
                    medical.setMedical_id(cursor.getInt(4));
                    medical.setCicle_id(cursor.getInt(5));
                    medical.setBuilding_id(cursor.getInt(6));
                    medical.setOperation_type(cursor.getString(7));
                    medical.setOperation_name(cursor.getString(8));
                    medical.setDate(cursor.getString(9));
                    medical.setComment(cursor.getString(10));
                    medical.setProduct(cursor.getString(11));
                    medical.setPrice(cursor.getInt(12));
                    medical.setFlag(cursor.getInt(13));
                    medical_list.add(medical);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        Log.d("medical_list", String.valueOf(medical_list.size()));
        return  medical_list;
    }

    public List<medical_> get_all_medical_by_user_id(int user_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_MEDICAL_INFO+" WHERE "+KEY_USER_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(user_id)});
        List<medical_> medical_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    medical_ medical=new medical_();
                    medical.setSql_medical_id(cursor.getInt(0));
                    medical.setSql_cicle_id(cursor.getInt(1));
                    medical.setSql_building_id(cursor.getInt(2));
                    medical.setUser_id(cursor.getInt(3));
                    medical.setMedical_id(cursor.getInt(4));
                    medical.setCicle_id(cursor.getInt(5));
                    medical.setBuilding_id(cursor.getInt(6));
                    medical.setOperation_type(cursor.getString(7));
                    medical.setOperation_name(cursor.getString(8));
                    medical.setDate(cursor.getString(9));
                    medical.setComment(cursor.getString(10));
                    medical.setProduct(cursor.getString(11));
                    medical.setPrice(cursor.getInt(12));
                    medical.setFlag(cursor.getInt(13));
                    medical_list.add(medical);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        Log.d("medical_list", String.valueOf(medical_list.size()));
        return  medical_list;
    }



    //Con                                                                             //change
    public void createCon(consommation_ con){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_SQL_CON_ID,con.getSql_consommation_id());
        values.put(KEY_SQL_CICLE_CON_ID,con.getSql_cicle_id());
        values.put(KEY_SQL_BUILDING_CON_ID,con.getSql_building_id());
        values.put(KEY_USER_ID,con.getUser_id());
        values.put(KEY_CON_CICLE_ID,con.getCicle_id());
        values.put(KEY_CON_BUILDING_ID,con.getBuilding_id());
        values.put(KEY_CON_DESIGNATION,con.getDesignation());
        values.put(KEY_CON_FOOD_LIST,con.getFood_list());
        values.put(KEY_CON_QUANTITY, con.getQuantity());
        values.put(KEY_CON_NUMBER,con.getNumber());
        values.put(KEY_CON_PER,con.getPer());
        values.put(KEY_CON_DATE_START, con.getDate_start());
        values.put(KEY_CON_DATE_END, con.getDate_end());
        values.put(KEY_FLAG, con.getFlag());
        long row=db.insert(TABLE_CON_INFO, null, values);
        Log.d("con_insert", "inserted");
        Log.d("consume_insert", String.valueOf(row));
        //cursor.close();
        
    }

    public consommation_ getCon(int consommation_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_CON_INFO+" WHERE "+KEY_CON_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(consommation_id)});
        consommation_ con=null;
        if(cursor!=null){
            if(cursor.moveToFirst()){
                con=new consommation_();
                con.setSql_consommation_id(cursor.getInt(0));
                con.setSql_cicle_id(cursor.getInt(1));
                con.setSql_building_id(cursor.getInt(2));
                con.setUser_id(cursor.getInt(3));
                con.setConsommation_id(cursor.getInt(4));
                con.setCicle_id(cursor.getInt(5));
                con.setBuilding_id(cursor.getInt(6));
                con.setDesignation(cursor.getString(7));
                con.setFood_list(cursor.getString(8));
                con.setQuantity(cursor.getInt(9));
                con.setNumber(cursor.getInt(10));
                con.setPer(cursor.getString(11));
                con.setDate_start(cursor.getString(12));
                con.setDate_end(cursor.getString(13));
                con.setFlag(cursor.getInt(14));
            }
        }
        cursor.close();
        
        //Log.d("medical_get_medical", medical.getOperation_type());
        return con;
    }

    public consommation_ getConBySQL(int sql_consommation_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_CON_INFO+" WHERE "+KEY_SQL_CON_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(sql_consommation_id)});
        consommation_ con=null;
        if(cursor!=null){
            if(cursor.moveToFirst()){
                con=new consommation_();
                con.setSql_consommation_id(cursor.getInt(0));
                con.setSql_cicle_id(cursor.getInt(1));
                con.setSql_building_id(cursor.getInt(2));
                con.setUser_id(cursor.getInt(3));
                con.setConsommation_id(cursor.getInt(4));
                con.setCicle_id(cursor.getInt(5));
                con.setBuilding_id(cursor.getInt(6));
                con.setDesignation(cursor.getString(7));
                con.setFood_list(cursor.getString(8));
                con.setQuantity(cursor.getInt(9));
                con.setNumber(cursor.getInt(10));
                con.setPer(cursor.getString(11));
                con.setDate_start(cursor.getString(12));
                con.setDate_end(cursor.getString(13));
                con.setFlag(cursor.getInt(14));
            }
        }
        cursor.close();
        
        //Log.d("medical_get_medical", medical.getOperation_type());
        return con;
    }

    public void deleteCon(consommation_ con){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_CON_INFO, KEY_CON_ID + "=?", new String[]{String.valueOf(con.getConsommation_id())});
        Log.d("con_del_status", "deleted");
        
    }

    public int updateCon(consommation_ con){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_SQL_CON_ID,con.getSql_consommation_id());
        values.put(KEY_SQL_CICLE_CON_ID,con.getSql_cicle_id());
        values.put(KEY_SQL_BUILDING_CON_ID,con.getSql_building_id());
        values.put(KEY_USER_ID,con.getUser_id());
        values.put(KEY_CON_CICLE_ID,con.getCicle_id());
        values.put(KEY_CON_BUILDING_ID,con.getBuilding_id());
        values.put(KEY_CON_DESIGNATION,con.getDesignation());
        values.put(KEY_CON_FOOD_LIST,con.getFood_list());
        values.put(KEY_CON_QUANTITY, con.getQuantity());
        values.put(KEY_CON_NUMBER,con.getNumber());
        values.put(KEY_CON_PER,con.getPer());
        values.put(KEY_CON_DATE_START, con.getDate_start());
        values.put(KEY_CON_DATE_END, con.getDate_end());
        values.put(KEY_FLAG,con.getFlag());
        int count=db.update(TABLE_CON_INFO,values,KEY_CON_ID+"=?",new String[]{String.valueOf(con.getConsommation_id())});
        Log.d("medical_update", String.valueOf(count));
        //Log.d("medical_update_type", medical.getOperation_type());
        //Log.d("medical_update_des", medical.getOperation_name());
        //Log.d("medical_update_equidid", String.valueOf(medical.getMedical_id()));

        
        return count;
    }

    public List<consommation_> get_all_con(int cicle_id,int building_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_CON_INFO+" WHERE "+KEY_CON_CICLE_ID+"=? AND "+KEY_CON_BUILDING_ID+"=? AND "+KEY_FLAG+"!=1";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        List<consommation_> con_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()) {
                do{
                    consommation_ con = new consommation_();
                    con.setSql_consommation_id(cursor.getInt(0));
                    con.setSql_cicle_id(cursor.getInt(1));
                    con.setSql_building_id(cursor.getInt(2));
                    con.setUser_id(cursor.getInt(3));
                    con.setConsommation_id(cursor.getInt(4));
                    con.setCicle_id(cursor.getInt(5));
                    con.setBuilding_id(cursor.getInt(6));
                    con.setDesignation(cursor.getString(7));
                    con.setFood_list(cursor.getString(8));
                    con.setQuantity(cursor.getInt(9));
                    con.setNumber(cursor.getInt(10));
                    con.setPer(cursor.getString(11));
                    con.setDate_start(cursor.getString(12));
                    con.setDate_end(cursor.getString(13));
                    con.setFlag(cursor.getInt(14));
                    con_list.add(con);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        Log.d("con_list", String.valueOf(con_list.size()));
        return  con_list;
    }

    public List<consommation_> get_all_con_by_user_id(int user_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_CON_INFO+" WHERE "+KEY_USER_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(user_id)});
        List<consommation_> con_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    consommation_ con=new consommation_();
                    con.setSql_consommation_id(cursor.getInt(0));
                    con.setSql_cicle_id(cursor.getInt(1));
                    con.setSql_building_id(cursor.getInt(2));
                    con.setUser_id(cursor.getInt(3));
                    con.setConsommation_id(cursor.getInt(4));
                    con.setCicle_id(cursor.getInt(5));
                    con.setBuilding_id(cursor.getInt(6));
                    con.setDesignation(cursor.getString(7));
                    con.setFood_list(cursor.getString(8));
                    con.setQuantity(cursor.getInt(9));
                    con.setNumber(cursor.getInt(10));
                    con.setPer(cursor.getString(11));
                    con.setDate_start(cursor.getString(12));
                    con.setDate_end(cursor.getString(13));
                    con.setFlag(cursor.getInt(14));
                    con_list.add(con);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        Log.d("con_list", String.valueOf(con_list.size()));
        return  con_list;
    }





    //Death
    public void createDeath(death_ death){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_SQL_DEATH_ID,death.getSql_death_id());
        values.put(KEY_SQL_CICLE_DEATH_ID,death.getSql_cicle_id());
        values.put(KEY_SQL_BUILDING_DEATH_ID,death.getSql_building_id());
        values.put(KEY_USER_ID, death.getUser_id());
        values.put(KEY_DEATH_CICLE_ID, death.getCicle_id());
        values.put(KEY_DEATH_BUILDING_ID,death.getBuilding_id());
        values.put(KEY_DEATH_DATE,death.getDate());
        values.put(KEY_DEATH_NO,death.getDeath_no());
        values.put(KEY_FLAG, death.getFlag());
        db.insert(TABLE_DEATH_INFO, null, values);
        Log.d("equip_insert", "inserted");
        //cursor.close();
        
    }

    public death_ getDeath(int death_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_DEATH_INFO+" WHERE "+KEY_DEATH_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(death_id)});
        death_ death=new death_();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                death.setSql_death_id(cursor.getInt(0));
                death.setSql_cicle_id(cursor.getInt(1));
                death.setSql_building_id(cursor.getInt(2));
                death.setUser_id(cursor.getInt(3));
                death.setDeath_id(cursor.getInt(4));
                death.setCicle_id(cursor.getInt(5));
                death.setBuilding_id(cursor.getInt(6));
                death.setDate(cursor.getString(7));
                death.setDeath_no(cursor.getInt(8));
                death.setFlag(cursor.getInt(9));
            }
        }
        cursor.close();
        
        //Log.d("equipment_get_equipment", death.getType());
        return death;

    }

    public death_ getDeathBySql(int sql_death_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_DEATH_INFO + " WHERE " + KEY_SQL_DEATH_ID + "=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(sql_death_id)});
        death_ death=null;
        if(cursor!=null){
            if(cursor.moveToFirst()){
                death=new death_();
                death.setSql_death_id(cursor.getInt(0));
                death.setSql_cicle_id(cursor.getInt(1));
                death.setSql_building_id(cursor.getInt(2));
                death.setUser_id(cursor.getInt(3));
                death.setDeath_id(cursor.getInt(4));
                death.setCicle_id(cursor.getInt(5));
                death.setBuilding_id(cursor.getInt(6));
                death.setDate(cursor.getString(7));
                death.setDeath_no(cursor.getInt(8));
                death.setFlag(cursor.getInt(9));
            }
        }
        cursor.close();
        
        //Log.d("equipment_get_equipment", death.getType());
        return death;

    }

    public void deleteDeath(death_ death){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_DEATH_INFO, KEY_DEATH_ID + "=?", new String[]{String.valueOf(death.getDeath_id())});
        Log.d("death_del_status", "deleted");
        
    }

    public int updateDeath(death_ death){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_SQL_DEATH_ID,death.getSql_death_id());
        values.put(KEY_SQL_CICLE_DEATH_ID,death.getSql_cicle_id());
        values.put(KEY_SQL_BUILDING_DEATH_ID,death.getSql_building_id());
        values.put(KEY_USER_ID,death.getUser_id());
        values.put(KEY_DEATH_CICLE_ID, death.getCicle_id());
        values.put(KEY_DEATH_BUILDING_ID,death.getBuilding_id());
        values.put(KEY_DEATH_DATE,death.getDate());
        values.put(KEY_DEATH_NO,death.getDeath_no());
        values.put(KEY_FLAG, death.getFlag());
        int count=db.update(TABLE_DEATH_INFO, values, KEY_DEATH_ID + "=?", new String[]{String.valueOf(death.getDeath_id())});
        Log.d("death_update", String.valueOf(count));
        Log.d("death_update_type", death.getDate());
        Log.d("death_update_deathid",String.valueOf(death.getDeath_id()));

        
        return count;
    }

    public List<death_> get_all_death(int cicle_id,int building_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_DEATH_INFO+" WHERE "+KEY_DEATH_CICLE_ID+"=? AND "+KEY_DEATH_BUILDING_ID+"=? AND "+KEY_FLAG+"!=-1";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        List<death_> death_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    death_ death=new death_();
                    death.setSql_death_id(cursor.getInt(0));
                    death.setSql_cicle_id(cursor.getInt(1));
                    death.setSql_building_id(cursor.getInt(2));
                    death.setUser_id(cursor.getInt(3));
                    death.setDeath_id(cursor.getInt(4));
                    death.setCicle_id(cursor.getInt(5));
                    death.setBuilding_id(cursor.getInt(6));
                    death.setDate(cursor.getString(7));
                    death.setDeath_no(cursor.getInt(8));
                    death.setFlag(cursor.getInt(9));
                    death_list.add(death);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        Log.d("death_list", String.valueOf(death_list.size()));
        return  death_list;
    }

    public List<death_> get_all_death_by_user_id(int user_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_DEATH_INFO+" WHERE "+KEY_USER_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(user_id)});
        List<death_> death_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    death_ death=new death_();
                    death.setSql_death_id(cursor.getInt(0));
                    death.setSql_cicle_id(cursor.getInt(1));
                    death.setSql_building_id(cursor.getInt(2));
                    death.setUser_id(cursor.getInt(3));
                    death.setDeath_id(cursor.getInt(4));
                    death.setCicle_id(cursor.getInt(5));
                    death.setBuilding_id(cursor.getInt(6));
                    death.setDate(cursor.getString(7));
                    death.setDeath_no(cursor.getInt(8));
                    death.setFlag(cursor.getInt(9));
                    death_list.add(death);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        Log.d("death_list", String.valueOf(death_list.size()));
        return  death_list;
    }



    //NORMAL_EGG
    public void createNormalEgg(egg_n egg){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_SQL_NORMAL_EGG_ID,egg.getSql_normal_egg_id());
        values.put(KEY_SQL_NORMAL_EGG_CICLE_ID,egg.getSql_cicle_id());
        values.put(KEY_SQL_NORMAL_EGG_BUILDING_ID,egg.getSql_building_id());
        values.put(KEY_USER_ID,egg.getUser_id());
        values.put(KEY_NORMAL_EGG_CICLE_ID, egg.getCicle_id());
        values.put(KEY_NORMAL_EGG_BUILDING_ID,egg.getBuilding_id());
        values.put(KEY_NORMAL_EGG_DATE,egg.getDate());
        values.put(KEY_NORAML_EGG_NUMBER,egg.getNumber());
        values.put(KEY_NORAML_EGG_TYPE,egg.getType());
        values.put(KEY_FLAG, egg.getFlag());
        long inserted=db.insert(TABLE_NORMAL_EGG_INFO, null, values);
        Log.d("enormal_egg_insert", String.valueOf(egg.getEgg_id()) + ":::" + String.valueOf(inserted));
        
    }

    public egg_n getNormalEgg(int egg_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+ TABLE_NORMAL_EGG_INFO +" WHERE "+ KEY_NORMAL_EGG_ID +"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(egg_id)});
        egg_n egg=new egg_n();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                egg.setSql_normal_egg_id(cursor.getInt(0));
                egg.setSql_cicle_id(cursor.getInt(1));
                egg.setSql_building_id(cursor.getInt(2));
                egg.setUser_id(cursor.getInt(3));
                egg.setEgg_id(cursor.getInt(4));
                egg.setCicle_id(cursor.getInt(5));
                egg.setBuilding_id(cursor.getInt(6));
                egg.setDate(cursor.getString(7));
                egg.setNumber(cursor.getInt(8));
                egg.setType(cursor.getString(9));
                egg.setFlag(cursor.getInt(10));
            }
        }
        cursor.close();
        
        //Log.d("equipment_get_equipment", death.getType());
        return egg;

    }

    public egg_n getNormalEggBYSQL(int normalegg_sql){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_NORMAL_EGG_INFO+" WHERE "+KEY_SQL_NORMAL_EGG_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(normalegg_sql)});
        egg_n egg=null;
        if(cursor!= null) {
            if (cursor.moveToFirst()) {
                egg=new egg_n();
                egg.setSql_normal_egg_id(cursor.getInt(0));
                egg.setSql_cicle_id(cursor.getInt(1));
                egg.setSql_building_id(cursor.getInt(2));
                egg.setUser_id(cursor.getInt(3));
                egg.setEgg_id(cursor.getInt(4));
                egg.setCicle_id(cursor.getInt(5));
                egg.setBuilding_id(cursor.getInt(6));
                egg.setDate(cursor.getString(7));
                egg.setNumber(cursor.getInt(8));
                egg.setType(cursor.getString(9));
                egg.setFlag(cursor.getInt(10));
                Log.d("get_normaleggSQL", egg.getType());
            }
        }
        cursor.close();
        
        return egg;

    }

    public void deleteNormalEgg(egg_n egg){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_NORMAL_EGG_INFO, KEY_NORMAL_EGG_ID + "=?", new String[]{String.valueOf(egg.getEgg_id())});
        Log.d("egg_del_status", "deleted");
        
    }

    public int updateNormalEgg(egg_n egg){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_SQL_NORMAL_EGG_ID,egg.getSql_normal_egg_id());
        values.put(KEY_SQL_NORMAL_EGG_CICLE_ID,egg.getSql_cicle_id());
        values.put(KEY_SQL_NORMAL_EGG_BUILDING_ID,egg.getSql_building_id());
        values.put(KEY_USER_ID,egg.getUser_id());
        values.put(KEY_NORMAL_EGG_CICLE_ID, egg.getCicle_id());
        values.put(KEY_NORMAL_EGG_BUILDING_ID,egg.getBuilding_id());
        values.put(KEY_NORMAL_EGG_DATE,egg.getDate());
        values.put(KEY_NORAML_EGG_NUMBER,egg.getNumber());
        values.put(KEY_NORAML_EGG_TYPE,egg.getType());
        values.put(KEY_FLAG, egg.getFlag());
        int count=db.update(TABLE_NORMAL_EGG_INFO, values, KEY_NORMAL_EGG_ID + "=?", new String[]{String.valueOf(egg.getEgg_id())});
        Log.d("egg_update", String.valueOf(count));
        Log.d("egg_update_type", egg.getDate());
        Log.d("egg_update_eggid",String.valueOf(egg.getEgg_id()));

        
        return count;
    }

    public List<egg_n> get_all_normal_egg(int cicle_id,int building_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+ TABLE_NORMAL_EGG_INFO +" WHERE "+ KEY_NORMAL_EGG_CICLE_ID +"=? AND "+ KEY_NORMAL_EGG_BUILDING_ID +"=? AND "+KEY_FLAG+"!=-1";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        List<egg_n> egg_n_list =new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    egg_n egg=new egg_n();
                    egg.setSql_normal_egg_id(cursor.getInt(0));
                    egg.setSql_cicle_id(cursor.getInt(1));
                    egg.setSql_building_id(cursor.getInt(2));
                    egg.setUser_id(cursor.getInt(3));
                    egg.setEgg_id(cursor.getInt(4));
                    egg.setCicle_id(cursor.getInt(5));
                    egg.setBuilding_id(cursor.getInt(6));
                    egg.setDate(cursor.getString(7));
                    egg.setNumber(cursor.getInt(8));
                    egg.setType(cursor.getString(9));
                    egg.setFlag(cursor.getInt(10));
                    egg_n_list.add(egg);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        Log.d("egg_n_list", String.valueOf(egg_n_list.size()));
        return egg_n_list;
    }

    public List<egg_n> get_all_normal_egg_by_user_id(int user_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_NORMAL_EGG_INFO+" WHERE "+KEY_USER_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(user_id)});
        List<egg_n> egg_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    egg_n egg=new egg_n();
                    egg.setSql_normal_egg_id(cursor.getInt(0));
                    egg.setSql_cicle_id(cursor.getInt(1));
                    egg.setSql_building_id(cursor.getInt(2));
                    egg.setUser_id(cursor.getInt(3));
                    egg.setEgg_id(cursor.getInt(4));
                    egg.setCicle_id(cursor.getInt(5));
                    egg.setBuilding_id(cursor.getInt(6));
                    egg.setDate(cursor.getString(7));
                    egg.setNumber(cursor.getInt(8));
                    egg.setType(cursor.getString(9));
                    egg.setFlag(cursor.getInt(10));
                    egg_list.add(egg);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        Log.d("normalegg_user_id",String.valueOf(egg_list.size()));
        return  egg_list;
    }



    //BROKEN_EGG
    public void createBrokenEgg(egg_b egg){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_SQL_BROKEN_EGG_ID,egg.getSql_broken_egg_id());
        values.put(KEY_SQL_BROKEN_EGG_CICLE_ID,egg.getSql_cicle_id());
        values.put(KEY_SQL_BROKEN_EGG_BUILDING_ID,egg.getSql_building_id());
        values.put(KEY_USER_ID,egg.getUser_id());
        values.put(KEY_BROKEN_EGG_CICLE_ID, egg.getCicle_id());
        values.put(KEY_BROKEN_EGG_BUILDING_ID,egg.getBuilding_id());
        values.put(KEY_BROKEN_EGG_DATE,egg.getDate());
        values.put(KEY_BROKEN_EGG_NUMBER,egg.getNumber());
        values.put(KEY_BROKEN_EGG_TYPE,egg.getType());
        values.put(KEY_FLAG, egg.getFlag());
        long inserted=db.insert(TABLE_BROKEN_EGG_INFO, null, values);
        Log.d("enormal_egg_insert", String.valueOf(egg.getEgg_id())+":::"+String.valueOf(inserted));
        //cursor.clos/e();
        
    }

    public egg_b getBrokenEgg(int egg_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+ TABLE_BROKEN_EGG_INFO +" WHERE "+ KEY_BROKEN_EGG_ID +"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(egg_id)});
        egg_b egg=new egg_b();
        if(cursor!=null){
            if(cursor.moveToFirst()) {
                egg.setSql_broken_egg_id(cursor.getInt(0));
                egg.setSql_cicle_id(cursor.getInt(1));
                egg.setSql_building_id(cursor.getInt(2));
                egg.setUser_id(cursor.getInt(3));
                egg.setEgg_id(cursor.getInt(4));
                egg.setCicle_id(cursor.getInt(5));
                egg.setBuilding_id(cursor.getInt(6));
                egg.setDate(cursor.getString(7));
                egg.setNumber(cursor.getInt(8));
                egg.setType(cursor.getString(9));
                egg.setFlag(cursor.getInt(10));
            }
        }
        cursor.close();
        
        //Log.d("equipment_get_equipment", death.getType());
        return egg;

    }

    public egg_b getBrokenEggBYSQL(int brokenegg_sql){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM " + TABLE_BROKEN_EGG_INFO+" WHERE "+KEY_SQL_BROKEN_EGG_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(brokenegg_sql)});
        egg_b egg=null;
        if(cursor != null) {
            if (cursor.moveToFirst()) {
                egg=new egg_b();
                egg.setSql_broken_egg_id(cursor.getInt(0));
                egg.setSql_cicle_id(cursor.getInt(1));
                egg.setSql_building_id(cursor.getInt(2));
                egg.setUser_id(cursor.getInt(3));
                egg.setEgg_id(cursor.getInt(4));
                egg.setCicle_id(cursor.getInt(5));
                egg.setBuilding_id(cursor.getInt(6));
                egg.setDate(cursor.getString(7));
                egg.setNumber(cursor.getInt(8));
                egg.setType(cursor.getString(9));
                egg.setFlag(cursor.getInt(10));
                Log.d("get_brokeneggSQL", egg.getType());
            }
        }
        cursor.close();
        
        return egg;

    }

    public void deleteBrokenEgg(egg_b egg){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_BROKEN_EGG_INFO, KEY_BROKEN_EGG_ID + "=?", new String[]{String.valueOf(egg.getEgg_id())});
        Log.d("egg_del_status", "deleted");
        
    }

    public int updateBrokenEgg(egg_b egg) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_SQL_BROKEN_EGG_ID,egg.getSql_broken_egg_id());
        values.put(KEY_SQL_BROKEN_EGG_CICLE_ID,egg.getSql_cicle_id());
        values.put(KEY_SQL_BROKEN_EGG_BUILDING_ID,egg.getSql_building_id());
        values.put(KEY_USER_ID,egg.getUser_id());
        values.put(KEY_BROKEN_EGG_CICLE_ID, egg.getCicle_id());
        values.put(KEY_BROKEN_EGG_BUILDING_ID,egg.getBuilding_id());
        values.put(KEY_BROKEN_EGG_DATE,egg.getDate());
        values.put(KEY_BROKEN_EGG_NUMBER,egg.getNumber());
        values.put(KEY_BROKEN_EGG_TYPE,egg.getType());
        values.put(KEY_FLAG, egg.getFlag());
        int count=db.update(TABLE_BROKEN_EGG_INFO, values, KEY_BROKEN_EGG_ID + "=?", new String[]{String.valueOf(egg.getEgg_id())});
        Log.d("egg_update", String.valueOf(count));
        Log.d("egg_update_type", egg.getDate());
        Log.d("egg_update_eggid",String.valueOf(egg.getEgg_id()));

        
        return count;
    }

    public List<egg_b> get_all_broken_egg(int cicle_id,int building_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+ TABLE_BROKEN_EGG_INFO +" WHERE "+ KEY_BROKEN_EGG_CICLE_ID +"=? AND "+ KEY_BROKEN_EGG_BUILDING_ID +"=? AND "+KEY_FLAG+"!=-1";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        List<egg_b> egg_b_list =new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    egg_b egg=new egg_b();
                    egg.setSql_broken_egg_id(cursor.getInt(0));
                    egg.setSql_cicle_id(cursor.getInt(1));
                    egg.setSql_building_id(cursor.getInt(2));
                    egg.setUser_id(cursor.getInt(3));
                    egg.setEgg_id(cursor.getInt(4));
                    egg.setCicle_id(cursor.getInt(5));
                    egg.setBuilding_id(cursor.getInt(6));
                    egg.setDate(cursor.getString(7));
                    egg.setNumber(cursor.getInt(8));
                    egg.setType(cursor.getString(9));
                    egg.setFlag(cursor.getInt(10));
                    egg_b_list.add(egg);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        Log.d("egg_b_list", String.valueOf(egg_b_list.size()));
        return egg_b_list;
    }

    public List<egg_b> get_all_broken_egg_by_user_id(int user_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_BROKEN_EGG_INFO+" WHERE "+KEY_USER_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(user_id)});
        List<egg_b> egg_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    egg_b egg=new egg_b();
                    egg.setSql_broken_egg_id(cursor.getInt(0));
                    egg.setSql_cicle_id(cursor.getInt(1));
                    egg.setSql_building_id(cursor.getInt(2));
                    egg.setUser_id(cursor.getInt(3));
                    egg.setEgg_id(cursor.getInt(4));
                    egg.setCicle_id(cursor.getInt(5));
                    egg.setBuilding_id(cursor.getInt(6));
                    egg.setDate(cursor.getString(7));
                    egg.setNumber(cursor.getInt(8));
                    egg.setType(cursor.getString(9));
                    egg.setFlag(cursor.getInt(10));
                    egg_list.add(egg);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        Log.d("brokenegg_user_id",String.valueOf(egg_list.size()));
        return  egg_list;
    }

    //Alive
    public int count_animals(int cicle_id, int building_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT "+KEY_ANIMAL_QUANTITY+" FROM "+TABLE_ANIMAL_INFO+" WHERE "+KEY_ANIMAL_CICLE_ID+"=? AND "+KEY_ANIMAL_BUILDING_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        int count = 0;
        if(cursor!=null){
            if(cursor.moveToFirst())
                count = cursor.getInt(0);
        }
        Log.d("count of animal", String.valueOf(count));
        cursor.close();
        
        return count;
    }

    public int count_deaths(int cicle_id, int building_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_DEATH_INFO+" WHERE "+KEY_DEATH_CICLE_ID+"=? AND "+KEY_DEATH_BUILDING_ID+"=? AND "+KEY_FLAG+"!=-1";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        int count = 0;
        if(cursor!=null){
            while(cursor.moveToNext()){
                count += cursor.getInt(8);
            }
        }
        cursor.close();
        
        return count;
    }

    //finance

    public all_object_list get_all_objects(int cicle_id,int building_id){
        List<equipment_> equipment_list=this.get_all_equipment(cicle_id, building_id);
        List<animal_class> animal_list=this.getAllAnimal(cicle_id, building_id);
        List<worker_class> worker_list=this.get_all_worker(cicle_id, building_id);
        List<food_class> food_list=this.getAllFood(cicle_id, building_id);
        List<medical_> medical_list=this.get_all_medical(cicle_id, building_id);
        List<additional_expense_class>additional_expense_List=this.get_all_additional_expense(cicle_id, building_id);
        List<down_payment_class>down_payment_classLog=this.get_all_down_payment(cicle_id,building_id);
        List<returned_expense_class>returned_list=this.get_all_returned_expense(cicle_id, building_id);
        all_object_list all_object_list=new all_object_list();
        all_object_list.setEquipment_list(equipment_list);
        all_object_list.setAnimal_list(animal_list);
        all_object_list.setWorker_list(worker_list);
        all_object_list.setFood_list(food_list);
        all_object_list.setAdditional_expense_List(additional_expense_List);
        all_object_list.setMedical_list(medical_list);
        all_object_list.setReturned_expenses(returned_list);
        all_object_list.setDown_payment_list(down_payment_classLog);

        return all_object_list;
    }

    //downpayment

    public long createDownPayment(down_payment_class down_payment){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_SQL_DOWN_PAYMENT_ID,down_payment.getSql_down_payment_id());
        values.put(KEY_SQL_CICLE_DOWN_PAYMENT_ID,down_payment.getSql_cicle_id());
        values.put(KEY_SQL_BUILDING_DOWN_PAYMENT_ID,down_payment.getSql_building_id());
        values.put(KEY_USER_ID,down_payment.getUser_id());
        values.put(KEY_DOWN_PAYMENT_CICLE_ID,down_payment.getCicle_id());
        values.put(KEY_DOWN_PAYMENT_BUILDING_ID,down_payment.getBuilding_id());
        values.put(KEY_DOWN_PAYMENT_WORKER_NAME, down_payment.getWorker_name());
        values.put(KEY_DOWN_PAYMENT_DATE, down_payment.getDate());
        values.put(KEY_DOWN_PAYMENT_PRICE, down_payment.getPrice());
        values.put(KEY_FLAG,down_payment.getFlag());
        long row=-1;
        row=db.insert(TABLE_DOWN_PAYMENT_INFO,null,values);
        //cursor.close();
        
        return row;
    }

    public down_payment_class getDownPayment(int down_payment_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_DOWN_PAYMENT_INFO+" WHERE "+KEY_DOWN_PAYMENT_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(down_payment_id)});
        down_payment_class down_payment=new down_payment_class();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                down_payment.setSql_down_payment_id(cursor.getInt(0));
                down_payment.setSql_cicle_id(cursor.getInt(1));
                down_payment.setSql_building_id(cursor.getInt(2));
                down_payment.setUser_id(cursor.getInt(3));
                down_payment.setDown_payment_id(cursor.getInt(4));
                down_payment.setCicle_id(cursor.getInt(5));
                down_payment.setBuilding_id(cursor.getInt(6));
                down_payment.setWorker_name(cursor.getString(7));
                down_payment.setDate(cursor.getString(8));
                down_payment.setPrice(cursor.getInt(9));
                down_payment.setFlag(cursor.getInt(10));
            }
        }
        cursor.close();
        
        Log.d("get_down_pay", down_payment.getWorker_name());
        return down_payment;

    }

    public down_payment_class getDownPaymentBySql(int sql_down_payment_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_DOWN_PAYMENT_INFO+" WHERE "+KEY_SQL_DOWN_PAYMENT_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(sql_down_payment_id)});
        down_payment_class down_payment=null;
        if(cursor!=null){
            if(cursor.moveToFirst()){
                down_payment=new down_payment_class();
                down_payment.setSql_down_payment_id(cursor.getInt(0));
                down_payment.setSql_cicle_id(cursor.getInt(1));
                down_payment.setSql_building_id(cursor.getInt(2));
                down_payment.setUser_id(cursor.getInt(3));
                down_payment.setDown_payment_id(cursor.getInt(4));
                down_payment.setCicle_id(cursor.getInt(5));
                down_payment.setBuilding_id(cursor.getInt(6));
                down_payment.setWorker_name(cursor.getString(7));
                down_payment.setDate(cursor.getString(8));
                down_payment.setPrice(cursor.getInt(9));
                down_payment.setFlag(cursor.getInt(10));
            }
        }
        cursor.close();
        
        //   Log.d("get_down_pay", down_payment.getWorker_name());
        return down_payment;

    }

    public void deleteDownPayment(down_payment_class down_payment){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_DOWN_PAYMENT_INFO, KEY_DOWN_PAYMENT_ID + "=?", new String[]{String.valueOf(down_payment.getDown_payment_id())});
        Log.d("dp_del_status", "deleted");
        
    }

    public int updateDownPayment(down_payment_class down_payment){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SQL_DOWN_PAYMENT_ID,down_payment.getSql_down_payment_id());
        values.put(KEY_SQL_CICLE_DOWN_PAYMENT_ID,down_payment.getSql_cicle_id());
        values.put(KEY_SQL_BUILDING_DOWN_PAYMENT_ID,down_payment.getSql_building_id());
        values.put(KEY_USER_ID,down_payment.getUser_id());
        values.put(KEY_DOWN_PAYMENT_CICLE_ID,down_payment.getCicle_id());
        values.put(KEY_DOWN_PAYMENT_BUILDING_ID,down_payment.getBuilding_id());
        values.put(KEY_DOWN_PAYMENT_WORKER_NAME, down_payment.getWorker_name());
        values.put(KEY_DOWN_PAYMENT_DATE, down_payment.getDate());
        values.put(KEY_DOWN_PAYMENT_PRICE, down_payment.getPrice());
        values.put(KEY_FLAG,down_payment.getFlag());
        Log.d("dp_update_flag",String.valueOf(down_payment.getFlag()));
        int count=db.update(TABLE_DOWN_PAYMENT_INFO,values,KEY_DOWN_PAYMENT_ID+"=?",new String[]{String.valueOf(down_payment.getDown_payment_id())});
        Log.d("dp_update", String.valueOf(count));
        
        return count;
    }

    public List<down_payment_class> get_all_down_payment(int cicle_id,int building_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_DOWN_PAYMENT_INFO+" WHERE "+KEY_DOWN_PAYMENT_CICLE_ID+"=? AND "+KEY_DOWN_PAYMENT_BUILDING_ID+"=? AND "+KEY_FLAG+"!=-1" ;
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        List<down_payment_class> down_payment_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    down_payment_class down_payment=new down_payment_class();
                    down_payment.setSql_down_payment_id(cursor.getInt(0));
                    down_payment.setSql_cicle_id(cursor.getInt(1));
                    down_payment.setSql_building_id(cursor.getInt(2));
                    down_payment.setUser_id(cursor.getInt(3));
                    down_payment.setDown_payment_id(cursor.getInt(4));
                    down_payment.setCicle_id(cursor.getInt(5));
                    down_payment.setBuilding_id(cursor.getInt(6));
                    down_payment.setWorker_name(cursor.getString(7));
                    down_payment.setDate(cursor.getString(8));
                    down_payment.setPrice(cursor.getInt(9));

                    down_payment.setFlag(cursor.getInt(10));
                    down_payment_list.add(down_payment);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        Log.d("down_payment_list", String.valueOf(down_payment_list.size()));
        return  down_payment_list;
    }

    public List<down_payment_class> get_all_down_payment_by_user_id(int user_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_DOWN_PAYMENT_INFO+" WHERE "+KEY_USER_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(user_id)});
        List<down_payment_class> down_payment_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    down_payment_class down_payment=new down_payment_class();
                    down_payment.setSql_down_payment_id(cursor.getInt(0));
                    down_payment.setSql_cicle_id(cursor.getInt(1));
                    down_payment.setSql_building_id(cursor.getInt(2));
                    down_payment.setUser_id(cursor.getInt(3));
                    down_payment.setDown_payment_id(cursor.getInt(4));
                    down_payment.setCicle_id(cursor.getInt(5));
                    down_payment.setBuilding_id(cursor.getInt(6));
                    down_payment.setWorker_name(cursor.getString(7));
                    down_payment.setDate(cursor.getString(8));
                    down_payment.setPrice(cursor.getInt(9));
                    down_payment.setFlag(cursor.getInt(10));
                    down_payment_list.add(down_payment);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        Log.d("down_payment_list", String.valueOf(down_payment_list.size()));
        return  down_payment_list;
    }

    //additional Expense

    public long createAdditionalExpense(additional_expense_class additional_expense){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_SQL_ADDITIONAL_EXPENSE_ID,additional_expense.getSql_additional_expense_id());
        values.put(KEY_SQL_CICLE_ADDITIONAL_EXPENSE_ID,additional_expense.getSql_cicle_id());
        values.put(KEY_SQL_BUILDING_ADDITIONAL_EXPENSE_ID,additional_expense.getSql_building_id());
        values.put(KEY_USER_ID,additional_expense.getUser_id());
        values.put(KEY_ADDITIONAL_EXPENSE_CICLE_ID,additional_expense.getCicle_id());
        values.put(KEY_ADDITIONAL_EXPENSE_BUILDING_ID,additional_expense.getBuilding_id());
        values.put(KEY_ADDITIONAL_EXPENSE_TYPE,additional_expense.getType());
        values.put(KEY_ADDITIONAL_EXPENSE_DESIGNATION,additional_expense.getDesignation());
        values.put(KEY_ADDITIONAL_EXPENSE_DATE,additional_expense.getDate());
        values.put(KEY_ADDITIONAL_EXPENSE_QUANTITY,additional_expense.getQuantity());
        values.put(KEY_ADDITIONAL_EXPENSE_PRICE, additional_expense.getPrice_per_one());
        values.put(KEY_FLAG, additional_expense.getFlag());
        long row=db.insert(TABLE_ADDITIONAL_EXPENSE_INFO,null,values);
        Log.d("add_expense_row", String.valueOf(row));
        //rsor.close();
        
        return  row;
    }

    public additional_expense_class getAdditionalExpense(int additional_expense_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_ADDITIONAL_EXPENSE_INFO+" WHERE "+KEY_ADDITIONAL_EXPENSE_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(additional_expense_id)});
        additional_expense_class additional_expense=new additional_expense_class();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                additional_expense.setSql_additional_expense_id(cursor.getInt(0));
                additional_expense.setSql_cicle_id(cursor.getInt(1));
                additional_expense.setSql_building_id(cursor.getInt(2));
                additional_expense.setUser_id(cursor.getInt(3));
                additional_expense.setAdditional_expense_id(cursor.getInt(4));
                additional_expense.setCicle_id(cursor.getInt(5));
                additional_expense.setBuilding_id(cursor.getInt(6));
                additional_expense.setType(cursor.getString(7));
                additional_expense.setDesignation(cursor.getString(8));
                additional_expense.setDate(cursor.getString(9));
                additional_expense.setQuantity(cursor.getInt(10));
                additional_expense.setPrice_per_one(cursor.getInt(11));
                additional_expense.setFlag(cursor.getInt(12));
            }
        }
        cursor.close();
        
//        Log.d("get_addtional_exp", additional_expense.getType());
        return additional_expense;

    }

    public additional_expense_class getAdditionalExpenseBySql(int sql_additional_expense_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_ADDITIONAL_EXPENSE_INFO+" WHERE "+KEY_SQL_ADDITIONAL_EXPENSE_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(sql_additional_expense_id)});
        additional_expense_class additional_expense=null;
        if(cursor!=null){
            if(cursor.moveToFirst()){
                additional_expense=new additional_expense_class();
                additional_expense.setSql_additional_expense_id(cursor.getInt(0));
                additional_expense.setSql_cicle_id(cursor.getInt(1));
                additional_expense.setSql_building_id(cursor.getInt(2));
                additional_expense.setUser_id(cursor.getInt(3));
                additional_expense.setAdditional_expense_id(cursor.getInt(4));
                additional_expense.setCicle_id(cursor.getInt(5));
                additional_expense.setBuilding_id(cursor.getInt(6));
                additional_expense.setType(cursor.getString(7));
                additional_expense.setDesignation(cursor.getString(8));
                additional_expense.setDate(cursor.getString(9));
                additional_expense.setQuantity(cursor.getInt(10));
                additional_expense.setPrice_per_one(cursor.getInt(11));
                additional_expense.setFlag(cursor.getInt(12));
            }
        }
        cursor.close();
        
        //  Log.d("get_addtional_exp", additional_expense.getType());
        return additional_expense;

    }

    public void deleteAdditionalExpense(additional_expense_class additional_expense){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_ADDITIONAL_EXPENSE_INFO, KEY_ADDITIONAL_EXPENSE_ID + "=?", new String[]{String.valueOf(additional_expense.getAdditional_expense_id())});
        Log.d("ae_del_status", "deleted");
        
    }

    public int countAdditionalExpense(int cicle_id,int building_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_ADDITIONAL_EXPENSE_INFO+" WHERE "+KEY_ADDITIONAL_EXPENSE_CICLE_ID+"=? AND "+KEY_ADDITIONAL_EXPENSE_BUILDING_ID+"=?";
        Cursor cursor=db.rawQuery(sql, new String[]{String.valueOf(cicle_id), String.valueOf(building_id)});
        int count=-1;
        if(cursor!=null){
            count=cursor.getCount();
        }
        cursor.close();
        
        return count;
    }

    public int updateAdditionalExpense(additional_expense_class additional_expense){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SQL_ADDITIONAL_EXPENSE_ID,additional_expense.getSql_additional_expense_id());
        values.put(KEY_SQL_CICLE_ADDITIONAL_EXPENSE_ID,additional_expense.getSql_cicle_id());
        values.put(KEY_SQL_BUILDING_ADDITIONAL_EXPENSE_ID,additional_expense.getSql_building_id());
        values.put(KEY_USER_ID,additional_expense.getUser_id());
        values.put(KEY_ADDITIONAL_EXPENSE_CICLE_ID,additional_expense.getCicle_id());
        values.put(KEY_ADDITIONAL_EXPENSE_BUILDING_ID,additional_expense.getBuilding_id());
        values.put(KEY_ADDITIONAL_EXPENSE_TYPE,additional_expense.getType());
        values.put(KEY_ADDITIONAL_EXPENSE_DESIGNATION,additional_expense.getDesignation());
        values.put(KEY_ADDITIONAL_EXPENSE_DATE,additional_expense.getDate());
        values.put(KEY_ADDITIONAL_EXPENSE_QUANTITY,additional_expense.getQuantity());
        values.put(KEY_ADDITIONAL_EXPENSE_PRICE, additional_expense.getPrice_per_one());
        values.put(KEY_FLAG,additional_expense.getFlag());
        int count=db.update(TABLE_ADDITIONAL_EXPENSE_INFO,values,KEY_ADDITIONAL_EXPENSE_ID+"=?",new String[]{String.valueOf(additional_expense.getAdditional_expense_id())});
        Log.d("dp_update", String.valueOf(count));
        
        return count;
    }

    public List<additional_expense_class> get_all_additional_expense(int cicle_id,int building_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_ADDITIONAL_EXPENSE_INFO+" WHERE "+KEY_ADDITIONAL_EXPENSE_CICLE_ID+"=? AND "+KEY_ADDITIONAL_EXPENSE_BUILDING_ID+"=? AND "+KEY_FLAG+"!=-1";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        List<additional_expense_class> additional_expense_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    additional_expense_class additional_expense=new additional_expense_class();
                    additional_expense.setSql_additional_expense_id(cursor.getInt(0));
                    additional_expense.setSql_cicle_id(cursor.getInt(1));
                    additional_expense.setSql_building_id(cursor.getInt(2));
                    additional_expense.setUser_id(cursor.getInt(3));
                    additional_expense.setAdditional_expense_id(cursor.getInt(4));
                    additional_expense.setCicle_id(cursor.getInt(5));
                    additional_expense.setBuilding_id(cursor.getInt(6));
                    additional_expense.setType(cursor.getString(7));
                    additional_expense.setDesignation(cursor.getString(8));
                    additional_expense.setDate(cursor.getString(9));
                    additional_expense.setQuantity(cursor.getInt(10));
                    additional_expense.setPrice_per_one(cursor.getInt(11));
                    additional_expense.setFlag(cursor.getInt(12));
                    additional_expense_list.add(additional_expense);
                    Log.d(cursor.getString(0) + "ALL_ADD_EXP:", cursor.getString(1) + "-" + cursor.getString(2) + "-" + cursor.getString(3) + "-" + cursor.getString(4) + "-" + cursor.getString(5) + "-" + cursor.getString(6) + "-" + cursor.getString(7));

                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        Log.d("additional_expense_list", String.valueOf(additional_expense_list.size()));
        return additional_expense_list;
    }

    public List<additional_expense_class> get_all_additional_expense_by_user_id(int user_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_ADDITIONAL_EXPENSE_INFO+" WHERE "+KEY_USER_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(user_id)});
        List<additional_expense_class> additional_expense_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    additional_expense_class additional_expense=new additional_expense_class();
                    additional_expense.setSql_additional_expense_id(cursor.getInt(0));
                    additional_expense.setSql_cicle_id(cursor.getInt(1));
                    additional_expense.setSql_building_id(cursor.getInt(2));
                    additional_expense.setUser_id(cursor.getInt(3));
                    additional_expense.setAdditional_expense_id(cursor.getInt(4));
                    additional_expense.setCicle_id(cursor.getInt(5));
                    additional_expense.setBuilding_id(cursor.getInt(6));
                    additional_expense.setType(cursor.getString(7));
                    additional_expense.setDesignation(cursor.getString(8));
                    additional_expense.setDate(cursor.getString(9));
                    additional_expense.setQuantity(cursor.getInt(10));
                    additional_expense.setPrice_per_one(cursor.getInt(11));
                    additional_expense.setFlag(cursor.getInt(12));
                    additional_expense_list.add(additional_expense);
                    Log.d(cursor.getString(0)+"ALL_ADD_EXP:",cursor.getString(1)+"-"+cursor.getString(2)+"-"+cursor.getString(3)+"-"+cursor.getString(4)+"-"+cursor.getString(5)+"-"+cursor.getString(6)+"-"+cursor.getString(7));
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        Log.d("additional_expense_list", String.valueOf(additional_expense_list.size()));
        return additional_expense_list;
    }

    //returned expense

    public long createReturnedExpense(returned_expense_class returned_expense){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_SQL_RETURNED_EXPENSE_ID,returned_expense.getSql_returned_expense_id());
        values.put(KEY_SQL_CICLE_RETURNED_EXPENSE_ID,returned_expense.getSql_cicle_id());
        values.put(KEY_SQL_BUILDING_RETURNED_EXPENSE_ID,returned_expense.getSql_building_id());
        values.put(KEY_USER_ID,returned_expense.getUser_id());
        values.put(KEY_RETURNED_EXPENSE_CICLE_ID, returned_expense.getCicle_id());
        values.put(KEY_RETURNED_EXPENSE_BUILDING_ID, returned_expense.getBuilding_id());
        values.put(KEY_RETURNED_EXPENSE_TYPE, returned_expense.getType());
        values.put(KEY_RETURNED_EXPENSE_DATE, returned_expense.getDate());
        values.put(KEY_RETURNED_EXPENSE_QUANTITY, returned_expense.getQuantity());
        values.put(KEY_RETURNED_EXPENSE_PRICE, returned_expense.getPrice());
        values.put(KEY_FLAG, returned_expense.getFlag());
        long row=db.insert(TABLE_RETURNED_EXPENSE_INFO,null,values);
        Log.d("row", String.valueOf(row));
        //cursor.close();
        
        return  row;
    }

    public returned_expense_class getReturnedExpense(int returned_expense_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_RETURNED_EXPENSE_INFO+" WHERE "+KEY_RETURNED_EXPENSE_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(returned_expense_id)});
        returned_expense_class returned_expense=new returned_expense_class();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                returned_expense.setSql_returned_expense_id(cursor.getInt(0));
                returned_expense.setSql_cicle_id(cursor.getInt(1));
                returned_expense.setSql_building_id(cursor.getInt(2));
                returned_expense.setUser_id(cursor.getInt(3));
                returned_expense.setReturned_expense_id(cursor.getInt(4));
                returned_expense.setCicle_id(cursor.getInt(5));
                returned_expense.setBuilding_id(cursor.getInt(6));
                returned_expense.setType(cursor.getString(7));
                returned_expense.setDate(cursor.getString(8));
                returned_expense.setQuantity(cursor.getInt(9));
                returned_expense.setPrice(cursor.getInt(10));
                returned_expense.setFlag(cursor.getInt(11));
            }
        }
        cursor.close();
        
//        Log.d("get_returned_exp", returned_expense.getType());
        return returned_expense;
    }

    public returned_expense_class getReturnedExpenseBySql(int sql_returned_expense_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_RETURNED_EXPENSE_INFO+" WHERE "+KEY_SQL_RETURNED_EXPENSE_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(sql_returned_expense_id)});
        returned_expense_class returned_expense=null;
        if(cursor!=null){
            if(cursor.moveToFirst()){
                returned_expense=new returned_expense_class();
                returned_expense.setSql_returned_expense_id(cursor.getInt(0));
                returned_expense.setSql_cicle_id(cursor.getInt(1));
                returned_expense.setSql_building_id(cursor.getInt(2));
                returned_expense.setUser_id(cursor.getInt(3));
                returned_expense.setReturned_expense_id(cursor.getInt(4));
                returned_expense.setCicle_id(cursor.getInt(5));
                returned_expense.setBuilding_id(cursor.getInt(6));
                returned_expense.setType(cursor.getString(7));
                returned_expense.setDate(cursor.getString(8));
                returned_expense.setQuantity(cursor.getInt(9));
                returned_expense.setPrice(cursor.getInt(10));
                returned_expense.setFlag(cursor.getInt(11));
            }
        }
        cursor.close();
        
//        Log.d("get_returned_exp", returned_expense.getType());
        return returned_expense;
    }

    public void deleteReturnedExpense(returned_expense_class returned_expense){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_RETURNED_EXPENSE_INFO, KEY_RETURNED_EXPENSE_ID + "=?", new String[]{String.valueOf(returned_expense.getReturned_expense_id())});
        Log.d("re_del_status", "deleted");
        
    }

    public int countReturnedExpense(int cicle_id,int building_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_RETURNED_EXPENSE_INFO+" WHERE "+KEY_RETURNED_EXPENSE_CICLE_ID+"=? AND "+KEY_RETURNED_EXPENSE_BUILDING_ID+"=?";
        Cursor cursor=db.rawQuery(sql, new String[]{String.valueOf(cicle_id), String.valueOf(building_id)});
        int count=-1;
        if(cursor!=null){
            count=cursor.getCount();
        }
        cursor.close();
        
        return count;
    }

    public int updateReturnedExpense(returned_expense_class returned_expense){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_SQL_RETURNED_EXPENSE_ID,returned_expense.getSql_returned_expense_id());
        values.put(KEY_SQL_CICLE_RETURNED_EXPENSE_ID,returned_expense.getSql_cicle_id());
        values.put(KEY_SQL_BUILDING_RETURNED_EXPENSE_ID,returned_expense.getSql_building_id());
        values.put(KEY_USER_ID,returned_expense.getUser_id());
        values.put(KEY_RETURNED_EXPENSE_CICLE_ID, returned_expense.getCicle_id());
        values.put(KEY_RETURNED_EXPENSE_BUILDING_ID, returned_expense.getBuilding_id());
        values.put(KEY_RETURNED_EXPENSE_TYPE, returned_expense.getType());
        values.put(KEY_RETURNED_EXPENSE_DATE, returned_expense.getDate());
        values.put(KEY_RETURNED_EXPENSE_QUANTITY, returned_expense.getQuantity());
        values.put(KEY_RETURNED_EXPENSE_PRICE, returned_expense.getPrice());
        values.put(KEY_FLAG,returned_expense.getFlag());
        values.put(KEY_RETURNED_EXPENSE_PRICE, returned_expense.getPrice());
        int count=db.update(TABLE_RETURNED_EXPENSE_INFO,values,KEY_RETURNED_EXPENSE_ID+"=?",new String[]{String.valueOf(returned_expense.getReturned_expense_id())});
        Log.d("dp_update", String.valueOf(count));
        
        return count;
    }

    public List<returned_expense_class> get_all_returned_expense(int cicle_id,int building_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_RETURNED_EXPENSE_INFO+" WHERE "+KEY_RETURNED_EXPENSE_CICLE_ID+"=? AND "+KEY_RETURNED_EXPENSE_BUILDING_ID+"=? AND "+KEY_FLAG+"!=-1";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        List<returned_expense_class> returned_expense_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    returned_expense_class returned_expense=new returned_expense_class();
                    returned_expense.setSql_returned_expense_id(cursor.getInt(0));
                    returned_expense.setSql_cicle_id(cursor.getInt(1));
                    returned_expense.setSql_building_id(cursor.getInt(2));
                    returned_expense.setUser_id(cursor.getInt(3));
                    returned_expense.setReturned_expense_id(cursor.getInt(4));
                    returned_expense.setCicle_id(cursor.getInt(5));
                    returned_expense.setBuilding_id(cursor.getInt(6));
                    returned_expense.setType(cursor.getString(7));
                    returned_expense.setDate(cursor.getString(8));
                    returned_expense.setQuantity(cursor.getInt(9));
                    returned_expense.setPrice(cursor.getInt(10));
                    returned_expense.setFlag(cursor.getInt(11));
                    returned_expense_list.add(returned_expense);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        Log.d("returned_expense_list", String.valueOf(returned_expense_list.size()));
        return returned_expense_list;
    }

    public List<returned_expense_class> get_all_returned_expense_by_user_id(int user_id){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_RETURNED_EXPENSE_INFO+" WHERE "+KEY_USER_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(user_id)});
        List<returned_expense_class> returned_expense_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    returned_expense_class returned_expense=new returned_expense_class();
                    returned_expense.setSql_returned_expense_id(cursor.getInt(0));
                    returned_expense.setSql_cicle_id(cursor.getInt(1));
                    returned_expense.setSql_building_id(cursor.getInt(2));
                    returned_expense.setUser_id(cursor.getInt(3));
                    returned_expense.setReturned_expense_id(cursor.getInt(4));
                    returned_expense.setCicle_id(cursor.getInt(5));
                    returned_expense.setBuilding_id(cursor.getInt(6));
                    returned_expense.setType(cursor.getString(7));
                    returned_expense.setDate(cursor.getString(8));
                    returned_expense.setQuantity(cursor.getInt(9));
                    returned_expense.setPrice(cursor.getInt(10));
                    returned_expense.setFlag(cursor.getInt(11));
                    returned_expense_list.add(returned_expense);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        Log.d("user_re_list", String.valueOf(returned_expense_list.size()));
        return returned_expense_list;
    }


    //task


    public int createTask(task_class task){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_USER_ID,task.getUser_id());
        values.put(KEY_SQL_TASK_ID,task.getSql_task_id());
        values.put(KEY_TASK_CICLE_NAME,task.getCicle_name());
        values.put(KEY_TASK_BUILDING_NAME,task.getBuilding_name());
        values.put(KEY_TASK_NAME,task.getTask_name());
        values.put(KEY_TASK_DATE,task.getDate());
        values.put(KEY_TASK_LOCATION,task.getLocation());
        values.put(KEY_TASK_TIME_START, task.getTime_start());
        values.put(KEY_TASK_DONE, task.getDone());
        long row=db.insert(TABLE_TASK_INFO, null, values);
        Log.d("create_task_date",values.getAsString(KEY_TASK_DATE));
        Log.d("row", String.valueOf(row));
        Log.d("task_user_id", String.valueOf(task.getUser_id()));
        Log.d("tasK_date", task.getDate());
        //cursor.close();
        
        return  (int)row;
    }

    public task_class getTask(int task_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_TASK_INFO+" WHERE "+KEY_TASK_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(task_id)});
        task_class task=null;
        if(cursor!=null){
            if(cursor.moveToFirst()){
                task=new task_class();
                task.setTask_id(cursor.getInt(0));
                task.setUser_id(cursor.getInt(1));
                task.setSql_task_id(cursor.getInt(2));
                task.setCicle_name(cursor.getString(3));
                task.setBuilding_name(cursor.getString(4));
                task.setTask_name(cursor.getString(5));
                task.setDate(cursor.getString(6));
                task.setLocation(cursor.getString(7));
                task.setTime_start(cursor.getString(8));
                task.setDone(cursor.getInt(9));
                Log.d("task_user_id", String.valueOf(task.getUser_id()));
                Log.d("tasK_date", task.getDate());
            }
        }
        cursor.close();
        
        Log.d("get_task_location", task.getLocation());
        return task;
    }


    public int countTask(int user_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_TASK_INFO+" WHERE "+KEY_USER_ID+"=?";
        Cursor cursor=db.rawQuery(sql, new String[]{String.valueOf(user_id)});
        int count=-1;
        if(cursor!=null){
            count=cursor.getCount();
        }
        Log.d("task_count", String.valueOf(count));
        cursor.close();
        
        return count;
    }


    public int updateTask(task_class task){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TASK_ID,task.getTask_id());
        values.put(KEY_USER_ID,task.getUser_id());
        values.put(KEY_SQL_TASK_ID,task.getSql_task_id());
        values.put(KEY_TASK_CICLE_NAME,task.getCicle_name());
        values.put(KEY_TASK_BUILDING_NAME,task.getBuilding_name());
        values.put(KEY_TASK_NAME,task.getTask_name());
        values.put(KEY_TASK_DATE,task.getDate());
        values.put(KEY_TASK_LOCATION,task.getLocation());
        values.put(KEY_TASK_TIME_START,task.getTime_start());
        values.put(KEY_TASK_DONE, task.getDone());
        int count=db.update(TABLE_TASK_INFO,values,KEY_TASK_ID+"=?",new String[]{String.valueOf(task.getTask_id())});
        Log.d("task_update", String.valueOf(count));
        
        return count;
    }


    public List<task_class> get_all_not_done_task(int user_id,String date){
        SQLiteDatabase db=getWritableDatabase();
        sql="SELECT * FROM "+TABLE_TASK_INFO+" WHERE "+KEY_USER_ID+"=? AND "+KEY_TASK_DATE+"=? AND "+KEY_TASK_DONE+"=?";
        Log.d("task_get_not_done_sql", sql);
        Log.d("task_done_0_id", String.valueOf(user_id));
        Log.d("task_done_0_date", date);
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(user_id),date,String.valueOf(0)});
        List<task_class> task_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    task_class task=new task_class();
                    task.setTask_id(cursor.getInt(0));
                    task.setUser_id(cursor.getInt(1));
                    task.setSql_task_id(cursor.getInt(2));
                    task.setCicle_name(cursor.getString(3));
                    task.setBuilding_name(cursor.getString(4));
                    task.setTask_name(cursor.getString(5));
                    task.setDate(cursor.getString(6));
                    task.setLocation(cursor.getString(7));
                    task.setTime_start(cursor.getString(8));
                    task.setDone(cursor.getInt(9));
                    task_list.add(task);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        Log.d("task_not-done_list", String.valueOf(task_list.size()));
        return task_list;
    }

    public List<task_class> get_all__done_task(int user_id,String date){
        SQLiteDatabase db=getWritableDatabase();
        Log.d("task_done_1_id",String.valueOf(user_id));
        Log.d("task_done_1_date",date);
        sql="SELECT * FROM "+TABLE_TASK_INFO+" WHERE "+KEY_USER_ID+"=? AND "+KEY_TASK_DATE+"=? AND "+KEY_TASK_DONE+"=?";
        Log.d("task_get__done_sql",sql);
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(user_id),date,String.valueOf(1)});
        List<task_class> task_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    task_class task=new task_class();
                    task.setTask_id(cursor.getInt(0));
                    task.setUser_id(cursor.getInt(1));
                    task.setSql_task_id(cursor.getInt(2));
                    task.setCicle_name(cursor.getString(3));
                    task.setBuilding_name(cursor.getString(4));
                    task.setTask_name(cursor.getString(5));
                    task.setDate(cursor.getString(6));
                    task.setLocation(cursor.getString(7));
                    task.setTime_start(cursor.getString(8));
                    task.setDone(cursor.getInt(9));
                    task_list.add(task);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        Log.d("task_done_list", String.valueOf(task_list.size()));
        return task_list;
    }

    public List<task_class> get_all_done_task_by_user_id(int user_id){
        SQLiteDatabase db=getWritableDatabase();
        Log.d("task_done_1_id",String.valueOf(user_id));

        sql="SELECT * FROM "+TABLE_TASK_INFO+" WHERE "+KEY_USER_ID+"=?  AND "+KEY_TASK_DONE+"=?";
        Log.d("task_get__done_sql",sql);
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(user_id),String.valueOf(1)});
        List<task_class> task_list=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    task_class task=new task_class();
                    task.setTask_id(cursor.getInt(0));
                    task.setUser_id(cursor.getInt(1));
                    task.setSql_task_id(cursor.getInt(2));
                    task.setCicle_name(cursor.getString(3));
                    task.setBuilding_name(cursor.getString(4));
                    task.setTask_name(cursor.getString(5));
                    task.setDate(cursor.getString(6));
                    task.setLocation(cursor.getString(7));
                    task.setTime_start(cursor.getString(8));
                    task.setDone(cursor.getInt(9));
                    task_list.add(task);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        
        Log.d("task_done_list", String.valueOf(task_list.size()));
        return task_list;
    }


    public int check_task(int sql_task_id){
        SQLiteDatabase db=getReadableDatabase();
        sql="SELECT * FROM "+TABLE_TASK_INFO+" WHERE "+KEY_SQL_TASK_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(sql_task_id)});
        int count=0;
        if(cursor!=null){
            count=cursor.getCount();
        }
        Log.d("check_sql_task",String.valueOf(count));
        cursor.close();
        
        return count;
    }


}
