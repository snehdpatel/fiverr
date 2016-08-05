package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sneh.myapplication.R;

/**
 * Created by sneh on 13/11/15.
 */
public class TempAdapter extends BaseAdapter {

    String[] temp,lux,humidity,date;
    Context context;
    private static LayoutInflater inflater=null;

    public TempAdapter(Context context,String []temp,String []lux,String []humidity,String []date){
        this.temp = temp;
        this.lux = lux;
        this.humidity = humidity;
        this.date = date;
        this.context = context;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return temp.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder{
        TextView temp;
        TextView lux;
        TextView humidity;
        TextView date;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        final View rowView;
        rowView = inflater.inflate(R.layout.each_temp, null);

        holder.temp = (TextView) rowView.findViewById(R.id.temp);
        holder.lux = (TextView) rowView.findViewById(R.id.lux);
        holder.humidity = (TextView) rowView.findViewById(R.id.humidity);
        holder.date = (TextView) rowView.findViewById(R.id.date);

        holder.temp.setText(temp[position]+"C");
        holder.lux.setText(lux[position]+"%");
        holder.humidity.setText(humidity[position]+"Lux");
        holder.date.setText(date[position]);

        return rowView;
    }
}
