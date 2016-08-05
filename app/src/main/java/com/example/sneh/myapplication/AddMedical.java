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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddMedical extends AppCompatActivity {

    int year_x, month_x, day_x;
    private Calendar calendar;
    private TextView date_text;

    EditText product,price,comment,other;
    Button save;
    int cicle_id,building_id,medical_id;
    int cicle_sql,building_sql;
    int operation_name = 0,operation_type = 0;
    int user_id;
    Spinner spinner,spinner2;
    String[] array_spinner1;
    String[][] array_spinner2;
    int medical_sql=0;
    medical_ medical;
    int flag = 0;
    int sql_flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medical);

        SharedPreferences preferences=getSharedPreferences("app_data", Context.MODE_PRIVATE);
        cicle_id=preferences.getInt("cicle_id", -1);
        building_id=preferences.getInt("building_id", -1);
        cicle_sql=preferences.getInt("cicle_sql", -1);
        building_sql=preferences.getInt("building_sql",-1);

        //getting user_id
        SharedPreferences preferences1=getSharedPreferences("user_data", Context.MODE_PRIVATE);
        user_id=preferences1.getInt("user_id",-1);
        Log.d("in_medical_user_id", String.valueOf(user_id));


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_add_medical);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        array_spinner1 = new String[]{getResources().getString(R.string.op_name),getResources().getString(R.string.med_product), getResources().getString(R.string.environment), getResources().getString(R.string.various)};
        array_spinner2 = new String[][]{{getResources().getString(R.string.op_type),getResources().getString(R.string.antibiotic), getResources().getString(R.string.vaccine), getResources().getString(R.string.vitiamin)}, {getResources().getString(R.string.op_type),getResources().getString(R.string.gas_litter), getResources().getString(R.string.hygiene),getResources().getString(R.string.product)}, {getResources().getString(R.string.other)}};

        spinner = (Spinner) findViewById(R.id.add_medical_op_name);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, array_spinner1);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        spinner2 = (Spinner) findViewById(R.id.add_medical_type);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner2(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //final EditText other=(EditText)findViewById(R.id.show_building_other);


        date_text = (TextView) findViewById(R.id.add_medical_start_date);
        product = (EditText) findViewById(R.id.add_medical_product);
        price = (EditText) findViewById(R.id.add_medical_price);
        comment = (EditText) findViewById(R.id.add_medical_comment);
        other = (EditText) findViewById(R.id.add_medical_product_other);

        calendar= Calendar.getInstance();
        year_x=calendar.get(Calendar.YEAR);
        month_x=calendar.get(Calendar.MONTH);
        day_x=calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year_x, month_x + 1, day_x);

        //Setting db_handler
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());

        /*getting building_id and cicle_id*/
        Intent intent=getIntent();
        medical_id=intent.getIntExtra("medical_id", -1);
        medical=handler.getMedical(medical_id);

        if(medical_id!=-1){
            for(int i=0; i<array_spinner1.length; i++){
                if(array_spinner1[i].equals(medical.getOperation_name()))
                    operation_name = i;
            }

            for(int i=0; i<array_spinner2[operation_name-1].length; i++){
                if(array_spinner2[operation_name-1][i].equals(medical.getOperation_type()))
                    operation_type = i;
            }

            sql_flag=medical.getFlag();
            medical_sql=medical.getSql_medical_id();
            product.setText(medical.getProduct());
            date_text.setText(medical.getDate());
            comment.setText(medical.getComment());
            price.setText(String.valueOf(medical.getPrice()));
            spinner.setSelection(operation_name);
            spinner2.setSelection(operation_type);
            other.setText(medical.getOperation_type());
            if(operation_name == 3)
                other.setVisibility(View.VISIBLE);


        }
        Log.d("Add_med_medical_id", String.valueOf(medical_id));
        Log.d("Add_med_cicle_id", String.valueOf(cicle_id));
        Log.d("Add_med_building_id", String.valueOf(building_id));
        Log.d("Add_med_cicle_sql", String.valueOf(cicle_sql));
        Log.d("Add_med_building_sql", String.valueOf(building_sql));
        Log.d("add_med_medical_sql", String.valueOf(medical_sql));
        save = (Button) findViewById(R.id.save_add_medical);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(spinner.getSelectedItemPosition() == 0 ||  product.getText().toString().isEmpty() || price.getText().toString().isEmpty()){
                    Toast.makeText(AddMedical.this, getResources().getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show();
                }
                else if(spinner.getSelectedItemPosition() == 3 && other.getText().toString().isEmpty()) {
                    Toast.makeText(AddMedical.this, getResources().getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show();
                }
                else if ((spinner.getSelectedItemPosition() == 1 || spinner.getSelectedItemPosition() == 2) && spinner2.getSelectedItemPosition() == 0){
                    Toast.makeText(AddMedical.this, getResources().getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show();
                }

                else {

                    if (medical_id != -1) {
                        medical_ medical_ = new medical_();
                        medical_.setMedical_id(medical_id);
                        medical_.setSql_medical_id(medical_sql);
                        medical_.setSql_cicle_id(cicle_sql);
                        medical_.setSql_building_id(building_sql);
                        medical_.setUser_id(user_id);
                        medical_.setCicle_id(cicle_id);
                        medical_.setBuilding_id(building_id);
                        medical_.setOperation_name(spinner.getSelectedItem().toString());
                        if (flag == 1)
                            medical_.setOperation_type(other.getText().toString());
                        else
                            medical_.setOperation_type(spinner2.getSelectedItem().toString());
                        medical_.setComment(comment.getText().toString());
                        medical_.setDate(date_text.getText().toString());
                        medical_.setProduct(product.getText().toString());
                        medical_.setPrice(Integer.parseInt(price.getText().toString()));
                        if (sql_flag == 0) {
                            sql_flag = 0;
                        } else {
                            sql_flag = 1;
                        }
                        medical_.setFlag(sql_flag);
                        Log.d("add_medical_flag", String.valueOf(sql_flag));
                        handler.updateMedical(medical_);
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.succesfully_updated), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Medical.class);
                        intent.putExtra("cicle_id", cicle_id);
                        intent.putExtra("building_id", building_id);
                        finish();
                        startActivity(intent);
                    } else {
                        medical_ medical_ = new medical_();
                        medical_.setSql_medical_id(medical_sql);
                        medical_.setSql_cicle_id(cicle_sql);
                        medical_.setSql_building_id(building_sql);
                        medical_.setUser_id(user_id);
                        medical_.setCicle_id(cicle_id);
                        medical_.setBuilding_id(building_id);
                        medical_.setOperation_name(spinner.getSelectedItem().toString());
                        if (flag == 1)
                            medical_.setOperation_type(other.getText().toString());
                        else
                            medical_.setOperation_type(spinner2.getSelectedItem().toString());
                        medical_.setComment(comment.getText().toString());
                        medical_.setDate(date_text.getText().toString());
                        medical_.setProduct(product.getText().toString());
                        medical_.setPrice(Integer.parseInt(price.getText().toString()));
                        medical_.setFlag(0);
                        handler.createMedical(medical_);
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.succesfully_created), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Medical.class);
                        intent.putExtra("cicle_id", cicle_id);
                        intent.putExtra("building_id", building_id);
                        //Toast.makeText(getApplicationContext(), price.getText().toString(), Toast.LENGTH_SHORT).show();
                        Log.d("price......medical", price.getText().toString());
                        finish();
                        startActivity(intent);
                    }
                }
            }
        });

//        Log.d("price_medical", String.valueOf(medical.getPrice()));

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
        Intent intent = new Intent(getApplicationContext(), Medical.class);
        intent.putExtra("cicle_id", cicle_id);
        intent.putExtra("building_id", building_id);
        finish();
        startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), Medical.class);
        intent.putExtra("cicle_id", cicle_id);
        intent.putExtra("building_id", building_id);
        finish();
        startActivity(intent);
    }

    public void spinner2(int position){
        if(position != 0)
            position = position-1;
        Log.d("position", String.valueOf(position));
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, array_spinner2[position]);
        spinner2.setAdapter(adapter2);
        spinner2.setSelection(0);

        for(int i=0; i<array_spinner2[position].length; i++){
            if(array_spinner2[position][i].equals(medical.getOperation_type()))
                operation_type = i;
        }

        if(position == 2) {
            flag = 1;
            other.setVisibility(View.VISIBLE);
        }
        else {
            flag = 0;
            other.setVisibility(View.GONE);
        }

    }

}
