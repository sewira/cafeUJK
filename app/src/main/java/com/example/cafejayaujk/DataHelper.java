package com.example.cafejayaujk;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "menucafe.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME , null , DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS menu");
        db.execSQL("CREATE TABLE menu(kd_menu text primary key, jenis text, nama_menu text, detail text, harga integer)");
        db.execSQL("INSERT INTO menu (kd_menu , jenis, nama_menu , detail, harga) VALUES('MN001','Minuman','Kopi Hitam','Kopi hitam dengan dengan di buat dengan teknik espresso,dimana di buat kopi yanng digunakan yang berasal dari Aceh Gayo jenis Arabika, disajikan dengan gula terpisah ','10000')");
        db.execSQL("INSERT INTO menu (kd_menu , jenis, nama_menu , detail, harga) VALUES('MN002','Minuman','Cappucino','Paduan kopi dengan buih susu kental serta  menggunakan sirup karamel, dimana biji kopi yang disajikan berasal dari Gunung Puntang jawabarat jenis robusta.','20000')");
        db.execSQL("INSERT INTO menu (kd_menu , jenis, nama_menu , detail, harga) VALUES('MN003','Minuman','Sparkling Tea','Minuman teh yang mengguanakan daun teh dari pegunungan daerah garut dengan tambahan sari melati asli dan gula merah alami.','15000')");

        db.execSQL("INSERT INTO menu (kd_menu , jenis, nama_menu , detail, harga) VALUES('MK001','Makanan','Batagor','Baso dan tahu goreng dengan sajian bumbu kacang dan kecap khas bandung.','25000')");
        db.execSQL("INSERT INTO menu (kd_menu , jenis, nama_menu , detail, harga) VALUES('MK002','Makanan','Cireng','Makanan ringan berupa tepung kanji goreng isi bahan tempe fermentasi yang di sebut oncom, disajikan dengan bumbu kacang pedas.','10000')");
        db.execSQL("INSERT INTO menu (kd_menu , jenis, nama_menu , detail, harga) VALUES('MK003','Makanan','Nasi Goreng','Nasi goreng ayam yang disajikan dengan telur mata sapi disertai sate ayam.','50000')");

        db.execSQL("INSERT INTO menu (kd_menu , jenis, nama_menu , detail, harga) VALUES('DS001','Dessert','Cheese Cake','Kue Tar 1 slice dengan bahan utama cream cheese dengan toping buah-buahan asli seperti anggur,jeruk, kiwi.','40000')");
        db.execSQL("INSERT INTO menu (kd_menu , jenis, nama_menu , detail, harga) VALUES('DS002','Dessert','Balck Salad','Potongan buah-buahan segar yang terdiri dari pepaya, jambu, melon, dan mangga disajikan dengan bumbu rujak kacang pedas dan gual merah.','30000')");

        db.execSQL("DROP TABLE IF EXISTS pesanan");
        db.execSQL("CREATE TABLE pesanan(kd_pesanan int primary key, tanggal text, jam text, kd_meja text)");
        db.execSQL("INSERT INTO pesanan(kd_pesanan, tanggal, jam, kd_meja) VALUES ('101', '25-10-2019', '10:30','MJ1')");
        db.execSQL("INSERT INTO pesanan(kd_pesanan, tanggal, jam, kd_meja) VALUES ('102', '25-10-2019', '09:20','MJ2')");
        db.execSQL("INSERT INTO pesanan(kd_pesanan, tanggal, jam, kd_meja) VALUES ('103', '25-10-2019', '10:00','MJ2')");
        db.execSQL("INSERT INTO pesanan(kd_pesanan, tanggal, jam, kd_meja) VALUES ('104', '25-10-2019', '12:00','MJ4')");

        db.execSQL("DROP TABLE IF EXISTS meja");
        db.execSQL("CREATE TABLE meja(kd_meja text primary key, posisi text)");
        db.execSQL("INSERT INTO meja(kd_meja, posisi) VALUES ('MJ1', 'Meja 01')");
        db.execSQL("INSERT INTO meja(kd_meja, posisi) VALUES ('MJ2', 'Meja 02')");
        db.execSQL("INSERT INTO meja(kd_meja, posisi) VALUES ('MJ3', 'Meja 03')");
        db.execSQL("INSERT INTO meja(kd_meja, posisi) VALUES ('MJ4', 'Meja 04')");
        db.execSQL("INSERT INTO meja(kd_meja, posisi) VALUES ('MJ5', 'Meja 05')");

        db.execSQL("DROP TABLE IF EXISTS pemesanan_detail");
        db.execSQL("CREATE TABLE pemesanan_detail(kd_pemesanan_detai int primary key, kd_pesanan int, nama_menu text, jumlah_pesan int, harga_total integer)");
        db.execSQL("INSERT INTO pemesanan_detail(kd_pemesanan_detai, kd_pesanan, nama_menu, jumlah_pesan, harga_total ) VALUES ('5001','101', 'Cappucino', '1', '20000' )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int il) {
        db.execSQL("DROP TABLE IF EXISTS jenis");
        db.execSQL("DROP TABLE IF EXISTS menu");
        db.execSQL("DROP TABLE IF EXISTS pesanan");
        db.execSQL("DROP TABLE IF EXISTS pesanan_detail");
        onCreate(db);

    }
}
