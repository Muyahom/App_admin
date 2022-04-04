package com.muyahome.checkin.view.Activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.muyahome.checkin.R;
import com.muyahome.checkin.databinding.ActivitySecondRegisterBinding;
import com.muyahome.checkin.databinding.ActivityThirdRegisterBinding;

public class SecondRegisterActivity extends AppCompatActivity {
    private ActivitySecondRegisterBinding activitySecondRegisterBinding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySecondRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_second_register);
    }
}
