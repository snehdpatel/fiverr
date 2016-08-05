package com.example.sneh.myapplication;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
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
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class connection_class {
    private String main_url="http://176.9.206.149/cicle/";

    public void login(Context context,String email,String password){
        class  login extends  AsyncTask<String , Void ,String>{
            private Context con;
            private Dialog loading;
            private String email,password;
            public login(Context context) {
                con = context;
            }

            public void onPreExecute()
            {
                super.onPreExecute();
                loading = ProgressDialog.show(con, con.getResources().getString(R.string.please_wait),  con.getResources().getString(R.string.checking));
            }

            public String doInBackground(String... params) {
                email=params[0];
                password=params[1];
                Log.d("email",email);
                String data = null;
                String login_url=main_url+"login.php";
                String result="";
                try {
                    data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                    data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                    Log.d("login_url",login_url);
                    URL url = new URL(login_url);
                    URLConnection connection = url.openConnection();
                    connection.setDoOutput(true);
                    //Writting
                    OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                    writer.write(data);
                    writer.flush();
                    //getting the input
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result = result + line;
                    }

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("result", result);
                return result;

            }
            public void onPostExecute(String result) {
                if (loading != null) {
                    loading.dismiss();
                }
                json_parsing json_parsing=new json_parsing();
                message_class message=json_parsing.login_register_parsing(result);
                Log.d("success",String.valueOf(message.getSuccess()));
                if(message.getSuccess()>0){
                    SharedPreferences pref=con.getSharedPreferences("user_data",Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit=pref.edit();
                    edit.putInt("user_id", message.getSuccess());
                    Log.d("user_id", String.valueOf(message.getSuccess()));
                    Log.d("user_email", email);
                    Log.d("user_password", password);
                    edit.putString("user_email", email);
                    edit.putString("user_password", password);
                    edit.commit();
                    Toast.makeText(con,message.getMessage(),Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(con,Tasks.class);
                    ((Activity)con).finish();
                    con.startActivity(intent);
                }
                if(message.getMessage()==null){
                    message.setMessage(con.getResources().getString(R.string.error_in_connection));
                }
                Toast.makeText(con, message.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        login login=new login(context);
        login.execute(email,password);
    }

    public void register(Context context, String fname, String lname, String email, String password) {

        class register extends AsyncTask<String, Void, String> {
            private Context con;
            private Dialog loading;

            public register(Context context) {
                con = context;
            }
            String register_url=main_url+"register.php";
            public void onPreExecute()

            {
                super.onPreExecute();
                loading = ProgressDialog.show(con, con.getResources().getString(R.string.please_wait),  con.getResources().getString(R.string.checking));
            }

            public String doInBackground(String... params) {
                String fname = params[0];
                String lname = params[1];
                String email = params[2];
                Log.d("email",email);
                String password = params[3];
                String result = "";

                String data = null;
                try {
                    data = URLEncoder.encode("fname", "UTF-8") + "=" + URLEncoder.encode(fname, "UTF-8");
                    data += "&" + URLEncoder.encode("lname", "UTF-8") + "=" + URLEncoder.encode(lname, "UTF-8");
                    data += "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                    data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                    URL url = new URL(register_url);
                    Log.d("register_url",url.toString());
                    URLConnection connection = url.openConnection();
                    connection.setDoOutput(true);
                    //Writting
                    OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                    writer.write(data);
                    writer.flush();
                    //getting the input
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result = result + line;
                    }

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("result", result);
                return result;

            }

            public void onPostExecute(String result) {
                if (loading != null) {
                    loading.dismiss();
                }
                json_parsing json_parsing=new json_parsing();
                 message_class message=json_parsing.login_register_parsing(result);
                if(message.getMessage()==null){
                    message.setMessage(con.getResources().getString(R.string.error_in_connection));
                }
                Toast.makeText(con,message.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
        register register=new register(context);
        register.execute(fname,lname,email,password);

    }

    public void sync_cicle(int user_id,String date,Context context){

        class sync extends  AsyncTask<String,Void,String>{
            private Context con;
            private Dialog loading;
            private String date;
            private  String user_id;
            String task_url=main_url+"get_task.php";
            public sync(Context context) {
                con = context;
            }

            public void onPreExecute()

            {
                super.onPreExecute();
                loading = ProgressDialog.show(con, con.getResources().getString(R.string.please_wait),  con.getResources().getString(R.string.checking));
            }

            public String doInBackground(String... params) {
                user_id=String.valueOf(params[0]);
                date=params[1];
                Log.d("con_user_id",user_id);
                Log.d("con_date",date);
                String data = null;
                String result="";
                try {
                    data = URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8");
                    data += "&" + URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8");

                    URL url = new URL(task_url);
                    URLConnection connection = url.openConnection();
                    connection.setDoOutput(true);
                    //Writting
                    OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                    writer.write(data);
                    writer.flush();
                    //getting the input
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result = result + line;
                    }

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("result", result);
                return result;

            }
            public void onPostExecute(String result) {


                String message=result;
                json_parsing parsing=new json_parsing();
                parsing.json(con,message);
                if (loading != null) {
                    loading.dismiss();
                }
                Toast.makeText(con, message, Toast.LENGTH_SHORT).show();
            }
        }
        sync sync=new sync(context);
        sync.execute(String.valueOf(user_id),date);
    }

    public void get_task(int user_id,Context context){
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage(context.getResources().getString(R.string.loading));
        pDialog.show();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime());
        Log.d("todays_date", formattedDate);
        int man= post_task(formattedDate, user_id, context);
        pDialog.hide();
    }

    public int post_task(final String date, final int user_id, final Context context){
        JSONObject js=new JSONObject();
        String task_url=main_url+"get_task.php";
        try {
            js.put("date",date);
            js.put("user_id",user_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("task_json",js.toString());
        //String url = "http://176.9.206.149/cicle/get_task.php";
        RequestQueue queue=Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST,task_url, js,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("onse", response.toString());
                        json_parsing json_parsing=new json_parsing();
                        json_parsing.json(context,response.toString());

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if( error instanceof NetworkError) {
                    Toast.makeText(context,context.getResources().getString(R.string.error_in_connection),Toast.LENGTH_SHORT).show();
                } else if( error instanceof ClientError) {
                    Toast.makeText(context,context.getResources().getString(R.string.error_in_connection),Toast.LENGTH_SHORT).show();
                } else if( error instanceof ServerError) {
                    Toast.makeText(context,context.getResources().getString(R.string.server_error),Toast.LENGTH_SHORT).show();
                } else if( error instanceof AuthFailureError) {
                    Toast.makeText(context,context.getResources().getString(R.string.authentication_failure),Toast.LENGTH_SHORT).show();
                } else if( error instanceof ParseError) {
                    Log.d("Error in parsing","Error in json_parsing");
                } else if( error instanceof NoConnectionError) {
                    Toast.makeText(context,context.getResources().getString(R.string.no_connection),Toast.LENGTH_SHORT).show();
                } else if( error instanceof TimeoutError) {
                    Log.d("TimeOut Error","TimeOut Error");
                }else{
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
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));

        queue.add(jsonObjReq);
        int man=1;
        return man;
    }

    public int check_connection(Context context){
       if(isNetworkAvailable(context)){
           return 1;
       }
        else{
           Toast.makeText(context,context.getResources().getString(R.string.internet_not_available),Toast.LENGTH_SHORT).show();
           return  -2;
       }
    }
    public boolean isNetworkAvailable(final Context context) {
            final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
            return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();

    }
    public boolean isInternetAvailable() {
        class NetTask extends AsyncTask<String, Integer, String>
        {
            @Override
            protected String doInBackground(String... params)
            {
                InetAddress addr = null;
                try
                {
                    addr = InetAddress.getByName(params[0]);
                    Log.d("addrr","dasadd");
                }

                catch (UnknownHostException e)
                {
                    e.printStackTrace();
                }
                return addr.getHostAddress();
            }
        }
        String netAddress = null;
        try
        {
            netAddress = new NetTask().execute("www.google.com").get();
            Log.d("network_address",netAddress);
        }
        catch (Exception e1)
        {
            e1.printStackTrace();
        }
        if(netAddress==""){
            return false;
        }
        else{
            return true;
        }
    }



}

