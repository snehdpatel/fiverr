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

public class ShowReturnedExpenses extends AppCompatActivity {
    int cicle_id,building_id,returned_expense_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_returned_expenses);
        //setting up intent
        Intent intent=getIntent();
        returned_expense_id=intent.getIntExtra("id",-1);
        Log.d("show_re_id", String.valueOf(returned_expense_id));

        //db_handler handler
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        setting_class sc = handler.get_all_setting();
        final returned_expense_class returned_expense=handler.getReturnedExpense(returned_expense_id);
        cicle_id=returned_expense.getCicle_id();
        building_id=returned_expense.getBuilding_id();
        Log.d("show_re_cicle",String.valueOf(cicle_id));
        Log.d("show_re_building",String.valueOf(building_id));
        //setting up textview
        final TextView type=(TextView)findViewById(R.id.show_returned_expense_type);
        final TextView date=(TextView)findViewById(R.id.show_returned_expense_date);
        final TextView quantity=(TextView)findViewById(R.id.show_returned_expense_quantity);
        final TextView price=(TextView)findViewById(R.id.show_returned_expense_price);

        type.setText(returned_expense.getType());
        date.setText(returned_expense.getDate());
        quantity.setText(String.valueOf(returned_expense.getQuantity()));
        price.setText(String.valueOf(returned_expense.getPrice())+" "+sc.getMoney_format());
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);

        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.show_re_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShowReturnedExpenses.this, AddReturnedExpenses.class);
                i.putExtra("cicle_id", cicle_id);
                i.putExtra("building_id", building_id);
                i.putExtra("returned_expense_id", returned_expense_id);
                finish();
                startActivity(i);
            }
        });


        FloatingActionButton del = (FloatingActionButton) findViewById(R.id.show_re_del);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setTitle(getResources().getString(R.string.alert_delete_title));
        builder.setMessage(getResources().getString(R.string.alert_delete_message));
        builder.setPositiveButton(getResources().getString(R.string.alert_detete_button_yes), new DialogInterface.OnClickListener() {



            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                //write whole functionaliy that your delete button is doing
                if(returned_expense.getFlag()==0){
                    handler.deleteReturnedExpense(returned_expense);
                }else{
                    returned_expense.setFlag(-1);
                    handler.updateReturnedExpense(returned_expense);
                }

                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_delete_entry), Toast.LENGTH_SHORT).show();

                Log.d("show_re_flag", String.valueOf(returned_expense.getFlag()));
                Intent i = new Intent(ShowReturnedExpenses.this, ReturnedExpenses.class);
                i.putExtra("cicle_id",cicle_id);
                i.putExtra("building_id",building_id);
                finish();
                startActivity(i);
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


        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.show();
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent(ShowReturnedExpenses.this, ReturnedExpenses.class);
        i.putExtra("cicle_id",cicle_id);
        i.putExtra("building_id",building_id);
        finish();
        startActivity(i);
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(ShowReturnedExpenses.this, ReturnedExpenses.class);
        i.putExtra("cicle_id",cicle_id);
        i.putExtra("building_id",building_id);
        finish();
        startActivity(i);
    }

}