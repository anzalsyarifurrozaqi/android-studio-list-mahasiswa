package com.example.tugascrud;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.tugascrud.DatabaseHelper.TABLE_NAME;

public class MainActivity extends AppCompatActivity {
    EditText xnim;
    EditText xnama;
    Button tblAdd, tblView, tblDel, tblEdit;
    DatabaseHelper BantuDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BantuDb = new DatabaseHelper(this);
        xnim=(EditText)findViewById(R.id.xnim);
        xnama=(EditText)findViewById(R.id.xnama);
        tblAdd=(Button)findViewById(R.id.tblAdd);
        tblView=(Button)findViewById(R.id.tblView);
        tblDel=(Button)findViewById(R.id.tblDel);
        tblEdit=(Button)findViewById(R.id.tblEdit);


        tblAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted =  BantuDb.insertData(xnim.getText().toString(),xnama.getText().toString());
                if(isInserted == true)
                    Toast.makeText(MainActivity.this,"Data Terimpan",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data Gagal Tersimpan",Toast.LENGTH_LONG).show();
            }
        });

        tblEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isEdited = BantuDb.updateData(xnim.getText().toString(), xnama.getText().toString(),"test");
                if(isEdited == true)
                    Toast.makeText(MainActivity.this,"Data Terubah",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data Gagal Terubah",Toast.LENGTH_LONG).show();
            }
        });

        tblDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int isDeleted = BantuDb.deleteData(xnim.getText().toString());
                if(isDeleted != 0)
                    Toast.makeText(MainActivity.this, "Data Terhapus", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data Gagal Terhapus", Toast.LENGTH_LONG).show();
            }
        });

      tblView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, list_mahasiswa.class);
                startActivity(i);
            }
        });
    }
}

