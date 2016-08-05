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
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddNormalEgg extends AppCompatActivity {

    EditText number;
    TextView date;
    Button save;
    int year_x, month_x, day_x;
    private TextView date_text;
    private Calendar calendar;
    int building_id,cicle_id,egg_id=0;
    int type_ = 99;
    int type_other = 0;
    String[] array_spinner;
    int cicle_sql,building_sql;
    int egg_sql=0,user_id;
    int sql_flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_normal_egg);

        SharedPreferences preferences=getSharedPreferences("app_data", Context.MODE_PRIVATE);
        cicle_id=preferences.getInt("cicle_id", -1);
        building_id=preferences.getInt("building_id", -1);
        cicle_sql=preferences.getInt("cicle_sql", -1);
        building_sql=preferences.getInt("building_sql",-1);

        SharedPreferences preferences1=getSharedPreferences("user_data", Context.MODE_PRIVATE);
        user_id=preferences1.getInt("user_id",-1);

        final TextView other = (TextView) findViewById(R.id.textView11);

        array_spinner = new String[8];
        array_spinner[0] = "Type(Select)";
        array_spinner[1] = "type1";
        array_spinner[2] = "Type 2";
        array_spinner[3] = "Type 3";
        array_spinner[4] = "Type 4";
        array_spinner[5] = "Type 5";
        array_spinner[6] = "Type 6";
        array_spinner[7] = "Other";

        final Spinner type = (Spinner) findViewById(R.id.add_egg_type);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, array_spinner);
        type.setAdapter(adapter);
        type.setSelection(0);




        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = type.getSelectedItem().toString();
                if (str.equals("Other")) {
                    type_other = 1;
                    other.setVisibility(View.VISIBLE);
                } else
                    other.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        date_text = (TextView) findViewById(R.id.add_egg_start_date);
        calendar=Calendar.getInstance();
        year_x=calendar.get(Calendar.YEAR);
        month_x=calendar.get(Calendar.MONTH);
        day_x=calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year_x, month_x + 1, day_x);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_add_egg);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        save = (Button) findViewById(R.id.save_add_egg);
        date=(TextView)findViewById(R.id.add_egg_start_date);
        number=(EditText)findViewById(R.id.add_egg_number);

        //Setting db_handler
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());

        /*getting building_id and cicle_id*/
        Intent intent=getIntent();
        //cicle_id=intent.getIntExtra("cicle_id", -1);
        //building_id=intent.getIntExtra("building_id",-1);
        egg_id=intent.getIntExtra("egg_id",-1);

        egg_n egg=handler.getNormalEgg(egg_id);
        for(int i=0; i<array_spinner.length-1; i++){
            if(array_spinner[i].equals(egg.getType()))
                type_ = i;
        }

        if(egg_id!=-1){
            if(type_ == 99) {
                other.setVisibility(View.VISIBLE);
                other.setText(egg.getType());
                type.setSelection(7);
            }
            else
                type.setSelection(type_);
            date.setText(egg.getDate());
            number.setText(String.valueOf(egg.getNumber()));
            sql_flag=egg.getFlag();
            egg_sql=egg.getSql_normal_egg_id();
        }


        Log.d("Add_N_egg_egg_id", String.valueOf(egg_id));
        Log.d("Add_N_egg_cicle_id", String.valueOf(cicle_id));
        Log.d("Add_N_egg_build_id",String.valueOf(building_id));
        Log.d("Add_N_egg_cicle_sql",String.valueOf(cicle_sql));
        Log.d("Add_N_egg_build_sql",String.valueOf(building_sql));
        Log.d("Add_N_egg_egg_sql",String.valueOf(egg_sql));
        Log.d("Add_N_egg_user_id",String.valueOf(user_id));
        Log.d("Add_N_egg_egg_flag",String.valueOf(sql_flag));

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String other_string;
                if (type.getSelectedItem().toString().equals("Other"))
                    other_string = other.getText().toString();
                else
                    other_string = type.getSelectedItem().toString();

                if (type.getSelectedItemPosition() == 0 || date.getText().toString().isEmpty() || number.getText().toString().isEmpty() || other_string.isEmpty())
                    Toast.makeText(AddNormalEgg.this, getResources().getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show();
                else {

                    if (egg_id != -1) {
                        egg_n egg = new egg_n();
                        egg.setSql_normal_egg_id(egg_sql);
                        egg.setSql_cicle_id(cicle_sql);
                        egg.setSql_building_id(building_sql);
                        egg.setUser_id(user_id);
                        egg.setEgg_id(egg_id);
                        egg.setCicle_id(cicle_id);
                        egg.setBuilding_id(building_id);
                        egg.setDate(date.getText().toString());
                        egg.setNumber(Integer.parseInt(number.getText().toString()));
                        if (type.getSelectedItem().toString().equals("Other"))
                            egg.setType(other.getText().toString());
                        else
                            egg.setType(type.getSelectedItem().toString());
                        if (sql_flag != 0)
                            sql_flag = 1;
                        egg.setFlag(sql_flag);
                        Log.d("add_ne_update_flag", String.valueOf(egg.getFlag()));
                        handler.updateNormalEgg(egg);
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.succesfully_updated), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), NormalEgg.class);
                        intent.putExtra("cicle_id", cicle_id);
                        intent.putExtra("building_id", building_id);
                        finish();
                        startActivity(intent);
                    } else {
                        egg_n egg = new egg_n();
                        egg.setSql_normal_egg_id(egg_sql);
                        egg.setSql_cicle_id(cicle_sql);
                        egg.setSql_building_id(building_sql);
                        egg.setUser_id(user_id);
                        egg.setEgg_id(egg_id);
                        egg.setCicle_id(cicle_id);
                        egg.setBuilding_id(building_id);
                        egg.setDate(date.getText().toString());
                        egg.setNumber(Integer.parseInt(number.getText().toString()));
                        if (type_other == 1)
                            egg.setType(other.getText().toString());
                        else
                            egg.setType(type.getSelectedItem().toString());
                        egg.setFlag(0);
                        handler.createNormalEgg(egg);
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.succesfully_created), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), NormalEgg.class);
                        intent.putExtra("cicle_id", cicle_id);
                        intent.putExtra("building_id", building_id);
                        finish();
                        startActivity(intent);
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
        Intent intent = new Intent(getApplicationContext(), NormalEgg.class);
        intent.putExtra("cicle_id", cicle_id);
        intent.putExtra("building_id", building_id);
        finish();
        startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), NormalEgg.class);
        intent.putExtra("cicle_id", cicle_id);
        intent.putExtra("building_id", building_id);
        finish();
        startActivity(intent);
    }

}
