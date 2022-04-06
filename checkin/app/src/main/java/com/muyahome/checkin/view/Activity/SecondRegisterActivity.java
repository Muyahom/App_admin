package com.muyahome.checkin.view.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.muyahome.checkin.R;
import com.muyahome.checkin.databinding.ActivitySecondRegisterBinding;
import com.muyahome.checkin.databinding.ActivityThirdRegisterBinding;
import com.muyahome.checkin.viewmodel.MapViewModel;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.util.FusedLocationSource;

public class SecondRegisterActivity extends AppCompatActivity  {
    private ActivitySecondRegisterBinding activitySecondRegisterBinding;
    private static final int LOCATION_PERMISSON_REQUEST_CODE = 1000;
    private FragmentManager fm = getSupportFragmentManager();
    private MapViewModel mapViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySecondRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_second_register);

        mapViewModel = new ViewModelProvider(this, new ViewModelProvider
                .AndroidViewModelFactory(getApplication())).get(MapViewModel.class);

        mapViewModel.setParentContext(this);

        mapViewModel.checkLocation(fm);

        activitySecondRegisterBinding.imgBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddressSearchActivity.class);
                startActivity(intent);
            }
        });


        activitySecondRegisterBinding.imgbtnNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ThirdRegisterActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mapViewModel.onRequestPermissionsResult(requestCode,permissions,grantResults);
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }
}
