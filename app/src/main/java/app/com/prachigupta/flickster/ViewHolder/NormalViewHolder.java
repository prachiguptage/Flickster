package app.com.prachigupta.flickster.ViewHolder;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import app.com.prachigupta.flickster.R;

public class NormalViewHolder extends RecyclerView.ViewHolder{

    private ImageView imageView;
    private TextView tvTitle;
    private TextView tvOverview;

    public NormalViewHolder(View view){
        super(view);
        imageView = (ImageView)view.findViewById(R.id.idMovieImage);
        tvTitle =(TextView)view.findViewById(R.id.tvTitle);
        tvOverview =(TextView)view.findViewById(R.id.tvOverview);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(TextView tvTitle) {
        this.tvTitle = tvTitle;
    }

    public TextView getTvOverview() {
        return tvOverview;
    }

    public void setTvOverview(TextView tvOverview) {
        this.tvOverview = tvOverview;
    }
}
