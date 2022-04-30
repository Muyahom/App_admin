package com.muyahome.checkin.model;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FireStoreDB {
    private final String TAG = "FireStoreDB";
    private static FireStoreDB instance;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseFirestore db;
    private FirebaseStorage storage;
    private Lodging lodging = Lodging.getInstance();
    private ArrayList<String> lodging_img_path = new ArrayList<>();
    private int count =0;

    private FireStoreDB(){
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
    }

    public static FireStoreDB getInstance() {
        if(instance == null)
            instance = new FireStoreDB();
        return instance;
    }

    public void sendImage(){
        StorageReference storageRef = storage.getReference();


        for(int i =0; i<lodging.getLivingroom_lodging_img().size();i++){
//            Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));
            StorageReference riversRef = storageRef.child("lodging_img/"+mAuth.getUid()+count+".jpg");
            lodging_img_path.add(mAuth.getUid()+count+".jpg");
            UploadTask uploadTask = riversRef.putFile(lodging.getLivingroom_lodging_img().get(i));
            count++;

// Register observers to listen for when the download is done or if it fails
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                    // ...
                }
            });
        }

        for(int i =0; i<lodging.getToilet_lodging_img().size();i++){
//            Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));
            StorageReference riversRef = storageRef.child("lodging_img/"+mAuth.getUid()+count+".jpg");
            lodging_img_path.add(mAuth.getUid()+count+".jpg");
            UploadTask uploadTask = riversRef.putFile(lodging.getToilet_lodging_img().get(i));
            count++;

// Register observers to listen for when the download is done or if it fails
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                    // ...
                }
            });
        }
        for(int i =0; i<lodging.getBedroom_lodging_img().size();i++){
//            Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));
            StorageReference riversRef = storageRef.child("lodging_img/"+mAuth.getUid()+count+".jpg");
            lodging_img_path.add(mAuth.getUid()+count+".jpg");
            UploadTask uploadTask = riversRef.putFile(lodging.getBedroom_lodging_img().get(i));
            count++;

// Register observers to listen for when the download is done or if it fails
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                    // ...
                }
            });
        }



    }

    public FirebaseAuth getmAuth(){
        return mAuth;
    }

    public FirebaseUser getUser(){
        user = mAuth.getCurrentUser();
        return user;
    }
    public FirebaseFirestore getDB(){
        return db;
    }

    public void lodgingsetting(){
        sendImage();

        Map<String, Object> lodgingmap = new HashMap<>();
        lodgingmap.put("acceptance", lodging.getAcceptance());
        lodgingmap.put("cnt_bedRoom", lodging.getBedroom());
        lodgingmap.put("cnt_toilet", lodging.getCounttoilet());
        lodgingmap.put("cnt_bed", lodging.getBed());
        lodgingmap.put("convenience", lodging.getConvenience());
        lodgingmap.put("host_uid", mAuth.getUid());
        lodgingmap.put("nfc_distance", lodging.isNfc());
        lodgingmap.put("geopoint", lodging.getGeoPoint());
        lodgingmap.put("title_image_path", mAuth.getUid()+"0.jpg");
        lodgingmap.put("uid",mAuth.getUid());
        lodgingmap.put("img_path",lodging_img_path);
        lodgingmap.put("address",lodging.getAddress());
        lodgingmap.put("fare",lodging.getFare());
        lodgingmap.put("introductory",lodging.getIntroductory());
        lodgingmap.put("state",lodging.getState());
        lodgingmap.put("city",lodging.getCity());
        lodgingmap.put("name",lodging.getName());
        lodgingmap.put("reservation_time_list",lodging.getReservation_time_list());
        lodgingmap.put("review",lodging.getReview());
        lodgingmap.put("type",lodging.getType());


        db.collection("lodging").document(mAuth.getUid())
                .set(lodgingmap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });

    }

}
