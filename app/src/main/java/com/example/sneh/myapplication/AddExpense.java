package com.example.sneh.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddExpense extends AppCompatActivity {

    int year_x, month_x, day_x;
    private TextView date_text;
    private Calendar calendar;
    setting_class sc;

    int cicle_id,building_id,additional_expense_id;
    int cicle_sql,building_sql;
    int user_id;
    int sql_flag=0;
    int add_expense_sql=0;
    private TextView currency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

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

        //setting up db
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        sc = handler.get_all_setting();
       // setting_class sc=handler.get_all_setting();
        currency=(TextView)findViewById(R.id.add_equipment_currency);
        currency.setText(sc.getMoney_format());
        //setting up intent to getting cicle_id and building_id
        Intent intent=getIntent();
        //cicle_id=intent.getIntExtra("cicle_id",-1);
        //building_id=intent.getIntExtra("building_id",-1);
        additional_expense_id=intent.getIntExtra("additional_expense_id",-1);
        Log.d("add_expense_cicle_id",String.valueOf(cicle_id));
        Log.d("add_expense_build_id",String.valueOf(building_id));
        Log.d("add_expense_add_exp_id",String.valueOf(additional_expense_id));
        Log.d("add_expense_build_id",String.valueOf(building_sql));
        Log.d("add_expense_add_exp_id",String.valueOf(add_expense_sql));
        Log.d("add_expense_cicle_id",String.valueOf(cicle_sql));
        Log.d("add_expense_user_id",String.valueOf(user_id));

        date_text = (TextView) findViewById(R.id.add_expense_start_date);
        calendar=Calendar.getInstance();
        year_x=calendar.get(Calendar.YEAR);
        month_x=calendar.get(Calendar.MONTH);
        day_x=calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year_x, month_x + 1, day_x);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_add_expense);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final TextView price = (TextView) findViewById(R.id.price_add_exp);
        final TextView quantity = (TextView) findViewById(R.id.quantity_add_exp);
        final TextView amount = (TextView) findViewById(R.id.amount_add_exp);
        final TextView designation=(TextView)findViewById(R.id.add_expense_designation);
        String[] array_spinner = new String[6];
        array_spinner[0] = "Type(Select)";
        array_spinner[1] = "Equipment";
        array_spinner[2] = "Animal";
        array_spinner[3] = "Worker";
        array_spinner[4] = "Food";
        array_spinner[5] = "Medical";

        final Spinner spinner = (Spinner) findViewById(R.id.add_expense_type);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, array_spinner);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        if(additional_expense_id!=-1){
            additional_expense_class additional_expense=handler.getAdditionalExpense(additional_expense_id);
            for(int i=0;i<array_spinner.length;i++){
                if(array_spinner[i].equals(additional_expense.getType())){
                    spinner.setSelection(i);
                }
            }
            designation.setText(additional_expense.getDesignation());
            date_text.setText(additional_expense.getDate());
            quantity.setText(String.valueOf(additional_expense.getQuantity()));
            price.setText(String.valueOf(additional_expense.getPrice_per_one()));
            add_expense_sql=additional_expense.getSql_additional_expense_id();
            sql_flag=additional_expense.getFlag();
        }

        quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            int total,_price,_capacity;
            @Override
            public void afterTextChanged(Editable s) {
                if(!price.getText().toString().isEmpty())
                    _price = Integer.parseInt(price.getText().toString());
                if(!quantity.getText().toString().isEmpty())
                    _capacity = Integer.parseInt(quantity.getText().toString());
                total=_price*_capacity;
                amount.setText(String.valueOf(total)+" "+sc.getMoney_format());
            }
        });


        price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            int total,_price,_capacity;
            @Override
            public void afterTextChanged(Editable s) {
                if(!price.getText().toString().isEmpty())
                    _price = Integer.parseInt(price.getText().toString());
                if(!quantity.getText().toString().isEmpty())
                    _capacity = Integer.parseInt(quantity.getText().toString());
                total=_price*_capacity;
                amount.setText(String.valueOf(total)+" "+sc.getMoney_format());
            }
        });

        Button save = (Button) findViewById(R.id.save_add_expense);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(spinner.getSelectedItemPosition() == 0 || designation.getText().toString().isEmpty() || quantity.getText().toString().isEmpty() || price.getText().toString().isEmpty()){
                    Toast.makeText(AddExpense.this, getResources().getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show();
                }else {
                    if (additional_expense_id == -1) {
                        Log.d("add_ae_add", "add");
                        additional_expense_class additional_expense = new additional_expense_class();
                        additional_expense.setSql_additional_expense_id(add_expense_sql);
                        additional_expense.setSql_cicle_id(cicle_sql);
                        additional_expense.setSql_building_id(building_sql);
                        additional_expense.setUser_id(user_id);
                        additional_expense.setCicle_id(cicle_id);
                        additional_expense.setBuilding_id(building_id);
                        additional_expense.setType(spinner.getSelectedItem().toString());
                        additional_expense.setDesignation(designation.getText().toString());
                        additional_expense.setDate(date_text.getText().toString());
                        additional_expense.setQuantity(Integer.parseInt(quantity.getText().toString()));
                        additional_expense.setPrice_per_one(Integer.parseInt(price.getText().toString()));
                        additional_expense.setFlag(0);
                        int value = (int) handler.createAdditionalExpense(additional_expense);
                        Log.d("ae_count", String.valueOf(handler.getAnimalCount(cicle_id, building_id)));
                        Log.d("value", String.valueOf(value));
                        if (value > 0) {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.succesfully_updated), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddExpense.this, Expense.class);
                            intent.putExtra("id", value);
                            finish();
                            startActivity(intent);
                        } else {
                            Toast.makeText(AddExpense.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.d("add_dp_update", "updating");
                        additional_expense_class additional_expense = new additional_expense_class();
                        additional_expense.setSql_additional_expense_id(add_expense_sql);
                        additional_expense.setSql_cicle_id(cicle_sql);
                        additional_expense.setSql_building_id(building_sql);
                        additional_expense.setUser_id(user_id);
                        additional_expense.setAdditional_expense_id(additional_expense_id);
                        additional_expense.setCicle_id(cicle_id);
                        additional_expense.setBuilding_id(building_id);
                        additional_expense.setType(spinner.getSelectedItem().toString());
                        additional_expense.setDesignation(designation.getText().toString());
                        additional_expense.setDate(date_text.getText().toString());
                        additional_expense.setQuantity(Integer.parseInt(quantity.getText().toString()));
                        additional_expense.setPrice_per_one(Integer.parseInt(price.getText().toString()));

                        if (sql_flag != 0)
                            sql_flag = 1;

                        additional_expense.setFlag(sql_flag);
                        Log.d("update_ae_flag", String.valueOf(sql_flag));

                        int value = handler.updateAdditionalExpense(additional_expense);

                        if (value > 0) {
                            Toast.makeText(getApplicationContext(),getResources().getString(R.string.succesfully_updated), Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(AddExpense.this, Expense.class);
                            intent.putExtra("id", value);
                            finish();
                            startActivity(intent);
                        }
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
        Intent intent=new Intent(AddExpense.this,Expense.class);
        intent.putExtra("cicle_id",cicle_id);
        intent.putExtra("building_id",building_id);
        finish();
        startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent intent=new Intent(AddExpense.this,Expense.class);
        intent.putExtra("cicle_id",cicle_id);
        intent.putExtra("building_id",building_id);
        finish();
        startActivity(intent);
    }
}
