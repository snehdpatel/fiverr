package com.example.sneh.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import Adapters.FoodAdapter;
import Adapters.TaskAdapter;

public class FoodConsommation extends AppCompatActivity {

    int cicle_id, building_id;
    int cicle_sql,building_sql;

    db_handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_consommation);
        SharedPreferences preferences=getSharedPreferences("app_data", Context.MODE_PRIVATE);
        cicle_id=preferences.getInt("cicle_id", -1);
        building_id=preferences.getInt("building_id", -1);
        cicle_sql=preferences.getInt("cicle_sql", -1);
        building_sql=preferences.getInt("building_sql",-1);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());



        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        String[] tabTitles = new String[] { getResources().getString(R.string.food_list), getResources().getString(R.string.con) };
        FoodAdapter adapter=new FoodAdapter(getSupportFragmentManager(),FoodConsommation.this , cicle_id, building_id, tabTitles);
        viewPager.setAdapter(adapter);
        // Give the TabLayout the ViewPager
        TabLayout tabs = (TabLayout) findViewById(R.id.sliding_tabs);
        tabs.setupWithViewPager(viewPager);
        tabs.setSelectedTabIndicatorColor(Color.parseColor("#fff176"));
        tabs.setBackgroundColor(Color.parseColor("#fdd835"));
        tabs.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#f5f5f5"));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent=new Intent(FoodConsommation.this,TitleBuilding.class);
        intent.putExtra("cicle_id", cicle_id);
        intent.putExtra("building_id", building_id);
        startActivity(intent);
        finish();

        return true;
    }

    public void onBackPressed(){
        Intent intent=new Intent(FoodConsommation.this,TitleBuilding.class);
        intent.putExtra("cicle_id", cicle_id);
        intent.putExtra("building_id", building_id);
        startActivity(intent);
        finish();
    }
}
