package com.muyahome.checkin.view.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.airbnb.lottie.LottieAnimationView;
import com.muyahome.checkin.R;
import com.muyahome.checkin.databinding.ActivityLastRegisterBinding;
import com.muyahome.checkin.databinding.ActivityRegisterSuccessBinding;
import com.muyahome.checkin.viewmodel.LodgingViewModel;

public class SuccessRegisterActivity extends AppCompatActivity {
    private ActivityRegisterSuccessBinding activityRegisterSucessBinding;

    private LodgingViewModel lodgingViewModel;
    LottieAnimationView animationView;
    private Handler mHadler = new Handler();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRegisterSucessBinding = DataBindingUtil.setContentView(this, R.layout.activity_register_success);



        lodgingViewModel = new ViewModelProvider(this, new ViewModelProvider
                .AndroidViewModelFactory(getApplication())).get(LodgingViewModel.class);

        lodgingViewModel.setParentContext(this);

        animationView = findViewById(R.id.lottie);
        animationView.setAnimation("complete.json");
        animationView.loop(true);
        animationView.playAnimation();
        mHadler.postDelayed(new SplashHandler(), 4000);


    }
    private class SplashHandler implements Runnable{
        public void run(){
            startActivity(new Intent(getApplication(), MainActivity.class));
            SuccessRegisterActivity.this.finish();
        }

    }

    //스플래시 화면에서 넘어갈때 뒤로가기 막기
    public void onBackPressed(){

    }
}