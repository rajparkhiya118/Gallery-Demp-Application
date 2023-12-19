package com.whats.tool.latestversion.gallerydempapplication.Model;

public class MyListDataModel {
    private int imgId;
    private boolean isClickItem;


    public MyListDataModel(int imgId, boolean isClickItem) {
        this.imgId = imgId;
        this.isClickItem = isClickItem;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public boolean isClickItem() {
        return isClickItem;
    }

    public void setClickItem(boolean clickItem) {
        isClickItem = clickItem;
    }
}