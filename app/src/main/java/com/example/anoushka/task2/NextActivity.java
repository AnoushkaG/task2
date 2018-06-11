package com.example.anoushka.task2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NextActivity extends AppCompatActivity {
    public String JsonString;
   // public LinearLayout item;

    JSONObject jsonObject;
    JSONArray jsonArray;
 //   ContactAdapter contactAdapter;
    public String jstring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        new BackgroundTask().execute();
        if(JsonString == null)
        {

            String json_string;

        }
    }
    class BackgroundTask extends AsyncTask<Void,Void,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url=new URL(" https://api.androidhive.info/contacts/");
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder=new StringBuilder();
                while ((JsonString=bufferedReader.readLine())!=null) {
                    stringBuilder.append(JsonString).append("\n");

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                jstring = stringBuilder.toString().trim();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return jstring;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            TextView textinfo=findViewById(R.id.textView);
            textinfo.setText(s);
        }
    }
}
