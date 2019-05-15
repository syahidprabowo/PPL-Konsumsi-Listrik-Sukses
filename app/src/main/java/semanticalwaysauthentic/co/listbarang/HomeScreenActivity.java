package semanticalwaysauthentic.co.listbarang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static semanticalwaysauthentic.co.listbarang.ListBarangOpenHelper.hargafixxx;
import static semanticalwaysauthentic.co.listbarang.PilihKwh.SpinnerLabel;

public class HomeScreenActivity extends AppCompatActivity {


    String ex;
    private ListBarangOpenHelper mDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        mDB = new ListBarangOpenHelper(this);
        TextView menu1 = (TextView)findViewById(R.id.menu1);
        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeScreenActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        TextView menu2 = (TextView)findViewById(R.id.menu2);
        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (hargafixxx == null){
                    Toast.makeText(getApplicationContext(), "Pilih KWH Terlebih Dahulu.",
                            Toast.LENGTH_SHORT).show();
                }else{
                    mDB.UpdateIsiTabelBarang();
                    Intent i = new Intent(HomeScreenActivity.this, ListHargaBarang.class);
                    startActivity(i);
                }

            }
        });

        TextView menu3 = (TextView)findViewById(R.id.menu3);
        menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeScreenActivity.this, CekVoltase.class);
                startActivity(i);
            }
        });

        TextView menu4 = (TextView)findViewById(R.id.menu4);
        menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeScreenActivity.this, PilihKwh.class);
                Toast.makeText(getApplicationContext(), "Anda Memilih : "+ SpinnerLabel + "KWH",
                        Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });


    }
}

