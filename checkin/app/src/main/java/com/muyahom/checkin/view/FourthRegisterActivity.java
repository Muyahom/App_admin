package com.muyahom.checkin.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.muyahom.checkin.R;
import com.muyahom.checkin.databinding.ActivityFourthRegisterBinding;
import com.muyahom.checkin.databinding.ActivitySecondRegisterBinding;

public class FourthRegisterActivity extends AppCompatActivity {
    private ActivityFourthRegisterBinding activityFourthRegisterBinding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityFourthRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_fourth_register);
    }
}
