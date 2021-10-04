package com.project.landmanagementcode;

public class Constants {
    //db name
    public static final String DB_NAME="LAND_INFO_DB";
    //db version
    public static final int DB_VERSION=1;
    //db table
    public static final String TABLE_NAME="LAND_INFO_TABLE";
    //table columns
    public static final String C_ID="ID";
    public static final String C_LANDTITLE="LANDTITLE";
    public static final String C_EXTENTINPERCHES="EXTENTINPERCHES";
    public static final String C_PRICEPERPERCH="PRICEPERPERCH";
    public static final String C_LANDADDRESS = "LANDADDRESS";
    public static final String C_LANDDESCRIPTION="LANDDESCRIPTION";
    public static final String C_SELLERNAME="SELLERNAME";
    public static final String C_SELLERPHONE="SELLERPHONE";
    public static final String C_SELLEREMAIL="SELLEREMAIL";
    public static final String C_IMAGE="IMAGE";
    public static final String C_ADD_TIMESTAMP="ADD_TIMESTAMP";
    public static final String C_UPDATE_TIMESTAMP="UPDATE_TIMESTAMP";

    //create query for table
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + C_LANDTITLE + " TEXT,"
            + C_EXTENTINPERCHES + " TEXT,"
            + C_PRICEPERPERCH + " TEXT,"
            + C_LANDADDRESS + " TEXT,"
            + C_LANDDESCRIPTION + " TEXT,"
            + C_SELLERNAME + " TEXT,"
            + C_SELLERPHONE + " TEXT,"
            + C_SELLEREMAIL + " TEXT,"
            + C_IMAGE + " TEXT,"
            + C_ADD_TIMESTAMP + " TEXT,"
            + C_UPDATE_TIMESTAMP + " TEXT"
            + ");";





}
