package com.project.landmanagementcode;

public class rentConstant {
    //db name
    public static final String DB_NAME="RENT_INFO_DB";
    //db version
    public static final int DB_VERSION=1;
    //db table
    public static final String TABLE_NAME="RENT_INFO_TABLE";
    //table columns
    public static final String C_ID="ID";
    public static final String C_RTITLE = "RTITLE" ;
    public static final String C_RAREA = "RAREA";
    public static final String C_RENTAL = "RENTAL";
    public static final String C_RDES = "RDES";
    public static final String C_RSELLER = "RSELLER";
    public static final String C_IMAGE="IMAGE";
    public static final String C_ADD_TIMESTAMP="ADD_TIMESTAMP";
    public static final String C_UPDATE_TIMESTAMP="UPDATE_TIMESTAMP";

    //create query for table
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + C_IMAGE + " TEXT,"
            + C_ADD_TIMESTAMP + " TEXT,"
            + C_UPDATE_TIMESTAMP + " TEXT"
            + C_RTITLE + " TEXT,"
            + C_RAREA + " TEXT,"
            + C_RENTAL + " TEXT,"
            + C_RDES + " TEXT,"
            + C_RSELLER + " TEXT,"
            + ");";


}
