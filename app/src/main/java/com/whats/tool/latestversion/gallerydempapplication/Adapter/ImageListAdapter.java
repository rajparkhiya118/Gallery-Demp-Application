package com.whats.tool.latestversion.gallerydempapplication.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.whats.tool.latestversion.gallerydempapplication.Model.MediaPojo;
import com.whats.tool.latestversion.gallerydempapplication.R;

import java.util.ArrayList;

public class ImageListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<MediaPojo> list;
    private Context context;
    private boolean is_Premium;
    public ClickInterface clickInterface;

    private static final int VIEW_TYPE_IMAGE = 0;
    private static final int VIEW_TYPE_AD = 1;

    public ImageListAdapter(ArrayList<MediaPojo> list, Context context, boolean is_Premium, ClickInterface clickInterface) {
        this.list = list;
        this.context = context;
        this.is_Premium = is_Premium;
        this.clickInterface = clickInterface;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (is_Premium) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_imge, parent, false);
            return new ImageViewHolder(view);
        } else {
            if (viewType == VIEW_TYPE_IMAGE) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_imge, parent, false);
                return new ImageViewHolder(view);
            } else {
                View adView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_ads, parent, false);
                return new AdViewHolder(adView);
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (is_Premium) {
            ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
            Glide.with(context).load(list.get(position).getPath()).into(imageViewHolder.imageView);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickInterface.ClickToActivity(list.get(position).getPath());
                }
            });
        } else {
            if (getItemViewType(position) == VIEW_TYPE_IMAGE) {
                int adjustedPosition = position - (position + 1) / 7;
                ImageViewHolder imageViewHolder = (ImageViewHolder) holder;

                Glide.with(context).load(list.get(adjustedPosition).getPath()).into(imageViewHolder.imageView);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clickInterface.ClickToActivity(list.get(adjustedPosition).getPath());
                    }
                });
            } else {
                AdViewHolder adViewHolder = (AdViewHolder) holder;

            }
        }
    }

    public interface ClickInterface {
        void ClickToActivity(String string);
    }

    @Override
    public int getItemCount() {
        if (is_Premium) {
            return list.size();
        } else {
            return list.size() + (list.size() / 6);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (is_Premium) {
            return super.getItemViewType(position);
        } else {
            return (position + 1) % 7 == 0 ? VIEW_TYPE_AD : VIEW_TYPE_IMAGE;
        }
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public class AdViewHolder extends RecyclerView.ViewHolder {

        public AdViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}