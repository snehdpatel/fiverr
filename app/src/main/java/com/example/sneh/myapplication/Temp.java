package com.example.sneh.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Adapters.EachListAdapter;
import Adapters.TempAdapter;

public class Temp extends AppCompatActivity {

    ListView lv;
    int building_id,cicle_id,temp_id;
    String Date;
    EditText temp_, lux_, humidity_;
    int first = 0;
    int cicle_sql,building_sql,user_id;
    int temp_sql=0,sql_flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences preferences=getSharedPreferences("app_data", Context.MODE_PRIVATE);
        cicle_id=preferences.getInt("cicle_id", -1);
        building_id=preferences.getInt("building_id", -1);
        cicle_sql=preferences.getInt("cicle_sql", -1);
        building_sql=preferences.getInt("building_sql",-1);

        SharedPreferences preferences1=getSharedPreferences("user_data", Context.MODE_PRIVATE);
        user_id=preferences1.getInt("user_id",-1);

        temp_ = (EditText) findViewById(R.id.temp);
        lux_ = (EditText) findViewById(R.id.lux);
        humidity_ = (EditText) findViewById(R.id.humidity);
        Button save = (Button) findViewById(R.id.temp_screen_button);
        Date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

        //Setting db_handler
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());

        /*getting building_id and cicle_id*/
        Intent intent=getIntent();
        //cicle_id=intent.getIntExtra("cicle_id", -1);
        //building_id=intent.getIntExtra("building_id",-1);
        temp_id=intent.getIntExtra("temp_id",-1);

        Log.d("Add_temp_temp_id", String.valueOf(temp_id));
        Log.d("Add_temp_cicle_id", String.valueOf(cicle_id));
        Log.d("Add_temp_build_id",String.valueOf(building_id));
        Log.d("Add_temp_cicle_sql",String.valueOf(cicle_sql));
        Log.d("Add_temp_build_sql",String.valueOf(building_sql));
        //Log.d("Add_B_egg_egg_sql",String.valueOf(egg_sql));
        Log.d("Add_temp_user_id",String.valueOf(user_id));
        Log.d("Add_temp_egg_flag",String.valueOf(sql_flag));


        if(first == 0){
            List<temp_> temp_list=handler.getAllTemp(cicle_id, building_id);

            String[] text1 =new String[temp_list.size()];
            String[] text2 =new String[temp_list.size()];
            String[] text3 =new String[temp_list.size()];
            String[] text4 =new String[temp_list.size()];

            for(int i=0;i<temp_list.size();i++){
                text1[i]=String.valueOf(temp_list.get(i).getTemp());
                text2[i]=String.valueOf(temp_list.get(i).getLux());
                text3[i]=String.valueOf(temp_list.get(i).getHumidity());
                text4[i]=temp_list.get(i).getDate();
            }

            lv = (ListView) findViewById(R.id.temp_list);
            lv.setAdapter(new TempAdapter(Temp.this, text1, text2, text3, text4));

            first = 1;
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                temp_ temp=new temp_();
                temp.setSql_temp_id(temp_sql);
                temp.setSql_cicle_id(cicle_sql);
                temp.setSql_building_id(building_sql);
                temp.setUser_id(user_id);
                temp.setCicle_id(cicle_id);
                temp.setBuilding_id(building_id);
                temp.setTemp(Integer.parseInt(String.valueOf(temp_.getText())));
                temp.setLux(Integer.parseInt(String.valueOf(lux_.getText())));
                temp.setHumidity(Integer.parseInt(String.valueOf(humidity_.getText())));
                temp.setDate(Date);
                temp.setFlag(0);
                handler.createTemp(temp);

                List<temp_> temp_list=handler.getAllTemp(cicle_id, building_id);

                String[] text1 =new String[temp_list.size()];
                String[] text2 =new String[temp_list.size()];
                String[] text3 =new String[temp_list.size()];
                String[] text4 =new String[temp_list.size()];

                for(int i=0;i<temp_list.size();i++){
                    Log.d("Temp_id_"+temp_list.get(i).getTemp_id(),String.valueOf(temp_list.get(i).getFlag()));
                    Log.d("Temp_sql_id_"+temp_list.get(i).getTemp_id(),String.valueOf(temp_list.get(i).getSql_temp_id()));
                    text1[i]=String.valueOf(temp_list.get(i).getTemp());
                    text2[i]=String.valueOf(temp_list.get(i).getLux());
                    text3[i]=String.valueOf(temp_list.get(i).getHumidity());
                    text4[i]=temp_list.get(i).getDate();
                }

                lv = (ListView) findViewById(R.id.temp_list);
                lv.setAdapter(new TempAdapter(Temp.this, text1, text2, text3, text4));
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
