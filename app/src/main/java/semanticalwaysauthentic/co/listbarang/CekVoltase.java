package semanticalwaysauthentic.co.listbarang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class CekVoltase extends AppCompatActivity {
    private static final String TAG = ListHargaBarang.class.getSimpleName();

    public static final int WORD_EDIT = 1;
    public static final int WORD_ADD = -1;

    private RecyclerView mRecyclerView;
    private ListHargaAdapter mAdapter;

    int totalkonsumsi;
    private ListBarangOpenHelper mDB;
    String statuslistrik="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_voltase);

        mDB = new ListBarangOpenHelper(this);
        DecimalFormat df = new DecimalFormat("#.##");
        totalkonsumsi = mDB.getkonsumsi();
        TextView totalView = (TextView) findViewById(R.id.statusvoltase);
        if(totalkonsumsi<=900){
            statuslistrik = "Listrik Anda masih stabil";
        }
        else{
            statuslistrik = "Daya tidak mencukupi, atur kebutuhan Anda";
        }
        totalView.setText(statuslistrik);

        // Create recycler view.
        //mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview2);
        // Create an mAdapter and supply the data to be displayed.
        //mAdapter = new ListHargaAdapter(this, mDB);
        // Connect the mAdapter with the recycler view.
        //mRecyclerView.setAdapter(mAdapter); /* <-- Force Close */
        // Give the recycler view a default layout manager.
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this)); /* <-- Force Close */
    }
}

/*
* <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="horizontal">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginLeft="20dp"
            android:orientation="vertical">

            <LinearLayout
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:orientation="horizontal">

                <TextView
                    android:id="@+id/word"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:textColor="@color/colorPrimaryDark"
                    android:textSize="20sp"
                    android:textStyle="bold" />

            </LinearLayout>


            <LinearLayout
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:orientation="horizontal">

                <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="Daya : "
                    android:textColor="@android:color/black"
                    android:textSize="17sp" />

                <TextView
                    android:id="@+id/v_watt"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:textSize="17sp"
                    android:textColor="@android:color/black"/>

                <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text=" Watt"
                    android:textColor="@android:color/black"
                    android:textSize="17sp" />

            </LinearLayout>

            <Button
                android:layout_marginTop="15dp"
                android:layout_width="match_parent"
                android:layout_height="@dimen/divider_height"
                android:background="@color/colorAccent" />

        </LinearLayout>

    </LinearLayout>
*
* */