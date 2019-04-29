package com.khbd.app.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.data.RecyclerData;
import com.driver.Security;
import com.factory.FactoryCallback;
import com.factory.ViewFactory;
import com.interfaces.AdditionalInterface;
import com.kernel.LoginCallback;
import com.kernel.UserDataUtil;
import com.kernel.component.MessageBox;
import com.kernel.network.HttpUser;
import com.khbd.app.R;
import com.khbd.app.SimplePlayActivity;
import com.khbd.data.Webcams;
import com.khbd.data.WebcamsClient;
import com.util.RouteUtil;

import java.util.List;

import javakit.result.ResultCallback;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements AdditionalInterface {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    private View view;
    private Toolbar fragment_search_toolbar;
    private RecyclerView fragment_search_recyclerview;
    public MainFragment() {
    }


    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
        view=inflater.inflate(R.layout.fragment_main, container, false);
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ViewFactory.OnCreateView(getActivity(),view,R.id.fragment_search_toolbar, new FactoryCallback<Toolbar>() {
            @Override
            public Toolbar onView(Context context, Toolbar view) {
                view.setTitle(getString(R.string.app_name));
                view.setTitleTextColor(Color.WHITE);
                view.setBackgroundColor(getResources().getColor(R.color.primary));
                return view;
            }
        });
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


    }


    @Override
    public void initFactory() {


    }

    @Override
    public void initData() {
        this.updateRecycler(getActivity());
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


    private void updateRecycler(@NonNull Context context){
        WebcamsClient.all(context, new WebcamsClient.WebcamsClientCallback() {
            @Override
            public void WebcamsAll(Context context, List<Webcams> list) {
                RecyclerData.load(getActivity(), fragment_search_recyclerview, list, new RecyclerData.ResultCallback<Webcams>() {
                    @Override
                    public void onItemClick(Webcams webcams) {
                        JumpActivity(UserDataUtil.getUser(getContext()),webcams);
                    }
                });
            }

            @Override
            public void onErrorResume(Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void JumpActivity(String user,Webcams webcams){
        HttpUser httpUser= new HttpUser();
        httpUser.Time(user, new ResultCallback<Integer>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void resove(Integer cls) {
                if(cls>=1){
                    RouteUtil.JumpWhenCanClick(getActivity(),SimplePlayActivity.class,webcams.getVideourl());
                }else{
                    httpUser.Jumbotron(new ResultCallback<String>() {
                        @Override
                        public void resove(String cls) {
                            MessageBox.make(getContext(),"请充值续费",cls,MessageBox.INFO).show();
                        }
                    });

                }
            }

            @Override
            public void reject(RuntimeException e) {
                Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

    }
}
