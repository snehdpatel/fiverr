package com.example.sneh.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ShowMedical extends AppCompatActivity {

    medical_ medical;
    TextView operation_name, operation_type, date, product, comment, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_medical);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //setting db
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        setting_class sc = handler.get_all_setting();

        //getting id;
        Intent intent=getIntent();
        final int medical_id=intent.getIntExtra("id", -1);
        Log.d("medical_id", String.valueOf(medical_id));


        medical=handler.getMedical(medical_id);

        operation_name=(TextView)findViewById(R.id.show_medical_product_name);
        operation_type=(TextView)findViewById(R.id.show_medical_operation_type);
        product=(TextView)findViewById(R.id.show_medical_product);
        comment=(TextView)findViewById(R.id.show_medical_comment);
        date=(TextView)findViewById(R.id.show_medical_date);
        price=(TextView)findViewById(R.id.show_medical_price);

        operation_name.setText(medical.getOperation_name());
        operation_type.setText(medical.getOperation_type());
        product.setText(medical.getProduct());
        comment.setText(medical.getComment());
        date.setText(medical.getDate());
        price.setText(String.valueOf(medical.getPrice())+" "+sc.getMoney_format());

        Log.d("show_medical_medical_id", String.valueOf(medical_id));
        Log.d("show_medical_cicle_id", String.valueOf(medical.getCicle_id()));
        Log.d("show_med_building_id", String.valueOf(medical.getBuilding_id()));
        Log.d("show_med_cicle_sql", String.valueOf(medical.getSql_cicle_id()));
        Log.d("show_med_building_sql", String.valueOf(medical.getSql_building_id()));
        Log.d("show_med_medical_sql", String.valueOf(medical.getSql_medical_id()));
        Log.d("show_medical_user_id", String.valueOf(medical.getUser_id()));
        Log.d("show_medical_flag", String.valueOf(medical.getFlag()));


        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.show_medical_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddMedical.class);
                intent.putExtra("cicle_id", medical.getCicle_id());
                intent.putExtra("building_id", medical.getBuilding_id());
                intent.putExtra("medical_id", medical.getMedical_id());
                finish();
                startActivity(intent);
            }
        });

        FloatingActionButton delete=(FloatingActionButton)findViewById(R.id.show_medical_del);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setTitle(getResources().getString(R.string.alert_delete_title));
        builder.setMessage(getResources().getString(R.string.alert_delete_message));


        builder.setPositiveButton(getResources().getString(R.string.alert_detete_button_yes), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                //write whole functionaliy that your delete button is doing
                 if(medical.getFlag()==0){
                    handler.deleteMedical(medical);
                }
                else{
                    medical.setFlag(-1);
                    handler.updateMedical(medical);
                }
                Log.d("show_med_del_flag", String.valueOf(medical.getFlag()));
                Toast.makeText(getApplicationContext(), "Successfully Deleted", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),Medical.class);
                intent.putExtra("cicle_id",medical.getCicle_id());
                intent.putExtra("building_id",medical.getBuilding_id());
                intent.putExtra("medical_id", medical.getMedical_id());
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


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.show();
            }
        });


        Log.d("price_medical", String.valueOf(medical.getPrice()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(ShowMedical.this, Medical.class);
        intent.putExtra("cicle_id",medical.getCicle_id());
        intent.putExtra("building_id",medical.getBuilding_id());
        finish();
        startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(ShowMedical.this, Medical.class);
        intent.putExtra("cicle_id",medical.getCicle_id());
        intent.putExtra("building_id",medical.getBuilding_id());
        finish();
        startActivity(intent);
    }

}