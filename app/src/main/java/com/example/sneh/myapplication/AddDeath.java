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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddDeath extends AppCompatActivity {

    EditText no;
    TextView date;
    Button save;
    int year_x, month_x, day_x;
    private TextView date_text;
    private Calendar calendar;
    int building_id,cicle_id;
    int cicle_sql,building_sql;
    int user_id,sql_flag=0;
    int death_sql=0;
    int death_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences=getSharedPreferences("app_data", Context.MODE_PRIVATE);
        cicle_id=preferences.getInt("cicle_id", -1);
        building_id=preferences.getInt("building_id", -1);
        cicle_sql=preferences.getInt("cicle_sql", -1);
        building_sql=preferences.getInt("building_sql",-1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_death);

        SharedPreferences preferences1=getSharedPreferences("user_data", Context.MODE_PRIVATE);
        user_id=preferences1.getInt("user_id",-1);

        date_text = (TextView) findViewById(R.id.add_death_start_date);
        calendar=Calendar.getInstance();
        year_x=calendar.get(Calendar.YEAR);
        month_x=calendar.get(Calendar.MONTH);
        day_x=calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year_x, month_x + 1, day_x);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_add_death);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        save = (Button) findViewById(R.id.save_add_death);
        date=(TextView)findViewById(R.id.add_death_start_date);
        no=(EditText)findViewById(R.id.add_death_no);

        //Setting db_handler
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());

        /*getting building_id and cicle_id*/
        Intent intent=getIntent();

        death_id=intent.getIntExtra("death_id",-1);

        if(death_id!=-1){
            death_ death=handler.getDeath(death_id);
            date.setText(death.getDate());
            no.setText(String.valueOf(death.getDeath_no()));
            death_sql=death.getSql_death_id();
            sql_flag=death.getFlag();
        }
        Log.d("add_death_cicle_id", String.valueOf(cicle_id));
        Log.d("add_death_building_id",String.valueOf(building_id));
        Log.d("add_death_death_id",String.valueOf(death_id));
        Log.d("add_death_sqlcicle_id",String.valueOf(cicle_sql));
        Log.d("add_death_sqlbuild_id",String.valueOf(building_sql));
        Log.d("add__death_user_id", String.valueOf(user_id));
        Log.d("add_death_sql", String.valueOf(death_sql));


        int animals = handler.count_animals(cicle_id,building_id);
        int deaths = handler.count_deaths(cicle_id, building_id);
        final int alives = animals - deaths;

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(date.getText().toString().isEmpty() || no.getText().toString().isEmpty()){
                    Toast.makeText(AddDeath.this, getResources().getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show();
                }
                else if(Integer.parseInt(no.getText().toString()) < 1){
                    Toast.makeText(AddDeath.this, getResources().getString(R.string.death_greater_zero), Toast.LENGTH_SHORT).show();
                }
                else if (alives-Integer.parseInt(no.getText().toString()) > -1){
                    if (death_id != -1) {
                        death_ death = new death_();
                        death.setSql_death_id(death_sql);
                        death.setSql_cicle_id(cicle_sql);
                        death.setSql_building_id(building_sql);
                        death.setUser_id(user_id);
                        death.setDeath_id(death_id);
                        death.setCicle_id(cicle_id);
                        death.setBuilding_id(building_id);
                        death.setDate(date.getText().toString());
                        death.setDeath_no(Integer.parseInt(no.getText().toString()));
                        if (sql_flag != 0) {
                            sql_flag = 1;
                        }
                        death.setFlag(sql_flag);
                        Log.d("add_death_update_death",String.valueOf(sql_flag));
                        handler.updateDeath(death);
                        Toast.makeText(getApplicationContext(),  getResources().getString(R.string.succesfully_updated), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Death.class);
                        intent.putExtra("cicle_id", cicle_id);
                        intent.putExtra("building_id", building_id);
                        finish();
                        startActivity(intent);
                    } else {
                        death_ death = new death_();

                        death.setSql_death_id(death_sql);
                        death.setSql_cicle_id(cicle_sql);
                        death.setSql_building_id(building_sql);
                        death.setUser_id(user_id);
                        death.setCicle_id(cicle_id);
                        death.setBuilding_id(building_id);
                        death.setDate(date.getText().toString());
                        death.setDeath_no(Integer.parseInt(no.getText().toString()));
                        death.setFlag(0);
                        handler.createDeath(death);
                        Toast.makeText(getApplicationContext(),  getResources().getString(R.string.succesfully_updated), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Death.class);
                        intent.putExtra("cicle_id", cicle_id);
                        intent.putExtra("building_id", building_id);
                        finish();
                        startActivity(intent);
                    }
                }else{
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.death_less_total),Toast.LENGTH_LONG).show();
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
        Intent intent = new Intent(getApplicationContext(), Death.class);
        intent.putExtra("cicle_id", cicle_id);
        intent.putExtra("building_id", building_id);
        finish();
        startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), Death.class);
        intent.putExtra("cicle_id", cicle_id);
        intent.putExtra("building_id", building_id);
        finish();
        startActivity(intent);
    }
}
