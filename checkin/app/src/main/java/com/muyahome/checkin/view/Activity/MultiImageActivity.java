package com.muyahome.checkin.view.Activity;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.label.ImageLabel;
import com.google.mlkit.vision.label.ImageLabeler;
import com.google.mlkit.vision.label.ImageLabeling;
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions;
import com.muyahome.checkin.R;
import com.muyahome.checkin.model.GeoPointer;
import com.muyahome.checkin.model.Lodging;
import com.muyahome.checkin.viewmodel.ActionListener;
import com.muyahome.checkin.viewmodel.ImageUploadModel;
import com.muyahome.checkin.viewmodel.MultiImageAdapter;

import java.util.ArrayList;
import java.util.List;

public class MultiImageActivity extends AppCompatActivity{
    private static final String TAG = "MultiImageActivity";
    private LifecycleOwner mlifeCycleowner = this;
    private Intent intent;
    private ImageUploadModel imageUploadModel;
    private int count=0;
    private Lodging lodging = Lodging.getInstance();
    ArrayList<Uri> uriList = new ArrayList<>();     // 이미지의 uri를 담을 ArrayList 객체
    ArrayList<Uri> toileturiList = new ArrayList<>();
    ArrayList<Uri> livingroomuriList = new ArrayList<>();
    ArrayList<Uri> bedroomuriList = new ArrayList<>();
    ArrayList<Uri> elseuriList = new ArrayList<>();

    RecyclerView recyclerView;  // 이미지를 보여줄 리사이클러뷰
    MultiImageAdapter toiletadapter;  // 리사이클러뷰에 적용시킬 어댑터
    MultiImageAdapter livingroomadapter;  // 리사이클러뷰에 적용시킬 어댑터
    MultiImageAdapter bedroomadapter;  // 리사이클러뷰에 적용시킬 어댑터
    MultiImageAdapter elseadapter;  // 리사이클러뷰에 적용시킬 어댑터

    RecyclerView livingroom_recyclerView, toilet_recyclerView, bedroom_recyclerView, else_recyclerView;  // 이미지를 보여줄 리사이클러뷰
    ArrayList<MultiImageAdapter> adapterlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_result);
        imageUploadModel = new ViewModelProvider(this, new ViewModelProvider
                .AndroidViewModelFactory(getApplication())).get(ImageUploadModel.class);
        imageUploadModel.setParentContext(this);
        intent = imageUploadModel.intent;


        startActivityForResult(Intent.createChooser(intent, "Select Picture"),2222);
        // 앨범으로 이동하는 버튼
        Button btn_getImage = findViewById(R.id.getImage);
        btn_getImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
