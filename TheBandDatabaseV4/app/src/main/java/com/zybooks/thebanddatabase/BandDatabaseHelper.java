package com.zybooks.thebanddatabase;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BandDatabaseHelper extends SQLiteOpenHelper {

    private Context mContext;

    private static final class Constants{
        private static final String TABLE = "bands";
        private static final String COL_ID = "_id";
        private static final String COL_NAME = "name";
        private static final String COL_DESC = "desc";
        private static final String COL_GENRE = "genre";
        private static final String COL_RATING = "rating";
    }

    public BandDatabaseHelper(Context context) {
        super(context, "Bands.db", null, 1);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + Constants.TABLE + " (" + Constants.COL_ID + " integer primary key autoincrement, " +
                Constants.COL_NAME + ", " + Constants.COL_DESC + ", " + Constants.COL_GENRE + ", " + Constants.COL_RATING + " decimal)");

        Resources res = mContext.getResources();
        String[] bands = res.getStringArray(R.array.bands);
        String[] descriptions = res.getStringArray(R.array.descriptions);
        String[] genre = res.getStringArray(R.array.genre);

        for(int i = 0; i < bands.length; i++){
            Band band = new Band();
            band.setName(bands[i]);
            band.setDescription(descriptions[i]);
            band.setGenre(genre[i]);
            band.setRating(-1);
            addBand(sqLiteDatabase, band);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table " + Constants.TABLE);
        onCreate(sqLiteDatabase);
    }

    public void addBand(SQLiteDatabase database, Band band){
        //Insert band into the bands table
        ContentValues values = new ContentValues();
        values.put(Constants.COL_NAME, band.getName());
        values.put(Constants.COL_DESC, band.getDescription());
        values.put(Constants.COL_GENRE, band.getGenre());
        values.put(Constants.COL_RATING, band.getRating());
        database.insert(Constants.TABLE,null,values);
    }

    public void updateBand(Band band){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.COL_NAME, band.getName());
        values.put(Constants.COL_DESC, band.getDescription());
        values.put(Constants.COL_GENRE, band.getGenre());
        values.put(Constants.COL_RATING, band.getRating());
        db.update(Constants.TABLE, values, Constants.COL_ID + " == ?",
                new String[] { "" + band.getId() });
    }

    public List<Band> getBands(){
        String query = "select * from " + Constants.TABLE;

        List<Band> bands = new ArrayList<Band>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Band band = new Band();
                band.setId(cursor.getInt(0));
                band.setName(cursor.getString(1));
                band.setDescription(cursor.getString(2));
                band.setGenre(cursor.getString(3));
                band.setRating(cursor.getFloat(4));
                bands.add(band);
            }while(cursor.moveToNext());
        }

        return bands;
    }
}
