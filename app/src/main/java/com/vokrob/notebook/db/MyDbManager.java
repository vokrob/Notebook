package com.vokrob.notebook.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vokrob.notebook.adapter.ListItem;

import java.util.ArrayList;
import java.util.List;

public class MyDbManager {
    private Context context;
    private MyDbHelper myDbHelper;
    private SQLiteDatabase db;

    public MyDbManager(Context context) {
        this.context = context;
        myDbHelper = new MyDbHelper(context);
    }
    public void openDb() {
        db = myDbHelper.getWritableDatabase();
    }
    public void insertToDb(String title, String desc, String uri) {
        ContentValues cv = new ContentValues();
        cv.put(MyConstants.TITLE, title);
        cv.put(MyConstants.DESC, desc);
        cv.put(MyConstants.URI, uri);
        db.insert(MyConstants.TABLE_NAME, null, cv);
    }
    public List<ListItem> getFromDb() {
        List<ListItem> tempList = new ArrayList<>();
        Cursor cursor = db.query(MyConstants.TABLE_NAME, null, null,
                null, null, null, null);
        while (cursor.moveToNext()) {
            ListItem item = new ListItem();
            String title = cursor.getString(cursor.getColumnIndex(MyConstants.TITLE));
            String desc = cursor.getString(cursor.getColumnIndex(MyConstants.DESC));
            String uri = cursor.getString(cursor.getColumnIndex(MyConstants.URI));
            item.setTitle(title);
            item.setDesc(desc);
            item.setUri(uri);
            tempList.add(item);
        }
        cursor.close();
        return tempList;
    }
    public void closeDb() {
        myDbHelper.close();
    }
}





























