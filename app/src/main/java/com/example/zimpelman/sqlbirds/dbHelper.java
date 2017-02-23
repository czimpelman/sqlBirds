package com.example.zimpelman.sqlbirds;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christopher on 2/21/2017.
 */

public class dbHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "birdsDatabase";
    private static final String BIRDS_TABLE = "birdsTable";

    //column names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESC = "description";

    public dbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + BIRDS_TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_NAME + " TEXT,"
                + KEY_DESC + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BIRDS_TABLE);
        onCreate(db);
    }

    public void deleteDB(Context context){
        context.deleteDatabase(DB_NAME);
    }

    //CRUD

    //Add new bird
    void addBird(Bird bird){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, bird.getName());
        values.put(KEY_DESC, bird.getDescription());

        db.insert(BIRDS_TABLE, null, values);
        db.close();
    }

    // Getting single contact
    Bird getBird(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(BIRDS_TABLE, new String[] { KEY_ID,
                        KEY_NAME, KEY_DESC }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Bird bird = new Bird(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return bird;
    }

    public List<Bird> getAllBirds() {
        List<Bird> birdList = new ArrayList<Bird>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + BIRDS_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Bird bird = new Bird();
                bird.setId(Integer.parseInt(cursor.getString(0)));
                bird.setName(cursor.getString(1));
                bird.setDescription(cursor.getString(2));
                // Adding bird to list
                birdList.add(bird);
            } while (cursor.moveToNext());
        }

        // return bird list
        return birdList;
    }

    // Updating single contact
    public int updateBird(Bird bird) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, bird.getName());
        values.put(KEY_DESC, bird.getDescription());

        // updating row
        return db.update(BIRDS_TABLE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(bird.getId()) });
    }

    // Deleting single contact
    public void deleteBird(Bird bird) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(BIRDS_TABLE, KEY_ID + " = ?",
                new String[] { String.valueOf(bird.getId()) });
        db.close();
    }

    // Getting contacts Count
    public int getBirdsCount() {
        String countQuery = "SELECT  * FROM " + BIRDS_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }
}
