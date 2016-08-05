package Tabs;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.sneh.myapplication.DatePickerFragment;
import com.example.sneh.myapplication.R;
import com.example.sneh.myapplication.TodaysTask;
import com.example.sneh.myapplication.db_handler;
import com.example.sneh.myapplication.task_class;
import com.example.sneh.myapplication.title_;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Adapters.InProgressListAdapter;
import Adapters.TodaysTaskAdapter;


/**
 * Created by sneh on 5/11/15.
 */public class TodaysDone extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    List<task_class> title_list;
    int title_id;
    String[] button;
    TextView count;
    TodaysTasks tt;
    ListView lv;
    int[] t_id;
    db_handler handler;
    String date;
    int user_id;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView date_textview;
    private ImageButton date_imageButton;
    StringBuilder date_stringbuilder;
    private String true_string;
    private String start_date;

    public TodaysTaskListAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(TodaysTaskListAdapter adapter) {
        this.adapter = adapter;
    }

    private TodaysTaskListAdapter adapter;
    private Context con;

    public void getContext(Context context) {
        con=context;

        //Toast.makeText(con, "jobjone_cicle_list_size:"+String.valueOf(cicle_list.size()), Toast.LENGTH_LONG).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_view_todays_task_done, container, false);
        tt=new TodaysTasks();
        handler = new db_handler(getContext());
        handler.onCreateTable(handler.getWritableDatabase());
        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh_layout);
        count=(TextView)view.findViewById(R.id.count_today_task_done);
        Intent intent = getActivity().getIntent();
        title_id = intent.getIntExtra("title id", -1);
        Log.d("title_id", String.valueOf(title_id));
        lv = (ListView) view.findViewById(R.id.listView_today_task_done);
        date_textview=(TextView)view.findViewById(R.id.add_done_date);
        date_imageButton=(ImageButton)view.findViewById(R.id.start_date_button);
        TodaysTaskListAdapter refresh = new TodaysTaskListAdapter();
        Date date_obj= Calendar.getInstance().getTime();
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        date=sdf.format(date_obj);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf1.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        start_date = sdf.format(c.getTime());
        // start_date=String.valueOf(calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH));
        showDate(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH));
        Log.d("start_date",start_date);
        //getting date
        date_imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(); //date set
            }
        });

        // Date date_obj= Calendar.getInstance().getTime();
        // dt is now the new date
        Log.d("date_",date);
        //getting user_id
        SharedPreferences preferences=con.getSharedPreferences("user_data",Context.MODE_PRIVATE);
        user_id=preferences.getInt("user_id",-1);
        Log.d("todays_done_user_id",String.valueOf(user_id));

        swipeRefreshLayout.setOnRefreshListener(this);
        title_list=handler.get_all__done_task(user_id,true_string);
        Log.d("true_string",true_string);

        count.setText("Task ("+String.valueOf(title_list.size())+")");
        Log.d("Listdone:",String.valueOf(title_list.size()));
        // title_list = handler.get_all_done_title();
        // button = new String[title_list.size()];
        // t_id = new int[title_list.size()];
        /// for(int i=0; i< title_list.size(); i++){
        //  button[i] = String.valueOf(title_list.get(i).getTitle());
        //  t_id[i] = title_list.get(i).getTitle_id();
        // }


        adapter=new TodaysTaskListAdapter(getContext(),title_list);
        lv.setAdapter(adapter);

    /* if(refresh.refresh == 1){
      Toast.makeText(getContext(), "fuck??????", Toast.LENGTH_LONG).show();
      handler = new db_handler(getContext());
      handler.onCreateTable(handler.getWritableDatabase());
      title_list = handler.get_all_not_done_title();
      button = new String[title_list.size()];
      for(int i=0; i< title_list.size(); i++){
        button[i]=String.valueOf(title_list.get(i).getTitle());
      }
*/
    /*lv = (ListView) view.findViewById(R.id.listView);
      lv.setAdapter(new TodaysTaskListAdapter(getContext(), t));
*/


        return view;
    }
    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        // showDate(calender.get(Calendar.YEAR),calender.get(Calendar.MONTH),calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondate);
        date.show(getFragmentManager(), "Date Picker");

    }
    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            showDate(year,monthOfYear+1,dayOfMonth);
        }
    };
    private void showDate(int year,int month,int day){
        date_stringbuilder=new StringBuilder().append(day).append("-").append(month).append("-").append(year);
        date_textview.setText(date_stringbuilder.toString());
        Log.d("date_textview:", date_stringbuilder.toString());
        if(day<10)
            true_string = String.valueOf(year) + "-" + String.valueOf(month) + "-" +"0"+String.valueOf(day);
        else
            true_string = String.valueOf(year) + "-" + String.valueOf(month) + "-"+String.valueOf(day);

    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        title_list = handler.get_all__done_task(user_id,true_string);
        Log.d("true_string_refresh:",true_string);
        Log.d("Listtd:",String.valueOf(title_list.size()));
        count.setText("Task (" + String.valueOf(title_list.size()) + ")");
        adapter=new TodaysTaskListAdapter(getContext(), title_list);

        lv.setAdapter(adapter);

        swipeRefreshLayout.setRefreshing(false);

    }
/*
  public void do_refresh(){
    handler = new db_handler(getContext());
    handler.onCreateTable(handler.getWritableDatabase());
    Toast.makeText(getContext(), "fuck??????", Toast.LENGTH_LONG).show();
    title_list = handler.get_all_not_done_title();
    handler.
    button = new String[title_list.size()];
    for(int i=0; i< title_list.size(); i++){
      button[i]=String.valueOf(title_list.get(i).getTitle());
    }

    lv = (ListView) lv.findViewById(R.id.listView);
    TodaysTaskListAdapter tt=new TodaysTaskListAdapter(getContext(), button, 1, t_id);
    lv.setAdapter((ListAdapter) tt);

  }*/


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

        public TodaysTaskListAdapter(){}

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
                    Log.d("t.getDate",t.getDate());
                    if (start_date.equals(t.getDate())) {
                        t.setDone(0);
                        handler.updateTask(t);
                        // context.startActivity(new Intent(context,context.getClass()));
                        title_list.remove(position);
                        adapter.notifyDataSetChanged();
                    }
//          tt.getAdapter().notifyDataSetChanged();

                    // Toast.makeText(context, position, Toast.LENGTH_LONG).show();
                }
            });

            return rowView;
        }
    }
  /*public void onk()
  {
    handler = new db_handler(getContext());
    handler.onCreateTable(handler.getWritableDatabase());
    title_list=new ArrayList<title_>();
    title_list = handler.get_all_done_title();
    adapter=new TodaysTaskListAdapter(getContext(), title_list);
    lv.setAdapter(adapter);
    Log.d("ONRESUME:",String.valueOf(title_list.size()));
  }*/


}