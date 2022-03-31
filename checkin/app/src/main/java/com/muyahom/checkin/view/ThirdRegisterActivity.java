package com.muyahom.checkin.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.muyahom.checkin.R;
import com.muyahom.checkin.databinding.ActivityRegisterBinding;
import com.muyahom.checkin.databinding.ActivitySecondRegisterBinding;
import com.muyahom.checkin.databinding.ActivityThirdRegisterBinding;

public class ThirdRegisterActivity extends AppCompatActivity {
    private ActivityThirdRegisterBinding activityThirdRegisterBinding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityThirdRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_third_register);
    }
}
