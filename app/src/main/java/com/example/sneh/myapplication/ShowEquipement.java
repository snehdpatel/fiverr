package com.example.sneh.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ShowEquipement extends AppCompatActivity {
    TextView type,designation,quantity,price;
    equipment_ equipment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_equipement);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //setting db
        final db_handler handler=new db_handler(getApplicationContext());
        handler.onCreateTable(handler.getWritableDatabase());
        setting_class sc = handler.get_all_setting();
        //getting id;
        Intent intent=getIntent();
        final int equip_id=intent.getIntExtra("id", -1);
        Log.d("equip_id",String.valueOf(equip_id));


        equipment=handler.getEquipment(equip_id);
        Log.d("show_equip_esql_id",String.valueOf(equipment.getSql_equip_id()));
        Log.d("show_equip_cicsql_id",String.valueOf(equipment.getSql_cicle_id()));
        Log.d("show_equip_buisql_id",String.valueOf(equipment.getSql_building_id()));
        type=(TextView)findViewById(R.id.show_equipment_type);
        designation=(TextView)findViewById(R.id.show_equipment_designation);
        price=(TextView)findViewById(R.id.show_equipment_price);
        quantity=(TextView)findViewById(R.id.show_equipment_quantity);

        type.setText(equipment.getType());
        designation.setText(equipment.getDesignation());
        quantity.setText(String.valueOf(equipment.getQuantity()));
        price.setText(String.valueOf(equipment.getPrice())+" " + sc.getMoney_format());

        FloatingActionButton edit = (FloatingActionButton) findViewById(R.id.show_equi_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddEquipment.class);
                intent.putExtra("cicle_id", equipment.getCicle_id());
                intent.putExtra("building_id", equipment.getBuilding_id());
                intent.putExtra("equip_id", equipment.getEquip_id());
                finish();
                startActivity(intent);
            }
        });

        /*FloatingActionButton delete=(FloatingActionButton)findViewById(R.id.show_equi_del);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.show();//calling og dailog box when delete button is called
            }
        });*/

        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setTitle(getResources().getString(R.string.alert_delete_title));
        builder.setMessage(getResources().getString(R.string.alert_delete_message));
        builder.setPositiveButton(getResources().getString(R.string.alert_detete_button_yes), new DialogInterface.OnClickListener() {


            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                //write whole functionaliy that your delete button is doing
                if(equipment.getFlag()==0){
                    Log.d("equip_delete_del", String.valueOf(equipment.getFlag()));
                    handler.deleteEquipment(equipment);
                   // Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_delete_entry), Toast.LENGTH_SHORT).show();


                }
                else{
                    equipment.setFlag(-1);
                    Log.d("equip_delete_upda",String.valueOf(equipment.getFlag()));
                    handler.updateEquipment(equipment);
                }
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_delete_entry), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), Equipment.class);
                intent.putExtra("cicle_id", equipment.getCicle_id());
                intent.putExtra("building_id", equipment.getBuilding_id());
                finish();
                startActivity(intent);

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

        FloatingActionButton delete=(FloatingActionButton)findViewById(R.id.show_equi_del);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.show();
            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(ShowEquipement.this, Equipment.class);
        intent.putExtra("cicle_id",equipment.getCicle_id());
        intent.putExtra("building_id",equipment.getBuilding_id());
        finish();
        startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(ShowEquipement.this, Equipment.class);
        intent.putExtra("cicle_id",equipment.getCicle_id());
        intent.putExtra("building_id",equipment.getBuilding_id());
        finish();
        startActivity(intent);
    }

}

