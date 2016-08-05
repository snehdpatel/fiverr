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

import java.util.List;

import Adapters.EachListAdapter;
import Adapters.MedicalListAdapter;

public class Medical extends AppCompatActivity {

    int cicle_id, building_id;
    int cicle_sql,building_sql;
    int user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences preferences=getSharedPreferences("app_data", Context.MODE_PRIVATE);
        cicle_id=preferences.getInt("cicle_id", -1);
        building_id=preferences.getInt("building_id", -1);
        cicle_sql=preferences.getInt("cicle_sql", -1);
        building_sql=preferences.getInt("building_sql",-1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_medical);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.d("in_medical_cicle_sql", String.valueOf(cicle_sql));
        Log.d("in_medical_build_sql",String.valueOf(building_sql));
        Log.d("In_medical_cicle_id", String.valueOf(cicle_id));
        Log.d("In_medical_building_id", String.valueOf(building_id));

        //getting user_id
        SharedPreferences preferences1=getSharedPreferences("user_data", Context.MODE_PRIVATE);
        user_id=preferences1.getInt("user_id",-1);
        Log.d("in_medical_user_id",String.valueOf(user_id));


        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.add_medical);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Medical.this, AddMedical.class);
                intent.putExtra("cicle_id", cicle_id);
                intent.putExtra("building_id", building_id);
                finish();
                startActivity(intent);

            }
        });
        //Setting db_handler
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        setting_class sc = handler.get_all_setting();

        List<medical_> medical_list=handler.get_all_medical(cicle_id,building_id);
        Log.d("medical_list_size",String.valueOf(medical_list.size()));
        String[] text1 =new String[medical_list.size()];
        String[] text3 = new String[medical_list.size()];
        String[] text5 =new String[medical_list.size()];
        String[] text6 =new String[medical_list.size()];
        int[] medical_id=new int[medical_list.size()];
        for(int i=0;i<medical_list.size();i++){
            text1[i]=medical_list.get(i).getOperation_name();
            text3[i]=medical_list.get(i).getComment();
            text5[i]=medical_list.get(i).getDate();
            text6[i]=String.valueOf(medical_list.get(i).getPrice())+" " +sc.getMoney_format();
            Log.d("price_medical",String.valueOf(medical_list.get(i).getPrice()));
            medical_id[i]=medical_list.get(i).getMedical_id();
        }

        TextView number = (TextView) findViewById(R.id.textView13);
        number.setText("Medicals (" + medical_list.size() + ")");
        ListView lv = (ListView) findViewById(R.id.medical_list);
        lv.setAdapter(new MedicalListAdapter(Medical.this,medical_id, text1,text3,text5,text6));
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