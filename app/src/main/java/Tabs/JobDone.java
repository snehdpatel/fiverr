package Tabs;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sneh.myapplication.R;
import com.example.sneh.myapplication.cicle;
import com.example.sneh.myapplication.db_handler;

import java.util.ArrayList;
import java.util.List;

import Adapters.InProgressListAdapter;

/**
 * Created by sneh on 23/10/15.
 */
public class JobDone extends Fragment {
    db_handler handler;
    Context con;
    List<cicle> cicle_list = new ArrayList<cicle>();
    int user_id;
    public void getContext(Context context) {
        con=context;
        handler=new db_handler(con);
        SharedPreferences preferences=con.getSharedPreferences("user_data",Context.MODE_PRIVATE);
        user_id=preferences.getInt("user_id",-1);
        Log.d("jobdone_user_id",String.valueOf(user_id));
        handler.onCreateTable(handler.getWritableDatabase());
        cicle_list=handler.get_all_done(user_id);
        //Toast.makeText(con, "jobjone_cicle_list_size:" + String.valueOf(cicle_list.size()), Toast.LENGTH_LONG).show();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_view, container, false);
        TextView tv=(TextView)view.findViewById(R.id.count);
        tv.setText("Cicle("+cicle_list.size()+")");

        String[] title=new String[cicle_list.size()];
        String[] owner=new String[cicle_list.size()];
        String[] type=new String[cicle_list.size()];
        for(int i=0;i<cicle_list.size();i++){
            title[i]=cicle_list.get(i).getTitle();
            owner[i]=cicle_list.get(i).getOwner();
            type[i]=cicle_list.get(i).getType();
            Log.d("titlei", title[i]);
            Log.d("owneri",owner[i]);
            Log.d("typei",type[i]);
        }
        ListView lv = (ListView) view.findViewById(R.id.listView);
        lv.setAdapter(new InProgressListAdapter(getContext(), cicle_list));

        return view;
    }
}
