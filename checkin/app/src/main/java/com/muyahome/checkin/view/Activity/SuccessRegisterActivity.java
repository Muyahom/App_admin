package com.muyahome.checkin.view.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.muyahome.checkin.R;
import com.muyahome.checkin.databinding.ActivityLastRegisterBinding;
import com.muyahome.checkin.databinding.ActivityRegisterSuccessBinding;
import com.muyahome.checkin.viewmodel.LodgingViewModel;

public class SuccessRegisterActivity extends AppCompatActivity {
    private ActivityRegisterSuccessBinding activityRegisterSucessBinding;
    private ImageButton imggohome;
    private LodgingViewModel lodgingViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRegisterSucessBinding = DataBindingUtil.setContentView(this, R.layout.activity_register_success);
        imggohome = activityRegisterSucessBinding.gohome;


        lodgingViewModel = new ViewModelProvider(this, new ViewModelProvider
                .AndroidViewModelFactory(getApplication())).get(LodgingViewModel.class);

        lodgingViewModel.setParentContext(this);

        imggohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lodgingViewModel.settingLodging();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}