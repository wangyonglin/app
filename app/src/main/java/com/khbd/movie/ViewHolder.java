package com.khbd.movie;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.khbd.app.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    ImageView moviePoster;
    TextView movieName;

    public ViewHolder(View itemView) {
        super(itemView);
        moviePoster = itemView.findViewById(R.id.image_item);
        movieName = itemView.findViewById(R.id.name_item);
    }
}
