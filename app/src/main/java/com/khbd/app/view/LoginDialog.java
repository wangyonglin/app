package com.khbd.app.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;


import com.kernel.UserDataUtil;
import com.khbd.app.R;

import java.io.IOException;

import javakit.network.JavaKitClientResponse;
import javakit.network.JavaKitClientResponseCallback;
import javakit.util.JavaKitJsonUtils;

public class LoginDialog {
    private static String TAG="LoginDialog";
    private static AutoCompleteTextView mUserNameView;
    private static EditText mPasswordView;
    private static Button mLoginView;
    private static Button mCancelView;

    public static BottomSheetDialog build(@NonNull Context context,LoginDialogFactoryCallback callback) {
        View view = View.inflate(context,R.layout.dialog_login, null);
        mUserNameView = view.findViewById(R.id.username);
        mPasswordView = view.findViewById(R.id.password);
        mLoginView= view.findViewById(R.id.action_login_submit);
        mCancelView= view.findViewById(R.id.action_login_cancel);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(view);
        callback.onView(context,mUserNameView,mPasswordView);
        bottomSheetDialog.show();
        mLoginView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer buffer= new StringBuffer();
                buffer.append(context.getString(R.string.url_user_login));
                buffer.append("?user=").append(mUserNameView.getText().toString()).append("&pass=").append(mPasswordView.getText().toString());

                JavaKitClientResponse.get(buffer.toString(), new JavaKitClientResponseCallback<String>() {
                    @Override
                    public void success( String res) {

                        if(res.isEmpty()&&res.equals("")){
                            callback.login_failure(new RuntimeException("login fail"));
                        }else {
                            try {
                                String  user  = JavaKitJsonUtils.readTree(res).get("user").textValue();
                                String  token  = JavaKitJsonUtils.readTree(res).get("token").textValue();
                                UserDataUtil.setUser(context,user);
                                UserDataUtil.setToken(context,token);
                                bottomSheetDialog.hide();
                                callback.login_success(context);
                            } catch (IOException e) {
                                callback.login_failure(new RuntimeException("info login fail"));
                            }
                        }

                    }

                    @Override
                    public void failure(Exception e) {
                        callback.login_failure(e);
                    }
                });
            }
        });
        mCancelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return bottomSheetDialog;
    }



}
