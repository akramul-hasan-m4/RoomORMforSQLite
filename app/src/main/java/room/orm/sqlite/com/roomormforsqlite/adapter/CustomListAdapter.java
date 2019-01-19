package room.orm.sqlite.com.roomormforsqlite.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import room.orm.sqlite.com.roomormforsqlite.R;
import room.orm.sqlite.com.roomormforsqlite.model.Movies;

/**
 * Created by DOLPHIN on 12/8/2018.
 */

public class CustomListAdapter extends BaseAdapter {
    /*==============================================================================================================*/
   /* call adapter from another class

    = listView = view.findViewById(R.id.listView);
        movies = movieService.fetchAllMovies();
        if (movies.size() > 0){
            adapter = new CustomAdapter(movies, context);
            listView.setAdapter(adapter);
        }else {
            Toast.makeText(context, "No data Found",Toast.LENGTH_SHORT).show();
        }

        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // for long click listener
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {

                return true;
            }
        });                                                                                             */
    /*=                                                                                                 =*/
    /*=   when you use only list view then u can use this adapter                                                                                              =*/
    /*=                                                                                                 =*/
    /*============================================================================================================*/




    private  List<Movies> movies ;
    private Context context;

    public CustomListAdapter() {
    }

    public CustomListAdapter(List<Movies> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            convertView = LayoutInflater.from(context).inflate(R.layout.show_list,null);
            TextView movieName = convertView.findViewById(R.id.list_text);
            TextView movieId = convertView.findViewById(R.id.list_id);

            movieName.setText(movies.get(position).getMovieName());
            movieId.setText(movies.get(position).getMovieId().toString());
        }catch (Exception e){
            Log.e("exception", e.getMessage());
        }
        return convertView;
    }
}
