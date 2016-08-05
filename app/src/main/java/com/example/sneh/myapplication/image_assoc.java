package com.example.sneh.myapplication;

import android.app.Activity;
import android.util.Log;

/**
 * Created by starsilver on 7/11/15.
 */
public class image_assoc  extends Activity {
    int[] cicle_images= new int[]{0, R.drawable.simpleanimal, R.drawable.simpleanimal2, R.drawable.simpleanimal3};
    String[] cicle_array_spinner = new String[]{"Type(Select)","type1","Type 2","Type 3"};

    String[] building_array_spinner=new String[]{"Type(Select)","type1","Type 2","Type 3","Other"};
    int[] building_images= new int[]{0, R.drawable.simplebuilding1, R.drawable.simplebuilding2, R.drawable.simplebuilding3,R.drawable.other};

    public int get_image_id(String check){
        int got=-1;
        Log.d("cicle_image_assoc",check);
        for(int i=0;i<cicle_images.length;i++){
            if(cicle_array_spinner[i].equals(check)){
                got=i;
            }
        }
        Log.d("cicle_image_got",String.valueOf(got));
        return cicle_images[got];
    }

    public int get_building_image_id(String check){
        int got=-1;
        Log.d("bulding_image_assoc",check);
        for(int i=0;i<building_images.length;i++){
            if(building_array_spinner[i].equals(check)){
                got=i;
            }
        }
        return building_images[got];
    }

}
