package com.khbd.app.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.data.RecyclerData;
import com.factory.RecyclerFactory;
import com.factory.ScrollingTextFactory;
import com.factory.ToolbarFactory;
import com.interfaces.AdditionalInterface;
import com.khbd.app.MainActivity;
import com.khbd.app.R;
import com.khbd.data.httpClintHelper;
import com.util.APIURL;
import com.util.Logger;
import com.factory.SearchFactory;
import com.util.ToastUtil;
import com.vendor.design.Atom;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment implements AdditionalInterface {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    private View view;
    private TextView fragment_search_text;
    private Toolbar fragment_search_toolbar;
    private RecyclerView fragment_search_recyclerview;
    public SearchFragment() {
    }


    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_search, container, false);
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        this.initViews();
        this.initFactory();
        this.initEvent();
        this.initData();
        super.onCreateView(inflater,container,savedInstanceState);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String str ) {
        if (mListener != null) {
            mListener.onSearchFragmentInteraction(str);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void initViews() {

        fragment_search_recyclerview=(RecyclerView)view.findViewById(R.id.fragment_search_recyclerview);
        fragment_search_text=(TextView) view.findViewById(R.id.fragment_search_text);
        fragment_search_toolbar = (Toolbar)view.findViewById(R.id.fragment_search_toolbar);
    }


    @Override
    public void initFactory() {
        RecyclerFactory.OnCreate(getActivity(), fragment_search_recyclerview, new RecyclerFactory.ResultCallback() {
            @Override
            public void OnCreateView(Context context, RecyclerView recyclerView) {

            }
        });

        ToolbarFactory.OnCreate(getActivity(), fragment_search_toolbar, new ToolbarFactory.ResultCallback() {
            @Override
            public void onShare() {
                ToastUtil.showToast(getActivity(),"SearchActivity in onShare...");
            }
        });
        ScrollingTextFactory.OnCreate(getActivity(), fragment_search_text, new ScrollingTextFactory.ResultCallback() {
            @Override
            public void onCreateView(@NonNull Context context, @NonNull TextView textView) {
                textView.setSelected(true);
            }
        });
    }

    @Override
    public void initData() {
        this.updateRecycler(APIURL.ALL(0, 12));
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onSearchFragmentInteraction(String str);
    }

    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToContext(context);

    }
    @SuppressWarnings("deprecation")
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity);
        }
    }

    protected void onAttachToContext(Context context) {
        //do something
        if(context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener)context; // 2.2 获取到宿主activity并赋值
        } else{
            throw new IllegalArgumentException("must implements FragmentInteraction");
        }

    }


    private void updateRecycler(@NonNull String url){
        httpClintHelper.ResultAtoms(url, new javakit.result.ResultCallback<List<Atom>>() {
            @Override
            public void resove(List<Atom> datas) {

                RecyclerData.load(getActivity(), fragment_search_recyclerview, datas, new RecyclerData.ResultCallback<Atom>() {
                    @Override
                    public void onItemClick(Atom atom) {
                        ToastUtil.showToast(getActivity(), atom.getTitle());
                    }
                });
            }
        });
    }
}
