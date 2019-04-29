package com.khbd.movie;

import android.content.Context;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khbd.app.R;
import com.khbd.data.BitmapTask;
import com.khbd.data.Webcams;
import com.vendor.event.OnMovieItemClickListener;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> implements View.OnClickListener{
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 数据集合
     */
  //  private List<Atom> data;
    private List<Webcams> mWebcamsData;
    private OnMovieItemClickListener onMovieItemClickListener;
/*
    public RecyclerAdapter(List<Atom> data, Context context) {
        this.data = data;
        this.mContext =context;

    }*/
    public RecyclerAdapter(List<Webcams> data, Context context) {
        this.mWebcamsData = data;
        this.mContext =context;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载item 布局文件
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_card, parent, false);
        view.setOnClickListener(this);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        //将数据设置到item上
         final Webcams webcams = mWebcamsData.get(position);
        new BitmapTask().execute(viewHolder.moviePoster,webcams.getImageurl());
        viewHolder.movieName.setText(webcams.getTitle());
        viewHolder.itemView.setTag(webcams);
    }


    @Override
    public int getItemCount() {
        return mWebcamsData.size();
    }

    @Override
    public void onClick(View v) {
        Webcams webcams = (Webcams) v.getTag();
        if (onMovieItemClickListener!=null){
            onMovieItemClickListener.onItemClick(webcams);
        }
    }

    public void setItemClickListener(OnMovieItemClickListener itemClickListener) {
        onMovieItemClickListener = itemClickListener;
    }



}
