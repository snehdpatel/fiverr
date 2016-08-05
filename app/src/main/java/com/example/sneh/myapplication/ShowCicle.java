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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ShowCicle extends AppCompatActivity {
    TextView title,owner,location,type;
    db_handler handler;
    Button show_cicle_save;
    int cicle_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cicle);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());

        Intent intent =getIntent();                             //getting intent
        cicle_id=intent.getIntExtra("cicle_id", -1);
        Log.d("show_building_cicle_id",String.valueOf(cicle_id));

        final cicle cicle = handler.getCicle(cicle_id);
        Log.d("cicle_flag",String.valueOf(cicle.getFlag()));
        String _title=cicle.getTitle();
        String _owner=cicle.getOwner();
        String _location=cicle.getLocation();
        String _type=cicle.getType();
        String _start_date=cicle.getStart_date();
        Log.d("show_cicle_title",_title);

        title=(TextView)findViewById(R.id.show_cicle_title);
        owner=(TextView)findViewById(R.id.show_cicle_owner);
        location=(TextView)findViewById(R.id.show_cicle_location);
        type=(TextView)findViewById(R.id.show_cicle_type);
        title.setText(_title);
        owner.setText(_owner);
        location.setText(_location);
        type.setText(_type);

        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.show_cicle_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddCicle.class);
                intent.putExtra("cicle_id", cicle.getCicle_id());
                finish();
                startActivity(intent);
            }
        });

        final FloatingActionButton delete=(FloatingActionButton)findViewById(R.id.show_cicle_del);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(getResources().getString(R.string.alert_delete_title));
        builder.setMessage(getResources().getString(R.string.alert_delete_message));
        builder.setPositiveButton(getResources().getString(R.string.alert_detete_button_yes), new DialogInterface.OnClickListener() {




            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                //write whole functionaliy that your delete button is doing

                delete_class delete=new delete_class(ShowCicle.this);
                delete.delete_cicle(cicle);

                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_delete_entry), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Tasks.class);

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
        Intent i = new Intent(ShowCicle.this, Building.class);
        i.putExtra("position",cicle_id);
        startActivity(i);
        finish();
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(ShowCicle.this, Building.class);
        i.putExtra("position",cicle_id);
        startActivity(i);
        finish();
    }

}
