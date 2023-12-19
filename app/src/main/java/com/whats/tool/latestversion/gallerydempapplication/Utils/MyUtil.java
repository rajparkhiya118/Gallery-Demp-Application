package com.whats.tool.latestversion.gallerydempapplication.Utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.whats.tool.latestversion.gallerydempapplication.Model.FolderModel;
import com.whats.tool.latestversion.gallerydempapplication.Model.MediaPojo;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class MyUtil {

    public static boolean isPremium = false;

    public static ArrayList<MediaPojo> getImages(Context context, String bucket) {
        ArrayList<MediaPojo> videoList = new ArrayList<>();
        String[] projection = {MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, "bucket_display_name =?", new String[]{bucket}, MediaStore.Images.Media.DATE_MODIFIED + " DESC");
        Log.e("count", "getImages: " + cursor.getCount());
        while (cursor.moveToNext()) {
            MediaPojo ImageModel = new MediaPojo(cursor.getInt(0), cursor.getString(1));
            videoList.add(ImageModel);
        }
        cursor.close();
        return videoList;
    }

    public static ArrayList<MediaPojo> getAllImage(Context context) {
        ArrayList<MediaPojo> imageList = new ArrayList<>();
        String[] projection = {MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
        while (cursor.moveToNext()) {
            MediaPojo ImageModel = new MediaPojo(cursor.getInt(0), cursor.getString(1));
            imageList.add(ImageModel);
        }
        cursor.close();
        return imageList;
    }

    public static ArrayList<FolderModel> getImageFolder(Context context) {
        ArrayList<FolderModel> folderList = new ArrayList<>();

        Cursor query = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{"bucket_display_name", "bucket_id", "_data"},
                null,
                null,
                MediaStore.Images.Media.DATE_MODIFIED + " DESC"
        );

        if (query != null) {
            try {
                LinkedHashSet<String> uniqueFolders = new LinkedHashSet<>();
                while (query.moveToNext()) {
                    String folderName = query.getString(0);
                    String bucketId = query.getString(1);
                    String filePath = query.getString(2);

                    if (!uniqueFolders.contains(folderName)) {
                        uniqueFolders.add(folderName);
                        int imageCount = getImageCount(context, folderName);
                        folderList.add(new FolderModel(Integer.parseInt(bucketId), folderName, imageCount, filePath));
                    }
                }
            } finally {
                query.close();
            }
        }

        return folderList;
    }

    private static int getImageCount(Context context, String folderName) {
        Cursor query = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{"_data"},
                "bucket_display_name = ?",
                new String[]{folderName},
                null
        );

        int count = 0;

        if (query != null) {
            try {
                count = query.getCount();
            } finally {
                query.close();
            }
        }
        return count;
    }
}
