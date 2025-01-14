package UI;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smovies_android.R;
import com.example.smovies_android.ViewMovieActivity;

import java.util.List;

import Model.Movie;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context context;
    private List<Movie> movieList;
//    private List<Integer> originalPositions;

    public SearchAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        String poster_url = movie.getPosterUrl();

        Glide.
                with(context).
                load(poster_url).
                placeholder(R.drawable.placeholder).
                error(R.drawable.error).
                into(holder.imageView);

        holder.title.setText(movie.getName());
        holder.genre.setText(movie.getGenre());
        holder.releasedOn.setText(movie.getReleaseDate());

        holder.movieCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "Clicked "+holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ViewMovieActivity.class);
//                intent.putExtra("position", String.valueOf(holder.getAdapterPosition()));
                intent.putExtra("position", String.valueOf(movie.getId()));
//                intent.putExtra("movieId", String.valueOf(movie.getId()));
//                Log.d("SUNIL SAYS ADAPTER POSITION", String.valueOf(holder.getAdapterPosition()));
                Log.d("SUNIL SAYS MOVIE ID FROM ADAPTER", String.valueOf(movie.getId()));
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView title;
        private TextView genre;
        private TextView releasedOn;
        private CardView movieCard;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.thumbnail);
            title = itemView.findViewById(R.id.title_recycler);
            genre = itemView.findViewById(R.id.genre_recycler);
            releasedOn = itemView.findViewById(R.id.releasedOn_recycler);
            movieCard = itemView.findViewById(R.id.movie_card);

        }
    }
}