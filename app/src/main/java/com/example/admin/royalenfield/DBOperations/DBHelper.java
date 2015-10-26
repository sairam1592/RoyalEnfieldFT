package com.example.admin.royalenfield.DBOperations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "REFT.db";
    public static final String CONTACTS_TABLE_NAME = "riderdetails";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_BULLTYPE = "bulltype";
    public static final String CONTACTS_COLUMN_MILEAGE = "mileage";
    public static final String CONTACTS_COLUMN_FUELCOST = "fuelcost";
    public static final String CONTACTS_COLUMN_UNIQUEKEY = "uniquekey";
    public static final String CONTACTS_COLUMN_FROMPLACE = "fromplace";
    public static final String CONTACTS_COLUMN_TOPLACE = "toplace";
    public static final String CONTACTS_COLUMN_DISTANCE = "distance";
    public static final String CONTACTS_COLUMN_LITRE = "litre";
    public static final String CONTACTS_COLUMN_AMOUNT = "amount";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("create table riderdetails "
                + "(id integer primary key AUTOINCREMENT, name text,bulltype TEXT,mileage TEXT,fuelcost TEXT,mailid TEXT)");
        db.execSQL("create table traveldetails "
                + "(id integer primary key AUTOINCREMENT,fromplace text,toplace TEXT,distance TEXT,litre TEXT,amount TEXT,url TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS riderdetails");
        db.execSQL("DROP TABLE IF EXISTS traveldetails");
        onCreate(db);
    }

    public boolean insertRiderDetail(LinkedHashMap<String, String> userDetails) {
        System.out.println("insert rider details method");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for (Map.Entry<String, String> entry : userDetails.entrySet()) {
            contentValues.put(entry.getKey(), entry.getValue());
        }
        db.insert("riderdetails", null, contentValues);
        return true;
    }

    public Cursor getUserData() {
        System.out.println("get rider Data method");
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from riderdetails", null);
        return res;
    }

    public Cursor getTravelData() {
        System.out.println("get travel Data method");
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from traveldetails", null);
        return res;
    }

    public int numberOfRows(String fromTable) {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db,
                fromTable);
        return numRows;
    }

    public boolean insertDistanceDetail(String fromPlace, String toPlace, String dist, String litre, String amount, String mapsUrl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fromplace", fromPlace);
        contentValues.put("toplace", toPlace);
        contentValues.put("distance", dist);
        contentValues.put("litre", litre);
        contentValues.put("amount", amount);
        contentValues.put("url", mapsUrl);
        db.insert("traveldetails", null, contentValues);
        return true;
    }

    public boolean updateRiderDetail(HashMap<String, String> key) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        // contentValues.put("fromplace", fromPlace);
        db.update("riderdetails", contentValues, "name = ? ",
                new String[]{key.toString()});
        return true;
    }


    public Integer deleteRiderDetail(String key) {
        System.out.println("Delete rider details method");
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("riderdetails", "name = ? ", new String[]{key.toString()});
    }

    public Integer deleteTravelDetails() {
        System.out.println("Delete travel details method");
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("traveldetails", null, null);
    }

    public Integer deleteAllDetails() {
        System.out.println("Delete all rider details method");
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("riderdetails", null, null);
    }


}
