package app.com.prachigupta.flickster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import app.com.prachigupta.flickster.adapter.MovieArrayAdapter;
import app.com.prachigupta.flickster.adapter.MovieRecyclerViewAdapter;
import app.com.prachigupta.flickster.models.Movie;
import cz.msebera.android.httpclient.Header;

public class MovieActivity extends AppCompatActivity {

    ArrayList<Movie> movies;
    MovieArrayAdapter movieArrayAdapter;
    ListView lvItems;
    MovieRecyclerViewAdapter movieRecyclerViewAdapter;
    RecyclerView rvItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        rvItems = (RecyclerView)findViewById(R.id.rvMovies);
        //lvItems =(ListView)findViewById(R.id.lvMovies);
        movies = new ArrayList<>();
//        movieArrayAdapter = new MovieArrayAdapter(this,movies);
//        lvItems.setAdapter(movieArrayAdapter);

        movieRecyclerViewAdapter = new MovieRecyclerViewAdapter(this,movies);
        rvItems.setAdapter(movieRecyclerViewAdapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJsonResult = null;

                try {
                    movieJsonResult=response.getJSONArray("results");
                    movies.addAll(Movie.fromJsonArray(movieJsonResult));
                    //movieArrayAdapter.notifyDataSetChanged();
                    movieRecyclerViewAdapter.notifyDataSetChanged();
                    Log.d("Debug",movies.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}
