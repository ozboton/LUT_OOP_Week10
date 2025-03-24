// JSONUtility - reads local JSON and returns a list of valid Movie objects
package com.example.week10;

import android.content.Context;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JSONUtility {

    public static List<Movie> loadMovies(Context context) {
        List<Movie> movieList = new ArrayList<>();

        try {
            // load JSON file from assets
            InputStream is = context.getAssets().open("movies.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);

            // loop through each object in the JSON
            for (int i = 0; i < jsonArray.length(); i++) {
                try {

                    //opens json
                    JSONObject obj = jsonArray.getJSONObject(i);

                    //converts json into string
                    String title = obj.optString("title", null);
                    String yearString = obj.optString("year", "-1");
                    String genre = obj.optString("genre", "Unknown");
                    String poster = obj.optString("poster", "default_poster");

                    // validate year
                    int year;
                    try {
                        year = Integer.parseInt(yearString);
                        //basic range, avoiding unrealistic years, i picked 1950 (random pick) and 2025 (current year)
                        if (year < 1950 || year > 2025) {
                            throw new NumberFormatException("Year out of range");
                        }
                    } catch (NumberFormatException e) {
                        //skips if year is invalid
                        Log.w("JSONUtility", "Invalid year format, index: " + i + ": " + yearString);
                        continue;
                    }

                    //skips if title is empty, skips if it is
                    if (title == null || title.trim().isEmpty()) {
                        Log.w("JSONUtility", "Missing title, index: " + i);
                        continue;
                    }

                    //aff the valid ones to the list
                    movieList.add(new Movie(title, year, genre, poster));

                //error catches
                } catch (JSONException e) {
                    Log.w("JSONUtility", "Invalid movie object, index: " + i + ": " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    Log.w("JSONUtility", "Validation failed, index: " + i + ": " + e.getMessage());
                }
            }

        //error catches
        } catch (IOException e) {
            //unable to open file
            Log.e("JSONUtility", "Could not read movies.json: " + e.getMessage());
        } catch (JSONException e) {
            //not valid format
            Log.e("JSONUtility", "Invalid JSON format: " + e.getMessage());
        }

        return movieList;
    }
}
