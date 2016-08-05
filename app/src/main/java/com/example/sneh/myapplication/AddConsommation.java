package com.example.sneh.myapplication;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddConsommation extends AppCompatActivity {


    int cicle_sql,building_sql;
    int consommation_sql=0;
    int cicle_id, building_id,consommation_id;
    int year_x, month_x, day_x;
    private TextView start_date, end_date;
    EditText designation, quantity;
    private Calendar calendar;
    db_handler handler;
    double cal_end_date = 0;
    List<food_class> food_list;
    Spinner spinner, spinner2;
    int user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_consommation);

        // final Notification_action notification_action=new Notification_action(this);
        final LinearLayout cal = (LinearLayout) findViewById(R.id.cal);
        handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());

        //getting cicle_id and building_id by sharedprefs
        SharedPreferences preferences=getSharedPreferences("app_data", Context.MODE_PRIVATE);
        cicle_id=preferences.getInt("cicle_id", -1);
        building_id=preferences.getInt("building_id", -1);
        cicle_sql=preferences.getInt("cicle_sql", -1);
        if(cicle_sql==-1)
            cicle_sql=0;
        building_sql=preferences.getInt("building_sql",-1);
        if(building_sql==-1)
            building_sql=0;

        //getting user_id by sharedprefs
        SharedPreferences pref1=getSharedPreferences("user_data", Context.MODE_PRIVATE);
        user_id=pref1.getInt("user_id",-1);
        Log.d("add_con_user_id",String.valueOf(user_id));

        Intent intent=getIntent();
        //cicle_id=intent.getIntExtra("cicle_id",-1);
        //building_id=intent.getIntExtra("building_id",-1);
        consommation_id = intent.getIntExtra("consommation_id",-1);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        designation = (EditText) findViewById(R.id.add_consommation_designation);
        quantity = (EditText) findViewById(R.id.add_consommation_quantity);
        //number = (EditText) findViewById(R.id.add_consommation_number);
        end_date = (TextView) findViewById(R.id.add_con_finish_date);

        start_date = (TextView) findViewById(R.id.add_con_start_date);
        calendar=Calendar.getInstance();
        year_x=calendar.get(Calendar.YEAR);
        month_x=calendar.get(Calendar.MONTH);
        day_x=calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year_x, month_x + 1, day_x);

        food_list = handler.getAllFood(cicle_id,building_id);

        String[] array_spinner1 = new String [food_list.size()+1];
        array_spinner1[0] = "Food_List";
        for(int i=1; i<food_list.size()+1; i++){
            array_spinner1[i] = food_list.get(i-1).getDesignation();
        }

        String[] per = {"Per day, Weekly, Monthly","Per day","Weekly","Monthly"};

        spinner = (Spinner) findViewById(R.id.add_consommation_foodlist);
        //final EditText other=(EditText)findViewById(R.id.show_building_other);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, array_spinner1);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        spinner2 = (Spinner) findViewById(R.id.add_consommation_Per);
        //final EditText other=(EditText)findViewById(R.id.show_building_other);
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, per);
        spinner2.setAdapter(adapter2);
        spinner2.setSelection(0);

        quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            //int total,_price,_capacity;
            @Override
            public void afterTextChanged(Editable s) {

                String[] parts = start_date.getText().toString().split("-");

                if (!quantity.getText().toString().isEmpty()) {
                    for (int j = 0; j < food_list.size(); j++) {
                        if (food_list.get(j).getDesignation().equals(spinner.getSelectedItem().toString()))
                            cal_end_date = (food_list.get(j).getQuantity() * 1000) / Integer.parseInt(quantity.getText().toString());
                    }
                }

                Calendar lastday = Calendar.getInstance();
                Calendar firstday = Calendar.getInstance();
                firstday.set(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]) - 1, Integer.parseInt(parts[2]));
                Log.d("date_lokkigfor", parts[0] + parts[1] + parts[2]+" "+String.valueOf(firstday.YEAR)+" "+firstday.MONTH+" "+firstday.DAY_OF_MONTH);
                if (spinner2.getSelectedItemPosition() == 3) {
                    //Toast.makeText(getApplicationContext(), "monthlyyyyyyy  " + firstday.YEAR, Toast.LENGTH_SHORT).show();
                    day_x = Integer.parseInt(parts[2]);
                    month_x = (int) ((Integer.parseInt(parts[1])-1 + (cal_end_date % 12)) % 12);
                    if((Integer.parseInt(parts[1])-1 + (cal_end_date % 12)) >= 12)
                        year_x = Integer.parseInt(parts[0]) + (int) (cal_end_date / 12) + 1;
                    else
                        year_x = Integer.parseInt(parts[0]) + (int) (cal_end_date / 12);

                } else {

                    long diff = 0;

                    if (spinner2.getSelectedItemPosition() == 1) {
                        diff = (long) (cal_end_date * (24 * 60 * 60 * 1000));
                        //Toast.makeText(getApplicationContext(), "dailyyyyyyy", Toast.LENGTH_SHORT).show();
                    }
                    if (spinner2.getSelectedItemPosition() == 2) {
                        diff = (long) (cal_end_date * (7 * 24 * 60 * 60 * 1000));
                        //Toast.makeText(getApplicationContext(), "weeklyyyyyyy", Toast.LENGTH_SHORT).show();
                    }

                    lastday.setTimeInMillis(firstday.getTimeInMillis() + diff);
                    Log.d("cal_time_millis", String.valueOf(firstday.getTimeInMillis()));


                    year_x = lastday.get(lastday.YEAR);
                    month_x = lastday.get(lastday.MONTH);
                    day_x = lastday.get(lastday.DAY_OF_MONTH);

                }
                showDate_(year_x, month_x + 1, day_x);
                Log.d("final_date", end_date.getText().toString());

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] parts = start_date.getText().toString().split("-");

                if (!quantity.getText().toString().isEmpty()) {
                    for (int j = 0; j < food_list.size(); j++) {
                        if (food_list.get(j).getDesignation().equals(spinner.getSelectedItem().toString()))
                            cal_end_date = (food_list.get(j).getQuantity() * 1000) / Integer.parseInt(quantity.getText().toString());
                    }
                }

                Calendar lastday = Calendar.getInstance();
                Calendar firstday = Calendar.getInstance();
                firstday.set(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]) - 1, Integer.parseInt(parts[2]));
                Log.d("date_lokkigfor", parts[0] + parts[1] + parts[2]+" "+String.valueOf(firstday.YEAR)+" "+firstday.MONTH+" "+firstday.DAY_OF_MONTH);
                if (spinner2.getSelectedItemPosition() == 3) {
                    //Toast.makeText(getApplicationContext(), "monthlyyyyyyy  " + firstday.YEAR, Toast.LENGTH_SHORT).show();
                    day_x = Integer.parseInt(parts[2]);
                    month_x = (int) ((Integer.parseInt(parts[1])-1 + (cal_end_date % 12)) % 12);
                    if((Integer.parseInt(parts[1])-1 + (cal_end_date % 12)) >= 12)
                        year_x = Integer.parseInt(parts[0]) + (int) (cal_end_date / 12) + 1;
                    else
                        year_x = Integer.parseInt(parts[0]) + (int) (cal_end_date / 12);

                } else {

                    long diff = 0;

                    if (spinner2.getSelectedItemPosition() == 1) {
                        diff = (long) (cal_end_date * (24 * 60 * 60 * 1000));
                        //Toast.makeText(getApplicationContext(), "dailyyyyyyy", Toast.LENGTH_SHORT).show();
                    }
                    if (spinner2.getSelectedItemPosition() == 2) {
                        diff = (long) (cal_end_date * (7 * 24 * 60 * 60 * 1000));
                        //Toast.makeText(getApplicationContext(), "weeklyyyyyyy", Toast.LENGTH_SHORT).show();
                    }

                    lastday.setTimeInMillis(firstday.getTimeInMillis() + diff);
                    Log.d("cal_time_millis", String.valueOf(firstday.getTimeInMillis()));


                    year_x = lastday.get(lastday.YEAR);
                    month_x = lastday.get(lastday.MONTH);
                    day_x = lastday.get(lastday.DAY_OF_MONTH);

                }
                showDate_(year_x, month_x + 1, day_x);
                Log.d("final_date", end_date.getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                               @Override
                                               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                   String[] parts = start_date.getText().toString().split("-");

                                                   if (!quantity.getText().toString().isEmpty()) {
                                                       for (int j = 0; j < food_list.size(); j++) {
                                                           if (food_list.get(j).getDesignation().equals(spinner.getSelectedItem().toString()))
                                                               cal_end_date = (food_list.get(j).getQuantity() * 1000) / Integer.parseInt(quantity.getText().toString());
                                                       }
                                                   }

                                                   Calendar lastday = Calendar.getInstance();
                                                   Calendar firstday = Calendar.getInstance();
                                                   firstday.set(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]) - 1, Integer.parseInt(parts[2]));
                                                   Log.d("date_lokkigfor", parts[0] + parts[1] + parts[2]+" "+String.valueOf(firstday.YEAR)+" "+firstday.MONTH+" "+firstday.DAY_OF_MONTH);
                                                   if (spinner2.getSelectedItemPosition() == 3) {
                                                       //Toast.makeText(getApplicationContext(), "monthlyyyyyyy  " + firstday.YEAR, Toast.LENGTH_SHORT).show();
                                                       day_x = Integer.parseInt(parts[2]);
                                                       month_x = (int) ((Integer.parseInt(parts[1])-1 + (cal_end_date % 12)) % 12);
                                                       if((Integer.parseInt(parts[1])-1 + (cal_end_date % 12)) >= 12)
                                                           year_x = Integer.parseInt(parts[0]) + (int) (cal_end_date / 12) + 1;
                                                       else
                                                           year_x = Integer.parseInt(parts[0]) + (int) (cal_end_date / 12);

                                                   } else {

                                                       long diff = 0;

                                                       if (spinner2.getSelectedItemPosition() == 1) {
                                                           diff = (long) (cal_end_date * (24 * 60 * 60 * 1000));
                                                           //Toast.makeText(getApplicationContext(), "dailyyyyyyy", Toast.LENGTH_SHORT).show();
                                                       }
                                                       if (spinner2.getSelectedItemPosition() == 2) {
                                                           diff = (long) (cal_end_date * (7 * 24 * 60 * 60 * 1000));
                                                           //Toast.makeText(getApplicationContext(), "weeklyyyyyyy", Toast.LENGTH_SHORT).show();
                                                       }

                                                       lastday.setTimeInMillis(firstday.getTimeInMillis() + diff);
                                                       Log.d("cal_time_millis", String.valueOf(firstday.getTimeInMillis()));


                                                       year_x = lastday.get(lastday.YEAR);
                                                       month_x = lastday.get(lastday.MONTH);
                                                       day_x = lastday.get(lastday.DAY_OF_MONTH);

                                                   }
                                                   showDate_(year_x, month_x + 1, day_x);
                                                   Log.d("final_date", end_date.getText().toString());
                                               }

                                               @Override
                                               public void onNothingSelected(AdapterView<?> parent) {

                                               }
                                           });

        start_date.addTextChangedListener(new TextWatcher() {
                                              @Override
                                              public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                              }

                                              @Override
                                              public void onTextChanged(CharSequence s, int start, int before, int count) {

                                              }

                                              @Override
                                              public void afterTextChanged(Editable s) {
                                                  calculation(start_date.getText().toString());
                                              }
                                          });

        Button save = (Button) findViewById(R.id.add_con_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (designation.getText().toString().isEmpty() || spinner.getSelectedItemPosition() == 0 || quantity.getText().toString().isEmpty() || spinner2.getSelectedItemPosition() == 0 || start_date.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show();
                } else {
                    consommation_ con = new consommation_();
                    con.setSql_consommation_id(consommation_sql);
                    con.setSql_cicle_id(cicle_sql);
                    con.setSql_building_id(building_sql);
                    con.setUser_id(user_id);
                    con.setConsommation_id(consommation_id);
                    con.setCicle_id(cicle_id);
                    con.setBuilding_id(building_id);
                    con.setDesignation(designation.getText().toString());
                    con.setFood_list(spinner.getSelectedItem().toString());
                    con.setFlag(0);
                    con.setQuantity(Integer.parseInt(quantity.getText().toString()));
                    con.setNumber(Integer.parseInt(quantity.getText().toString()));
                    con.setPer(spinner2.getSelectedItem().toString());
                    con.setDate_start(start_date.getText().toString());
                    String s[]=end_date.getText().toString().split("-");
                    int year=Integer.parseInt(s[0]);
                    int month=Integer.parseInt(s[1]);
                    int day=Integer.parseInt(s[2]);
                    String true_string;
                    if(month<10&&day<10)
                    {
                        true_string = String.valueOf(year) + "-" + "0"+String.valueOf(month) + "-" +"0"+String.valueOf(day);

                    }
                    else
                    if(month<10)
                    {
                        true_string = String.valueOf(year) + "-" + "0"+String.valueOf(month) + "-" +String.valueOf(day);

                    }
                    else
                    if(day<10)
                        true_string = String.valueOf(year) + "-" + String.valueOf(month) + "-" +"0"+String.valueOf(day);
                    else
                        true_string = String.valueOf(year) + "-" + String.valueOf(month) + "-"+String.valueOf(day);
                    Log.d("End::DATE",true_string);
                    con.setDate_end(true_string);

                    handler.createCon(con);
                    setting_class settingClass = handler.get_all_setting();
                    //if(settingClass.getNotification()=="YES") {
                    Calendar start = Calendar.getInstance();
                    String s1[] = con.getDate_start().split("-");
                    start.set(Calendar.DAY_OF_MONTH, Integer.parseInt(s1[2]));
                    start.set(Calendar.MONTH, Integer.parseInt(s1[1]) - 1);
                    start.set(Calendar.YEAR, Integer.parseInt(s1[0]));

                    Calendar enddate = Calendar.getInstance();
                    String s12[] = con.getDate_end().split("-");
                    enddate.set(Calendar.DAY_OF_MONTH, Integer.parseInt(s12[2]));
                    enddate.set(Calendar.MONTH, Integer.parseInt(s12[1]) - 1);
                    enddate.set(Calendar.YEAR, Integer.parseInt(s12[0]));
                    long diff = enddate.getTimeInMillis() - start.getTimeInMillis();
                    cicle cicle1 = handler.getCicle(cicle_id);
                    building_class buildingClass = handler.getBuilding(building_id);
                    task_class taskClass = new task_class();
                    taskClass.setBuilding_name(buildingClass.getTitle());
                    taskClass.setCicle_name(cicle1.getTitle());
                    taskClass.setDate(con.getDate_end());
                    taskClass.setDone(0);
                    taskClass.setLocation(cicle1.getLocation());
                    taskClass.setSql_task_id(0);
                    taskClass.setUser_id(user_id);
                    taskClass.setTask_name("Buy " + con.getFood_list() + "For Cicle: " + cicle1.getTitle() + " and Building: " + buildingClass.getTitle());
                    taskClass.setTime_start("00-00-00");
                    int id = handler.createTask(taskClass);
                    Log.d("Cicle+Building:", cicle1.getTitle() + " " + buildingClass.getTitle());
                    Log.d("task_length_size", String.valueOf(handler.countTask(user_id)));

                    if (settingClass.getNotification().equals("YES"))
                        this.scheduleNotification(this.getNotification("Buy " + con.getFood_list() + "For Cicle: " + cicle1.getTitle() + " and Building: " + buildingClass.getTitle()), diff, id);

                    Intent intent = new Intent(getApplicationContext(), FoodConsommation.class);
                    intent.putExtra("cicle_id", cicle_id);
                    intent.putExtra("building_id", building_id);
                    startActivity(intent);
                    finish();
                }
            }

            public void scheduleNotification(Notification notification, long delay, int id) {

                Intent notificationIntent = new Intent(AddConsommation.this, NotificationPublisher.class);
                notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, id);
                notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
                Log.d("Notification_id", String.valueOf(id));
                PendingIntent pendingIntent = PendingIntent.getBroadcast(AddConsommation.this, id, notificationIntent, PendingIntent.FLAG_ONE_SHOT);
                // long futureInMillis = SystemClock.elapsedRealtime() + delay;
                //Log.d("Alarm123",String.valueOf(delay+" "+futureInMillis));

                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + delay, pendingIntent);
            }

            public Notification getNotification(String content) {
                Notification.Builder builder = new Notification.Builder(AddConsommation.this);
                builder.setContentTitle("Food Item Needed");
                builder.setContentText(content);
                Log.d("Alarm12:", "Alarampublishaer");
                builder.setSmallIcon(R.drawable.notification_icon);
                return builder.build();
            }


        });

    }

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
        start_date.setText(new StringBuilder().append(year).append("-").append(month).append("-").append(day));
    }

    private void showDate_(int year,int month,int day){
        end_date.setText(new StringBuilder().append(year).append("-").append(month).append("-").append(day));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), FoodConsommation.class);
        intent.putExtra("cicle_id", cicle_id);
        intent.putExtra("building_id", building_id);
        startActivity(intent);
        finish();
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), FoodConsommation.class);
        intent.putExtra("cicle_id", cicle_id);
        intent.putExtra("building_id", building_id);
        startActivity(intent);
        finish();
    }

    public void calculation(String date) {

        String[] parts = date.split("-");

        if (!quantity.getText().toString().isEmpty()) {
            for (int j = 0; j < food_list.size(); j++) {
                if (food_list.get(j).getDesignation().equals(spinner.getSelectedItem().toString()))
                    cal_end_date = (food_list.get(j).getQuantity() * 1000) / Integer.parseInt(quantity.getText().toString());
            }
        }

        Calendar lastday = Calendar.getInstance();
        Calendar firstday = Calendar.getInstance();
        firstday.set(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]) - 1, Integer.parseInt(parts[2]));
        Log.d("date_lokkigfor", parts[0] + parts[1] + parts[2]+" "+String.valueOf(firstday.YEAR)+" "+firstday.MONTH+" "+firstday.DAY_OF_MONTH);
        if (spinner2.getSelectedItemPosition() == 3) {
            //Toast.makeText(getApplicationContext(), "monthlyyyyyyy  " + firstday.YEAR, Toast.LENGTH_SHORT).show();
            day_x = Integer.parseInt(parts[2]);
            month_x = (int) ((Integer.parseInt(parts[1])-1 + (cal_end_date % 12)) % 12);
            if((Integer.parseInt(parts[1])-1 + (cal_end_date % 12)) >= 12)
                year_x = Integer.parseInt(parts[0]) + (int) (cal_end_date / 12) + 1;
            else
                year_x = Integer.parseInt(parts[0]) + (int) (cal_end_date / 12);

        } else {

            long diff = 0;

            if (spinner2.getSelectedItemPosition() == 1) {
                diff = (long) (cal_end_date * (24 * 60 * 60 * 1000));
               // Toast.makeText(getApplicationContext(), "dailyyyyyyy", Toast.LENGTH_SHORT).show();
            }
            if (spinner2.getSelectedItemPosition() == 2) {
                diff = (long) (cal_end_date * (7 * 24 * 60 * 60 * 1000));
                //Toast.makeText(getApplicationContext(), "weeklyyyyyyy", Toast.LENGTH_SHORT).show();
            }

            lastday.setTimeInMillis(firstday.getTimeInMillis() + diff);
            Log.d("cal_time_millis", String.valueOf(firstday.getTimeInMillis()));


            year_x = lastday.get(lastday.YEAR);
            month_x = lastday.get(lastday.MONTH);
            day_x = lastday.get(lastday.DAY_OF_MONTH);

        }
        showDate_(year_x, month_x + 1, day_x);
        Log.d("final_date", end_date.getText().toString());
    }
}