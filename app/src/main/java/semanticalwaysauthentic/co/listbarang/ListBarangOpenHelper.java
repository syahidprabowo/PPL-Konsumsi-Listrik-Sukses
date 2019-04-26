package semanticalwaysauthentic.co.listbarang;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static semanticalwaysauthentic.co.listbarang.PilihKwh.SpinnerLabel;
import static semanticalwaysauthentic.co.listbarang.PilihKwh.hargafix;

public class ListBarangOpenHelper extends SQLiteOpenHelper {
    // It's a good idea to always define a log tag like this.
    private static final String TAG = ListBarangOpenHelper.class.getSimpleName();
    // has to be 1 first time or app will crash
    private static final int DATABASE_VERSION = 1;
    private static final String TABEL_BARANG = "ListBarang";
    private static final String TABEL_KWH = "KWH";
    private static final String DATABASE_NAME = "KonsumsiListrik";
    // Column names...
    public static final String KEY_ID = "_id";
    public static final String KEY_WORD = "barang";
    public static final String KEY_WATT = "watt";
    public static final String KEY_DURASI = "durasi";
    public static final String KEY_BIAYA = "biaya";
    //TABEL KWH
    public static final String ID_KWH = "id_kwh";
    public static final String JUMLAH = "total";
    public static final String HARGA = "harga";
    // ... and a string array of columns.
    private static final String[] COLUMNS = { KEY_ID, KEY_WORD, KEY_WATT,KEY_DURASI, KEY_BIAYA};

    private SQLiteDatabase mWritableDB;
    private SQLiteDatabase mReadableDB;
    Context Mcontext;

    // Create a container for the data.
    ContentValues values = new ContentValues();
    ContentValues values2 = new ContentValues();
    ContentValues values3 = new ContentValues();

    static Integer hargafixxx;
    static Integer HARGAFIX;


    // Build the SQL query that creates the table.
    private static final String BUAT_TABEL_BARANG =
            "CREATE TABLE " + TABEL_BARANG + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY, " +
// id will auto-increment if no value passed
                    KEY_WORD + " TEXT, "+ KEY_WATT + " INTEGER , "+ KEY_DURASI +
                    " INTEGER, " + KEY_BIAYA + " DOUBLE );";

    private static final String BUAT_TABEL_KWH =
            "CREATE TABLE " + TABEL_KWH + " ( " +
                    ID_KWH + " INTEGER PRIMARY KEY, " +
                    JUMLAH + " INTEGER, " + HARGA + " INTEGER);";

