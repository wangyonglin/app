package com.khbd.app;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.driver.Controller;
import com.kernel.LoginCallback;
import com.kernel.UserDataUtil;
import com.kernel.network.Welcome;


/**
 * A login screen that offers login via email/password.
 */
public  class LoginActivity extends AppCompatActivity implements Controller {
    private static AutoCompleteTextView login_username;
    private static EditText login_password;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Explode explode = new Explode();
        explode.setDuration(500);
        getWindow().setExitTransition(explode);
        getWindow().setEnterTransition(explode);
        Button go = (Button)findViewById(R.id.login);
        go.setOnClickListener(this);
         login_username=(AutoCompleteTextView)findViewById(R.id.login_username);
        login_username.setText(UserDataUtil.getUser(this));
         login_password=(EditText)findViewById(R.id.login_password);


    }


    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.login){
            Welcome.Login(this,login_username.getText().toString(),login_password.getText().toString(),new LoginCallback<String>(){

                @Override
                public void login_success(@NonNull Context context, String val) {
                    Toast.makeText(LoginActivity.this,"login ok",Toast.LENGTH_SHORT).show();
                    startActivity(LoginActivity.this,MainActivity.class);
                }

                @Override
                public void login_failure(Exception e) {
                    Toast.makeText(LoginActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


}




