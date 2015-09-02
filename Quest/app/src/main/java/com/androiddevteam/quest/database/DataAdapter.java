package com.androiddevteam.quest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;

public class DataAdapter
{
    protected static final String TAG = "DataAdapter";

    public static final String KEY_ID = "_id";

    public static final String QUEST_NAME_STRING = "name";
    public static final String QUEST_AVATAR_BLOB = "avatar";
    public static final String QUEST_TIME_STRING = "time";
    public static final String QUEST_DATE_STRING = "date";
    public static final String QUEST_CREATOR_STRING = "creator";
    public static final String QUEST_PRIZE_STRING = "prize";

    private static final String QUEST_ITEMS_NAME_TABLE = "quest_items";

    private SQLiteDatabase database;
    private DataBaseHelper dataBaseHelper;

    public DataAdapter(Context context) {
        dataBaseHelper = new DataBaseHelper(context);
    }

    public DataAdapter createDatabase() throws SQLException
    {
        try {
            dataBaseHelper.createDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public DataAdapter clearDatabase() throws SQLException
    {
        try {
            dataBaseHelper.clearDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToClearDatabase");
        }
        return this;
    }

    public boolean isDatabaseOpen(){
        return dataBaseHelper.isDatabaseOpen();
    }

    public DataAdapter open() throws SQLException
    {
        dataBaseHelper.openDataBase();
//        dataBaseHelper.close();
        database = dataBaseHelper.getWritableDatabase();

        return this;
    }

    public void close()
    {
        dataBaseHelper.close();
    }

    public void insert(String tableName, ContentValues contentValues)throws Exception{
        database.insertOrThrow(tableName, null, contentValues);
    }

    public void update(String tableName, ContentValues contentValues, int id) throws Exception{
        database.update(tableName, contentValues, "_id =" + id, null);
    }

    public Cursor getRowByNumber(String tableName, int rowNumber){
        return database.rawQuery("SELECT FROM " + tableName + "LIMIT " + rowNumber + ", 1", null);
    }

    public Cursor getQuests() {
        String selectQuery = "SELECT * FROM " + QUEST_ITEMS_NAME_TABLE;

        return database.rawQuery(selectQuery, null);
    }

    public Cursor getQuestItem(int id) {
        String selectQuery = "SELECT * FROM " + QUEST_ITEMS_NAME_TABLE + " WHERE _id=" + id;

        Cursor cursor = database.rawQuery(selectQuery, null);
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
            }
        return cursor;
    }

    public boolean deleteQuest(int id) {
        try {
            return  database.delete(QUEST_ITEMS_NAME_TABLE, KEY_ID + "=" + id, null) > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
