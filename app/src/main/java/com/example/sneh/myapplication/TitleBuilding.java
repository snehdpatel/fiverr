package com.example.sneh.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Animatable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class TitleBuilding extends AppCompatActivity {

    int building_id;
    building_class building;
    int cicle_sql,cicle_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_building);
        //Setting up db

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        LinearLayout equipment = (LinearLayout) findViewById(R.id.equipment);
        LinearLayout animals = (LinearLayout) findViewById(R.id.animals);
        LinearLayout worker = (LinearLayout) findViewById(R.id.workers);
        LinearLayout food = (LinearLayout) findViewById(R.id.food);
        LinearLayout medical = (LinearLayout) findViewById(R.id.medical);
        final LinearLayout finianse = (LinearLayout) findViewById(R.id.finianse);
        LinearLayout death = (LinearLayout) findViewById(R.id.death);
        LinearLayout alive = (LinearLayout) findViewById(R.id.alive);
        LinearLayout egg = (LinearLayout) findViewById(R.id.egg);
        LinearLayout temp = (LinearLayout) findViewById(R.id.temp);
        LinearLayout note = (LinearLayout) findViewById(R.id.note);
        LinearLayout pic = (LinearLayout) findViewById(R.id.pics);




        /* getting the building_id */
        Intent intent=getIntent();
        building_id=intent.getIntExtra("building_id", -1);
        Log.d("Title_building_id", String.valueOf(building_id));
        building=handler.getBuilding(building_id);
        Log.d("title_building_cicle_id",String.valueOf(building.getCicle_id()));
        final int building_sql=building.getSql_building_id();
        Log.d("title_build_sql",String.valueOf(building_sql));

        //Getting sharedPreferece
        SharedPreferences pref=getSharedPreferences("app_data", Context.MODE_PRIVATE);
        cicle_id=pref.getInt("cicle_id",-1);
        Log.d("title_pref_cicle-id",String.valueOf(cicle_id));
        cicle_sql=pref.getInt("cicle_sql",-1);
        Log.d("title_pref_cicle_sql",String.valueOf(cicle_sql));

        //Setting shared preference
        SharedPreferences preferences=getSharedPreferences("app_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putInt("building_id",building_id);
        editor.putInt("building_sql",building_sql);
        editor.commit();

        //getting sharedpreference and user_id
        equipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TitleBuilding.this, Equipment.class);
                intent.putExtra("cicle_id",building.getCicle_id());
                intent.putExtra("building_id",building.getBuilding_id());
                finish();
                startActivity(intent);

            }
        });

        animals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TitleBuilding.this,Animals.class);
                intent.putExtra("cicle_id",building.getCicle_id());
                intent.putExtra("building_id",building.getBuilding_id());
                finish();
                startActivity(intent);
            }
        });

        worker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TitleBuilding.this, Worker.class);
                intent.putExtra("cicle_id",building.getCicle_id());
                intent.putExtra("building_id",building.getBuilding_id());
                finish();
                startActivity(intent);
            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TitleBuilding.this, FoodConsommation.class);
                intent.putExtra("cicle_id",building.getCicle_id());
                intent.putExtra("building_id",building.getBuilding_id());
                finish();
                startActivity(intent);
            }
        });

        medical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TitleBuilding.this, Medical.class);
                intent.putExtra("cicle_id",building.getCicle_id());
                intent.putExtra("building_id",building.getBuilding_id());
                finish();
                startActivity(intent);
            }
        });

        finianse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TitleBuilding.this, Finianse.class);
                intent.putExtra("cicle_id",building.getCicle_id());
                intent.putExtra("building_id",building.getBuilding_id());
                finish();
                startActivity(intent);
            }
        });

        egg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TitleBuilding.this, egg_extra_screen.class);
                intent.putExtra("cicle_id",building.getCicle_id());
                intent.putExtra("building_id",building.getBuilding_id());
                startActivity(intent);
            }
        });

        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TitleBuilding.this, Temp.class));
            }
        });

        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TitleBuilding.this, note.class));
            }
        });

        death.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TitleBuilding.this, Death.class);
                intent.putExtra("cicle_id",building.getCicle_id());
                intent.putExtra("building_id",building.getBuilding_id());
                finish();
                startActivity(intent);
            }
        });

        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TitleBuilding.this, MainActivity.class);
                i.putExtra("cicle_id",building.getCicle_id());
                i.putExtra("building_id",building.getBuilding_id());
                finish();
                startActivity(i);
            }
        });

        alive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TitleBuilding.this, Alive.class);
                intent.putExtra("cicle_id",building.getCicle_id());
                intent.putExtra("building_id",building.getBuilding_id());
                startActivity(intent);



            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                //finish();
                Intent i = new Intent(TitleBuilding.this, ShowBuilding.class);
                i.putExtra("building_id", building_id);
                finish();
                startActivity(i);

                return true;

            default:
                Intent i2 = new Intent(TitleBuilding.this, Building.class);
                i2.putExtra("position", building.getCicle_id());
                finish();
                startActivity(i2);
                return true;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_title_building, menu);
        return true;
    }



    @Override
    public void onBackPressed(){
        Intent i2 = new Intent(TitleBuilding.this, Building.class);
        i2.putExtra("position", building.getCicle_id());
        finish();
        startActivity(i2);
    }

}
