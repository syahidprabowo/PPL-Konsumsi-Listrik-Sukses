package semanticalwaysauthentic.co.listbarang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PilihKwH extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_kwh);

        TextView l = (TextView)findViewById(R.id.ok);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PilihKwH.this, HomeScreenActivity.class);
                startActivity(i);
            }
        });
    }
}
