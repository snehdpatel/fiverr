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

public class ShowBuilding extends AppCompatActivity {

    db_handler handler;
    int building_id, cicle_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_building);

        Intent i = getIntent();
        building_id = i.getIntExtra("building_id", -1);
        cicle_id = i.getIntExtra("cicle_id",-1);

        handler = new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        final building_class building_class = handler.getBuilding(building_id);

        Log.d("building_id_show", String.valueOf(building_id));

        TextView title = (TextView) findViewById(R.id.show_building_title);
        TextView capacity = (TextView) findViewById(R.id.show_building_capacity);
        TextView type = (TextView) findViewById(R.id.show_building_type);

        title.setText(building_class.getTitle());
        capacity.setText(String.valueOf(building_class.getCapacity()));
        type.setText(building_class.getType()+"  "+building_class.getOther());

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.show_build_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShowBuilding.this, AddBuilding.class);
                i.putExtra("building_id",building_id);
                Log.d("show_build_send_id",String.valueOf(building_id));
                startActivity(i);
            }
        });

        FloatingActionButton delete = (FloatingActionButton) findViewById(R.id.show_build_del);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setTitle(getResources().getString(R.string.alert_delete_title));
        builder.setMessage(getResources().getString(R.string.alert_delete_message));
        builder.setPositiveButton(getResources().getString(R.string.alert_detete_button_yes), new DialogInterface.OnClickListener() {


            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                //write whole functionaliy that your delete button is doing

                delete_class delete=new delete_class(ShowBuilding.this);
                delete.delete_building(building_class);

                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_delete_entry), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Building.class);
                intent.putExtra("position", building_class.getCicle_id());
                intent.putExtra("building_id", building_class.getBuilding_id());
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
        Intent i = new Intent(ShowBuilding.this, TitleBuilding.class);
        i.putExtra("cicle_id",cicle_id);
        i.putExtra("building_id",building_id);
        startActivity(i);
        finish();
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(ShowBuilding.this, TitleBuilding.class);
        i.putExtra("cicle_id",cicle_id);
        i.putExtra("building_id",building_id);
        startActivity(i);
        finish();
    }

}
