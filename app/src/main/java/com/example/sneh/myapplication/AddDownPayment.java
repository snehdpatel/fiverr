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

public class AddDownPayment extends AppCompatActivity {

    int year_x, month_x, day_x;
    private TextView date_text;
    private Calendar calendar;
    int cicle_id;
    int building_id;
    int down_payment_id;
    int cicle_sql,building_sql;
    int user_id;
    int sql_flag=0;
    int down_payment_sql=0;
    int worker_id=0;
    EditText price;
    private TextView currency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_down_payment);

        date_text = (TextView) findViewById(R.id.add_dp_start_date);
        calendar=Calendar.getInstance();
        year_x=calendar.get(Calendar.YEAR);
        month_x=calendar.get(Calendar.MONTH);
        day_x=calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year_x, month_x + 1, day_x);

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

        //setting up dp
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        setting_class sc=handler.get_all_setting();
        currency=(TextView)findViewById(R.id.add_equipment_currency);
        currency.setText(sc.getMoney_format());
        //setting up intent to get building_id and cicle_id
        Intent intent=getIntent();
        //final int cicle_id=intent.getIntExtra("cicle_id", -1);
        //final int building_id=intent.getIntExtra("building_id",-1);
        down_payment_id=intent.getIntExtra("down_payment_id",-1);

        //handler on Create Table

        final List<worker_class> worker_list=handler.get_all_worker(cicle_id,building_id);
        String[] array_spinner = new String[worker_list.size()];
        for(int i=0;i<worker_list.size();i++){
            array_spinner[i]=worker_list.get(i).getName();
        }
        final Spinner spinner = (Spinner) findViewById(R.id.add_dp_type);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, array_spinner);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_add_dp);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //setting for adding price
        price=(EditText)findViewById(R.id.add_downpayment_price);
        if(down_payment_id!=-1){
            down_payment_class down_payment=handler.getDownPayment(down_payment_id);
            for(int i=0;i<array_spinner.length;i++){
                if(array_spinner[i].equals(down_payment.getWorker_name())){
                    spinner.setSelection(i);
                }
            }
            Log.d("dp_flag", String.valueOf(down_payment.getFlag()));
            down_payment_sql=down_payment.getSql_down_payment_id();
            sql_flag=down_payment.getFlag();
            date_text.setText(down_payment.getDate());
            price.setText(String.valueOf(down_payment.getPrice()));
            worker_id=down_payment.getWorker_id();
        }

        Log.d("add_DP_cicle_id", String.valueOf(cicle_id));
        Log.d("add_Dp_building_id", String.valueOf(building_id));
        Log.d("add_dp_dp_id", String.valueOf(down_payment_id));
        Log.d("add_DP_cicle_sql", String.valueOf(cicle_sql));
        Log.d("add_Dp_building_sql", String.valueOf(building_sql));
        Log.d("add_user_id",String.valueOf(user_id));
        Log.d("add_DP_DP_sql",String.valueOf(down_payment_sql));
        Log.d("add_dp_dp_flag",String.valueOf(sql_flag));
        Button save = (Button) findViewById(R.id.save_add_dp);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(price.getText().toString().isEmpty()){
                    Toast.makeText(AddDownPayment.this, getResources().getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show();
                }else {
                    if (down_payment_id == -1) {
                        Log.d("add_dp_add", "add");
                        down_payment_class down_payment = new down_payment_class();
                        down_payment.setUser_id(user_id);
                        down_payment.setSql_cicle_id(cicle_sql);
                        down_payment.setSql_building_id(building_sql);
                        down_payment.setSql_down_payment_id(down_payment_sql);
                        down_payment.setCicle_id(cicle_id);
                        down_payment.setBuilding_id(building_id);
                        down_payment.setWorker_name(spinner.getSelectedItem().toString());
                        down_payment.setDate(date_text.getText().toString());
                        down_payment.setPrice(Integer.parseInt(price.getText().toString()));
                        for (int i = 0; i < worker_list.size(); i++) {
                            if (worker_list.get(i).getName().equals(down_payment.getWorker_name())) {
                                worker_class worker = worker_list.get(i);
                                worker.setSalary(worker.getSalary() - down_payment.getPrice());
                                down_payment.setWorker_id(worker_list.get(i).getWorker_id());
                                handler.updateWorker(worker);
                            }
                        }
                        down_payment.setFlag(0);

                        long value = handler.createDownPayment(down_payment);
                        Log.d("value", String.valueOf(value));
                        if (value > 0) {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.succesfully_created), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddDownPayment.this, DownPayment.class);
                            intent.putExtra("id", (int) value);
                            finish();
                            startActivity(intent);
                        } else {
                            //Toast.makeText(AddDownPayment.this,getResources().getString(R.string.err), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.d("add_dp_update", "updating");
                        down_payment_class down_payment = new down_payment_class();
                        down_payment.setUser_id(user_id);
                        down_payment.setSql_cicle_id(cicle_sql);
                        down_payment.setSql_building_id(building_sql);
                        down_payment.setSql_down_payment_id(down_payment_sql);
                        down_payment.setDown_payment_id(down_payment_id);
                        down_payment.setCicle_id(cicle_id);
                        down_payment.setBuilding_id(building_id);
                        down_payment.setWorker_name(spinner.getSelectedItem().toString());
                        down_payment.setDate(date_text.getText().toString());
                        down_payment.setPrice(Integer.parseInt(price.getText().toString()));
                        down_payment.setWorker_id(worker_id);
                        if (sql_flag != 0) {
                            sql_flag = 1;
                        }
                        Log.d("update_dp_sql_flag", String.valueOf(sql_flag));
                        down_payment.setFlag(sql_flag);
                        int value = handler.updateDownPayment(down_payment);

                        if (value > 0) {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.succesfully_updated), Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(AddDownPayment.this, DownPayment.class);
                            intent.putExtra("id", value);
                            finish();
                            startActivity(intent);
                        }
                            //Toast.makeText(AddDownPayment.this, "Error in updating", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

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
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), DownPayment.class);
        intent.putExtra("cicle_id", cicle_id);
        intent.putExtra("building_id", building_id);
        finish();
        startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), DownPayment.class);
        intent.putExtra("cicle_id", cicle_id);
        intent.putExtra("building_id", building_id);
        finish();
        startActivity(intent);
    }
}