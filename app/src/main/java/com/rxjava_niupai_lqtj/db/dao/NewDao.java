package com.rxjava_niupai_lqtj.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rxjava_niupai_lqtj.db.DBConstant;
import com.rxjava_niupai_lqtj.db.DBOpenHelper;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by laucherish on 16/3/14.
 */
public class NewDao {
    private DBOpenHelper helper;

    public NewDao(Context context) {
        helper = DBOpenHelper.getInstance(context);
    }

    public void insertReadNew(String id){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBConstant.COLUMN_ID,id);
        long insertNum = db.insert(DBConstant.TABLE_READ, null, cv);
    }

    public List<String> getAllReadNew(){
        List<String> list = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        String sqlQuery = "select * from "+DBConstant.TABLE_READ;
        Cursor cursor = db.rawQuery(sqlQuery, null);
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                list.add(id);
            } while (cursor.moveToNext());
        }
        return list;
    }
}
