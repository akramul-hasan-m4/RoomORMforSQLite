package room.orm.sqlite.com.roomormforsqlite;

import android.content.Context;
/*import android.support.test.InstrumentationRegistry;*/

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import room.orm.sqlite.com.roomormforsqlite.databse_config.MovieDatabase;

/**
 * Created by DOLPHIN on 12/15/2018.
 */

@RunWith(JUnit4.class)
public class RoomPersistenceTest {

    public static MovieDatabase movieDatabase ;
    private Context context;

    @Before
    public void initDb() throws Exception {
       // context = InstrumentationRegistry.getContext();
        movieDatabase = MovieDatabase.getMovieDatabase(context);
        /*mDatabase = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                UsersDatabase.class)
                .build();*/
    }
}
