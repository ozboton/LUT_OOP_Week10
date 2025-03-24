package com.example.week10;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // sets up RecyclerView with vertical layout
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // load movie list from local JSON using JSONUtility
        List<Movie> movieList = JSONUtility.loadMovies(this);

        // error handling, if no valid movies were found
        if (movieList.isEmpty()) {
            Toast.makeText(this, "No valid movie data found!", Toast.LENGTH_LONG).show();
        }

        // set the adapter to show the movie list in the RecyclerView
        adapter = new MovieAdapter(this, movieList);
        recyclerView.setAdapter(adapter);
    }
}
