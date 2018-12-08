package room.orm.sqlite.com.roomormforsqlite.databse_config;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import room.orm.sqlite.com.roomormforsqlite.model.Movies;
import room.orm.sqlite.com.roomormforsqlite.repository.DaoAccess;

/**
 * Created by DOLPHIN on 11/30/2018.
 */

@Database(entities = {Movies.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    private static MovieDatabase instance;

    public abstract DaoAccess daoAccess() ;

    public static MovieDatabase getMovieDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MovieDatabase.class,
                    "mydb")
                    .allowMainThreadQueries() // SHOULD NOT BE USED IN PRODUCTION !!!
                    .build();
        }
        return instance;
    }

}