package com.project.landmanagementcode;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class buyerDBhelper extends SQLiteOpenHelper {
	public buyerDBhelper(Context context) {
		super(context, "buyerLogin.db", null, 1);
	}
	
	@Override
	public void onCreate(SQLiteDatabase buyDB) {
		buyDB.execSQL("create Table buyers(buyname Text ,buyemail Text primary key, buypassword Text, buyphonenumber Text)");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase buyDB, int oldVersion, int newVersion) {
		
		buyDB.execSQL("drop Table if exists buyers");
	}
	
	public Boolean insertData(String name, String email, String password, String phonenumber) {
		SQLiteDatabase buyDB = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("buyname", name);
		contentValues.put("buyemail", email);
		contentValues.put("buypassword", password);
		contentValues.put("buyphonenumber", phonenumber);
		long result = buyDB.insert("buyers", null, contentValues);
		
		return result != -1;
		
	}
	
	public boolean checkbuyemail(String buyemail) {
		
		SQLiteDatabase buyDB = this.getWritableDatabase();
		Cursor cursor = buyDB.rawQuery("select * from buyers where buyemail = ?", new String[]{buyemail});
		return cursor.getCount() > 0;
	}
	
	public boolean checkbuyemailpassword(String buyemail, String buypassword) {
		SQLiteDatabase buyDB = this.getWritableDatabase();
		Cursor cursor = buyDB.rawQuery("select * from buyers where buyemail= ? and buypassword = ?", new String[]{buyemail, buypassword});
		return cursor.getCount() > 0;
		
	}
	
	public void deleteBuyerAccount(String buyermail) {
		SQLiteDatabase buyDB = this.getWritableDatabase();
		buyDB.execSQL("delete from buyers where buyemail= ?", new String[]{buyermail});
	}
	
	public void updateBuyerAcoount(String name, String email, String password, String mobileNumber) {
		SQLiteDatabase buyDb = this.getWritableDatabase();
		buyDb.execSQL("update buyers SET buyname = ?, buyemail = ?, buypassword = ?, buyphonenumber = ?", new String[]{name, email, password, mobileNumber});
	}
	
	public List<String> getBuyerData(String buyermail) {
		List<String> list = new ArrayList<>();
		SQLiteDatabase buyDb = this.getWritableDatabase();
		Cursor cursor = buyDb.rawQuery("select * from buyers where buyemail = ?", new String[]{buyermail});
		if (cursor.moveToNext()) {
			String name = cursor.getString(cursor.getColumnIndex("buyname"));
			String email = cursor.getString(cursor.getColumnIndex("buyemail"));
			String password = cursor.getString(cursor.getColumnIndex("buypassword"));
			String phoneNumber = cursor.getString(cursor.getColumnIndex("buyphonenumber"));
			list.add(name);
			list.add(email);
			list.add(password);
			list.add(phoneNumber);
		}
		return list;
	}
	
}
