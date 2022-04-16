package com.muyahome.checkin.viewmodel;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;
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
import com.muyahome.checkin.view.Activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class MultiImageAdapter extends RecyclerView.Adapter<MultiImageAdapter.ViewHolder>{
    private ArrayList<Uri> mData = null ;
    private Context mContext = null ;
    private Object resource;

    // 생성자에서 데이터 리스트 객체, Context를 전달받음.
    public MultiImageAdapter(ArrayList<Uri> list, Context context) {
        mData = list ;
        mContext = context;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;

        ViewHolder(View itemView) {
            super(itemView) ;
            // 뷰 객체에 대한 참조.
            image = itemView.findViewById(R.id.image);
        }
    }


    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    // LayoutInflater - XML에 정의된 Resource(자원) 들을 View의 형태로 반환.
    @Override
    public MultiImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;    // context에서 LayoutInflater 객체를 얻는다.
        View view = inflater.inflate(R.layout.multi_image_item, parent, false) ;	// 리사이클러뷰에 들어갈 아이템뷰의 레이아웃을 inflate.
        MultiImageAdapter.ViewHolder vh = new MultiImageAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(MultiImageAdapter.ViewHolder holder, int position) {
        Uri image_uri = mData.get(position);
        resource = mData.get(position);
//        Glide.with(mContext)
//                .load(image_uri)
//                .into(holder.image);

        Glide.with(mContext)
                .asBitmap()
                .load(image_uri)
                .apply(new RequestOptions().override(100, 100))
                .into(new SimpleTarget() {
                    @Override
                    public void onResourceReady(@NonNull Object resource, @Nullable Transition transition) {
                        holder.image.setImageBitmap((Bitmap) resource);
                    }
                });

//        Glide.with(mContext)
//                .asBitmap()
//                .load(image_uri)
////                .apply(new RequestOptions().override(100, 100))
//                .into(new SimpleTarget() {
//                    @Override
//                    public void onResourceReady(@NonNull Object resource, @Nullable Transition transition) {
//                        InputImage image = InputImage.fromBitmap((Bitmap)resource,0);
//                        ImageLabeler labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS);
//
//                        // calling a method to process an image and adding on success listener method to it.
//                        labeler.process(image).addOnSuccessListener(new OnSuccessListener<List<ImageLabel>>() {
//                            @Override
//                            public void onSuccess(List<ImageLabel> firebaseVisionImageLabels) {
//
//                                // inside on success method we are running a loop to get the data from our list.
//                                for (ImageLabel label : firebaseVisionImageLabels) {
//
//                                    // on below line we are getting text from our list.
//                                    String text = label.getText();
//
//                                    // on below line we are getting its entity id
////                                    String entityId = label.getEntityId();
//                                    // on below line we are getting the
//                                    // confidence level of our modal.
//                                    float confidence = label.getConfidence();
//                                    System.out.println("이름"+text+"확률"+confidence);
//
//
//                                }
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                // error handling for on failure method
//                            }
//                        });
//                        holder.image.setImageBitmap((Bitmap) resource);
//                    }
//                });
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }

}
