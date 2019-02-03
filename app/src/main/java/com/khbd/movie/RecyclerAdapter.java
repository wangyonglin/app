package com.khbd.movie;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khbd.app.R;
import com.vendor.design.Atom;
import com.vendor.event.OnMovieItemClickListener;

import java.io.IOException;

import java.io.InputStream;
import java.util.List;

import javakit.network.HttpClientResponse;
import okhttp3.ResponseBody;
import vendor.android.DensityUtil;

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> implements View.OnClickListener{
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 数据集合
     */
    private List<Atom> data;

    private OnMovieItemClickListener onMovieItemClickListener;

    public RecyclerAdapter(List<Atom> data, Context context) {
        this.data = data;
        this.mContext =context;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载item 布局文件
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        view.setOnClickListener(this);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        //将数据设置到item上
         final Atom atom = data.get(position);

        HttpClientResponse.InputStream(atom.getPoster(), new HttpClientResponse.ResultCallback<InputStream>() {
            @Override
            public void success(InputStream res) throws IOException {
                Bitmap bitmap = BitmapFactory.decodeStream(res);
                ViewGroup.LayoutParams imageLayoutParams = viewHolder.moviePoster.getLayoutParams();
                Resources resources = mContext.getResources();
                DisplayMetrics dm = resources.getDisplayMetrics();
                int width = (dm.widthPixels- DensityUtil.dip2px(12,mContext))/3;
                imageLayoutParams.width = width;//获取实际展示的图片宽度
                imageLayoutParams.height = bitmap.getHeight();//获取最终图片高度
                viewHolder.moviePoster.setLayoutParams(imageLayoutParams);
                viewHolder.moviePoster.setImageBitmap(bitmap);
                viewHolder.movieName.setText(atom.getTitle());
            }

            @Override
            public void failure(Exception e) {
                e.printStackTrace();
            }
        });

        viewHolder.itemView.setTag(atom);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View v) {
        Atom atom = (Atom) v.getTag();
        if (onMovieItemClickListener!=null){
            onMovieItemClickListener.onItemClick(atom);
        }
    }

    public void setItemClickListener(OnMovieItemClickListener itemClickListener) {
        onMovieItemClickListener = itemClickListener;
    }



}
