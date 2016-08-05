package com.example.sneh.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Himanshu on 11/10/2015.
 */
public class DataBaseHandler extends SQLiteOpenHelper
{


    private static final String KEY_USER_ID="user_id";
    private static final String KEY_FLAG="flag";
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = " Camera_imagedb";

    // Contacts table name
    private static final String TABLE_CONTACTS = " Camera_contacts";

    // Contacts Table Columns names
   // private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_IMAGE = "image";

    //
    private static final String TABLE_NOTES = "notes_table";
    private static final String KEY_SQL_NOTES_ID="sql_notes_id";
    private static final String KEY_SQL_NOTES_CICLE_ID="sql_notes_cicle_id";
    private static final String KEY_SQL_NOTES_BUILDING_ID="sql_notes_building_id";
    private static final String KEY_NOTES_CICLE_ID="notes_cicle_id";
    private static final String KEY_NOTES_BUILDING_ID="notes_building_id";
    private static final String KEY_ID_NOTES="id";
    private static final String KEY_NOTES="notes";


    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
   // @Override
    public void onCreate(SQLiteDatabase db) {

    }

    public void onCreateTable(SQLiteDatabase db) {
        String CREATE_Notes_TABLE = "create table if not exists " + TABLE_NOTES + "("
                + KEY_SQL_NOTES_ID + " INTEGER, "
                + KEY_SQL_NOTES_CICLE_ID + " INTEGER, "
                + KEY_SQL_NOTES_BUILDING_ID + " INTEGER, "
                + KEY_USER_ID + " INTEGER, "
                + KEY_ID_NOTES + " INTEGER PRIMARY KEY , "
                + KEY_NOTES_CICLE_ID+ " INTEGER, "
                + KEY_NOTES_BUILDING_ID + " INTEGER, "
                + KEY_NOTES + " TEXT, "
                +KEY_FLAG+" INTEGER "
                + ")";
        Log.d("table_notes_info_sql", CREATE_Notes_TABLE);
        db.execSQL(CREATE_Notes_TABLE);
        Log.d("building_info_status", "created");

        Log.d("building_info_status", "crted");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read) Operations
     */
    /**
     * All CRUD(Create, Read) Operations
     */