//                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 2222);
                Intent intent = new Intent(getApplicationContext(), SixthRegisterActivity.class);
                Toast.makeText(getApplicationContext(),"이미지 업로드 완료", Toast.LENGTH_SHORT);
                startActivity(intent);
            }
        });
        else_recyclerView = findViewById(R.id.else_recyclerView);
        livingroom_recyclerView = findViewById(R.id.livingroom_recyclerView);
        toilet_recyclerView = findViewById(R.id.toilet_recyclerView);
        bedroom_recyclerView = findViewById(R.id.bedroom_recyclerView);

    }



    // 앨범에서 액티비티로 돌아온 후 실행되는 메서드
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data == null){   // 어떤 이미지도 선택하지 않은 경우
            Toast.makeText(getApplicationContext(), "이미지를 선택하지 않았습니다.", Toast.LENGTH_LONG).show();
        }
        else{   // 이미지를 하나라도 선택한 경우
            if(data.getClipData() == null){     // 이미지를 하나만 선택한 경우
                Log.e("single choice: ", String.valueOf(data.getData()));
                Uri imageUri = data.getData();
                uriList.add(imageUri);

                imageClassification(uriList);

//                livingroomadapter = new MultiImageAdapter(uriList, getApplicationContext(),1);
//                livingroom_recyclerView.setAdapter(livingroomadapter);   // 리사이클러뷰에 어댑터 세팅
//                livingroom_recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
//                toiletadapter = new MultiImageAdapter(uriList, getApplicationContext(),2);
//                toilet_recyclerView.setAdapter(toiletadapter);   // 리사이클러뷰에 어댑터 세팅
//                toilet_recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
//                bedroomadapter = new MultiImageAdapter(uriList, getApplicationContext(),3);
//                bedroom_recyclerView.setAdapter(bedroomadapter);   // 리사이클러뷰에 어댑터 세팅
//                bedroom_recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)); // 리사이클러뷰 수평 스크롤 적용
            }
            else{      // 이미지를 여러장 선택한 경우
                ClipData clipData = data.getClipData();
                Log.e("clipData", String.valueOf(clipData.getItemCount()));

                if(clipData.getItemCount() > 10){   // 선택한 이미지가 11장 이상인 경우
                    Toast.makeText(getApplicationContext(), "사진은 10장까지 선택 가능합니다.", Toast.LENGTH_LONG).show();
                }
                else{   // 선택한 이미지가 1장 이상 10장 이하인 경우
                    Log.e(TAG, "multiple choice");

                    for (int i = 0; i < clipData.getItemCount(); i++){
                        Uri imageUri = clipData.getItemAt(i).getUri();  // 선택한 이미지들의 uri를 가져온다.
                        try {
                            uriList.add(imageUri);  //uri를 list에 담는다.

                        } catch (Exception e) {
                            Log.e(TAG, "File select error", e);
                        }
                    }

                    imageClassification(uriList);


//                    livingroomadapter = new MultiImageAdapter(uriList, getApplicationContext(),1);
//                    livingroom_recyclerView.setAdapter(livingroomadapter);   // 리사이클러뷰에 어댑터 세팅
//                    livingroom_recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
//                    toiletadapter = new MultiImageAdapter(uriList, getApplicationContext(),2);
//                    toilet_recyclerView.setAdapter(toiletadapter);   // 리사이클러뷰에 어댑터 세팅
//                    toilet_recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
//                    bedroomadapter = new MultiImageAdapter(uriList, getApplicationContext(),3);
//                    bedroom_recyclerView.setAdapter(bedroomadapter);   // 리사이클러뷰에 어댑터 세팅
//                    bedroom_recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)); // 리사이클러뷰 수평 스크롤 적용
                }
            }
        }
    }

    public void imageClassification(ArrayList<Uri> list){
        this.uriList = list;
        for(int i =0;i<uriList.size(); i++){
            Glide.with(this)
                    .asBitmap()
                    .load(uriList.get(i))
                    .apply(new RequestOptions().override(100, 100))
                    .into(new SimpleTarget() {
                        @Override
                        public void onResourceReady(@NonNull Object resource, @Nullable Transition transition) {
                            InputImage image = InputImage.fromBitmap((Bitmap)resource,0);
                            System.out.println("Count" + count);
                            ImageLabeler labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS);
                            // calling a method to process an image and adding on success listener method to it.
                            labeler.process(image).addOnSuccessListener(new OnSuccessListener<List<ImageLabel>>() {
                                @Override
                                public void onSuccess(List<ImageLabel> firebaseVisionImageLabels) {
                                    count++;
                                    // inside on success method we are running a loop to get the data from our list.
                                    for (ImageLabel label : firebaseVisionImageLabels) {
                                        // on below line we are getting text from our list.
                                        String text = label.getText();
                                        // on below line we are getting its entity id
//                                    String entityId = label.getEntityId();
                                        // on below line we are getting the
                                        // confidence level of our modal.
                                        float confidence = label.getConfidence();
                                        System.out.println("이름"+text+"확률"+confidence);

                                        if(((text.equals("Chair") && confidence>=0.6)||(text.equals("Desk") && confidence>=0.55)||(text.equals("Couch") && confidence>=0.65))){
                                            livingroomuriList.add(uriList.get(count-1));
                                            break;
                                        }
                                        else if((text.equals("Toilet")||text.equals("Bathroom")) && confidence>=0.7 ){
                                            toileturiList.add(uriList.get(count-1));
                                            break;
                                        }
                                        else if (text.equals("Bedroom") && confidence>=0.7 ){
                                            bedroomuriList.add(uriList.get(count-1));
                                            break;
                                        }
                                        else{
                                            if((!text.equals("Room") && confidence>=0.6) &&(!text.equals("Building") && confidence>=0.6) &&(!text.equals("Product") && confidence>=0.6)){
                                                elseuriList.add(uriList.get(count-1));
                                                break;
                                            }
                                        }

                                    }
                                    if(count==uriList.size()){
                                        lodging.setBedroom_lodging_img(bedroomuriList);
                                        lodging.setLivingroom_lodging_img(livingroomuriList);
                                        lodging.setToilet_lodging_img(toileturiList);
                                        lodging.setCountbedroom(bedroomuriList.size());
                                        lodging.setCountlivingroom(livingroomuriList.size());
                                        lodging.setCounttoilet(toileturiList.size());
                                        elseadapter = new MultiImageAdapter(elseuriList,getApplicationContext());
                                        toiletadapter = new MultiImageAdapter(toileturiList,getApplicationContext());
                                        livingroomadapter = new MultiImageAdapter(livingroomuriList,getApplicationContext());
                                        bedroomadapter = new MultiImageAdapter(bedroomuriList,getApplicationContext());
                                        else_recyclerView.setAdapter(elseadapter);
                                        livingroom_recyclerView.setAdapter(livingroomadapter);
                                        toilet_recyclerView.setAdapter(toiletadapter);
                                        bedroom_recyclerView.setAdapter(bedroomadapter);
                                        livingroom_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, true));
                                        toilet_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, true));
                                        bedroom_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, true));
                                        else_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, true));
                                    }

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // error handling for on failure method
                                }
                            });

                        }
                    });
        }

    }

}