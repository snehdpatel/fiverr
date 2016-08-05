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

public class ShowDownPayment extends AppCompatActivity {
    TextView name,date,price;
    int building_id, cicle_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_down_payment);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //setting up textviews
        name=(TextView)findViewById(R.id.show_dp_name);
        date=(TextView)findViewById(R.id.show_dp_date);
        price=(TextView)findViewById(R.id.show_dp_price);

        //setting up intent to get down_payment_id
        Intent intent=getIntent();
        final int down_payment_id=intent.getIntExtra("id",-1);
        Log.d("show_dp_id", String.valueOf(down_payment_id));
        //setting up database
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        setting_class sc = handler.get_all_setting();

        final down_payment_class down_payment=handler.getDownPayment(down_payment_id);
        building_id=down_payment.getBuilding_id();
        cicle_id=down_payment.getCicle_id();
        name.setText(down_payment.getWorker_name());
        date.setText(down_payment.getDate());
        price.setText(String.valueOf(down_payment.getPrice())+" "+sc.getMoney_format());

        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.show_dp_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShowDownPayment.this, AddDownPayment.class);
                i.putExtra("cicle_id", cicle_id);
                i.putExtra("building_id", building_id);
                i.putExtra("down_payment_id", down_payment_id);
                finish();
                startActivity(i);
            }
        });

        FloatingActionButton del = (FloatingActionButton) findViewById(R.id.show_dp_del);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        Log.d("show_DP_cicle_id", String.valueOf(down_payment.getCicle_id()));
        Log.d("show_Dp_building_id", String.valueOf(down_payment.getBuilding_id()));
        Log.d("show_dp_dp_id", String.valueOf(down_payment.getDown_payment_id()));
        Log.d("show_DP_cicle_sql", String.valueOf(down_payment.getSql_cicle_id()));
        Log.d("show_Dp_building_sql", String.valueOf(down_payment.getSql_down_payment_id()));
        Log.d("show_user_id",String.valueOf(down_payment.getUser_id()));
        Log.d("show_DP_DP_sql",String.valueOf(down_payment.getSql_down_payment_id()));
        Log.d("show_DP_flag",String.valueOf(down_payment.getFlag()));
        builder.setTitle(getResources().getString(R.string.alert_delete_title));
        builder.setMessage(getResources().getString(R.string.alert_delete_message));
        builder.setPositiveButton(getResources().getString(R.string.alert_detete_button_yes), new DialogInterface.OnClickListener() {





            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                //write whole functionaliy that your delete button is doing
                if(down_payment.getFlag()==0){
                    handler.deleteDownPayment(down_payment);
                   // Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_delete_entry), Toast.LENGTH_SHORT).show();

                }else{
                    down_payment.setFlag(-1);
                    handler.updateDownPayment(down_payment);
                }
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_delete_entry), Toast.LENGTH_SHORT).show();

                Log.d("show_dp_flag",String.valueOf(down_payment.getFlag()));
                Intent i = new Intent(ShowDownPayment.this, DownPayment.class);
                i.putExtra("cicle_id",cicle_id);
                i.putExtra("building_id",building_id);
                finish();
                startActivity(i);
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
        Intent i = new Intent(ShowDownPayment.this, DownPayment.class);
        i.putExtra("cicle_id",cicle_id);
        i.putExtra("building_id",building_id);
        finish();
        startActivity(i);
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(ShowDownPayment.this, DownPayment.class);
        i.putExtra("cicle_id",cicle_id);
        i.putExtra("building_id",building_id);
        finish();
        startActivity(i);
    }

}