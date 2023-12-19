package com.whats.tool.latestversion.gallerydempapplication.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.whats.tool.latestversion.gallerydempapplication.Adapter.ImageListAdapter;
import com.whats.tool.latestversion.gallerydempapplication.R;
import com.whats.tool.latestversion.gallerydempapplication.Utils.MyUtil;

public class FolderDataActivity extends AppCompatActivity {

    String mealId;
    RecyclerView recyclerView;
    ImageListAdapter imageListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_data);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            mealId = bundle.getString("mealID");
        }

        recyclerView = (RecyclerView) findViewById(R.id.rv);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);

        if (!MyUtil.isPremium) {
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (position + 1) % 7 == 0 ? 3 : 1;
                }
            });
            recyclerView.setLayoutManager(gridLayoutManager);
            imageListAdapter = new ImageListAdapter(MyUtil.getImages(this, mealId), this, MyUtil.isPremium, new ImageListAdapter.ClickInterface() {
                @Override
                public void ClickToActivity(String string) {
                    startActivity(new Intent(FolderDataActivity.this, FullScreenImageActivity.class).
                            putExtra("imageUrl", string));
                }
            });
            recyclerView.setAdapter(imageListAdapter);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (MyUtil.isPremium) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            recyclerView.setLayoutManager(gridLayoutManager);
            imageListAdapter = new ImageListAdapter(MyUtil.getImages(this, mealId), this, MyUtil.isPremium, new ImageListAdapter.ClickInterface() {
                @Override
                public void ClickToActivity(String string) {
                    startActivity(new Intent(FolderDataActivity.this, FullScreenImageActivity.class).
                            putExtra("imageUrl", string));
                }
            });
            recyclerView.setAdapter(imageListAdapter);
        }
    }
}