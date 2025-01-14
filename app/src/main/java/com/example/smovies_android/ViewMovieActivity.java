package com.example.smovies_android;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;

import java.util.List;

import Data.DatabaseHandler;
import Model.Movie;
import Util.UtilDb;
import Util.UtilYoutube;

public class ViewMovieActivity extends AppCompatActivity {

    private TextView movieTitle;
    private ImageView moviePoster;
    private TextView movieGenre;
    private TextView movieReleaseDate;
    private TextView movieRuntime;
    private TextView movieLanguage;
    private TextView movieCountry;
    private WebView movieWebViewTrailer;
    private TextView movieDirector;
    private TextView movieCast;
    private TextView moviePlot;
    private ImageButton rotateScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_movie);
        DatabaseHandler db = new DatabaseHandler(this);

        Intent intent = getIntent();
        String positionStr = intent.getStringExtra("position");
        assert positionStr != null;
//        Log.d("SUNIL SAYS RECEIVED POSITION", positionStr);
        int position = Integer.parseInt(positionStr);
//        String idStr = intent.getStringExtra("movieId");
//        int id = Integer.parseInt(idStr);
//        Log.d("SUNIL SAYS INSIDE VIEW MOVIE", String.valueOf(position));

        List<Movie> movieList = db.getMoviesOrderAscending(UtilDb.KEY_NAME);
//        Movie movie = movieList.get(position);

        String EMBED_CODE = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/EPEq3gx377o?autoplay=1\" title=\"Sorry not available\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" ></iframe>";

        Movie movie = new Movie();
        for(int i=0; i<movieList.size(); i++){
            if(movieList.get(i).getId()==position) {
                movie = movieList.get(i);
                for(int j=0; j<UtilYoutube.movieName.length; j++){
                    if(movie.getName().equals(UtilYoutube.movieName[j])){
                        EMBED_CODE = UtilYoutube.embedUrl(UtilYoutube.movieVideoId[j], UtilYoutube.movieTitle[j]);
                        break;
                    }
                }

//                Log.d("SUNIL SAYS METADATA", movieList.get(i).toString());
                break;
            }
        }


        movieTitle = findViewById(R.id.movie_title);
        movieTitle.setText(movie.getName());

        moviePoster = findViewById(R.id.movie_poster);
        Glide.with(this)
                .load(movie.getPosterUrl()).placeholder(R.drawable.placeholder).error(R.drawable.error)
                .into(moviePoster);

        movieGenre = findViewById(R.id.movie_genre);
        movieGenre.setText("Genre: "+movie.getGenre());

        movieReleaseDate = findViewById(R.id.movie_release_date);
        movieReleaseDate.setText("Released on: "+movie.getReleaseDate());

        movieRuntime = findViewById(R.id.movie_runtime);
        movieRuntime.setText("Runtime: "+movie.getRunTime());

        movieLanguage = findViewById(R.id.movie_language);
        movieLanguage.setText("Language: "+movie.getLanguage());

        movieCountry = findViewById(R.id.movie_country);
        movieCountry.setText("Country: "+movie.getCountry());

        movieWebViewTrailer = findViewById(R.id.movie_webView_trailer);

        movieDirector = findViewById(R.id.movie_director);
        movieDirector.setText("Director: "+movie.getDirector());

        movieCast = findViewById(R.id.movie_cast);
        movieCast.setText("Cast: "+movie.getCast());

        moviePlot = findViewById(R.id.movie_plot);
        moviePlot.setText("Plot: "+movie.getPlot());

        WebSettings webSettings = movieWebViewTrailer.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setMediaPlaybackRequiresUserGesture(false);

        movieWebViewTrailer.setWebViewClient(new WebViewClient());
        movieWebViewTrailer.loadData(EMBED_CODE, "text/html", "utf-8");


        db.close();
    }


}