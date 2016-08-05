package com.example.sneh.myapplication;

/**
 * Created by Himanshu on 11/13/2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
//http://www.androidbegin.com/tutorial/android-display-images-from-sd-card-tutorial/
public class ViewImage extends AppCompatActivity {
    // Declare Variable
    TextView text;
    ImageView imageview;
    VideoView videoView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from view_image.xml
        setContentView(R.layout.view_image);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_view_photos_video);

        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Retrieve data from MainActivity on GridView item click
        Intent i = getIntent();

        // Get the position
        int position = i.getExtras().getInt("position");

        // Get String arrays FilePathStrings
        String[] filepath = i.getStringArrayExtra("filepath");

        // Get String arrays FileNameStrings
        String[] filename = i.getStringArrayExtra("filename");

        // Locate the TextView in view_image.xml
        text = (TextView) findViewById(R.id.imagetext);

        // Load the text into the TextView followed by the position

        videoView=(VideoView)findViewById(R.id.videoView);
        imageview = (ImageView) findViewById(R.id.full_image_view);
        if (filename[position].contains(".mp4")) {
            if(filename[position].contains("_.mp4"))
                text.setText(filename[position].substring(filename[position].indexOf("*")+1,filename[position].lastIndexOf("*")));


            imageview.setVisibility(View.INVISIBLE);

            videoView.setVisibility(View.VISIBLE);
            // MediaController mediaController = new MediaController(this);
            // mediaController.setAnchorView(videoView);
            Uri uri= Uri.parse(filepath[position]);
            //  videoView.setMediaController(mediaController);
            videoView.setVideoURI(uri);

            videoView.start();
        }
        else{
            if(filename[position].contains(".jpg"))
                text.setText(filename[position].substring(filename[position].indexOf("*")+1,filename[position].lastIndexOf("*")));

            // text.setText(filename[position].substring(0,filename[position].indexOf("_")));
            videoView.setVisibility(View.INVISIBLE);
            imageview.setVisibility(View.VISIBLE);

            // Locate the ImageView in view_image.xml


            // Decode the filepath with BitmapFactory followed by the position
            Bitmap bmp = BitmapFactory.decodeFile(filepath[position]);

            // Set the decoded bitmap into ImageView
            imageview.setImageBitmap(bmp);
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*Intent i2 = new Intent(ViewImage.this, TitleBuilding.class);
        i2.putExtra("cicle_id", cicle_id);
        i2.putExtra("building_id",building_id);*/
        finish();
        return true;
    }

}