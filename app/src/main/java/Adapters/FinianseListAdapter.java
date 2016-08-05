package Adapters;

/**
 * Created by sneh on 6/11/15.
 */
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sneh.myapplication.R;

/**
 * Created by Himanshu on 11/4/2015.
 */
/*
class Singlerow{
    String  heading;
    String  money;
    String  Curr;
    Singlerow(String heading,String money,String Curr)
    {
        this.Curr=Curr;
        this.heading=heading;
        this.money=money;
    }

}*/
public class FinianseListAdapter extends BaseAdapter {
    Context c;
    String[]  _money;
    String[]  _Curr;
    String[] _heading;
    public FinianseListAdapter(Context c,String[] heading,String[] money,String[] currency)
    {   this.c=c;

        this._money=money;
        this._Curr=currency;
        this._heading=heading;
    }

    @Override
    public int getCount() {
        return _money.length;
    }

    @Override
    public Object getItem(int position) {
        return _money[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder{

        TextView heading;
        TextView money;
        TextView currency;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder=new ViewHolder();


        LayoutInflater inflater=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(R.layout.finance_listview, parent, false);
        holder.heading=(TextView)row.findViewById(R.id.finance_listview_heading);
        holder.money=(TextView)row.findViewById(R.id.finance_listview_money);
        holder.currency=(TextView)row.findViewById(R.id.finance_listview_currency);
        Log.d("money",_money[position]);
        holder.money.setText(_money[position]);
        holder.heading.setText(_heading[position]);
        holder.currency.setText(_Curr[position]);
        return row;
    }
}