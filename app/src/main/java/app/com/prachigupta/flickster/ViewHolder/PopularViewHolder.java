package app.com.prachigupta.flickster.ViewHolder;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import app.com.prachigupta.flickster.R;

public class PopularViewHolder extends RecyclerView.ViewHolder {

    private ImageView imView;

    public PopularViewHolder(View view) {
        super(view);
        imView = (ImageView) view.findViewById(R.id.idBackdropImage);
    }

    public ImageView getImView() {
        return imView;
    }

    public void setImView(ImageView imView) {
        this.imView = imView;
    }
}
