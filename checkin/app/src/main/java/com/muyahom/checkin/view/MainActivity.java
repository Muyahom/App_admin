package com.muyahom.checkin.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.muyahom.checkin.R;
import com.muyahom.checkin.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private Fragment frag_main = new FragMain();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.bottomNavigationView.setItemIconTintList(null);

        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, frag_main).commit();

    }
}