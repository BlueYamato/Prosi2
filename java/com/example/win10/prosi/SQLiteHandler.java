package com.example.win10.prosi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

public class SQLiteHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "pengajar.db";

    private static final String TABLE_CONTACTS = "Pengajar";
    private static final String KEY_ID = "ID_Pengajar" ;
    private static final String KEY_NAME = "nama";
    // private static final String KEY_IMAGE = "gambar" ;
    private static final String KEY_STUDY = "pelajaran";
    //private static final String KEY_NOTE = "note";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_IMAGE_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("

                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_STUDY + " TEXT"+ ")";
        db.execSQL(CREATE_IMAGE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void addImageData(Pengajar pengajar) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, pengajar.getNama());
        values.put(KEY_STUDY, pengajar.getPelajaran());

        db.insert(TABLE_CONTACTS, null, values);

        db.close();

    }

    public Pengajar getPengajar(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID, KEY_NAME,KEY_STUDY}, KEY_ID + "=?",

                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null)

            cursor.moveToFirst();

        Pengajar pengajar = new Pengajar(cursor.getString(1),cursor.getString(2));
        return pengajar;

    }

    public List<Pengajar> getAllPengajar() {

        List<Pengajar> imageList = new LinkedList<Pengajar>();

        String selectQuery = "SELECT * FROM "+TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                Pengajar pengajar = new Pengajar(cursor.getString(1)
                        ,cursor.getString(2));

                imageList.add(pengajar);

            } while (cursor.moveToNext());

        }

        db.close();
        return imageList;
    }

    public List<Pengajar> getAllPengajar(String pelajaran) {

        List<Pengajar> imageList = new LinkedList<Pengajar>();

        String selectQuery = "SELECT * FROM "+TABLE_CONTACTS+" WHERE "+KEY_STUDY+" = '" + pelajaran +"'";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                Pengajar pengajar = new Pengajar(cursor.getString(1)
                        ,cursor.getString(2));

                imageList.add(pengajar);

            } while (cursor.moveToNext());

        }

        db.close();
        return imageList;
    }
  /*  public int updateImageData(Pengajar pengajar,String judulLama) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, image.getJudul());
        values.put(KEY_NOTE, image.getNote());
        return db.update(TABLE_CONTACTS, values, KEY_PATH + " = ?",
                new String[] { image.getPath() });
    }*/

    public void deletePengajar(String nama) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_CONTACTS, KEY_NAME + " = ?",
                new String[] { nama });
        db.close();

    }

    public int getDataPengajar() {

        String countQuery = "SELECT * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        cursor.close();

        return cursor.getCount();

    }

   /* public boolean checkIfPathExistInDB(String path){
        String query = "SELECT * FROM "+TABLE_CONTACTS+" WHERE path = '" + path + "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        return cursor.getCount() > 0;
    }*/
}