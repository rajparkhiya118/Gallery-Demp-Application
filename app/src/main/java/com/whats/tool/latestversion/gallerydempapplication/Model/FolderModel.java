package com.whats.tool.latestversion.gallerydempapplication.Model;


public class FolderModel {

    int id;
    String name;
    String path;
    int count;
    String img;


    public FolderModel(int id, String name, int count, String img) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


}