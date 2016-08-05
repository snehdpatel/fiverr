package Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sneh.myapplication.R;
import com.example.sneh.myapplication.ShowWorker;
import com.example.sneh.myapplication.db_handler;
import com.example.sneh.myapplication.worker_class;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Himanshu on 11/13/2015.
 */
public class WorkerListAdapter extends BaseAdapter {
    private final String[] s3;
    private final String[] s4;
    private final List<worker_class> worker_list;
    String[] st1;
    String[] st2;
    Context context;
    db_handler handler;
    int[] id;
    private static LayoutInflater inflater=null;
    private String today_date;
    private String money_formate;

    public WorkerListAdapter(String[] s1, String[] s2, Context context, int[] id,String[] s3,String[] s4 ,List<worker_class> worker_list, String money_formate )
    {
        this.st1=s1;
        this.st2=s2;
        this.context=context;
        this.id=id;
        this.s3=s3;
        this.s4=s4;
        this.worker_list=worker_list;
        this.money_formate=money_formate;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        handler=new db_handler(context);
        handler.onCreateTable(handler.getWritableDatabase());
    }
    @Override
    public int getCount() {
        return st1.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    class holder{
        TextView name;
        TextView price;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View rowView;
        rowView = inflater.inflate(R.layout.each_worker, null);
        holder h=new holder();
        h.name=(TextView)rowView.findViewById(R.id.each_worker_name);
        h.price=(TextView)rowView.findViewById(R.id.each_worker_price);
        h.name.setText(worker_list.get(position).getName());
        String last_update=worker_list.get(position).getLast_update();
        worker_class worker=worker_list.get(position);
        String s[]=last_update.split("/");

        Calendar thatDay = Calendar.getInstance();

        thatDay.set(Calendar.DAY_OF_MONTH,Integer.parseInt(s[0]));
        thatDay.set(Calendar.MONTH, Integer.parseInt(s[1])-1); // 0-11 so 1 less
        thatDay.set(Calendar.YEAR, Integer.parseInt(s[2]));
        Date d=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
        today_date=simpleDateFormat.format(d);
        if(worker_list.get(position).getSetactive()==2)
        {
            h.price.setText(String.valueOf(worker.getSalary())+" "+ money_formate);
            worker.setLast_update(today_date);
            handler.updateWorker(worker);
        }
        else {




            Calendar today = Calendar.getInstance();
            String s1[]=today_date.split("/");
            today.set(Calendar.DAY_OF_MONTH,Integer.parseInt(s1[0]));
            today.set(Calendar.MONTH,Integer.parseInt(s1[1])-1);
            today.set(Calendar.YEAR,Integer.parseInt(s1[2]));

            long diff=today.getTimeInMillis()-thatDay.getTimeInMillis();

            long days =  (diff / (24 * 60 * 60 * 1000));


            Log.d("days:", String.valueOf(days) + s[0] + s[1] + s[2] + s1[0] + s1[1] + s1[2]);
            if(days>0)
            {worker.setSalary(worker.getSalary() + (worker.getPrice_per_day() * (int) days));
                worker.setLast_update(today_date);
                handler.updateWorker(worker);}
            h.price.setText(String.valueOf(worker.getSalary())+ " "+ money_formate);
        }
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, ShowWorker.class);
                i.putExtra("id",id[position]);
                ((Activity)context).finish();
                context.startActivity(i);
            }
        });

        return rowView;
    }


}