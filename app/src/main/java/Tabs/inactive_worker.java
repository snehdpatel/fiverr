package Tabs;

import android.app.Fragment;

/**
 * Created by Himanshu on 11/13/2015.
 */


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sneh.myapplication.AddWorker;
import com.example.sneh.myapplication.R;
import com.example.sneh.myapplication.db_handler;
import com.example.sneh.myapplication.setting_class;
import com.example.sneh.myapplication.worker_class;

import java.util.List;

import Adapters.EachListAdapter;
import Adapters.WorkerListAdapter;


/**
 * Created by Himanshu on 11/13/2015.
 */
public class inactive_worker extends android.support.v4.app.Fragment {


    private int building_id;
    private int cicle_id;
    private Context con;
    private db_handler handler;
    List<worker_class> worker_list;


    public void getContext(Context context) {
        con=context;
        handler=new db_handler(con);
        //Toast.makeText(con,"inprogress_list_size="+String.valueOf(cicle_list.size()),Toast.LENGTH_SHORT).show();
    }

    public void get_ids(int cicle_id,int building_id){
        this.cicle_id=cicle_id;
        this.building_id=building_id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {


        Log.d("workeria_cicle_id", String.valueOf(cicle_id));
        Log.d("workeria_building_id", String.valueOf(building_id));

        handler.onCreateTable(handler.getWritableDatabase());
        worker_list=handler.get_all_inactive_worker(cicle_id, building_id);
        setting_class sc = handler.get_all_setting();
        // worker_salery_list=handler.get_all_worker_salary(cicle_id,building_id);
        View view = inflater.inflate(R.layout.list_view, container, false);
        TextView tv=(TextView)view.findViewById(R.id.count);
        tv.setText("Worker ("+worker_list.size() + ")");
        FloatingActionButton addworker=(FloatingActionButton)view.findViewById(R.id.add_worker_button);

        String[] name=new String[worker_list.size()];
        String[] price=new String[worker_list.size()];
        int[] id=new int[worker_list.size()];
        String[] text1 = new String[worker_list.size()];
        String[] text2 = new String[worker_list.size()];
        String[] text3 = new String[worker_list.size()];
        String[] text4 = new String[worker_list.size()];
        String[] text5 = new String[worker_list.size()];
        for(int i=0;i<worker_list.size();i++)
        {    text1[i]="";
            name[i]=worker_list.get(i).getName();
            price[i]=String.valueOf(worker_list.get(i).getPrice_per_day());
            id[i]=worker_list.get(i).getWorker_id();
            text4[i]=worker_list.get(i).getDate_start();
            text5[i]=String.valueOf(worker_list.get(i).getSetactive());
        }

        ListView lv=(ListView)view.findViewById(R.id.listView);
        lv.setAdapter(new WorkerListAdapter(name,price,getContext(),id,text4,text5,worker_list, sc.getMoney_format()));
        return  view;
    }
}