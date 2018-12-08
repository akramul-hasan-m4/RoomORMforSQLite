package room.orm.sqlite.com.roomormforsqlite.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import room.orm.sqlite.com.roomormforsqlite.R;
import room.orm.sqlite.com.roomormforsqlite.model.Movies;
import room.orm.sqlite.com.roomormforsqlite.service.MovieService;


/**
 * A simple {@link Fragment} subclass.
 */
public class SaveFragment extends Fragment implements View.OnClickListener{

    Button saveBtn;
    EditText movieName;
    private MovieService movieService = new MovieService(); // new instance prevent your Attempt to invoke virtual method on a null object reference error

    public SaveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_save, container, false);
        saveBtn = view.findViewById(R.id.btnSave);
        movieName = view.findViewById(R.id.movie_name);
        saveBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        String mName = movieName.getText().toString();
        if(mName != null && !mName.isEmpty()){
            Movies movie = new Movies();
            movie.setMovieName(mName);
                movieService.saveMovie(movie);
                Toast.makeText(getActivity(), "saved "+mName, Toast.LENGTH_SHORT).show();
                movieName.setText("");
        } else {
            Toast.makeText(getActivity(), "Please insert movie name", Toast.LENGTH_SHORT).show();
        }
    }
}
