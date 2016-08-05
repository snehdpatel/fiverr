package Tabs;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sneh.myapplication.NotificationPublisher;
import com.example.sneh.myapplication.TodaysTask;
import com.example.sneh.myapplication.task_class;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.example.sneh.myapplication.R;
import com.example.sneh.myapplication.db_handler;
import com.example.sneh.myapplication.title_;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by sneh on 5/11/15.
 */
public class TodaysTasks extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    TodaysDone td;
    int title_id;
    String[] button;
    List<task_class> title_list;
    ListView lv;
    int[] t_id;
    db_handler handler;
    int user_id;
    String date;
    String un_done[];

    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView count;
    public int d;
    private List<title_> title_list1;

    public TodaysTaskListAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(TodaysTaskListAdapter adapter) {
        this.adapter = adapter;
    }

    TodaysTaskListAdapter adapter;
    private Context con;

    public void getContext(Context context) {
        con=context;

        //Toast.makeText(con, "jobjone_cicle_list_size:"+String.valueOf(cicle_list.size()), Toast.LENGTH_LONG).show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        td=new TodaysDone();
        View view = inflater.inflate(R.layout.list_view_today_task, container, false);
        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh_layout);

        handler = new db_handler(getContext());
        handler.onCreateTable(handler.getWritableDatabase());

        //getting date

        Date date_obj= Calendar.getInstance().getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        date=sdf.format(date_obj);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf1.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        date = sdf.format(c.getTime()); // dt is now the new date
        Log.d("date_", date);
        Log.d("date",date);
        //getting user_id
        SharedPreferences preferences=con.getSharedPreferences("user_data", Context.MODE_PRIVATE);
        user_id=preferences.getInt("user_id",-1);
        Log.d("todays_done_user_id", String.valueOf(user_id));

        Intent intent = getActivity().getIntent();
        title_id = intent.getIntExtra("title id", -1);
        Log.d("title_id", String.valueOf(title_id));
        lv = (ListView) view.findViewById(R.id.listView_today_task);
        swipeRefreshLayout.setOnRefreshListener(this);

        count=(TextView)view.findViewById(R.id.count_today_task);
        // un_done = new String[]{"Do stuff0111", "Do stuff0222", "Do stuff0333"};


    /*title_list = handler.get_all_not_done_task();
    title_list1=handler.get_all_done_title();
    if(title_list.size()==0&&title_list1.size()==0)
    if(title_list.size()<=0){
      for(int i=0; i<un_done.length; i++){
        title_ title = new title_();
        title.setTitle(un_done[i]);
        title.setDone(1);


        handler.CreateTitle(title);

      }}*/
        title_list = handler.get_all_not_done_task(user_id,date);
        int flag=-1;
        for(int i=0;i<title_list.size();i++)
        {if(title_list.get(i).getSql_task_id()>0)
            check_if_set(title_list.get(i).getSql_task_id());
        }
        int j=0,k=0,f=0;
        while(j!=-1&&k<title_list.size()){
            if(title_list.get(k).getSql_task_id()>0)
            {
                j=-1;
                Log.d("Value_of_j",String.valueOf(j)+" "+String.valueOf(k));
            }

            k++;
        }
        if(k>0)
            Log.d("notification_detail_b", title_list.get(k - 1).getTask_name() + " " + String.valueOf(title_list.get(k - 1).getSql_task_id())+handler.get_all_setting().getNotification());

        if (handler.get_all_setting().getNotification().equals("YES")&&k>0) {
            if(title_list.get(k-1).getSql_task_id()>0){
                scheduleNotification(getNotification(title_list.get(k - 1).getTask_name()), 1000 *60*30, title_list.get(k - 1).getSql_task_id());

                Log.d("notification_details", title_list.get(k - 1).getTask_name() + " " + String.valueOf(title_list.get(k - 1).getSql_task_id()));}

        }//    }else{
