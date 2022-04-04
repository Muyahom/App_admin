package com.muyahome.checkin.view;

import android.view.View;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;
import com.muyahome.checkin.viewmodel.ActionListener;
import com.orhanobut.logger.Logger;

//요약
//모델에서 값을 가져와 viewmodel감시하다가(액티비티에서 정의한 콜백이 있음) 업데이트가되면  액티비티에 알림을 주고 알림이오면 액티비티의 콜백을 뷰모델이 실행
//Activity대신해서 실행(View를 조정 혹은 알림을 준다. mutabledata 처리 및 알림

public class LoginView {

    private final String TAG = LoginView.class.getSimpleName();
    private View mView;
    private ActionListener mActionListener;
    private LifecycleOwner mLifecycleOwner;

    public LoginView(View view, LifecycleOwner lifecycleOwner) {
        this.mView = view;
        this.mLifecycleOwner = lifecycleOwner;
    }

    public void setFirebaseUserLiveData(LiveData<FirebaseUser> liveData) {
        liveData.observe(mLifecycleOwner, data -> {
            if (liveData.getValue() == null) {
                //로그인 실패시
                Logger.d(TAG, "Login failed");
                return;
            }
            //로그인 성공시(activity에 notify)
            Logger.d(TAG, "Login success");
            mActionListener.NotifySignInSuccess();
        });
    }

    public void setThrowableUserLiveData(LiveData<Throwable> liveData) {
        liveData.observe(mLifecycleOwner, data -> {
            Logger.d(TAG, data.getMessage());
        });
    }

    public void setActionListener(ActionListener actionListener) {
        mActionListener = actionListener;
    }
}
