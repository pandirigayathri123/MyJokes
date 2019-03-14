package com.example.gayathri.myjokes;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

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

public class JokeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>
{
    RecyclerView recyclerView;
    public static final int Loader_Id=1;
    ArrayList<JokesModel> jokesModels;
    String jokeuri = "http://api.icndb.com/jokes/random/";
    String str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        recyclerView = findViewById(R.id.recycler);
        Intent intent = getIntent();
        str = intent.getStringExtra("jokes");
        jokesModels = new ArrayList<>();

        getSupportLoaderManager().initLoader(Loader_Id, null, this);

    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {

        return new AsyncTaskLoader<String>(this) {
            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                forceLoad();
            }

            @Nullable
            @Override
            public String loadInBackground() {

                try {
                    URL url = new URL(jokeuri + str);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.connect();
                    InputStream inputStream = urlConnection.getInputStream();
                    Scanner scanner = new Scanner(inputStream);
                    scanner.useDelimiter("\\A");
                    if (scanner.hasNext()) {
                        return scanner.next();
                    }
                    else{
                        return null;
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;

            }
        };
    }


    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        if (s != null) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                Log.i("str",jsonObject.toString());
                //Toast.makeText(JokeActivity.this, "" + s, Toast.LENGTH_SHORT).show();
                //String type = jsonObject.getString("type");
                JSONArray jsonArray = jsonObject.getJSONArray("value");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    //String id = obj.getString("id");
                    String joke = obj.getString("joke");
                    //JSONArray jsonArray1 = obj.getJSONArray("categories");
                    jokesModels.add(new JokesModel(joke));
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(JokeActivity.this));
                recyclerView.setAdapter(new JokesAdapter(JokeActivity.this, jokesModels));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }


   /* public class Jokes extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(jokeuri + str);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                Scanner scanner = new Scanner(inputStream);
                scanner.useDelimiter("\\A");
                if (scanner.hasNext()) {
                    return scanner.next();
                }
                else{
                    return null;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    Log.i("str",jsonObject.toString());
                   //Toast.makeText(JokeActivity.this, "" + s, Toast.LENGTH_SHORT).show();
                    //String type = jsonObject.getString("type");
                    JSONArray jsonArray = jsonObject.getJSONArray("value");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        //String id = obj.getString("id");
                        String joke = obj.getString("joke");
                        //JSONArray jsonArray1 = obj.getJSONArray("categories");
                        jokesModels.add(new JokesModel(joke));
                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(JokeActivity.this));
                    recyclerView.setAdapter(new JokesAdapter(JokeActivity.this, jokesModels));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }*/

        //}
    //}
}
