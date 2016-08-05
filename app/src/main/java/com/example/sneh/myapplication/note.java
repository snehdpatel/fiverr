package com.example.sneh.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class note extends AppCompatActivity {
    DataBaseHandler db;
    EditText notes_entry;
    Button save;
    int id=0;
    int cicle_id,cicle_sql,building_id,building_sql;
    DataBaseHandler handler;
    int note_id=0;
    int note_sql=0;
    int sql_flag=0;
    int user_id;
    String note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        notes_entry=(EditText)findViewById(R.id.notes_enrty);
        //save=(Button)findViewById(R.id.save_button_notes);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SharedPreferences preferences=getSharedPreferences("app_data", Context.MODE_PRIVATE);
        cicle_id=preferences.getInt("cicle_id", -1);
        building_id=preferences.getInt("building_id", -1);
        cicle_sql=preferences.getInt("cicle_sql", -1);
        building_sql=preferences.getInt("building_sql",-1);
        handler=new DataBaseHandler(this);
        handler.onCreateTable(handler.getWritableDatabase());

        SharedPreferences pref1=getSharedPreferences("user_data",Context.MODE_PRIVATE);
        user_id=pref1.getInt("user_id",-1);
        Log.d("add_equip_user_id",String.valueOf(user_id));

        List<notes_file> note_list=handler.get_all_note(cicle_id,building_id);
        int size=note_list.size();
        Log.d("counttttttttttt",String.valueOf(size));
        if(size==0){
            note=notes_entry.getText().toString();
        }
        else{
            note_id=note_list.get(size-1).getId();
            note=note_list.get(size-1).getNotes();
            note_sql=note_list.get(size-1).getSql_notes_id();
            sql_flag=note_list.get(size-1).getFlag();
            notes_entry.setText(note);
        }

        Log.d("add_note_note_id",String.valueOf(note_id));
        Log.d("add_note-note",String.valueOf(note));
        Log.d("add_note-flag",String.valueOf(sql_flag));
        Log.d("add_note-note_sql", String.valueOf(note_sql));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Intent intent=new Intent(this,TitleBuilding.class);
        //intent.putExtra("cicle_id",cicle_id);
        //intent.putExtra("building_id",building_id);
        Log.d("add_note_note_id",String.valueOf(note_id));
        Log.d("add_note-note",String.valueOf(note));
        Log.d("add_note-flag",String.valueOf(sql_flag));
        Log.d("add_note-note_sql",String.valueOf(note_sql));
        if(note_id==0){
            Log.d("adding_note","adding_note");
            notes_file note_=new notes_file();
            note_.setSql_notes_id(note_sql);
            note_.setSql_cicle_id(cicle_sql);
            note_.setSql_building_id(building_sql);
            note_.setUser_id(user_id);
            note_.setCicle_id(cicle_id);
            note_.setBuilding_id(building_id);
            note_.setNotes(notes_entry.getText().toString());
            note_.setFlag(0);
            handler.CreateNote(note_);
            Toast.makeText(getApplicationContext(),"Notes Successfully Created",Toast.LENGTH_SHORT).show();


        }else{
            Log.d("update_note", "updatig_note");
            notes_file note_=new notes_file();
            note_.setSql_notes_id(note_sql);
            note_.setSql_cicle_id(cicle_sql);
            note_.setSql_building_id(building_sql);
            note_.setId(note_id);
            note_.setUser_id(user_id);
            note_.setCicle_id(cicle_id);
            note_.setBuilding_id(building_id);
            note_.setNotes(notes_entry.getText().toString());
            if (sql_flag!=0)
                note_.setFlag(1);
            else{
                note_.setFlag(0);
            }
            handler.UpdateNote(note_);
        }
        finish();
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.succesfully_updated), Toast.LENGTH_SHORT).show();

        //startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed(){
        Log.d("add_note_note_id",String.valueOf(note_id));
        Log.d("add_note-note",String.valueOf(note));
        Log.d("add_note-flag", String.valueOf(sql_flag));
        Log.d("add_note-note_sql", String.valueOf(note_sql));
        if(note_id==0){
            Log.d("adding_note","adding_note");
            notes_file note_=new notes_file();
            note_.setSql_notes_id(note_sql);
            note_.setSql_cicle_id(cicle_sql);
            note_.setSql_building_id(building_sql);
            note_.setUser_id(user_id);
            note_.setCicle_id(cicle_id);
            note_.setBuilding_id(building_id);
            note_.setNotes(notes_entry.getText().toString());
            note_.setFlag(0);
            handler.CreateNote(note_);
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.succesfully_created),Toast.LENGTH_SHORT).show();

        }else{
            Log.d("update_note","updatig_note");
            notes_file note_=new notes_file();
            note_.setSql_notes_id(note_sql);
            note_.setSql_cicle_id(cicle_sql);
            note_.setSql_building_id(building_sql);
            note_.setId(note_id);
            note_.setUser_id(user_id);
            note_.setCicle_id(cicle_id);
            note_.setBuilding_id(building_id);
            note_.setNotes(notes_entry.getText().toString());
            if (sql_flag!=0)
                note_.setFlag(1);
            else{
                note_.setFlag(0);
            }
            handler.UpdateNote(note_);
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.succesfully_updated), Toast.LENGTH_SHORT).show();
        }
        finish();
    }


}