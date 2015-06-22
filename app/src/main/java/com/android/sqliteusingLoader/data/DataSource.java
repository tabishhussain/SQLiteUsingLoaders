package com.android.sqliteusingLoader.data;

/**
 * Created by tabishhassan on 6/19/15.
 */
import java.util.List;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
public abstract class DataSource<T> {
    protected SQLiteDatabase mDatabase;
    public DataSource(SQLiteDatabase database) {
        mDatabase = database;
    }
    public abstract boolean insert(T entity);
    public abstract boolean delete(T entity);
    public abstract boolean update(T entity);
    public abstract List read();
    public abstract List read(String selection, String[] selectionArgs,
                              String groupBy, String having, String orderBy);
}
