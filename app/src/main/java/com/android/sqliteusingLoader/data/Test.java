package com.android.sqliteusingLoader.data;

/**
 * Created by tabishhassan on 6/19/15.
 */
public class Test {
    private int id;
    private String name;

    public Test(){}

    public Test(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}