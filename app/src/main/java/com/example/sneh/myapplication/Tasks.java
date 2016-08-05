package com.example.sneh.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.sneh.myapplication.R;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import Adapters.TaskAdapter;

public class Tasks extends AppCompatActivity {
    db_handler handler;
    TabLayout tabs;
    ViewPager viewPager;
    String tab1;
    String tab2;
    TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        Intent intent = getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

     /*   final connection_class connection_class=new connection_class();
        connection_class.check_connection(Tasks.this);*/
        handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        DataBaseHandler new_handler=new DataBaseHandler(getApplicationContext());
        new_handler.onCreateTable(new_handler.getWritableDatabase());

        //setting tabs and pager
        set_pager_tab();

        //shared prefs and getting user_id
        final SharedPreferences preferences=getSharedPreferences("user_data", Context.MODE_PRIVATE);
        final  int user_id=preferences.getInt("user_id", -1);
        final check_class check_class=new check_class();
        check_class.check_cicle(handler.get_all_cicle(user_id), Tasks.this);
        //Log.d("task_build_size",handler.getAllBuilding())
        check_class.check_building(handler.getAllBuilding(user_id), Tasks.this);

        //sharedpreferences
        final SharedPreferences preferences1=getSharedPreferences("setting_data",Context.MODE_PRIVATE);
        String language=preferences1.getString("language", "no");
        //Setting setting = new Setting();
        //setting.setLocal(language,Tasks.this);
        String notification=preferences1.getString("notification", "no_notification");
        String country=preferences1.getString("country", "no_country");
        String money=preferences1.getString("money","no_money");
        String video=preferences1.getString("video","no_video");
        Log.d("language",language);
        Log.d("country",country);
        Log.d("money",money);
        Log.d("notification",notification);
        Log.d("setting_video", video);
        Log.d("task_user_id", String.valueOf(user_id));
        Button task = (Button) findViewById(R.id.task);
        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connection_class conn = new connection_class();
                if (conn.check_connection(Tasks.this) > 0) {
                    Log.d("task_inte","fine");
                    conn.get_task(user_id, Tasks.this);
                    startActivity(new Intent(Tasks.this, TodaysTask.class));
                } else {
                    Log.d("task_inte", "not fine");
                    startActivity(new Intent(Tasks.this, TodaysTask.class));

                }
            }
        });
        Button sync=(Button)findViewById(R.id.sync);
        sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                connection_class connection = new connection_class();
                if (connection.check_connection(Tasks.this) > 0) {
                    sync_class sync_class = new sync_class(Tasks.this);
                    sync_class.get_list();
                    sync_class.upload_image_handler();
                    //,"Uploading Image","");

                }
                //finish();
                adapter.notifyDataSetChanged();
                Intent intent=new Intent(Tasks.this,Tasks.class);
                overridePendingTransition(0,0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                //finish();
                overridePendingTransition(0, 0);

            }
        });

        Button log_out=(Button)findViewById(R.id.log_out);
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences.edit().clear().commit();
                Intent intent=new Intent(Tasks.this,Login.class);
                startActivity(intent);
            }
        });


    }

    public void set_pager_tab(){
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tab1=getResources().getString(R.string.in_progress);
        tab2=getResources().getString(R.string.job_done);
        adapter=new TaskAdapter(getSupportFragmentManager(),Tasks.this,tab1,tab2);
        adapter.notifyDataSetChanged();
        viewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        tabs = (TabLayout) findViewById(R.id.sliding_tabs);
        tabs.setupWithViewPager(viewPager);
        tabs.setSelectedTabIndicatorColor(Color.parseColor("#fff176"));
        tabs.setBackgroundColor(Color.parseColor("#fdd835"));
        tabs.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#f5f5f5"));
    }

}

