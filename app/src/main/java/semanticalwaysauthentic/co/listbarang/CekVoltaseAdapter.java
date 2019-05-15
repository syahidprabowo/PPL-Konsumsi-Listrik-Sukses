package semanticalwaysauthentic.co.listbarang;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CekVoltaseAdapter extends RecyclerView.Adapter<CekVoltaseAdapter.WordViewHolder2>{

    class WordViewHolder2 extends RecyclerView.ViewHolder{
        public final TextView idItemView;
        public final TextView barangItemView;
        public final TextView wattItemView;

        public WordViewHolder2(View itemView) {
            super(itemView);
            idItemView = (TextView) itemView.findViewById(R.id.id);
            barangItemView = (TextView) itemView.findViewById(R.id.word);
            wattItemView = (TextView) itemView.findViewById(R.id.v_watt);
        }
    }

    private static final String TAG = CekVoltaseAdapter.class.getSimpleName();
    private final LayoutInflater mInflater2;
    Context mContext;

    ListBarangOpenHelper mDB;

    public CekVoltaseAdapter(Context context, ListBarangOpenHelper db) {
        mInflater2 = LayoutInflater.from(context);
        mContext = context;
        mDB = db;
    }

    @Override
    public WordViewHolder2 onCreateViewHolder(ViewGroup parent, int i) {
        View itemView = mInflater2.inflate(R.layout.activity_cek_voltase, parent, false);
        return new WordViewHolder2(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder2 holder2, int position) {
        itembarang current = mDB.query(position);
        holder2.idItemView.setText(current.getSid()+". ");
        holder2.barangItemView.setText(current.getWord());
        holder2.wattItemView.setText(current.getSWatt());
    }

    @Override
    public int getItemCount() {
        // Placeholder so we can see some mock data.
        return (int) mDB.count();
    }
}