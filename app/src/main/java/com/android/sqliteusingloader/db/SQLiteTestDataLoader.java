package com.android.sqliteusingloader.db;

/**
 * Created by tabishhassan on 6/19/15.
 */
import java.util.List;

import android.content.Context;

import com.example.tabishhassan.sqliteusingloaders.AbstractDataLoader;
import com.android.sqliteusingLoader.data.DataSource;
import com.android.sqliteusingLoader.data.Test;


public class SQLiteTestDataLoader extends AbstractDataLoader<List<Test>> {
    private DataSource<Test> mDataSource;
    private String mSelection;
    private String[] mSelectionArgs;
    private String mGroupBy;
    private String mHaving;
    private String mOrderBy;

    public SQLiteTestDataLoader(Context context, DataSource dataSource, String selection, String[] selectionArgs,
                                String groupBy, String having, String orderBy) {
        super(context);
        mDataSource = dataSource;
        mSelection = selection;
        mSelectionArgs = selectionArgs;
        mGroupBy = groupBy;
        mHaving = having;
        mOrderBy = orderBy;
    }

    @Override
    protected List<Test> buildList() {
        List<Test> testList = mDataSource.read(mSelection, mSelectionArgs, mGroupBy, mHaving,
                mOrderBy);
        return testList;
    }

    public void insert(Test entity) {
        new InsertTask(this).execute(entity);
    }

    public void update(Test entity) {
        new UpdateTask(this).execute(entity);
    }

    public void delete(Test entity) {
        new DeleteTask(this).execute(entity);
    }

    private class InsertTask extends ContentChangingTask<Test, Void, Void> {
        InsertTask(SQLiteTestDataLoader loader) {
            super(loader);
        }

        @Override
        protected Void doInBackground(Test... params) {
            mDataSource.insert(params[0]);
            return (null);
        }
    }

    private class UpdateTask extends ContentChangingTask<Test, Void, Void> {
        UpdateTask(SQLiteTestDataLoader loader) {
            super(loader);
        }

        @Override
        protected Void doInBackground(Test... params) {
            mDataSource.update(params[0]);
            return (null);
        }
    }

    private class DeleteTask extends ContentChangingTask<Test, Void, Void> {
        DeleteTask(SQLiteTestDataLoader loader) {
            super(loader);
        }

        @Override
        protected Void doInBackground(Test... params) {
            mDataSource.delete(params[0]);
            return (null);
        }
    }
}

