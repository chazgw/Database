package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import com.example.database.FeedReaderContract.FeedEntry;

import androidx.annotation.Nullable;

public class FeedReaderDbHelper extends SQLiteOpenHelper {


    public FeedReaderDbHelper(@Nullable Context context,
                              @Nullable String name,  // name of DB
                              @Nullable SQLiteDatabase.CursorFactory factory,  // only if we write our own cursor class
                              int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FeedReaderContract.FeedEntry.SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        // Same as tutorial
        db.execSQL(FeedEntry.SQL_DELETE_ENTRIES);
        onCreate(db);

    }

    // Put information into a database
    // https://developer.android.com/training/data-storage/sqlite#WriteDbRow
    public long insert(String title, String yearMade) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, title);
        contentValues.put(FeedEntry.COLUMN_NAME_YEAR_MADE, yearMade);

        // Insert the new row, returning the primary key value of the new row
        return db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, contentValues);

    }


    // Read information from a database
    // https://developer.android.com/training/data-storage/sqlite#ReadDbRow
    // This section also contains a WHERE clause
    public Cursor getData() {

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + FeedReaderContract.FeedEntry.TABLE_NAME;
        Cursor data = db.rawQuery(query, null);

        // More args, but use query over rawQuery()
        //data = db.query(FeedReaderContract.FeedEntry.TABLE_NAME,)
        return data;  // gives an entire result set;
        // we will call the Cursor's various methods from inside of
        // whatever class uses this result set

    }

}
