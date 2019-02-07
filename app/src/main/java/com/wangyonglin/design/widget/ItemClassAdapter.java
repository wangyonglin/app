package com.wangyonglin.design.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.khbd.app.R;
import com.wangyonglin.app.domain.ItemClass;

import java.text.ParseException;
import java.util.List;

import vendor.android.framework.FrameworkUtil;
import vendor.date.TimeUtil;
import vendor.http.bitmap.BitmapCallBack;
import vendor.http.bitmap.BitmapUtil;
import com.khbd.app.view.PictureView;

public class ItemClassAdapter extends ArrayAdapter{

    public static class ViewHolder{
        private PictureView picture;
       // private PictureView pictureView;
        private TextView name;
        private TextView overview;
        private TextView released;
    }
    private final int resourceId;
    private List<ItemClass>list;
    public ItemClassAdapter(@NonNull Context context, int resource, @NonNull List<ItemClass> datas) {
        super(context, resource, datas);

        resourceId=resource;
        list=datas;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        ItemClass itemClass = list.get(position);
        View view = makeContextView(holder,parent);
        BitmapUtil.loadBitmap(itemClass.picture, new BitmapCallBack() {
            @Override
            public void loadBitmap(Bitmap bitmap) {
                holder.picture.setImageBitmap(bitmap);
            }
        });
        String released = null;
        try {
            released = TimeUtil.difference(itemClass.released);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.released.setText(released);

        holder.name.setText(itemClass.name);
        return view;
    }
    private View makeContextView(ViewHolder viewHolder,@NonNull ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(resourceId,null);
        viewHolder.picture=(PictureView) view.findViewById(R.id.itemclass_picture);
        int basic =  FrameworkUtil.Span(parent.getContext(),12,1);
        LinearLayout.LayoutParams lpImage = new LinearLayout.LayoutParams(basic*3,basic*2);
        lpImage.setMargins(basic/2,0,basic/2,0);
        viewHolder.picture.setLayoutParams(lpImage);
      //  viewHolder.picture.setBackgroundColor(Color.YELLOW);
        viewHolder.name=(TextView) view.findViewById(R.id.itemclass_name);
        viewHolder.name.setHeight(basic*2/2);
        viewHolder.name.setTextSize(TypedValue.COMPLEX_UNIT_PX,40);
        viewHolder.name.setTextColor(Color.BLACK);
        TextPaint paint = viewHolder.name.getPaint();
        paint.setFakeBoldText(false);

        viewHolder.released=(TextView) view.findViewById(R.id.itemclass_released);
        viewHolder.released.setHeight(basic*2/2);
        viewHolder.released.setTextSize(TypedValue.COMPLEX_UNIT_PX,32);
        //viewHolder.released.setBackgroundColor(Color.BLUE);
        return view;
    }

}
