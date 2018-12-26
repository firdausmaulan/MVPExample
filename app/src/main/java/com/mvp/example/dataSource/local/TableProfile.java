package com.mvp.example.dataSource.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.mvp.example.dataSource.local.DatabaseHelper;
import com.mvp.example.model.profile.Profile;

public class TableProfile extends DatabaseHelper {

    public TableProfile(Context context) {
        super(context);
    }

    public static final String TABLE_NAME = "t_profile";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATA = "data";
    private static final String COLUMN_TIMESTAMP = "timestamp";

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER,"
                    + COLUMN_DATA + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    public boolean insert(Profile profile) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, profile.getId());
        values.put(COLUMN_DATA, new Gson().toJson(profile));
        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id != -1;
    }

    public String selectWhereID(int id) {
        String profile = "";
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=" + id;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                profile = cursor.getString(cursor.getColumnIndex(COLUMN_DATA));
            }
            cursor.close();
        }
        db.close();
        return profile;
    }

    public int countByID(int id) {
        String query = "SELECT  * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=" + id;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public boolean delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        long deletedID = db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
        return deletedID != 0;
    }
}