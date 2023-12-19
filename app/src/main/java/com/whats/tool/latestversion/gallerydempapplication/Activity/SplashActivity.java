package com.whats.tool.latestversion.gallerydempapplication.Activity;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_MEDIA_IMAGES;
import static android.Manifest.permission.READ_MEDIA_VIDEO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.whats.tool.latestversion.gallerydempapplication.R;
import com.whats.tool.latestversion.gallerydempapplication.Utils.MyUtil;
import com.whats.tool.latestversion.gallerydempapplication.Utils.TinyDB;

public class SplashActivity extends AppCompatActivity {

    private static final int BELOW_ANDROID_13 = 101;
    private static final int ABOVE_ANDROID_13 = 202;
    TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tinyDB = new TinyDB(this);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(SplashActivity.this, new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE}, BELOW_ANDROID_13);
        } else {
            ActivityCompat.requestPermissions(SplashActivity.this, new String[]{READ_MEDIA_VIDEO, READ_MEDIA_IMAGES}, ABOVE_ANDROID_13);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case BELOW_ANDROID_13:
                boolean gotPermission1 = grantResults.length > 0;
                for (int result : grantResults) {
                    gotPermission1 &= result == PackageManager.PERMISSION_GRANTED;
                }
                if (gotPermission1) {
                    nextScreen();
                } else {
                    Toast.makeText(SplashActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            case ABOVE_ANDROID_13:
                boolean gotPermission2 = grantResults.length > 0;
                for (int result : grantResults) {
                    gotPermission2 &= result == PackageManager.PERMISSION_GRANTED;
                }
                if (gotPermission2) {
                    nextScreen();
                } else {
                    Toast.makeText(SplashActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void nextScreen() {
        MyUtil.isPremium = tinyDB.getBoolean("is_premium");

        findViewById(R.id.myButton).setOnClickListener(v -> {
            startActivity(new Intent(SplashActivity.this, DashBoardActivity.class));

        });

        findViewById(R.id.myButton1).setOnClickListener(v -> {
            startActivity(new Intent(SplashActivity.this, EditTextActivity.class));
        });
    }

}