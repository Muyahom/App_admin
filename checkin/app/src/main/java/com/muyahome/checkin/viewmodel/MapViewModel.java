package com.muyahome.checkin.viewmodel;

import android.Manifest;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.muyahome.checkin.R;
import com.muyahome.checkin.model.GeoPointer;
import com.muyahome.checkin.view.Activity.AddressSearchActivity;
import com.muyahome.checkin.view.LoginView;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.util.FusedLocationSource;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.lang.ref.WeakReference;

public class MapViewModel extends ViewModel implements OnMapReadyCallback {
    private WeakReference<Activity> mActivityRef;
    private NaverMap naverMap;
    private FusedLocationSource locationSource;
    private static final int LOCATION_PERMISSON_REQUEST_CODE = 1000;
    private MapFragment mapFragment;
    private FragmentManager fm;
    private static final String[] PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    private GeoPointer geoPointer;

    public MapViewModel(){
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    // activity setting
    public void setParentContext(Activity parentContext){
        mActivityRef = new WeakReference<>(parentContext);
    }

    public void checkLocation(FragmentManager fm){
        this.fm = fm;
        mapFragment = (MapFragment)fm.findFragmentById(R.id.map);
        if (mapFragment ==null){
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
        locationSource = new FusedLocationSource(mActivityRef.get(),LOCATION_PERMISSON_REQUEST_CODE);

    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;
        naverMap.setLocationSource(locationSource);
        ActivityCompat.requestPermissions(mActivityRef.get(),PERMISSIONS,LOCATION_PERMISSON_REQUEST_CODE);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        if(locationSource.onRequestPermissionsResult(requestCode,permissions,grantResults)){
            if(!locationSource.isActivated()){
                naverMap.setLocationTrackingMode(LocationTrackingMode.None);
                return;
            }else{
                naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);
            }
        }
    }

    public void run() {
        getPoint(
                "부천시 원종동 469 원종e편한세상아파트"
        );
    }

    private void getPoint(String... addr) {
        geoPointer = new GeoPointer(mActivityRef.get(), listener);
        geoPointer.execute(addr);
    }

    private GeoPointer.OnGeoPointListener listener = new GeoPointer.OnGeoPointListener() {
        @Override
        public void onPoint(MutableLiveData<GeoPointer.Point> p) {
            int sCnt = 0, fCnt = 0;
            if (p!=null) sCnt++;
            else fCnt++;
            Log.d("TEST_CODE", p.getValue().toString());
            Log.d("TEST_CODE", String.format("성공 : %s, 실패 : %s", sCnt, fCnt));
        }

        @Override
        public void onProgress(int progress, int max) {
            Log.d("TEST_CODE", String.format("좌표를 얻어오는중 %s / %s", progress, max));
        }
    };


    public void setPointObserveValue(AddressSearchActivity addressSearchActivity){
        addressSearchActivity.setPointLiveData(geoPointer.getPointLiveData());
    }
}
