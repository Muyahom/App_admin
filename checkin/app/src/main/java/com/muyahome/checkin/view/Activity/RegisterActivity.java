package com.muyahome.checkin.view.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.muyahome.checkin.R;
import com.muyahome.checkin.databinding.ActivityRegisterBinding;
import com.muyahome.checkin.model.Lodging;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding activityRegisterBinding;
    private Lodging lodging = Lodging.getInstance();
    private ImageButton btnhouseall, btnhotel, btnalone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        btnhouseall = activityRegisterBinding.btnhouseall;
        btnalone = activityRegisterBinding.btnalone;
        btnhotel = activityRegisterBinding.btnhotel;

        btnhouseall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnhouseall.setSelected(true);
                btnalone.setSelected(false);
                btnhotel.setSelected(false);
                btnhouseall.setImageResource(R.drawable.s1);
                btnalone.setImageResource(R.drawable.h2);
                btnhotel.setImageResource(R.drawable.h3);
            }
        });

        btnhotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnhouseall.setSelected(false);
                btnalone.setSelected(false);
                btnhotel.setSelected(true);
                btnhotel.setImageResource(R.drawable.s3);
                btnalone.setImageResource(R.drawable.h2);
                btnhouseall.setImageResource(R.drawable.h1);
            }
        });

        btnalone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnhouseall.setSelected(false);
                btnalone.setSelected(true);
                btnhotel.setSelected(false);
                btnalone.setImageResource(R.drawable.s2);
                btnhouseall.setImageResource(R.drawable.h1);
                btnhotel.setImageResource(R.drawable.h3);
            }
        });

        activityRegisterBinding.imgbtnNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnhotel.isSelected()){
                    lodging.setType(2);
                }
                else if(btnalone.isSelected()){
                    lodging.setType(1);
                }
                else{
                    lodging.setType(0);
                }
                System.out.println("Type"+lodging.getType());
                Intent intent = new Intent(getApplicationContext(), SecondRegisterActivity.class);
                startActivity(intent);
            }
        });


    }
}
