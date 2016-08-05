package com.example.sneh.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import Adapters.FinianseListAdapter;

public class Finianse extends AppCompatActivity {
    int cicle_id,building_id;
    int cicle_sql,building_sql;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences preferences=getSharedPreferences("app_data", Context.MODE_PRIVATE);
        SharedPreferences setting_preferences = getSharedPreferences("setting_data", Context.MODE_PRIVATE);
        cicle_id=preferences.getInt("cicle_id", -1);
        building_id=preferences.getInt("building_id", -1);
        cicle_sql=preferences.getInt("cicle_sql", -1);
        building_sql=preferences.getInt("building_sql",-1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finianse);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button expense = (Button) findViewById(R.id.expense);
        Button dp = (Button) findViewById(R.id.dp);
        Button re = (Button) findViewById(R.id.re);
        final Button pdf_generate=(Button)findViewById(R.id.generate_pdf);
        TextView total=(TextView)findViewById(R.id.total_expense);
        TextView total_currency=(TextView)findViewById(R.id.total_currency);


        Log.d("finanace_cicle_id",String.valueOf(cicle_id));
        Log.d("finance_building_id", String.valueOf(building_id));

        expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Finianse.this, Expense.class);
                intent.putExtra("cicle_id", cicle_id);
                intent.putExtra("building_id", building_id);
                startActivity(intent);
                finish();
            }
        });

        dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Finianse.this,DownPayment.class);
                intent.putExtra("cicle_id",cicle_id);
                intent.putExtra("building_id",building_id);
                startActivity(intent);
                finish();
            }
        });

        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Finianse.this, ReturnedExpenses.class);
                intent.putExtra("cicle_id",cicle_id);
                intent.putExtra("building_id",building_id);
                startActivity(intent);
                finish();
            }
        });

        pdf_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pdf p = new pdf(Finianse.this, cicle_id, building_id);
                p.createPdf();
            }
        });

        //setting up db_handler
        db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        all_object_list all_object_list=handler.get_all_objects(cicle_id,building_id);

        total.setText(String.valueOf(all_object_list.all_expense()));
        total_currency.setText(setting_preferences.getString("money","no_money"));

        String[] heading={"Equipment Expense","Animal Expense","Worker Expense","Food Expense","Medical Expense"};
        List<returned_expense_class>returned_expense_classList=handler.get_all_returned_expense(cicle_id, building_id);
        List<additional_expense_class>add_expense_classList=handler.get_all_additional_expense(cicle_id,building_id);
        int a1=0,a2=0,a3=0,a4=0,a5=0;
        for(int i=0;i<add_expense_classList.size();i++) {
            if (add_expense_classList.get(i).getType().equals("Equipment"))
                a1 = add_expense_classList.get(i).getPrice_per_one()*add_expense_classList.get(i).getQuantity();
            if (add_expense_classList.get(i).getType().equals("Animal"))
                a2 =add_expense_classList.get(i).getPrice_per_one()*add_expense_classList.get(i).getQuantity();
            if (add_expense_classList.get(i).getType().equals("Worker"))
                a3 = add_expense_classList.get(i).getPrice_per_one() *add_expense_classList.get(i).getQuantity();
            if (add_expense_classList.get(i).getType().equals("Food"))
                a4 = add_expense_classList.get(i).getPrice_per_one()*add_expense_classList.get(i).getQuantity();
            if (add_expense_classList.get(i).getType().equals("Medical"))
                a5 = add_expense_classList.get(i).getPrice_per_one()*add_expense_classList.get(i).getQuantity();
        }
        for(int i=0;i<returned_expense_classList.size();i++) {
            if (returned_expense_classList.get(i).getType().equals("Equipment"))
                a1 =a1- (returned_expense_classList.get(i).getPrice()*returned_expense_classList.get(i).getQuantity());
            if (returned_expense_classList.get(i).getType().equals("Animal"))
                a2 = a2- (returned_expense_classList.get(i).getPrice()*returned_expense_classList.get(i).getQuantity());
            if (returned_expense_classList.get(i).getType().equals("Worker"))
                a3 =a3-  (returned_expense_classList.get(i).getPrice()*returned_expense_classList.get(i).getQuantity());
            if (returned_expense_classList.get(i).getType().equals("Food"))
                a4 = a4- (returned_expense_classList.get(i).getPrice()*returned_expense_classList.get(i).getQuantity());
            if (returned_expense_classList.get(i).getType().equals("Medical"))
                a5 = a5- (returned_expense_classList.get(i).getPrice()*returned_expense_classList.get(i).getQuantity());

        }

        String[] money={String.valueOf(all_object_list.get_equipment_expense()+a1),String.valueOf(all_object_list.get_animal_expense()+a2),String.valueOf(all_object_list.get_worker_expense()+a3),String.valueOf(all_object_list.get_food_expense()+a4),String.valueOf(all_object_list.get_medical_expense()+a5)};
        String[] currency={setting_preferences.getString("money","no_money"),setting_preferences.getString("money","no_money"),setting_preferences.getString("money","no_money"),setting_preferences.getString("money","no_money"),setting_preferences.getString("money","no_money")};
        ListView listView=(ListView)findViewById(R.id.finance_listview);
        listView.setAdapter(new FinianseListAdapter(this,heading,money,currency));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent=new Intent(Finianse.this,TitleBuilding.class);
        intent.putExtra("cicle_id",cicle_id);
        intent.putExtra("building_id",building_id);
        startActivity(intent);
        finish();
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent intent=new Intent(Finianse.this,TitleBuilding.class);
        intent.putExtra("cicle_id",cicle_id);
        intent.putExtra("building_id",building_id);
        startActivity(intent);
        finish();
    }

}