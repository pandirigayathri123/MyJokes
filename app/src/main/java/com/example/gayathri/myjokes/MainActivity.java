package com.example.gayathri.myjokes;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    EditText edittext;
    Button button;
    String val;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edittext=findViewById(R.id.edtext);
        button=findViewById(R.id.btn);
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               val=edittext.getText().toString();
               Intent intent = new Intent(MainActivity.this, JokeActivity.class);
               intent.putExtra("jokes", val);
               startActivity(intent);

           }
       });

    }

}
