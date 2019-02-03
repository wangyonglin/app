package com.khbd.app.support.fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.event.SearchEvent;
import com.interfaces.AdditionalInterface;
import com.khbd.app.R;

import com.util.ToastUtil;

import com.view.SearchFactory;

public class HomeFragment extends Fragment implements View.OnClickListener,AdditionalInterface {
    private static final String TAG = "HomeFragment";
    private SearchView fragment_main_search;
    private View view;
    public HomeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view =inflater.inflate(R.layout.fragment_home, container, false);
         this.initViews(inflater,container,savedInstanceState);
         this.initFactory();
         this.initEvent();
         this.initData();
        super.onCreateView(inflater,container,savedInstanceState);
        return view;
    }
    @Override
    public void onClick(View view) {

    }

    @Override
    public void initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fragment_main_search=view.findViewById(R.id.fragment_main_search);
    }
    @Override
    public void initFactory() {
        SearchFactory.OnCreate(getActivity(),fragment_main_search, new SearchFactory.ResultCallback() {
            @Override
            public void onQueryTextSubmit(Context context, SearchView searchView, String search) {
                ToastUtil.showToast(context,"HomeFragment: "+search);
            }
        });
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {
        // TODO Auto-generated method stub

    }
}
