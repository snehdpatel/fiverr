package com.example.sneh.myapplication;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddCicle extends AppCompatActivity {
    ImageButton date_pick;
    int year_x, month_x, day_x;
    static final int DIALOG_ID = 999;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView date_text;
    private TextView finish_date_text;
    ImageButton finish_date_button;
    ImageButton map_click;
    private TextView map_text;
    private boolean flag=false;
    int _id;
    private DatePickerDialog fromDatePickerDialog;
    String[] array_spinner;
    // AppLocationService appLocationService;
    private LocationManager locationMangaer=null;
    private LocationListener locationListener=null;
    int user_id;
    int cicle_id;
    int cicle_type;
    int sql_flag=0;
    //gps
    GPSTracker gps;
    Geocoder geocoder;
    double latitude;
    double longitude;
    String stringThisAddress;
    String City_state;
    Button add_cicle_save;
    EditText title,owner;
    Spinner spinner;
    int[] images;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cicle);
        title=(EditText)findViewById(R.id.add_cicle_title);
        owner=(EditText)findViewById(R.id.add_cicle_owner);
        finish_date_text=(TextView)findViewById(R.id.add_cicle_finish_date);
        date_text = (TextView) findViewById(R.id.add_cicle_start_date);
        map_text=(EditText)findViewById(R.id.add_cicle_location);

        SharedPreferences preferences=getSharedPreferences("user_data",Context.MODE_PRIVATE);
        user_id=preferences.getInt("user_id",-1);
        Log.d("add_cicle_user_id", String.valueOf(user_id));

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        array_spinner = new String[4];
        array_spinner[0] = "Type(Select)";
        array_spinner[1] = "type1";
        array_spinner[2] = "Type 2";
        array_spinner[3] = "Type 3";


        images= new int[]{0, R.drawable.simpleanimal, R.drawable.simpleanimal2, R.drawable.simpleanimal3};

        spinner = (Spinner) findViewById(R.id.add_cicle_type);
        // ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, array_spinner);
        // spinner.setAdapter(adapter);
        spinner.setAdapter(new MyAdapter(AddCicle.this,R.layout.row,array_spinner));
        spinner.setSelection(0);

        final db_handler db = new db_handler(getApplicationContext());
        db.onCreateTable(db.getWritableDatabase());

        Intent intent=getIntent();
        cicle_id=intent.getIntExtra("cicle_id", -1);

        cicle cicle_=db.getCicle(cicle_id);



        if(cicle_id != -1){
            for(int i=0; i<array_spinner.length; i++){
                if(array_spinner[i].equals(cicle_.getType()))
                    cicle_type = i;
            }
            title.setText(cicle_.getTitle());
            owner.setText(cicle_.getOwner());
            map_text.setText(cicle_.getLocation());
            date_text.setText(cicle_.getStart_date());
            finish_date_text.setText(cicle_.getFinish_date());
            spinner.setSelection(cicle_type);

            sql_flag=cicle_.getFlag();
        }

        add_cicle_save=(Button)findViewById(R.id.add_cicle_save);
        add_cicle_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int title_exist = 0;
                /*List<cicle> cicle_list=db.get_all_cicle(user_id);
                for(int i=0; i<cicle_list.size(); i++){
                    if(cicle_list.get(i).getTitle().equals(title.getText().toString()))
                        title_exist = 1;
                }*/
                if(title.getText().toString().isEmpty() || owner.getText().toString().isEmpty() || map_text.getText().toString().isEmpty() || date_text.getText().toString().isEmpty() || finish_date_text.getText().toString().isEmpty() || spinner.getSelectedItemPosition() == 0){
                    Toast.makeText(AddCicle.this, getResources().getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show();
                }
                else if(title_exist == 1){
                    Toast.makeText(AddCicle.this,  getResources().getString(R.string.same_name), Toast.LENGTH_SHORT).show();
                }else {
                    if (cicle_id != -1) {
                        cicle cicle_=new cicle();
                        cicle_.setSql_cicle_id(0);
                        cicle_.setCicle_id(cicle_id);
                        cicle_.setTitle(title.getText().toString());
                        cicle_.setOwner(owner.getText().toString());
                        cicle_.setUser_id(user_id);
                        cicle_.setLocation(map_text.getText().toString());
                        cicle_.setStart_date(date_text.getText().toString());
                        cicle_.setFinish_date(finish_date_text.getText().toString());
                        cicle_.setType(spinner.getSelectedItem().toString());

                        List<cicle> cicle_list=db.get_all_cicle(user_id);
                        for(int i=0; i<cicle_list.size(); i++){
                            if(cicle_list.get(i).getTitle().equals(title.getText().toString()))
                                cicle_list.remove(i);
                        }

                        for(int i=0; i<cicle_list.size(); i++){
                            if(cicle_list.get(i).getTitle().equals(title.getText().toString()))
                                title_exist = 1;
                        }

                        cicle_.setDone(1);
                        if(sql_flag==0){
                            cicle_.setFlag(0);
                        }else {
                            cicle_.setFlag(1);
                        }

                        if(title_exist==1)
                            Toast.makeText(getApplicationContext(),  getResources().getString(R.string.same_name), Toast.LENGTH_SHORT).show();
                        else{
                        int ret = db.updateCicle(cicle_);
                        if (ret == 1) {
                            Toast.makeText(getApplicationContext(),  getResources().getString(R.string.succesfully_updated), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(),  getResources().getString(R.string.succesfully_notupdate), Toast.LENGTH_SHORT).show();
                        }
                        Log.d("return value", String.valueOf(ret));
                        Intent intent=new Intent(AddCicle.this, Tasks.class);
                        finish();
                        startActivity(intent);}
                    } else {
                        cicle cicle_=new cicle();
                        cicle_.setSql_cicle_id(0);
                        cicle_.setTitle(title.getText().toString());
                        cicle_.setOwner(owner.getText().toString());
                        cicle_.setUser_id(user_id);
                        cicle_.setLocation(map_text.getText().toString());
                        cicle_.setStart_date(date_text.getText().toString());
                        cicle_.setFinish_date(finish_date_text.getText().toString());
                        cicle_.setType(spinner.getSelectedItem().toString());
                        cicle_.setDone(1);
                        cicle_.setFlag(0);

                        List<cicle> cicle_list=db.get_all_cicle(user_id);
                        for(int i=0; i<cicle_list.size(); i++){
                            if(cicle_list.get(i).getTitle().equals(title.getText().toString()))
                                title_exist = 1;
                        }
                        if(title_exist==1)
                            Toast.makeText(getApplicationContext(),  getResources().getString(R.string.same_name), Toast.LENGTH_SHORT).show();
                        else{
                        db.CreateCicle(cicle_);
                        Intent intent=new Intent(AddCicle.this, Tasks.class);
                        finish();
                        startActivity(intent);}
                    }
                }
            }
        });

        //setting date
        finish_date_button=(ImageButton)findViewById(R.id.date_to_finish);

        date_pick=(ImageButton)findViewById(R.id.start_date_button);
        calendar=Calendar.getInstance();
        year_x=calendar.get(Calendar.YEAR);
        month_x=calendar.get(Calendar.MONTH);
        day_x=calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year_x, month_x + 1, day_x);
        date_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _id=1;
                showDialog(DIALOG_ID);
            }
        });

        finish_date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _id=2;
                showDialog(DIALOG_ID);
                showDialog(DIALOG_ID);
            }
        });




        //Setting gps

        map_click = (ImageButton) findViewById(R.id.imageButton2);
        locationMangaer=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        geocoder=new Geocoder(this);

        map_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps = new GPSTracker(AddCicle.this);
                if(gps.canGetLocation()) {
                    latitude = gps.getLatitude();
                    longitude = gps.getLongitude();
                    // map_text.setText(latitude+"kk"+longitude);
                } else {
                    gps.showSettingsAlert();
                }
                if(latitude==0 && longitude==0)
                {
                    //Toast.makeText(AddCicle.this, "Please Press the button again", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    List<Address> geoResult = findGeocoder(latitude, longitude);
                    if(geoResult != null){
                        // List<String> geoStringResult = new ArrayList<String>();
                        for(int i=0; i < geoResult.size(); i++){
                            Address thisAddress = geoResult.get(i);
                            stringThisAddress = "";
                            for(int a=0; a < thisAddress.getMaxAddressLineIndex(); a++) {
                                stringThisAddress += thisAddress.getAddressLine(a) + "\n";
                            }

                            stringThisAddress +=
                                    "CountryName: " + thisAddress.getCountryName() + "\n"
                                            + "CountryCode: " + thisAddress.getCountryCode() + "\n"
                                            + "AdminArea: " + thisAddress.getAdminArea() + "\n"
                                            + "FeatureName: " + thisAddress.getFeatureName();
                            // geoStringResult.add(stringThisAddress);
                            City_state=thisAddress.getAdminArea()+" ,"+thisAddress.getCountryName();
                        }
                    }

                }
                //System.out.print(stringThisAddress);
                // Log.d("9345",stringThisAddress);
                map_text.setText(City_state);
            }});


    }
    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
    }


    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year_x, month_x, day_x);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2 + 1, arg3);
        }
    };

    private void showDate(int year,int month,int day){
        if(_id==1)
            date_text.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
        if(_id==2)
            finish_date_text.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent1=new Intent(AddCicle.this,Tasks.class);
        finish();
        startActivity(intent1);
        return true;
    }
    @Override
    public void onBackPressed(){
        Intent intent1=new Intent(AddCicle.this,Tasks.class);
        finish();
        startActivity(intent1);

    }

    private List<Address> findGeocoder(Double lat, Double lon){
        final int maxResults = 2;
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(lat, lon, maxResults);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return addresses;
    }

    public class MyAdapter extends ArrayAdapter<String>{

        public MyAdapter(Context context, int textViewResourceId,  String[] objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater=getLayoutInflater();
            View row=inflater.inflate(R.layout.row, parent, false);
            TextView label=(TextView)row.findViewById(R.id.company);
            label.setText(array_spinner[position]);


            ImageView icon=(ImageView)row.findViewById(R.id.image);
            if(position>0) {
                icon.setImageResource(images[position]);
            }
            return row;
        }
    }


}