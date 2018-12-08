package room.orm.sqlite.com.roomormforsqlite;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import room.orm.sqlite.com.roomormforsqlite.databse_config.MovieDatabase;
import room.orm.sqlite.com.roomormforsqlite.fragments.HomeFragment;
import room.orm.sqlite.com.roomormforsqlite.model.Movies;

public class MainActivity extends AppCompatActivity {


    public static FragmentManager fragmentManager;
    private static final String DATABASE_NAME = "movies_db";
    public static MovieDatabase movieDatabase ;
    Button sav, getall, fetchByid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        if (findViewById(R.id.main_fragment) != null){
            if (savedInstanceState != null){
                return;
            }
            fragmentManager.beginTransaction().add(R.id.main_fragment, new HomeFragment()).commit();
        }


        movieDatabase = MovieDatabase.getMovieDatabase(this);

       /* sav = findViewById(R.id.save);
        getall = findViewById(R.id.fetch);
        fetchByid = findViewById(R.id.fetchByid);

        sav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        getall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchAll();
            }
        });
        fetchByid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchByID(4);
            }
        });*/

    }

    public void save(){
        try {
            Movies movie = new Movies();
            movie.setMovieName("Titanic");
            movieDatabase.daoAccess().insertOnlySingleMovie (movie);
            Toast.makeText(this, "saved",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.d("err", "========="+e.getMessage());
        }

    }

  /*  public void fetchByID(Integer id){
        Movies movie = movieDatabase.daoAccess().fetchOneMoviesbyMovieId(id);
        Log.d("movi", movie.getMovieName());
    }*/

    public void fetchAll(){
        List<Movies> movies = movieDatabase.daoAccess().fetchAllMovies();
        if (movies.size() > 0) {
            for (Movies m: movies){
                Log.d("moviId", m.getMovieId().toString());
                Log.d("moviName", m.getMovieName());
            }
        }
    }

}
