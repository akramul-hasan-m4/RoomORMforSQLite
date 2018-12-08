package room.orm.sqlite.com.roomormforsqlite.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import room.orm.sqlite.com.roomormforsqlite.R;
import room.orm.sqlite.com.roomormforsqlite.model.Movies;

/**
 * Created by DOLPHIN on 12/8/2018.
 */

public class ShowSingleMovieDialog extends AppCompatDialogFragment {

    String moviId, moviNAme;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.show_single_movie_dialog, null);
        TextView movieId = view.findViewById(R.id.show_m_id);
        TextView movieName = view.findViewById(R.id.show_m_name);

        movieId.setText("ID = "+moviId);
        movieName.setText("Name = "+moviNAme);
        builder.setView(view)
                .setTitle("Search result")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }

    public void dialoGMsg(Movies m){
        moviId = m.getMovieId().toString();
        moviNAme = m.getMovieName();
    }

}
