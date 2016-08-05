package com.example.sneh.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import Adapters.EachListAdapter;

public class Equipment extends AppCompatActivity {

    int cicle_id, building_id;
    int cicle_sql,building_sql;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);

        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());


        //shared preferences
        SharedPreferences preferences=getSharedPreferences("app_data", Context.MODE_PRIVATE);
        SharedPreferences preferences1=getSharedPreferences("setting_data", Context.MODE_PRIVATE);
        setting_class sc=handler.get_all_setting();
        cicle_id=preferences.getInt("cicle_id", -1);
        building_id=preferences.getInt("building_id", -1);
        cicle_sql=preferences.getInt("cicle_sql", -1);
        building_sql=preferences.getInt("building_sql",-1);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_equipment);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*getting building_id and cicle_id*/
        Log.d("In_equipment_cicle_id",String.valueOf(cicle_id));
        Log.d("In_equip_building_id", String.valueOf(building_id));

        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.add_equi);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Equipment.this, AddEquipment.class);
                intent.putExtra("cicle_id", cicle_id);
                intent.putExtra("building_id", building_id);
                finish();
                startActivity(intent);

            }
        });
        List<equipment_> equipment_list=handler.get_all_equipment(cicle_id,building_id);
        Log.d("equip_list_size",String.valueOf(equipment_list.size()));
        String[] text1 =new String[equipment_list.size()];
        String[] text2 =new String[equipment_list.size()];
        String[] text6 = new String[equipment_list.size()];
        String[]text4 =new String[equipment_list.size()];
        String[] text5 =new String[equipment_list.size()];
        int[] equip_id=new int[equipment_list.size()];
        for(int i=0;i<equipment_list.size();i++){
            text1[i]=equipment_list.get(i).getDesignation();
            text2[i]="";
            int amount=(equipment_list.get(i).getPrice())*(equipment_list.get(i).getQuantity());
            text6[i]=String.valueOf(amount)+" "+sc.getMoney_format();
            text4[i]="type";
            text5[i]=equipment_list.get(i).getType();
            equip_id[i]=equipment_list.get(i).getEquip_id();
        }
        ListView lv = (ListView) findViewById(R.id.equi_list);
        lv.setAdapter(new EachListAdapter(Equipment.this , 0 ,text1,text2,text6,text4,text5,equip_id));

        TextView number = (TextView) findViewById(R.id.textView13);
        number.setText("Equipments (" + equipment_list.size() + ")");
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