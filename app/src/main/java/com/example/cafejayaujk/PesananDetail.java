package com.example.cafejayaujk;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class PesananDetail extends AppCompatActivity {

    String[] daftar, daftar2;
    TextView kdPemesananDetail, kdPesanan, namaMenu, jumlahPesan, hargaTotal,time;
    DataHelper dbcenter;
    protected Cursor cursor;
    public static PesananDetail ma;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan_detail);

       kdPemesananDetail = findViewById(R.id.kodepemesanandetail);
       kdPesanan = findViewById(R.id.kodepesanan);
       namaMenu = findViewById(R.id.namamenu);
       jumlahPesan = findViewById(R.id.jumlahpesan);
       hargaTotal = findViewById(R.id.hargatotal);
       time = findViewById(R.id.time);

        dbcenter = new DataHelper(this);

        SQLiteDatabase db =dbcenter.getReadableDatabase();
        cursor = db.rawQuery("select * from pemesanan_detail where kd_pemesanan_detai '" + getIntent().getStringExtra("comot") + "'", null );
        cursor.moveToFirst();
        if (cursor.getCount() >0) {
            cursor.moveToPosition(0);
            kdPemesananDetail.setText(cursor.getString(0).toString());
            kdPesanan.setText(cursor.getString(1).toString());
            namaMenu.setText(cursor.getString(2).toString());
            jumlahPesan.setText(cursor.getString(3).toString());
            hargaTotal.setText(cursor.getString(4).toString());
            time.setText(cursor.getString(5).toString());

        }

    }
}