//      Log.d("value _of_f",String.valueOf(f));
//    if (handler.get_all_setting().getNotification().equals("YES")&&f>0) {
//      scheduleNotification(getNotification(title_list.get(f - 1).getTask_name()), 1000*60*30,title_list.get(f-1).getSql_task_id());
//      Log.d("notification_details",title_list.get(f-1).getTask_name()+" "+String.valueOf(title_list.get(f-1).getSql_task_id()));
//
//    }}

        count.setText("Task ("+String.valueOf(title_list.size())+")");
        Log.d("List:",String.valueOf(title_list.size()));
        Log.d("size_of_task", String.valueOf(handler.countTask(user_id)));
        adapter=new TodaysTaskListAdapter(getContext(), title_list);

        lv.setAdapter(adapter);

        //title_list = handler.get_all_not_done_title();



        return view;
    }
    public void check_if_set(int id)
    {
        Intent intent=new Intent(con,NotificationPublisher.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(con, id, intent,0);
        AlarmManager alarmManager=(AlarmManager)con.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }
    public void scheduleNotification(Notification notification, long delay, int id) {

        Intent notificationIntent = new Intent(con, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, id);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        Log.d("Notification_id", String.valueOf(id));
        PendingIntent pendingIntent = PendingIntent.getBroadcast(con, id, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        // long futureInMillis = SystemClock.elapsedRealtime() + delay;
        //Log.d("Alarm123",String.valueOf(delay+" "+futureInMillis));
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        AlarmManager alarmManager = (AlarmManager) con.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + delay,delay, pendingIntent);
    }

    public Notification getNotification(String content) {
        PendingIntent contentIntent = PendingIntent.getActivity(con, 0,
                new Intent(con, TodaysTask.class), PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intent=new Intent(con,TodaysTask.class);
        Notification.Builder builder = new Notification.Builder(con);
        builder.setContentTitle("New Task");
        builder.setContentIntent(contentIntent);
        builder.setContentText(content);
        builder.setDefaults(Notification.DEFAULT_SOUND);
        builder.setWhen((new Date()).getTime());
        Log.d("Alarm12:", "Alarampublishaer");
        builder.setSmallIcon(R.drawable.notification_icon);
        return builder.build();
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        title_list = handler.get_all_not_done_task(user_id,date);
        count.setText("Task ("+String.valueOf(title_list.size())+")");
        Log.d("Listtt:",String.valueOf(title_list.size()));
        adapter=new TodaysTaskListAdapter(getContext(), title_list);

        lv.setAdapter(adapter);

        title_list = handler.get_all_not_done_task(user_id,date);
        int flag=-1;
        for(int i=0;i<title_list.size();i++)
        {if(title_list.get(i).getSql_task_id()>0)
            check_if_set(title_list.get(i).getSql_task_id());
        }
        int j=0,k=0,f=0;
        while(j!=-1&&k<title_list.size()){
            if(title_list.get(k).getSql_task_id()>0)
            {
                j=-1;
                Log.d("Value_of_j",String.valueOf(j)+" "+String.valueOf(k));
            }

            k++;
        }
        if(k>0)
            Log.d("notification_detail_b", title_list.get(k - 1).getTask_name() + " " + String.valueOf(title_list.get(k - 1).getSql_task_id())+handler.get_all_setting().getNotification());

        if (handler.get_all_setting().getNotification().equals("YES")&&k>0) {
            if(title_list.get(k-1).getSql_task_id()>0){
                scheduleNotification(getNotification(title_list.get(k - 1).getTask_name()), 1000 * 60*30, title_list.get(k - 1).getSql_task_id());

                Log.d("notification_details", title_list.get(k - 1).getTask_name() + " " + String.valueOf(title_list.get(k - 1).getSql_task_id()));}

        }
        swipeRefreshLayout.setRefreshing(false);

    }



/*
  public void do_refresh(){
    handler = new db_handler(getContext());
    handler.onCreateTable(handler.getWritableDatabase());
    Toast.makeText(getContext(), "fuck??????", Toast.LENGTH_LONG).show();
    title_list = handler.get_all_not_done_title();
    button = new String[title_list.size()];
    for(int i=0; i< title_list.size(); i++){
      button[i]=String.valueOf(title_list.get(i).getTitle());
    }

    lv = (ListView) lv.findViewById(R.id.listView);
    lv.setAdapter(new TodaysTaskListAdapter(getContext(), button, 1, t_id));
  }*/


    /**
     * Created by sneh on 23/10/15.
     */
    public class TodaysTaskListAdapter extends BaseAdapter {

        TodaysTasks tsk = new TodaysTasks();
        TodaysDone tdn = new TodaysDone();
        String [] button;
        int[] t_id;
        public int refresh = 0;
        int titleid;
        Context context;
        int done;
        List<task_class> title_list;
        private LayoutInflater inflater=null;
        private List<task_class> done_list;


        public TodaysTaskListAdapter(Context context,List<task_class> title_list) {
            this.button = button;
            this.context = context;
            this.done = done;
            this.t_id = t_id;
            this.title_list=title_list;



            inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return title_list.size();
        }

        @Override
        public Object getItem(int position) {
            return title_list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public class Holder
        {
            Button button;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

    /* final db_handler handler = new db_handler(context);
    handler.onCreateTable(handler.getWritableDatabase());
    final title_ title = handler.getTitle(t_id[position]);*/

            final Holder holder = new Holder();
            final View rowView;
            rowView = inflater.inflate(R.layout.tasks, null);

            holder.button = (RadioButton) rowView.findViewById(R.id.radioButton);
            final task_class t=title_list.get(position);
            holder.button.setText(t.getTask_name());

            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
       /* if (done == 1)
          title.setDone(2);
        else
          title.setDone(1);
        handler.updateTitle(title);
        refresh = 1;*/
                    //tsk.do_refresh();
                    //tdn.do_refresh();


                    t.setDone(1);

                    check_if_set(t.getSql_task_id());
                    Log.d("UNCKECKED _in_adapter:", String.valueOf(t.getSql_task_id()));

                    handler.updateTask(t);
                    done_list=handler.get_all__done_task(user_id,date);
                    for(int i=0;i<done_list.size();i++)
                    {  if(done_list.get(i).getSql_task_id()>0)
                        check_if_set(done_list.get(i).getSql_task_id());
                    }
                    title_list.remove(position);
                    adapter.notifyDataSetChanged();
                    title_list = handler.get_all_not_done_task(user_id,date);
                    int flag=-1;
                    for(int i=0;i<title_list.size();i++)
                    {if(title_list.get(i).getSql_task_id()>0)
                        check_if_set(title_list.get(i).getSql_task_id());
                    }
                    int j=0,k=0,f=0;
                    while(j!=-1&&k<title_list.size()){
                        if(title_list.get(k).getSql_task_id()>0)
                        {
                            j=-1;
                            Log.d("Value_of_j",String.valueOf(j)+" "+String.valueOf(k));
                        }

                        k++;
                    }
                    if(k>0)
                        Log.d("notification_detail_b", title_list.get(k - 1).getTask_name() + " " + String.valueOf(title_list.get(k - 1).getSql_task_id())+handler.get_all_setting().getNotification());

                    if (handler.get_all_setting().getNotification().equals("YES")&&k>0) {
                        if(title_list.get(k-1).getSql_task_id()>0){
                            scheduleNotification(getNotification(title_list.get(k - 1).getTask_name()), 1000 * 60*30, title_list.get(k - 1).getSql_task_id());

                            Log.d("notification_details", title_list.get(k - 1).getTask_name() + " " + String.valueOf(title_list.get(k - 1).getSql_task_id()));}

                    }


                }
            });

            return rowView;
        }
    }



}