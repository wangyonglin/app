package com.event;

import android.content.Context;
import android.support.v7.widget.SearchView;
import android.view.View;

public class SearchEvent {
    public static void OnClickListener(Context context, SearchView searchView,ResultCallback resultCallback){
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    public interface ResultCallback{

    }
}
