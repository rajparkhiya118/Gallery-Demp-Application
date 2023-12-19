package com.whats.tool.latestversion.gallerydempapplication.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.whats.tool.latestversion.gallerydempapplication.Model.FolderModel;
import com.whats.tool.latestversion.gallerydempapplication.R;

import java.util.ArrayList;

public class FolderListAdapter extends RecyclerView.Adapter<FolderListAdapter.ViewHolder> {

    private ArrayList<FolderModel> list;
    private Context context;
    private Click click;

    public FolderListAdapter(ArrayList<FolderModel> list, Context context, Click click) {
        this.list = list;
        this.context = context;
        this.click = click;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.itemview_folder, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(list.get(position).getImg()).into(holder.folderThumbImg);
        holder.folderNameTxt.setText(list.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.ClickToActivity(list.get(position).getName());
            }
        });
    }

    public interface Click {
        void ClickToActivity(String s);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView folderThumbImg;
        public TextView folderNameTxt;

        public ViewHolder(View itemView) {
            super(itemView);
            folderThumbImg = (ImageView) itemView.findViewById(R.id.folderThumbImg);
            folderNameTxt = (TextView) itemView.findViewById(R.id.folderNameTxt);
        }
    }
}  