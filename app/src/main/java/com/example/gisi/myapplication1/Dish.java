package com.example.gisi.myapplication1;

import android.app.Activity;
import android.content.Intent;
import java.io.File;
import java.io.IOException;
import android.content.Context;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.Menu;
import java.io.FileOutputStream;
import android.view.View;
import android.provider.MediaStore;
import android.content.Intent;
import android.widget.Toast;
import android.net.Uri;
import java.util.ArrayList;
import android.os.Parcelable;
import android.database.Cursor;
import android.widget.Button;
import android.widget.ImageView;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.view.MenuItem;
import android.widget.EditText;
import android.os.Bundle;

/**
 * Created by GISI on 07-10-2015.
 */
public class Dish extends Activity {
    EditText e1, e2;
    File f;
    Intent data1;
    private final int PICK_IMAGE_MULTIPLE =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dish);
        e1 = (EditText)findViewById(R.id.editText7);
        e2 = (EditText)findViewById(R.id.editText9);
    }

    public void pick(View v) {
        Intent intent = new Intent(this, CustomGallery.class);
        startActivityForResult(intent, PICK_IMAGE_MULTIPLE);
    }
    public void submit(View v)  throws IOException
    {
        f = getFilesDir();

        FileOutputStream fos = openFileOutput("viz.txt", Context.MODE_APPEND);
        byte[] bn = data1.getStringExtra("data").getBytes();

        fos.write(bn);

        SharedPreferences i=getSharedPreferences("My Data", Context.MODE_APPEND);
        SharedPreferences.Editor editor=i.edit();
        editor.putString("DishName",e1.getText().toString());
        editor.putString("DishPrice",e2.getText().toString());
        editor.putString("FileName",f.getName() );
        editor.commit();
        Toast.makeText(this, "File Saved Successfully" + "   " + f, Toast.LENGTH_LONG).show();
        fos.close();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE_MULTIPLE) {
                data1=data;


            }
        }
    }
}
