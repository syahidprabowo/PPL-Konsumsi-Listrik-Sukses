package semanticalwaysauthentic.co.listbarang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EditListBarang extends AppCompatActivity {

    private static final String TAG = EditListBarang.class.getSimpleName();

    private static final int NO_ID = -99;
    private static final String NO_WORD = "";
    private static final String NO_WATT = "";
    private static final String NO_DURASI = "";

    private EditText mEditWordView;
    private EditText mEditWattView;
    private EditText mEditDurasiView;

    // Unique tag for the intent reply.
    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    public static final String EXTRA_REPLY_WATT = "com.example.android.wordlistsql.REPLY2";
    public static final String EXTRA_REPLY_DURASI = "com.example.android.wordlistsql.REPLY3";

    int mId = MainActivity.WORD_ADD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_barang);

        mEditWordView = (EditText) findViewById(R.id.edit_word);
        mEditWattView = (EditText) findViewById(R.id.edit_watt);
        mEditDurasiView = (EditText) findViewById(R.id.edit_durasi);

        // Get data sent from calling activity.
        Bundle extras = getIntent().getExtras();

        // If we are passed content, fill it in for the user to edit.
        if (extras != null) {
            int id = extras.getInt(ListBarangAdapter.EXTRA_ID, NO_ID);
            String word = extras.getString(ListBarangAdapter.EXTRA_WORD, NO_WORD);
            String watt = extras.getString(ListBarangAdapter.EXTRA_WATT, NO_WATT);
            String durasi = extras.getString(ListBarangAdapter.EXTRA_DURASI, NO_DURASI);
            if (id != NO_ID && word != NO_WORD ) {
                mId = id;
                mEditWordView.setText(word);
                mEditWattView.setText(watt);
                mEditDurasiView.setText(durasi);
            }
        } // Otherwise, start with empty fields.
    }

    public void returnReply(View view) {
        String barang = ((EditText) findViewById(R.id.edit_word)).getText().toString();
        String watt = ((EditText) findViewById(R.id.edit_watt)).getText().toString();
        String durasi = ((EditText) findViewById(R.id.edit_durasi)).getText().toString();

        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, barang);
        replyIntent.putExtra(EXTRA_REPLY_WATT, watt);
        replyIntent.putExtra(EXTRA_REPLY_DURASI, durasi);
        replyIntent.putExtra(ListBarangAdapter.EXTRA_ID, mId);
        setResult(RESULT_OK, replyIntent);
        finish();
    }


}
