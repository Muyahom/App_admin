package com.muyahome.checkin.view.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.muyahome.checkin.R;
import com.muyahome.checkin.databinding.ActivityFifthRegisterBinding;
import com.muyahome.checkin.viewmodel.ImageUploadModel;
import com.muyahome.checkin.viewmodel.MapViewModel;

public class FifthRegisterActivity extends AppCompatActivity {
    private ActivityFifthRegisterBinding activityFifthRegisterBinding;
    private ImageUploadModel imageUploadModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityFifthRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_fifth_register);



        imageUploadModel = new ViewModelProvider(this, new ViewModelProvider
                .AndroidViewModelFactory(getApplication())).get(ImageUploadModel.class);
        imageUploadModel.setParentContext(this);

        activityFifthRegisterBinding.imgbtnNext5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SixthRegisterActivity.class);
                startActivity(intent);
            }
        });
        activityFifthRegisterBinding.imgupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageUploadModel.gotoGalery();
//                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
//                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent, 2222);
            }
        });

    }
}
