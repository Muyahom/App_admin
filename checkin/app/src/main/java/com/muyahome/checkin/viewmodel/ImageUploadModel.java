package com.muyahome.checkin.viewmodel;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.label.ImageLabel;
import com.google.mlkit.vision.label.ImageLabeler;
import com.google.mlkit.vision.label.ImageLabeling;
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions;
import com.muyahome.checkin.model.GeoPointer;
import com.muyahome.checkin.view.Activity.AddressSearchActivity;
import com.muyahome.checkin.view.Activity.MultiImageActivity;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ImageUploadModel extends ViewModel {
    private WeakReference<Activity> mActivityRef;
    public static Intent intent;
    ArrayList<Uri> uriList = new ArrayList<>();// 이미지의 uri를 담을 ArrayList 객체
    ArrayList<Object> toileturiList = new ArrayList<>();
    ArrayList<Object> livingroomuriList = new ArrayList<>();
    ArrayList<Object> bedroomuriList = new ArrayList<>();
    RecyclerView recyclerView;  // 이미지를 보여줄 리사이클러뷰
    MultiImageAdapter toiletadapter;  // 리사이클러뷰에 적용시킬 어댑터
    MultiImageAdapter livingroomadapter;  // 리사이클러뷰에 적용시킬 어댑터
    MultiImageAdapter bedroomadapter;  // 리사이클러뷰에 적용시킬 어댑터

    ArrayList<MultiImageAdapter> adapterlist = new ArrayList<>();
    public ImageUploadModel(){
        Logger.addLogAdapter(new AndroidLogAdapter());
    }




    public void setParentContext(Activity parentContext){
        mActivityRef = new WeakReference<>(parentContext);
    }

    //갤러리 열고 다음 화면으로 전환
    public void gotoGalery(){
//        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*"); //allows any image file type. Change * to specific extension to limit it
//**The following line is the important one!
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        Intent intent2 = new Intent(mActivityRef.get(), MultiImageActivity.class);
        mActivityRef.get().startActivity(intent2);
        mActivityRef.get().finish();
    }





}
