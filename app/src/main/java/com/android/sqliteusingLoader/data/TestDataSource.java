package com.android.sqliteusingLoader.data;

/**
 * Created by tabishhassan on 6/19/15.
 */
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
public class TestDataSource extends DataSource<Test> {
    public static final String TABLE_NAME = "test";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    // Database creation sql statement
    public static final String CREATE_COMMAND = "create table " + TABLE_NAME
            + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null);";
    public TestDataSource(SQLiteDatabase database) {
        super(database);
        // TODO Auto-generated constructor stub
    }
    @Override
    public boolean insert(Test entity) {
        if (entity == null) {
            return false;
        }
        long result = mDatabase.insert(TABLE_NAME, null,
                generateContentValuesFromObject(entity));
        return result != -1;
    }
    @Override
    public boolean delete(Test entity) {
        if (entity == null) {
            return false;
        }
        int result = mDatabase.delete(TABLE_NAME,
                COLUMN_ID + " = " + entity.getId(), null);
        return result != 0;
    }
    @Override
    public boolean update(Test entity) {
        if (entity == null) {
            return false;
        }
        int result = mDatabase.update(TABLE_NAME,
                generateContentValuesFromObject(entity), COLUMN_ID + " = "
                        + entity.getId(), null);
        return result != 0;
    }
    @Override
    public List read() {
        Cursor cursor = mDatabase.query(TABLE_NAME, getAllColumns(), null,
                null, null, null, null);
        List tests = new ArrayList();
        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                tests.add(generateObjectFromCursor(cursor));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return tests;
    }
    @Override
    public List read(String selection, String[] selectionArgs,
                     String groupBy, String having, String orderBy) {
        Cursor cursor = mDatabase.query(TABLE_NAME, getAllColumns(), selection, selectionArgs, groupBy, having, orderBy);
        List tests = new ArrayList();
        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                tests.add(generateObjectFromCursor(cursor));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return tests;
    }
    public String[] getAllColumns() {
        return new String[] { COLUMN_ID, COLUMN_NAME };
    }
    public Test generateObjectFromCursor(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        Test test = new Test();
        test.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
        test.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
        return test;
    }
    public ContentValues generateContentValuesFromObject(Test entity) {
        if (entity == null) {
            return null;
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, entity.getName());
        return values;
    }
}