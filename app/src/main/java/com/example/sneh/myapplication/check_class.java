package com.example.sneh.myapplication;

import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by starsilver on 7/12/15.
 */
public class check_class {
    Context con;

    public void check_cicle(List<cicle> cicle_list,Context context){
        con=context;
        Log.d("in check cicle","IN check cicle");
        int i=0;
        for (i=0;i<cicle_list.size();i++){
            if(cicle_list.get(i).getFlag()==-1){
                delete_class delete=new delete_class(con);
                delete.delete_cicle(cicle_list.get(i));
            }
            Log.d("check_cicle_flag:"+String.valueOf(cicle_list.get(i).getCicle_id()),String.valueOf(cicle_list.get(i).getFlag()));
        }
    }

    public void check_building(List<building_class> building_list,Context context){
        con=context;
        int i=0;
        Log.d("in_check_building","in check building");
        Log.d("in_check_" , String.valueOf(building_list.size()));
        for(i=0;i<building_list.size();i++){
            if(building_list.get(i).getFlag()==-1){
                delete_class delete=new delete_class(con);
                delete.delete_building(building_list.get(i));
            }
            Log.d("check_build_flag:"+String.valueOf(building_list.get(i).getCicle_id()),String.valueOf(building_list.get(i).getFlag()));
        }
    }
}
