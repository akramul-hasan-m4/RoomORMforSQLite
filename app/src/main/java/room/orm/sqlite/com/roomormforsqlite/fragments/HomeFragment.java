package room.orm.sqlite.com.roomormforsqlite.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import room.orm.sqlite.com.roomormforsqlite.MainActivity;
import room.orm.sqlite.com.roomormforsqlite.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{

    Button addMovie, showOneBtn , featchAllBtn;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        addMovie = view.findViewById(R.id.saveId);
        showOneBtn = view.findViewById(R.id.getone);
        featchAllBtn = view.findViewById(R.id.getAll);

        addMovie.setOnClickListener(this);
        showOneBtn.setOnClickListener(this);
        featchAllBtn.setOnClickListener(this);

        return  view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.saveId){
            MainActivity.fragmentManager.beginTransaction().replace(R.id.main_fragment, new SaveFragment()).addToBackStack(null).commit();
        }
        else if (v.getId() == R.id.getone){
            Log.d("yes","i m");
            MainActivity.fragmentManager.beginTransaction().replace(R.id.main_fragment, new showOneInputFragment()).addToBackStack(null).commit();
        }
        else if (v.getId() == R.id.getAll){
           // MainActivity.fragmentManager.beginTransaction().replace(R.id.main_fragment, new showOneInputFragment()).addToBackStack(null).commit();
        }
    }
}
