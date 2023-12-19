package com.whats.tool.latestversion.gallerydempapplication.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.whats.tool.latestversion.gallerydempapplication.R;
import com.whats.tool.latestversion.gallerydempapplication.Utils.TinyDB;
import com.whats.tool.latestversion.gallerydempapplication.Utils.MyUtil;

public class FullScreenImageActivity extends AppCompatActivity {

    private String imageUrl;
    ImageView fullscreenImg;
    Button setAsWallpaperBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            imageUrl = bundle.getString("imageUrl");
        }

        inBinding();
        Glide.with(this).load(imageUrl).into(fullscreenImg);

        setAsWallpaperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog myDialog = new Dialog(FullScreenImageActivity.this);
                myDialog.requestWindowFeature(1);
                myDialog.setContentView(R.layout.dialog_primum);
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                myDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                myDialog.show();

                myDialog.findViewById(R.id.no_text).setOnClickListener(v1 -> {
                    myDialog.dismiss();
                });

                myDialog.findViewById(R.id.yes_text).setOnClickListener(v1 -> {
                    myDialog.dismiss();
                    TinyDB tinyDB;
                    tinyDB = new TinyDB(FullScreenImageActivity.this);
                    tinyDB.putBoolean("is_premium", true);

                    MyUtil.isPremium = true;
                    finish();
                });
            }
        });
    }

    public void inBinding() {
        fullscreenImg = (ImageView) findViewById(R.id.fullscreenImg);
        setAsWallpaperBtn = (Button) findViewById(R.id.setAsWallpaperBtn);
    }
}