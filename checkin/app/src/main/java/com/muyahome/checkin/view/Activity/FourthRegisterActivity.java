package com.muyahome.checkin.view.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.muyahome.checkin.R;
import com.muyahome.checkin.databinding.ActivityFourthRegisterBinding;
import com.muyahome.checkin.databinding.ActivitySecondRegisterBinding;

public class FourthRegisterActivity extends AppCompatActivity {
    private ActivityFourthRegisterBinding activityFourthRegisterBinding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityFourthRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_fourth_register);
        activityFourthRegisterBinding.imgbtnNext4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FifthRegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
