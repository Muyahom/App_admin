package com.muyahome.checkin.view.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.muyahome.checkin.R;
import com.muyahome.checkin.databinding.FragmentMainBinding;
import com.muyahome.checkin.view.Activity.RegisterActivity;

public class FragMain extends Fragment {

    private FragmentMainBinding fragmentMainBinding;

    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        fragmentMainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main,container, false);

        fragmentMainBinding.imgRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        return fragmentMainBinding.getRoot();
    }

}
