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

import com.example.sneh.myapplication.Equipment;
import com.example.sneh.myapplication.R;
import com.example.sneh.myapplication.ShowBuilding;
import com.example.sneh.myapplication.ShowEquipement;
import com.example.sneh.myapplication.TitleBuilding;
import com.example.sneh.myapplication.building_class;
import com.example.sneh.myapplication.image_assoc;

import java.util.List;

/**
 * Created by sneh on 4/11/15.
 */
public class BuildListAdapter extends BaseAdapter {

    List<building_class> building_list;
    Context context;
    private static LayoutInflater inflater=null;

    public BuildListAdapter(Context context,List<building_class> building_list) {
        //this.designation = designation;
        this.building_list=building_list;
        this.context = context;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return building_list.size();
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
        //TextView designation;
        /*TextView worth;
        TextView type;*/
        ImageView image;
        TextView text2;
        TextView text3;
        TextView text5;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        final View rowView;
        rowView = inflater.inflate(R.layout.each_build, null);

        holder.text2 = (TextView) rowView.findViewById(R.id.each_build_title);
        holder.text3 = (TextView) rowView.findViewById(R.id.each_build_type);
        holder.text5 = (TextView) rowView.findViewById(R.id.each_build_building);
        holder.image=(ImageView)rowView.findViewById(R.id.building_imageView);
        final building_class building=building_list.get(position);
        image_assoc assoc=new image_assoc();
        int image_id=assoc.get_building_image_id(building.getType());
        holder.text2.setText(building.getTitle());
        holder.text3.setText(building.getType()+"  "+building.getOther());
        holder.text5.setText(String.valueOf(building.getCapacity()));
        holder.image.setImageResource(image_id);
        Log.d("hellboy",building.getOther());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, TitleBuilding.class);
                i.putExtra("cicle_id",building.getCicle_id());
                i.putExtra("building_id",building.getBuilding_id());
                ((Activity)context).finish();
                context.startActivity(i);
            }
        });

        return rowView;
    }
}
