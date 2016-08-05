
package com.example.sneh.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Animals extends AppCompatActivity {

    int animal_id, building_id, cicle_id;
    int cicle_sql,building_sql;
    int user_id;
    int sql_flag=0;
    int animal_sql=0;
    setting_class sc;
    private TextView currency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals);

        SharedPreferences preferences=getSharedPreferences("app_data", Context.MODE_PRIVATE);
        cicle_id=preferences.getInt("cicle_id", -1);
        building_id=preferences.getInt("building_id", -1);
        cicle_sql=preferences.getInt("cicle_sql", -1);
        building_sql=preferences.getInt("building_sql",-1);


        //getting user_id by sharedprefs
        SharedPreferences pref1=getSharedPreferences("user_data",Context.MODE_PRIVATE);
        user_id=pref1.getInt("user_id",-1);
        Log.d("add_animal_user_id",String.valueOf(user_id));
        //setting the intent and getting animal_id
        Intent intent=getIntent();
        animal_id=intent.getIntExtra("animal_id",-1);


        Log.d("add_animal_building_id",String.valueOf(building_id));
        Log.d("add_animal_cicle_id",String.valueOf(cicle_id));
        Log.d("add_animal_animal_id",String.valueOf(animal_id));
        Log.d("add_animal_building_sql",String.valueOf(building_id));
        Log.d("add_animal_cicle_sql",String.valueOf(cicle_id));
        Log.d("add_animal_user_id",String.valueOf(user_id));
        Log.d("add_animal_animal_sql",String.valueOf(animal_sql));
        //setting up database
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());

        sc = handler.get_all_setting();
        //Toast.makeText(getApplication(),"country:" + sc.getMoney_format(),Toast.LENGTH_LONG).show();

        currency=(TextView)findViewById(R.id.add_equipment_currency);
        currency.setText(sc.getMoney_format());
        Log.d("add_animal_animal_count", String.valueOf(handler.getAnimalCount(cicle_id, building_id)));
        if(handler.getAnimalCount(cicle_id,building_id)==1 && animal_id==-1){
            Intent intent1=new Intent(getApplicationContext(),ShowAnimal.class);
            intent1.putExtra("animal_id", handler.getAnimalCount(cicle_id, building_id));
            finish();
            startActivity(intent1);
        }


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText price = (EditText) findViewById(R.id.price_animal);
        final EditText quantity = (EditText) findViewById(R.id.quantity_animal);
        final TextView amount = (TextView) findViewById(R.id.amount_animal);
        final LinearLayout other1=(LinearLayout)findViewById(R.id.llother);

        final EditText other = (EditText) findViewById(R.id.textView111);
        String[] array_spinner = new String[8];
        array_spinner[0] = "Type(Select)";
        array_spinner[1] = "Type 1";
        array_spinner[2] = "Type 2";
        array_spinner[3] = "Type 3";
        array_spinner[4] = "Type 4";
        array_spinner[5] = "Type 5";
        array_spinner[6] = "Type 6";
        array_spinner[7] = "Other";
        final Spinner spinner = (Spinner) findViewById(R.id.spinner_animal);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, array_spinner);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        if(animal_id!=-1){

            animal_class animal=handler.getAnimal(animal_id);
            animal_sql=animal.getSql_animal_id();
            sql_flag=animal.getFlag();
            price.setText(String.valueOf(animal.getPrice()));
            quantity.setText(String.valueOf(animal.getQuantity()));

            for(int i=0;i<array_spinner.length;i++){
                if(array_spinner[i].equals(animal.getType())){
                    spinner.setSelection(i);
                }
            }
            int total=animal.getPrice()*animal.getQuantity();
            amount.setText(String.valueOf(total)+" "+sc.getMoney_format());
            other.setText(animal.getOther());
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



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = spinner.getSelectedItem().toString();
                if (str.equals("Other")) {
                    other1.setVisibility(View.VISIBLE);
                }
                else{
                    other1.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        Button save_animal=(Button)findViewById(R.id.add_animal_save);
        save_animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String other_string;
                if (spinner.getSelectedItem().toString().equals("Other"))
                    other_string = other.getText().toString();
                else
                    other_string = spinner.getSelectedItem().toString();

                if (other_string.isEmpty() || quantity.getText().toString().isEmpty() || price.getText().toString().isEmpty()) {
                    Toast.makeText(Animals.this, getResources().getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show();
                } else {
                    if (animal_id == -1) {
                        Log.d("add_animal_inserted", "insert");
                        animal_class animal = new animal_class();
                        animal.setSql_animal_id(animal_sql);
                        animal.setSql_cicle_id(cicle_sql);
                        animal.setSql_building_id(building_sql);
                        animal.setUser_id(user_id);
                        animal.setBuilding_id(building_id);
                        animal.setCicle_id(cicle_id);
                        animal.setType(spinner.getSelectedItem().toString());
                        animal.setOther(other.getText().toString());
                        animal.setQuantity(Integer.parseInt(quantity.getText().toString()));
                        animal.setPrice(Integer.parseInt(price.getText().toString()));
                        animal.setFlag(0);
                        handler.createAnimal(animal);
                        //Toast.makeText(getApplicationContext(), String.valueOf(handler.getAnimalCount(cicle_id, animal_id)), Toast.LENGTH_SHORT).show();
                        List<animal_class> animal_list = handler.getAllAnimal(cicle_id, building_id);
                        animal_id = animal_list.get(animal_list.size() - 1).getAnimal_id();
                    } else {
                        Log.d("add_animal_update", "update");
                        animal_class animal = new animal_class();
                        animal.setSql_animal_id(animal_sql);
                        animal.setSql_cicle_id(cicle_sql);
                        animal.setSql_building_id(building_sql);
                        animal.setUser_id(user_id);
                        animal.setAnimal_id(animal_id);
                        animal.setBuilding_id(building_id);
                        animal.setCicle_id(cicle_id);
                        animal.setType(spinner.getSelectedItem().toString());
                        Log.d("add_animal_other", other.getText().toString());
                        animal.setOther(other.getText().toString());
                        animal.setQuantity(Integer.parseInt(quantity.getText().toString()));
                        animal.setPrice(Integer.parseInt(price.getText().toString()));
                        if (sql_flag != 0) {
                            sql_flag = 1;
                        }
                        animal.setFlag(sql_flag);
                        Log.d("add_animal_sql_flag", String.valueOf(sql_flag));
                        int update = handler.updateAnimal(animal);
                        String message;
                        if (update == 1) {
                            message = getResources().getString(R.string.succesfully_updated);
                        } else {
                            message = getResources().getString(R.string.succesfully_notupdate);
                        }
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(getApplicationContext(), ShowAnimal.class);
                    intent.putExtra("animal_id", animal_id);
                    finish();
                    startActivity(intent);
                }
            }
        });

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        if(animal_id != -1) {
            intent = new Intent(getApplicationContext(), ShowAnimal.class);
            intent.putExtra("animal_id", animal_id);
        }
        else {
            intent = new Intent(getApplicationContext(), TitleBuilding.class);
            intent.putExtra("building_id", building_id);
            intent.putExtra("cicle_id", cicle_id);
        }

        finish();
        startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent intent;
        if(animal_id != -1) {
            intent = new Intent(getApplicationContext(), ShowAnimal.class);
            intent.putExtra("animal_id", animal_id);
        }
        else {
            intent = new Intent(getApplicationContext(), TitleBuilding.class);
            intent.putExtra("building_id", building_id);
            intent.putExtra("cicle_id", cicle_id);
        }
        finish();
        startActivity(intent);
    }



}