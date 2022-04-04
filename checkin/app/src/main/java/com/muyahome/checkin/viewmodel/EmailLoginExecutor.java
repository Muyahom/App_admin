package com.muyahome.checkin.viewmodel;

import android.app.Activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.common.api.ApiException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.orhanobut.logger.Logger;

public class EmailLoginExecutor {

    private final String TAG = EmailLoginExecutor.class.getSimpleName();


    private MutableLiveData<FirebaseUser> mFirebaseUserLiveData = new MutableLiveData<>();
    private MutableLiveData<Throwable> mThrowableLiveData = new MutableLiveData<>();
    private FirebaseAuth mAuth;


    public EmailLoginExecutor(){
        mAuth = FirebaseAuth.getInstance();
    }

    public void loadUserData(){
        FirebaseUser user = mAuth.getCurrentUser();
        mFirebaseUserLiveData.setValue(user);
    }

    public LiveData<FirebaseUser> getUserLiveData(){
        return mFirebaseUserLiveData;
    }

    public LiveData<Throwable> getThrowableLiveData(){
        return mThrowableLiveData;
    }

    public void signInWithEmail(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Logger.d(TAG, "email login success");
                        try{
                            task.getResult(ApiException.class);
                            mFirebaseUserLiveData.setValue(mAuth.getCurrentUser());
                        }catch (ApiException e){
                            mThrowableLiveData.setValue(e);
                            return;
                        }
                    }else{
                        Logger.d(TAG, "email login failed");
                        mThrowableLiveData.setValue(new NullPointerException("등록된 사용자가 아닙니다."));
                    }
                });
    }

}
