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

public class ShowExpense extends AppCompatActivity {
    int cicle_id,building_id,additional_expense_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_expense);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //setting db_handler
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        setting_class sc = handler.get_all_setting();

        //setting up intent and getting additional_expense_id
        Intent intent=getIntent();
        additional_expense_id=intent.getIntExtra("id",-1);
        Log.d("show_expense_aei",String.valueOf(additional_expense_id));

        //setting up textview
        TextView type=(TextView)findViewById(R.id.show_expense_type);
        TextView designation=(TextView)findViewById(R.id.show_expense_designation);
        TextView date=(TextView)findViewById(R.id.show_expense_date);
        TextView quantity=(TextView)findViewById(R.id.show_expense_quantity);
        TextView price=(TextView)findViewById(R.id.show_expense_price);

        final additional_expense_class additional_expense=handler.getAdditionalExpense(additional_expense_id);
        cicle_id=additional_expense.getCicle_id();
        building_id=additional_expense.getBuilding_id();
        Log.d("show_expense_cicle",String.valueOf(cicle_id));
        Log.d("show_expense_building",String.valueOf(building_id));
        type.setText(additional_expense.getType());
        designation.setText(additional_expense.getDesignation());
        date.setText(additional_expense.getDate());
        quantity.setText(String.valueOf(additional_expense.getQuantity()));
        price.setText(String.valueOf(additional_expense.getPrice_per_one())+ " "+sc.getMoney_format());

        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.show_expense_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShowExpense.this, AddExpense.class);
                i.putExtra("cicle_id",cicle_id);
                i.putExtra("building_id",building_id);
                i.putExtra("additional_expense_id",additional_expense_id);
                finish();
                startActivity(i);
            }
        });

        FloatingActionButton del = (FloatingActionButton) findViewById(R.id.show_expense_del);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setTitle(getResources().getString(R.string.alert_delete_title));
        builder.setMessage(getResources().getString(R.string.alert_delete_message));
        builder.setPositiveButton(getResources().getString(R.string.alert_detete_button_yes), new DialogInterface.OnClickListener() {


            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                //write whole functionaliy that your delete button is doing
                if (additional_expense.getFlag() == 0) {
                    handler.deleteAdditionalExpense(additional_expense);
                } else {
                    additional_expense.setFlag(-1);
                    handler.updateAdditionalExpense(additional_expense);
                }
                Log.d("show_ae_flag", String.valueOf(additional_expense.getFlag()));
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_delete_entry), Toast.LENGTH_SHORT).show();


                Intent i = new Intent(ShowExpense.this, Expense.class);
                i.putExtra("cicle_id", cicle_id);
                i.putExtra("building_id", building_id);
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
        Intent intent=new Intent(ShowExpense.this,Expense.class);
        intent.putExtra("cicle_id",cicle_id);
        intent.putExtra("building_id",building_id);
        finish();
        startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent intent=new Intent(ShowExpense.this,Expense.class);
        intent.putExtra("cicle_id",cicle_id);
        intent.putExtra("building_id",building_id);
        finish();
        startActivity(intent);

    }

}