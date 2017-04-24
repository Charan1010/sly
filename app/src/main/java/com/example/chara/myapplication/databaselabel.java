package com.example.chara.myapplication;

import android.provider.BaseColumns;

/**
 * Created by chara on 12-02-2017.
 */

public class databaselabel{
    private  databaselabel(){

    }
    public static abstract class dbs implements BaseColumns
    {
        public static final String NAME="name";
        public static final String PASWD="paswd";
        public static final String AGE="age";
        public static final String EMAIL="email";
        public static final String LATITUDE="latitude";
        public static final String LONGITUDE="longitude";
        public static final String SONGDBASE="songdbase";
        public static final String SONGTABLE="songtable";

    }
}
