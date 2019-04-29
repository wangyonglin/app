package com.khbd.app.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.driver.Manufacturer;
import com.interfaces.AdditionalInterface;
import com.kernel.UserDataUtil;
import com.kernel.component.vip.BulletinDialog;

import com.khbd.app.R;
import com.khbd.app.view.MessageBox;
import com.khbd.app.view.OneLineView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeFragment extends Fragment implements AdditionalInterface {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    OneLineView oneItem, twoItem, thereItem,four_item,del_user;
    public MeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MeFragment newInstance(String param1, String param2) {
        MeFragment fragment = new MeFragment();
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
        //MessageBox.run(getContext(),"充值会员请加微信: EX7132",5000);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_me, container, false);
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
                UserDataUtil.setUser(getActivity(),"");
                UserDataUtil.setPass(getActivity(),"");
                UserDataUtil.setToken(getActivity(),"");
                Toast.makeText(getActivity(),"clear user info ok",Toast.LENGTH_SHORT).show();
            }
        },1);
        oneItem.initMine(R.mipmap.ic_vip, getString(R.string.VIP), "top up", false);
        oneItem.setOnRootClickListener(new OneLineView.OnRootClickListener() {
            @Override
            public void onRootClick(View view) {
                BulletinDialog.make(getContext(),"充值请增加微信:ULV1688").show();
            }
        },1);
        four_item.initMine(R.mipmap.ic_setting, getString(R.string.Setting), "", false);
        twoItem.initMine(R.mipmap.ic_version, getString(R.string.Version), "", false);
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
        if(context instanceof MeFragment.OnFragmentInteractionListener) {
            mListener = (MeFragment.OnFragmentInteractionListener)context; // 2.2 获取到宿主activity并赋值
        } else{
            throw new IllegalArgumentException("must implements FragmentInteraction");
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
        void onMeFragmentInteraction(String str);
    }

}
