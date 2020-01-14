package com.example.rpl2016_21.apklogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private Adapter adapter;
    private ArrayList<Siswa> rvData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        initDataset();

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new Adapter(rvData);

        Log.e("", "onCreate: " + rvData.size() );

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    private void initDataset(){


        Siswa siswa1 = new Siswa();
        siswa1.setNama("Fizus");
        siswa1.setAlamat("kudus");
        siswa1.setKelas("XII");
        rvData.add(siswa1);

        Siswa siswa2 = new Siswa();
        siswa2.setNama("Topo");
        siswa2.setAlamat("kudus");
        siswa2.setKelas("XII");
        rvData.add(siswa2);

        Siswa siswa3 = new Siswa();
        siswa3.setNama("Yazid");
        siswa3.setAlamat("kudus");
        siswa3.setKelas("XII");
        rvData.add(siswa3);

        Siswa siswa4 = new Siswa();
        siswa4.setNama("Pandu");
        siswa4.setAlamat("kudus");
        siswa4.setKelas("XII");
        rvData.add(siswa4);

        Siswa siswa5 = new Siswa();
        siswa5.setNama("Sofwan");
        siswa5.setAlamat("kudus");
        siswa5.setKelas("XII");
        rvData.add(siswa5);
    }
}
