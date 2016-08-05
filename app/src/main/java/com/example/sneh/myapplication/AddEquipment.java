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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddEquipment extends AppCompatActivity {

    EditText type,designation,capacity,price;
    Button save;
    int cicle_id,building_id;
    int cicle_sql,building_sql;
    TextView currency;
    private EditText other;
    int user_id;
    int equip_id,sql_equip_id;
    int flag;
    int type_ = 99;
    int type_other = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_equipment);
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
        Log.d("add_equip_cicle_sql",String.valueOf(cicle_sql));
        Log.d("add_equip_building_sql",String.valueOf(building_sql));
        //getting user_id by sharedprefs
        SharedPreferences pref1=getSharedPreferences("user_data",Context.MODE_PRIVATE);
        user_id=pref1.getInt("user_id",-1);
        Log.d("add_equip_user_id",String.valueOf(user_id));

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_add_equipment);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String[] array_spinner = new String[8];
        array_spinner[0] = "Type(Select)";
        array_spinner[1] = "Type 1";
        array_spinner[2] = "Type 2";
        array_spinner[3] = "Type 3";
        array_spinner[4] = "Type 4";
        array_spinner[5] = "Type 5";
        array_spinner[6] = "Type 6";
        array_spinner[7] = "Other";
        final Spinner spinner = (Spinner) findViewById(R.id.add_equipment_spinner);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, array_spinner);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        save = (Button) findViewById(R.id.add_equipment_save);
        other=(EditText)findViewById(R.id.other_add_eqipment);
        designation=(EditText)findViewById(R.id.add_equipment_designation);
        capacity=(EditText)findViewById(R.id.add_equipment_quantity);
        price=(EditText)findViewById(R.id.add_equipment_price);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = spinner.getSelectedItem().toString();
                if (str.equals("Other")) {
                    type_other = 1;
                    other.setVisibility(View.VISIBLE);
                }
                else
                    other.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }});

            //Setting db_handler
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());

        /*getting equip_id */
        Intent intent=getIntent();
        equip_id=intent.getIntExtra("equip_id",-1);
        sql_equip_id=intent.getIntExtra("sql_equip_id",-1);
        if(sql_equip_id==-1){
            sql_equip_id=0;
        }
        setting_class sc=handler.get_all_setting();
        currency=(TextView)findViewById(R.id.add_equipment_currency);
        currency.setText(sc.getMoney_format());

        if(equip_id!=-1){
            equipment_ equipment=handler.getEquipment(equip_id);

            for(int i=0; i<array_spinner.length-1; i++){
                if(array_spinner[i].equals(equipment.getType()))
                    type_ = i;
            }

            Log.d("equipment",equipment.getType());
            if(type_ == 99) {
                other.setVisibility(View.VISIBLE);
                other.setText(equipment.getType());
                spinner.setSelection(7);
            }
            else
                spinner.setSelection(type_);
            sql_equip_id=equipment.getSql_equip_id();
            flag=equipment.getFlag();
            designation.setText(equipment.getDesignation());
            capacity.setText(String.valueOf(equipment.getQuantity()));
            price.setText(String.valueOf(equipment.getPrice()));
        }
        Log.d("Add_equipment_equip_id",String.valueOf(equip_id));
        Log.d("Add_equipment_cicle_id", String.valueOf(cicle_id));
        Log.d("Add_equip_building_id", String.valueOf(building_id));



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String other_string;
                if (spinner.getSelectedItem().toString().equals("Other"))
                    other_string = other.getText().toString();
                else
                    other_string = spinner.getSelectedItem().toString();


                if(spinner.getSelectedItemPosition() == 0 || designation.getText().toString().isEmpty() || capacity.getText().toString().isEmpty() || price.getText().toString().isEmpty() || other_string.isEmpty()){
                    Toast.makeText(AddEquipment.this, getResources().getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show();
                }
                else {
                    if (equip_id != -1) {
                        Log.d("Add_equip_up", "updating");
                        equipment_ equipment = new equipment_();
                        equipment.setEquip_id(equip_id);
                        equipment.setSql_equip_id(sql_equip_id);
                        equipment.setSql_cicle_id(cicle_sql);
                        equipment.setSql_building_id(building_sql);
                        equipment.setUser_id(user_id);
                        equipment.setCicle_id(cicle_id);
                        equipment.setBuilding_id(building_id);
                        if (spinner.getSelectedItem().toString().equals("Other")) {
                            equipment.setType(other.getText().toString());
                            //other_string = other.getText().toString();
                        } else {
                            equipment.setType(spinner.getSelectedItem().toString());
                            //other_string = spinner.getSelectedItem().toString();
                        }
                        equipment.setDesignation(designation.getText().toString());
                        equipment.setQuantity(Integer.parseInt(capacity.getText().toString()));
                        equipment.setPrice(Integer.parseInt(price.getText().toString()));;

                        if (flag != 0) {
                            flag = 1;
                        }
                        equipment.setFlag(flag);
                        Log.d("add_equip_update_flag",String.valueOf(equipment.getFlag()));
                        handler.updateEquipment(equipment);
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.succesfully_updated), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Equipment.class);
                        intent.putExtra("cicle_id", cicle_id);
                        intent.putExtra("building_id", building_id);
                        finish();
                        startActivity(intent);
                    } else {
                        Log.d("add_equip_insert", "inserting");
                        equipment_ equipment = new equipment_();
                        equipment.setSql_equip_id(sql_equip_id);
                        equipment.setSql_cicle_id(cicle_sql);
                        equipment.setSql_building_id(building_sql);
                        equipment.setUser_id(user_id);
                        equipment.setCicle_id(cicle_id);
                        equipment.setBuilding_id(building_id);
                        if (type_other == 1) {
                            equipment.setType(other.getText().toString());
                            //other_string = other.getText().toString();
                        } else {
                            equipment.setType(spinner.getSelectedItem().toString());
                            //other_string = type.getSelectedItem().toString();
                        }
                        equipment.setDesignation(designation.getText().toString());
                        equipment.setQuantity(Integer.parseInt(capacity.getText().toString()));
                        equipment.setPrice(Integer.parseInt(price.getText().toString()));
                        equipment.setFlag(0);
                        handler.createEquipment(equipment);
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.succesfully_created), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Equipment.class);
                        intent.putExtra("cicle_id", cicle_id);
                        intent.putExtra("building_id", building_id);
                        finish();
                        startActivity(intent);
                    }
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), Equipment.class);
        intent.putExtra("cicle_id", cicle_id);
        intent.putExtra("building_id", building_id);
        finish();
        startActivity(intent);
        //startActivity(new Intent(getApplicationContext(), Equipment.class));
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), Equipment.class);
        intent.putExtra("cicle_id", cicle_id);
        intent.putExtra("building_id", building_id);
        finish();
        startActivity(intent);
    }
}
