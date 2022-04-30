package com.muyahome.checkin.view.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.muyahome.checkin.R;
import com.muyahome.checkin.databinding.ActivityFifthRegisterBinding;
import com.muyahome.checkin.databinding.ActivityLastRegisterBinding;
import com.muyahome.checkin.databinding.ActivityRegisterBinding;
import com.muyahome.checkin.model.Lodging;
import com.muyahome.checkin.viewmodel.ImageUploadModel;
import com.muyahome.checkin.viewmodel.LodgingViewModel;
import com.muyahome.checkin.viewmodel.MapViewModel;

public class LastRegisterActivity extends AppCompatActivity {
    private ActivityLastRegisterBinding activityLastRegisterBinding;
    private ImageButton btnnfcok;
    private LodgingViewModel lodgingViewModel;
    private Lodging lodging = Lodging.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLastRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_last_register);
        btnnfcok = activityLastRegisterBinding.imgbtnnfcok;






        btnnfcok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lodging.setNfc(true);
                Intent intent = new Intent(getApplicationContext(), SuccessRegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
