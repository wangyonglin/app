package com.khbd.app.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.data.RecyclerData;
import com.event.TabLayoutEvent;
import com.factory.RecyclerFactory;
import com.factory.TabLayoutFactory;
import com.interfaces.AdditionalInterface;
import com.khbd.app.R;
import com.khbd.data.httpClintHelper;
import com.util.APIURL;
import com.util.ToastUtil;
import com.vendor.design.Atom;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CategoriesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CategoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoriesFragment extends Fragment implements AdditionalInterface {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TabLayout fragment_main_categories;
    private OnFragmentInteractionListener mListener;
    private View view;
    private RecyclerView fragment_categories_recyclerview;

    public CategoriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoriesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoriesFragment newInstance(String param1, String param2) {
        CategoriesFragment fragment = new CategoriesFragment();
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
        view= inflater.inflate(R.layout.fragment_categories, container, false);
        this.initViews();
        this.initFactory();
        this.initData();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String str) {
        if (mListener != null) {
            mListener.onCategoriesInteraction(str);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onCategoriesInteraction(String str);
    }

    @Override
    public void initViews() {
        fragment_main_categories=view.findViewById(R.id.fragment_main_categories);
        fragment_categories_recyclerview=view.findViewById(R.id.fragment_categories_recyclerview);
    }

    @Override
    public void initFactory() {
        RecyclerFactory.OnCreate(getActivity(), fragment_categories_recyclerview, new RecyclerFactory.ResultCallback() {
            @Override
            public void OnCreateView(Context context, RecyclerView recyclerView) {

            }
        });
        TabLayoutFactory.OnCreate(getActivity(), fragment_main_categories, new TabLayoutFactory.ResultCallback() {
            @Override
            public void OnCreateView(Context context, TabLayout view) {
                view.addTab(view.newTab().setText("Japanese"));
                view.addTab(view.newTab().setText("喜剧"));
                view.addTab(view.newTab().setText("动作"));
                view.addTab(view.newTab().setText("爱情"));
                view.addTab(view.newTab().setText("科幻"));
                view.addTab(view.newTab().setText("动画"));
                view.addTab(view.newTab().setText("悬疑"));
                view.addTab(view.newTab().setText("惊悚"));
                view.addTab(view.newTab().setText("犯罪"));
                view.addTab(view.newTab().setText("音乐"));
                view.addTab(view.newTab().setText("歌舞"));
                view.addTab(view.newTab().setText("传记"));
                view.addTab(view.newTab().setText("历史"));
                view.addTab(view.newTab().setText("战争"));
                view.addTab(view.newTab().setText("西部"));
                view.addTab(view.newTab().setText("奇幻"));
                view.addTab(view.newTab().setText("冒险"));
                view.addTab(view.newTab().setText("灾难"));
                view.addTab(view.newTab().setText("武侠"));
                view.addTab(view.newTab().setText("情色"));
            }

            @Override
            public void onTabSelected(String  text) {
                ToastUtil.showToast(getActivity(),text);
                updateRecycler(APIURL.CATEGORIES(text,0,12));
            }
        });
    }

    @Override
    public void initEvent() {
        TabLayoutEvent.OnTabSelectedListener(getActivity(), fragment_main_categories, new TabLayoutEvent.ResultCallback() {
            @Override
            public void onTabSelected(String text) {

                mListener.onCategoriesInteraction(text);
            }
        });
    }
    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToContext(context);
    }
    /*
     * Deprecated on API 23
     * Use onAttachToContext instead
     */
    @SuppressWarnings("deprecation")
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity);
        }
    }

    /*
     * Called when the fragment attaches to the context
     */
    protected void onAttachToContext(Context context) {
        //do something
        if(context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener)context; // 2.2 获取到宿主activity并赋值
        } else{
            throw new IllegalArgumentException("must implements FragmentInteraction");
        }

    }

    @Override
    public void initData() {
        this.updateRecycler(APIURL.CATEGORIES("喜剧",0,12));
    }

    private void updateRecycler(@NonNull String url){
        httpClintHelper.ResultAtoms(url, new javakit.result.ResultCallback<List<Atom>>() {
            @Override
            public void resove(List<Atom> datas) {

                RecyclerData.load(getActivity(), fragment_categories_recyclerview, datas, new RecyclerData.ResultCallback<Atom>() {
                    @Override
                    public void onItemClick(Atom atom) {
                        ToastUtil.showToast(getActivity(), atom.getTitle());
                    }
                });
            }
        });
    }
}
