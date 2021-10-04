package com.project.landmanagementcode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import com.example.landsalesapp.R;
public class EditRecordActivity extends AppCompatActivity {

    private ImageView pImageView;
    private EditText ltitleet, extentinpercheset, priceperperchet, landaddresset, landdescriptionet, sellernameet, sellerphoneet, selleremailet;
    Button saveInfoBt;
    ActionBar actionBar;

    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 101;

    private static final int IMAGE_PICK_CAMERA_CODE = 102;
    private static final int IMAGE_PICK_GALLERY_CODE = 103;

    private String[] cameraPermissions;
    private String[] storagePermissions;
    private Uri imageUri;

    private String id,landtitle, extentinperches, priceperperch, landaddress, landdescription, sellername, sellerphone, selleremail, addTimeStamp, updateTimeStamp;
    private boolean editMode = false;
    private DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        pImageView = findViewById(R.id.image);
        ltitleet = findViewById(R.id.landtitle);
        extentinpercheset = findViewById(R.id.extentinperches);
        priceperperchet = findViewById(R.id.priceperperch);
        landaddresset = findViewById(R.id.landaddress);
        landdescriptionet = findViewById(R.id.landdescription);
        sellernameet = findViewById(R.id.sellername);
        sellerphoneet = findViewById(R.id.sellerphone);
        selleremailet = findViewById(R.id.selleremail);

        saveInfoBt = findViewById(R.id.addButton);

        Intent intent = getIntent();
        editMode = intent.getBooleanExtra("editMode",editMode);
        id = intent.getStringExtra("ID");
        landtitle = intent.getStringExtra("LANDTITLE");
        extentinperches = intent.getStringExtra("EXTENTINPERCHES");
        priceperperch = intent.getStringExtra("PRICEPERPERCH");
        landaddress = intent.getStringExtra("LANDADDRESS");
        landdescription = intent.getStringExtra("LANDDESCRIPTION");
        sellername = intent.getStringExtra("SELLERNAME");
        sellerphone = intent.getStringExtra("SELLERPHONE");
        selleremail = intent.getStringExtra("SELLEREMAIL");
        imageUri = Uri.parse(intent.getStringExtra("IMAGE"));
        addTimeStamp = intent.getStringExtra("ADD_TIMESTAMP");
        updateTimeStamp = intent.getStringExtra("UPDATE_TIMESTAMP");

        if(editMode){
            actionBar.setTitle("Update Information");

            editMode = intent.getBooleanExtra("editMode",editMode);
            id=intent.getStringExtra("ID");
            landtitle = intent.getStringExtra("LANDTITLE");
            extentinperches = intent.getStringExtra("EXTENTINPERCHES");
            priceperperch = intent.getStringExtra("PRICEPERPERCH");
            landaddress = intent.getStringExtra("LANDADDRESS");
            landdescription = intent.getStringExtra("LANDDESCRIPTION");
            sellername = intent.getStringExtra("SELLERNAME");
            sellerphone = intent.getStringExtra("SELLERPHONE");
            selleremail = intent.getStringExtra("SELLEREMAIL");
            imageUri = Uri.parse(intent.getStringExtra("IMAGE"));
            addTimeStamp = intent.getStringExtra("ADD_TIMESTAMP");
            updateTimeStamp = intent.getStringExtra("UPDATE_TIMESTAMP");

            ltitleet.setText(landtitle);
            extentinpercheset.setText(extentinperches);
            priceperperchet.setText(priceperperch);
            landaddresset.setText(landaddress);
            landdescriptionet.setText(landdescription);
            sellernameet.setText(sellername);
            sellerphoneet.setText(sellerphone);
            selleremailet.setText(selleremail);

            if(imageUri.toString().equals("null")){
                pImageView.setImageResource(R.drawable.ic_action_name);
            }
            else{
                pImageView.setImageURI(imageUri);
            }

        }
        else{
            actionBar.setTitle("Add information");
        }


        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        //initiate database object in main function
        dbHelper = new DatabaseHelper(this);

        pImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePickDialog();
            }

        });
        saveInfoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                startActivity(new Intent(EditRecordActivity.this,MainActivity.class));
                Toast.makeText(EditRecordActivity.this,"Updated successfully",Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void getData() {
        landtitle = "" + ltitleet.getText().toString().trim();
        extentinperches = "" + extentinpercheset.getText().toString().trim();
        priceperperch = "" + priceperperchet.getText().toString().trim();
        landaddress = "" + landaddresset.getText().toString().trim();
        landdescription = "" + landdescriptionet.getText().toString().trim();
        sellername = "" + sellernameet.getText().toString().trim();
        sellerphone = "" + sellerphoneet.getText().toString().trim();
        selleremail = "" + selleremailet.getText().toString().trim();

        if(editMode){
            String newUpdateTime = ""+System.currentTimeMillis();

            dbHelper.updateInfo(
                    "" + id,
                    ""+landtitle,
                    ""+extentinperches,
                    ""+priceperperch,
                    ""+landaddress,
                    ""+landdescription,
                    ""+sellername,
                    ""+sellerphone,
                    ""+selleremail,
                    ""+imageUri,
                    ""+addTimeStamp,
                    ""+newUpdateTime

            );
        }
        else {
            String timestamp = "" + System.currentTimeMillis();
            dbHelper.insertInfo(
                    "" + landtitle,
                    "" + extentinperches,
                    "" + priceperperch,
                    "" + landaddress,
                    "" + landdescription,
                    "" + sellername,
                    "" + sellerphone,
                    "" + selleremail,
                    "" + imageUri,
                    "" + timestamp,
                    "" + timestamp

            );
        }
        //Toast.makeText(this, "Record added to id:" + id, Toast.LENGTH_SHORT).show();

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
        //this function gets the image from camera
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, IMAGE_PICK_GALLERY_CODE);


    }
    private void pickFromCamera(){
        //now get image from camera
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
                    pImageView.setImageURI(resultUri);
                }else if(resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                    Exception error = result.getError();
                    Toast.makeText(this, ""+error,Toast.LENGTH_SHORT).show();
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    public boolean onSupportNavigateUp(){
        //this function moves our addrecord activity to mainactivity when back button is pressed
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}