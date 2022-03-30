package com.muyahom.checkin.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.muyahom.checkin.R;
import com.muyahom.checkin.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding activityRegisterBinding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);


    }
}
