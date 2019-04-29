package com.khbd.app.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.driver.Manufacturer;
import com.driver.SystemInfo;
import com.interfaces.AdditionalInterface;
import com.kernel.UserDataUtil;
import com.kernel.component.MessageBox;
import com.kernel.network.HttpMain;
import com.kernel.network.HttpUser;
import com.khbd.app.R;
import com.khbd.app.view.OneLineView;
import javakit.Callback;
import javakit.result.ResultCallback;

public class UserFragment extends Fragment implements AdditionalInterface {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView jumbotron_text;
    private OnFragmentInteractionListener mListener;
    OneLineView oneItem, twoItem, thereItem,four_item,del_user;
    private String user_id="";
    private String vip_time="Members have expired";
    private String version="1.0";
    private HttpUser mHttpUser=new HttpUser();
    private HttpMain mHttpMain = new HttpMain();
    public UserFragment() {
    }

    // TODO: Rename and change types and number of parameters
    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
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
        View view=inflater.inflate(R.layout.fragment_user, container, false);
        jumbotron_text=(TextView) view.findViewById(R.id.jumbotron_text);
        user_id=UserDataUtil.getUser(getContext());
        //加载网络Jumbotron
        mHttpUser.Jumbotron(new ResultCallback<String>() {
            @Override
            public void resove(String cls) {
                jumbotron_text.setText(Html.fromHtml(cls));
            }
        });
        //加载会员到期时间
        mHttpUser.Time(user_id, new ResultCallback<Integer>() {
            @Override
            public void resove(Integer cls) {
               if(cls>=1){
                   vip_time=String.valueOf(cls) +" D";
               }
            }

            @Override
            public void reject(RuntimeException e) {
                Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        //
        mHttpMain.Version(new ResultCallback<String>() {
            @Override
            public void resove(String cls) {
                version=cls;
            }
        });
        SystemInfo.Version(getContext(), new Callback<PackageInfo>() {
            @Override
            public void resove(PackageInfo cls) {
                version=cls.versionName;

            }
        });
        //在xml布局中使用MyOneLineView
        del_user=(OneLineView)view.findViewById(R.id.del_user);
        oneItem = (OneLineView) view.findViewById(R.id.one_item);
        twoItem = (OneLineView) view.findViewById(R.id.two_item);
        thereItem = (OneLineView) view.findViewById(R.id.there_item);
        four_item = (OneLineView) view.findViewById(R.id.four_item);
        del_user.initMine(R.mipmap.ic_exit, getString(R.string.LogOut),"exit", false);
        del_user.setOnRootClickListener(new OneLineView.OnRootClickListener() {
            @Override
            public void onRootClick(View view) {
                UserDataUtil.setPass(getActivity(),"");
                UserDataUtil.setToken(getActivity(),"");
                Toast.makeText(getActivity(),"clear user info ok",Toast.LENGTH_SHORT).show();
            }
        },1);
        oneItem.initMine(R.mipmap.ic_vip, getString(R.string.VIP), vip_time, false);
        oneItem.setOnRootClickListener(new OneLineView.OnRootClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onRootClick(View view) {
                MessageBox.make(getContext(),"User Info","user id："+user_id,MessageBox.INFO).show();
            }
        },1);
        four_item.initMine(R.mipmap.ic_setting, getString(R.string.Setting), "", false);
        twoItem.initMine(R.mipmap.ic_version, getString(R.string.Version), version, false);
        twoItem.setOnRootClickListener(new OneLineView.OnRootClickListener() {
            @Override
            public void onRootClick(View view) {
                try {
                    Manufacturer.check(getContext()).onErrorResume(new Manufacturer.OnErrorCallback() {
                        @Override
                        public void ok() {
                            Toast.makeText(getContext(),"Currently the latest version",Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (PackageManager.NameNotFoundException e) {
                    Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }

            }
        },1);
        thereItem.initMine(R.mipmap.ic_contact, getString(R.string.Contact), "", false);
        thereItem.setOnRootClickListener(new OneLineView.OnRootClickListener() {
            @Override
            public void onRootClick(View view) {

            }
        },1);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String str ) {
        if (mListener != null) {
            mListener.onMeFragmentInteraction(str);
        }
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
        if(context instanceof UserFragment.OnFragmentInteractionListener) {
            mListener = (UserFragment.OnFragmentInteractionListener)context; // 2.2 获取到宿主activity并赋值
        } else{
            throw new IllegalArgumentException("must implements FragmentInteraction");
        }

    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onMeFragmentInteraction(String str);
    }



}
