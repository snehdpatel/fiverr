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

public class AddReturnedExpenses extends AppCompatActivity {

    int year_x, month_x, day_x;
    private TextView date_text;
    private Calendar calendar;
    int cicle_id,building_id,returned_expense_id;
    setting_class sc;
    private TextView currency;
    private int cicle_sql;
    private int building_sql;
    private int user_id;
    private int sql_flag=0;
    private int returned_sql=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_returned_expenses);
        //setting up intent

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
        Log.d("return_expenses_user_id",String.valueOf(user_id));
        Intent intent=getIntent();
      //  cicle_id=intent.getIntExtra("cicle_id",-1);
     //   building_id=intent.getIntExtra("building_id",-1);
        returned_expense_id=intent.getIntExtra("returned_expense_id",-1);
        Log.d("add_re_cicle_id",String.valueOf(cicle_id));
        Log.d("add_re_building_id",String.valueOf(building_id));
        Log.d("add_re_re_id",String.valueOf(returned_expense_id));
        Log.d("add_re_cicle_sql",String.valueOf(cicle_sql));
        Log.d("add_re_build_sql",String.valueOf(building_sql));
        Log.d("add_re_user_id",String.valueOf(user_id));
        Log.d("add_re_re_sql",String.valueOf(returned_sql));

        //setting up db_handler
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        sc = handler.get_all_setting();

        currency=(TextView)findViewById(R.id.add_equipment_currency);
        currency.setText(sc.getMoney_format());
        String[] array_spinner = new String[6];
        array_spinner[0] = "Type(Select)";
        array_spinner[1] = "Equipment";
        array_spinner[2] = "Animal";
        array_spinner[3] = "Worker";
        array_spinner[4] = "Food";
        array_spinner[5] = "Medical";
        final Spinner spinner = (Spinner) findViewById(R.id.add_re_type);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, array_spinner);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        date_text = (TextView) findViewById(R.id.add_re_start_date);
        calendar=Calendar.getInstance();
        year_x=calendar.get(Calendar.YEAR);
        month_x=calendar.get(Calendar.MONTH);
        day_x=calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year_x, month_x + 1, day_x);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_add_re);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final TextView price = (TextView) findViewById(R.id.price_add_re);
        final TextView quantity = (TextView) findViewById(R.id.quantity_add_re);
        final TextView amount = (TextView) findViewById(R.id.amount_add_re);

        if(returned_expense_id!=-1){
            returned_expense_class returned_expense=handler.getReturnedExpense(returned_expense_id);
            for(int i=0;i<array_spinner.length;i++){
                if(array_spinner[i].equals(returned_expense.getType())){
                    spinner.setSelection(i);
                }
            }
            date_text.setText(returned_expense.getDate());
            quantity.setText(String.valueOf(returned_expense.getQuantity()));
            price.setText(String.valueOf(returned_expense.getPrice()));
            returned_sql=returned_expense.getSql_returned_expense_id();
            sql_flag=returned_expense.getFlag();
        }
        Log.d("add_re_cicle_id",String.valueOf(cicle_id));
        Log.d("add_re_building_id",String.valueOf(building_id));
        Log.d("add_re_re_id",String.valueOf(returned_expense_id));
        Log.d("add_re_cicle_sql",String.valueOf(cicle_sql));
        Log.d("add_re_build_sql",String.valueOf(building_sql));
        Log.d("add_re_user_id",String.valueOf(user_id));
        Log.d("add_re_re_sql",String.valueOf(returned_sql));


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

        Button save = (Button) findViewById(R.id.save_add_re);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spinner.getSelectedItemPosition() == 0 || quantity.getText().toString().isEmpty() || price.getText().toString().isEmpty()){
                    Toast.makeText(AddReturnedExpenses.this, getResources().getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show();
                }else {
                    if (returned_expense_id == -1) {
                        Log.d("add_re_add", "add");
                        returned_expense_class returned_expense = new returned_expense_class();
                        returned_expense.setSql_returned_expense_id(returned_sql);
                        returned_expense.setSql_cicle_id(cicle_sql);
                        returned_expense.setSql_building_id(building_sql);
                        returned_expense.setUser_id(user_id);
                        returned_expense.setCicle_id(cicle_id);
                        returned_expense.setBuilding_id(building_id);
                        returned_expense.setType(spinner.getSelectedItem().toString());
                        returned_expense.setDate(date_text.getText().toString());
                        returned_expense.setQuantity(Integer.parseInt(quantity.getText().toString()));
                        returned_expense.setPrice(Integer.parseInt(price.getText().toString()));
                        returned_expense.setFlag(0);
                        int value = (int) handler.createReturnedExpense(returned_expense);
                        Log.d("ae_count", String.valueOf(handler.countReturnedExpense(cicle_id, building_id)));
                        Log.d("value", String.valueOf(value));
                        if (value > 0) {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.succesfully_updated), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddReturnedExpenses.this, ReturnedExpenses.class);
                            intent.putExtra("id", value);
                            finish();
                            startActivity(intent);
                        } else {
                            //Toast.makeText(AddReturnedExpenses.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.d("add_dp_update", "updating");
                        returned_expense_class returned_expense = new returned_expense_class();
                        returned_expense.setSql_returned_expense_id(returned_sql);
                        returned_expense.setSql_cicle_id(cicle_sql);
                        returned_expense.setSql_building_id(building_sql);
                        returned_expense.setUser_id(user_id);
                        returned_expense.setReturned_expense_id(returned_expense_id);
                        returned_expense.setCicle_id(cicle_id);
                        returned_expense.setBuilding_id(building_id);
                        returned_expense.setType(spinner.getSelectedItem().toString());
                        returned_expense.setDate(date_text.getText().toString());
                        returned_expense.setQuantity(Integer.parseInt(quantity.getText().toString()));
                        returned_expense.setPrice(Integer.parseInt(price.getText().toString()));
                        if (sql_flag != 0) {
                            sql_flag = 1;
                        }
                        returned_expense.setFlag(sql_flag);
                        Log.d("update_re_sql_flag", String.valueOf(sql_flag));
                        int value = handler.updateReturnedExpense(returned_expense);

                        if (value > 0) {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.succesfully_updated), Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(AddReturnedExpenses.this, ReturnedExpenses.class);
                            intent.putExtra("id", value);
                            finish();
                            startActivity(intent);
                        }
                            //Toast.makeText(AddReturnedExpenses.this, "Error in updating", Toast.LENGTH_SHORT).show();
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
        Intent intent=new Intent(AddReturnedExpenses.this,ReturnedExpenses.class);
        intent.putExtra("cicle_id",cicle_id);
        intent.putExtra("building_id",building_id);
        finish();
        startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent intent=new Intent(AddReturnedExpenses.this,ReturnedExpenses.class);
        intent.putExtra("cicle_id",cicle_id);
        intent.putExtra("building_id",building_id);
        finish();
        startActivity(intent);
    }

}
