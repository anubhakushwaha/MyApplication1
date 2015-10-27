package com.example.gisi.myapplication1;

/**
 * Created by GISI on 10-10-2015.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

public class UploadingData extends AsyncTask<String,Void,String> {
    public UploadingData()
    {}
    protected void onPreExecute(){

    }


    protected String doInBackground(String... arg0) {


            try {
               // Toast.makeText(this,"ConnectionEstablished",Toast.LENGTH_LONG).show();
                String password = (String) arg0[0];
                String address = (String) arg0[1] + " " + (String) arg0[2] + " " + (String) arg0[3];
                String phoneNo = (String) arg0[4];
                String emailId = (String) arg0[5];
                String openingHrs = (String) arg0[6];
                String events = (String) arg0[7];
                String link = "http://localhost/anu/anubhaaa.php";
                String data = URLEncoder.encode("Password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                data += "&" + URLEncoder.encode("Address", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8");
                data += "&" + URLEncoder.encode("PhoneNo", "UTF-8") + "=" + URLEncoder.encode(phoneNo, "UTF-8");
                data += "&" + URLEncoder.encode("EmailId", "UTF-8") + "=" + URLEncoder.encode(emailId, "UTF-8");
                data += "&" + URLEncoder.encode("OpeningHrs", "UTF-8") + "=" + URLEncoder.encode(openingHrs, "UTF-8");
                data += "&" + URLEncoder.encode("Events", "UTF-8") + "=" + URLEncoder.encode(events, "UTF-8");
                URL url = new URL(link);
                URLConnection conn = url.openConnection();
/*BASICALLY HERE I WANTED TO CHECK KI DATA JAA BHI RHA TO MY LOCAL DATABASE SO INSTEAD OF SEPERATE IDENTITIES I HAVE CONCANTED ALL INFO TOGETHER AND WAHAAN
LOCAL DATABASE PAE DTABASE MAE DO COLUMNS HAI ID KI AUR BLOB TYPE KI FILE NAAM SAE WHERE I M TRYING TO STORE THIS CONCANTED DATA
 */
                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write(data);
                wr.flush();

              BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null)
                {
                    sb.append(line);
                    break;
                }

                return sb.toString();

            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }



    protected void onPostExecute(){

    }
}


