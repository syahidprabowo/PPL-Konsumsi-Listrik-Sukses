package semanticalwaysauthentic.co.listbarang;

public class itembarang {

    private int mId;
    private String mBarang;
    private int mWatt;
    private int mDurasi;
    private double mBiaya;
    private int mJumlah;

    public itembarang() {}

    public int getId() {
        return this.mId;
    }

    public String getSid(){
        return String.valueOf(this.mId);
    }

    public String getWord() {
        return this.mBarang;
    }

    public int getWatt(){ return this.mWatt;}

    public String getSWatt(){ return String.valueOf(this.mWatt);}

    public int getDurasi(){ return  this.mDurasi;}

    public String getSDurasi(){ return String.valueOf(this.mDurasi);}

    public String getBiaya(){ return String.valueOf(this.mBiaya);}

    public int getmJumlah(){ return  this.mJumlah;}

    public String getSjumlah(){ return String.valueOf(this.mJumlah);}



    public void setId(int id) {
        this.mId = id;
    }

    public void setWord(String word) {
        this.mBarang = word;
    }

    public void setWatt(int watt) { this.mWatt = watt; }

    public void setmDurasi (int durasi){ this.mDurasi = durasi;}

    public void setmBiaya (double biaya){ this.mBiaya = biaya;}

    public void setmJumlah (int jumlah){ this.mJumlah = jumlah;}



}
