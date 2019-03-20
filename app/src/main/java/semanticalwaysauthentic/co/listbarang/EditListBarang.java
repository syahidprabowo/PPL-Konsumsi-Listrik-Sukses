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

    private EditText mEditWordView;

    // Unique tag for the intent reply.
    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    int mId = MainActivity.WORD_ADD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_barang);

        mEditWordView = (EditText) findViewById(R.id.edit_word);

        // Get data sent from calling activity.
        Bundle extras = getIntent().getExtras();

        // If we are passed content, fill it in for the user to edit.
        if (extras != null) {
            int id = extras.getInt(ListBarangAdapter.EXTRA_ID, NO_ID);
            String word = extras.getString(ListBarangAdapter.EXTRA_WORD, NO_WORD);
            if (id != NO_ID && word != NO_WORD) {
                mId = id;
                mEditWordView.setText(word);
            }
        } // Otherwise, start with empty fields.
    }

    public void returnReply(View view) {
        String barang = ((EditText) findViewById(R.id.edit_word)).getText().toString();

        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, barang);
        replyIntent.putExtra(ListBarangAdapter.EXTRA_ID, mId);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}