    private static final String BUAT_TABEL_TAMPUNG =
            "CREATE TABLE "+ TABEL_X + " ( " +
                    ID_TAM + " INTEGER PRIMARY KEY, " +
                    TAM + " INTEGER );";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BUAT_TABEL_BARANG);
        db.execSQL(BUAT_TABEL_KWH);
        db.execSQL(BUAT_TABEL_TAMPUNG);

        IsiTabelKWH(db);
        IsiTabelBarang(db);


    }

    public int getharga(){
        int harga;
        String query = "SELECT "+HARGA+" FROM "+ TABEL_KWH +" WHERE "+ TABEL_KWH + "." + JUMLAH +" = "+ hargafix;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        harga = cursor.getInt(cursor.getColumnIndex("harga"));
        cursor.close();
        db.close();
        return harga;
    }

    public void IsiTabelBarang(SQLiteDatabase db) {

        String[] nama_barang = {"AC 1 PK", "AC 1/2 PK", "AC 2 PK",
                "Lampu TL LED", "Komputer PC", "Proyektor",
                "Kipas Angin"};

        Integer[] watt_barang = {840, 1170, 1920, 16, 140, 205, 103};

        Integer[] durasi = {2, 1, 6, 12, 2, 5, 7};

        for (int i=0; i < nama_barang.length; i++) {
            // Put column/value pairs into the container.
            // put() overrides existing values.
            values.put(KEY_WORD, nama_barang[i]);
            values.put(KEY_WATT, watt_barang[i]);
            values.put(KEY_DURASI, durasi[i]);
            values.put(KEY_BIAYA, 0);
            db.insert(TABEL_BARANG, null, values);
        }
    }

    public void UpdateIsiTabelBarang() {


        String query = "SELECT * FROM " + TABEL_BARANG;
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteDatabase bd = this.getReadableDatabase();
        Cursor cursor = bd.rawQuery(query, null);
            if (cursor.moveToFirst()){
                do {
                    int id = cursor.getInt(cursor.getColumnIndex("_id"));
                    int watt = cursor.getInt(cursor.getColumnIndex("watt"));
                    int durasi = cursor.getInt(cursor.getColumnIndex("durasi"));
                    double harga = cursor.getDouble(cursor.getColumnIndex("biaya"));
                    double newharga = HitungBiaya(watt, durasi);
                    String upquery = "UPDATE "+TABEL_BARANG+" SET biaya = "+newharga+" WHERE _id = "+id;
                    db.execSQL(upquery);

                }while (cursor.moveToNext());
            }
            // Must close cursor and db now that we are done with it.
            cursor.close();
            db.close();

    }

    private void IsiTabelKWH(SQLiteDatabase db){
        Integer[] total_kwh = {900,1300};

        Integer[] harga = {1632, 2500};

        for (int i=0; i < total_kwh.length; i++){
            values2.put(JUMLAH, total_kwh[i]);
            values2.put(HARGA, harga[i]);
            db.insert(TABEL_KWH, null, values2);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(ListBarangOpenHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_BARANG);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_KWH);
        onCreate(db);

    }

    public ListBarangOpenHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    public itembarang query(int position){
        String query = "SELECT  * FROM " + TABEL_BARANG +
                " ORDER BY " + KEY_ID + " ASC " +
                "LIMIT " + position + ",1";

        Cursor cursor = null;
        itembarang entry = new itembarang();
        try {
            if (mReadableDB == null) {mReadableDB = getReadableDatabase();}
            cursor = mReadableDB.rawQuery(query, null);
            cursor.moveToFirst();
            entry.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            entry.setWord(cursor.getString(cursor.getColumnIndex(KEY_WORD)));
            entry.setWatt(cursor.getInt(cursor.getColumnIndex(KEY_WATT)));
            entry.setmDurasi(cursor.getInt(cursor.getColumnIndex(KEY_DURASI)));
            entry.setmBiaya(cursor.getInt(cursor.getColumnIndex(KEY_BIAYA)));

        }catch (Exception e){

            Log.d(TAG, "QUERY EXCEPTION! " + e.getMessage());

        }finally {

            // Must close cursor and db now that we are done with it.
            cursor.close();
            return entry;

        }
    }



    public long insert(String barang, int watt, int durasi){
        long newId = 0;
        ContentValues values = new ContentValues();
        values.put(KEY_WORD, barang);
        values.put(KEY_WATT, watt);
        values.put(KEY_DURASI, durasi);
        values.put(KEY_BIAYA, HitungBiaya(durasi, watt));
        try{
            if (mWritableDB == null){
                mWritableDB = getWritableDatabase();
            }
            newId = mWritableDB.insert(TABEL_BARANG, null, values);
        }catch (Exception e){
            Log.d(TAG, "GAGAL INSERT !" + e.getMessage());
        }
        return newId;
    }




    public long count() {
        if (mReadableDB == null) {
            mReadableDB = getReadableDatabase();
        }
        return DatabaseUtils.queryNumEntries(mReadableDB, TABEL_BARANG);
    }

    public int delete(int id){
        int deleted = 0;
        try{
            if (mWritableDB == null){
                mWritableDB = getWritableDatabase();
            }
            deleted = mWritableDB.delete(TABEL_BARANG, //nama barang
            KEY_ID + "=?", new String[]{String.valueOf(id)});
        }catch (Exception e){
            Log.d(TAG, "DELETE EXCEPTION"+e.getMessage());
        }
        return deleted;
    }

    public int update(int id, String word, int watt, int durasi) {
        int mNumberOfRowsUpdated = -1;
        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            ContentValues values = new ContentValues();
            values.put(KEY_WORD, word);
            values.put(KEY_WATT, watt);
            values.put(KEY_DURASI, durasi);
            values.put(KEY_BIAYA, HitungBiaya(durasi, watt));
            mNumberOfRowsUpdated = mWritableDB.update(TABEL_BARANG,
                    values,
                    KEY_ID + " = ?",
                    new String[]{String.valueOf(id)});
        } catch (Exception e) {
            Log.d (TAG, "UPDATE EXCEPTION! " + e.getMessage());
        }
        return mNumberOfRowsUpdated;
    }



    private double HitungBiaya(int durasi, int watt){

        int harga = hargafixxx;
        double total;

        total = ((watt*0.001) * harga) * durasi;

        return total;

    }



    public List<String> getAllLabels(){
        List<String>labels = new ArrayList<String>();

        String selectQuery = "SELECT * FROM "+ TABEL_KWH;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                labels.add(cursor.getString(cursor.getColumnIndex("total")));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }

    public double gettotalharga(){
        double total = 0;
        double hitung = 0;

        String query = "SELECT "+KEY_BIAYA+" FROM "+ TABEL_BARANG;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do {
                hitung = cursor.getDouble(cursor.getColumnIndex(KEY_BIAYA));
                total = total + hitung;
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return total;
    }


}
