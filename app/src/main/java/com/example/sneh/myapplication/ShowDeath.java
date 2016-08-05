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

public class ShowDeath extends AppCompatActivity {

    TextView date,no;
    death_ death;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_death);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //setting db
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());

        //getting id;
        Intent intent=getIntent();
        final int death_id=intent.getIntExtra("id", -1);
        Log.d("death_id", String.valueOf(death_id));


        death=handler.getDeath(death_id);

        date=(TextView)findViewById(R.id.show_death_date);
        no=(TextView)findViewById(R.id.show_death_no);

        date.setText(death.getDate());
        no.setText(String.valueOf(death.getDeath_no()));

        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.show_death_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddDeath.class);
                intent.putExtra("cicle_id", death.getCicle_id());
                intent.putExtra("building_id", death.getBuilding_id());
                intent.putExtra("death_id", death.getDeath_id());
                finish();
                startActivity(intent);
            }
        });

        FloatingActionButton delete=(FloatingActionButton)findViewById(R.id.show_death_del);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Log.d("show_death_cicle_id", String.valueOf(death.getCicle_id()));
        Log.d("show_death_building_id",String.valueOf(death.getBuilding_id()));
        Log.d("show_death_db_count",String.valueOf(death.getDeath_id()));
        Log.d("show_death_sqlcicle_id",String.valueOf(death.getSql_cicle_id()));
        Log.d("show_death_sqlbuild_id",String.valueOf(death.getSql_building_id()));
        Log.d("show_death_user_id", String.valueOf(death.getUser_id()));
        Log.d("show_death_flag", String.valueOf(death.getFlag()));

        builder.setTitle(getResources().getString(R.string.alert_delete_title));
        builder.setMessage(getResources().getString(R.string.alert_delete_message));
        builder.setPositiveButton(getResources().getString(R.string.alert_detete_button_yes), new DialogInterface.OnClickListener() {




            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                //write whole functionaliy that your delete button is doing
                if(death.getFlag()==0){
                    handler.deleteDeath(death);
                }else{
                    death.setFlag(-1);
                    handler.updateDeath(death);
                }
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_delete_entry), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),Death.class);
                intent.putExtra("cicle_id",death.getCicle_id());
                intent.putExtra("building_id",death.getBuilding_id());
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


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(ShowDeath.this, Death.class);
        intent.putExtra("cicle_id", death.getCicle_id());
        intent.putExtra("building_id",death.getBuilding_id());
        finish();
        startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(ShowDeath.this, Death.class);
        intent.putExtra("cicle_id", death.getCicle_id());
        intent.putExtra("building_id",death.getBuilding_id());
        finish();
        startActivity(intent);
    }

}
