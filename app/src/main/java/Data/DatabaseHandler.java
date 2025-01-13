package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import Model.Movie;
import Util.UtilDb;
import Util.UtilIMDB;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(@Nullable Context context) {
        super(context, UtilDb.DATABASE_NAME, null, UtilDb.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MOVIES_TABLE = String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY, " +       // ID column
                        "%s TEXT, " +                      // Name column
                        "%s TEXT, " +                      // Release Date column
                        "%s TEXT, " +                   // Runtime column
                        "%s TEXT, " +                      // Genre column
                        "%s TEXT, " +                      // Director column
                        "%s TEXT, " +                      // Cast column
                        "%s TEXT, " +                      // Plot column
                        "%s TEXT, " +                      // Language column
                        "%s TEXT, " +                      // Country column
                        "%s TEXT, " +                      // Box Office column
                        "%s TEXT, " +                      // Poster URL column
                        "%s TEXT)",                     // Metacritic Score column
                UtilDb.KEY_TABLE_NAME,
                UtilDb.KEY_ID,
                UtilDb.KEY_NAME,
                UtilDb.KEY_RELEASE_DATE,
                UtilDb.KEY_RUNTIME,
                UtilDb.KEY_GENRE,
                UtilDb.KEY_DIRECTOR,
                UtilDb.KEY_CAST,
                UtilDb.KEY_PLOT,
                UtilDb.KEY_LANGUAGE,
                UtilDb.KEY_COUNTRY,
                UtilDb.KEY_BOXOFFICE,
                UtilDb.KEY_POSTER_URL,
                UtilDb.KEY_METACRITIC_SCORE
        );
        db.execSQL(CREATE_MOVIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+UtilDb.KEY_TABLE_NAME);
        onCreate(db);
    }

    public void addMovies(Movie movie){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UtilDb.KEY_NAME, movie.getName());
        values.put(UtilDb.KEY_RELEASE_DATE, movie.getReleaseDate());
        values.put(UtilDb.KEY_RUNTIME, movie.getRunTime());
        values.put(UtilDb.KEY_GENRE, movie.getGenre());
        values.put(UtilDb.KEY_DIRECTOR, movie.getDirector());
        values.put(UtilDb.KEY_CAST, movie.getCast());
        values.put(UtilDb.KEY_PLOT, movie.getPlot());
        values.put(UtilDb.KEY_LANGUAGE, movie.getLanguage());
        values.put(UtilDb.KEY_COUNTRY, movie.getCountry());
        values.put(UtilDb.KEY_BOXOFFICE, movie.getBoxOffice());
        values.put(UtilDb.KEY_POSTER_URL, movie.getPosterUrl());
        values.put(UtilDb.KEY_METACRITIC_SCORE, movie.getMetacriticScore());

        db.insert(UtilDb.KEY_TABLE_NAME, null, values);
        db.close();
    }

    public Movie getMovie(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(UtilDb.KEY_TABLE_NAME,
                new String[]{
                        UtilDb.KEY_ID,
                        UtilDb.KEY_NAME,
                        UtilDb.KEY_RELEASE_DATE,
                        UtilDb.KEY_RUNTIME,
                        UtilDb.KEY_GENRE,
                        UtilDb.KEY_DIRECTOR,
                        UtilDb.KEY_CAST,
                        UtilDb.KEY_PLOT,
                        UtilDb.KEY_LANGUAGE,
                        UtilDb.KEY_COUNTRY,
                        UtilDb.KEY_BOXOFFICE,
                        UtilDb.KEY_POSTER_URL,
                        UtilDb.KEY_METACRITIC_SCORE
                }, UtilDb.KEY_ID+"=?",
                new String[]{String.valueOf(id)}, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
            Movie movie = new Movie(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9),
                    cursor.getString(10),
                    cursor.getString(11),
                    cursor.getString(12)
            );
            cursor.close();
            db.close();
            return movie;
        } else{
            db.close();
            return new Movie();
        }
    }

    public List<Movie> getAllMovies(){
        List<Movie> moviesList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String SELECT_ALL = "SELECT * FROM "+UtilDb.KEY_TABLE_NAME;
        Cursor cursor = db.rawQuery(SELECT_ALL, null);

        if(cursor.moveToFirst()){
            do{
                Movie movie = new Movie(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11),
                        cursor.getString(12)
                );
                moviesList.add(movie);

            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return moviesList;
    }

    public int getMoviesCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+UtilDb.KEY_TABLE_NAME, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count;
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+ UtilDb.KEY_TABLE_NAME);
        db.close();
    }

    public List<Movie> getMoviesOrderAscending(String columnName){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Movie> sortedMovieList = new ArrayList<>();

        String ORDER_ASCENDING = "SELECT * FROM "+UtilDb.KEY_TABLE_NAME+" ORDER BY "+columnName+ " ASC";

        Cursor cursor = db.rawQuery(ORDER_ASCENDING, null);

        if(cursor.moveToFirst()){
            do{
                Movie movie = new Movie(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11),
                        cursor.getString(12)
                );
                sortedMovieList.add(movie);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return sortedMovieList;
    }


}
