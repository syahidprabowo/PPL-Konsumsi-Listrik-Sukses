package semanticalwaysauthentic.co.listbarang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static semanticalwaysauthentic.co.listbarang.ListBarangOpenHelper.hargafixxx;

public class EditListBarang extends AppCompatActivity {

    private static final String TAG = EditListBarang.class.getSimpleName();

    private static final int NO_ID = -99;
    private static final String NO_WORD = "";
    private static final String NO_WATT = "";
    private static final String NO_DURASI = "";
    private static final String NO_JUM = "";

    private EditText mEditWordView;
    private EditText mEditWattView;
    private EditText mEditDurasiView;
    private EditText mEditJumlahView;

    // Unique tag for the intent reply.
    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    public static final String EXTRA_REPLY_WATT = "com.example.android.wordlistsql.REPLY2";
    public static final String EXTRA_REPLY_DURASI = "com.example.android.wordlistsql.REPLY3";
    public static final String EXTRA_REPLY_JUMLAH = "com.example.android.wordlistsql.REPLY4";

    int mId = MainActivity.WORD_ADD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_barang);

        mEditWordView = (EditText) findViewById(R.id.edit_word);
        mEditWattView = (EditText) findViewById(R.id.edit_watt);
        mEditDurasiView = (EditText) findViewById(R.id.edit_durasi);
        mEditJumlahView = (EditText) findViewById(R.id.edit_jumlah);

        // Get data sent from calling activity.
        Bundle extras = getIntent().getExtras();

        // If we are passed content, fill it in for the user to edit.
        if (extras != null) {
            int id = extras.getInt(ListBarangAdapter.EXTRA_ID, NO_ID);
            String word = extras.getString(ListBarangAdapter.EXTRA_WORD, NO_WORD);
            String watt = extras.getString(ListBarangAdapter.EXTRA_WATT, NO_WATT);
            String durasi = extras.getString(ListBarangAdapter.EXTRA_DURASI, NO_DURASI);
            String jumlah = extras.getString(ListBarangAdapter.EXTRA_JUMLAH, NO_JUM);
            if (id != NO_ID && word != NO_WORD ) {
                mId = id;
                mEditWordView.setText(word);
                mEditWattView.setText(watt);
                mEditDurasiView.setText(durasi);
                mEditJumlahView.setText(jumlah);
            }
        } // Otherwise, start with empty fields.
    }

    public void returnReply(View view) {
        String barang = ((EditText) findViewById(R.id.edit_word)).getText().toString();
        String watt = ((EditText) findViewById(R.id.edit_watt)).getText().toString();
        String durasi = ((EditText) findViewById(R.id.edit_durasi)).getText().toString();
        String jumlah = ((EditText) findViewById(R.id.edit_jumlah)).getText().toString();

        if (barang.isEmpty() || watt.isEmpty() || durasi.isEmpty() || jumlah.isEmpty()){
            Toast.makeText(getApplicationContext(), "Isi Semua Form "+ barang,
                    Toast.LENGTH_SHORT).show();
        }else {
            Intent replyIntent = new Intent();
            replyIntent.putExtra(EXTRA_REPLY, barang);
            replyIntent.putExtra(EXTRA_REPLY_WATT, watt);
            replyIntent.putExtra(EXTRA_REPLY_DURASI, durasi);
            replyIntent.putExtra(EXTRA_REPLY_JUMLAH, jumlah);
            replyIntent.putExtra(ListBarangAdapter.EXTRA_ID, mId);
            setResult(RESULT_OK, replyIntent);
            finish();
        }



    }


}
