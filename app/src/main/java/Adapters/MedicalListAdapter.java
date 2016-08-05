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

import com.example.sneh.myapplication.Equipment;
import com.example.sneh.myapplication.R;
import com.example.sneh.myapplication.ShowEquipement;
import com.example.sneh.myapplication.ShowMedical;

/**
 * Created by sneh on 3/11/15.
 */
public class MedicalListAdapter extends BaseAdapter {

    String[] text1,text3,text5,text6;
    Context context;
    int[] medical_id;
    private static LayoutInflater inflater = null;

    public MedicalListAdapter(Context context,int[] medical_id, String[] text1, String[] text3, String[] text5,  String[] text6) {
        this.text1 = text1;
        this.text3 = text3;
        this.text5 = text5;
        this.text6 = text6;
        this.medical_id = medical_id;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return text1.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder {
        TextView text1;
        TextView text3;
        TextView text5;
        TextView text6;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        final View rowView;
        rowView = inflater.inflate(R.layout.each_medical, null);

        holder.text1 = (TextView) rowView.findViewById(R.id.text1);
        holder.text3 = (TextView) rowView.findViewById(R.id.text3);
        holder.text5 = (TextView) rowView.findViewById(R.id.text5);
        holder.text6 = (TextView) rowView.findViewById(R.id.text6);

        //Log.d("priceeee",text6[position]);

        holder.text1.setText(text1[position]);
        holder.text3.setText(text3[position]);
        holder.text5.setText(text5[position]);
        holder.text6.setText(text6[position]);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ShowMedical.class);
                i.putExtra("id",medical_id[position]);
                ((Activity)context).finish();
                context.startActivity(i);
            }
        });

        return rowView;
    }
}