    public int CreateNote(notes_file note){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_SQL_NOTES_ID,note.getSql_notes_id());
        values.put(KEY_SQL_NOTES_CICLE_ID,note.getSql_cicle_id());
        values.put(KEY_SQL_NOTES_BUILDING_ID,note.getSql_building_id());
        values.put(KEY_USER_ID,note.getUser_id());
        values.put(KEY_NOTES_CICLE_ID,note.getCicle_id());
        values.put(KEY_NOTES_BUILDING_ID,note.getBuilding_id());
        values.put(KEY_NOTES,note.getNotes());
        values.put(KEY_FLAG,note.getFlag());
        int row=-1;
        row=(int)db.insert(TABLE_NOTES,null,values);
        Log.d("create_note_note_id",String.valueOf(row));
        db.close();
        return row;
    }

    public notes_file getNote(int note_id){
        SQLiteDatabase db=getReadableDatabase();
        String sql="SELECT * FROM "+KEY_ID_NOTES+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(note_id)});
        notes_file note=null;
        if(cursor!=null){
            if(cursor.moveToFirst()) {
                note = new notes_file();
                note.setSql_notes_id(cursor.getInt(0));
                note.setSql_cicle_id(cursor.getInt(1));
                note.setSql_building_id(cursor.getInt(2));
                note.setUser_id(cursor.getInt(3));
                note.setId(cursor.getInt(4));
                note.setCicle_id(cursor.getInt(5));
                note.setBuilding_id(cursor.getInt(6));
                note.setNotes(cursor.getString(7));
                note.setFlag(cursor.getInt(8));
            }
        }
        if(cursor!=null){
            Log.d("get_note_note",String.valueOf(note.getNotes()));
        }

        return  note;
    }

    public notes_file getNoteBySql(int sql_note_id){
        SQLiteDatabase db=getReadableDatabase();
        String sql="SELECT * FROM "+TABLE_NOTES+" WHERE "+KEY_SQL_NOTES_ID+"=?";

        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(sql_note_id)});
        notes_file note=null;
        if(cursor!=null){
            if(cursor.moveToFirst()) {
                note = new notes_file();
                note.setSql_notes_id(cursor.getInt(0));
                note.setSql_cicle_id(cursor.getInt(1));
                note.setSql_building_id(cursor.getInt(2));
                note.setUser_id(cursor.getInt(3));
                note.setId(cursor.getInt(4));
                note.setCicle_id(cursor.getInt(5));
                note.setBuilding_id(cursor.getInt(6));
                note.setNotes(cursor.getString(7));
                note.setFlag(cursor.getInt(8));
            }
        }
        if(cursor!=null){
//            Log.d("get_note_note",String.valueOf(note.getNotes()));
        }

        return  note;
    }

    public void delete_note(notes_file note){
        SQLiteDatabase db=getWritableDatabase();

    }

    public List<notes_file> get_all_note(int cicle_id,int building_id){
        SQLiteDatabase db=getWritableDatabase();
        List<notes_file> file=new ArrayList<>();
        String sql="SELECT * FROM "+TABLE_NOTES+" WHERE "+KEY_NOTES_CICLE_ID+"=? AND "+KEY_NOTES_BUILDING_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(cicle_id),String.valueOf(building_id)});
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    notes_file note=new notes_file();
                    note.setSql_notes_id(cursor.getInt(0));
                    note.setSql_cicle_id(cursor.getInt(1));
                    note.setSql_building_id(cursor.getInt(2));
                    note.setUser_id(cursor.getInt(3));
                    note.setId(cursor.getInt(4));
                    note.setCicle_id(cursor.getInt(5));
                    note.setBuilding_id(cursor.getInt(6));
                    note.setNotes(cursor.getString(7));
                    note.setFlag(cursor.getInt(8));
                    file.add(note);
                    Log.d("notes",String.valueOf(cursor.getInt(0))+String.valueOf(cursor.getInt(1))+String.valueOf(cursor.getInt(2))+String.valueOf(cursor.getInt(3))+String.valueOf(cursor.getInt(4))+String.valueOf(cursor.getInt(5))+String.valueOf(cursor.getInt(6))+"  "+String.valueOf(cursor.getString(7))+String.valueOf(cursor.getInt(8)));
                }while(cursor.moveToNext());
            }
        }
        Log.d("get_note_list",String.valueOf(file.size()));
        return file;
    }

    public int UpdateNote(notes_file note){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_SQL_NOTES_ID,note.getSql_notes_id());
        values.put(KEY_SQL_NOTES_CICLE_ID,note.getSql_cicle_id());
        values.put(KEY_SQL_NOTES_BUILDING_ID,note.getSql_building_id());
        values.put(KEY_USER_ID,note.getUser_id());
        values.put(KEY_NOTES_CICLE_ID,note.getCicle_id());
        values.put(KEY_NOTES_BUILDING_ID,note.getBuilding_id());
        values.put(KEY_NOTES,note.getNotes());
        values.put(KEY_FLAG,note.getFlag());
        int row=-1;
        row=db.update(TABLE_NOTES,values,KEY_ID_NOTES+"=?",new String[]{String.valueOf(note.getId())});
        Log.d("create_note_note_id",String.valueOf(row));
        db.close();
        return row;
    }

    public List<notes_file> get_all_note_by_user_id(int user_id){
        SQLiteDatabase db=getWritableDatabase();
        List<notes_file> file=new ArrayList<>();
        String sql="SELECT * FROM "+TABLE_NOTES+" WHERE "+KEY_USER_ID+"=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(user_id)});
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    notes_file note=new notes_file();
                    note.setSql_notes_id(cursor.getInt(0));
                    note.setSql_cicle_id(cursor.getInt(1));
                    note.setSql_building_id(cursor.getInt(2));
                    note.setUser_id(cursor.getInt(3));
                    note.setId(cursor.getInt(4));
                    note.setCicle_id(cursor.getInt(5));
                    note.setBuilding_id(cursor.getInt(6));
                    note.setNotes(cursor.getString(7));
                    note.setFlag(cursor.getInt(8));
                    file.add(note);
                    Log.d("notes",String.valueOf(cursor.getInt(0))+String.valueOf(cursor.getInt(1))+String.valueOf(cursor.getInt(2))+String.valueOf(cursor.getInt(3))+String.valueOf(cursor.getInt(4))+String.valueOf(cursor.getInt(5))+String.valueOf(cursor.getInt(6))+"  "+String.valueOf(cursor.getString(7))+String.valueOf(cursor.getInt(8)));
                }while(cursor.moveToNext());
            }
        }
        Log.d("get_note_list_by_user", String.valueOf(file.size()));
        return file;
    }


}