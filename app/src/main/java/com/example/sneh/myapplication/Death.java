package com.example.sneh.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Adapters.EachListAdapter;

public class Death extends AppCompatActivity {

    int cicle_id, building_id;
    int cicle_sql,building_sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences=getSharedPreferences("app_data", Context.MODE_PRIVATE);
        cicle_id=preferences.getInt("cicle_id", -1);
        building_id=preferences.getInt("building_id", -1);
        cicle_sql=preferences.getInt("cicle_sql", -1);
        building_sql=preferences.getInt("building_sql",-1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_death);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_death);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Log.d("In_equipment_cicle_id", String.valueOf(cicle_id));
        Log.d("In_equip_building_id", String.valueOf(building_id));

        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());

        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.add_death);
        int animals = handler.count_animals(cicle_id,building_id);
        int deaths = handler.count_deaths(cicle_id, building_id);
        final int alives = animals - deaths;
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(getApplicationContext(), String.valueOf(animals), Toast.LENGTH_LONG).show();
                if(alives > 0) {
                    Intent intent = new Intent(Death.this, AddDeath.class);
                    intent.putExtra("cicle_id", cicle_id);
                    intent.putExtra("building_id", building_id);
                    finish();
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "There are no Animals", Toast.LENGTH_LONG).show();
                }

            }
        });
        //Setting db_handler

        List<death_> death_list=handler.get_all_death(cicle_id, building_id);
        Log.d("death_list_size",String.valueOf(death_list.size()));
        String[] text1 =new String[death_list.size()];
        String[] text2 =new String[death_list.size()];
        String[] text6 = new String[death_list.size()];
        String[]text4 =new String[death_list.size()];
        String[] text5 =new String[death_list.size()];
        int[] death_id=new int[death_list.size()];
        for(int i=0;i<death_list.size();i++){
            text1[i]="Death";
            text2[i]=String.valueOf(death_list.get(i).getDeath_no());
            //int amount=(equipment_list.get(i).getPrice())*(equipment_list.get(i).getQuantity());
            text6[i]=death_list.get(i).getDate();
            //text4[i]="type";
            //text5[i]=equipment_list.get(i).getType();
            death_id[i]=death_list.get(i).getDeath_id();
        }
        ListView lv = (ListView) findViewById(R.id.death_list);
        lv.setAdapter(new EachListAdapter(Death.this , 4 ,text1,text2,text6,text4,text5,death_id));

        TextView number = (TextView) findViewById(R.id.textView13);
        number.setText("Deaths (" + death_list.size() + ")");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent=new Intent(getApplicationContext(),TitleBuilding.class);
        intent.putExtra("cicle_id",cicle_id);
        intent.putExtra("building_id",building_id);
        finish();
        startActivity(intent);

        return true;
    }

    @Override
    public void onBackPressed(){
        Intent intent=new Intent(getApplicationContext(),TitleBuilding.class);
        intent.putExtra("cicle_id",cicle_id);
        intent.putExtra("building_id",building_id);
        finish();
        startActivity(intent);
    }

}
