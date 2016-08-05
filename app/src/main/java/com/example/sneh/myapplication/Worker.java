package com.example.sneh.myapplication;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import Adapters.EachListAdapter;
import Adapters.TaskAdapter;
import Adapters.worker_taskAdapter;
import Tabs.active_worker;

public class Worker extends AppCompatActivity {

    private int cicle_id;
    private int building_id;
    private int cicle_sql,building_sql;
    private int user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);


        //getting cicle_id and building_id by sharedprefs
        SharedPreferences preferences=getSharedPreferences("app_data", Context.MODE_PRIVATE);
        cicle_id=preferences.getInt("cicle_id", -1);
        building_id=preferences.getInt("building_id", -1);
        cicle_sql=preferences.getInt("cicle_sql", -1);
        if(cicle_sql==-1)
            cicle_sql=0;
        building_sql=preferences.getInt("building_sql",-1);
        if(building_sql==-1)
            building_sql=0;

        //getting user_id by sharedprefs
        SharedPreferences pref1=getSharedPreferences("user_data",Context.MODE_PRIVATE);
        user_id=pref1.getInt("user_id",-1);
        Log.d("worker_user_id",String.valueOf(user_id));

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_worker);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //setting intent and gettting cicle_id and building_id
        Intent intent=getIntent();
        Log.d("worker_cicle_id", String.valueOf(cicle_id));
        Log.d("worker_building_id", String.valueOf(building_id));
        Log.d("worker_cicle_sql",String.valueOf(cicle_sql));
        Log.d("worker_building_sql",String.valueOf(building_sql));
        Log.d("worker_user_id",String.valueOf(user_id));


        Bundle bundle=new Bundle();
        bundle.putInt("Cicle_id",cicle_id);
        bundle.putInt("building_id",building_id);
        active_worker aw=new active_worker();
        aw.setArguments(bundle);

        //setting up db_handler
        db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());

        //
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_worker);
        String tabTitles[] = new String[] { getResources().getString(R.string.active), getResources().getString(R.string.in_active) };
        worker_taskAdapter adapter=new worker_taskAdapter(getSupportFragmentManager(),getApplicationContext(),cicle_id,building_id, tabTitles);
        viewPager.setAdapter(adapter);
        // Give the TabLayout the ViewPager
        TabLayout tabs = (TabLayout) findViewById(R.id.sliding_tabs_worker);
        tabs.setupWithViewPager(viewPager);
        tabs.setSelectedTabIndicatorColor(Color.parseColor("#fff176"));
        tabs.setBackgroundColor(Color.parseColor("#fdd835"));
        tabs.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#f5f5f5"));
/*
        List<worker_class> worker_list=handler.get_all_worker(cicle_id,building_id);
        Log.d("Worker_worker_list_size",String.valueOf(worker_list.size()));

        String[] text1 = new String[worker_list.size()];
        String[] text2 = new String[worker_list.size()];
        String[] text3 = new String[worker_list.size()];
        String[] text4 = new String[worker_list.size()];
        String[] text5 = new String[worker_list.size()];
        int[] id=new int[worker_list.size()];

        for(int i=0;i<worker_list.size();i++){
            text1[i]="";
            text2[i]=worker_list.get(i).getName();
            text3[i]=String.valueOf(worker_list.get(i).getPrice_per_day());
            text4[i]="";
            text5[i]="";
            id[i]=worker_list.get(i).getWorker_id();

        }

        ListView lv = (ListView) findViewById(R.id.worker_list);
        lv.setAdapter(new EachListAdapter(Worker.this, 1, text1, text2, text3, text4, text5,id));*/

       /// FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.add_worker);
    /*    edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Worker.this, AddWorker.class);
                intent.putExtra("cicle_id", cicle_id);
                intent.putExtra("building_id", building_id);
                startActivity(intent);
            }
        });*/


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent=new Intent(Worker.this,TitleBuilding.class);
        intent.putExtra("cicle_id",cicle_id);
        intent.putExtra("building_id",building_id);
        startActivity(intent);
        finish();
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent intent=new Intent(Worker.this,TitleBuilding.class);
        intent.putExtra("cicle_id",cicle_id);
        intent.putExtra("building_id",building_id);
        startActivity(intent);
        finish();
    }
}
