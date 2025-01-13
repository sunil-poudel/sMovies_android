package com.example.smovies_android;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import Data.DatabaseHandler;
import Model.Movie;
import Util.UtilIMDB;

public class WelcomeActivity extends AppCompatActivity {

    private ImageView gifView;
    private Button enterButton;

    DatabaseHandler db = new DatabaseHandler(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        gifView = findViewById(R.id.gifView);
        enterButton = findViewById(R.id.enterButton);

        Glide.with(this).asGif().load(R.drawable.welcome).into(gifView);


//        db.deleteAll();
        if(db.getMoviesCount()==0) {
            getMovie();
            // Set the timer duration (e.g., 15 seconds)
            long durationInMillis = 15000;

            // Create and start the countdown timer
            new CountDownTimer(durationInMillis, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    // Update the timer display on every tick (every second)
                    long secondsRemaining = millisUntilFinished / 1000;
                    enterButton.setText("first run, loading data: " + secondsRemaining + " seconds");

                }

                @Override
                public void onFinish() {
                    // This is called when the timer finishes
                    enterButton.setText("Enter");
                    enterButton.setBackgroundColor(getColor(R.color.black));
                    enterButton.setTextColor(getColor(R.color.white));
                    enterButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });

                }
            }.start();
        } else{
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }


    public void getMovie(){
        for(int i=0; i<UtilIMDB.IMDB_ID.length; i++) {
            String URL = UtilIMDB.GET_API_URL(UtilIMDB.IMDB_ID[i]);
            RequestQueue queue = Volley.newRequestQueue(this);
            JsonObjectRequest objectRequest = new JsonObjectRequest(
                    Request.Method.GET, URL, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String name = response.getString("Title");
                                String releaseDate = response.getString("Released");
                                String runTime = response.getString("Runtime");
                                String genre = response.getString("Genre");
                                String director = response.getString("Director");
                                String cast = response.getString("Actors");
                                String plot = response.getString("Plot");
                                String language = response.getString("Language");
                                String country = response.getString("Country");
                                String boxOffice = response.getString("BoxOffice");
                                String posterUrl = response.getString("Poster");
                                String metacriticScore = response.getString("Metascore");

                                Movie movie = new Movie(name, releaseDate, runTime, genre, director, cast, plot, language, country, boxOffice, posterUrl, metacriticScore);
                                db.addMovies(movie);

                            } catch (JSONException e) {
                                //                            Log.d("SUNIL SAYS JSON EXCEPTION", "ID "+i+" IMDB "+UtilIMDB.IMDB_ID[i]);
                                throw new RuntimeException(e);
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //                Log.d("SUNIL SAYS ERROR ON RESPONSE", "ID "+i+" IMDB "+UtilIMDB.IMDB_ID[i]);
                }
            }
            );

            queue.add(objectRequest);
        }
    }
    private void restartApplication() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); // Ensure the current activity is completely destroyed
    }


}