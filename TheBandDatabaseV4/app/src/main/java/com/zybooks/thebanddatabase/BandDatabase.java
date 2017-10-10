package com.zybooks.thebanddatabase;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

public class BandDatabase {

    private static BandDatabase sBandDatabase;
    private List<Band> mBands;
    private BandDatabaseHelper mDbHelper;

    public static BandDatabase get(Context context) {
        if (sBandDatabase == null) {
            sBandDatabase = new BandDatabase(context);
        }
        return sBandDatabase;
    }

    private BandDatabase(Context context) {
        mDbHelper = new BandDatabaseHelper(context.getApplicationContext());
    }

    public List<Band> getBands() {
        if(mBands == null){
            mBands = mDbHelper.getBands();
        }
        return mBands;
    }

    public Band getBand(int bandId) {
        for (Band band : mBands) {
            if (band.getId() == bandId) {
                return band;
            }
        }
        return null;
    }

    public void updateRating(){
        //TODO: implement updating the DB
    }
}
