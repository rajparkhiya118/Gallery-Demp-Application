package com.whats.tool.latestversion.gallerydempapplication.Activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.whats.tool.latestversion.gallerydempapplication.Adapter.MyListAdapter;
import com.whats.tool.latestversion.gallerydempapplication.Model.MyListDataModel;
import com.whats.tool.latestversion.gallerydempapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GridActivity extends AppCompatActivity {
    int anInt;
    ArrayList<MyListDataModel> list;
    List<Integer> selectedNumbers;
    private MyListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        list = new ArrayList<>();
        selectedNumbers = new ArrayList<>();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            anInt = extras.getInt("Position", 3);
        }


        int random = ThreadLocalRandom.current().nextInt(0, (anInt * anInt));
        for (int i = 0; i < (anInt * anInt); i++) {
            if (i == random) {
                selectedNumbers.add(random);
                list.add(new MyListDataModel(i, true));
            } else {
                list.add(new MyListDataModel(i, false));
            }
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new MyListAdapter(this, random, list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, anInt));
        recyclerView.setAdapter(adapter);
    }


}