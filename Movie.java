package com.example.week10;

public class Movie {
    private String title;
    private int year;
    private String genre;
    private String poster;

    public Movie(String title, int year, String genre, String poster) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title is required.");
        }
        if (year < 1888 || year > 2100) {
            throw new IllegalArgumentException("Invalid year: " + year);
        }
        this.title = title;
        this.year = year;
        this.genre = genre != null ? genre : "Unknown";
        this.poster = poster != null ? poster : "default_poster";
    }

    //getters
    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public String getPoster() {
        return poster;
    }
}
