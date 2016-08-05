package com.example.sneh.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class AddWorker extends AppCompatActivity {
    int worker_id;
    int year_x, month_x, day_x;
    private TextView date_text;
    private Calendar calendar;
    int cicle_id,building_id;
    int cicle_sql,building_sql;
    int user_id;
    private EditText name,address,phone,start_date,price;
    private int active;
    private TextView currency;
    private int worker_sql;
    private int flag=0;
    private String set_date;//for update purpose only
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_worker);

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


        date_text = (TextView) findViewById(R.id.add_worker_start_date);
        calendar=Calendar.getInstance();
        year_x=calendar.get(Calendar.YEAR);
        month_x=calendar.get(Calendar.MONTH);
        day_x=calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year_x, month_x + 1, day_x);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_add_worker);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //

        //spinner.setAdapter(new AddCicle.MyAdapter(AddWorker.this,R.layout.row,array_spinner));



        //setting intent and getting cicle_id building_id worker_id
        Intent intent=getIntent();
        worker_id=intent.getIntExtra("worker_id",-1);
        worker_sql=intent.getIntExtra("worker_sql",-1);
        if(worker_sql==-1){
            worker_sql=0;
        }
        active=intent.getIntExtra("type",-1);    //if worker_id!=-1 then update
        Log.d("Add_work_worker_id", String.valueOf(worker_id));
        Log.d("Add_work_worker_sql",String.valueOf(worker_sql));
        Log.d("Add_work_cicle_id", String.valueOf(cicle_id));
        Log.d("Add_work_building_id", String.valueOf(building_id));
        Log.d("Add_work_cicle_sql",String.valueOf(cicle_sql));
        Log.d("Add_work_building_sql",String.valueOf(building_sql));
        Log.d("Add_work_user_id",String.valueOf(user_id));
        //settting up db_handler
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        setting_class sc=handler.get_all_setting();
        currency=(TextView)findViewById(R.id.add_equipment_currency);
        currency.setText(sc.getMoney_format());
        //setting up the edit text
        name=(EditText)findViewById(R.id.add_worker_name);
        address=(EditText)findViewById(R.id.add_worker_address);
        phone=(EditText)findViewById(R.id.add_worker_phone);
        price=(EditText)findViewById(R.id.add_worker_price);

        final worker_class worker;
        //setting various edittext for updating
        if(worker_id!=-1){
            worker=handler.getWorker(worker_id);
            worker_sql=worker.getSql_worker_id();
            name.setText(worker.getName());
            address.setText(worker.getAddress());
            phone.setText(worker.getTel());
            date_text.setText(worker.getDate_start());
            price.setText(String.valueOf(worker.getPrice_per_day()));
            flag=worker.getFlag();
            set_date=worker.getLast_update();

        }
        Log.d("worker_flag", String.valueOf(flag));
        Log.d("Worker_sql", String.valueOf(worker_sql));
        Button save = (Button) findViewById(R.id.add_worker_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name.getText().toString().isEmpty() || address.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || date_text.getText().toString().isEmpty() || price.getText().toString().isEmpty()) {
                    Toast.makeText(AddWorker.this, getResources().getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show();
                } else {
                    if (worker_id != -1) {
                        Log.d("add_worker", "update");
                        worker_class worker = new worker_class();
                        worker.setSql_worker_id(worker_sql);
                        worker.setSql_cicle_id(cicle_sql);
                        worker.setSql_building_id(building_sql);
                        worker.setUser_id(user_id);
                        worker.setWorker_id(worker_id);
                        worker.setCicle_id(cicle_id);
                        worker.setBuilding_id(building_id);
                        worker.setName(name.getText().toString());
                        worker.setAddress(address.getText().toString());
                        worker.setTel(phone.getText().toString());
                        worker.setDate_start(date_text.getText().toString());
                        worker.setSetactive(active);
                        worker.setLast_update(set_date);
                        worker.setPrice_per_day(Integer.parseInt(price.getText().toString()));
                        if (flag != 0) {
                            flag = 1;
                        }
                        worker.setFlag(flag);
                        Log.d("add_worker_update_flag",String.valueOf(worker.getFlag()));
                        int row = handler.updateWorker(worker);
                        if (row == 1) {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.succesfully_updated), Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        Log.d("add_worker", "create_worker");
                        worker_class worker = new worker_class();
                        worker.setSql_worker_id(0);
                        worker.setSql_cicle_id(cicle_sql);
                        worker.setSql_building_id(building_sql);
                        worker.setUser_id(user_id);
                        worker.setCicle_id(cicle_id);
                        worker.setBuilding_id(building_id);
                        worker.setName(name.getText().toString());
                        worker.setAddress(address.getText().toString());
                        worker.setTel(phone.getText().toString());
                        worker.setDate_start(date_text.getText().toString());
                        worker.setPrice_per_day(Integer.parseInt(price.getText().toString()));
                        worker.setSalary(0);
                        worker.setSetactive(1);//1 for active 2 in active
                        worker.setLast_update(worker.getDate_start());
                        worker.setFlag(0);
                        Log.d("WORKERALL:", worker.getName() + worker.getSetactive() + worker.getAddress() + worker.getTel() + worker.getDate_start() + worker.getPrice_per_day());


                        worker_id = handler.CreateWorker(worker);
                        Log.d("add_work_worker_added", String.valueOf(worker_id));
                    }

                    Intent intent = new Intent(getApplicationContext(), Worker.class);
                    intent.putExtra("id", worker_id);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), Worker.class);
        intent.putExtra("cicle_id", cicle_id);
        intent.putExtra("building_id", building_id);
        finish();
        startActivity(intent);
        return true;
    }

    public void setDate(View view) {
        showDialog(999);
    }


    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year_x, month_x, day_x);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2 + 1, arg3);
        }
    };

    private void showDate(int year,int month,int day){
        date_text.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
    }

    @Override
    public void onBackPressed(){
        Intent intent=new Intent(AddWorker.this,Worker.class);
        intent.putExtra("cicle_id",cicle_id);
        intent.putExtra("building_id",building_id);
        finish();
        startActivity(intent);
    }

}
