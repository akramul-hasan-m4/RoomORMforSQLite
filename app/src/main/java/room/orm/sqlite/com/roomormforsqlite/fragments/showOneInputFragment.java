package room.orm.sqlite.com.roomormforsqlite.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import room.orm.sqlite.com.roomormforsqlite.R;
import room.orm.sqlite.com.roomormforsqlite.dialogs.ShowSingleMovieDialog;
import room.orm.sqlite.com.roomormforsqlite.model.Movies;
import room.orm.sqlite.com.roomormforsqlite.service.MovieService;


public class showOneInputFragment extends Fragment implements View.OnClickListener{

    EditText searchInput;
    Button searchBtn;
    private MovieService movieService = new MovieService();

    public showOneInputFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_one_input, container, false);
        searchInput = view.findViewById(R.id.movie_search);
        searchBtn = view.findViewById(R.id.search_btn);

        searchBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        String mName = searchInput.getText().toString().trim();
        ShowSingleMovieDialog movieDialog = new ShowSingleMovieDialog();

        if(mName != null && !mName.isEmpty()){
            try {
                Movies movie =  movieService.fetchByMovieName(mName);
                if (movie != null){
                    movieDialog.dialoGMsg(movie);
                    movieDialog.show(getFragmentManager(), "dialog test");
                    searchInput.setText("");
                }else {
                    Toast.makeText(getActivity(), "No item found by : "+mName, Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e){
                Log.d("movieFindEx", e.getMessage());
            }

        } else {
            Toast.makeText(getActivity(), "Please insert movie name", Toast.LENGTH_SHORT).show();
        }
    }
}
