package com.example.tabishhassan.sqliteusingloaders;

import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.android.sqliteusingLoader.data.Test;
import com.android.sqliteusingLoader.data.TestDataSource;
import com.android.sqliteusingloader.db.DbHelper;

import java.util.List;


public class MainActivity extends FragmentActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbHelper helper = new DbHelper(this);
        SQLiteDatabase database = helper.getWritableDatabase();
        TestDataSource dataSource = new TestDataSource(database);
        List list = dataSource.read();
        if(list == null || list.size() == 0){
            dataSource.insert(new Test("Tom"));
            dataSource.insert(new Test("Dick"));
            dataSource.insert(new Test("Harry"));

        }
        
        database.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
