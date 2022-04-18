package com.muyahome.checkin.view.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.muyahome.checkin.R;
import com.muyahome.checkin.databinding.ActivityFourthRegisterBinding;
import com.muyahome.checkin.databinding.ActivitySecondRegisterBinding;
import com.muyahome.checkin.model.Lodging;

import java.util.ArrayList;
import java.util.List;

public class FourthRegisterActivity extends AppCompatActivity {
    private ActivityFourthRegisterBinding activityFourthRegisterBinding;
    private ImageButton imgbtnwifi, imgbtntv, imgbtnkitchen, imgbtnair, imgbtncar;
    private Lodging lodging = Lodging.getInstance();
    private ArrayList<String> convenience = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityFourthRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_fourth_register);
        imgbtnwifi= activityFourthRegisterBinding.imgbtnwifi;
        imgbtntv = activityFourthRegisterBinding.imgbtntv;
        imgbtnkitchen = activityFourthRegisterBinding.imgbtnkichen;
        imgbtnair = activityFourthRegisterBinding.imgbtnair;
        imgbtncar = activityFourthRegisterBinding.imgbtncar;

        imgbtnwifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgbtnwifi.isSelected()==true){
                    imgbtnwifi.setSelected(false);
                }
                else{
                    imgbtnwifi.setSelected(true);
                }
            }
        });
        imgbtntv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgbtntv.isSelected()==true){
                    imgbtntv.setSelected(false);
                }
                else{
                    imgbtntv.setSelected(true);
                }
            }
        });
        imgbtnkitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgbtnkitchen.isSelected()==true){
                    imgbtnkitchen.setSelected(false);
                }
                else{
                    imgbtnkitchen.setSelected(true);
                }
            }
        });
        imgbtncar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgbtncar.isSelected()==true){
                    imgbtncar.setSelected(false);
                }
                else{
                    imgbtncar.setSelected(true);
                }
            }
        });

        imgbtnair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgbtnair.isSelected()==true){
                    imgbtnair.setSelected(false);
                }else{
                    imgbtnair.setSelected(true);
                }
            }
        });




        activityFourthRegisterBinding.imgbtnNext4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FifthRegisterActivity.class);
                if(imgbtnwifi.isSelected()==true){
                    convenience.add("WIFI");
                }
                if(imgbtnair.isSelected()==true){
                    convenience.add("에어컨");
                }
                if(imgbtncar.isSelected()==true){
                    convenience.add("주차장");
                }
                if(imgbtnkitchen.isSelected()==true){
                    convenience.add("주방");
                }
                if(imgbtntv.isSelected() == true){
                    convenience.add("TV");
                }
                lodging.setConvenience(convenience);
                startActivity(intent);
            }
        });
    }
}
