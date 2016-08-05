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

public class DownPayment extends AppCompatActivity {

    int cicle_id, building_id;
    int cicle_sql,building_sql;
    int user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_payment);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_dp);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        //setting up intent and getting cicle_id and building_id
        Intent intent=getIntent();
        //cicle_id=intent.getIntExtra("cicle_id", -1);
        //building_id=intent.getIntExtra("building_id",-1);
        Log.d("Down_payment_cicle_id",String.valueOf(cicle_id));
        Log.d("Down_pay_building_id", String.valueOf(building_id));

        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.add_dp);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DownPayment.this, AddDownPayment.class);
                intent.putExtra("cicle_id", cicle_id);
                intent.putExtra("building_id", building_id);
                finish();
                startActivity(intent);
            }
        });
        //setting up db
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        setting_class sc = handler.get_all_setting();
        List<down_payment_class> down_payment_list=handler.get_all_down_payment(cicle_id,building_id);

        String[] text1 = new String[down_payment_list.size()];//{"worker", "Designation 2", "Designation 3"};
        String[] text2 = new String[down_payment_list.size()];//{"worker_name","",""};
        String[] text3 = new String[down_payment_list.size()];//{"date","",""};
        String[] text4 = new String[down_payment_list.size()];//{"Type","Type","Type"};
        String[] text5 = new String[down_payment_list.size()];//{"amount", "equipment 2", "equipment 3"}
        int[] id=new int[down_payment_list.size()];
        for(int i=0;i<down_payment_list.size();i++){
            text1[i]="Worker";
            text2[i]=down_payment_list.get(i).getWorker_name();
            text3[i]=down_payment_list.get(i).getDate();
            text4[i]="Amount";
            text5[i]=String.valueOf(down_payment_list.get(i).getPrice())+" "+ sc.getMoney_format();
            id[i]=down_payment_list.get(i).getDown_payment_id();
        }
        ListView lv = (ListView) findViewById(R.id.dp_list);
        lv.setAdapter(new EachListAdapter(DownPayment.this, 6, text1, text2, text3, text4, text5,id));

        TextView number = (TextView) findViewById(R.id.textView13);
        number.setText("Down Payments (" + down_payment_list.size() + ")");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent=new Intent(getApplicationContext(),Finianse.class);
        intent.putExtra("cicle_id",cicle_id);
        intent.putExtra("building_id",building_id);
        finish();
        startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent intent=new Intent(getApplicationContext(),Finianse.class);
        intent.putExtra("cicle_id",cicle_id);
        intent.putExtra("building_id",building_id);
        finish();
        startActivity(intent);
    }

}