package room.orm.sqlite.com.roomormforsqlite;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import room.orm.sqlite.com.roomormforsqlite.databse_config.MovieDatabase;
import room.orm.sqlite.com.roomormforsqlite.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {


    public static FragmentManager fragmentManager;
    public static MovieDatabase movieDatabase ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        if (findViewById(R.id.main_fragment) != null){
            if (savedInstanceState != null){
                return;
            }
            fragmentManager.beginTransaction().add(R.id.main_fragment, new HomeFragment()).commit(); //replace a fragment in main layout
        }
        movieDatabase = MovieDatabase.getMovieDatabase(this);
    }


    /*@Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure to Exit Application");
        builder.setIcon(R.drawable.upset);
        builder.setCancelable(false);

        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNeutralButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "cancel", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }*/
}
