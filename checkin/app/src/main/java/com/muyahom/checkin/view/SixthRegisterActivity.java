package com.muyahom.checkin.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.muyahom.checkin.R;
import com.muyahom.checkin.databinding.ActivitySixthRegisterBinding;

public class SixthRegisterActivity extends AppCompatActivity {
    private ActivitySixthRegisterBinding activitySixthRegisterBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySixthRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_sixth_register);
    }
}
