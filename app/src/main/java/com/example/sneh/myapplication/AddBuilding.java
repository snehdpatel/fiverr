package com.example.sneh.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddBuilding extends AppCompatActivity {
    EditText title,capacity;
    Button add_building_save;
    db_handler handler;
    private String[] array_spinner;
    int [] images;
    int user_id;
    int cicle_id,building_id,building_type;
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_building);

        title=(EditText)findViewById(R.id.add_building_title);
        capacity=(EditText)findViewById(R.id.add_building_capacity);
        final Spinner spinner = (Spinner) findViewById(R.id.add_building_type);
        final EditText other=(EditText)findViewById(R.id.show_building_other);
        add_building_save=(Button)findViewById(R.id.add_building_save);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_add_build);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        array_spinner = new String[5];
        array_spinner[0] = "Type(Select)";
        array_spinner[1] = "type1";
        array_spinner[2] = "Type 2";
        array_spinner[3] = "Type 3";
        array_spinner[4]="Other";
        //setting up database
        handler =new db_handler(getApplicationContext());

        //shared preference and getting sql_cicle_id and cicle_id
        SharedPreferences preferences=getSharedPreferences("app_data", Context.MODE_PRIVATE);
        cicle_id=preferences.getInt("cicle_id", -1);
        //building_id = preferences.getInt("building_id", -1);
        Log.d("add_building_cicle_id",String.valueOf(cicle_id));

        //Setting intent
        Intent intent1=getIntent();
        building_id=intent1.getIntExtra("building_id",-1);
        Log.d("add_build_build_id",String.valueOf(building_id));

        cicle cicle=handler.getCicle(cicle_id);
        final int cicle_sql=cicle.getSql_cicle_id();
        Log.d("add_build_cicle_sql",String.valueOf(cicle.getSql_cicle_id()));

        //shared preference and getting user_id
        SharedPreferences pref=getSharedPreferences("user_data",Context.MODE_PRIVATE);
        user_id=pref.getInt("user_id",-1);
        Log.d("add_build_user_id",String.valueOf(user_id));

        images= new int[]{0, R.drawable.simplebuilding1, R.drawable.simplebuilding2, R.drawable.simplebuilding3,R.drawable.other};

        //  spinner = (Spinner) findViewById(R.id.add_cicle_type);
        // ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, array_spinner);
        // spinner.setAdapter(adapter);
        spinner.setAdapter(new MyAdapter(AddBuilding.this, R.layout.row, array_spinner));
        spinner.setSelection(0);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = spinner.getSelectedItem().toString();
                if (str.equals("Other")) {
                    other.setVisibility(View.VISIBLE);
                }
                else
                    other.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        building_class building = handler.getBuilding(building_id);


        if(building_id != -1){
            for(int i=0; i<array_spinner.length; i++){
                if(array_spinner[i].equals(building.getType()))
                    building_type = i;
            }
            title.setText(building.getTitle());
            capacity.setText(String.valueOf(building.getCapacity()));
            spinner.setSelection(building_type);
            other.setText(building.getOther());
            if(building_type == 4)
                other.setVisibility(View.VISIBLE);
            flag=building.getFlag();
        }

        add_building_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int title_exist = 0;

                if(title.getText().toString().isEmpty() || capacity.getText().toString().isEmpty() || spinner.getSelectedItemPosition() == 0){
                    Toast.makeText(AddBuilding.this, getResources().getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show();
                    if(spinner.getSelectedItemPosition() == 4 && other.getText().toString().isEmpty())
                        Toast.makeText(AddBuilding.this, getResources().getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show();
                }
               else {
                    if (building_id != -1) {
                        building_class building=new building_class();
                        building.setUser_id(user_id);
                        building.setSql_cicle_id(cicle_sql);
                        building.setSql_building_id(0);
                        building.setCicle_id(cicle_id);
                        building.setBuilding_id(building_id);
                        building.setTitle(title.getText().toString());
                        building.setCapacity(Integer.parseInt(capacity.getText().toString()));
                        building.setType(spinner.getSelectedItem().toString());
                        if(spinner.getSelectedItemPosition()==4)
                        building.setOther(other.getText().toString());
                        else
                        building.setOther("");
                        building.setFlag(0);

                        List<building_class> building_list=handler.get_all_building(cicle_id);


                        for(int i=0; i<building_list.size(); i++){
                            if(building_list.get(i).getTitle().equals(title.getText().toString()))
                                building_list.remove(i);

                        }
                        for(int i=0; i<building_list.size(); i++){
                            if(building_list.get(i).getTitle().equals(title.getText().toString()))
                               title_exist=1;

                        }
                        if(title_exist==1)
                            Toast.makeText(getApplicationContext(),  getResources().getString(R.string.same_name), Toast.LENGTH_SHORT).show();
                        else{
                        handler.updateBuilding(building);

                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.succesfully_updated), Toast.LENGTH_SHORT);
                        Intent intent=new Intent(getApplicationContext(),Building.class);
                        intent.putExtra("position",cicle_id);
                        finish();
                        startActivity(intent);}
                    } else {
                        building_class building=new building_class();
                        building.setUser_id(user_id);
                        building.setSql_cicle_id(cicle_sql);
                        building.setSql_building_id(0);
                        building.setCicle_id(cicle_id);
                        building.setTitle(title.getText().toString());
                        building.setCapacity(Integer.parseInt(capacity.getText().toString()));
                        building.setType(spinner.getSelectedItem().toString());
                        if(spinner.getSelectedItemPosition()==4)
                            building.setOther(other.getText().toString());
                        else
                            building.setOther("");
                        if(flag==0){
                            building.setFlag(0);
                        }else{
                            building.setFlag(1);
                        }
                        List<building_class> building_list=handler.get_all_building(cicle_id);
                        for(int i=0; i<building_list.size(); i++){
                            if(building_list.get(i).getTitle().equals(title.getText().toString()))
                                title_exist = 1;
                        }
                        if(title_exist==1)
                            Toast.makeText(getApplicationContext(),  getResources().getString(R.string.same_name), Toast.LENGTH_SHORT).show();
                        else
                        {  handler.createBuilding(building);


                        Toast.makeText(getApplicationContext(),  getResources().getString(R.string.succesfully_created), Toast.LENGTH_SHORT);
                        Intent intent=new Intent(getApplicationContext(),Building.class);
                        intent.putExtra("position",cicle_id);
                        finish();
                        startActivity(intent);}
                    }
                }

            }
        });

    }
    public class MyAdapter extends ArrayAdapter<String>{

        public MyAdapter(Context context, int textViewResourceId,  String[] objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater=getLayoutInflater();
            View row=inflater.inflate(R.layout.row, parent, false);
            TextView label=(TextView)row.findViewById(R.id.company);

            label.setText(array_spinner[position]);


            ImageView icon=(ImageView)row.findViewById(R.id.image);
            if(position>0) {
                icon.setImageResource(images[position]);
            }
            return row;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), Building.class);
        intent.putExtra("position", cicle_id);
        finish();
        startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), Building.class);
        intent.putExtra("position", cicle_id);
        finish();
        startActivity(intent);
    }

}