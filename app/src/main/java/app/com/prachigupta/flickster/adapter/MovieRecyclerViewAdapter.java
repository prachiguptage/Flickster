package app.com.prachigupta.flickster.adapter;


import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.com.prachigupta.flickster.R;
import app.com.prachigupta.flickster.ViewHolder.NormalViewHolder;
import app.com.prachigupta.flickster.ViewHolder.PopularViewHolder;
import app.com.prachigupta.flickster.models.Movie;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<Movie> items;
    private final int POPULAR =0;
    private final int NORMAL =1;

    public MovieRecyclerViewAdapter(Context context, List<Movie> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;

        switch (viewType){
            case POPULAR:
                view = inflater.inflate(R.layout.item_movie_popular,parent,false);
                viewHolder = new PopularViewHolder(view);
                break;
            case NORMAL:
                view = inflater.inflate(R.layout.item_movie,parent,false);
                viewHolder = new NormalViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()){
            case POPULAR:
                PopularViewHolder pViewHolder =(PopularViewHolder) holder;
                configurePopularViewHolder(pViewHolder,position);
                break;
            case NORMAL:
                NormalViewHolder nViewHolder =(NormalViewHolder) holder;
                configureNormalViewHolder(nViewHolder,position);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(Float.parseFloat(items.get(position).getVoteAverage())>5){
            return POPULAR;
        }else {
            return NORMAL;
        }
    }


    private void configurePopularViewHolder (PopularViewHolder holder,int position){
        Movie movie = items.get(position);
        if(movie!=null){
            Picasso.with(context).load(movie.getBackdropPath()).placeholder(R.drawable.placeholder).into(holder.getImView());
        }
    }

    private void configureNormalViewHolder (NormalViewHolder holder,int position){
        Movie movie = items.get(position);
        if(movie!=null){

            holder.getTvTitle().setText(movie.getOriginalTitle());
            holder.getTvOverview().setText(movie.getOverview());

            String image = null;
            int orientation = context.getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_PORTRAIT){
                image = movie.getPosterPath();
            }else if (orientation == Configuration.ORIENTATION_LANDSCAPE){
                image = movie.getBackdropPath();
            }
            Picasso.with(context).load(image).placeholder(R.drawable.placeholder).into(holder.getImageView());
        }
    }
}
