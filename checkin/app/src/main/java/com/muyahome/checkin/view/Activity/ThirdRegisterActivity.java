package com.muyahome.checkin.view.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.muyahome.checkin.R;
import com.muyahome.checkin.databinding.ActivityRegisterBinding;
import com.muyahome.checkin.databinding.ActivitySecondRegisterBinding;
import com.muyahome.checkin.databinding.ActivityThirdRegisterBinding;

public class ThirdRegisterActivity extends AppCompatActivity {
    private ActivityThirdRegisterBinding activityThirdRegisterBinding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityThirdRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_third_register);
        activityThirdRegisterBinding.imgbtnNext3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FourthRegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}