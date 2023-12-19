package com.whats.tool.latestversion.gallerydempapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.whats.tool.latestversion.gallerydempapplication.Adapter.FolderListAdapter;
import com.whats.tool.latestversion.gallerydempapplication.Adapter.ImageListAdapter;
import com.whats.tool.latestversion.gallerydempapplication.Activity.FolderDataActivity;
import com.whats.tool.latestversion.gallerydempapplication.Model.FolderModel;
import com.whats.tool.latestversion.gallerydempapplication.Activity.FullScreenImageActivity;
import com.whats.tool.latestversion.gallerydempapplication.Model.MediaPojo;
import com.whats.tool.latestversion.gallerydempapplication.R;
import com.whats.tool.latestversion.gallerydempapplication.Utils.MyUtil;

import java.util.ArrayList;

public class ImageFragment extends Fragment {

    RecyclerView allImageRV;
    RecyclerView folder4RV;
    ArrayList<FolderModel> allFolderList;
    ArrayList<FolderModel> folder4List;
    ArrayList<MediaPojo> allImageList;
    FolderListAdapter folderListAdapter;
    ImageListAdapter imageListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_image, container, false);

        idBinding(view);

        allFolderList = new ArrayList<>();
        folder4List = new ArrayList<>();
        allImageList = new ArrayList<>();

        allFolderList = MyUtil.getImageFolder(requireActivity());
        allImageList = MyUtil.getAllImage(requireActivity());

        for (int i = 0; i < allFolderList.size(); i++) {
            if (i <= 3) {
                folder4List.add(allFolderList.get(i));
            }
        }

        folderListAdapter = new FolderListAdapter(folder4List, requireContext(), new FolderListAdapter.Click() {
            @Override
            public void ClickToActivity(String s) {
                Intent intent = new Intent(requireActivity(), FolderDataActivity.class);
                intent.putExtra("mealID", s);
                startActivity(intent);
            }
        });

        folder4RV.setLayoutManager(new GridLayoutManager(requireContext(), 4));
        folder4RV.setAdapter(folderListAdapter);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);

        if (!MyUtil.isPremium) {
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (position + 1) % 7 == 0 ? 3 : 1;
                }
            });

            allImageRV.setLayoutManager(gridLayoutManager);
            imageListAdapter = new ImageListAdapter(allImageList, requireContext(), MyUtil.isPremium, new ImageListAdapter.ClickInterface() {
                @Override
                public void ClickToActivity(String string) {
                    startActivity(new Intent(requireActivity(), FullScreenImageActivity.class).
                            putExtra("imageUrl", string));
                }
            });
            allImageRV.setAdapter(imageListAdapter);
        }

        return view;
    }

    private void idBinding(View view) {
        allImageRV = (RecyclerView) view.findViewById(R.id.allImageRV);
        folder4RV = (RecyclerView) view.findViewById(R.id.folder4RV);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MyUtil.isPremium) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
            allImageRV.setLayoutManager(gridLayoutManager);
            imageListAdapter = new ImageListAdapter(allImageList, requireContext(), MyUtil.isPremium, new ImageListAdapter.ClickInterface() {
                @Override
                public void ClickToActivity(String string) {
                    startActivity(new Intent(requireActivity(), FullScreenImageActivity.class).
                            putExtra("imageUrl", string));
                }
            });
            allImageRV.setAdapter(imageListAdapter);
        }
    }
}