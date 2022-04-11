package com.muyahome.checkin.view.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.muyahome.checkin.R;
import com.muyahome.checkin.databinding.ActivitySearchResultBinding;
import com.muyahome.checkin.databinding.ActivitySecondRegisterBinding;
import com.muyahome.checkin.viewmodel.MapViewModel;

public class SearchResultActivity extends AppCompatActivity {
    private ActivitySearchResultBinding activitySearchResultBinding;
    private FragmentManager fm = getSupportFragmentManager();
    private MapViewModel mapViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySearchResultBinding = DataBindingUtil.setContentView(this, R.layout.activity_search_result);

        mapViewModel = new ViewModelProvider(this, new ViewModelProvider
                .AndroidViewModelFactory(getApplication())).get(MapViewModel.class);

        mapViewModel.setParentContext(this);
        mapViewModel.checkLocation(fm);




        activitySearchResultBinding.imgbtnNext8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ThirdRegisterActivity.class);
                startActivity(intent);
            }
        });
    }

}
