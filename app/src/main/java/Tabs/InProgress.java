package Tabs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sneh.myapplication.AddCicle;
import com.example.sneh.myapplication.Equipment;
import com.example.sneh.myapplication.R;
import com.example.sneh.myapplication.Setting;
import com.example.sneh.myapplication.ShowCicle;
import com.example.sneh.myapplication.cicle;
import com.example.sneh.myapplication.db_handler;

import java.util.ArrayList;
import java.util.List;

import Adapters.InProgressListAdapter;

/**
 * Created by sneh on 23/10/15.
 */
public class InProgress extends Fragment {
    db_handler handler;
    Context con;
    List<cicle> cicle_list = new ArrayList<cicle>();
    int user_id;
    public void getContext(Context context) {
        con=context;
        SharedPreferences preferences=con.getSharedPreferences("user_data",Context.MODE_PRIVATE);
        user_id=preferences.getInt("user_id",-1);
        Log.d("inprog_user_id",String.valueOf(user_id));
        handler=new db_handler(con);
        handler.onCreateTable(handler.getWritableDatabase());
        cicle_list=handler.get_all_not_done(user_id);

      //  Toast.makeText(con,"inprogress_list_size="+String.valueOf(cicle_list.size()),Toast.LENGTH_SHORT).show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inprogress, container, false);
        TextView tv=(TextView)view.findViewById(R.id.inprogress_cicle_count);

        tv.setText("Cicle("+String.valueOf(cicle_list.size())+")");

        FloatingActionButton addCicle = (FloatingActionButton) view.findViewById(R.id.add_cicle);
        addCicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
                Intent i = new Intent(getContext() , AddCicle.class);
                startActivity(i);
            }
        });

        FloatingActionButton setting = (FloatingActionButton) view.findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), Setting.class));
            }
        });
        String[] title=new String[cicle_list.size()];
        String[] owner=new String[cicle_list.size()];
        String[] type=new String[cicle_list.size()];
        for(int i=0;i<cicle_list.size();i++){
            title[i]=cicle_list.get(i).getTitle();
            owner[i]=cicle_list.get(i).getOwner();
            type[i]=cicle_list.get(i).getType();
            Log.d("titlei",title[i]);
            Log.d("owneri",owner[i]);
            Log.d("typei",type[i]);
        }
        ListView lv = (ListView) view.findViewById(R.id.listView_inprogress);
        lv.setAdapter(new InProgressListAdapter(getContext(), cicle_list));

        return view;
    }
}