package com.whats.tool.latestversion.gallerydempapplication.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.whats.tool.latestversion.gallerydempapplication.Fragment.FolderFragment;
import com.whats.tool.latestversion.gallerydempapplication.Fragment.ImageFragment;

public class MyAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public MyAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs  
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ImageFragment homeFragment = new ImageFragment();
                return homeFragment;
            case 1:
                FolderFragment sportFragment = new FolderFragment();
                return sportFragment;

            default:
                return null;
        }
    }

    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}  