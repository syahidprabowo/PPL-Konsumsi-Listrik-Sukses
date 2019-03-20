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
    private static final String WORD_LIST_TABLE = "Nama_Barang";
    private static final String DATABASE_NAME = "ListBarang";
    // Column names...
    public static final String KEY_ID = "_id";
    public static final String KEY_WORD = "word";
    // ... and a string array of columns.
    private static final String[] COLUMNS = { KEY_ID, KEY_WORD };

    private SQLiteDatabase mWritableDB;
    private SQLiteDatabase mReadableDB;

    // Create a container for the data.
    ContentValues values = new ContentValues();

    // Build the SQL query that creates the table.
    private static final String WORD_LIST_TABLE_CREATE =
            "CREATE TABLE " + WORD_LIST_TABLE + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY, " +
// id will auto-increment if no value passed
                    KEY_WORD + " TEXT );";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(WORD_LIST_TABLE_CREATE);
        fillDatabaseWithData(db);
    }

    private void fillDatabaseWithData(SQLiteDatabase db) {
        String[] words = {"Kipas Angin", "Air Conditioner", "Proyektor",
                "Lampu","Laptop", "Komputer", "Televisi",
                "Router", "Sound System","Lift",
                "Layar LCD"};

        for (int i=0; i < words.length; i++) {
            // Put column/value pairs into the container.
            // put() overrides existing values.
            values.put(KEY_WORD, words[i]);
            db.insert(WORD_LIST_TABLE, null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(ListBarangOpenHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + WORD_LIST_TABLE);
        onCreate(db);

    }

    public ListBarangOpenHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public itembarang query(int position){
        String query = "SELECT  * FROM " + WORD_LIST_TABLE +
                " ORDER BY " + KEY_WORD + " ASC " +
                "LIMIT " + position + ",1";

        Cursor cursor = null;
        itembarang entry = new itembarang();
        try {
            if (mReadableDB == null) {mReadableDB = getReadableDatabase();}
            cursor = mReadableDB.rawQuery(query, null);
            cursor.moveToFirst();
            entry.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            entry.setWord(cursor.getString(cursor.getColumnIndex(KEY_WORD)));

        }catch (Exception e){

            Log.d(TAG, "QUERY EXCEPTION! " + e.getMessage());

        }finally {

            // Must close cursor and db now that we are done with it.
            cursor.close();
            return entry;

        }
    }

    public long insert(String barang){
        long newId = 0;
        ContentValues values = new ContentValues();
        values.put(KEY_WORD, barang);
        try{
            if (mWritableDB == null){
                mWritableDB = getWritableDatabase();
            }
            newId = mWritableDB.insert(WORD_LIST_TABLE, null, values);
        }catch (Exception e){
            Log.d(TAG, "GAGAL INSERT !" + e.getMessage());
        }
        return newId;
    }

    public long count() {
        if (mReadableDB == null) {
            mReadableDB = getReadableDatabase();
        }
        return DatabaseUtils.queryNumEntries(mReadableDB, WORD_LIST_TABLE);
    }

    public int delete(int id){
        int deleted = 0;
        try{
            if (mWritableDB == null){
                mWritableDB = getWritableDatabase();
            }
            deleted = mWritableDB.delete(WORD_LIST_TABLE, //nama barang
            KEY_ID + "=?", new String[]{String.valueOf(id)});
        }catch (Exception e){
            Log.d(TAG, "DELETE EXCEPTION"+e.getMessage());
        }
        return deleted;
    }

    public int update(int id, String word) {
        int mNumberOfRowsUpdated = -1;
        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            ContentValues values = new ContentValues();
            values.put(KEY_WORD, word);
            mNumberOfRowsUpdated = mWritableDB.update(WORD_LIST_TABLE,
                    values,
                    KEY_ID + " = ?",
                    new String[]{String.valueOf(id)});
        } catch (Exception e) {
            Log.d (TAG, "UPDATE EXCEPTION! " + e.getMessage());
        }
        return mNumberOfRowsUpdated;
    }

}
