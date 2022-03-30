package com.muyahom.checkin.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.muyahom.checkin.R;
import com.muyahom.checkin.databinding.FragmentMainBinding;

public class FragMain extends Fragment {

    private FragmentMainBinding fragmentMainBinding;

    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        fragmentMainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main,container, false);
        return fragmentMainBinding.getRoot();
    }

}