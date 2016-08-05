package com.example.sneh.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends AppCompatActivity {
    static String user_description;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private Uri fileUri;
    // Declare variables
    private String[] FilePathStrings;
    private String[] FileNameStrings;
    private File[] listFile;
    GridView grid;
    GridViewAdapter adapter;
    File file;
    FloatingActionButton fab;
    private static String IMAGE_DIRECTORY_NAME = "THIS_IS_APP";
    private int cicle_id;
    private int building_id;
    private FloatingActionButton video;
    db_handler handler;
    private int cicle_sql;
    private int building_sql;
    private int user_id;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview_main);


        SharedPreferences preferences=getSharedPreferences("app_data", Context.MODE_PRIVATE);
        cicle_id=preferences.getInt("cicle_id", -1);
        building_id=preferences.getInt("building_id", -1);
        cicle_sql=preferences.getInt("cicle_sql", -1);
        building_sql = preferences.getInt("building_sql", -1);

        SharedPreferences preferences1=getSharedPreferences("user_data", Context.MODE_PRIVATE);
        user_id=preferences1.getInt("user_id", -1);

        Intent intent=getIntent();
        user_description=intent.getStringExtra("user_desp");
        if(user_description!=null) {
            Log.d("user_desp", user_description);
        }
        if(cicle_id!=-1&&building_id!=-1)
            IMAGE_DIRECTORY_NAME = "CICLEAPP";

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_photos);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        handler = new db_handler(getApplicationContext());
        final cicle cicle=handler.getCicle(cicle_id);
        final building_class building=handler.getBuilding(building_id);
        final File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), IMAGE_DIRECTORY_NAME);


        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");

            }
        }
  /*   // Check for SD Card
    if (!Environment.getExternalStorageState().equals(
        Environment.MEDIA_MOUNTED)) {
      Toast.makeText(this, "Error! No SDCARD Found!", Toast.LENGTH_LONG)
          .show();
    } else {
      // Locate the image folder in your SD Card
      file = new File(Environment.getExternalStorageDirectory()
          + File.separator + "SDImageTutorial");
      // Create a new folder if no folder named SDImageTutorial exist
      file.mkdirs();
    }
*/
        if (file.isDirectory()) {
            listFile = file.listFiles();
            List<image_class> image_list= handler.get_all_image(cicle_id,building_id);

            // Create a String array for FilePathStrings
            for (int i = 0; i < image_list.size(); i++) {
                // image_class image=new image_class();
                image_class image = new image_class();
                image = image_list.get(i);
                File file1 = new File(image.getPath());

                Log.d("image_" + String.valueOf(image_list.get(i).getImage_id()), String.valueOf(image_list.get(i).getSql_image_id()));
                Log.d("image_" + String.valueOf(image_list.get(i).getImage_id()), String.valueOf(image_list.get(i).getFlag()));
                Log.d("cicle_sql_image_" + String.valueOf(image_list.get(i).getImage_id()), String.valueOf(image_list.get(i).getSql_cicle_id()));
                Log.d("build_sql_image_" + String.valueOf(image_list.get(i).getImage_id()), String.valueOf(image_list.get(i).getSql_building_id()));


                if (!file1.exists()) {
                    if (image.getSql_image_id() != 0) {
                        handler.deleteImage(image);
                        image_list.remove(image);
                    } else {
                        handler.deleteImage(image);
                        file1.delete();
                        image_list.remove(image);
                    }
                }

            }
            FilePathStrings = new String[image_list.size()];
            // Create a String array for FileNameStrings
            FileNameStrings = new String[image_list.size()];
            for(int j=0;j<image_list.size();j++)
            {
                FileNameStrings[j]=image_list.get(j).getName();
                FilePathStrings[j]=image_list.get(j).getPath();
                Log.d("photo_path" ,FilePathStrings[j]);
                Log.d("photo_name" ,FileNameStrings[j]);
            }
        }
        fab=(FloatingActionButton)findViewById(R.id.photo_button);
        video=(FloatingActionButton)findViewById(R.id.video_button);
        // Locate the GridView in gridview_main.xml
        grid = (GridView) findViewById(R.id.gridview);
        // Pass String arrays to LazyAdapter Class
        adapter = new GridViewAdapter(this, handler.get_all_image(cicle_id,building_id));
        // Set the LazyAdapter to the GridView
        grid.setAdapter(adapter);

        // Capture gridview item click
        grid.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent i = new Intent(MainActivity.this, ViewImage.class);

                // Pass String arrays FilePathStrings
                i.putExtra("filepath", FilePathStrings);
                // Pass String arrays FileNameStrings
                i.putExtra("filename", FileNameStrings);
                // Pass click position
                i.putExtra("position", position);

                startActivity(i);
            }

        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
                final View promptView = layoutInflater.inflate(R.layout.prompts, null);
                final EditText editText = (EditText) promptView.findViewById(R.id.editTextDialogUserInput);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setView(promptView);
                alertDialogBuilder.setTitle(getResources().getString(R.string.enter_the_description));
                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton(getResources().getString(R.string.hint_save), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (editText.getText().toString().isEmpty()) {
                                    dialog.cancel();
                                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.fill_the_description), Toast.LENGTH_SHORT).show();

                                } else {
                                    // Log.d("user_desp",user_description);
                                    int j = -1;
                                    if (file.exists()) {
                                        j = file.listFiles().length;
                                    }
                                    user_description = editText.getText().toString().replace(" ", "");
                                    user_description = cicle.getTitle() + "_" + building.getTitle() + "*" + user_description;
                                    user_description = user_description.replace(" ", "");
                                    String s = file.getPath() + File.separator + user_description + "*" + j + "_.jpg";
                                    // s.replace(" ","_");
                                    String s1 = user_description + "*" + j + "_.jpg";
                                    Log.d("hellyoo:", s);
                                    image_class image = new image_class();
                                    image.setSql_image_id(0);
                                    image.setBuilding_id(building_id);
                                    image.setCicle_id(cicle_id);
                                    image.setSql_building_id(building_sql);
                                    image.setSql_cicle_id(cicle_sql);
                                    image.setUser_id(user_id);
                                    image.setName(s1);
                                    image.setPath(s);
                                    image.setFlag(0);
                                    image.setType(1);
                                    Log.d("above_image", "above_image_reation");

                                    Log.d("Path_image", s);
                                    handler.createImage(image);
                                    captureImage();

                                }


                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                final AlertDialog alert = alertDialogBuilder.create();
                alert.show();

            }
        });
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
                final View promptView = layoutInflater.inflate(R.layout.prompts, null);
                final EditText editText = (EditText) promptView.findViewById(R.id.editTextDialogUserInput);
                final TextView textView=(TextView)promptView.findViewById(R.id.prompts_title);
                textView.setText("Type Video Description ");
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        MainActivity.this);
                alertDialogBuilder.setView(promptView);
                alertDialogBuilder.setTitle("Enter Description");
                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("SAVE ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(editText.getText().toString().isEmpty()){
                                    dialog.cancel();
                                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.fill_the_description),Toast.LENGTH_SHORT).show();

                                }
                                else {
                                    int j = -1;
                                    if (file.exists()) {
                                        j = file.listFiles().length;
                                    }
                                    user_description = editText.getText().toString().replace(" ","");
                                    user_description=cicle.getTitle()+"_"+building.getTitle()+"*"+user_description;
                                    user_description=user_description.replace(" ","");

                                    String s = file.getPath() + File.separator + user_description + "*" + j + "_.mp4";
                                    String s1= user_description+"*" + j + "_.mp4";;

                                    image_class image = new image_class();
                                    image.setSql_image_id(0);
                                    image.setBuilding_id(building_id);
                                    image.setCicle_id(cicle_id);
                                    image.setSql_building_id(building_sql);
                                    image.setSql_cicle_id(cicle_sql);
                                    image.setUser_id(user_id);

                                    image.setName(s1);
                                    image.setPath(s);
                                    image.setFlag(0);
                                    image.setType(2);
                                    Log.d("above_image", "above_video_reation");
                                    handler.createImage(image);
                                    Log.d("Path_video", s);
                                    recordVideo();
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                final AlertDialog alert=alertDialogBuilder.create();
                alert.show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i2 = new Intent(MainActivity.this, TitleBuilding.class);
        i2.putExtra("cicle_id", cicle_id);
        i2.putExtra("building_id",building_id);
        finish();
        startActivity(i2);
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent i2 = new Intent(MainActivity.this, TitleBuilding.class);
        i2.putExtra("cicle_id", cicle_id);
        i2.putExtra("building_id",building_id);
        finish();
        startActivity(i2);
    }

    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    /**
     * Capturing Camera Image will lauch camera app requrest image capture
     */
    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);


        Log.d("in_captureimage","finally here");
    }

    /**
     * Here we store the file url as it will be null after returning from camera
     * app
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on scren orientation
        // changes
        outState.putParcelable("file_uri", fileUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
    }

    /**
     * Recording video
     */
    private void recordVideo() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);

        // set video quality
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file
        // name

        // start the video capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_VIDEO_REQUEST_CODE);
    }

    /**
     * Receiving activity result method will be called after closing the camera
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // successfully captured the image
                // display it in image view
                Intent intent=new Intent(MainActivity.this, MainActivity.class);
                intent.putExtra("cicle_id",cicle_id);
                intent.putExtra("building_id", cicle_id);
                intent.putExtra("user_desp",user_description);
                startActivity(intent);
                finish();

            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        getResources().getString(R.string.cancel_image), Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(),
                        getResources().getString(R.string.failed_image), Toast.LENGTH_SHORT)
                        .show();
            }
        } else if (requestCode == CAMERA_CAPTURE_VIDEO_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // video successfully recorded
                // preview the recorded video
                Intent intent=new Intent(MainActivity.this, MainActivity.class);
                intent.putExtra("cicle_id",cicle_id);
                intent.putExtra("building_id", cicle_id);
                intent.putExtra("user_desp",user_description);
                startActivity(intent);
                finish();

            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled recording
                Toast.makeText(getApplicationContext(),
                        getResources().getString(R.string.cancel_video), Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to record video
                Toast.makeText(getApplicationContext(),
                        getResources().getString(R.string.failed_video), Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    /**
     * Display image from a path to ImageView
     */


    /**
     * Previewing recorded video
     */


    /**
     * ------------ Helper Methods ----------------------
     * */

    /**
     * Creating file uri to store image/video
     */
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /**
     * returning image / video
     */
    private static File getOutputMediaFile(int type) {
        int j=-1;

        // External sdcard location
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        if(mediaStorageDir.isDirectory())
        {
            j=mediaStorageDir.listFiles().length;
        }
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create " + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE)
            mediaFile=new File(mediaStorageDir.getPath()+File.separator+user_description+"*"+j+"_.jpg");
        else
        if(type==MEDIA_TYPE_VIDEO)
            mediaFile=new File(mediaStorageDir.getPath()+File.separator+user_description+"*"+j+"_.mp4");



//      if(user_description=="")
//      {
//        mediaFile = new File(mediaStorageDir.getPath() + File.separator
//            + "IMG_" + timeStamp + ".jpg");}
//      else{
//        mediaFile = new File(mediaStorageDir.getPath() + File.separator
//            + user_description + "_"+j+"_.jpg");}
//      Log.d("Checking_path",mediaFile.getParent());
//
//    } else if (type == MEDIA_TYPE_VIDEO) {
//      if(user_description=="")
//      {
//        mediaFile = new File(mediaStorageDir.getPath() + File.separator
//            + "VID_" + timeStamp + ".mp4");}
//      else{
//        mediaFile = new File(mediaStorageDir.getPath() + File.separator
//            + user_description + "_"+j+"_.mp4");
//      }
//    }
        else {
            return null;
        }

        return mediaFile;
    }



}