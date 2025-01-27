package com.example.smovies_android;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
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
import UI.SearchAdapter;
import Util.UtilDb;
import Util.UtilIMDB;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private TextView movieCount;
    private ImageButton searchMovieButton;
    private DatabaseHandler db;
    private SearchAdapter searchResultAdapter;

    private AlertDialog.Builder alertDialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHandler(this);

        movieCount = findViewById(R.id.no_of_movies);
        searchMovieButton = findViewById(R.id.search_movie_button);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        movieCount.setText("Total movies: "+db.getMoviesCount());

            if(db.getMoviesCount()!=0){
                List<Movie> movieList = db.getMoviesOrderAscending(UtilDb.KEY_NAME);
                movieAdapter = new MovieAdapter(this,movieList);
                recyclerView.setAdapter(movieAdapter);
            }

        searchMovieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create pop up dialog with search feature
                alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

                alertDialogBuilder.setTitle("Search Movie");
                alertDialogBuilder.setIcon(R.drawable.search);
                alertDialogBuilder.setCancelable(true);
                View view = LayoutInflater.from(v.getContext()).inflate(R.layout.pop_up_search, null, false);
                EditText searchMovieText = view.findViewById(R.id.movieSearchText);
                alertDialogBuilder.setView(view);

                alertDialogBuilder.setPositiveButton("Search", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String searchKey = searchMovieText.getText().toString().trim();
                        List<Movie> movieList = searchResults(searchKey);
                        searchResultAdapter = new SearchAdapter(MainActivity.this, movieList);
                        recyclerView.setAdapter(searchResultAdapter);
                    }
                });
                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = alertDialogBuilder.create();
                dialog.show();



            }
        });

            getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
                @Override
                public void handleOnBackPressed() {
                    recyclerView.setAdapter(movieAdapter);
                }
            });
    }



    public List<Movie> searchResults(String key_name){
        List<Movie> movieList = db.getMoviesOrderAscending(UtilDb.KEY_NAME);
        List<Movie> searchResults = new ArrayList<>();

        int j=0;
        for(int i=0; i<movieList.size(); i++){
            Movie movie = movieList.get(i);

            String searchKey = movie.toString();
            if(searchKey.toLowerCase().contains(key_name.toLowerCase())){
                searchResults.add(movieList.get(i));
                searchResults.get(j).setId(movieList.get(i).getId());
                j++;
            }

        }
        db.close();
        return searchResults;
    }
}

