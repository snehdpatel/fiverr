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
import java.util.Objects;

import Adapters.EachListAdapter;

public class Expense extends AppCompatActivity {


    int cicle_id,building_id;
    int cicle_sql,building_sql;
    int user_id;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

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


        //setting up database
        db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        setting_class sc = handler.get_all_setting();

        //setting up intent and getting cicle_id and building_id
        Intent intent=getIntent();
        //cicle_id=intent.getIntExtra("cicle_id",-1);
        //building_id=intent.getIntExtra("building_id",-1);
        Log.d("expense_cicle_id", String.valueOf(cicle_id));
        Log.d("expense_building_id", String.valueOf(building_id));


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_add_expense);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.add_expense);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Expense.this, AddExpense.class);
                i.putExtra("cicle_id",cicle_id);
                i.putExtra("building_id",building_id);
                finish();
                startActivity(i);
            }
        });

        List<additional_expense_class> additional_expense_list=handler.get_all_additional_expense(cicle_id,building_id);

        String[] text1 = new String[additional_expense_list.size()];//{"Designation 1", "Designation 2", "Designation 3"};
        String[] text2 = new String[additional_expense_list.size()];//{"","",""};
        String[] text3 = new String[additional_expense_list.size()];//{"","",""};
        String[] text4 = new String[additional_expense_list.size()];//{"Type","Type","Type"};
        String[] text5 = new String[additional_expense_list.size()];//{"equipment 1", "equipment 2", "equipment 3"};
        int[] id=new int[additional_expense_list.size()];
        for(int i=0;i<additional_expense_list.size();i++){
            text1[i]="TYPE";
            text2[i]=additional_expense_list.get(i).getType();
            text3[i]=additional_expense_list.get(i).getDate();
            text4[i]="AMOUNT";
            int amount=additional_expense_list.get(i).getQuantity()*additional_expense_list.get(i).getPrice_per_one();
            text5[i]=String.valueOf(amount)+" "+ sc.getMoney_format();
            id[i]=additional_expense_list.get(i).getAdditional_expense_id();
            Log.d("expense_id",String .valueOf(id[i]));

        }
        ListView lv = (ListView) findViewById(R.id.expense_list);
        lv.setAdapter(new EachListAdapter(Expense.this, 5, text1, text2, text3, text4, text5,id));

        TextView number = (TextView) findViewById(R.id.textView13);
        number.setText("Additional Expenses (" + additional_expense_list.size() + ")");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent=new Intent(Expense.this,Finianse.class);
        intent.putExtra("cicle_id",cicle_id);
        intent.putExtra("building_id",building_id);
        finish();
        startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent intent=new Intent(Expense.this,Finianse.class);
        intent.putExtra("cicle_id",cicle_id);
        intent.putExtra("building_id",building_id);
        finish();
        startActivity(intent);
    }

}