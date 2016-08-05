package com.example.sneh.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.sneh.myapplication.
import com.example.sneh.myapplication.AddFood;
import com.example.sneh.myapplication.FoodConsommation;
import com.example.sneh.myapplication.consommation_;
import com.example.sneh.myapplication.db_handler;
import com.example.sneh.myapplication.food_class;
import com.example.sneh.myapplication.setting_class;

public class ShowConsommation extends AppCompatActivity {
    int con_id;
    TextView date,designation,quantity,price,amount;
    consommation_ con;
    int cicle_id,cicle_sql,building_id,building_sql;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_food);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //setting db
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        setting_class sc = handler.get_all_setting();




        //setting up intent
        Intent intent=getIntent();
        food_id=intent.getIntExtra("id", -1);
        food=handler.getFood(food_id);

        Log.d("show_food_food_id", String.valueOf(food_id));
        Log.d("show_food_cicle_id", String.valueOf(food.getFood_id()));
        Log.d("show_food_building_id", String.valueOf(food.getBuilding_id()));
        Log.d("show_food_cicle_sql", String.valueOf(food.getSql_cicle_id()));
        Log.d("show_food_building_sql", String.valueOf(food.getSql_building_id()));
        Log.d("show_food_food_sql",String.valueOf(food.getSql_food_id()));
        Log.d("show_food_user_id",String.valueOf(food.getUser_id()));
        Log.d("show_food_flag",String.valueOf(food.getFlag()));
        //setting textview
        date=(TextView)findViewById(R.id.show_food_date);
        designation=(TextView)findViewById(R.id.show_food_designation);
        price=(TextView)findViewById(R.id.show_food_price);
        quantity=(TextView)findViewById(R.id.show_food_quantity);
        amount=(TextView)findViewById(R.id.show_food_amount);
        date.setText(food.getDate());
        designation.setText(food.getDesignation());
        price.setText(String.valueOf(food.getPrice())+" "+ sc.getMoney_format());
        quantity.setText(String.valueOf(food.getQuantity()));
        int total=food.getPrice()*food.getQuantity();
        amount.setText(String.valueOf(total)+" "+ sc.getMoney_format());



        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.show_food_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowFood.this, AddFood.class);
                intent.putExtra("cicle_id", food.getCicle_id());
                intent.putExtra("building_id", food.getBuilding_id());
                intent.putExtra("food_id", food_id);
                finish();
                startActivity(intent);
            }
        });
        FloatingActionButton del = (FloatingActionButton) findViewById(R.id.show_food_del);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setTitle(getResources().getString(R.string.alert_delete_title));
        builder.setMessage(getResources().getString(R.string.alert_delete_message));
        builder.setPositiveButton(getResources().getString(R.string.alert_detete_button_yes), new DialogInterface.OnClickListener() {


            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                //write whole functionaliy that your delete button is doing
                if(food.getFlag()==0){
                    handler.deleteFood(food);
                }else{
                    food.setFlag(-1);
                    handler.updateFood(food);
                }
                Log.d("show_food_flag::"+String.valueOf(food.getFood_id()),String.valueOf(food.getFlag()));

                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_delete_entry), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ShowFood.this, FoodConsommation.class);
                intent.putExtra("cicle_id",food.getCicle_id());
                intent.putExtra("building_id",food.getBuilding_id());
                finish();
                startActivity(intent);
            }

        });

        builder.setNegativeButton(getResources().getString(R.string.alert_detete_button_no), new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
                dialog.dismiss();
            }
        });

        final AlertDialog alert = builder.create();


        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.show();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(ShowFood.this, FoodConsommation.class);
        intent.putExtra("cicle_id",food.getCicle_id());
        intent.putExtra("building_id",food.getBuilding_id());
        finish();
        startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(ShowFood.this, FoodConsommation.class);
        intent.putExtra("cicle_id",food.getCicle_id());
        intent.putExtra("building_id",food.getBuilding_id());
        finish();
        startActivity(intent);
    }

}
