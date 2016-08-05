package com.example.sneh.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.sneh.myapplication.R;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    EditText login_email_id,login_password;
    EditText register_fname,register_password,register_password_re,register_email,register_lname;
    Button login_button,register_button,forgoet_password;
    db_handler handler;
    String[] array_spinner,array_language;
    Spinner spinner;
    int language;

    setting_class setting = new setting_class();
    int spinner_position;
    private int[] images;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email_id=(EditText)findViewById(R.id.login_email_id);
        login_password=(EditText)findViewById(R.id.login_password);
        login_button = (Button) findViewById(R.id.login_button);
        handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        register_fname=(EditText)findViewById(R.id.register_firstname);
        register_lname=(EditText)findViewById(R.id.register_lastname);
        register_password=(EditText)findViewById(R.id.register_password);
        register_password_re=(EditText)findViewById(R.id.register_password_re);
        register_email=(EditText)findViewById(R.id.register_email);
        register_button=(Button)findViewById(R.id.register_button);
        forgoet_password=(Button)findViewById(R.id.forget_password_button);

        final Setting setting_ = new Setting();

        array_spinner = new String[]{"Select Language","English","French"};
        images= new int[]{0, R.drawable.uk, R.drawable.france};
        array_language = new String[]{"en","fr"};
        spinner = (Spinner) findViewById(R.id.add_select_language);
        // ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, array_spinner1);
        //spinner.setAdapter(adapter);
        spinner.setAdapter(new MyAdapter(Login.this,R.layout.row,array_spinner));
        spinner.setSelection(0);

        Intent i= getIntent();
        language = i.getIntExtra("language" , 0);
        Log.d("language" , String.valueOf(language));
        if(language == 2)   spinner.setSelection(2);
        if(language == 1)   spinner.setSelection(1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner_position=position;
                if(language != position && position != 0) {
                    Intent i = new Intent(Login.this, Login.class);
                    i.putExtra("language", position);
                    setting_.setLocal(array_language[position - 1], Login.this);
                    finish();
                    startActivity(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        SharedPreferences preferences=getSharedPreferences("setting_data", Context.MODE_PRIVATE);

        setting_class setting=new setting_class();
        setting.setNotification("YES");
        setting.setMoney_format("$");
        setting.setCountry(getResources().getString(R.string.country_usa));

        handler.insert_setting(setting);
        Log.d("select_notif", setting.getNotification());
        Log.d("select country", setting.getCountry());
        Log.d("select monety", setting.getMoney_format());

        //}

        SharedPreferences pref=getSharedPreferences("user_data", Context.MODE_PRIVATE);
        int user_id=pref.getInt("user_id",-1);
        if(user_id!=-1){
            Intent intent=new Intent(Login.this,Tasks.class);
            finish();
            startActivity(intent);
        }

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_id=login_email_id.getText().toString();
                String password=login_password.getText().toString();
                if(email_id.isEmpty() || password.isEmpty()){
                    Toast.makeText(Login.this,getResources().getString(R.string.empty_uname_password), Toast.LENGTH_SHORT).show();
                }else if(email_id.isEmpty() && !isValidEmail(email_id)){
                    Toast.makeText(Login.this, getResources().getString(R.string.enter_valid_email), Toast.LENGTH_SHORT).show();
                }
                else{
                    connection_class connection=new connection_class();
                    SharedPreferences preferences=getSharedPreferences("setting_data", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();

                    //if(preferences.getString("language", "no").equals("no")) {
                    Log.d("Login_editor", "Its running");
                    editor.putString("notification", "YES");
                    editor.putString("country", getResources().getString(R.string.country_usa));
                    editor.putString("money", "$");
                    editor.putString("language", array_language[spinner_position]);
                    editor.putString("video", "YES");
                    editor.apply();
                    if(connection.check_connection(Login.this)>0) {
                        connection.login(Login.this, email_id, password);
                    }
                    //Intent intent1=new Intent(Login.this,Tasks.class);
                    //startActivity(intent1);
                }

                /*setting.setNotification("NO");
                setting.setCountry("United States");
                setting.setMoney_format("$");
                handler.insert_setting(setting);
                */
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname,lname,password,password_re,email;
                fname=register_fname.getText().toString();
                lname=register_lname.getText().toString();
                password=register_password.getText().toString();
                password_re=register_password_re.getText().toString();
                email=register_email.getText().toString();

                if(fname.isEmpty() || lname.isEmpty() || password.isEmpty() || email.isEmpty()){
                    Toast.makeText(Login.this, getResources().getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show();
                }
                else if(!isValidEmail(email)){
                    Toast.makeText(Login.this, getResources().getString(R.string.enter_valid_email), Toast.LENGTH_SHORT).show();
                }
                else if(password.equals(password_re)==false && password!=""){
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.password_not_match),Toast.LENGTH_SHORT).show();
                }
                else{
                    connection_class connection=new connection_class();
                    if(connection.check_connection(Login.this)>0) {
                        connection.register(Login.this, fname, lname, email, password);
                    }
                }
            }
        });

        forgoet_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://duckduckgo.com/"));
                startActivity(intent);
            }
        });

    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
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