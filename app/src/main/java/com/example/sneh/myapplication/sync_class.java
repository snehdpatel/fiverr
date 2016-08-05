package com.example.sneh.myapplication;

/**
 * Created by starsilver on 3/12/15.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.ClientError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class sync_class {
    int size;
    Context context;
    ProgressDialog loading;

    /***URL TO SYNC ****/
    String url="http://176.9.206.149/cicle/parse_json.php";

    /**URL TO UPLOAD MEDIA FILES*****/
    String upLoadServerUri = "http://176.9.206.149/cicle/upload_image.php";

    List<cicle> cicle_list=new ArrayList<>();
    List<building_class> building_list=new ArrayList<>();
    List<equipment_> equip_list=new ArrayList<>();
    List<medical_> medical_list=new ArrayList<>();
    List<worker_class> worker_list=new ArrayList<>();
    List<food_class > food_list=new ArrayList<>();
    List<consommation_> con_list=new ArrayList<>();
    List<animal_class> animal_list=new ArrayList<>();
    List<death_> death_list=new ArrayList<>();
    List<returned_expense_class> returned_list=new ArrayList<>();
    List<additional_expense_class> additional_list=new ArrayList<>();
    List<down_payment_class> down_payment_list=new ArrayList<>();
    List<egg_n>normal_egg_list=new ArrayList<>();
    List<egg_b>broken_egg_list=new ArrayList<>();
    List<temp_>temp_list=new ArrayList<>();
    List<notes_file>note_list=new ArrayList<>();
    List<image_class> image_list=new ArrayList<>();
    List<task_class> task_list=new ArrayList<>();
    db_handler handler;
    DataBaseHandler new_handler;
    int user_id;
    int i=0;
    String video;
    public sync_class(Context con){
        context=con;
        handler=new db_handler(context);
        handler.onCreateTable(handler.getWritableDatabase());
        new_handler=new DataBaseHandler(context);
        new_handler.onCreateTable(handler.getWritableDatabase());
        SharedPreferences preferences=context.getSharedPreferences("setting_data",Context.MODE_PRIVATE);
        video=preferences.getString("video","no_video");
        loading=null;
    }
    public void upload_image_handler(){
        Log.d("image_file_size",String.valueOf(image_list.size()));


        if(video.equals("YES")) {
            loading = ProgressDialog.show(context, context.getResources().getString(R.string.please_wait),context.getResources().getString(R.string.uploading));
            //start a new thread to process job
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //heavy job here

                    image_list=handler.get_all_image_by_user_id(user_id);
                    Log.d("step1","entering run"+String.valueOf(image_list.size()));
                    for (int i = 0; i < image_list.size() ; i++) {
                        Log.d("step2","entering loop");
                        if (image_list.get(i).getSql_image_id() == 0) {
                            Log.d("uploading_image", "uploading_image");
                            image_class img=upload_image(context, image_list.get(i));
                            Log.d("step3","image uploaded");
                            if(img!=null){
                                handler.updateImage(img);
                                Log.d("fuck_upload::"+img.getImage_id(),String.valueOf(img.getSql_image_id())+"::"+String.valueOf(img.getFlag()) );
                            }
                        }
                    }
                    //send message to main thread
                    handler1.sendEmptyMessage(0);
                    Log.d("in_thread" , "sending msg to handler");
                }
            }).start();


            /*image_list.clear();
            this.get_list();
            this.get_json();
            Log.d("starting_dowloading","yes");*/
        }

        else  if(video.equals("NO")){
            image_list.clear();
            this.get_list();
            this.get_json_no_image();
        }

    }

    Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            loading.dismiss();
            Toast.makeText(context, context.getResources().getString(R.string.sync_finish)+"  "+context.getResources().getString(R.string.uploading),Toast.LENGTH_SHORT).show();
            image_list.clear();
            get_list();
            get_json();
            Log.d("starting_dowloading", "yes");
        }
    };

    public void get_list() {

        SharedPreferences preferences = context.getSharedPreferences("user_data",Context.MODE_PRIVATE);
        user_id = preferences.getInt("user_id",-1);
        cicle_list.clear();
        cicle_list.addAll(handler.get_all_cicle(user_id));
        Log.d("sync_cicle_list_size", String.valueOf(cicle_list.size()));
        building_list.clear();
        building_list.addAll(handler.getAllBuilding(user_id));
        Log.d("sync_build_list_size", String.valueOf(building_list.size()));
        equip_list.clear();
        equip_list.addAll(handler.get_all_equipment_by_user_id(user_id));
        Log.d("sync_equip_list_size", String.valueOf(equip_list.size()));
        worker_list.clear();
        worker_list.addAll(handler.get_all_worker_by_user_id(user_id));
        Log.d("sync_worker_list_size", String.valueOf(worker_list.size()));
        medical_list.clear();
        medical_list.addAll(handler.get_all_medical_by_user_id(user_id));
        Log.d("sync_medical_list_size", String.valueOf(medical_list.size()));
        food_list.clear();
        food_list.addAll(handler.getAllFoodBYUSER_ID(user_id));
        Log.d("sync_food_list_size", String.valueOf(food_list.size()));
        con_list.clear();
        con_list.addAll(handler.get_all_con_by_user_id(user_id));
        Log.d("sync_con_list_size", String.valueOf(con_list.size()));
        animal_list.clear();
        animal_list.addAll(handler.getAllAnimalByUser_id(user_id));
        Log.d("sync_animal_list_size", String.valueOf(animal_list.size()));
        death_list.clear();
        death_list.addAll(handler.get_all_death_by_user_id(user_id));
        Log.d("sync_death_list_size", String.valueOf(death_list.size()));
        returned_list.clear();
        returned_list.addAll(handler.get_all_returned_expense_by_user_id(user_id));
        Log.d("sync_returned_list_size", String.valueOf(returned_list.size()));
        additional_list.clear();
        additional_list.addAll(handler.get_all_additional_expense_by_user_id(user_id));
        Log.d("sync_returned_list_size", String.valueOf(returned_list.size()));
        down_payment_list.clear();
        down_payment_list.addAll(handler.get_all_down_payment_by_user_id(user_id));
        Log.d("sync_returned_list_size", String.valueOf(returned_list.size()));
        normal_egg_list.clear();
        normal_egg_list.addAll(handler.get_all_normal_egg_by_user_id(user_id));
        Log.d("sync_n_egg_list_size", String.valueOf(normal_egg_list.size()));
        broken_egg_list.clear();
        broken_egg_list.addAll(handler.get_all_broken_egg_by_user_id(user_id));
        Log.d("sync_b_egg_list_size", String.valueOf(broken_egg_list.size()));
        temp_list.clear();
        temp_list.addAll(handler.get_all_temp_by_user_id(user_id));
        Log.d("sync_temp_list_size", String.valueOf(temp_list.size()));
        note_list.clear();
        note_list.addAll(new_handler.get_all_note_by_user_id(user_id));
        Log.d("sync_note_list_size", String.valueOf(note_list.size()));
        image_list.clear();
        image_list.addAll(handler.get_all_image_by_user_id(user_id));
        Log.d("sync_image_list_size", String.valueOf(image_list.size()));
        task_list.clear();
        task_list.addAll(handler.get_all_done_task_by_user_id(user_id));
        Log.d("sync_task_list_size",String.valueOf(task_list.size()));
    }
    public void get_json(){
        image_list.clear();
        image_list.addAll(handler.get_all_image_by_user_id(user_id));



        Gson gson=new Gson();
        String cicle_json=gson.toJson(cicle_list);
        String building_json=gson.toJson(building_list);
        String equip_json=gson.toJson(equip_list);
        String worker_json=gson.toJson(worker_list);
        String medical_json=gson.toJson(medical_list);
        String food_json=gson.toJson(food_list);
        String con_json=gson.toJson(con_list);
        String animal_json=gson.toJson(animal_list);
        String death_json=gson.toJson(death_list);
        String returned_expense_json=gson.toJson(returned_list);
        String additional_expense_json=gson.toJson(additional_list);
        String down_payment_json=gson.toJson(down_payment_list);
        String normal_egg_json=gson.toJson(normal_egg_list);
        String broken_egg_json=gson.toJson(broken_egg_list);
        String temp_json=gson.toJson(temp_list);
        String note_json=gson.toJson(note_list);
        String image_json=gson.toJson(image_list);
        String task_json=gson.toJson(task_list);
        JSONObject object=new JSONObject();
        try {
            object.put("user_id",user_id);
            object.put("cicle",cicle_json);
            object.put("building",building_json);
            object.put("equipment",equip_json);
            object.put("worker",worker_json);
            object.put("medical",medical_json);
            object.put("food",food_json);
            object.put("consommation",con_json);
            object.put("returned_expense",returned_expense_json);
            object.put("additional_expense",additional_expense_json);
            object.put("down_payment",down_payment_json);
            object.put("animal",animal_json);
            object.put("death",death_json);
            object.put("broken_egg",broken_egg_json);
            object.put("normal_egg",normal_egg_json);
            object.put("temp",temp_json);
            object.put("note",note_json);
            object.put("image",image_json);
            object.put("task",task_json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("Json_gson",object.toString());
        String res=object.toString().replace("\\","");
        Log.d("json_string", res);
        send_data(object, context);
    }
    public void get_json_no_image(){
        Gson gson=new Gson();
        String cicle_json=gson.toJson(cicle_list);
        String building_json=gson.toJson(building_list);
        String equip_json=gson.toJson(equip_list);
        String worker_json=gson.toJson(worker_list);
        String medical_json=gson.toJson(medical_list);
        String food_json=gson.toJson(food_list);
        String con_json=gson.toJson(con_list);
        String animal_json=gson.toJson(animal_list);
        String death_json=gson.toJson(death_list);
        String returned_expense_json=gson.toJson(returned_list);
        String additional_expense_json=gson.toJson(additional_list);
        String down_payment_json=gson.toJson(down_payment_list);
        String normal_egg_json=gson.toJson(normal_egg_list);
        String broken_egg_json=gson.toJson(broken_egg_list);
        String temp_json=gson.toJson(temp_list);
        String note_json=gson.toJson(note_list);
     //   String image_json=gson.toJson(image_list);
        String task_json=gson.toJson(task_list);
        JSONObject object=new JSONObject();
        try {
            object.put("user_id",user_id);
            object.put("cicle",cicle_json);
            object.put("building",building_json);
            object.put("equipment",equip_json);
            object.put("worker",worker_json);
            object.put("medical",medical_json);
            object.put("food",food_json);
            object.put("consommation",con_json);
            object.put("returned_expense",returned_expense_json);
            object.put("additional_expense",additional_expense_json);
            object.put("down_payment",down_payment_json);
            object.put("animal",animal_json);
            object.put("death",death_json);
            object.put("broken_egg",broken_egg_json);
            object.put("normal_egg",normal_egg_json);
            object.put("temp",temp_json);
            object.put("note",note_json);
       //     object.put("image",image_json);
            object.put("task",task_json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("Json_gson",object.toString());
        String res=object.toString().replace("\\","");
        Log.d("json_string", res);
        send_data_no_image(object, context);
    }

    public void send_data(JSONObject js, final Context context){
       // String url = "http://176.9.206.149/cicle/test.php";
        Log.d("sync_url",url);
        loading = ProgressDialog.show(context, context.getResources().getString(R.string.please_wait), context.getResources().getString(R.string.downloading));

        RequestQueue queue=Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST,url, js,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("onse", response.toString());
                        json_parsing parsing=new json_parsing();
                        parsing.get_list(cicle_list, building_list, equip_list, worker_list, medical_list, food_list, con_list, additional_list, returned_list, down_payment_list, animal_list, death_list, normal_egg_list, broken_egg_list, temp_list, note_list,image_list,task_list);
                        size=parsing.main_parse(response,context);
                        if(size>0){
                            sync_class.this.get_list();
                            sync_class.this.get_json();
                        }
                        else{
                            loading.dismiss();
                            Toast.makeText(context, context.getResources().getString(R.string.sync_finish)+"  "+context.getResources().getString(R.string.downloading),Toast.LENGTH_SHORT).show();
                            ((Activity)context).finish();
                            context.startActivity(new Intent(context, Tasks.class));
                        }
                    //loading.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                loading.hide();
                Toast.makeText(context,context.getResources().getString(R.string.error_in_connection)+"  "+context.getResources().getString(R.string.restart),Toast.LENGTH_LONG).show();

                if( error instanceof NetworkError) {
                    if(loading.isShowing() && loading!=null){
                        Log.d("onnn","onn");
                    }
                    else{
                        Log.d("offf","offf");
                    }
                    Log.d("Error ","NetworkError");
                } else if( error instanceof ClientError) {
                    if(loading.isShowing() && loading!=null){
                        Log.d("onnn","onn");
                    }
                    else{
                        Log.d("offf","offf");
                    }
                    //loading.hide();
                    //Toast.makeText(context,context.getResources().getString(R.string.error_in_connection),Toast.LENGTH_SHORT).show();
                } else if( error instanceof ServerError) {
                    if(loading.isShowing() && loading!=null){
                        Log.d("onnn","onn");
                    }
                    else{
                        Log.d("offf","offf");
                    }
                    //loading.hide();
                    //Toast.makeText(context,context.getResources().getString(R.string.server_error),Toast.LENGTH_SHORT).show();
                } else if( error instanceof AuthFailureError) {
                    if(loading.isShowing() && loading!=null){
                        Log.d("onnn","onn");
                    }
                    else{
                        Log.d("offf","offf");
                    }
                    //loading.hide();
                    //Toast.makeText(context,context.getResources().getString(R.string.authentication_failure),Toast.LENGTH_SHORT).show();
                } else if( error instanceof ParseError) {
                    if(loading.isShowing() && loading!=null){
                        Log.d("onnn","onn");
                    }
                    else{
                        Log.d("offf","offf");
                    }
                    //loading.dismiss();
                    Log.d("mess","mess");
                    //Toast.makeText(context,"Error in Connecting to Server",Toast.LENGTH_SHORT).show();
                } else if( error instanceof NoConnectionError) {
                    if(loading.isShowing() && loading!=null){
                        Log.d("onnn","onn");
                    }
                    else{
                        Log.d("offf","offf");
                    }
                    //loading.hide();
                 //   Toast.makeText(context,context.getResources().getString(R.string.no_connection),Toast.LENGTH_SHORT).show();
                } else if( error instanceof TimeoutError) {
                    if(loading.isShowing() && loading!=null){
                        Log.d("onnn","onn");
                    }
                    else{
                        Log.d("offf","offf");
                    }
                    //loading.hide();
                    Log.d("TimeOut Error","TimeOut Error");
                }else{
                    if(loading.isShowing() && loading!=null){
                        Log.d("onnn","onn");
                    }
                    else{
                        Log.d("offf","offf");
                    }
                    //loading.hide();
                    Log.d("Unknown_error","unknown_error");
                }
            }

        }) {
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(3000,
                0,
                0
        ));

        queue.add(jsonObjReq);
    }

    public void send_data_no_image(JSONObject js, final Context context){
        //String url = "http://176.9.206.149/cicle/parse_json.php";
        Log.d("sync_url",url);
        loading = ProgressDialog.show(context, context.getResources().getString(R.string.please_wait), context.getResources().getString(R.string.downloading));

        RequestQueue queue=Volley.newRequestQueue(context);
       /* if(loading==null) {
            Log.d("go_to_hell","go+to_hell");
            loading = ProgressDialog.show(context, context.getResources().getString(R.string.please_wait), "Synchronizing");
        }*/
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST,url, js,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("onse", response.toString());
                        json_parsing parsing=new json_parsing();
                        parsing.get_list_without_image(cicle_list, building_list, equip_list, worker_list, medical_list, food_list, con_list, additional_list, returned_list, down_payment_list, animal_list, death_list, normal_egg_list, broken_egg_list, temp_list, note_list,task_list);
                        size=parsing.main_parse_without_image(response, context);
                        if(size>0){
                            sync_class.this.get_list();
                            sync_class.this.get_json_no_image();
                        }
                        else{
                            loading.dismiss();
                            Toast.makeText(context,context.getResources().getString(R.string.sync_finish)+" "+context.getResources().getString(R.string.downloading),Toast.LENGTH_SHORT).show();
                            ((Activity)context).finish();
                            context.startActivity(new Intent(context, Tasks.class));
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                loading.dismiss();
                Toast.makeText(context,context.getResources().getString(R.string.error_in_connection)+" "+context.getResources().getString(R.string.restart),Toast.LENGTH_LONG).show();

                if( error instanceof NetworkError) {
                    //loading.hide();
                    Log.d("Error ","NetworkError");
                } else if( error instanceof ClientError) {
                    //loading.hide();
                    //Toast.makeText(context,context.getResources().getString(R.string.error_in_connection),Toast.LENGTH_SHORT).show();
                } else if( error instanceof ServerError) {
                    //Toast.makeText(context,context.getResources().getString(R.string.server_error),Toast.LENGTH_SHORT).show();
                } else if( error instanceof AuthFailureError) {
                    //loading.hide();
                    //Toast.makeText(context,context.getResources().getString(R.string.authentication_failure),Toast.LENGTH_SHORT).show();
                } else if( error instanceof ParseError) {
                    //loading.hide();
                    Log.d("Errorrrrrr in parsing","Errorrrrrrrr in json_parsing");
                } else if( error instanceof NoConnectionError) {
                    //loading.hide();
                    //Toast.makeText(context,context.getResources().getString(R.string.no_connection),Toast.LENGTH_SHORT).show();
                } else if( error instanceof TimeoutError) {
                    //loading.hide();
                }else{
                    Log.d("Unknown_error","unknown_error");
                }            }

        }) {
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));

        queue.add(jsonObjReq);
    }



    public image_class upload_image(Context context,image_class image) {
        class UploadImage extends AsyncTask<image_class, image_class, image_class> {

            /******
             * Url where you want to upload image
             *********/
            //ProgressDialog dialog;

            image_class image = null;
            Context con;
            //ProgressDialog dialog;

            public UploadImage(Context con, image_class image) {
                this.image = image;
                this.con = con;
                //this.dialog=_dialog;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //dialog = ProgressDialog.show(con, "Uploading Image", "Stop");
                //dialog.show();
            }

            @Override
            protected image_class doInBackground(image_class... params) {
                //String fileName = "/storage/emulated/0/Pictures/Cicle_App-1-1/vddhhd_10_.mp4";
                String result = "";
                HttpURLConnection conn = null;
                DataOutputStream dos = null;
                String lineEnd = "\r\n";
                String twoHyphens = "--";
                String boundary = "*****";
                int bytesRead, bytesAvailable, bufferSize;
                byte[] buffer;
                int maxBufferSize = 1024 * 4;
                File sourceFile = new File(image.getPath());
                // sourceFile.getTotalSpace();
                Log.d("upload_media_url",upLoadServerUri);
                Log.d("Size_of_image",String.valueOf(sourceFile.getTotalSpace()));
                if (!sourceFile.isFile()) {
                    Log.e("uploadFile", "Source File not exist :");

                } else {
                    FileInputStream fileInputStream = null;
                    try {
                        Log.d("uploading_file","Uploading_file");
                        fileInputStream = new FileInputStream(sourceFile);
                        URL url = new URL(upLoadServerUri);

                        // Open a HTTP  connection to  the URL
                        conn = (HttpURLConnection) url.openConnection();
                        conn.setConnectTimeout(2000);
                        conn.setDoInput(true); // Allow Inputs
                        conn.setDoOutput(true); // Allow Outputs
                        conn.setUseCaches(false); // Don't use a Cached Copy
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Connection", "Keep-Alive");
                        conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                        conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                        conn.setRequestProperty("uploaded_file", image.getName());

                        Log.d("uploading_file", "Uploading_file2");
                        dos = new DataOutputStream(conn.getOutputStream());

                        dos.writeBytes(twoHyphens + boundary + lineEnd);
                        dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\"" + image.getName() + "\"" + lineEnd);

                        dos.writeBytes(lineEnd);


                        // create a buffer of  maximum size
                        bytesAvailable = fileInputStream.available();
                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        buffer = new byte[bufferSize];

                        // read file and write it into form...
                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                        while (bytesRead > 0) {

                            dos.write(buffer, 0, bufferSize);
                            bytesAvailable = fileInputStream.available();
                            bufferSize = Math.min(bytesAvailable, maxBufferSize);
                            bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                        }

                        Log.d("uploading_file", "Uploading_file3");



                        // send multipart form data necesssary after file data...
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
                        //sending a sql_cicle_id to server
                        dos.writeBytes(twoHyphens + boundary + lineEnd);
                        dos.writeBytes("Content-Disposition: form-data; name=\"sql_cicle_id\"" + lineEnd);
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(String.valueOf(image.getSql_cicle_id()));
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                        //sending a sql_cicle_id to server
                        dos.writeBytes(twoHyphens + boundary + lineEnd);
                        dos.writeBytes("Content-Disposition: form-data; name=\"sql_building_id\"" + lineEnd);
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(String.valueOf(image.getSql_building_id()));
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                        //sending user_id to the server
                        dos.writeBytes(twoHyphens + boundary + lineEnd);
                        dos.writeBytes("Content-Disposition: form-data; name=\"user_id\"" + lineEnd);
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(String.valueOf(image.getUser_id()));
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                        //sending image_id to the server
                        dos.writeBytes(twoHyphens + boundary + lineEnd);
                        dos.writeBytes("Content-Disposition: form-data; name=\"image_id\"" + lineEnd);
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(String.valueOf(image.getImage_id()));
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                        //sending cicle_id to the server
                        dos.writeBytes(twoHyphens + boundary + lineEnd);
                        dos.writeBytes("Content-Disposition: form-data; name=\"cicle_id\"" + lineEnd);
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(String.valueOf(image.getCicle_id()));
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                        //sending building_id to the server
                        dos.writeBytes(twoHyphens + boundary + lineEnd);
                        dos.writeBytes("Content-Disposition: form-data; name=\"building_id\"" + lineEnd);
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(String.valueOf(image.getBuilding_id()));
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                        //sending file_name

                        dos.writeBytes(twoHyphens + boundary + lineEnd);
                        dos.writeBytes("Content-Disposition: form-data; name=\"file_name\"" + lineEnd);
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(String.valueOf(image.getName()));
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                        //sending file_type

                        dos.writeBytes(twoHyphens + boundary + lineEnd);
                        dos.writeBytes("Content-Disposition: form-data; name=\"file_type\"" + lineEnd);
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(String.valueOf(image.getType()));
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                        //sending flag

                        dos.writeBytes(twoHyphens + boundary + lineEnd);
                        dos.writeBytes("Content-Disposition: form-data; name=\"flag\"" + lineEnd);
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(String.valueOf(image.getFlag()));
                        dos.writeBytes(lineEnd);
                        dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                        //getting the input
                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String line;

                        while ((line = reader.readLine()) != null) {
                            result = result + line;
                        }
                        Log.d("resulttttt", result);
                        if(Integer.parseInt(result)>0){
                            image.setSql_image_id(Integer.parseInt(result));
                            image.setFlag(1);
                            Log.d("In backgrpimd_flag",String.valueOf(image.getFlag()));
                        }

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (ProtocolException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Log.d("result",result);
                return image;
            }


            @Override
            protected void onPostExecute(image_class imagee) {
                // dismiss the dialog after the file was downloaded

                if(imagee.getSql_image_id()<0){
                    Log.d("useless","2");
                    imagee.setSql_image_id(0);
                    imagee.setFlag(0);
                }
                else{
                    Log.d("going_in_one","one");
                    imagee.setFlag(1);
                }
                Log.d("Result", String.valueOf(image.getSql_image_id()));
            }

        }

        UploadImage ui=new UploadImage(context,image);
        image_class return_image=null;
        try {
            return_image=ui.execute(image).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return return_image;
    }




}