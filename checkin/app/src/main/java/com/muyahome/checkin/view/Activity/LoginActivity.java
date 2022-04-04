package com.muyahome.checkin.view.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.FirebaseApp;
import com.muyahome.checkin.R;
import com.muyahome.checkin.databinding.ActivityLoginBinding;
import com.muyahome.checkin.view.LoginView;
import com.muyahome.checkin.viewmodel.ActionListener;
import com.muyahome.checkin.viewmodel.GoogleLoginExecutor;
import com.muyahome.checkin.viewmodel.LoginViewModel;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.lang.ref.WeakReference;

public class LoginActivity extends AppCompatActivity {
    private final String TAG = LoginActivity.class.getSimpleName();
    private ActivityLoginBinding activityLoginBinding;
    private LoginViewModel mLoginViewModel;

    //firebase 로그인 처리를 위한 변수
    private static final int RC_SIGN_IN = 9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        mLoginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        mLoginViewModel.setParentContext(this);

        init();

        activityLoginBinding.emailSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logger.d(TAG, "Email Login Request");
                String email = activityLoginBinding.edtId.getText().toString().trim();
                String password = activityLoginBinding.edtPassword.getText().toString().trim();
                if(!email.equals("") && !password.equals("")){
                    mLoginViewModel.onRequestSignInWithEmail(email, password);

                }
            }
        });

        activityLoginBinding.signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logger.d(TAG, "Google Login Request");
                mLoginViewModel.onRequestSignInWithGoogle();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Logger.d(TAG, "자동로그인 기능 실행");
        mLoginViewModel.loadUserData();
    }

    private void init() {
        Logger.addLogAdapter(new AndroidLogAdapter());
        //구글 로그인을 사용하는 경우를 위한 메서드 실행
        mLoginViewModel.setGoogleLoginExecutor();
        mLoginViewModel.setGoogleObserveValue(new LoginView(activityLoginBinding.getRoot(), this));

        mLoginViewModel.setEmailLoginExecutor();
        mLoginViewModel.setEmailObservevalue(new LoginView(activityLoginBinding.getRoot(), this));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            mLoginViewModel.onActivityResult(data);
        }
    }
}