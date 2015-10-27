package com.example.gisi.myapplication1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.content.Intent;
import android.content.Context;
import android.view.MenuItem;
import java.io.File;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import java.util.HashMap;
import android.widget.EditText;

import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {
EditText e1,e2,e3,e4,e5,e6,e7,e8;
    File f;
    String ss;
    public static final String DEFAULT="\n";
    public static final String UPLOAD_URL = "http://www.localhost/anu/upload.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        e3=(EditText)findViewById(R.id.editText3);
        e4=(EditText)findViewById(R.id.editText4);
        e5=(EditText)findViewById(R.id.editText5);
        e6=(EditText)findViewById(R.id.editText6);
        e7=(EditText)findViewById(R.id.editText8);
        e8=(EditText)findViewById(R.id.editText10);


    }
    public void proceed(View v) throws IOException
    {
        SharedPreferences i=getSharedPreferences("My Data",Context.MODE_APPEND);
        SharedPreferences.Editor editor=i.edit();
        editor.putString("Add", e1.getText().toString());
        editor.putString("Add2", e2.getText().toString());
        editor.putString("Add3",e3.getText().toString());
        editor.putString("PhoneNo",e4.getText().toString());
        editor.putString("EmailId",e5.getText().toString());
        editor.putString("OpeningHrs",e6.getText().toString());
        editor.putString("Events",e7.getText().toString());
        editor.putString("Password", e8.getText().toString());
        editor.commit();
        Toast.makeText(this,"DATA WAS SAVED SUCCESSFULLY",Toast.LENGTH_LONG).show();
        String selectImages = "";
        selectImages+= e1.getText().toString()+"|"+e2.getText().toString()+"|"+e3.getText().toString()+"|"+e4.getText().toString()+"|"+e5.getText().toString()+"|"+e6.getText().toString()+"|"+e7.getText().toString()+"|"+e8.getText().toString();
        FileOutputStream fos = openFileOutput("anubha.txt", Context.MODE_APPEND);
        byte[] bn = selectImages.getBytes();
        fos.write(bn);
        fos.close();
        SharedPreferences in = getSharedPreferences("My Data2", Context.MODE_APPEND);
        SharedPreferences.Editor editor1=in.edit();
        editor1.putInt("KEY", 1);
        editor1.commit();
    }

    public void display(View v)
    {
        SharedPreferences i=getSharedPreferences("My Data",Context.MODE_APPEND);
        Toast.makeText(this,i.getString("Add",DEFAULT),Toast.LENGTH_LONG).show();
        Toast.makeText(this,i.getString("Add2",DEFAULT),Toast.LENGTH_LONG).show();
        Toast.makeText(this,i.getString("Add3",DEFAULT),Toast.LENGTH_LONG).show();
        Toast.makeText(this,i.getString("PhoneNo",DEFAULT),Toast.LENGTH_LONG).show();
        Toast.makeText(this,i.getString("EmailId",DEFAULT),Toast.LENGTH_LONG).show();
        Toast.makeText(this,i.getString("OpeningHrs",DEFAULT),Toast.LENGTH_LONG).show();
        Toast.makeText(this,i.getString("Events",DEFAULT),Toast.LENGTH_LONG).show();
        Toast.makeText(this,i.getString("Password",DEFAULT),Toast.LENGTH_LONG).show();

    }
    public void submit(View v) throws IOException
    {
        class UploadImage extends AsyncTask<String,Void,String> {

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Uploading ", "Please wait...", true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... arg0) {
                String fpath = arg0[0];
                Toast.makeText(getApplicationContext(),"<<<<<<"+fpath+">>>>>",Toast.LENGTH_LONG).show();
                // TODO Auto-generated method stub
                HashMap<String, String> data = new HashMap<>();
                data.put("file",fpath );

                String result = rh.sendPostRequest(UPLOAD_URL, data);
                Toast.makeText(getApplicationContext(),"STOREDDDDDDD"+fpath,Toast.LENGTH_LONG).show();
                return result;
            }
        }

        UploadImage ui = new UploadImage();

        File g=open();
        try {
            FileInputStream fin = new FileInputStream(g);
            byte[] buffer = new byte[(int) g.length()];
            new DataInputStream(fin).readFully(buffer);
            fin.close();
            ss = new String(buffer, "UTF-8");

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ui.execute(ss);
        Toast.makeText(this,ss+">>>>>",Toast.LENGTH_LONG).show();
    }
    public File open() throws IOException
    {
        File file = new File(getFilesDir() + "/" + "anubha.txt");
        return (file);
    }
    public void next(View v)
    {

        Intent i=new Intent(this,Dish.class);
        startActivity(i);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
