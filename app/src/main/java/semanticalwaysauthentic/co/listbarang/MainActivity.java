package semanticalwaysauthentic.co.listbarang;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final int WORD_EDIT = 1;
    public static final int WORD_ADD = -1;
    private RecyclerView mRecyclerView;
    private ListBarangAdapter mAdapter;
    private ListBarangOpenHelper mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDB = new ListBarangOpenHelper(this);
        // Create recycler view.
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        // Create an mAdapter and supply the data to be displayed.
        mAdapter = new ListBarangAdapter(this, mDB);
        // Connect the mAdapter with the recycler view.
        mRecyclerView.setAdapter(mAdapter);
        // Give the recycler view a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayToast(getString(R.string.tambah_barang));
                Intent intent = new Intent(getBaseContext(), EditListBarang.class);
                startActivityForResult(intent, WORD_EDIT);
            }
        });
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == WORD_EDIT) {
            if (resultCode == RESULT_OK) {
                String word = data.getStringExtra(EditListBarang.EXTRA_REPLY);

                Integer watt = Integer.valueOf(data.getStringExtra(EditListBarang.EXTRA_REPLY_WATT));
                Integer durasi = Integer.valueOf(data.getStringExtra(EditListBarang.EXTRA_REPLY_DURASI));
                Integer jumlah = Integer.valueOf(data.getStringExtra(EditListBarang.EXTRA_REPLY_JUMLAH));
                // Update the database
                if (!TextUtils.isEmpty(word)) {
                    int id = data.getIntExtra(ListBarangAdapter.EXTRA_ID, -99);
                    if (id == WORD_ADD) {
                        mDB.insert(word, watt, durasi, jumlah);
                    }
                    else if (id >= 0) {
                        mDB.update(id, word, watt, durasi, jumlah);
                    }
                    // Update the UI
                    mAdapter.notifyDataSetChanged();

                } else {

                    Toast.makeText(
                            getApplicationContext(),
                            R.string.empty_not_saved,
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}