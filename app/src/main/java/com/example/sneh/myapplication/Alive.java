package com.example.sneh.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Alive extends AppCompatActivity {

    int building_id,cicle_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alive);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView animal = (TextView) findViewById(R.id.starting_animals_input);
        TextView death = (TextView) findViewById(R.id.dead_animals_entry);
        TextView alive = (TextView) findViewById(R.id.alive_animals_entry);

        Intent intent=getIntent();
        cicle_id=intent.getIntExtra("cicle_id",-1);
        building_id=intent.getIntExtra("building_id",-1);

        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getReadableDatabase());
        int animals = handler.count_animals(cicle_id,building_id);
        int deaths = handler.count_deaths(cicle_id, building_id);
        int alives = animals - deaths;

        animal.setText(String.valueOf(animals));
        death.setText(String.valueOf(deaths));
        alive.setText(String.valueOf(alives));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

}
