package com.muyahome.checkin.viewmodel;

import android.app.Activity;
import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.muyahome.checkin.view.Activity.MainActivity;
import com.muyahome.checkin.view.LoginView;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.lang.ref.WeakReference;

//View excuter 연결 및 View의 알림이 왔을경우 적절한 처리

public class LoginViewModel extends ViewModel {
    private WeakReference<Activity> mActivityRef;

    //firebase 로그인 처리를 위한 변수
    // LiveData
    private EmailLoginExecutor mEmailLoginExecutor;
    private GoogleLoginExecutor mGoogleLoginExecutor;
    private static final int RC_SIGN_IN = 9001;

    public LoginViewModel(){
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    // activity setting
    public void setParentContext(Activity parentContext){
        mActivityRef = new WeakReference<>(parentContext);
    }

    //구글 로그인을 위한 googleloginexecutor 생성
    public void setGoogleLoginExecutor(){
        mGoogleLoginExecutor = new GoogleLoginExecutor(mActivityRef.get());
    }



    // 로그인 및 에러 변수 observe를 위해 변수 세팅
    public void setGoogleObserveValue(LoginView loginView){
        loginView.setFirebaseUserLiveData(mGoogleLoginExecutor.getUserLiveData());
        loginView.setThrowableUserLiveData(mGoogleLoginExecutor.getThrowableLiveData());
        loginView.setActionListener(getActionListener());
    }


    //자동로그인 기능을 위한 변수 세팅
    public void loadUserData(){
        mGoogleLoginExecutor.loadUserData();
        mEmailLoginExecutor.loadUserData();
    }

    //activity에서 구글 로그인 버튼을 클릭한 경우
    public void onRequestSignInWithGoogle(){
        Intent signInIntent = mGoogleLoginExecutor.getSignInIntent();
        if(mActivityRef.get() != null){
            mActivityRef.get().startActivityForResult(signInIntent, RC_SIGN_IN);
        }
    }

    //구글 ui로 부터 로그인을 진행할 이메일을 가져옴
    public boolean onActivityResult(Intent data){
        mGoogleLoginExecutor.firebaseAuthWithGoogle(data, mActivityRef.get());
        return true;
    }

    public ActionListener getActionListener(){
        return new ActionListener() {
            @Override
            public void NotifySignInSuccess() {
                Intent intent = new Intent(mActivityRef.get(), MainActivity.class);
                mActivityRef.get().startActivity(intent);
                finishActivity();
            }
        };
    }
    private void finishActivity() {
        if (mActivityRef.get() != null) {
            mActivityRef.get().finish();
        }
    }

    public void setEmailLoginExecutor(){
        mEmailLoginExecutor = new EmailLoginExecutor();
    }

    public void setEmailObservevalue(LoginView loginView){
        loginView.setFirebaseUserLiveData(mEmailLoginExecutor.getUserLiveData());
        loginView.setThrowableUserLiveData(mEmailLoginExecutor.getThrowableLiveData());
        loginView.setActionListener(getActionListener());
    }


    public void onRequestSignInWithEmail(String email, String password){
        mEmailLoginExecutor.signInWithEmail(email, password);
    }
}
