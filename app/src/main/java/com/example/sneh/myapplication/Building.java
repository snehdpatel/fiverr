package com.example.sneh.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Adapters.BuildListAdapter;

public class Building extends AppCompatActivity {

    db_handler handler;
    //int building_id;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building);
        handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_build);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.add_build);
        FloatingActionButton done_cicle = (FloatingActionButton) findViewById(R.id.building_done_cicle);
        Intent intent=getIntent();
        position=intent.getIntExtra("position",-1);
        //getting cicle_object
        final cicle cicle=handler.getCicle(position);
        Log.d("building_cicle_id", String.valueOf(position));
        Log.d("building_cicle_sql",String.valueOf(cicle.getSql_cicle_id()));
        position=intent.getIntExtra("position", -1);
        SharedPreferences preferences=getSharedPreferences("app_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putInt("cicle_id", position);
        editor.putInt("cicle_sql", cicle.getSql_cicle_id());
        editor.commit();

        SharedPreferences preferences1=getSharedPreferences("user_data", Context.MODE_PRIVATE);
        int user_id=preferences1.getInt("user_id",-1);


        done_cicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cicle.setDone(2);
                if(cicle.getFlag()!=0){
                    cicle.setFlag(1);
                }
                cicle.setFlag(1);
                int count=handler.updateCicle(cicle);
                //Toast.makeText(getApplicationContext(),"building_cicle_update_info"+String.valueOf(count),Toast.LENGTH_SHORT).show();
                if(count==cicle.getCicle_id()){
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.succesfully_completed),Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),Tasks.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.succesfully_completed),Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),Tasks.class);
                    startActivity(intent);
                }

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Building.this, AddBuilding.class);
                i.putExtra("cicle_id",position);
                finish();
                startActivity(i);
            }
        });

        List<building_class> building_list=handler.get_all_building(position);
        ListView lv = (ListView) findViewById(R.id.build_list);
        lv.setAdapter(new BuildListAdapter(Building.this , building_list));

        TextView number = (TextView) findViewById(R.id.textView13);
        number.setText("Buildings (" + building_list.size() + ")");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                //finish();
                Intent i = new Intent(Building.this, ShowCicle.class);
                i.putExtra("cicle_id", position);
                finish();
                startActivity(i);

                return true;

            default:
                Intent intent=new Intent(getApplicationContext(),Tasks.class);
                finish();
                startActivity(intent);
                return true;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_title_building, menu);
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent intent=new Intent(getApplicationContext(),Tasks.class);
        finish();
        startActivity(intent);
    }

}
