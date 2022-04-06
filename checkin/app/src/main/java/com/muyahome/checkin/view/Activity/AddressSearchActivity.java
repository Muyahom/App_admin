package com.muyahome.checkin.view.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseUser;
import com.muyahome.checkin.R;
import com.muyahome.checkin.databinding.ActivityAddressSearchBinding;
import com.muyahome.checkin.model.GeoPointer;
import com.muyahome.checkin.viewmodel.MapViewModel;
import com.orhanobut.logger.Logger;

public class AddressSearchActivity extends AppCompatActivity implements LifecycleOwner{
    private ActivityAddressSearchBinding activityAddressSearchBinding;
    private MapViewModel mapViewModel;
    private LifecycleOwner mlifeCycleowner = this;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddressSearchBinding = DataBindingUtil.setContentView(this, R.layout.activity_address_search);

        mapViewModel = new ViewModelProvider(this, new ViewModelProvider
                .AndroidViewModelFactory(getApplication())).get(MapViewModel.class);
        mapViewModel.setParentContext(this);

        activityAddressSearchBinding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapViewModel.run();
                mapViewModel.setPointObserveValue((AddressSearchActivity)view.getContext());
            }
        });



    }

    public void setPointLiveData(@Nullable LiveData<GeoPointer.Point> liveData) {
        liveData.observe(mlifeCycleowner, data -> {
            if (liveData.getValue() == null) {
                System.out.println("키모링");
                return;
            }
            //로그인 성공시(activity에 notify)
            System.out.println("키모링");
        });
    }



}
