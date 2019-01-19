package room.orm.sqlite.com.roomormforsqlite.adapter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import room.orm.sqlite.com.roomormforsqlite.R;
import room.orm.sqlite.com.roomormforsqlite.dialogs.ShowSingleMovieDialog;
import room.orm.sqlite.com.roomormforsqlite.model.Movies;
import room.orm.sqlite.com.roomormforsqlite.service.MovieService;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.CustomRecyclerViewHolder> implements Filterable {
    public List<Movies> orginalMovieList;
    private List<Movies> cloneMovieList;
    private List<Movies> tempList;
    private Context context;
    private ShowSingleMovieDialog movieDialog = new ShowSingleMovieDialog();
    private MovieService movieService = new MovieService();

    class CustomRecyclerViewHolder extends RecyclerView.ViewHolder{

        TextView movieName;
        TextView movieId;

        CustomRecyclerViewHolder(View itemView) {
            super(itemView);
            movieName = itemView.findViewById(R.id.list_text);
            movieId = itemView.findViewById(R.id.list_id);
        }

    }

    public CustomRecyclerViewAdapter(List<Movies> exampleList) {
        this.orginalMovieList = exampleList;
        cloneMovieList = new ArrayList<>(exampleList);
    }

    @NonNull
    @Override
    public CustomRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_list, parent, false);
        context = parent.getContext();
        return new CustomRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomRecyclerViewHolder holder, final int position) {
        Movies currentItem = orginalMovieList.get(position);
        tempList = new ArrayList<>();

        holder.movieName.setText(currentItem.getMovieName());
        holder.movieId.setText(currentItem.getMovieId().toString());

        final CardView cardView = holder.itemView.findViewById(R.id.card);
        cardView.setCardBackgroundColor(checkOddEvenColor(position));
        //cardView.setRadius(20.5f);

        //show data on click
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // notifyDataSetChanged();  if u want to previous selected automatic unselect
                Movies item = orginalMovieList.get(position);
                int color = Color.parseColor("#567845");

                if (tempList.contains(item)){
                    cardView.setCardBackgroundColor(checkOddEvenColor(position));
                    tempList.remove(item);
                }else {
                    cardView.setCardBackgroundColor(color);
                    tempList.add(item);
                    if (item != null){
                       movieDialog.dialoGMsg(item);
                       FragmentManager manager = ((FragmentActivity)context).getSupportFragmentManager(); // get fragment manager for android.support.v4.app.FragmentManager from context
                       movieDialog.show(manager, "dialog test");
                    }
                }
            }
        });

        //delete data when long press based on confirm
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Movies movie =  orginalMovieList.get(position);
                if (movie !=  null){
                    AlertDialog dialog = checkUsermineListener(movie, position);
                    dialog.show();
                }else {
                    Toast.makeText(context,"Somthing is wrong",Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return orginalMovieList.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Movies> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(cloneMovieList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Movies item : cloneMovieList) {
                    if (item.getMovieName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            orginalMovieList.clear();
            orginalMovieList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    private int checkOddEvenColor(int position){
        int color = Color.parseColor("#FFFAF8FD");
        if(position %2 == 1){
            color = Color.parseColor("#b9afaf");
        }
        return color;
    }

    private AlertDialog checkUsermineListener(final Movies movie, final int position){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder.setTitle(R.string.alert_title);
        alertDialogBuilder.setMessage(R.string.alert_msg);
        alertDialogBuilder.setIcon(R.drawable.icondanger);

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteMovie(movie, position);
            }
        });
        alertDialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context,"Cancel",Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = alertDialogBuilder.create();
        // dialog.show();
        return dialog;
    }

    private void deleteMovie(Movies movie, int pos){
        if (movie !=  null){
            movieService.deleteMovie(movie);
            orginalMovieList.remove(pos);
            notifyDataSetChanged();
            Toast.makeText(context,"Successfully delete : " + movie.getMovieName(),Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"Something is wrong",Toast.LENGTH_SHORT).show();
        }
    }
}