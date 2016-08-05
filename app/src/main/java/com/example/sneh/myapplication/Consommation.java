package com.example.sneh.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import Adapters.ConListAdapter;
import Adapters.MedicalListAdapter;

public class Consommation extends Fragment {

    int cicle_id, building_id;

    public void setid(int cicle_id, int building_id){
        this.cicle_id = cicle_id;
        this.building_id = building_id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_consommation, container, false);

        /*Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_medical);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        Log.d("In_con_cicle_id", String.valueOf(cicle_id));
        Log.d("Incon_building_id", String.valueOf(building_id));

        FloatingActionButton edit = (FloatingActionButton) view.findViewById(R.id.add_con);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddConsommation.class);
                intent.putExtra("cicle_id", cicle_id);
                intent.putExtra("building_id", building_id);
                ((Activity)getContext()).getIntent();
                startActivity(intent);

            }
        });

        //Setting db_handler
        final db_handler handler=new db_handler(getContext());
        handler.onCreateTable(handler.getWritableDatabase());

        List<consommation_> con_list=handler.get_all_con(cicle_id, building_id);
        Log.d("con_list_size",String.valueOf(con_list.size()));
        String[] text2 =new String[con_list.size()];
        String[] text4 = new String[con_list.size()];
        String[] text5 =new String[con_list.size()];
        String[] text6 =new String[con_list.size()];
        String[] text7 =new String[con_list.size()];
        //int[] consommation_id=new int[con_list.size()];
        for(int i=0;i<con_list.size();i++){
            text2[i] = con_list.get(i).getDesignation();
            text4[i] = String.valueOf(con_list.get(i).getQuantity());
            Log.d("quantity",String.valueOf(con_list.get(i).getQuantity()));
            text5[i] = con_list.get(i).getPer();
            text6[i] = con_list.get(i).getDate_start();
            text7[i] = con_list.get(i).getDate_end();
            Log.d("price_con",String.valueOf(con_list.get(i).getNumber()));
            //medical_id[i]=medical_list.get(i).getMedical_id();
        }

        TextView number = (TextView) view.findViewById(R.id.textView13);
        number.setText("Consommations (" + con_list.size() + ")");
        ListView lv = (ListView) view.findViewById(R.id.con_list);
        lv.setAdapter(new ConListAdapter(getContext(),text2,text4,text5,text6, text7));

        return view;

    }

}
