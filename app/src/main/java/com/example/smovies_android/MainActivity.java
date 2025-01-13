package com.example.smovies_android;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Data.DatabaseHandler;
import Model.Movie;
import UI.MovieAdapter;
import Util.UtilDb;
import Util.UtilIMDB;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MovieAdapter movieAdapter;
    TextView movieCount;

    DatabaseHandler db = new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieCount = findViewById(R.id.no_of_movies);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        movieCount.setText("no. of movies: "+db.getMoviesCount());
//        db.deleteAll();

//            Log.d("SUNIL SAYS COUNT", String.valueOf(db.getMoviesCount()));
            if(db.getMoviesCount()!=0){
                List<Movie> movieList = db.getMoviesOrderAscending(UtilDb.KEY_NAME);
                movieAdapter = new MovieAdapter(this,movieList);
                recyclerView.setAdapter(movieAdapter);
//                Log.d("SUNIL SAYS", "DATABASE IS not EMPTY");
//                Log.d("SUNIL SAYS", "Size of database: "+db.getMoviesCount());


            }
}
    }

