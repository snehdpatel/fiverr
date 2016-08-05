package com.example.sneh.myapplication;

/**
 * Created by Himanshu on 11/13/2015.
 */
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {

    // Declare variables
    private Activity activity;
    private String[] filepath;
    private String[] filename;

    private static LayoutInflater inflater = null;

    public GridViewAdapter(Activity a,List<image_class> image) {
        activity = a;
        filename=new String[image.size()];
        filepath=new String[image.size()];
        for(int i=0;i<image.size();i++)
        {
            filepath[i]=image.get(i).getPath();
            filename[i]=image.get(i).getName();
        }

        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public int getCount() {
        return filepath.length;

    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.gridview_item, null);
        // Locate the TextView in gridview_item.xml
        TextView text = (TextView) vi.findViewById(R.id.text);
        // Locate the ImageView in gridview_item.xml
        ImageView image = (ImageView) vi.findViewById(R.id.image);

        // Set file name to the TextView followed by the position

        Bitmap bmp;
        if(filename[position].contains(".mp4"))
        {if(filename[position].contains("_.mp4"))
            text.setText(filename[position].substring(filename[position].indexOf("*")+1,filename[position].lastIndexOf("*")));


            bmp= ThumbnailUtils.createVideoThumbnail(filepath[position],0);
        }
        else{
            // Decode the filepath with BitmapFactory followed by the position
            if(filename[position].contains("_.jpg"))
                text.setText(filename[position].substring(filename[position].indexOf("*")+1,filename[position].lastIndexOf("*")));
            Log.d("lenght_size:", String.valueOf(filename[position].indexOf("*"))+" "+filename[position].lastIndexOf("*")+" "+filename[position].substring(filename[position].indexOf("*"),filename[position].lastIndexOf("*")));
            bmp = decodeSampledBitmapFromUri(filepath[position],100,100);}

        // Set the decoded bitmap into ImageView
        image.setImageBitmap(bmp);
        return vi;
    }

    public Bitmap decodeSampledBitmapFromUri(String path, int reqWidth, int reqHeight) {

        Bitmap bm = null;
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(path, options);

        return bm;

    }

    public int calculateInSampleSize(

            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }

        return inSampleSize;
    }
}