package semanticalwaysauthentic.co.listbarang;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.List;

import static semanticalwaysauthentic.co.listbarang.ListBarangOpenHelper.hargafixxx;

public class PilihKwh extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    static String SpinnerLabel;
    static Integer hargafix;
    private PrefManager prefManager;
    public static final String EXTRA_REPLY_KWH = "com.example.android.wordlistsql.MESSAGE";
    private ListBarangOpenHelper mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set waktu pertama kali launch
        prefManager = new PrefManager(this);

        setContentView(R.layout.activity_pilih_kwh);

        final Spinner spinner = findViewById(R.id.pilih_kwh);
        ListBarangOpenHelper db = new ListBarangOpenHelper(getApplicationContext());
        List<String> labels = db.getAllLabels();

        if(spinner != null){
            spinner.setOnItemSelectedListener(this);
        }

        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, labels);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (spinner!=null){
            spinner.setAdapter(adapter);
        }
        mDB = new ListBarangOpenHelper(this);


        TextView l = (TextView)findViewById(R.id.ok);



        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDB.UpdateIsiTabelBarang();
                launchHomeScreen();
            }
        });


    }

    private void launchHomeScreen(){
        Intent intent = new Intent(this, HomeScreenActivity.class);

        startActivity(intent);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        SpinnerLabel = adapterView.getItemAtPosition(i).toString();
        hargafix = Integer.valueOf(SpinnerLabel);
        hargafixxx = mDB.getharga();
        Toast.makeText(getApplicationContext(), "Anda Memilih : "+ SpinnerLabel + "KWH",
                Toast.LENGTH_SHORT).show();

    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
