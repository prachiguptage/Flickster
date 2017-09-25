package app.com.prachigupta.flickster.adapter;


import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.com.prachigupta.flickster.R;
import app.com.prachigupta.flickster.models.Movie;

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    private static class ViewHolder{
        ImageView ivImage;
        TextView tvTitle;
        TextView tvOverview;
    }
    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, R.layout.item_movie,movies );
    }

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        //get Data Item
        Movie movie = getItem(position);

        ViewHolder viewHolder;

        //check existing view reused
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.item_movie,parent,false);

            viewHolder.ivImage = (ImageView)convertView.findViewById(R.id.idMovieImage);
            viewHolder.tvTitle =(TextView)convertView.findViewById(R.id.tvTitle);
            viewHolder.tvOverview =(TextView)convertView.findViewById(R.id.tvOverview);

            convertView.setTag(viewHolder);
        }else {
            viewHolder =(ViewHolder)convertView.getTag();
        }

        viewHolder.tvTitle.setText(movie.getOriginalTitle());
        viewHolder.tvOverview.setText(movie.getOverview());

        String image = null;
        int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT){
            image = movie.getPosterPath();
        }else if (orientation == Configuration.ORIENTATION_LANDSCAPE){
            image = movie.getBackdropPath();
        }

        Picasso.with(getContext()).load(image).placeholder(R.drawable.placeholder).into(viewHolder.ivImage);

        return convertView;
    }
}
