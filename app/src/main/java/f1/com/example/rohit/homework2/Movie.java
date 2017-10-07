package f1.com.example.rohit.homework2;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by Rohit on 1/26/2017.
 */

public class Movie implements Serializable {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    String name,description,genre,imdb;
    int rating,year;

    public Movie()
    {

    }
    public Movie(String name, String description, String genre, String imdb, int year, int rating) {
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.imdb = imdb;
        this.year = year;
        this.rating = rating;
    }



public static  Comparator<Movie> getMovieByRating()
 {
     Comparator comp = new Comparator<Movie>(){
         @Override
         public int compare(Movie s1, Movie s2)
         {
             return s2.rating-s1.rating;
         }
     };
     return comp;
     }



   public static Comparator<Movie> getMovieByYear()
    {
        Comparator comp = new Comparator<Movie>(){
            @Override
            public int compare(Movie s1, Movie s2)
            {
                return s1.year-s2.year;
            }
        };
        return comp;
    }


    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", genre='" + genre + '\'' +
                ", imdb='" + imdb + '\'' +
                ", rating=" + rating +
                ", year=" + year +
                '}';
    }
}

