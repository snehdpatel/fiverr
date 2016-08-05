package com.example.sneh.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class egg_extra_screen extends AppCompatActivity {

    int cicle_id, building_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egg_extra_screen);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayout normal_egg = (LinearLayout) findViewById(R.id.normal_egg_linearlayout);
        LinearLayout broken_egg = (LinearLayout) findViewById(R.id.broken_egg_linearlayout);

        Intent intent=getIntent();
        cicle_id=intent.getIntExtra("cicle_id", -1);
        building_id=intent.getIntExtra("building_id",-1);
        Log.d("In_equipment_cicle_id", String.valueOf(cicle_id));
        Log.d("In_equip_building_id", String.valueOf(building_id));

        normal_egg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(egg_extra_screen.this, NormalEgg.class);
                intent.putExtra("cicle_id",cicle_id);
                intent.putExtra("building_id",building_id);
                finish();
                startActivity(intent);
            }
        });

        broken_egg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(egg_extra_screen.this, BrokenEgg.class);
                intent.putExtra("cicle_id",cicle_id);
                intent.putExtra("building_id",building_id);
                finish();
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

}
