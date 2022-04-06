package com.muyahome.checkin.view.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.muyahome.checkin.R;
import com.muyahome.checkin.databinding.ActivityMainBinding;
import com.muyahome.checkin.view.Fragment.FragMain;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private Fragment frag_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.bottomNavigationView.setItemIconTintList(null);
        init();
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, frag_main).commit();


    }

    public void init(){
        frag_main = new FragMain();
    }
}