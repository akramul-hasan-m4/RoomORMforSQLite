package room.orm.sqlite.com.roomormforsqlite.repository;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import room.orm.sqlite.com.roomormforsqlite.model.Movies;

/**
 * Created by DOLPHIN on 11/30/2018.
 */

@Dao
public interface DaoAccess {

    public static final String QUERY_BY_MOVIE_NAME ="SELECT * FROM Movies WHERE movie_name = :movieName";
    public static final String QUERY_ALL ="SELECT * FROM Movies";

    @Insert
    void insertOnlySingleMovie(Movies movies);

    @Insert
    void insertMultipleMovies(List<Movies> moviesList);

    @Query(QUERY_BY_MOVIE_NAME)
    Movies fetchOneMoviesbyMovieName(String movieName);

    @Query(QUERY_ALL)
    List<Movies> fetchAllMovies();

    @Update
    void updateMovie(Movies movies);

    @Delete
    void deleteMovie(Movies movies);
}