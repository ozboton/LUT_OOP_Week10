package com.example.week10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final List<Movie> movieList;
    private final Context context;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movieList = movies;
    }

    @NonNull
    @Override
    //inlfation of item layout
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        //binds data to views
        Movie movie = movieList.get(position);
        holder.textTitle.setText(movie.getTitle());
        holder.textYear.setText(String.valueOf(movie.getYear()));
        holder.textGenre.setText(movie.getGenre());

        // load poster image by resource name
        int imageResId = context.getResources().getIdentifier(
                movie.getPoster(), "drawable", context.getPackageName());

        if (imageResId != 0) {
            holder.imagePoster.setImageResource(imageResId);
        } else {
            holder.imagePoster.setImageResource(R.drawable.default_poster); //if there is no picture, replaces it  with a default one
        }
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    // ViewHolder holds references to each item view, inner class, could be in a different java file
    //, but i put it to here, due to personal preferences
    static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView imagePoster;
        TextView textTitle, textYear, textGenre;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imagePoster = itemView.findViewById(R.id.imagePoster);
            textTitle = itemView.findViewById(R.id.textTitle);
            textYear = itemView.findViewById(R.id.textYear);
            textGenre = itemView.findViewById(R.id.textGenre);
        }
    }
}
