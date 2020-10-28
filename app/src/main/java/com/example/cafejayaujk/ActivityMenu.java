package com.example.cafejayaujk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ActivityMenu extends AppCompatActivity {

    String[] daftar;
    ListView lvMenu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static ActivityMenu ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ma = this;
        dbcenter = new DataHelper(this);
        RefreshList();

    }

    public void RefreshList() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("select * from menu", null);
        daftar = new String[cursor.getCount()];
        final String[] daftar2 = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar[cc] = "Jenis Menu : " + cursor.getString(1).toString() + "\n" +
                    "\nNama Menu :   " + cursor.getString(2).toString() + "\n" +
                    "\nPenjelasan Menu :   " + cursor.getString(3).toString() + "\n" +
                    "\nHarga  : Rp." + cursor.getString(4).toString();
        }
        lvMenu = findViewById(R.id.listviewmenu);
        lvMenu.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,daftar));
        lvMenu.setSelected(true);
        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                final String seleksi = daftar2[arg2];
                final  CharSequence[] dialogitem = {"Pesan Menu "};
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityMenu.this);
                builder.setTitle("Pesan menu ini!");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0 :
                                Intent i = new Intent(getApplicationContext(),PesanMenu.class);
                                i.putExtra("comot", seleksi);
                                startActivity(i);
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter)lvMenu.getAdapter()).notifyDataSetInvalidated();
    }
}
