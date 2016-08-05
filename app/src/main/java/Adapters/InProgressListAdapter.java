package Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.sneh.myapplication.Building;
import com.example.sneh.myapplication.R;
import com.example.sneh.myapplication.cicle;
import com.example.sneh.myapplication.image_assoc;

import java.util.List;


public class InProgressListAdapter extends BaseAdapter {

    String [] title,owner,type;
    Context context;
    private static LayoutInflater inflater=null;
    private List<cicle> cicle_list;

    public InProgressListAdapter(Context context, List<cicle> cicle_list) {
        this.cicle_list=cicle_list;
        this.context = context;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return cicle_list.size();
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
        ImageView image;
        TextView title;
        TextView owner;
        TextView type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        final View rowView;
        //callig image_assoc
        image_assoc assoc=new image_assoc();
        rowView = inflater.inflate(R.layout.circles, null);
        final cicle cicle=cicle_list.get(position);
        int image_id=assoc.get_image_id(cicle.getType());
        Log.d("in_progress_img_id",String.valueOf(image_id));
        holder.title = (TextView) rowView.findViewById(R.id.title);
        holder.owner = (TextView) rowView.findViewById(R.id.owner);
        holder.type = (TextView) rowView.findViewById(R.id.type);
        holder.image=(ImageView)rowView.findViewById(R.id.cicle_imageView);
        holder.image.setImageResource(image_id);
        holder.title.setText(cicle.getTitle());
        holder.owner.setText(cicle.getOwner());

        holder.type.setText(cicle.getType());
        final int pos=position;

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Building.class);
                Log.d("posssssss",String.valueOf(cicle.getCicle_id()));
                intent.putExtra("position",cicle.getCicle_id());
                ((Activity)context).finish();
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
