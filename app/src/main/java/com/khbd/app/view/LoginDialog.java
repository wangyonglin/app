package com.khbd.app.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.driver.AuthUtils;
import com.driver.Token;
import com.fasterxml.jackson.databind.JsonNode;
import com.kernel.BootloaderParams;
import com.khbd.app.LoginActivity;
import com.khbd.app.MainActivity;
import com.khbd.app.R;
import com.util.RouteUtil;
import com.util.ToastUtil;

import java.io.IOException;

import javakit.jackson.JacksonUtil;
import javakit.network.HttpClientResponse;
import javakit.network.JavaKitClientResponse;
import javakit.network.JavaKitClientResponseCallback;
import javakit.util.JavaKitJsonUtils;
import javakit.util.JavaKitStringUtil;

public class LoginDialog {
    private static String TAG="LoginDialogFactory";
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
                        System.out.print(res);
                        if(res.isEmpty()&&res.equals("")){
                            callback.failure(new RuntimeException("用户名或密码错误"));
                        }else {
                            try {
                                String  user  = JavaKitJsonUtils.readTree(res).get("user").textValue();
                                String  token  = JavaKitJsonUtils.readTree(res).get("token").textValue();
                                AuthUtils.initUser(context,user);
                                AuthUtils.initToken(context,token);
                                callback.success(context);
                            } catch (IOException e) {
                                e.getStackTrace();
                            }
                        }

                    }

                    @Override
                    public void failure(Exception e) {
                        callback.failure(e);
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
