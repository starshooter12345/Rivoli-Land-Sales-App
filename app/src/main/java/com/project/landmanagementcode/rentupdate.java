package com.project.landmanagementcode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.example.landsalesapp.R;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;


import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class rentupdate extends AppCompatActivity {

    Button update;
    private EditText rtitles, rsellers, rdess, rareas, rentals;
    ActionBar actionBar;
    private ImageView rImageView;
    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 101;
    private static final int IMAGE_PICK_CAMERA_CODE = 102;
    private static final int IMAGE_PICK_GALLERY_CODE = 103;

    private String[] cameraPermissions;
    private String[] storagePermissions;
    private Uri imageUri;

    private String  id,rtitle,rseller,rdes,rarea,rental, addTimeStamp, updateTimeStamp;
    private boolean editMode = false;
    private rentDBhelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rent_update);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        rtitles = findViewById(R.id.rtitle);
        rareas = findViewById(R.id.rarea);
        rentals = findViewById(R.id.rental);
        rdess = findViewById(R.id.rdes);
        rsellers = findViewById(R.id.rseller);
        rImageView = findViewById(R.id.image);
        update = findViewById(R.id.addButton);

        Intent intent = getIntent();
        editMode = intent.getBooleanExtra("editMode",editMode);
        id = intent.getStringExtra("ID");
        rtitle = intent.getStringExtra("TITLE");
        rarea = intent.getStringExtra("AREA");
        rental = intent.getStringExtra("RENT");
        rdes = intent.getStringExtra("DESCRIPTION");
        rseller = intent.getStringExtra("RSELLER");
        addTimeStamp = intent.getStringExtra("ADD_TIMESTAMP");
        updateTimeStamp = intent.getStringExtra("UPDATE_TIMESTAMP");

        if(editMode){
            actionBar.setTitle("Update Information");
            editMode = intent.getBooleanExtra("editMode",editMode);
            id = intent.getStringExtra("ID");
            rtitle = intent.getStringExtra("TITLE");
            rarea = intent.getStringExtra("AREA");
            rental = intent.getStringExtra("RENT");
            rdes = intent.getStringExtra("DESCRIPTION");
            rseller = intent.getStringExtra("RSELLER");
            imageUri = Uri.parse(intent.getStringExtra("IMAGE"));
            addTimeStamp = intent.getStringExtra("ADD_TIMESTAMP");
            updateTimeStamp = intent.getStringExtra("UPDATE_TIMESTAMP");

            rtitles.setText(rtitle);
            rareas.setText(rarea);
            rentals.setText(rental);
            rdess.setText(rdes);
            rsellers.setText(rseller);



            if(imageUri.toString().equals("null")){
                rImageView.setImageResource(R.drawable.ic_action_name);
            }
            else{
                rImageView.setImageURI(imageUri);
            }

        }
        else{
            actionBar.setTitle("Add information");
        }


        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        dbHelper = new rentDBhelper(this);

        rImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePickDialog();
            }

        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                startActivity(new Intent(rentupdate.this,MainActivity.class));
                Toast.makeText(rentupdate.this,"Updated successfully",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getData() {
        rtitle = "" + rtitles.getText().toString().trim();
        rarea = "" + rareas.getText().toString().trim();
        rental = "" + rentals.getText().toString().trim();
        rdes = "" + rdess.getText().toString().trim();
        rseller = "" + rsellers.getText().toString().trim();

      /*  if(editMode){
            String newUpdateTime = ""+System.currentTimeMillis();
            dbHelper.updateInfo(
                    "" + id,
                    ""+rtitle,
                    ""+rarea,
                    ""+rental,
                    ""+rdes,
                    ""+rseller,
                    ""+imageUri,
                    ""+addTimeStamp,
                    ""+newUpdateTime
            );
        }*/
      /*  else {
            String timestamp = "" + System.currentTimeMillis();
            dbHelper.updateInfo(
                    "" + id,
                    ""+rtitle,
                    ""+rarea,
                    ""+rental,
                    ""+rdes,
                    ""+rseller,
                    "" + timestamp,
                    "" + timestamp


                    );
        }*/

    }

    private void imagePickDialog(){
        String[]options={"Camera","Gallery"};
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Select for image");
        builder.setItems(options,new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int which){
                if(which==0){
                    if(!checkCameraPermission()){
                        requestCameraPermission();
                    }else{
                        pickFromCamera();
                    }
                }
                else if(which == 1){
                    if(!checkStoragePermission()){
                        requestStoragePermission();
                    }else{
                        pickFromStorage();
                    }
                }


            }
        });
        builder.create().show();
    }
    private void pickFromStorage(){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, IMAGE_PICK_GALLERY_CODE);


    }
    private void pickFromCamera(){
        ContentValues values=new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"Image Title");
        values.put(MediaStore.Images.Media.DESCRIPTION,"Image description");
        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(cameraIntent,IMAGE_PICK_CAMERA_CODE);


    }
    private boolean checkStoragePermission(){
        boolean result= ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                ==(PackageManager.PERMISSION_GRANTED);
        return result;
    }
    private void requestStoragePermission(){
        ActivityCompat.requestPermissions(this, storagePermissions,STORAGE_REQUEST_CODE);
    }
    private boolean checkCameraPermission(){
        boolean result = ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)
                ==(PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                ==(PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }
    private void requestCameraPermission(){
        ActivityCompat.requestPermissions(this,cameraPermissions,CAMERA_REQUEST_CODE);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case CAMERA_REQUEST_CODE:{
                if(grantResults.length>0){
                    boolean cameraAccepted = grantResults[0]==PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1]==PackageManager.PERMISSION_GRANTED;

                    if(cameraAccepted && storageAccepted){
                        pickFromCamera();
                    }else{
                        Toast.makeText(this,"Camera permission required!",Toast.LENGTH_SHORT).show();
                    }

                }
            }
            break;
            case STORAGE_REQUEST_CODE:{
                if(grantResults.length>0){
                    boolean storageAccepted=grantResults[0]==PackageManager.PERMISSION_GRANTED;
                    if(storageAccepted){
                        pickFromStorage();
                    }else{
                        Toast.makeText(this,"Storage permission required",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK) {
            if (requestCode == IMAGE_PICK_GALLERY_CODE) {
                CropImage.activity(data.getData())
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1, 1)
                        .start(this);
            }
            else if(requestCode == IMAGE_PICK_CAMERA_CODE){
                CropImage.activity(imageUri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1,1)
                        .start(this);
            }
            else if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if(resultCode==RESULT_OK){
                    Uri resultUri = result.getUri();
                    imageUri=resultUri;
                    rImageView.setImageURI(resultUri);
                }else if(resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                    Exception error = result.getError();
                    Toast.makeText(this, ""+error,Toast.LENGTH_SHORT).show();
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}

