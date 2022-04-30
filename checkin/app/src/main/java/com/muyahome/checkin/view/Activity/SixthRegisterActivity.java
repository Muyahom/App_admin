package com.muyahome.checkin.view.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.muyahome.checkin.R;
import com.muyahome.checkin.databinding.ActivitySixthRegisterBinding;

public class SixthRegisterActivity extends AppCompatActivity {
    private ActivitySixthRegisterBinding activitySixthRegisterBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySixthRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_sixth_register);
        activitySixthRegisterBinding.imgbtnNext6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LastRegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
