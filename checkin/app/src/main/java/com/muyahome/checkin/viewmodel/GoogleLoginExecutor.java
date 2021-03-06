package com.muyahome.checkin.viewmodel;

import android.app.Activity;
import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.muyahome.checkin.R;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
//구글로그인만을 위한 실행자

/*Activity 혹은 Fragment에서 로그인 버튼을 클릭했을때 로그인을 수행하도록 trigger역할을 하거나 onStart()에서 로그인이 완료되었는지
체크하는 작업등을 하는 executor*/
public class GoogleLoginExecutor{
    private final String TAG = GoogleLoginExecutor.class.getSimpleName();
    //mutable - 변경가능한 변수의 유형
    /*firebase에 로그인이 완료되거나 아직 로그인이 되지 않았을때 값을 넣어주게 된다.
    view controller에서 이 livedata를 구독하고 FirebaseUser데이터가 유효한 값이면 이미 로그인이 되었다는 의미이므로
    다음 화면으로 진행하는 방향으로 설계를 진행하였다.*/
    private MutableLiveData<FirebaseUser> mFirebaseUserLiveData = new MutableLiveData<>();


    /*에러가 발생했을때 저장하는 data이다. view controller가 구독한다. 어떤 에러가 발생하면 구독중인 view에서 적절한 ui error handling을 하면 된다.
    error code에 따라서 로그인 버튼을 재 노출 혹은 백그라운드로 다시 로그인 트라이를 하는 작업을 하는 방향으로 설계하였다.*/
    private MutableLiveData<Throwable> mThrowableLiveData = new MutableLiveData<>();

    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;

    public GoogleLoginExecutor(Activity activity){
        Logger.addLogAdapter(new AndroidLogAdapter());
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(activity.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);
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

    public void firebaseAuthWithGoogle(Intent data, Activity activity){
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        GoogleSignInAccount account;
        try{
            account = task.getResult(ApiException.class);
        }catch(ApiException e){
            e.printStackTrace();
            mThrowableLiveData.setValue(e);
            return;
        }
        if (account == null){
            mThrowableLiveData.setValue(new NullPointerException("Failed to retrieve a GoogleSignInAccount"));
            return;
        }
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(paramtask -> {
            if(paramtask.isSuccessful()){
                //로그인 성공 시
                Logger.d(TAG, "LoginSuccess");
                mFirebaseUserLiveData.setValue(mAuth.getCurrentUser());
                return;
            }
            mThrowableLiveData.setValue(task.getException());
        });
    }

    public Intent getSignInIntent(){
        return mGoogleSignInClient.getSignInIntent();
    }
}
