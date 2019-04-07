package semanticalwaysauthentic.co.listbarang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ListBarangOpenHelper extends SQLiteOpenHelper {
    // It's a good idea to always define a log tag like this.
    private static final String TAG = ListBarangOpenHelper.class.getSimpleName();
    // has to be 1 first time or app will crash
    private static final int DATABASE_VERSION = 1;
    private static final String TABEL_BARANG = "Nama_Barang";
    private static final String DATABASE_NAME = "ListBarang";
    // Column names...
    public static final String KEY_ID = "_id";
    public static final String KEY_WORD = "barang";
    public static final String KEY_WATT = "watt";
    public static final String KEY_DURASI = "durasi";
    public static final String KEY_BIAYA = "biaya";
    // ... and a string array of columns.
    private static final String[] COLUMNS = { KEY_ID, KEY_WORD, KEY_WATT,KEY_DURASI, KEY_BIAYA};

    private SQLiteDatabase mWritableDB;
    private SQLiteDatabase mReadableDB;

    // Create a container for the data.
    ContentValues values = new ContentValues();

    // Build the SQL query that creates the table.
    private static final String WORD_LIST_TABLE_CREATE =
            "CREATE TABLE " + TABEL_BARANG + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY, " +
// id will auto-increment if no value passed
                    KEY_WORD + " TEXT, "+ KEY_WATT + " INTEGER , "+ KEY_DURASI +
                    " INTEGER," + KEY_BIAYA + " FLOAT );";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(WORD_LIST_TABLE_CREATE);
        fillDatabaseWithData(db);
    }

    private void fillDatabaseWithData(SQLiteDatabase db) {
        String[] nama_barang = {"AC 1 PK", "AC 1/2 PK", "AC 2 PK",
                "Lampu TL LED", "Komputer PC", "Proyektor",
                "Kipas Angin"};

        Integer[] watt_barang = {840, 1170, 1920, 16, 140, 205, 103};

        Integer[] durasi = {2, 1, 6, 12, 2, 5, 7};

        for (int i=0; i < nama_barang.length; i++) {
            // Put column/value pairs into the container.
            // put() overrides existing values.
            double biaya = HitungBiaya(durasi[i], watt_barang[i]);
            values.put(KEY_WORD, nama_barang[i]);
            values.put(KEY_WATT, watt_barang[i]);
            values.put(KEY_DURASI, durasi[i]);
            values.put(KEY_BIAYA, biaya);
            db.insert(TABEL_BARANG, null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(ListBarangOpenHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_BARANG);
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

        double biaya;
        biaya =((watt*0.001) * 1632) * durasi;

        return biaya;
    }

}
