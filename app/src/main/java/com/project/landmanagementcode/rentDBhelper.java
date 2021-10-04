package com.project.landmanagementcode;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class rentDBhelper extends SQLiteOpenHelper{

    private String rtitle;
    private String rarea;
    private String rental;
    private String rdes;
    private String rseller;

    public rentDBhelper(@Nullable Context context){
        super(context,rentConstant.DB_NAME,null,rentConstant.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(rentConstant.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +rentConstant.TABLE_NAME);
        onCreate(db);

    }
    //insert into function
    public long insertInfo(String rtitle, String rarea, String rental, String rdes, String rseller, String image,String addTimeStamp, String updateTimeStamp) {
        this.rtitle = rtitle;
        this.rarea = rarea;
        this.rental = rental;
        this.rdes = rdes;
        this.rseller = rseller;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(rentConstant.C_RTITLE,rtitle);
        values.put(rentConstant.C_RAREA,rarea);
        values.put(rentConstant.C_RENTAL,rental);
        values.put(rentConstant.C_RDES,rdes);
        values.put(rentConstant.C_RSELLER,rseller);
        values.put(rentConstant.C_IMAGE,image);
        values.put(rentConstant.C_ADD_TIMESTAMP,addTimeStamp);
        values.put(rentConstant.C_UPDATE_TIMESTAMP,updateTimeStamp);




        long id=db.insert(rentConstant.TABLE_NAME,null,values);
        db.close();
        return id;

    }

    //update info function
    public void updateInfo(String id,String image,String addTimeStamp, String updateTimeStamp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(rentConstant.C_RSELLER,rseller);
        values.put(rentConstant.C_RTITLE,rtitle);
        values.put(rentConstant.C_RENTAL,rental);
        values.put(rentConstant.C_RDES,rdes);
        values.put(rentConstant.C_RAREA,rarea);
        values.put(rentConstant.C_IMAGE,image);
        values.put(rentConstant.C_ADD_TIMESTAMP,addTimeStamp);
        values.put(rentConstant.C_UPDATE_TIMESTAMP,updateTimeStamp);

        values.put(Constants.C_IMAGE,image);
        values.put(Constants.C_ADD_TIMESTAMP,addTimeStamp);
        values.put(Constants.C_UPDATE_TIMESTAMP,updateTimeStamp);






        db.update(rentConstant.TABLE_NAME,values,rentConstant.C_ID + " = ?", new String[]{id});

        db.close();
    }


    //delete information
    public void deleteInfo(String id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(rentConstant.TABLE_NAME,rentConstant.C_ID + " = ? ",new String[]{id});
        db.close();
    }

    public ArrayList<rentModel>getAllData(String orderBy) {
        ArrayList<rentModel> arrayList = new ArrayList<>();
        //query for selecting all info in the database
        String selectQuery = "SELECT * FROM " + rentConstant.TABLE_NAME + " ORDER BY " + orderBy;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToNext()) {
            do {

                rentModel model = new rentModel(
                        "" + cursor.getInt(cursor.getColumnIndex(rentConstant.C_ID)),
                        "" + cursor.getString(cursor.getColumnIndex(rentConstant.C_IMAGE)),
                        "" + cursor.getString(cursor.getColumnIndex(rentConstant.C_RTITLE)),
                        "" + cursor.getString(cursor.getColumnIndex(rentConstant.C_RAREA)),
                        "" + cursor.getString(cursor.getColumnIndex(rentConstant.C_RENTAL)),
                        "" + cursor.getString(cursor.getColumnIndex(rentConstant.C_RDES)),
                        "" + cursor.getString(cursor.getColumnIndex(rentConstant.C_RSELLER)),
                        "" + cursor.getString(cursor.getColumnIndex(rentConstant.C_ADD_TIMESTAMP)),
                        "" + cursor.getString(cursor.getColumnIndex(rentConstant.C_UPDATE_TIMESTAMP)));


                arrayList.add(model);
            } while (cursor.moveToNext());
        }
        db.close();
        return arrayList;
    }}