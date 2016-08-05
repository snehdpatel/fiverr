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

public class ShowBrokenEgg extends AppCompatActivity {

    TextView date,number,type;
    egg_b egg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_broken_egg);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //setting db
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());

        //getting id;
        Intent intent=getIntent();
        final int egg_id=intent.getIntExtra("id", -1);
        Log.d("egg_id", String.valueOf(egg_id));


        egg=handler.getBrokenEgg(egg_id);

        date=(TextView)findViewById(R.id.show_egg_date);
        number=(TextView)findViewById(R.id.show_egg_number);
        type=(TextView)findViewById(R.id.show_egg_type);

        date.setText(egg.getDate());
        number.setText(String.valueOf(egg.getNumber()));
        type.setText(egg.getType());

        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.show_egg_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddBrokenEgg.class);
                intent.putExtra("cicle_id", egg.getCicle_id());
                intent.putExtra("building_id", egg.getBuilding_id());
                intent.putExtra("egg_id", egg.getEgg_id());
                finish();
                startActivity(intent);
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setTitle(getResources().getString(R.string.alert_delete_title));
        builder.setMessage(getResources().getString(R.string.alert_delete_message));
        builder.setPositiveButton(getResources().getString(R.string.alert_detete_button_yes), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                //write whole functionaliy that your delete button is doing
                handler.deleteBrokenEgg(egg);

                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_delete_entry), Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),BrokenEgg.class);
                intent.putExtra("cicle_id",egg.getCicle_id());
                intent.putExtra("building_id", egg.getBuilding_id());
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

        FloatingActionButton delete=(FloatingActionButton)findViewById(R.id.show_egg_del);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.show();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(ShowBrokenEgg.this, BrokenEgg.class);
        intent.putExtra("cicle_id", egg.getCicle_id());
        intent.putExtra("building_id",egg.getBuilding_id());
        finish();
        startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(ShowBrokenEgg.this, BrokenEgg.class);
        intent.putExtra("cicle_id", egg.getCicle_id());
        intent.putExtra("building_id",egg.getBuilding_id());
        finish();
        startActivity(intent);
    }

}
