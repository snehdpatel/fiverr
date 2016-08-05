package com.example.sneh.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Setting extends AppCompatActivity {
    String[] array_spinner;
    String[] array_notif;
    String[] array_money;
    String[] array_language;
    String[] array_pvsyn;
    private Locale myLocale;
    SharedPreferences preferences;
    private setting_class setting;
    private Spinner s_notif;
    private Spinner s;
    private Spinner s_money;
    private db_handler handler;
    private String date;
    private List<task_class> title_list;
    private int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        s_notif=(Spinner)findViewById(R.id.setting_notification);
        array_notif=new String[3];
        array_notif[0]="Notification(YES/NO)";
        array_notif[1]="YES";
        array_notif[2]="NO";
        final ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, array_notif);
        s_notif.setAdapter(adapter2);
        s_notif.setSelection(0);
        //Country Spinner
        array_spinner= getResources().getStringArray(R.array.entries);

        s = (Spinner) findViewById(R.id.setting_country);
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, array_spinner);
        s.setAdapter(adapter);

        SharedPreferences preferences2=getSharedPreferences("user_data",Context.MODE_PRIVATE);
        user_id=preferences2.getInt("user_id",-1);

        //sharedpreferences
        final SharedPreferences preferences1=getSharedPreferences("setting_data",Context.MODE_PRIVATE);
        String lang=preferences1.getString("language", "no");
        String notification=preferences1.getString("notification", "no_notification");
        String country=preferences1.getString("country", "no_country");
        String money=preferences1.getString("money","no_money");
        String video=preferences1.getString("video","no_video");
        Log.d("setting_language",lang);
        Log.d("setting_country",country);
        Log.d("setting_money",money);
        Log.d("setting_notification",notification);
        Log.d("setting_video",video);
        //Log.d("language",String.valueOf(spinner_position));
        if(notification!="no_notification"){
            for(int i=0;i<array_notif.length;i++){
                if(array_notif[i].equals(notification))
                    s_notif.setSelection(i);
            }
        }
        if(country!="no_country"){
            for(int i=0;i<array_spinner.length;i++){
                if(array_spinner[i].equals(country)){
                    s.setSelection(i);
                }
            }
        }

        //s.setSelection(spinner_position);
        array_money=getResources().getStringArray(R.array.values);
        s_money=(Spinner)findViewById(R.id.setting_money_format);
        ArrayAdapter adapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, array_money);
        s_money.setAdapter(adapter3);
        s_money.setSelection(0);

        if(money!="no_money"){
            for(int i=0;i<array_money.length;i++){
                if(array_money[i].equals(money)){
                    s_money.setSelection(i);
                }
            }
        }
        Button save_button=(Button)findViewById(R.id.setting_save);

        final Spinner language=(Spinner)findViewById(R.id.chose_language);
        array_language=new String[3];
        array_language[0]="Choose Language";
        array_language[1]="English";
        array_language[2]="French";
        String[] language_array=new String[]{"0","en","fr"};
        ArrayAdapter adapter4=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,array_language);
        language.setAdapter(adapter4);
        language.setSelection(0);

        if(lang!="no_language"){
            for(int i=0;i<array_language.length;i++){
                if(array_language[i].equals(lang)){
                    language.setSelection(i);
                }
            }
        }



        final Spinner pv_syn=(Spinner)findViewById(R.id.photo_video_syn);
        array_pvsyn=new String[3];
        array_pvsyn[0]="Sync Photo/Video (YES/NO)";
        array_pvsyn[1]="YES";
        array_pvsyn[2]="NO";
        final ArrayAdapter adapter5 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, array_pvsyn);
        pv_syn.setAdapter(adapter5);
        pv_syn.setSelection(0);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int n = s.getSelectedItemPosition();
                s_money.setSelection(n);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if(video!="no_video"){
            for(int i=0;i<array_pvsyn.length;i++){
                if(array_pvsyn[i].equals(video)){
                    pv_syn.setSelection(i);
                }
            }
        }

        s_money.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int n = s_money.getSelectedItemPosition();
                s.setSelection(n);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // Notification Spinner

        //Money Unit
        language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int n=language.getSelectedItemPosition();
                language.setSelection(n);
                if(n==1)
                {
                    setLocal("en",Setting.this); //to selectlanguage
                }
                if(n==2)
                {
                    setLocal("fr",Setting.this);//french selected
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        setting=new setting_class();
        //

        handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        setting_class setting1=handler.get_all_setting();
        int row=handler.delete_setting(setting1);
        if(setting1==null){
            //  Toast.makeText(getApplicationContext(),"null",Toast.LENGTH_SHORT).show();
        }
        else if(setting1!=null){
            //Toast.makeText(getApplicationContext(),"not_null",Toast.LENGTH_SHORT).show();
        }

        if (setting1 != null) {
            Log.d("notif", String.valueOf(setting1.getNotification()));
            Log.d("country", setting1.getCountry());
            Log.d("money", setting1.getMoney_format());
            for (int i = 0; i < array_spinner.length; i++) {
                if (array_spinner[i].equals(setting1.getCountry())) {
                    s.setSelection(i);
                }
            }
            for(int i=0;i<array_notif.length;i++){
                if(array_notif[i].equals(setting1.getNotification())){
                    s_notif.setSelection(i);
                }
            }

            for (int i = 0; i < array_money.length; i++) {
                if (array_money[i].equals(setting1.getMoney_format())) {
                    s_money.setSelection(i);
                }
            }
        }
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s_notif.getSelectedItemPosition() == 0 || s_money.getSelectedItemPosition() == 0 || language.getSelectedItemPosition() == 0 || pv_syn.getSelectedItemPosition() == 0){
                    Toast.makeText(Setting.this, "Fill All Fields", Toast.LENGTH_SHORT).show();
                }else {
                    setting.setNotification(s_notif.getSelectedItem().toString());
                    setting.setCountry(s.getSelectedItem().toString());
                    setting.setMoney_format(s_money.getSelectedItem().toString());
                    handler.insert_setting(setting);

                    Log.d("select_notif", setting.getNotification());
                    Log.d("select country", setting.getCountry());
                    Log.d("select monety", setting.getMoney_format());
                    preferences=getSharedPreferences("setting_data", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("notification",setting.getNotification());
                    editor.putString("country",setting.getCountry());
                    editor.putString("money", setting.getMoney_format());
                    editor.putString("language", language.getSelectedItem().toString());
                    editor.putString("video",pv_syn.getSelectedItem().toString());
                    editor.apply();
                    if(s_notif.getSelectedItem().toString().equals("NO"))
                    {
                        Date date_obj= Calendar.getInstance().getTime();
                        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                        date=sdf.format(date_obj);
                        Log.d("setting_date",date);
                        title_list = handler.get_all_not_done_task(user_id,date);
                        for(int i=0;i<title_list.size();i++)
                        {
                            check_if_set(title_list.get(i).getTask_id());
                        }
                        title_list.addAll(handler.get_all__done_task(user_id,date));
                        Log.d("row_in_task",String.valueOf(title_list.size()));
                        for(int i=0;i<title_list.size();i++)
                        {
                            check_if_set(title_list.get(i).getSql_task_id());
                            Log.d("setting_task_id",String.valueOf(title_list.get(i).getSql_task_id()));
                        }

                    }
                    Intent intent = new Intent(getApplicationContext(), Tasks.class);
                    finish();
                    startActivity(intent);
                }
            }
        });

    }
    public void check_if_set(int id)
    {
        Intent intent=new Intent(this,NotificationPublisher.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this, id, intent,0);
        AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }


    public void setLocal(String lang,Context context) {
        myLocale = new Locale(lang);
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        // Intent refresh = new Intent(this, MainActivity.class);
        //startActivity(refresh);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setting.setNotification(s_notif.getSelectedItem().toString());
        setting.setCountry(s.getSelectedItem().toString());
        setting.setMoney_format(s_money.getSelectedItem().toString());
        Log.d("money_format_", setting.getMoney_format());
        handler.insert_setting(setting);
        finish();
        return true;
    }

    @Override
    public void onBackPressed(){
        setting.setNotification(s_notif.getSelectedItem().toString());
        setting.setCountry(s.getSelectedItem().toString());
        setting.setMoney_format(s_money.getSelectedItem().toString());
        Log.d("money_format",setting.getMoney_format());
        handler.insert_setting(setting);
        finish();
    }

}