package com.example.sneh.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ShowWorker extends AppCompatActivity {
    int worker_id;
    db_handler handler;
    worker_class worker;
    int cicle_id,building_id;
    int cicle_sql,building_sql;
    int user_id;
    String Date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_worker);

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

        //getting user_id by sharedprefs
        SharedPreferences pref1=getSharedPreferences("user_data",Context.MODE_PRIVATE);
        user_id=pref1.getInt("user_id",-1);
        Log.d("worker_user_id",String.valueOf(user_id));


        Date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //setting up textview
        TextView name=(TextView)findViewById(R.id.show_worker_name);
        TextView address=(TextView)findViewById(R.id.show_worker_address);
        TextView tel=(TextView)findViewById(R.id.show_worker_phone);
        TextView date=(TextView)findViewById(R.id.show_worker_date);
        TextView price=(TextView)findViewById(R.id.show_worker_price);


        //setting up intent to get worker id
        Intent intent=getIntent();
        worker_id=intent.getIntExtra("id",-1);
        Log.d("show_worker_worker_id",String.valueOf(worker_id));

        //setting up db_handler
        handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        setting_class sc = handler.get_all_setting();

        final  worker_class worker1=handler.getWorker(worker_id);
        if(worker1==null){
            Log.d("worker_is_null","worker_is_null");
        }

        Log.d("show_worker_flag",String.valueOf(worker1.getFlag()+" "+worker1.getLast_update()));
        cicle_id=worker1.getCicle_id();
        building_id=worker1.getBuilding_id();
        worker=worker1;
        Log.d("show_worker_title", worker1.getName() + worker1.getDate_start());
        name.setText(worker1.getName());
        address.setText(worker1.getAddress());
        tel.setText(worker1.getTel());
        date.setText(worker1.getDate_start());
        price.setText(String.valueOf(worker1.getPrice_per_day())+" "+sc.getMoney_format());
        Log.d("show_worker_worker_id", String.valueOf(worker_id));
        Log.d("show_worker_cicle_id", String.valueOf(worker.getWorker_id()));
        Log.d("show_worker_building_id", String.valueOf(worker.getBuilding_id()));
        Log.d("show_worker_cicle_sql", String.valueOf(worker.getSql_cicle_id()));
        Log.d("show_worker_build_sql", String.valueOf(worker.getSql_building_id()));
        Log.d("show_worker_worker_sql",String.valueOf(worker.getSql_worker_id()));
        Log.d("show_worker_user_id",String.valueOf(worker.getUser_id()));
        Log.d("show_worker_flag",String.valueOf(worker.getFlag()));

        FloatingActionButton setactive_inactive = (FloatingActionButton) findViewById(R.id.show_worker_set_inactive_active);
        setactive_inactive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(worker1.getSetactive()==1) {
                    worker1.setSetactive(2);
                    if(worker1.getFlag()!=0){
                        worker1.setFlag(1);
                    }
                    handler.updateWorker(worker1);
                    Log.d("upload_work_1flag", String.valueOf(worker1.getFlag()));
                    Toast.makeText(getApplication(),getResources().getString(R.string.worker_sendto_inactive),Toast.LENGTH_LONG).show();
                }
                else
                {
                    if (worker1.getSetactive() == 2) {
                        worker1.setSetactive(1);
                        if(worker1.getFlag()!=0){
                            worker1.setFlag(1);
                        }
                        handler.updateWorker(worker1);
                        Log.d("upload_work_2flag", String.valueOf(worker1.getFlag()));
                        Toast.makeText(getApplication(), getResources().getString(R.string.worker_sendto_active), Toast.LENGTH_LONG).show();
                    }
                }
            }});

        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.show_worker_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShowWorker.this, AddWorker.class);
                i.putExtra("cicle_id",worker1.getCicle_id());
                i.putExtra("building_id",worker1.getBuilding_id());
                i.putExtra("worker_id",worker1.getWorker_id());
                i.putExtra("type",worker1.getSetactive());
                finish();
                startActivity(i);
            }
        });
        FloatingActionButton del = (FloatingActionButton) findViewById(R.id.show_worker_del);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setTitle(getResources().getString(R.string.alert_delete_title));
        builder.setMessage(getResources().getString(R.string.alert_delete_message));


        builder.setPositiveButton(getResources().getString(R.string.alert_detete_button_yes), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                //write whole functionaliy that your delete button is doing
                int affected_row;
                if(worker.getFlag()==0){
                    affected_row=handler.deleteWorker(worker);
                }
                else{
                    worker.setFlag(-1);
                    affected_row=handler.updateWorker(worker);
                }
                String message;
                if(affected_row==1){
                    message="Successfully deleted";
                }
                else{
                    message="Error in deleting";
                }
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),Worker.class);
                intent.putExtra("cicle_id",worker1.getCicle_id());
                intent.putExtra("building_id", worker1.getBuilding_id());
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
        Intent intent=new Intent(getApplicationContext(),Worker.class);
        intent.putExtra("cicle_id", worker.getCicle_id());
        intent.putExtra("building_id", worker.getBuilding_id());
        finish();
        startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent intent=new Intent(ShowWorker.this,Worker.class);
        intent.putExtra("cicle_id",cicle_id);
        intent.putExtra("building_id",building_id);
        finish();
        startActivity(intent);
    }
}