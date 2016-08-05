package com.example.sneh.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import java.util.Arrays;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class json_parsing {

    //Database Handler
    db_handler handler;
    DataBaseHandler new_handler;
    List<cicle> cicle_list;
    List<building_class> building_list;
    List<equipment_> equipment_list;
    List<worker_class>worker_list;
    List<medical_> medical_list;
    List<food_class> food_list;
    List<consommation_>consommation_list;
    List<animal_class> animal_list;
    List<death_>death_list;
    List<returned_expense_class> returned_expense_list;
    List<additional_expense_class>additional_expense_list;
    List<down_payment_class>down_payment_list;
    List<egg_n>normalegg_list;
    List<egg_b>brokenegg_list;
    List<temp_>temp_list;
    List<notes_file>note_list;
    List<image_class>image_list;
    List<task_class>task_list;
    Context con;
    int man_size;
    public message_class login_register_parsing(String result) {
        message_class message = new message_class();
        try {
            JSONObject json_object = new JSONObject(result);
            message.setSuccess(json_object.getInt("success"));
            message.setMessage(json_object.getString("message"));
            Log.d("message_mes", message.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return message;
    }

    public void json(Context context, String result) {
        handler = new db_handler(context.getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        //getting user_id
        SharedPreferences pref = context.getSharedPreferences("user_data", Context.MODE_PRIVATE);
        int user_id = pref.getInt("user_id", -1);
        Log.d("user_id", String.valueOf(user_id));
        try {
            JSONObject jobject = new JSONObject(result);
            JSONArray task_array = jobject.getJSONArray("task");
            Log.d("task_length", String.valueOf(task_array));
            Log.d("task_count",String.valueOf(handler.countTask(user_id)));
            for (int i = 0; i < task_array.length() && task_array.length()>0; i++) {
                if(task_array.getJSONObject(i)==null){
                    return;
                }
                JSONObject obj = task_array.getJSONObject(i);
                task_class task = new task_class();
                task.setUser_id(user_id);
                task.setSql_task_id(obj.getInt("sql_task_id"));
                task.setCicle_name(obj.getString("cicle"));
                task.setBuilding_name(obj.getString("building"));
                task.setTask_name(obj.getString("task"));
                task.setDate(obj.getString("date"));
                task.setLocation(obj.getString("location"));
                task.setTime_start(obj.getString("time_start"));
                task.setDone(obj.getInt("done"));
                if (handler.check_task(task.getSql_task_id()) == 0) {
                    int row = handler.createTask(task);
                    Log.d("task_row", String.valueOf(row));
                }
                Log.d("task_location", task.getLocation());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public int main_parse(JSONObject object, Context context) {
        con = context;


        Log.d("main_parse_with_image", "image");
        try {
            JSONArray cicle_json_array = object.getJSONArray("cicle");
            JSONArray build_json_array = object.getJSONArray("building");
            JSONArray equip_json_array=object.getJSONArray("equipment");
            JSONArray worker_json_array=object.getJSONArray("worker");
            JSONArray medical_json_array=object.getJSONArray("medical");
            JSONArray food_json_array=object.getJSONArray("food");
            JSONArray con_json_array=object.getJSONArray("consommation");
            JSONArray add_expense_json_array=object.getJSONArray("additional_expense");
            JSONArray ret_expense_json_array=object.getJSONArray("returned_expense");
            JSONArray down_payment_json_array=object.getJSONArray("down_payment");
            JSONArray animal_json_array=object.getJSONArray("animal");
            JSONArray death_json_array=object.getJSONArray("death");
            JSONArray normal_egg_json_array=object.getJSONArray("normal_egg");
            JSONArray broken_egg_json_array=object.getJSONArray("broken_egg");
            JSONArray temp_json=object.getJSONArray("temp");
            JSONArray note_json_array=object.getJSONArray("note");
            JSONArray image_json_array=object.getJSONArray("image");
            JSONArray task_json_array=object.getJSONArray("task");
            this.cicle_json(cicle_json_array);
            this.building_json(build_json_array);
            this.equipment_json(equip_json_array);
            this.worker_json(worker_json_array);
            this.medical_json(medical_json_array);
            this.food_json(food_json_array);
            this.consommation_json(con_json_array);
            this.additional_expense_json(add_expense_json_array);
            this.returned_expense_json(ret_expense_json_array);
            this.down_payment_json(down_payment_json_array);
            this.animal_json(animal_json_array);
            this.death_json(death_json_array);
            this.brokenegg_json(broken_egg_json_array);
            this.normalegg_json(normal_egg_json_array);
            this.temp_json(temp_json);
            this.note_json(note_json_array);
            this.image_json(image_json_array);
            this.task_json(task_json_array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("man_size_with_img", String.valueOf(man_size));


        return man_size;
    }

    public int main_parse_without_image(JSONObject object, Context context) {
        con = context;
        Log.d("main_parse__image", "no_image");

        try {
            JSONArray cicle_json_array = object.getJSONArray("cicle");
            JSONArray build_json_array = object.getJSONArray("building");
            JSONArray equip_json_array=object.getJSONArray("equipment");
            JSONArray worker_json_array=object.getJSONArray("worker");
            JSONArray medical_json_array=object.getJSONArray("medical");
            JSONArray food_json_array=object.getJSONArray("food");
            JSONArray con_json_array=object.getJSONArray("consommation");
            JSONArray add_expense_json_array=object.getJSONArray("additional_expense");
            JSONArray ret_expense_json_array=object.getJSONArray("returned_expense");
            JSONArray down_payment_json_array=object.getJSONArray("down_payment");
            JSONArray animal_json_array=object.getJSONArray("animal");
            JSONArray death_json_array=object.getJSONArray("death");
            JSONArray normal_egg_json_array=object.getJSONArray("normal_egg");
            JSONArray broken_egg_json_array=object.getJSONArray("broken_egg");
            JSONArray temp_json=object.getJSONArray("temp");
            JSONArray note_json_array=object.getJSONArray("note");
            JSONArray task_json_array=object.getJSONArray("task");
            this.cicle_json(cicle_json_array);
            this.building_json(build_json_array);
            this.equipment_json(equip_json_array);
            this.worker_json(worker_json_array);
            this.medical_json(medical_json_array);
            this.food_json(food_json_array);
            this.consommation_json(con_json_array);
            this.additional_expense_json(add_expense_json_array);
            this.returned_expense_json(ret_expense_json_array);
            this.down_payment_json(down_payment_json_array);
            this.animal_json(animal_json_array);
            this.death_json(death_json_array);
            this.brokenegg_json(broken_egg_json_array);
            this.normalegg_json(normal_egg_json_array);
            this.temp_json(temp_json);
            this.note_json(note_json_array);
            this.task_json(task_json_array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("man_size_without_img", String.valueOf(man_size));


        return man_size;
    }

    public void get_list_without_image(List<cicle> cicle, List<building_class> building,List<equipment_> equipment,List<worker_class> worker,List<medical_> medical,List<food_class> food,List<consommation_>consommation,List<additional_expense_class> additonal,List<returned_expense_class> returned,List<down_payment_class>down_payment,List<animal_class> animal,List<death_> death,List<egg_n> normal,List<egg_b> broken,List<temp_> temp,List<notes_file> note,List<task_class> task) {
        cicle_list = cicle;
        building_list = building;
        equipment_list=equipment;
        worker_list=worker;
        medical_list=medical;
        food_list=food;
        consommation_list=consommation;
        additional_expense_list=additonal;
        returned_expense_list=returned;
        down_payment_list=down_payment;
        animal_list=animal;
        death_list=death;
        normalegg_list=normal;
        brokenegg_list=broken;
        temp_list=temp;
        note_list=note;
        task_list=task;
        Log.d("jscicle_list_size",String.valueOf(cicle_list.size()));
        Log.d("jsbuilding_list_size", String.valueOf(building_list.size()));
        Log.d("jsequip_list_size", String.valueOf(equipment_list.size()));
        Log.d("jsworker_list_size", String.valueOf(worker_list.size()));
        Log.d("jsadd_list_size", String.valueOf(additional_expense_list.size()));
        Log.d("jsreturn_list_size", String.valueOf(returned_expense_list.size()));
        Log.d("jsdown_list_size", String.valueOf(down_payment_list.size()));
        Log.d("jsworker_list_size", String.valueOf(worker_list.size()));

    }

    public void get_list(List<cicle> cicle, List<building_class> building,List<equipment_> equipment,List<worker_class> worker,List<medical_> medical,List<food_class> food,List<consommation_>consommation,List<additional_expense_class> additonal,List<returned_expense_class> returned,List<down_payment_class>down_payment,List<animal_class> animal,List<death_> death,List<egg_n> normal,List<egg_b> broken,List<temp_> temp,List<notes_file> note,List<image_class>image,List<task_class> task) {
        cicle_list = cicle;
        building_list = building;
        equipment_list=equipment;
        worker_list=worker;
        medical_list=medical;
        food_list=food;
        consommation_list=consommation;
        additional_expense_list=additonal;
        returned_expense_list=returned;
        down_payment_list=down_payment;
        animal_list=animal;
        death_list=death;
        normalegg_list=normal;
        brokenegg_list=broken;
        temp_list=temp;
        note_list=note;
        image_list=image;
        task_list=task;
        Log.d("jscicle_list_size",String.valueOf(cicle_list.size()));
        Log.d("jsbuilding_list_size", String.valueOf(building_list.size()));
        Log.d("jsequip_list_size", String.valueOf(equipment_list.size()));
        Log.d("jsworker_list_size", String.valueOf(worker_list.size()));
        Log.d("jsadd_list_size", String.valueOf(additional_expense_list.size()));
        Log.d("jsreturn_list_size", String.valueOf(returned_expense_list.size()));
        Log.d("jsdown_list_size", String.valueOf(down_payment_list.size()));
        Log.d("jsworker_list_size", String.valueOf(worker_list.size()));

    }

    public void task_json(JSONArray task_json) {
        JSONObject keys = null;
        handler = new db_handler(con);
        Log.d("In equipment", "In equipment");
        int i=0;
        try {
            if (task_json.get(0) instanceof JSONArray)
                keys = null;
            if (task_json.get(0) instanceof JSONObject) {
                keys = task_json.getJSONObject(0);
                Log.d("task_instance", "instance");
            }
            for(i=0;i<task_list.size() && keys!=null ;i++){
                Log.d("task_list_initial_sql"+String.valueOf(task_list.get(i).getTask_id()), String.valueOf(task_list.get(i).getSql_task_id()));
                if(keys.has(String.valueOf(task_list.get(i).getTask_id()))){
                    int sql_key=keys.getInt(String.valueOf(task_list.get(i).getTask_id()));
                    Log.d("task_sql_key::"+String.valueOf(task_list.get(i).getTask_id()), String.valueOf(sql_key));
                    if(sql_key<0){
                        Log.d("updating task","updating task");
                    }
                    else{
                        Log.d("adding_task","addding_task");
                        task_list.get(i).setSql_task_id(sql_key);
                        handler.updateTask(task_list.get(i));
                    }
                }
                Log.d("task_list_initial_sql"+String.valueOf(task_list.get(i).getTask_id()), String.valueOf(task_list.get(i).getSql_task_id()));

            }
        } catch (JSONException e) {
                e.printStackTrace();
        }
    }

    public void cicle_json(JSONArray cicle) {
        JSONArray new_arrival;

        JSONObject keys = null;
        handler = new db_handler(con);
        new_handler=new DataBaseHandler(con);
        handler.onCreateTable(handler.getWritableDatabase());
        new_handler.onCreateTable(handler.getWritableDatabase());
        int i = 0;
        try {
            new_arrival = cicle.getJSONArray(0);

            Log.d("aaaaaaaaaaa", cicle.get(i).getClass().toString());
            if (cicle.get(1) instanceof JSONArray) {
                keys = null;
            }
            if (cicle.get(1) instanceof JSONObject) {
                Log.d("instance", "Jsonobject");
                keys = cicle.getJSONObject(1);
            }

            for (i = 0; i < cicle_list.size() && keys != null; i++) {

                Log.d("ci", String.valueOf(cicle_list.get(i).getCicle_id()));
                Log.d("flags", String.valueOf(cicle_list.get(i).getFlag()));
                if (keys.has(String.valueOf(cicle_list.get(i).getCicle_id()))) {
                    int sql_key = keys.getInt(String.valueOf(cicle_list.get(i).getCicle_id()));
                    Log.d("key_value", String.valueOf(sql_key));

                    if (sql_key < 0) {

                        if (cicle_list.get(i).getFlag() == -1 && sql_key == -2) {
                            Log.d("deleting", "deleting");
                            handler.deleteCicle(cicle_list.get(i));
                        }
                        if (cicle_list.get(i).getFlag() == 1 && sql_key == -5) {
                            Log.d("updatin", "updating");
                            cicle_list.get(i).setFlag(4);
                            handler.updateCicle(cicle_list.get(i));
                        }
                    } else {
                        Log.d("adding", "adding");
                        cicle_list.get(i).setSql_cicle_id(keys.getInt(String.valueOf(cicle_list.get(i).getCicle_id())));
                        cicle_list.get(i).setFlag(4);
                        handler.updateCicle(cicle_list.get(i));
                    }
                }

                Log.d("cicle_tag", String.valueOf(cicle_list.get(i).getSql_cicle_id()));
                Log.d("cicle_flag_a", String.valueOf(cicle_list.get(i).getFlag()));
                if(cicle_list.get(i).getFlag()!=4){
                    man_size=man_size+1;
                }
            }
            Gson gson = new Gson();
            for (i = 0; i < new_arrival.length() && new_arrival.length() > 0; i++) {
                JSONObject object = new_arrival.getJSONObject(i);
                String json = object.toString().replace("\\", "");
                Log.d("json_string,parse", json);
                cicle cicle_obj = gson.fromJson(json, cicle.class);
                Log.d("jparse_cicle_insert", cicle_obj.getTitle());
                Log.d("Jparse_cicle_sql", String.valueOf(cicle_obj.getSql_cicle_id()));
                Log.d("Jparse_cicle_user_id", String.valueOf(cicle_obj.getUser_id()));
                Log.d("Jparse_cicle_flag", String.valueOf(cicle_obj.getFlag()));
                if (handler.get_sql_cicle(cicle_obj.getSql_cicle_id()) == null) {
                    if(cicle_obj.getFlag()!=-1){
                        cicle_obj.setFlag(1);
                    }
                    else{
                        cicle_obj.setFlag(-1);
                    }
                    handler.CreateCicle(cicle_obj);
                } else {
                    if(cicle_obj.getFlag()!=-1){
                        cicle_obj.setFlag(1);
                    }
                    else{
                        cicle_obj.setFlag(-1);
                    }
                    handler.updateCicle(cicle_obj);
                }
                if(cicle_obj.getFlag()!=4){
                    man_size=man_size+1;
                }
            }
            Log.d("Ending_cicle_josn","Ending_cicle_json");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void building_json(JSONArray build_json) {
        JSONObject keys = null;
        int i = 0;
        JSONArray new_arrival;
        Log.d("building_json_before",build_json.toString());
        Log.d("IN building", "in building");
        try {
            new_arrival=build_json.getJSONArray(0);
            if (build_json.get(1) instanceof JSONArray) {
                keys = null;
            }


            if (build_json.get(1) instanceof JSONObject) {
                Log.d("buildinstance", "Jsonobject");
                keys = build_json.getJSONObject(1);
            }
            for (i = 0; i < building_list.size() && keys!=null; i++) {
                Log.d(String.valueOf(building_list.get(i).getBuilding_id()), String.valueOf(building_list.get(i).getFlag()));
                Log.d("building_flags", String.valueOf(building_list.get(i).getFlag()));
                Log.d("building_list_id",String.valueOf(building_list.get(i).getBuilding_id()));
                // Log.d("keys_valye",String.valueOf(keys.get("1")));
                if (!keys.isNull(String.valueOf(building_list.get(i).getBuilding_id()))) {
                    int sql_key = keys.getInt(String.valueOf(building_list.get(i).getBuilding_id()));
                    Log.d("key_value", String.valueOf(sql_key));
                    if (sql_key < 0) {
                        if (building_list.get(i).getFlag() == -1 && sql_key == -2) {
                            Log.d("parse_deleting_build", "deletinh");
                            handler.deleteBuilding(building_list.get(i));
                        }
                        if (building_list.get(i).getFlag() == 1 && sql_key == -5) {
                            Log.d("parse_update_build", "updating");
                            building_list.get(i).setFlag(4);
                            handler.updateBuilding(building_list.get(i));
                        }
                    } else {
                        Log.d("build_adding", "adding");
                        building_list.get(i).setSql_building_id(sql_key);
                        building_list.get(i).setFlag(4);
                        if(building_list.get(i).getOther().isEmpty()){
                            building_list.get(i).setOther("");
                        }
                        Log.d("obuilding_sqlcicle_id", String.valueOf(building_list.get(i).getSql_cicle_id()));
                        Log.d("obuild_sql_building_id", String.valueOf(building_list.get(i).getSql_building_id()));
                        Log.d("obuilding_cicle_id", String.valueOf(building_list.get(i).getCicle_id()));
                        Log.d("obuilding_build_id", String.valueOf(building_list.get(i).getSql_cicle_id()));
                        int j = 0;
                        for (j = 0; j < cicle_list.size(); j++) {
                            if (cicle_list.get(j).getCicle_id() == building_list.get(i).getCicle_id()) {
                                building_list.get(i).setSql_cicle_id(cicle_list.get(j).getSql_cicle_id());
                                Log.d("building_cicle_id", String.valueOf(building_list.get(i).getSql_cicle_id()));
                                Log.d("build_sql_building_id", String.valueOf(building_list.get(i).getSql_building_id()));
                                Log.d("building_sql_cicle_id", String.valueOf(building_list.get(i).getSql_cicle_id()));
                                Log.d("building_cicle_sql_id", String.valueOf(cicle_list.get(j).getSql_cicle_id()));
                                building_list.get(i).setFlag(1);
                                handler.updateBuilding(building_list.get(i));
                            }

                        }
                    }
                }
                Log.d("building_after_flags", String.valueOf(building_list.get(i).getFlag()));
                Log.d(String.valueOf("after_flag:"+building_list.get(i).getBuilding_id()), String.valueOf(building_list.get(i).getFlag()));
                if(building_list.get(i).getFlag()==1){
                    man_size=man_size+1;
                }
            }

            Gson gson=new Gson();
            for(i=0;i<new_arrival.length() && new_arrival.length()>0;i++){
                JSONObject jobj=new_arrival.getJSONObject(i);
                String json=jobj.toString().replace("\\", "");
                Log.d("json_parse_building",json);
                building_class building=gson.fromJson(json, building_class.class);
                cicle cicle=handler.get_sql_cicle(building.getSql_cicle_id());
                if(cicle!=null) {
                    building.setCicle_id(cicle.getCicle_id());
                    Log.d("jparse_build_insert", building.getTitle());
                    Log.d("Jparse_building_sql", String.valueOf(building.getSql_cicle_id()));
                    Log.d("Jparse_build_cicle_sql", String.valueOf(building.getSql_building_id()));
                    Log.d("Jparse_cicle_flag", String.valueOf(building.getFlag()));
                    Log.d("jparse_build_cicle_id", String.valueOf(building.getCicle_id()));
                    Log.d("jparse_build_build_id", String.valueOf(building.getBuilding_id()));
                    Log.d("jparse_build_user_id", String.valueOf(building.getUser_id()));
                    if (handler.getBuildingBYSQL(building.getSql_building_id()) == null) {
                        if (building.getFlag() != -1) {
                            building.setFlag(1);
                        } else {
                            building.setFlag(-1);
                        }
                        Log.d("building_null" + String.valueOf(building.getBuilding_id()), String.valueOf(cicle.getCicle_id()));
                        Log.d("building_nullflag_" + String.valueOf(building.getBuilding_id()), String.valueOf(building.getFlag()));
                        building.setCicle_id(cicle.getCicle_id());
                        handler.createBuilding(building);
                    } else {
                        if (building.getFlag() != -1) {
                            building.setFlag(1);
                        } else {
                            building.setFlag(-1);
                        }
                        Log.d("buildingnnull" + String.valueOf(building.getBuilding_id()), String.valueOf(cicle.getCicle_id()));
                        Log.d("building_nnullflag_" + String.valueOf(building.getBuilding_id()), String.valueOf(building.getFlag()));
                        building.setCicle_id(cicle.getCicle_id());
                        handler.updateBuilding(building);
                    }
                    if (building.getFlag() != 4) {
                        man_size = man_size + 1;
                    }
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void equipment_json(JSONArray equip_json){
        JSONObject keys = null;
        int i = 0;
        JSONArray new_arrival;
        Log.d("In equipment","In equipment");
        try {
            new_arrival=equip_json.getJSONArray(0);
            if(equip_json.get(1) instanceof JSONArray)
                keys=null;
            if(equip_json.get(1) instanceof  JSONObject) {
                keys = equip_json.getJSONObject(1);
                Log.d("equip_instance","instance");
            }

            for(i=0;i<equipment_list.size() && keys!=null;i++){
                Log.d(String.valueOf(equipment_list.get(i).getEquip_id()), String.valueOf(equipment_list.get(i).getEquip_id()));
                Log.d(String.valueOf("equip_before_flag::"+equipment_list.get(i).getEquip_id()), String.valueOf(equipment_list.get(i).getFlag()));
                if(keys.has(String.valueOf(equipment_list.get(i).getEquip_id())) == true){
                    int sql_key = keys.getInt(String.valueOf(equipment_list.get(i).getEquip_id()));
                    Log.d("equip_key_value", String.valueOf(sql_key));
                    if (sql_key < 0) {
                        if (equipment_list.get(i).getFlag() == -1 && sql_key == -2) {
                            Log.d("parse_deleting_equip", "deletinh");
                            handler.deleteEquipment(equipment_list.get(i));
                        }
                        if (equipment_list.get(i).getFlag() == 1 && sql_key == -5) {
                            Log.d("parse_update_equip", "updating");
                            equipment_list.get(i).setFlag(4);
                            handler.updateEquipment(equipment_list.get(i));
                        }
                    }
                    else{
                        Log.d("adding_equip", "adding_equip");
                        equipment_list.get(i).setSql_equip_id(sql_key);
                        equipment_list.get(i).setFlag(4);

                        int j = 0;
                        for (j = 0; j < cicle_list.size(); j++) {
                            if (cicle_list.get(j).getCicle_id() == equipment_list.get(i).getCicle_id()) {
                                equipment_list.get(i).setSql_cicle_id(cicle_list.get(j).getSql_cicle_id());
                                Log.d("ADd_equip_cicle_sql","add_equip_cicle_sql");
                                Log.d("equip_cicle_id", String.valueOf(cicle_list.get(j).getSql_cicle_id()));
                                Log.d("equip_sql_equip_id", String.valueOf(equipment_list.get(i).getEquip_id()));
                                Log.d("equip_sql_cicle_id", String.valueOf(equipment_list.get(i).getSql_cicle_id()));
                                Log.d("equip_cicle_sql_id", String.valueOf(cicle_list.get(j).getSql_cicle_id()));
                                equipment_list.get(i).setFlag(1);
                            }
                        }
                        for(j=0;j<building_list.size();j++){
                            if(building_list.get(j).getBuilding_id()==equipment_list.get(i).getBuilding_id()){
                                equipment_list.get(i).setSql_building_id(building_list.get(j).getSql_building_id());
                                Log.d("ADd_equip_cicle_sql","add_equip_cicle_sql");
                                Log.d("equip_build_id", String.valueOf(building_list.get(j).getSql_cicle_id()));
                                Log.d("equip_sql_equip_id", String.valueOf(equipment_list.get(i).getEquip_id()));
                                Log.d("equip_sql_cicle_id", String.valueOf(equipment_list.get(i).getSql_cicle_id()));
                                Log.d("equip_sql_build_id", String.valueOf(equipment_list.get(i).getSql_building_id()));
                                equipment_list.get(i).setFlag(1);
                                handler.updateEquipment(equipment_list.get(i));
                            }
                        }
                    }
                }
                Log.d(String.valueOf("equip_after_flag::"+equipment_list.get(i).getEquip_id()), String.valueOf(equipment_list.get(i).getEquip_id()));
                if(equipment_list.get(i).getFlag()!=4){
                    man_size=man_size+1;
                }

            }

            Gson gson=new Gson();
            for(i=0;i<new_arrival.length() && new_arrival.length()>0;i++) {
                JSONObject jobj = new_arrival.getJSONObject(i);
                String json = jobj.toString().replace("\\", "");
                Log.d("json_parse_equipment", json);
                equipment_ equipment = gson.fromJson(json, equipment_.class);
                cicle cicle = handler.get_sql_cicle(equipment.getSql_cicle_id());
                building_class build = handler.getBuildingBYSQL(equipment.getSql_building_id());
                if(cicle!=null && build!=null) {
                    equipment.setCicle_id(cicle.getCicle_id());
                    equipment.setBuilding_id(build.getBuilding_id());
                    Log.d("jp_equip_ciclesql", String.valueOf(equipment.getSql_cicle_id()));
                    Log.d("jp_equip_buildsql", String.valueOf(equipment.getSql_building_id()));
                    Log.d("jp_equip_cicle_id", String.valueOf(equipment.getCicle_id()));
                    Log.d("jp_equip_build_id", String.valueOf(equipment.getBuilding_id()));
                    Log.d("jp_equip_flag", String.valueOf(equipment.getFlag()));
                    Log.d("jp_equip_equip_id", String.valueOf(equipment.getSql_equip_id()));
                    Log.d("jp_equip_user_id", String.valueOf(equipment.getUser_id()));
                    if (handler.getEquipmentBYSQL(equipment.getSql_equip_id()) == null) {
                        Log.d("jp_create_equip", "create_equip");
                        if (equipment.getFlag() != -1) {
                            equipment.setFlag(1);
                        } else {
                            equipment.setFlag(-1);
                        }

                        //equipment.setFlag(1);
                        handler.createEquipment(equipment);
                    } else {
                        Log.d("jp_update_equip", "update_equip");
                        if (equipment.getFlag() != -1) {
                            equipment.setFlag(1);
                        } else {
                            equipment.setFlag(-1);
                        }
                        handler.updateEquipment(equipment);

                    }
                    if (equipment.getFlag() != 4) {
                        man_size = man_size + 1;
                    }
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void worker_json(JSONArray worker_json){
        JSONObject keys = null;
        int i = 0;
        JSONArray new_arrival;
        Log.d("In worker","In worker");
        try {
            new_arrival=worker_json.getJSONArray(0);
            if(worker_json.get(1) instanceof JSONArray)
                keys=null;
            if(worker_json.get(1) instanceof  JSONObject)
                keys=worker_json.getJSONObject(1);

            for(i=0;i<worker_list.size() && keys!=null;i++){
                Log.d(String.valueOf(worker_list.get(i).getWorker_id()), String.valueOf(worker_list.get(i).getWorker_id()));
                Log.d(String.valueOf("work_before_flag::"+worker_list.get(i).getWorker_id()), String.valueOf(worker_list.get(i).getFlag()));
                if(keys.has(String.valueOf(worker_list.get(i).getWorker_id())) == true){
                    int sql_key = keys.getInt(String.valueOf(worker_list.get(i).getWorker_id()));
                    Log.d("worker_key_value", String.valueOf(sql_key));
                    if (sql_key < 0) {
                        if (worker_list.get(i).getFlag() == -1 && sql_key == -2) {
                            Log.d("parse_deleting_worker", "deletinh");
                            handler.deleteWorker(worker_list.get(i));
                        }
                        if (worker_list.get(i).getFlag() == 1 && sql_key == -5) {
                            Log.d("parse_update_worker", "updating");
                            worker_list.get(i).setFlag(4);
                            handler.updateWorker(worker_list.get(i));
                        }
                    }
                    else{
                        Log.d("adding_worker", "adding_worker");
                        worker_list.get(i).setSql_worker_id(sql_key);
                        worker_list.get(i).setFlag(4);
                        Log.d("add_work_worker_sql", String.valueOf(worker_list.get(i).getWorker_id()));
                        Log.d("add_worker_worker_id", String.valueOf(worker_list.get(i).getWorker_id()));
                        int j = 0;
                        for (j = 0; j < cicle_list.size(); j++) {
                            if (cicle_list.get(j).getCicle_id() == worker_list.get(i).getCicle_id()) {
                                worker_list.get(i).setSql_cicle_id(cicle_list.get(j).getSql_cicle_id());
                                Log.d("ADd_work_cicle_sql","add_work_cicle_sql");
                                Log.d("work_sql_cicle_id", String.valueOf(worker_list.get(i).getSql_cicle_id()));
                                Log.d("work_cicle_d", String.valueOf(worker_list.get(i).getCicle_id()));
                                worker_list.get(i).setFlag(1);
                            }
                        }
                        for(j=0;j<building_list.size();j++){
                            if(building_list.get(j).getBuilding_id()==worker_list.get(i).getBuilding_id()){
                                worker_list.get(i).setSql_building_id(building_list.get(j).getSql_building_id());
                                Log.d("add_work_build_sql","add_work_build_sql");
                                Log.d("work_building_Sql_id", String.valueOf(worker_list.get(i).getSql_building_id()));
                                Log.d("work_building_id", String.valueOf(worker_list.get(i).getSql_building_id()));
                                worker_list.get(i).setFlag(1);
                                handler.updateWorker(worker_list.get(i));
                            }
                        }
                    }
                }
                Log.d(String.valueOf("worker_after_flag::"+worker_list.get(i).getWorker_id()), String.valueOf(worker_list.get(i).getFlag()));
                if(worker_list.get(i).getFlag()!=4){
                    man_size=man_size+1;
                }

            }

            Gson gson=new Gson();
            for(i=0;i<new_arrival.length() && new_arrival.length()>0;i++){
                JSONObject jobj=new_arrival.getJSONObject(i);
                String json=jobj.toString().replace("\\", "");
                Log.d("json_parse_worker",json);
                worker_class worker=gson.fromJson(json,worker_class.class);
                cicle cicle=handler.get_sql_cicle(worker.getSql_cicle_id());
                building_class build=handler.getBuildingBYSQL(worker.getSql_building_id());
                if(cicle!=null && build!=null) {
                    worker.setCicle_id(cicle.getCicle_id());
                    worker.setBuilding_id(build.getBuilding_id());
                    Log.d("jp_worker_worker_sql", String.valueOf(worker.getSql_worker_id()));
                    Log.d("jp_worker_ciclesql", String.valueOf(worker.getSql_cicle_id()));
                    Log.d("jp_worker_buildsql", String.valueOf(worker.getSql_building_id()));
                    Log.d("jp_worker_cicle_id", String.valueOf(worker.getCicle_id()));
                    Log.d("jp_worker_build_id", String.valueOf(worker.getBuilding_id()));
                    if (worker.getFlag() != -1) {
                        worker.setFlag(1);
                    } else {
                        worker.setFlag(-1);
                    }
                    if (handler.getWorkerBySql(worker.getSql_worker_id()) == null) {
                        Log.d("creating_new_worker","creating_new_worker");
                        handler.CreateWorker(worker);
                    } else {
                        Log.d("updating_existing_work","updating_existing_worker");
                        handler.updateWorker(worker);
                    }
                    if (worker.getFlag() != 4) {
                        man_size = man_size + 1;
                    }
                }
                Log.d("jp_worker_new_flag",String.valueOf(worker.getFlag()));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void medical_json(JSONArray medical_json) {
        JSONObject keys = null;
        int i = 0;
        JSONArray new_arrival;
        Log.d("In medical", "In medical");
        try {
            new_arrival = medical_json.getJSONArray(0);
            if (medical_json.get(1) instanceof JSONArray)
                keys = null;
            if (medical_json.get(1) instanceof JSONObject)
                keys = medical_json.getJSONObject(1);

            for (i = 0; i < medical_list.size() && keys != null; i++) {
                Log.d(String.valueOf(medical_list.get(i).getMedical_id()), String.valueOf(medical_list.get(i).getMedical_id()));
                Log.d(String.valueOf("medical_before_flag::" + medical_list.get(i).getMedical_id()), String.valueOf(medical_list.get(i).getFlag()));
                if (keys.has(String.valueOf(medical_list.get(i).getMedical_id())) == true) {
                    int sql_key = keys.getInt(String.valueOf(medical_list.get(i).getMedical_id()));
                    Log.d("medical_key_value", String.valueOf(sql_key));
                    if (sql_key < 0) {
                        if (medical_list.get(i).getFlag() == -1 && sql_key == -2) {
                            Log.d("parse_deleting_medical", "deletinh");
                            handler.deleteMedical(medical_list.get(i));
                        }
                        if (medical_list.get(i).getFlag() == 1 && sql_key == -5) {
                            Log.d("parse_update_medical", "updating");
                            medical_list.get(i).setFlag(4);
                            handler.updateMedical(medical_list.get(i));
                        }
                    } else {
                        Log.d("adding_medical", "adding_medical");
                        medical_list.get(i).setSql_medical_id(sql_key);
                        medical_list.get(i).setFlag(4);
                        Log.d("add_medical_medical_sql", String.valueOf(medical_list.get(i).getSql_medical_id()));
                        Log.d("add_medical_medical_id", String.valueOf(medical_list.get(i).getMedical_id()));
                        int j = 0;
                        for (j = 0; j < cicle_list.size(); j++) {
                            if (cicle_list.get(j).getCicle_id() == medical_list.get(i).getCicle_id()) {
                                medical_list.get(i).setSql_cicle_id(cicle_list.get(j).getSql_cicle_id());
                                Log.d("ADd_medical_cicle_sql", "add_medical_cicle_sql");
                                Log.d("medical_sql_cicle_id", String.valueOf(medical_list.get(i).getSql_cicle_id()));
                                Log.d("medical_cicle_d", String.valueOf(medical_list.get(i).getCicle_id()));
                                medical_list.get(i).setFlag(1);
                            }
                        }
                        for (j = 0; j < building_list.size(); j++) {
                            if (building_list.get(j).getBuilding_id() == medical_list.get(i).getBuilding_id()) {
                                medical_list.get(i).setSql_building_id(building_list.get(j).getSql_building_id());
                                Log.d("add_medical_build_sql", "add_medical_build_sql");
                                Log.d("medical_building_Sql_id", String.valueOf(medical_list.get(i).getSql_building_id()));
                                Log.d("medical_building_id", String.valueOf(medical_list.get(i).getSql_building_id()));
                                medical_list.get(i).setFlag(1);
                                handler.updateMedical(medical_list.get(i));
                            }
                        }
                    }
                }
                if(medical_list.get(i).getFlag()!=4){
                    man_size=man_size+1;
                }
                Log.d(String.valueOf("medical_after_flag::" + medical_list.get(i).getMedical_id()), String.valueOf(medical_list.get(i).getFlag()));

            }

            Gson gson = new Gson();
            for (i = 0; i < new_arrival.length() && new_arrival.length() > 0; i++) {
                JSONObject jobj = new_arrival.getJSONObject(i);
                String json = jobj.toString().replace("\\", "");
                Log.d("json_parse_medical", json);
                medical_ medical = gson.fromJson(json, medical_.class);
                cicle cicle = handler.get_sql_cicle(medical.getSql_cicle_id());
                building_class build = handler.getBuildingBYSQL(medical.getSql_building_id());
                if(cicle!=null && build!=null) {
                    medical.setCicle_id(cicle.getCicle_id());
                    medical.setBuilding_id(build.getBuilding_id());
                    Log.d("jp_medical_medical_sql", String.valueOf(medical.getSql_medical_id()));
                    Log.d("jp_medical_ciclesql", String.valueOf(medical.getSql_cicle_id()));
                    Log.d("jp_medical_buildsql", String.valueOf(medical.getSql_building_id()));
                    Log.d("jp_medical_cicle_id", String.valueOf(medical.getCicle_id()));
                    Log.d("jp_medical_build_id", String.valueOf(medical.getBuilding_id()));
                    if (medical.getFlag() != -1) {
                        medical.setFlag(1);
                    } else {
                        medical.setFlag(-1);
                    }
                    if (handler.getMedicalBySql(medical.getSql_medical_id()) == null) {
                        handler.createMedical(medical);
                    } else {
                        handler.updateMedical(medical);
                    }
                    if (medical.getFlag() != 4) {
                        man_size = man_size + 1;
                    }
                }
                Log.d("jp_medical_new_flag", String.valueOf(medical.getFlag()));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void food_json(JSONArray food_json){
        JSONObject keys = null;
        int i = 0;
        JSONArray new_arrival;
        Log.d("In food","In food");
        try {
            new_arrival=food_json.getJSONArray(0);
            if(food_json.get(1) instanceof JSONArray)
                keys=null;
            if(food_json.get(1) instanceof  JSONObject)
                keys=food_json.getJSONObject(1);

            for(i=0;i<food_list.size() && keys!=null;i++){
                Log.d(String.valueOf(food_list.get(i).getFood_id()), String.valueOf(food_list.get(i).getFood_id()));
                Log.d(String.valueOf("food_before_flag::"+food_list.get(i).getFood_id()), String.valueOf(food_list.get(i).getFlag()));
                if(keys.has(String.valueOf(food_list.get(i).getFood_id())) == true){
                    int sql_key = keys.getInt(String.valueOf(food_list.get(i).getFood_id()));
                    Log.d("food_key_value", String.valueOf(sql_key));
                    if (sql_key < 0) {
                        if (food_list.get(i).getFlag() == -1 && sql_key == -2) {
                            Log.d("parse_deleting_food", "deletinh");
                            handler.deleteFood(food_list.get(i));
                        }
                        if (food_list.get(i).getFlag() == 1 && sql_key == -5) {
                            Log.d("parse_update_food", "updating");
                            food_list.get(i).setFlag(4);
                            handler.updateFood(food_list.get(i));
                        }
                    }
                    else{
                        Log.d("adding_food", "adding_food");
                        food_list.get(i).setSql_food_id(sql_key);
                        food_list.get(i).setFlag(4);
                        Log.d("add_food_food_sql", String.valueOf(food_list.get(i).getSql_food_id()));
                        Log.d("add_food_food_id", String.valueOf(food_list.get(i).getFood_id()));
                        int j ;
                        for (j = 0; j < cicle_list.size(); j++) {
                            if (cicle_list.get(j).getCicle_id() == food_list.get(i).getCicle_id()) {
                                food_list.get(i).setSql_cicle_id(cicle_list.get(j).getSql_cicle_id());
                                Log.d("ADd_food_cicle_sql","add_food_cicle_sql");
                                Log.d("food_sql_cicle_id", String.valueOf(food_list.get(i).getSql_cicle_id()));
                                Log.d("food_cicle_d", String.valueOf(food_list.get(i).getCicle_id()));
                                food_list.get(i).setFlag(1);
                            }
                        }
                        for(j=0;j<building_list.size();j++){
                            if(building_list.get(j).getBuilding_id() == food_list.get(i).getBuilding_id()){
                                food_list.get(i).setSql_building_id(building_list.get(j).getSql_building_id());
                                Log.d("add_food_build_sql","add_food_build_sql");
                                Log.d("food_building_Sql_id", String.valueOf(food_list.get(i).getSql_building_id()));
                                Log.d("food_building_id", String.valueOf(food_list.get(i).getSql_building_id()));
                                food_list.get(i).setFlag(1);
                                handler.updateFood(food_list.get(i));
                            }
                        }
                    }
                }
                if(food_list.get(i).getFlag()!=4){
                    man_size=man_size+1;
                }
                Log.d(String.valueOf("food_after_flag::"+food_list.get(i).getFood_id()), String.valueOf(food_list.get(i).getFlag()));

            }

            Gson gson=new Gson();
            for(i=0;i<new_arrival.length() && new_arrival.length()>0;i++){
                JSONObject jobj=new_arrival.getJSONObject(i);
                String json=jobj.toString().replace("\\", "");
                Log.d("json_parse_food",json);
                food_class food=gson.fromJson(json,food_class.class);
                cicle cicle=handler.get_sql_cicle(food.getSql_cicle_id());
                building_class build=handler.getBuildingBYSQL(food.getSql_building_id());
                if(cicle!=null && build!=null) {
                    food.setCicle_id(cicle.getCicle_id());
                    food.setBuilding_id(build.getBuilding_id());
                    Log.d("jp_food_food_sql", String.valueOf(food.getSql_food_id()));
                    Log.d("jp_food_ciclesql", String.valueOf(food.getSql_cicle_id()));
                    Log.d("jp_food_buildsql", String.valueOf(food.getSql_building_id()));
                    Log.d("jp_food_cicle_id", String.valueOf(food.getCicle_id()));
                    Log.d("jp_food_build_id", String.valueOf(food.getBuilding_id()));
                    if (food.getFlag() != -1) {
                        food.setFlag(1);
                    } else {
                        food.setFlag(-1);
                    }
                    if (handler.getFoodBYSQL(food.getSql_food_id()) == null) {
                        handler.createFood(food);
                    } else {
                        handler.updateFood(food);
                    }
                    if (food.getFlag() != 4) {
                        man_size = man_size + 1;
                    }
                }
                Log.d("jp_food_new_flag",String.valueOf(food.getFlag()));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void consommation_json(JSONArray consommation_json){
        JSONObject keys = null;
        int i = 0;
        JSONArray new_arrival;
        Log.d("In consommation","In consommation");
        try {
            new_arrival=consommation_json.getJSONArray(0);
            if(consommation_json.get(1) instanceof JSONArray)
                keys=null;
            if(consommation_json.get(1) instanceof  JSONObject)
                keys=consommation_json.getJSONObject(1);

            for(i=0;i<consommation_list.size() && keys!=null;i++){
                Log.d(String.valueOf(consommation_list.get(i).getConsommation_id()), String.valueOf(consommation_list.get(i).getConsommation_id()));
                Log.d(String.valueOf("consommation_before_flag::"+consommation_list.get(i).getConsommation_id()), String.valueOf(consommation_list.get(i).getFlag()));
                if(keys.has(String.valueOf(consommation_list.get(i).getConsommation_id())) == true){
                    int sql_key = keys.getInt(String.valueOf(consommation_list.get(i).getConsommation_id()));
                    Log.d("consommation_key_value", String.valueOf(sql_key));
                    if (sql_key < 0) {
                        if (consommation_list.get(i).getFlag() == -1 && sql_key == -2) {
                            Log.d("parse_deleting_con", "deletinh");
                            handler.deleteCon(consommation_list.get(i));
                        }
                        if (consommation_list.get(i).getFlag() == 1 && sql_key == -5) {
                            Log.d("parse_update_con", "updating");
                            consommation_list.get(i).setFlag(4);
                            handler.updateCon(consommation_list.get(i));
                        }
                    }
                    else{
                        Log.d("adding_consommation", "adding_consommation");
                        consommation_list.get(i).setSql_consommation_id(sql_key);
                        consommation_list.get(i).setFlag(4);
                        Log.d("add_con_con_sql", String.valueOf(consommation_list.get(i).getConsommation_id()));
                        Log.d("add_con_con_id", String.valueOf(consommation_list.get(i).getConsommation_id()));
                        int j = 0;
                        for (j = 0; j < cicle_list.size(); j++) {
                            if (cicle_list.get(j).getCicle_id() == consommation_list.get(i).getCicle_id()) {
                                consommation_list.get(i).setSql_cicle_id(cicle_list.get(j).getSql_cicle_id());
                                Log.d("ADd_con_cicle_sql","add_consommation_cicle_sql");
                                Log.d("con_sql_cicle_id", String.valueOf(consommation_list.get(i).getSql_cicle_id()));
                                Log.d("con_cicle_d", String.valueOf(consommation_list.get(i).getCicle_id()));
                                consommation_list.get(i).setFlag(1);
                            }
                        }
                        for(j=0;j<building_list.size();j++){
                            if(building_list.get(j).getBuilding_id()==consommation_list.get(i).getBuilding_id()){
                                consommation_list.get(i).setSql_building_id(building_list.get(j).getSql_building_id());
                                Log.d("add_con_build_sql","add_consommation_build_sql");
                                Log.d("con_building_Sql_id", String.valueOf(consommation_list.get(i).getSql_building_id()));
                                Log.d("con_building_id", String.valueOf(consommation_list.get(i).getSql_building_id()));
                                consommation_list.get(i).setFlag(1);
                                handler.updateCon(consommation_list.get(i));
                            }
                        }
                    }
                }
                if(consommation_list.get(i).getFlag()!=4){
                    man_size=man_size+1;
                }
                Log.d(String.valueOf("consommation_after_flag::"+consommation_list.get(i).getConsommation_id()), String.valueOf(consommation_list.get(i).getFlag()));

            }

            Gson gson=new Gson();
            for(i=0;i<new_arrival.length() && new_arrival.length()>0;i++){
                JSONObject jobj=new_arrival.getJSONObject(i);
                String json=jobj.toString().replace("\\", "");
                Log.d("json_parse_consommation",json);
                consommation_ consommation=gson.fromJson(json,consommation_.class);
                cicle cicle=handler.get_sql_cicle(consommation.getSql_cicle_id());
                building_class build=handler.getBuildingBYSQL(consommation.getSql_building_id());
                if(cicle!=null && build!=null){
                    consommation.setCicle_id(cicle.getCicle_id());
                    consommation.setBuilding_id(build.getBuilding_id());
                    Log.d("jp_con_consommation_sql", String.valueOf(consommation.getSql_cicle_id()));
                    Log.d("jp_con_ciclesql", String.valueOf(consommation.getSql_cicle_id()));
                    Log.d("jp_con_buildsql", String.valueOf(consommation.getSql_building_id()));
                    Log.d("jp_con_cicle_id", String.valueOf(consommation.getCicle_id()));
                    Log.d("jp_con_build_id", String.valueOf(consommation.getBuilding_id()));
                    if (consommation.getFlag() != -1) {
                        consommation.setFlag(1);
                    } else {
                        consommation.setFlag(-1);
                    }
                    if (handler.getConBySQL(consommation.getSql_consommation_id()) == null) {
                        handler.createCon(consommation);
                    } else {
                        handler.updateCon(consommation);
                    }
                    if (consommation.getFlag() != 4) {
                        man_size = man_size + 1;
                    }
                }
                Log.d("jp_con_new_flag",String.valueOf(consommation.getFlag()));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void animal_json(JSONArray animal_json){
        JSONObject keys = null;
        int i = 0;
        JSONArray new_arrival;
        Log.d("In animal","In animal");
        try {
            new_arrival=animal_json.getJSONArray(0);
            if(animal_json.get(1) instanceof JSONArray)
                keys=null;
            if(animal_json.get(1) instanceof  JSONObject)
                keys=animal_json.getJSONObject(1);

            for (i=0;i<animal_list.size() && keys!=null;i++){
                Log.d(String.valueOf(animal_list.get(i).getAnimal_id()), String.valueOf(animal_list.get(i).getAnimal_id()));
                Log.d(String.valueOf("animal_before_flag::"+animal_list.get(i).getAnimal_id()), String.valueOf(animal_list.get(i).getFlag()));
                if(keys.has(String.valueOf(animal_list.get(i).getAnimal_id())) == true){
                    int sql_key = keys.getInt(String.valueOf(animal_list.get(i).getAnimal_id()));
                    Log.d("animal_key_value", String.valueOf(sql_key));
                    if (sql_key < 0) {
                        if (animal_list.get(i).getFlag() == -1 && sql_key == -2) {
                            Log.d("parse_deleting_animal", "deletinh");
                            handler.deletAnimal(animal_list.get(i));
                        }
                        if (animal_list.get(i).getFlag() == 1 && sql_key == -5) {
                            Log.d("parse_update_animal", "updating");
                            animal_list.get(i).setFlag(4);
                            handler.updateAnimal(animal_list.get(i));
                        }
                    }
                    else{
                        Log.d("adding_animal", "adding_animal");
                        animal_list.get(i).setSql_animal_id(sql_key);
                        animal_list.get(i).setFlag(4);
                        Log.d("add_animal_animal_sql", String.valueOf(animal_list.get(i).getSql_animal_id()));
                        Log.d("add_animal_animal_id", String.valueOf(animal_list.get(i).getAnimal_id()));
                        int j ;
                        for (j = 0; j < cicle_list.size(); j++) {
                            if (cicle_list.get(j).getCicle_id() == animal_list.get(i).getCicle_id()) {
                                animal_list.get(i).setSql_cicle_id(cicle_list.get(j).getSql_cicle_id());
                                Log.d("ADd_animal_cicle_sql","add_animal_cicle_sql");
                                Log.d("animal_sql_cicle_id", String.valueOf(animal_list.get(i).getSql_cicle_id()));
                                Log.d("animal_cicle_d", String.valueOf(animal_list.get(i).getCicle_id()));
                                animal_list.get(i).setFlag(1);
                            }
                        }
                        for(j=0;j<building_list.size();j++){
                            if(building_list.get(j).getBuilding_id()==animal_list.get(i).getBuilding_id()){
                                animal_list.get(i).setSql_building_id(building_list.get(j).getSql_building_id());
                                Log.d("add_animal_build_sql","add_animal_build_sql");
                                Log.d("animal_building_Sql_id", String.valueOf(animal_list.get(i).getSql_building_id()));
                                Log.d("animal_building_id", String.valueOf(animal_list.get(i).getSql_building_id()));
                                animal_list.get(i).setFlag(1);
                                handler.updateAnimal(animal_list.get(i));
                            }
                        }
                    }
                }
                if(animal_list.get(i).getFlag()!=4){
                    man_size=man_size+1;
                }
                Log.d(String.valueOf("animal_after_flag::"+animal_list.get(i).getAnimal_id()), String.valueOf(animal_list.get(i).getFlag()));

            }

            Gson gson=new Gson();
            for(i=0;i<new_arrival.length() && new_arrival.length()>0;i++){
                JSONObject jobj=new_arrival.getJSONObject(i);
                String json=jobj.toString().replace("\\", "");
                Log.d("json_parse_animal",json);
                animal_class animal=gson.fromJson(json,animal_class.class);
                cicle cicle=handler.get_sql_cicle(animal.getSql_cicle_id());
                building_class build=handler.getBuildingBYSQL(animal.getSql_building_id());
                if(cicle!=null && build!=null) {
                    animal.setCicle_id(cicle.getCicle_id());
                    animal.setBuilding_id(build.getBuilding_id());
                    Log.d("jp_animal_animal_sql", String.valueOf(animal.getSql_animal_id()));
                    Log.d("jp_animal_ciclesql", String.valueOf(animal.getSql_cicle_id()));
                    Log.d("jp_animal_buildsql", String.valueOf(animal.getSql_building_id()));
                    Log.d("jp_animal_cicle_id", String.valueOf(animal.getCicle_id()));
                    Log.d("jp_animal_build_id", String.valueOf(animal.getBuilding_id()));
                    if (animal.getFlag() != -1) {
                        animal.setFlag(1);
                    } else {
                        animal.setFlag(-1);
                    }
                    if (handler.getAnimalBYSQL(animal.getSql_animal_id()) == null) {
                        handler.createAnimal(animal);
                    } else {
                        handler.updateAnimal(animal);
                    }
                    if (animal.getFlag() != 4) {
                        man_size = man_size + 1;
                    }
                }
                Log.d("jp_animal_new_flag",String.valueOf(animal.getFlag()));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void death_json(JSONArray death_json){
        JSONObject keys = null;
        int i = 0;
        JSONArray new_arrival;
        Log.d("In death","In death");
        try {
            new_arrival=death_json.getJSONArray(0);
            if(death_json.get(1) instanceof JSONArray)
                keys=null;
            if(death_json.get(1) instanceof  JSONObject)
                keys=death_json.getJSONObject(1);

            for(i=0;i<death_list.size() && keys!=null;i++){
                Log.d(String.valueOf(death_list.get(i).getDeath_id()), String.valueOf(death_list.get(i).getDeath_id()));
                Log.d(String.valueOf("death_before_flag::"+death_list.get(i).getDeath_id()), String.valueOf(death_list.get(i).getFlag()));
                if(keys.has(String.valueOf(death_list.get(i).getDeath_id())) == true){
                    int sql_key = keys.getInt(String.valueOf(death_list.get(i).getDeath_id()));
                    Log.d("death_key_value", String.valueOf(sql_key));
                    if (sql_key < 0) {
                        if (death_list.get(i).getFlag() == -1 && sql_key == -2) {
                            Log.d("parse_deleting_death", "deletinh");
                            handler.deleteDeath(death_list.get(i));
                        }
                        if (death_list.get(i).getFlag() == 1 && sql_key == -5) {
                            Log.d("parse_update_death", "updating");
                            death_list.get(i).setFlag(4);
                            handler.updateDeath(death_list.get(i));
                        }
                    }
                    else{
                        Log.d("adding_death", "adding_death");
                        death_list.get(i).setSql_death_id(sql_key);
                        death_list.get(i).setFlag(4);
                        Log.d("add_death_death_sql", String.valueOf(death_list.get(i).getSql_death_id()));
                        Log.d("add_death_death_id", String.valueOf(death_list.get(i).getDeath_id()));
                        int j ;
                        for (j = 0; j < cicle_list.size(); j++) {
                            if (cicle_list.get(j).getCicle_id() == death_list.get(i).getCicle_id()) {
                                death_list.get(i).setSql_cicle_id(cicle_list.get(j).getSql_cicle_id());
                                Log.d("ADd_death_cicle_sql","add_death_cicle_sql");
                                Log.d("death_sql_cicle_id", String.valueOf(death_list.get(i).getSql_cicle_id()));
                                Log.d("death_cicle_d", String.valueOf(death_list.get(i).getCicle_id()));
                                death_list.get(i).setFlag(1);
                            }
                        }
                        for(j=0;j<building_list.size();j++){
                            if(building_list.get(j).getBuilding_id()==death_list.get(i).getBuilding_id()){
                                death_list.get(i).setSql_building_id(building_list.get(j).getSql_building_id());
                                Log.d("add_death_build_sql","add_death_build_sql");
                                Log.d("death_building_Sql_id", String.valueOf(death_list.get(i).getSql_building_id()));
                                Log.d("death_building_id", String.valueOf(death_list.get(i).getSql_building_id()));
                                death_list.get(i).setFlag(1);
                                handler.updateDeath(death_list.get(i));
                            }
                        }
                    }
                }
                if(death_list.get(i).getFlag()!=4){
                    man_size=man_size+1;
                }
                Log.d(String.valueOf("death_after_flag::"+death_list.get(i).getDeath_id()), String.valueOf(death_list.get(i).getFlag()));

            }

            Gson gson=new Gson();
            for(i=0;i<new_arrival.length() && new_arrival.length()>0;i++){
                JSONObject jobj=new_arrival.getJSONObject(i);
                String json=jobj.toString().replace("\\", "");
                Log.d("json_parse_death",json);
                death_ death=gson.fromJson(json,death_.class);
                cicle cicle=handler.get_sql_cicle(death.getSql_cicle_id());
                building_class build=handler.getBuildingBYSQL(death.getSql_building_id());
                if(cicle!=null && build!=null) {
                    death.setCicle_id(cicle.getCicle_id());
                    death.setBuilding_id(build.getBuilding_id());
                    Log.d("jp_death_death_sql", String.valueOf(death.getSql_death_id()));
                    Log.d("jp_death_ciclesql", String.valueOf(death.getSql_cicle_id()));
                    Log.d("jp_death_buildsql", String.valueOf(death.getSql_building_id()));
                    Log.d("jp_death_cicle_id", String.valueOf(death.getCicle_id()));
                    Log.d("jp_death_build_id", String.valueOf(death.getBuilding_id()));
                    if (death.getFlag() != -1) {
                        death.setFlag(1);
                    } else {
                        death.setFlag(-1);
                    }
                    if (handler.getDeathBySql(death.getSql_death_id()) == null) {
                        handler.createDeath(death);
                    } else {
                        handler.updateDeath(death);
                    }
                    if (death.getFlag() != 4) {
                        man_size = man_size + 1;
                    }
                }
                Log.d("jp_death_new_flag",String.valueOf(death.getFlag()));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void returned_expense_json(JSONArray returned_expense_json){
        JSONObject keys = null;
        int i = 0;
        JSONArray new_arrival;
        Log.d("In returned_expense","In returned_expense");
        try {
            new_arrival=returned_expense_json.getJSONArray(0);
            if(returned_expense_json.get(1) instanceof JSONArray)
                keys=null;
            if(returned_expense_json.get(1) instanceof  JSONObject)
                keys=returned_expense_json.getJSONObject(1);

            for(i=0;i<returned_expense_list.size() && keys!=null;i++){
                Log.d(String.valueOf(returned_expense_list.get(i).getReturned_expense_id()), String.valueOf(returned_expense_list.get(i).getReturned_expense_id()));
                Log.d(String.valueOf("re_before_flag::"+returned_expense_list.get(i).getReturned_expense_id()), String.valueOf(returned_expense_list.get(i).getFlag()));
                if(keys.has(String.valueOf(returned_expense_list.get(i).getReturned_expense_id())) == true) {
                    int sql_key = keys.getInt(String.valueOf(returned_expense_list.get(i).getReturned_expense_id()));
                    Log.d("re_key_value", String.valueOf(sql_key));
                    if (sql_key < 0) {
                        if (returned_expense_list.get(i).getFlag() == -1 && sql_key == -2) {
                            Log.d("parse_deleting_re", "deletinh");
                            handler.deleteReturnedExpense(returned_expense_list.get(i));
                        }
                        if (returned_expense_list.get(i).getFlag() == 1 && sql_key == -5) {
                            Log.d("parse_update_re", "updating");
                            returned_expense_list.get(i).setFlag(4);
                            handler.updateReturnedExpense(returned_expense_list.get(i));
                        }
                    }
                    else{
                        Log.d("adding_returned_expense", "adding_returned_expense");
                        returned_expense_list.get(i).setSql_returned_expense_id(sql_key);
                        returned_expense_list.get(i).setFlag(4);
                        Log.d("add_re_re_sql", String.valueOf(returned_expense_list.get(i).getSql_returned_expense_id()));
                        Log.d("add_re_re_id", String.valueOf(returned_expense_list.get(i).getReturned_expense_id()));
                        int j ;
                        for (j = 0; j < cicle_list.size(); j++) {
                            if (cicle_list.get(j).getCicle_id() == returned_expense_list.get(i).getCicle_id()) {
                                returned_expense_list.get(i).setSql_cicle_id(cicle_list.get(j).getSql_cicle_id());
                                Log.d("ADd_re_cicle_sql","add_returned_expense_cicle_sql");
                                Log.d("re_sql_cicle_id", String.valueOf(returned_expense_list.get(i).getSql_cicle_id()));
                                Log.d("re_cicle_d", String.valueOf(returned_expense_list.get(i).getCicle_id()));
                                returned_expense_list.get(i).setFlag(1);
                            }
                        }
                        for(j=0;j<building_list.size();j++){
                            if(building_list.get(j).getBuilding_id()==returned_expense_list.get(i).getBuilding_id()){
                                returned_expense_list.get(i).setSql_building_id(building_list.get(j).getSql_building_id());
                                Log.d("add_re_build_sql","add_returned_expense_build_sql");
                                Log.d("re_building_Sql_id", String.valueOf(returned_expense_list.get(i).getSql_building_id()));
                                Log.d("re_building_id", String.valueOf(returned_expense_list.get(i).getSql_building_id()));
                                returned_expense_list.get(i).setFlag(1);
                                handler.updateReturnedExpense(returned_expense_list.get(i));
                            }
                        }
                    }
                }
                if(returned_expense_list.get(i).getFlag()!=4){
                    man_size=man_size+1;
                }
                Log.d(String.valueOf("returned_expense_after_flag::"+returned_expense_list.get(i).getReturned_expense_id()), String.valueOf(returned_expense_list.get(i).getFlag()));

            }

            Gson gson=new Gson();
            for(i=0;i<new_arrival.length() && new_arrival.length()>0;i++){
                JSONObject jobj=new_arrival.getJSONObject(i);
                String json=jobj.toString().replace("\\", "");
                Log.d("json_parse_re",json);
                returned_expense_class returned_expense=gson.fromJson(json,returned_expense_class.class);
                cicle cicle=handler.get_sql_cicle(returned_expense.getSql_cicle_id());
                building_class build=handler.getBuildingBYSQL(returned_expense.getSql_building_id());
                if(cicle!=null && build!=null) {
                    returned_expense.setCicle_id(cicle.getCicle_id());
                    returned_expense.setBuilding_id(build.getBuilding_id());
                    Log.d("jp_re_rse_sql", String.valueOf(returned_expense.getSql_returned_expense_id()));
                    Log.d("jp_re_ciclesql", String.valueOf(returned_expense.getSql_cicle_id()));
                    Log.d("jp_re_buildsql", String.valueOf(returned_expense.getSql_building_id()));
                    Log.d("jp_re_cicle_id", String.valueOf(returned_expense.getCicle_id()));
                    Log.d("jp_re_build_id", String.valueOf(returned_expense.getBuilding_id()));
                    if (returned_expense.getFlag() != -1) {
                        returned_expense.setFlag(1);
                    } else {
                        returned_expense.setFlag(-1);
                    }
                    if (handler.getReturnedExpenseBySql(returned_expense.getSql_returned_expense_id()) == null) {
                        handler.createReturnedExpense(returned_expense);
                    } else {
                        handler.updateReturnedExpense(returned_expense);
                    }
                    if (returned_expense.getFlag() != 4) {
                        man_size = man_size + 1;
                    }
                }
                Log.d("jp_rse_new_flag",String.valueOf(returned_expense.getFlag()));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }




    public void additional_expense_json(JSONArray additional_expense_json){
        JSONObject keys = null;
        int i = 0;
        JSONArray new_arrival;
        Log.d("In additional_expense","In additional_expense");
        try {
            new_arrival=additional_expense_json.getJSONArray(0);
            if(additional_expense_json.get(1) instanceof JSONArray)
                keys=null;
            if(additional_expense_json.get(1) instanceof  JSONObject)
                keys=additional_expense_json.getJSONObject(1);

            for(i=0;i<additional_expense_list.size() && keys!=null;i++){
                Log.d(String.valueOf(additional_expense_list.get(i).getAdditional_expense_id()), String.valueOf(additional_expense_list.get(i).getAdditional_expense_id()));
                Log.d(String.valueOf("re_before_flag::"+additional_expense_list.get(i).getAdditional_expense_id()), String.valueOf(additional_expense_list.get(i).getFlag()));
                if(keys.has(String.valueOf(additional_expense_list.get(i).getAdditional_expense_id())) == true){
                    int sql_key = keys.getInt(String.valueOf(additional_expense_list.get(i).getAdditional_expense_id()));
                    Log.d("ae_key_value", String.valueOf(sql_key));
                    if (sql_key < 0) {
                        if (additional_expense_list.get(i).getFlag() == -1 && sql_key == -2) {
                            Log.d("parse_deleting_ae", "deletinh");
                            handler.deleteAdditionalExpense(additional_expense_list.get(i));
                        }
                        if (additional_expense_list.get(i).getFlag() == 1 && sql_key == -5) {
                            Log.d("parse_update_ae", "updating");
                            additional_expense_list.get(i).setFlag(4);
                            handler.updateAdditionalExpense(additional_expense_list.get(i));
                        }
                    }
                    else{
                        Log.d("adding_ae", "adding_additional_expense");
                        additional_expense_list.get(i).setSql_additional_expense_id(sql_key);
                        additional_expense_list.get(i).setFlag(4);
                        Log.d("add_re_ae_sql", String.valueOf(additional_expense_list.get(i).getSql_additional_expense_id()));
                        Log.d("add_re_ae_id", String.valueOf(additional_expense_list.get(i).getAdditional_expense_id()));
                        int j ;
                        for (j = 0; j < cicle_list.size(); j++) {
                            if (cicle_list.get(j).getCicle_id() == additional_expense_list.get(i).getCicle_id()) {
                                additional_expense_list.get(i).setSql_cicle_id(cicle_list.get(j).getSql_cicle_id());
                                Log.d("ADd_re_cicle_sql","add_additional_expense_cicle_sql");
                                Log.d("re_sql_cicle_id", String.valueOf(additional_expense_list.get(i).getSql_cicle_id()));
                                Log.d("re_cicle_d", String.valueOf(additional_expense_list.get(i).getCicle_id()));
                                additional_expense_list.get(i).setFlag(1);
                            }
                        }
                        for(j=0;j<building_list.size();j++){
                            if(building_list.get(j).getBuilding_id()==additional_expense_list.get(i).getBuilding_id()){
                                additional_expense_list.get(i).setSql_building_id(building_list.get(j).getSql_building_id());
                                Log.d("add_re_build_sql","add_additional_expense_build_sql");
                                Log.d("re_building_Sql_id", String.valueOf(additional_expense_list.get(i).getSql_building_id()));
                                Log.d("re_building_id", String.valueOf(additional_expense_list.get(i).getSql_building_id()));
                                additional_expense_list.get(i).setFlag(1);
                                handler.updateAdditionalExpense(additional_expense_list.get(i));
                            }
                        }
                    }
                }
                if(additional_expense_list.get(i).getFlag()!=4){
                    man_size=man_size+1;
                }
                Log.d(String.valueOf("additional_expense_after_flag::"+additional_expense_list.get(i).getAdditional_expense_id()), String.valueOf(additional_expense_list.get(i).getFlag()));

            }

            Gson gson=new Gson();
            for(i=0;i<new_arrival.length() && new_arrival.length()>0;i++){
                JSONObject jobj=new_arrival.getJSONObject(i);
                String json=jobj.toString().replace("\\", "");
                Log.d("json_parse_ase",json);
                additional_expense_class additional_expense=gson.fromJson(json,additional_expense_class.class);
                cicle cicle=handler.get_sql_cicle(additional_expense.getSql_cicle_id());
                building_class build=handler.getBuildingBYSQL(additional_expense.getSql_building_id());
                if(cicle!=null && build!=null) {
                    additional_expense.setCicle_id(cicle.getCicle_id());
                    additional_expense.setBuilding_id(build.getBuilding_id());
                    Log.d("jp_ae_ae_sql", String.valueOf(additional_expense.getSql_additional_expense_id()));
                    Log.d("jp_ae_ciclesql", String.valueOf(additional_expense.getSql_cicle_id()));
                    Log.d("jp_ae_buildsql", String.valueOf(additional_expense.getSql_building_id()));
                    Log.d("jp_ae_cicle_id", String.valueOf(additional_expense.getCicle_id()));
                    Log.d("jp_ae_build_id", String.valueOf(additional_expense.getBuilding_id()));
                    if (additional_expense.getFlag() != -1) {
                        additional_expense.setFlag(1);
                    } else {
                        additional_expense.setFlag(-1);
                    }
                    if (handler.getAdditionalExpenseBySql(additional_expense.getSql_additional_expense_id()) == null) {
                        Log.d("Adding Ae","adding_ae");
                        handler.createAdditionalExpense(additional_expense);
                    } else {
                        Log.d("updating_ae","updating_ae");
                        handler.updateAdditionalExpense(additional_expense);
                    }
                    if (additional_expense.getFlag() != 4) {
                        man_size = man_size + 1;
                    }
                }
                Log.d("jp_rse_new_flag", String.valueOf(additional_expense.getFlag()));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void down_payment_json(JSONArray down_payment_json){
        JSONObject keys = null;
        int i = 0;
        JSONArray new_arrival;
        Log.d("In down_payment","In down_payment");
        try {
            new_arrival=down_payment_json.getJSONArray(0);
            if(down_payment_json.get(1) instanceof JSONArray)
                keys=null;
            if(down_payment_json.get(1) instanceof  JSONObject)
                keys=down_payment_json.getJSONObject(1);

            for(i=0;i<down_payment_list.size() && keys!=null;i++){
                Log.d(String.valueOf(down_payment_list.get(i).getDown_payment_id()), String.valueOf(down_payment_list.get(i).getDown_payment_id()));
                Log.d(String.valueOf("dp_before_flag::"+down_payment_list.get(i).getDown_payment_id()), String.valueOf(down_payment_list.get(i).getFlag()));
                if(keys.has(String.valueOf(down_payment_list.get(i).getDown_payment_id())) == true){
                    int sql_key = keys.getInt(String.valueOf(down_payment_list.get(i).getDown_payment_id()));
                    Log.d("down_payment_key_value", String.valueOf(sql_key));
                    if (sql_key < 0) {
                        if (down_payment_list.get(i).getFlag() == -1 && sql_key == -2) {
                            Log.d("parse_deleting_dt", "deletinh");
                            handler.deleteDownPayment(down_payment_list.get(i));
                        }
                        if (down_payment_list.get(i).getFlag() == 1 && sql_key == -5) {
                            Log.d("parse_update_dt", "updating");
                            down_payment_list.get(i).setFlag(4);
                            handler.updateDownPayment(down_payment_list.get(i));
                        }
                    }
                    else{
                        Log.d("adding_dp", "adding_down_payment");
                        down_payment_list.get(i).setSql_down_payment_id(sql_key);
                        down_payment_list.get(i).setFlag(4);
                        Log.d("add_dp_down_payment_sql", String.valueOf(down_payment_list.get(i).getSql_down_payment_id()));
                        Log.d("adddp_down_payment_id", String.valueOf(down_payment_list.get(i).getDown_payment_id()));
                        int j ;
                        for (j = 0; j < cicle_list.size(); j++) {
                            if (cicle_list.get(j).getCicle_id() == down_payment_list.get(i).getCicle_id()) {
                                down_payment_list.get(i).setSql_cicle_id(cicle_list.get(j).getSql_cicle_id());
                                Log.d("ADd_dp_cicle_sql","add_down_payment_cicle_sql");
                                Log.d("dp_sql_cicle_id", String.valueOf(down_payment_list.get(i).getSql_cicle_id()));
                                Log.d("dp_cicle_d", String.valueOf(down_payment_list.get(i).getCicle_id()));
                                down_payment_list.get(i).setFlag(1);
                            }
                        }
                        for(j=0;j<building_list.size();j++){
                            if(building_list.get(j).getBuilding_id()==down_payment_list.get(i).getBuilding_id()){
                                down_payment_list.get(i).setSql_building_id(building_list.get(j).getSql_building_id());
                                Log.d("add_dp_build_sql","add_down_payment_build_sql");
                                Log.d("dp_building_Sql_id", String.valueOf(down_payment_list.get(i).getSql_building_id()));
                                Log.d("dp_building_id", String.valueOf(down_payment_list.get(i).getSql_building_id()));
                                down_payment_list.get(i).setFlag(1);
                                handler.updateDownPayment(down_payment_list.get(i));
                            }
                        }
                    }
                }
                if(down_payment_list.get(i).getFlag()!=4){
                    man_size=man_size+1;
                }
                Log.d(String.valueOf("down_payment_after_flag::"+down_payment_list.get(i).getDown_payment_id()), String.valueOf(down_payment_list.get(i).getFlag()));

            }

            Gson gson=new Gson();
            for(i=0;i<new_arrival.length() && new_arrival.length()>0;i++){
                JSONObject jobj=new_arrival.getJSONObject(i);
                String json=jobj.toString().replace("\\", "");
                Log.d("json_parse_down_payment",json);
                down_payment_class down_payment=gson.fromJson(json,down_payment_class.class);
                cicle cicle=handler.get_sql_cicle(down_payment.getSql_cicle_id());
                building_class build=handler.getBuildingBYSQL(down_payment.getSql_building_id());
                if(cicle!=null && build!=null) {
                    down_payment.setCicle_id(cicle.getCicle_id());
                    down_payment.setBuilding_id(build.getBuilding_id());
                    Log.d("jp_dt_dt_sql", String.valueOf(down_payment.getSql_down_payment_id()));
                    Log.d("jp_dt_ciclesql", String.valueOf(down_payment.getSql_cicle_id()));
                    Log.d("jp_dt_buildsql", String.valueOf(down_payment.getSql_building_id()));
                    Log.d("jp_dt_cicle_id", String.valueOf(down_payment.getCicle_id()));
                    Log.d("jp_dt_build_id", String.valueOf(down_payment.getBuilding_id()));
                    if (down_payment.getFlag() != -1) {
                        down_payment.setFlag(1);
                    } else {
                        down_payment.setFlag(-1);
                    }
                    if (handler.getDownPaymentBySql(down_payment.getSql_down_payment_id()) == null) {
                        handler.createDownPayment(down_payment);
                    } else {
                        handler.updateDownPayment(down_payment);
                    }
                    if (down_payment.getFlag() != 4) {
                        man_size = man_size + 1;
                    }
                }
                Log.d("jp_rse_new_flag", String.valueOf(down_payment.getFlag()));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public void normalegg_json(JSONArray normalegg_json){
        JSONObject keys = null;
        int i = 0;
        JSONArray new_arrival;
        Log.d("In normalegg","In normalegg");
        try {
            new_arrival=normalegg_json.getJSONArray(0);
            if(normalegg_json.get(1) instanceof JSONArray)
                keys=null;
            if(normalegg_json.get(1) instanceof  JSONObject)
                keys=normalegg_json.getJSONObject(1);

            for(i = 0;i<normalegg_list.size() && keys!=null;i++){
                Log.d(String.valueOf(normalegg_list.get(i).getEgg_id()), String.valueOf(normalegg_list.get(i).getEgg_id()));
                Log.d(String.valueOf("normalegg_before_flag::"+normalegg_list.get(i).getEgg_id()), String.valueOf(normalegg_list.get(i).getFlag()));
                if(keys.has(String.valueOf(normalegg_list.get(i).getEgg_id())) == true){
                    int sql_key = keys.getInt(String.valueOf(normalegg_list.get(i).getEgg_id()));
                    Log.d("normalegg_key_value", String.valueOf(sql_key));
                    if (sql_key < 0) {
                        if (normalegg_list.get(i).getFlag() == -1 && sql_key == -2) {
                            Log.d("parse_deleting_ne", "deletinh");
                            handler.deleteNormalEgg(normalegg_list.get(i));
                        }
                        if (normalegg_list.get(i).getFlag() == 1 && sql_key == -5) {
                            Log.d("parse_update_normalegg", "updating");
                            normalegg_list.get(i).setFlag(4);
                            handler.updateNormalEgg(normalegg_list.get(i));
                        }
                    }
                    else{
                        Log.d("adding_normalegg", "adding_normalegg");
                        normalegg_list.get(i).setSql_normal_egg_id(sql_key);
                        normalegg_list.get(i).setFlag(4);
                        Log.d("add_ne_normalegg_sql", String.valueOf(normalegg_list.get(i).getEgg_id()));
                        Log.d("add_ne_normalegg_id", String.valueOf(normalegg_list.get(i).getEgg_id()));
                        for (int j = 0; j < cicle_list.size(); j++) {
                            if (cicle_list.get(j).getCicle_id() == normalegg_list.get(i).getCicle_id()) {
                                normalegg_list.get(i).setSql_cicle_id(cicle_list.get(j).getSql_cicle_id());
                                Log.d("ADd_normalegg_cicle_sql","add_normalegg_cicle_sql");
                                Log.d("normalegg_sql_cicle_id", String.valueOf(normalegg_list.get(i).getSql_cicle_id()));
                                Log.d("normalegg_cicle_d", String.valueOf(normalegg_list.get(i).getCicle_id()));
                                normalegg_list.get(i).setFlag(1);
                            }
                        }
                        for(int j=0;j<building_list.size();j++){
                            if(building_list.get(j).getBuilding_id()==normalegg_list.get(i).getBuilding_id()){
                                normalegg_list.get(i).setSql_building_id(building_list.get(j).getSql_building_id());
                                Log.d("add_normalegg_build_sql","add_normalegg_build_sql");
                                Log.d("ne_building_Sql_id", String.valueOf(normalegg_list.get(i).getSql_building_id()));
                                Log.d("normalegg_building_id", String.valueOf(normalegg_list.get(i).getSql_building_id()));
                                normalegg_list.get(i).setFlag(1);
                                handler.updateNormalEgg(normalegg_list.get(i));
                            }
                        }
                    }
                }
                if(normalegg_list.get(i).getFlag()!=4){
                    man_size=man_size+1;
                }
                Log.d(String.valueOf("normalegg_after_flag::" + normalegg_list.get(i).getEgg_id()), String.valueOf(normalegg_list.get(i).getEgg_id()));


            }

            Gson gson=new Gson();
            for(i=0;i<new_arrival.length() && new_arrival.length()>0;i++){
                JSONObject jobj=new_arrival.getJSONObject(i);
                String json=jobj.toString().replace("\\", "");
                Log.d("json_parse_normalegg",json);
                egg_n normalegg=gson.fromJson(json,egg_n.class);
                cicle cicle=handler.get_sql_cicle(normalegg.getSql_cicle_id());
                building_class build=handler.getBuildingBYSQL(normalegg.getSql_building_id());
                if(cicle!=null && build!=null) {
                    normalegg.setCicle_id(cicle.getCicle_id());
                    normalegg.setBuilding_id(build.getBuilding_id());
                    Log.d("jp_ne_ne_sql", String.valueOf(normalegg.getSql_normal_egg_id()));
                    Log.d("jp_normalegg_ciclesql", String.valueOf(normalegg.getSql_cicle_id()));
                    Log.d("jp_normalegg_buildsql", String.valueOf(normalegg.getSql_building_id()));
                    Log.d("jp_normalegg_cicle_id", String.valueOf(normalegg.getCicle_id()));
                    Log.d("jp_normalegg_build_id", String.valueOf(normalegg.getBuilding_id()));
                    if (normalegg.getFlag() != -1) {
                        normalegg.setFlag(1);
                    } else {
                        normalegg.setFlag(-1);
                    }
                    if (handler.getNormalEggBYSQL(normalegg.getSql_normal_egg_id()) == null) {
                        handler.createNormalEgg(normalegg);
                    } else {
                        handler.updateNormalEgg(normalegg);
                    }
                    if (normalegg.getFlag() != 4) {
                        man_size = man_size + 1;
                    }
                }
                Log .d("jp_normalegg_new_flag", String.valueOf(normalegg.getFlag()));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void brokenegg_json(JSONArray brokenegg_json){
        JSONObject keys = null;
        int i = 0;
        JSONArray new_arrival;
        Log.d("In brokenegg","In brokenegg");
        try {
            new_arrival=brokenegg_json.getJSONArray(0);
            if(brokenegg_json.get(1) instanceof JSONArray)
                keys=null;
            if(brokenegg_json.get(1) instanceof  JSONObject)
                keys=brokenegg_json.getJSONObject(1);

            for(i = 0;i<brokenegg_list.size() && keys!=null;i++){
                Log.d(String.valueOf(brokenegg_list.get(i).getEgg_id()), String.valueOf(brokenegg_list.get(i).getEgg_id()));
                Log.d(String.valueOf("brokenegg_before_flag::"+brokenegg_list.get(i).getEgg_id()), String.valueOf(brokenegg_list.get(i).getFlag()));
                if(keys.has(String.valueOf(brokenegg_list.get(i).getEgg_id())) == true){
                    int sql_key = keys.getInt(String.valueOf(brokenegg_list.get(i).getEgg_id()));
                    Log.d("brokenegg_key_value", String.valueOf(sql_key));
                    if (sql_key < 0) {
                        if (brokenegg_list.get(i).getFlag() == -1 && sql_key == -2) {
                            Log.d("parse_deleting_be", "deletinh");
                            handler.deleteBrokenEgg(brokenegg_list.get(i));
                        }
                        if (brokenegg_list.get(i).getFlag() == 1 && sql_key == -5) {
                            Log.d("parse_update_brokenegg", "updating");
                            brokenegg_list.get(i).setFlag(4);
                            handler.updateBrokenEgg(brokenegg_list.get(i));
                        }
                    }
                    else{
                        Log.d("adding_brokenegg", "adding_brokenegg");
                        brokenegg_list.get(i).setSql_broken_egg_id(sql_key);
                        brokenegg_list.get(i).setFlag(4);
                        Log.d("add_be_brokenegg_sql", String.valueOf(brokenegg_list.get(i).getEgg_id()));
                        Log.d("add_be_brokenegg_id", String.valueOf(brokenegg_list.get(i).getEgg_id()));
                        for (int j = 0; j < cicle_list.size(); j++) {
                            if (cicle_list.get(j).getCicle_id() == brokenegg_list.get(i).getCicle_id()) {
                                brokenegg_list.get(i).setSql_cicle_id(cicle_list.get(j).getSql_cicle_id());
                                Log.d("ADd_brokenegg_cicle_sql","add_brokenegg_cicle_sql");
                                Log.d("brokenegg_sql_cicle_id", String.valueOf(brokenegg_list.get(i).getSql_cicle_id()));
                                Log.d("brokenegg_cicle_d", String.valueOf(brokenegg_list.get(i).getCicle_id()));
                                brokenegg_list.get(i).setFlag(1);
                            }
                        }
                        for(int j=0;j<building_list.size();j++){
                            if(building_list.get(j).getBuilding_id()==brokenegg_list.get(i).getBuilding_id()){
                                brokenegg_list.get(i).setSql_building_id(building_list.get(j).getSql_building_id());
                                Log.d("add_brokenegg_build_sql","add_brokenegg_build_sql");
                                Log.d("ne_building_Sql_id", String.valueOf(brokenegg_list.get(i).getSql_building_id()));
                                Log.d("brokenegg_building_id", String.valueOf(brokenegg_list.get(i).getSql_building_id()));
                                brokenegg_list.get(i).setFlag(1);
                                handler.updateBrokenEgg(brokenegg_list.get(i));
                            }
                        }
                    }
                }
                if(brokenegg_list.get(i).getFlag()!=4)
                    man_size=man_size+1;

                Log.d(String.valueOf("brokenegg_after_flag::" + brokenegg_list.get(i).getEgg_id()), String.valueOf(brokenegg_list.get(i).getEgg_id()));


            }

            Gson gson=new Gson();
            for(i=0;i<new_arrival.length() && new_arrival.length()>0;i++){
                JSONObject jobj=new_arrival.getJSONObject(i);
                String json=jobj.toString().replace("\\", "");
                Log.d("json_parse_brokenegg",json);
                egg_b brokenegg=gson.fromJson(json,egg_b.class);
                cicle cicle=handler.get_sql_cicle(brokenegg.getSql_cicle_id());
                building_class build=handler.getBuildingBYSQL(brokenegg.getSql_building_id());
                if(cicle!=null && build!=null) {
                    brokenegg.setCicle_id(cicle.getCicle_id());
                    brokenegg.setBuilding_id(build.getBuilding_id());
                    Log.d("jp_be_be_sql", String.valueOf(brokenegg.getSql_broken_egg_id()));
                    Log.d("jp_brokenegg_ciclesql", String.valueOf(brokenegg.getSql_cicle_id()));
                    Log.d("jp_brokenegg_buildsql", String.valueOf(brokenegg.getSql_building_id()));
                    Log.d("jp_brokenegg_cicle_id", String.valueOf(brokenegg.getCicle_id()));
                    Log.d("jp_brokenegg_build_id", String.valueOf(brokenegg.getBuilding_id()));
                    if (brokenegg.getFlag() != -1) {
                        brokenegg.setFlag(1);
                    } else {
                        brokenegg.setFlag(-1);
                    }
                    if (handler.getBrokenEggBYSQL(brokenegg.getSql_broken_egg_id()) == null) {
                        handler.createBrokenEgg(brokenegg);
                    } else {
                        handler.updateBrokenEgg(brokenegg);
                    }
                    if (brokenegg.getFlag() != 4) {
                        man_size = man_size + 1;
                    }
                }
                Log.d("jp_brokenegg_new_flag", String.valueOf(brokenegg.getFlag()));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void temp_json(JSONArray temp_json){
        JSONObject keys = null;
        int i = 0;
        JSONArray new_arrival;
        Log.d("In temp","In temp");
        try {
            new_arrival=temp_json.getJSONArray(0);
            if(temp_json.get(1) instanceof JSONArray)
                keys=null;
            if(temp_json.get(1) instanceof  JSONObject)
                keys=temp_json.getJSONObject(1);

            for(i=0;i<temp_list.size() && keys!=null;i++){
                Log.d(String.valueOf(temp_list.get(i).getTemp_id()), String.valueOf(temp_list.get(i).getTemp_id()));
                Log.d(String.valueOf("temp_before_flag::"+temp_list.get(i).getTemp_id()), String.valueOf(temp_list.get(i).getFlag()));
                if(keys.has(String.valueOf(temp_list.get(i).getTemp_id())) == true){
                    int sql_key = keys.getInt(String.valueOf(temp_list.get(i).getTemp_id()));
                    Log.d("temp_key_value", String.valueOf(sql_key));
                    if (sql_key < 0) {
                        if (temp_list.get(i).getFlag() == -1 && sql_key == -2) {
                            Log.d("parse_deleting_temp", "deletinh");
                            handler.deleteTemp(temp_list.get(i));
                        }
                        if (temp_list.get(i).getFlag() == 1 && sql_key == -5) {
                            Log.d("parse_update_temp", "updating");
                            temp_list.get(i).setFlag(4);
                            handler.updateTemp(temp_list.get(i));
                        }
                    }
                    else{
                        Log.d("adding_temp", "adding_temp");
                        temp_list.get(i).setSql_temp_id(sql_key);
                        temp_list.get(i).setFlag(4);
                        Log.d("add_temp_temp_sql", String.valueOf(temp_list.get(i).getTemp_id()));
                        Log.d("add_temp_temp_id", String.valueOf(temp_list.get(i).getTemp_id()));
                        for (int j = 0; j < cicle_list.size(); j++) {
                            if (cicle_list.get(j).getCicle_id() == temp_list.get(i).getCicle_id()) {
                                temp_list.get(i).setSql_cicle_id(cicle_list.get(j).getSql_cicle_id());
                                Log.d("ADd_temp_cicle_sql","add_temp_cicle_sql");
                                Log.d("temp_sql_cicle_id", String.valueOf(temp_list.get(i).getSql_cicle_id()));
                                Log.d("temp_cicle_d", String.valueOf(temp_list.get(i).getCicle_id()));
                                temp_list.get(i).setFlag(1);
                            }
                        }
                        for(int j=0;j<building_list.size();j++){
                            if(building_list.get(j).getBuilding_id()==temp_list.get(i).getBuilding_id()){
                                temp_list.get(i).setSql_building_id(building_list.get(j).getSql_building_id());
                                Log.d("add_temp_build_sql","add_temp_build_sql");
                                Log.d("ne_temp_Sql_id", String.valueOf(temp_list.get(i).getSql_building_id()));
                                Log.d("temp_building_id", String.valueOf(temp_list.get(i).getSql_building_id()));
                                temp_list.get(i).setFlag(1);
                                handler.updateTemp(temp_list.get(i));
                            }
                        }
                    }
                }
                if(temp_list.get(i).getFlag()!=4)
                    man_size=man_size+1;
                Log.d(String.valueOf("temp_after_flag::" + temp_list.get(i).getTemp_id()), String.valueOf(temp_list.get(i).getTemp_id()));


            }

            Gson gson=new Gson();
            for(i=0;i<new_arrival.length() && new_arrival.length()>0;i++){
                JSONObject jobj=new_arrival.getJSONObject(i);
                String json=jobj.toString().replace("\\", "");
                Log.d("json_parse_temp",json);
                temp_ temp=gson.fromJson(json,temp_.class);
                cicle cicle=handler.get_sql_cicle(temp.getSql_cicle_id());
                building_class build=handler.getBuildingBYSQL(temp.getSql_building_id());
                if(cicle!=null && build!=null) {
                    temp.setCicle_id(cicle.getCicle_id());
                    temp.setBuilding_id(build.getBuilding_id());
                    Log.d("jp_temp_temp_sql", String.valueOf(temp.getSql_temp_id()));
                    Log.d("jp_temp_ciclesql", String.valueOf(temp.getSql_cicle_id()));
                    Log.d("jp_temp_buildsql", String.valueOf(temp.getSql_building_id()));
                    Log.d("jp_temp_cicle_id", String.valueOf(temp.getCicle_id()));
                    Log.d("jp_temp_build_id", String.valueOf(temp.getBuilding_id()));
                    if (temp.getFlag() != -1) {
                        temp.setFlag(1);
                    } else {
                        temp.setFlag(-1);
                    }
                    if (handler.getTempBYSQL(temp.getSql_temp_id()) == null) {
                        handler.createTemp(temp);
                    } else {
                        handler.updateTemp(temp);
                    }
                    if (temp.getFlag() != 4)
                        man_size = man_size + 1;
                }
                Log.d("jp_temp_new_flag", String.valueOf(temp.getFlag()));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    public void note_json(JSONArray note_json){
        JSONObject keys = null;
        int i = 0;
        JSONArray new_arrival;
        Log.d("In note","In note");
        try {
            new_arrival=note_json.getJSONArray(0);
            if(note_json.get(1) instanceof JSONArray)
                keys=null;
            if(note_json.get(1) instanceof  JSONObject)
                keys=note_json.getJSONObject(1);

            for(i=0;i<note_list.size() && keys!=null;i++){
                Log.d(String.valueOf(note_list.get(i).getId()), String.valueOf(note_list.get(i).getId()));
                Log.d(String.valueOf("note_before_flag::"+note_list.get(i).getId()), String.valueOf(note_list.get(i).getFlag()));
                if(keys.has(String.valueOf(note_list.get(i).getId())) == true){
                    int sql_key = keys.getInt(String.valueOf(note_list.get(i).getId()));
                    Log.d("note_key_value", String.valueOf(sql_key));
                    if (sql_key < 0) {
                        if (note_list.get(i).getFlag() == -1 && sql_key == -2) {
                            Log.d("parse_deleting_note", "deletinh");
                            new_handler.delete_note(note_list.get(i));
                        }
                        if (note_list.get(i).getFlag() == 1 && sql_key == -5) {
                            Log.d("parse_update_note", "updating");
                            note_list.get(i).setFlag(4);
                            new_handler.UpdateNote(note_list.get(i));
                        }
                    }
                    else{
                        Log.d("adding_note", "adding_note");
                        note_list.get(i).setSql_notes_id(sql_key);
                        note_list.get(i).setFlag(4);
                        Log.d("add_note_note_sql", String.valueOf(note_list.get(i).getId()));
                        Log.d("add_note_note_id", String.valueOf(note_list.get(i).getId()));
                        for (int j = 0; j < cicle_list.size(); j++) {
                            if (cicle_list.get(j).getCicle_id() == note_list.get(i).getCicle_id()) {
                                note_list.get(i).setSql_cicle_id(cicle_list.get(j).getSql_cicle_id());
                                Log.d("ADd_note_cicle_sql","add_note_cicle_sql");
                                Log.d("note_sql_cicle_id", String.valueOf(note_list.get(i).getSql_cicle_id()));
                                Log.d("note_cicle_d", String.valueOf(note_list.get(i).getCicle_id()));
                                note_list.get(i).setFlag(1);
                            }
                        }
                        for(int j=0;j<building_list.size();j++){
                            if(building_list.get(j).getBuilding_id()==note_list.get(i).getBuilding_id()){
                                note_list.get(i).setSql_building_id(building_list.get(j).getSql_building_id());
                                Log.d("add_note_build_sql","add_note_build_sql");
                                Log.d("ne_note_Sql_id", String.valueOf(note_list.get(i).getSql_building_id()));
                                Log.d("note_building_id", String.valueOf(note_list.get(i).getSql_building_id()));
                                note_list.get(i).setFlag(1);
                                new_handler.UpdateNote(note_list.get(i));
                            }
                        }
                    }
                }
                if(note_list.get(i).getFlag()!=4)
                    man_size=man_size+1;
                Log.d(String.valueOf("note_after_flag::" + note_list.get(i).getId()), String.valueOf(note_list.get(i).getId()));


            }

            Gson gson=new Gson();
            for(i=0;i<new_arrival.length() && new_arrival.length()>0;i++){
                JSONObject jobj=new_arrival.getJSONObject(i);
                String json=jobj.toString().replace("\\", "");
                Log.d("json_parse_note",json);
                notes_file note=gson.fromJson(json,notes_file.class);
                cicle cicle=handler.get_sql_cicle(note.getSql_cicle_id());
                building_class build=handler.getBuildingBYSQL(note.getSql_building_id());
                note.setCicle_id(cicle.getCicle_id());
                note.setBuilding_id(build.getBuilding_id());
                Log.d("jp_note_note_sql",String.valueOf(note.getSql_notes_id()));
                Log.d("jp_note_ciclesql", String.valueOf(note.getSql_cicle_id()));
                Log.d("jp_note_buildsql", String.valueOf(note.getSql_building_id()));
                Log.d("jp_note_cicle_id", String.valueOf(note.getCicle_id()));
                Log.d("jp_note_build_id", String.valueOf(note.getBuilding_id()));
                if(note.getFlag()!=-1){
                    note.setFlag(1);
                } else {
                    note.setFlag(-1);
                }
                if(new_handler.getNoteBySql(note.getSql_notes_id()) == null){
                    new_handler.CreateNote(note);
                }
                else{
                    new_handler.UpdateNote(note);
                }
                if(note.getFlag()!=4)
                    man_size=man_size+1;
                Log.d("jp_note_new_flag",String.valueOf(note.getFlag()));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void image_json(JSONArray image_json){
        JSONObject keys = null;
        int i = 0;
        JSONArray new_arrival;
        Log.d("In image","In image");
        try {
            new_arrival=image_json.getJSONArray(0);
            if(image_json.get(1) instanceof JSONArray)
                keys=null;
            if(image_json.get(1) instanceof  JSONObject)
                keys=image_json.getJSONObject(1);

            for(i=0;i<image_list.size() && keys!=null;i++){
                Log.d(String.valueOf(image_list.get(i).getImage_id()), String.valueOf(image_list.get(i).getImage_id()));
                Log.d(String.valueOf("image_before_flag::"+image_list.get(i).getImage_id()), String.valueOf(image_list.get(i).getFlag()));
                Log.d("image_before_path",image_list.get(i).getPath());
                if(keys.has(String.valueOf(image_list.get(i).getImage_id())) == true){
                    int sql_key = keys.getInt(String.valueOf(image_list.get(i).getImage_id()));
                    Log.d("image_key_value", String.valueOf(sql_key));
                    if (sql_key < 0) {
                        if (image_list.get(i).getFlag() == -1 && sql_key == -2) {
                            Log.d("delete_image_image_path",image_list.get(i).getPath());

                            Log.d("parse_deleting_image", "deletinh");
                            handler.deleteImage(image_list.get(i));
                            File file=new File(image_list.get(i).getPath());
                            file.delete();
                        }
                        if (image_list.get(i).getFlag() == 1 && sql_key == -5) {
                            Log.d("parse_update_image", "updating");
                            Log.d("update_image_image_path", image_list.get(i).getPath());
                            Log.d("update_image", "update_image");
                            Log.d("up_image_image_sql", String.valueOf(image_list.get(i).getImage_id()));
                            Log.d("up_image_image_id", String.valueOf(image_list.get(i).getImage_id()));
                            Log.d("up_image_image_path",image_list.get(i).getPath());
                            if(image_list.get(i).getSql_cicle_id()<=0 || image_list.get(i).getSql_building_id()<=0) {
                                Log.d("in_image_for_loop","im+image=foe");
                                for (int j = 0; j < cicle_list.size(); j++) {
                                    if (cicle_list.get(j).getCicle_id() == image_list.get(i).getCicle_id()) {
                                        image_list.get(i).setSql_cicle_id(cicle_list.get(j).getSql_cicle_id());
                                        Log.d("up_image_cicle_sql", "up_image_cicle_sql");
                                        Log.d("up_image_sql_cicle_id", String.valueOf(image_list.get(i).getSql_cicle_id()));
                                        Log.d("up_image_cicle_d", String.valueOf(image_list.get(i).getCicle_id()));
                                        image_list.get(i).setFlag(1);
                                    }
                                }
                                for (int j = 0; j < building_list.size(); j++) {
                                    if (image_list.get(i).getBuilding_id() == building_list.get(j).getBuilding_id()) {
                                        image_list.get(i).setSql_building_id(building_list.get(j).getSql_building_id());
                                        Log.d("up_image_build_sql", "up_image_build_sql");
                                        Log.d("up_image_Sql_id", String.valueOf(image_list.get(i).getSql_building_id()));
                                        Log.d("image_building_id", String.valueOf(image_list.get(i).getSql_building_id()));
                                        image_list.get(i).setFlag(1);
                                        handler.updateImage(image_list.get(i));
                                    }
                                }

                            }
                            else{
                                Log.d("image_image","flag_updating_image_to_4");
                                image_list.get(i).setFlag(4);
                                handler.updateImage(image_list.get(i));
                            }
                        }

                        if(image_list.get(i).getFlag()!=4){
                            man_size=man_size+1;
                            Log.d("image_man_update", String.valueOf(man_size));

                        }

                    }
                    else{
                        Log.d("adding_image", "adding_image");


                    }
                    if(image_list.get(i).getFlag()!=4){
                        man_size=man_size+1;
                        Log.d("image_man_update", String.valueOf(man_size));

                    }
                }
                Log.d("image_after_flag",image_list.get(i).getPath());
                if(image_list.get(i).getFlag()!=4)
                    man_size=man_size+1;
                Log.d(String.valueOf("image_after_path::" + image_list.get(i).getImage_id()), String.valueOf(image_list.get(i).getImage_id()));


            }

            Gson gson=new Gson();
            for(i=0;i<new_arrival.length() && new_arrival.length()>0;i++){
                JSONObject jobj=new_arrival.getJSONObject(i);
                String json=jobj.toString().replace("\\", "");
                Log.d("json_parse_image",json);
                image_class image=gson.fromJson(json,image_class.class);
                cicle cicle=handler.get_sql_cicle(image.getSql_cicle_id());
                building_class build=handler.getBuildingBYSQL(image.getSql_building_id());
                if(cicle!=null && build!=null) {
                    image.setCicle_id(cicle.getCicle_id());
                    image.setBuilding_id(build.getBuilding_id());
                    Log.d("jp_image_image_sql", String.valueOf(image.getSql_image_id()));
                    Log.d("jp_image_ciclesql", String.valueOf(image.getSql_cicle_id()));
                    Log.d("jp_image_buildsql", String.valueOf(image.getSql_building_id()));
                    Log.d("jp_image_cicle_id", String.valueOf(image.getCicle_id()));
                    Log.d("jp_image_build_id", String.valueOf(image.getBuilding_id()));
                    Log.d("jp_image_path", image.getPath());
                    if (image.getFlag() != -1) {
                        image.setFlag(1);
                    } else {
                        image.setFlag(-1);
                    }
                    if (handler.getImageBYSQL(image.getSql_image_id()) == null) {
                        image=downloadfile_url(con,image.getPath(),image);
                        if(image!=null){
                            Log.i("image_down_null_error","the downloaded image is null");
                            Log.d("image_downloaded_sql", String.valueOf(image.getSql_image_id()));
                            Log.d("image_downloaded_id",String.valueOf(image.getImage_id()));
                            Log.d("image_downloaded_path",String.valueOf(image.getPath()));
                            Log.d("image_downloaded_sql",String.valueOf(image.getSql_image_id()));
                            Log.d("image_downloaded_sql",String.valueOf(image.getSql_image_id()));

                            if(handler.getImageBYSQL(image.getSql_image_id())==null){
                                handler.createImage(image);
                            }
                        }

                    } else {
                        image_class update_image=handler.getImageBYSQL(image.getSql_image_id());
                        image.setPath(update_image.getPath());
                        image.setName(update_image.getName());
                        handler.updateImage(image);
                    }
                    if(image.getFlag()!=4) {
                        man_size = man_size + 1;
                        Log.d("image_man_create",String.valueOf(man_size));
                    }
                }
                else{
                    Log.i("image_no_root_error", "There is no cicle with sql id" + image.getSql_cicle_id() + " OR building with sql id" + image.getSql_building_id());

                }

                Log.d("jp_image_new_flag",String.valueOf(image.getFlag()));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public image_class downloadfile_url(Context context,String url,image_class image){
        class DownloadFileFromURL extends AsyncTask<image_class,image_class , image_class> {
            Context context;
            //ProgressDialog dialog;
            image_class image;
            String image_url;
            String IMAGE_DIRECTORY_NAME="CICLEAPP";
            public DownloadFileFromURL(Context con,String url,image_class image){
                context=con;
                this.image=image;
                image_url=url;
            }

            /**
             * Before starting background thread Show Progress Bar Dialog
             * */
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            /**
             * Downloading file in background thread
             * */
            @Override
            protected image_class doInBackground(image_class... f_url) {
                int count;
                int progress=0;

                try {

                    Log.d("android_studio", "In BaxkGround1");


                    // download the file

                    File file = new File(
                            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),IMAGE_DIRECTORY_NAME);

                    Log.d("android_studio","In BaxkGround2");

                    if (!file.exists()) {
                        if (!file.mkdirs()) {
                            Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                                    + IMAGE_DIRECTORY_NAME + " directory");

                        }
                    }

                    // String url1=" http://192.168.1.101/cicle/images/kjbs_6_.jpg";
                    URL url = new URL(image.getPath());
                    URLConnection conection = url.openConnection();
                    conection.setConnectTimeout(2000);
                    conection.setReadTimeout(2500);


                    Log.d("android_studio", "In BaxkGround3");
                    conection.connect();
                    String  filename=image.getName();
                    // this will be useful so that you can show a tipical 0-100%
                    // progress bar
                    int lenghtOfFile = conection.getContentLength();

                    // download the file
                    InputStream input = new BufferedInputStream(url.openStream(),
                            8192);
                    Log.d("android_studio", "In BaxkGround45");

                    // Output stream
                    OutputStream output = new FileOutputStream(file.getAbsolutePath()
                            + "/"+image.getName());

                    Log.d("filelocation",file.getAbsolutePath()+"/"+filename);

                    byte data[] = new byte[1024];

                    long total = 0;
                    while ((count = input.read(data)) != -1) {
                        total += count;
                        // publishing the progress....
                        // After this onProgressUpdate will be called
                        progress=(int) ((total * 100) / lenghtOfFile);
                        // writing data to file
                        output.write(data, 0, count);
                    }
                    Log.d("progress_download",String.valueOf(progress));
                    File file_download=new File(file.getAbsolutePath()+"/"+filename);

                    if(progress==100 && file_download.exists()){
                        Log.d("adding_image_url_db", "inserting new_element");
                        image.setPath(file.getAbsolutePath() + "/" + filename);
                        image.setName(filename);
                        image.setProgress(100);
                    }


                    // flushing output
                    output.flush();

                    // closing streams
                    output.close();
                    input.close();

                } catch (MalformedURLException e) {
                    return null;
                } catch (ProtocolException e) {
                    return null;
                } catch (IOException e) {
                    return null;
                } catch (Exception e){
                    return  null;
                }

                return image;
            }

            /**
             * Updating progress bar
             * */

            /**
             * After completing background task Dismiss the progress dialog
             * **/
            @Override
            protected void onPostExecute(image_class image) {
                // dismiss the dialog after the file was downloaded
                Log.d("image_post_id",String.valueOf(image.getImage_id()));
                Log.d("image_post_ciclesql",String.valueOf(image.getSql_cicle_id()));
                Log.d("image_post_buildsql",String.valueOf(image.getSql_building_id()));
                Log.d("image_post_imagesql",String.valueOf(image.getSql_image_id()));
                Log.d("image_post_cicle_id",String.valueOf(image.getImage_id()));
                Log.d("image_post_build_id",String.valueOf(image.getBuilding_id()));
                Log.d("image_post_name",String.valueOf(image.getName()));
                Log.d("image_post_path",String.valueOf(image.getPath()));



            }

        }

        DownloadFileFromURL downloadFileFromURL=new DownloadFileFromURL(context,url,image);
        image_class test=null;
        try {
            test= downloadFileFromURL.execute(image).get();
            if(test!=null){
                Log.d("image_apost_id",String.valueOf(image.getImage_id()));
                Log.d("image_apost_ciclesql",String.valueOf(image.getSql_cicle_id()));
                Log.d("image_apost_buildsql",String.valueOf(image.getSql_building_id()));
                Log.d("image_apost_imagesql",String.valueOf(image.getSql_image_id()));
                Log.d("image_apost_cicle_id",String.valueOf(image.getImage_id()));
                Log.d("image_apost_build_id",String.valueOf(image.getBuilding_id()));
                Log.d("image_apost_name",String.valueOf(image.getName()));
                Log.d("image_apost_path",String.valueOf(image.getPath()));

            }
        } catch (InterruptedException e) {
            e.printStackTrace();

        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return test;
    }








}