package com.example.cafejayaujk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

public class PesanMenu extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper,dbcenter;
    public static PesanMenu ma;
    Button PesananBatal, tambahitem, minusitem, simpanpesanan;
    EditText etext1, etext2, etext3;
    Double hargamenu,tampilkode;
    String pilih, kode;
    TextView kode_pesanan, mshowCount, totalharga, kode_pesanan_detail;
    EditText date;
    DatePickerDialog datePickerDialog;
    EditText tvTimeResult;
    TimePickerDialog timePickerDialog;
    EditText kodemeja;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan_menu);

        kodemeja = findViewById(R.id.kodemeja);
        tambahitem = findViewById(R.id.tambahitem);
        minusitem = findViewById(R.id.minusitem);
        simpanpesanan = findViewById(R.id.simpanpesanan);
        dbHelper = new DataHelper(this);
        kode_pesanan = (TextView) findViewById(R.id.kodepesanan);
        kode_pesanan_detail = (TextView) findViewById(R.id.kd_pesanan_detail);
        etext1 = (EditText) findViewById(R.id.kolom_ed1);
        etext2 = (EditText) findViewById(R.id.kolom_ed2);
        etext3 = (EditText) findViewById(R.id.kolom_ed3);
        date = (EditText) findViewById(R.id.date);
        mshowCount = (TextView) findViewById(R.id.total_jumlahmenu);
        totalharga =  (TextView) findViewById(R.id.total_harga);
        ma = this;
        dbcenter = new DataHelper(this);
        RefreshList();


        tvTimeResult = (EditText) findViewById(R.id.time);
        tvTimeResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog();
            }
        });


        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM menu WHERE nama_menu = '" + getIntent().getStringExtra("comot") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            etext1.setText(cursor.getString(0).toString());
            etext2.setText(cursor.getString(2).toString());
            hargamenu= Double.parseDouble(cursor.getString(4).toString());
            etext3.setText(String.valueOf(Math.round(hargamenu)));
        }
        //Menarik data base Spiner Nomor Meja
        final Cursor meja = db.rawQuery("SELECT * FROM meja", null);
        String[] tampil = new String[meja.getCount()];
        final String[] kode = new String[meja.getCount()];
        meja.moveToFirst();
        for (int cc = 0; cc < meja.getCount(); cc++) {
            meja.moveToPosition(cc);
            tampil[cc] = meja.getString(1).toString();
            //Nomor Meja yang ditampilkan
            kode[cc] =  meja.getString(0).toString();
        }

        //Spiner kode suplier
//        final Spinner spsupiler = findViewById(R.id.sp_meja);
//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tampil);
//        spsupiler.setAdapter(adapter);
//        spsupiler.setSelected(true);
//        spsupiler.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                pilih = kode[position];
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        PesananBatal = (Button) findViewById(R.id.button1);
        PesananBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                // tahun
                int mYear = c.get(Calendar.YEAR);
                //bulan
                int mMonth = c.get(Calendar.MONTH);
                //hari
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                // date picker dialog
                datePickerDialog = new DatePickerDialog(PesanMenu.this,new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        date.setText(dayOfMonth + "/"+ (monthOfYear + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

    }

    private void RefreshList() {
        final SQLiteDatabase KodePesanan = dbcenter.getReadableDatabase();
        Cursor kode2 = KodePesanan.rawQuery("SELECT kd_pesanan from pesanan order by kd_pesanan DESC limit 1", null);
        kode2.moveToFirst();
        for (int cc = 0; cc < kode2.getCount(); cc++) {
            kode2.moveToPosition(cc);

            double n_id_sebelum = Double.parseDouble(kode2.getString(0));
            double stotal = 1 + n_id_sebelum;
            tampilkode = stotal;
            kode_pesanan.setText(String.valueOf(Math.round(tampilkode)));

        }

        final SQLiteDatabase KodePesanandetail = dbcenter.getReadableDatabase();
        Cursor kode_detail = KodePesanandetail.rawQuery("SELECT kd_pemesanan_detai from pemesanan_detail order by kd_pemesanan_detai DESC limit 1", null);
        kode_detail.moveToFirst();
        for (int cc = 0; cc < kode_detail.getCount(); cc++) {
            kode_detail.moveToPosition(cc);

            double n_id_detail_sebelum = Double.parseDouble(kode_detail.getString(0));
            double stotal2 = 1 + n_id_detail_sebelum;
            double tampilkode_detail = stotal2;
            kode_pesanan_detail.setText(String.valueOf(Math.round(tampilkode_detail)));

        }

    }



    private void showTimeDialog() {
        Calendar calendar = Calendar.getInstance();
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                tvTimeResult.setText( hourOfDay + ":" + minute);
            }
        },
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),

                DateFormat.is24HourFormat(this));

        timePickerDialog.show();


        }
    int mCount = 1;

    public void tambahitem(View view) {
        mCount++;
        if (mCount == 0) {
            mshowCount.setText("1");
            Toast.makeText(getApplicationContext(), "Pembelian barang minimal 1", Toast.LENGTH_LONG).show();
        } else {
            mshowCount.setText(Integer.toString(mCount));
            Locale local = new Locale("in", "ID");
            NumberFormat formatRp = NumberFormat.getCurrencyInstance(local);
            double nharga = Double.parseDouble(etext3.getText().toString());
            double njumlah = Double.parseDouble(mshowCount.getText().toString());
            double ntotal = nharga * njumlah;
            totalharga.setText(String.valueOf(Math.round(ntotal)));
        }
    }

    public void minusitem(View view) {
        mCount--;
        if (mCount < 1) {
            mshowCount.setText("1");
            Locale local = new Locale("in", "ID");
            NumberFormat formatRp = NumberFormat.getCurrencyInstance(local);
            double nharga = Double.parseDouble(etext3.getText().toString());
            double njumlah = Double.parseDouble(mshowCount.getText().toString());
            double ntotal = nharga * njumlah;
            totalharga.setText(String.valueOf(Math.round(ntotal)));
            Toast.makeText(getApplicationContext(), "Pembelian barang minimal 1", Toast.LENGTH_LONG).show();
            mCount=1;
        } else {
            mshowCount.setText(Integer.toString(mCount));
            Locale local = new Locale("in", "ID");
            NumberFormat formatRp = NumberFormat.getCurrencyInstance(local);

            double nharga = Double.parseDouble(etext3.getText().toString());
            double njumlah = Double.parseDouble(mshowCount.getText().toString());
            double ntotal = nharga * njumlah;
            totalharga.setText(String.valueOf(formatRp.format(ntotal)));
        }
    }

    public void simpan_pesanan(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        db.execSQL("insert into pesanan(kd_pesanan, tanggal, jam, kd_meja) values" +
                "('" + kode_pesanan.getText().toString() + "','" + date.getText().toString() + "'" +
                ",'" + tvTimeResult.getText().toString() + "','" + kodemeja.getText().toString() + "')");


        db.execSQL("INSERT INTO pemesanan_detail(kd_pemesanan_detai, kd_pesanan, nama_menu, jumlah_pesan, harga_total )values('" +
                kode_pesanan_detail.getText().toString() + "','"+
                kode_pesanan.getText().toString()+"','" +
                etext2.getText().toString() + "','" +
                mshowCount.getText().toString() + "','" +
                totalharga.getText().toString() +"')");


        Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
        ActivityMenu.ma.RefreshList();
        finish();
    }


}


