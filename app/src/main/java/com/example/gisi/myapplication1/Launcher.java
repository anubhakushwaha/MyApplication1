package com.example.gisi.myapplication1;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.widget.Toast;

/**
 * Created by GISI on 06-10-2015.
 */

public class Launcher extends Activity{
    static int r=0;
    public static final int DEFAULT=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher);


    }



    public void click(View v)
    {

        SharedPreferences i=getSharedPreferences("My Data2", Context.MODE_APPEND);
        r=i.getInt("KEY",DEFAULT);
        if(r==0)
        {Intent in=new Intent(this,MainActivity.class);
        startActivity(in);
        }
        else
        {
            Intent in=new Intent(this,Dish.class);
            startActivity(in);
        }
    }
}
