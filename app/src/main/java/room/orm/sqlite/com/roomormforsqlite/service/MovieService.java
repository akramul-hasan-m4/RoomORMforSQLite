package room.orm.sqlite.com.roomormforsqlite.service;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import room.orm.sqlite.com.roomormforsqlite.MainActivity;
import room.orm.sqlite.com.roomormforsqlite.model.Movies;

/**
 * Created by DOLPHIN on 12/1/2018.
 */

public class MovieService {

    public void saveMovie(Movies movie){
        Log.d("saveerr", "========="+movie.toString());
        try {
           MainActivity.movieDatabase.daoAccess().insertOnlySingleMovie (movie);
        }catch (Exception e){
           Log.d("err", "========="+e.getMessage());
        }

    }

    public Movies fetchByMovieName(String movieName){
        Movies movie = MainActivity.movieDatabase.daoAccess().fetchOneMoviesbyMovieName(movieName);
        if (movie == null){
            return null;
        }
        Log.d("movie", movie.getMovieName());
        return  movie;
    }

    public List<Movies> fetchAllMovies(){
        List<Movies> movies = MainActivity.movieDatabase.daoAccess().fetchAllMovies();
        if (movies.size() > 0) {
            return movies;
        }
        return new ArrayList<>();
    }
}
