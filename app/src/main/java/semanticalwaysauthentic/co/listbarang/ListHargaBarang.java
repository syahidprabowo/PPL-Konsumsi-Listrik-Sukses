package semanticalwaysauthentic.co.listbarang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ListHargaBarang extends AppCompatActivity {

    private static final String TAG = ListHargaBarang.class.getSimpleName();

    public static final int WORD_EDIT = 1;
    public static final int WORD_ADD = -1;

    private RecyclerView mRecyclerView;
    private ListHargaAdapter mAdapter;

    double totalharga;



    private ListBarangOpenHelper mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_harga_barang);

        mDB = new ListBarangOpenHelper(this);
        DecimalFormat df = new DecimalFormat("###,###,###.##");
        totalharga = mDB.gettotalharga();
        TextView totalView = (TextView) findViewById(R.id.hargatotal);
        totalView.setText("Rp. "+String.valueOf(df.format(totalharga))+" /bln");

        // Create recycler view.
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview2);
        // Create an mAdapter and supply the data to be displayed.
        mAdapter = new ListHargaAdapter(this, mDB);
        // Connect the mAdapter with the recycler view.
        mRecyclerView.setAdapter(mAdapter);
        // Give the recycler view a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}
