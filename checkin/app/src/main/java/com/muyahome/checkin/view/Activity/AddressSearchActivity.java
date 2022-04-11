package com.muyahome.checkin.view.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseUser;
import com.muyahome.checkin.R;
import com.muyahome.checkin.databinding.ActivityAddressSearchBinding;
import com.muyahome.checkin.model.GeoPointer;
import com.muyahome.checkin.model.NetworkStatus;
import com.muyahome.checkin.viewmodel.MapViewModel;
import com.orhanobut.logger.Logger;

public class AddressSearchActivity extends AppCompatActivity implements LifecycleOwner{
    private ActivityAddressSearchBinding activityAddressSearchBinding;
    private MapViewModel mapViewModel;
    private LifecycleOwner mlifeCycleowner = this;
    private EditText edtaddress;
    private FragmentManager fm = getSupportFragmentManager();
    private static final int SEARCH_ADDRESS_ACTIVITY = 1300112;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddressSearchBinding = DataBindingUtil.setContentView(this, R.layout.activity_address_search);
        edtaddress = activityAddressSearchBinding.edtAddress;
        mapViewModel = new ViewModelProvider(this, new ViewModelProvider
                .AndroidViewModelFactory(getApplication())).get(MapViewModel.class);
        mapViewModel.setParentContext(this);
        edtaddress.setFocusable(false);

        edtaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("주소설정페이지", "주소입력창 클릭");
                int status = NetworkStatus.getConnectivityStatus(getApplicationContext());
                if(status == NetworkStatus.TYPE_MOBILE || status == NetworkStatus.TYPE_WIFI) {

                    Log.i("주소설정페이지", "주소입력창 클릭");
                    Intent i = new Intent(getApplicationContext(), AddressApiActivity.class);
                    // 화면전환 애니메이션 없애기
                    overridePendingTransition(0, 0);
                    // 주소결과
                    startActivityForResult(i, SEARCH_ADDRESS_ACTIVITY);

                }else {
                    Toast.makeText(getApplicationContext(), "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
                }


            }
        });

        activityAddressSearchBinding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = edtaddress.getText().toString();
                System.out.println(address);
                Toast.makeText(view.getContext(),address,Toast.LENGTH_SHORT).show();
                mapViewModel.run(address);
                mapViewModel.setPointObserveValue((AddressSearchActivity)view.getContext());
            }
        });



    }

    public void setPointLiveData(@Nullable LiveData<GeoPointer.Point> liveData) {
        liveData.observe(mlifeCycleowner, data -> {
            if (liveData.getValue() == null) {
                Toast.makeText(this,"주소를 정확하게 입력해주세요.", Toast.LENGTH_LONG).show();
                return;
            }
            //로그인 성공시(activity에 notify)
            mapViewModel.onRequestSueccessed();
        });
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Log.i("test", "onActivityResult");

        switch (requestCode) {
            case SEARCH_ADDRESS_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    String data = intent.getExtras().getString("data");
                    if (data != null) {
                        Log.i("test", "data:" + data);
                        edtaddress.setText(data);
                    }
                }
                else{
                    String data = intent.getExtras().getString("data");
                    if (data != null) {
                        Log.i("test", "data:" + data);
                        edtaddress.setText(data);
                    }
                }
                break;
        }
    }



}
