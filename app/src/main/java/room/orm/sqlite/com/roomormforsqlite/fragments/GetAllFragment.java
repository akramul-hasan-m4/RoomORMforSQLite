package room.orm.sqlite.com.roomormforsqlite.fragments;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import room.orm.sqlite.com.roomormforsqlite.R;
import room.orm.sqlite.com.roomormforsqlite.adapter.CustomRecyclerViewAdapter;
import room.orm.sqlite.com.roomormforsqlite.dialogs.ShowSingleMovieDialog;
import room.orm.sqlite.com.roomormforsqlite.model.Movies;
import room.orm.sqlite.com.roomormforsqlite.service.MovieService;

/**
 * A simple {@link Fragment} subclass.
 */
public class GetAllFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private MovieService movieService = new MovieService();
    private ShowSingleMovieDialog movieDialog = new ShowSingleMovieDialog();
    public List<Movies> movies;
    private Context context;
    private RecyclerView recyclerView;
    private CustomRecyclerViewAdapter adapter;
    private SwipeRefreshLayout swipeLayout;

    ListView listView;
    public GetAllFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); //for start calling option on - onCreateOptionsMenu
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_get_all, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);

        swipeLayout = view.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(Color.GREEN);

        initializeMovieAndAdapter();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context; // initialize context for send activity to another class
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.getall_search, menu);
        super.onCreateOptionsMenu(menu, inflater);
        try {
            MenuItem searchItem = menu.findItem(R.id.action_search);
            SearchView searchView = (SearchView) searchItem.getActionView();
            searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    try {
                        adapter.getFilter().filter(newText);
                    }catch (Exception e){
                        Log.d("error",e.getMessage());
                    }

                    return false;
                }
            });
        }catch (Exception e){
            Log.d("error",e.getMessage());
        }
    }

    @Override
    public void onRefresh() {
        initializeMovieAndAdapter();
    }

    private void initializeMovieAndAdapter(){
        movies = movieService.fetchAllMovies();

        if (movies.size() > 0){
            adapter = new CustomRecyclerViewAdapter(movies);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);

            if (swipeLayout.isRefreshing()){
                swipeLayout.setRefreshing(false);
            }
        } else {
            Toast.makeText(context, "Movie List is empty",Toast.LENGTH_SHORT).show();
        }
    }
}
