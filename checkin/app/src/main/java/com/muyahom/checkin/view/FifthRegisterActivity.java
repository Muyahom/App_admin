package com.muyahom.checkin.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.muyahom.checkin.R;
import com.muyahom.checkin.databinding.ActivityFifthRegisterBinding;

public class FifthRegisterActivity extends AppCompatActivity {
    private ActivityFifthRegisterBinding activityFifthRegisterBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityFifthRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_fifth_register);


    }
}
