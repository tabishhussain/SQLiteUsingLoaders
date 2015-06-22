package com.example.tabishhassan.sqliteusingloaders;

/**
 * Created by tabishhassan on 6/19/15.
 */
import java.util.List;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.android.sqliteusingLoader.data.Test;
import com.android.sqliteusingLoader.data.TestDataSource;
import com.android.sqliteusingloader.db.DbHelper;
import com.android.sqliteusingloader.db.SQLiteTestDataLoader;

public class CustomLoaderExampleListFragment extends ListFragment  implements
        LoaderManager.LoaderCallbacks<List<Test>>{
    private ArrayAdapter mAdapter;
    // The Loader's id (this id is specific to the ListFragment's LoaderManager)
    private static final int LOADER_ID = 1;
    private  static final boolean DEBUG = true;
    private static final String TAG = "SqliteUsingLoader";
    private SQLiteDatabase mDatabase;
    private TestDataSource mDataSource;
    private DbHelper mDbHelper;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        //setHasOptionsMenu(true);
        mDbHelper = new DbHelper(getActivity());
        mDatabase = mDbHelper.getWritableDatabase();
        mDataSource = new TestDataSource(mDatabase);
        mAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1);
        setEmptyText("No data, please add from menu.");
        setListAdapter(mAdapter);
        setListShown(false);
        if (DEBUG) {
            Log.i(TAG, "+++ Calling initLoader()! +++");
            if (getLoaderManager().getLoader(LOADER_ID) == null) {
                Log.i(TAG, "+++ Initializing the new Loader... +++");
            } else {
                Log.i(TAG, "+++ Reconnecting with existing Loader (id '1')... +++");
            }
        }
        // Initialize a Loader with id '1'. If the Loader with this id already
        // exists, then the LoaderManager will reuse the existing Loader.
        getLoaderManager().initLoader(LOADER_ID, null, this);
    }
    @Override
    public Loader<List<Test>> onCreateLoader(int id, Bundle args) {
        SQLiteTestDataLoader loader = new SQLiteTestDataLoader(getActivity(), mDataSource, null, null, null, null, null);
        return loader;
    }
    @Override
    public void onLoadFinished(Loader<List<Test>> loader, List<Test> data) {
        if (DEBUG) Log.i(TAG, "+++ onLoadFinished() called! +++");
        mAdapter.clear();
        for(Test test : data){
            mAdapter.add(test);
        }
        if (isResumed()) {
            setListShown(true);
        } else {
            setListShownNoAnimation(true);
        }
    }
    @Override
    public void onLoaderReset(Loader<List<Test>> arg0) {
        mAdapter.clear();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mDbHelper.close();
        mDatabase.close();
        mDataSource = null;
        mDbHelper = null;
        mDatabase = null;
    }
}
