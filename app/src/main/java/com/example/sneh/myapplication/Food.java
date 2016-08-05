package com.example.sneh.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import Adapters.EachListAdapter;

public class Food extends Fragment {

    int cicle_id, building_id;


    public void setid(int cicle_id, int building_id){
        this.cicle_id = cicle_id;
        this.building_id = building_id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_food, container, false);

        /*Toolbar myToolbar = (Toolbar) view.findViewById(R.id.my_toolbar_food);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        TextView tv = (TextView) view.findViewById(R.id.textView111);

        Log.d("In_food_cicle_id", String.valueOf(cicle_id));
        Log.d("In_food_building_id", String.valueOf(building_id));
        //Log.d("In food_food_id", String.valueOf(food_id));

        //setting up db_handler to get cicle-id,building_id and food_id
        final db_handler handler=new db_handler(getContext());
        handler.onCreateTable(handler.getWritableDatabase());
        setting_class sc = handler.get_all_setting();

        List<food_class> food_list=handler.getAllFood(cicle_id,building_id);
        Log.d("food_list_size",String.valueOf(food_list.size()));

        String[] text1 =new String[food_list.size()];
        String[] text2 =new String[food_list.size()];
        String[] text3 =new String[food_list.size()];
        String[] text4 =new String[food_list.size()];
        String[] text5 =new String[food_list.size()];
        int[] id=new int[food_list.size()];

        for(int i=0;i<food_list.size();i++){
            text1[i]="Designation";
            text2[i]=food_list.get(i).getDesignation();
            text3[i]=food_list.get(i).getDate();
            text4[i]="Amount";
            int amount=food_list.get(i).getPrice()*food_list.get(i).getQuantity();
            text5[i]=String.valueOf(amount)+" "+sc.getMoney_format();
            id[i]=food_list.get(i).getFood_id();
        }

        ListView lv = (ListView) view.findViewById(R.id.food_list);
        lv.setAdapter(new EachListAdapter(getContext(), 2, text1, text2, text3, text4, text5,id));

        FloatingActionButton edit = (FloatingActionButton) view.findViewById(R.id.add_food);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddFood.class);
                intent.putExtra("cicle_id",cicle_id);
                intent.putExtra("building_id",building_id);
                startActivity(intent);
                ((Activity)getContext()).finish();
            }
        });

        tv.setText("Food (" + food_list.size() + ")");

        return view;

    }

}