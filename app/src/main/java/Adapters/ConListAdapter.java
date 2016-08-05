package Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sneh.myapplication.R;
import com.example.sneh.myapplication.ShowMedical;

/**
 * Created by sneh on 16/11/15.
 */
public class ConListAdapter extends BaseAdapter {

    String[] text2,text4,text5,text6,text7;
    Context context;
    //int[] medical_id;
    private static LayoutInflater inflater = null;

    public ConListAdapter(Context context, String[] text2, String[] text4, String[] text5,  String[] text6, String[] text7) {
        this.text2 = text2;
        this.text4 = text4;
        this.text5 = text5;
        this.text6 = text6;
        this.text7 = text7;
        //this.medical_id = medical_id;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return text2.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class Holder{
        TextView text2;
        TextView text4;
        TextView text5;
        TextView text6;
        TextView text7;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        final View rowView;
        rowView = inflater.inflate(R.layout.each_con, null);

        holder.text2 = (TextView) rowView.findViewById(R.id.text2);
        holder.text4 = (TextView) rowView.findViewById(R.id.text4);
        holder.text5 = (TextView) rowView.findViewById(R.id.text5);
        holder.text6 = (TextView) rowView.findViewById(R.id.text6);
        holder.text7 = (TextView) rowView.findViewById(R.id.text7);

        //Log.d("priceeee",text6[position]);

        holder.text2.setText(text2[position]);
        holder.text4.setText(text4[position]);
        holder.text5.setText(text5[position]);
        holder.text6.setText(text6[position]);
        holder.text7.setText(text7[position]);

        /*rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ShowMedical.class);
                i.putExtra("id",medical_id[position]);
                ((Activity)context).finish();
                context.startActivity(i);
            }
        });*/

        return rowView;
    }
}
