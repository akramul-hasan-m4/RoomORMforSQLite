package room.orm.sqlite.com.roomormforsqlite.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by DOLPHIN on 11/30/2018.
 */

@Entity
public class Movies {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private Integer movieId;

    @ColumnInfo(name = "movie_name")
    private String movieName;

    public Movies() {
    }

    public Integer getMovieId() { return movieId; }

    public void setMovieId(Integer movieId) { this.movieId = movieId; }

    public String getMovieName() { return movieName; }

    public void setMovieName (String movieName) { this.movieName = movieName; }
}