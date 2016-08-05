package Adapters;

        import android.app.Activity;
        import android.content.Intent;
        import android.content.Context;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.TextView;
        import com.example.sneh.myapplication.R;
        import com.example.sneh.myapplication.ShowBrokenEgg;
        import com.example.sneh.myapplication.ShowDeath;
        import com.example.sneh.myapplication.ShowDownPayment;
        import com.example.sneh.myapplication.ShowNormalEgg;
        import com.example.sneh.myapplication.ShowEquipement;
        import com.example.sneh.myapplication.ShowExpense;
        import com.example.sneh.myapplication.ShowFood;
        import com.example.sneh.myapplication.ShowReturnedExpenses;
        import com.example.sneh.myapplication.ShowWorker;

public class EachListAdapter extends BaseAdapter {

    String[] text1,text2,text6,text4,text5;
    int j = 0;
    int[] id;
    Class[] contexts = {ShowEquipement.class, ShowWorker.class, ShowFood.class, ShowNormalEgg.class, ShowDeath.class, ShowExpense.class, ShowDownPayment.class, ShowReturnedExpenses.class, ShowBrokenEgg.class};
    Context context;
    private static LayoutInflater inflater=null;

    public EachListAdapter(Context context, int i, String[] text1, String[] text2, String[] text6, String[] text4, String[] text5,int[] id) {
        this.text1 = text1;
        this.text2 = text2;
        this.text6 = text6;
        this.text4 = text4;
        this.text5 = text5;
        this.j = i;
        this.id=id;
        this.context = context;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

    public class Holder
    {
        TextView text1;
        TextView text2;
        TextView text6;
        TextView text4;
        TextView text5;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        final View rowView;
        rowView = inflater.inflate(R.layout.each, null);

        holder.text1 = (TextView) rowView.findViewById(R.id.text1);
        holder.text2 = (TextView) rowView.findViewById(R.id.text2);
        holder.text6 = (TextView) rowView.findViewById(R.id.text6);
        holder.text4 = (TextView) rowView.findViewById(R.id.text4);
        holder.text5 = (TextView) rowView.findViewById(R.id.text5);

        holder.text1.setText(text1[position]);
        holder.text2.setText(text2[position]);
        holder.text6.setText(text6[position]);
        holder.text4.setText(text4[position]);
        holder.text5.setText(text5[position]);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, contexts[j]);
                i.putExtra("id",id[position]);
                Log.d("expense_idddddddddd",String.valueOf(id[position]));
                ((Activity)context).finish();
                context.startActivity(i);
            }
        });

        return rowView;
    }
}