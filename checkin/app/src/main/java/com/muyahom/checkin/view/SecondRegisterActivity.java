package com.muyahom.checkin.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.muyahom.checkin.R;
import com.muyahom.checkin.databinding.ActivitySecondRegisterBinding;
import com.muyahom.checkin.databinding.ActivityThirdRegisterBinding;

public class SecondRegisterActivity extends AppCompatActivity {
    private ActivitySecondRegisterBinding activitySecondRegisterBinding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySecondRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_second_register);
    }
}
