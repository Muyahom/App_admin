package com.muyahome.checkin.view.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.muyahome.checkin.R;
import com.muyahome.checkin.databinding.ActivityRegisterBinding;
import com.muyahome.checkin.databinding.ActivitySecondRegisterBinding;
import com.muyahome.checkin.databinding.ActivityThirdRegisterBinding;
import com.muyahome.checkin.model.Lodging;

public class ThirdRegisterActivity extends AppCompatActivity {
    private Lodging lodging = Lodging.getInstance();
    private ActivityThirdRegisterBinding activityThirdRegisterBinding;
    private ImageView imgguestplus,imgguestminus, imgbedplus,imgbedminus, imgbedroomplus, imgbedroomminus, imgpetplus, imgpetminus;
    private TextView txtguestcount, txtbedcount, txtbedroomcount, txtpetcount;
    private int guestcount=0;
    private int bedcount=0;
    private int bedroomcount=0;
    private int petcount=0;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityThirdRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_third_register);
        imgguestplus = activityThirdRegisterBinding.imgguestplus;
        imgguestminus=activityThirdRegisterBinding.imgguestminus;
        txtguestcount = activityThirdRegisterBinding.txtguestcount;
        imgbedplus = activityThirdRegisterBinding.imgbedplus;
        imgbedminus = activityThirdRegisterBinding.imgbedminus;
        txtbedcount = activityThirdRegisterBinding.txtbedcount;
        imgbedroomplus = activityThirdRegisterBinding.imgbedroomplus;
        imgbedroomminus = activityThirdRegisterBinding.imgbedroomminus;
        txtbedroomcount = activityThirdRegisterBinding.txtbedroomcount;
        imgpetplus = activityThirdRegisterBinding.imgpetplus;
        imgpetminus = activityThirdRegisterBinding.imgpetminus;
        txtpetcount = activityThirdRegisterBinding.txtpetcount;

        imgguestplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guestcount++;
                txtguestcount.setText(String.valueOf(guestcount));
            }
        });

        imgguestminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guestcount--;
                txtguestcount.setText(String.valueOf(guestcount));
            }
        });

        imgbedplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bedcount++;
                txtbedcount.setText(String.valueOf(bedcount));
            }
        });

        imgbedminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bedcount--;
                txtbedcount.setText(String.valueOf(bedcount));
            }
        });

        imgbedroomplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bedroomcount++;
                txtbedroomcount.setText(String.valueOf(bedroomcount));
            }
        });

        imgbedroomminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bedroomcount--;
                txtbedroomcount.setText(String.valueOf(bedroomcount));
            }
        });
        imgpetplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                petcount++;
                txtpetcount.setText(String.valueOf(petcount));
            }
        });

        imgpetminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                petcount--;
                txtpetcount.setText(String.valueOf(petcount));
            }
        });


        activityThirdRegisterBinding.imgbtnNext3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FourthRegisterActivity.class);
                lodging.setAcceptance(guestcount);
                lodging.setBed(bedcount);
                lodging.setBedroom(bedroomcount);
                lodging.setPet(petcount);
                startActivity(intent);
            }
        });
    }
}
