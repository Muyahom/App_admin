package com.muyahome.checkin.viewmodel;

import android.app.Activity;

import androidx.lifecycle.ViewModel;

import com.muyahome.checkin.model.FireStoreDB;
import com.muyahome.checkin.model.Lodging;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.lang.ref.WeakReference;

public class LodgingViewModel extends ViewModel {
    private WeakReference<Activity> mActivityRef;
    private Lodging lodging = Lodging.getInstance();
    private FireStoreDB fireStoreDB = FireStoreDB.getInstance();

    public LodgingViewModel(){
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public void setParentContext(Activity parentContext){
        mActivityRef = new WeakReference<>(parentContext);
    }


    public void settingLodging(){
        fireStoreDB.lodgingsetting();
    }

}
