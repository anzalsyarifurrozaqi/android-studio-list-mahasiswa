package com.example.tugascrud;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class list_mahasiswa extends AppCompatActivity {
    ListView listView;
    DatabaseHelper BantuDb;
    ArrayList<String> listNim;
    ArrayList<String> listNamaMhs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mahasiswa);

        BantuDb = new DatabaseHelper(this);
        listView = (ListView) findViewById(R.id.listMahasiswa);
        listNim = new ArrayList<>();
        listNamaMhs = new ArrayList<>();
        viewAll();
//        itemAdapter adapter = new itemAdapter(this);
//        listView.setAdapter(adapter);

    }
    private void viewAll() {

        //= {"v","v","V","v","v","v","V","v","v","v","V","v","v","v","V","v"};
        Cursor res = BantuDb.getAllData();
        if (res.getCount() == 0) {
            return;
        }

        while (res.moveToNext()) {
            listNim.add(res.getString(0));
            listNamaMhs.add(res.getString(1));
            itemAdapter adapter = new itemAdapter(this);
            listView.setAdapter(adapter);
        }
    }

    private  class itemAdapter extends  BaseAdapter {
        LayoutInflater mInflater;

        public  itemAdapter(Context c){
            mInflater =(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return listNim.size();
        }

        @Override
        public Object getItem(int position) {
            return listNim.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view1 = mInflater.inflate(R.layout.activity_row_list_mahasiswa,null);
            TextView no = view1.findViewById(R.id.no);
            TextView NIM = view1.findViewById(R.id.nim);
            TextView NamaMHS = view1.findViewById(R.id.namaMhs);

            no.setText(Integer.valueOf(position + 1).toString());
            NIM.setText(listNim.get(position));
            NamaMHS.setText(listNamaMhs.get(position));

            return view1;
        }
    }
}

