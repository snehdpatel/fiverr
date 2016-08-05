package com.example.sneh.myapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Adapters.TaskAdapter;
import Adapters.TodaysTaskAdapter;

public class TodaysTask extends AppCompatActivity implements task_implement{
    public db_handler handler;
    private String un_done[];
    List<title_> title_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todays_task);
        handler=new db_handler(this);
        handler.onCreateTable(handler.getWritableDatabase());
    /* title_list=new ArrayList<title_>();
    un_done = new String[]{"Do stuff0111", "Do stuff0222", "Do stuff0333"};
    if(title_list.size()<=0){
      for(int i=0; i<un_done.length; i++){
        title_ title = new title_();
        title.setTitle(un_done[i]);
        title.setDone(1);


        handler.CreateTitle(title);
      }}*/

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        TodaysTaskAdapter adapter = new TodaysTaskAdapter(getSupportFragmentManager(),getApplicationContext());
        viewPager.setAdapter(adapter);

        TabLayout tabs = (TabLayout) findViewById(R.id.sliding_tabs);
        tabs.setupWithViewPager(viewPager);
        adapter.notifyDataSetChanged();
        tabs.setupWithViewPager(viewPager);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
        /* android.support.v4.app.Fragment fragment=((FragmentPagerAdapter)viewPager.getAdapter()).getItem(position);
          if((position==1&&fragment!=null)||(position==0&&fragment!=null))
          {
            fragment.onResume();
          }*/
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        tabs.setSelectedTabIndicatorColor(Color.parseColor("#fff176"));
        tabs.setBackgroundColor(Color.parseColor("#fdd835"));
        tabs.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#f5f5f5"));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent=new Intent(TodaysTask.this,Tasks.class);
        finish();
        startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent intent=new Intent(TodaysTask.this,Tasks.class);
        finish();
        startActivity(intent);
    }

    @Override
    public void respond(String data, int data1) {

        FragmentManager fm=getFragmentManager();



    }
}