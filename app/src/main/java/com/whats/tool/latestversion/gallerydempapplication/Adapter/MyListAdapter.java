package com.whats.tool.latestversion.gallerydempapplication.Adapter;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.whats.tool.latestversion.gallerydempapplication.Activity.GridActivity;
import com.whats.tool.latestversion.gallerydempapplication.Model.MyListDataModel;
import com.whats.tool.latestversion.gallerydempapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {

    private GridActivity activity;
    List<Integer> selectedNumbers;
    private int anInt;
    CountDownTimer countDownTimer;
    private ArrayList<MyListDataModel> listDataModels;


    public MyListAdapter(GridActivity gridActivity, int anInt, ArrayList<MyListDataModel> size) {
        this.activity = gridActivity;
        this.anInt = anInt;
        this.listDataModels = size;
        selectedNumbers = new ArrayList<>();
        selectedNumbers.add(anInt);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (listDataModels.get(position).isClickItem() == true)
            holder.relativeLayout.setBackgroundColor(activity.getResources().getColor(R.color.black));
        else
            holder.relativeLayout.setBackgroundColor(activity.getResources().getColor(R.color.gry));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(selectedNumbers.size() == listDataModels.size() - 1)) {
                    if (listDataModels.get(position).isClickItem() == true) {
                        Toast.makeText(view.getContext(), "This item is not clickable. \uD83D\uDE1E ", Toast.LENGTH_LONG).show();
                    } else {
                        listDataModels.set(position, new MyListDataModel(position, true));
                        selectedNumbers.add(position);
                        int randomNum = generateRandomNumber(0, listDataModels.size() - 1, selectedNumbers);
                        selectedNumbers.add(randomNum);
                        listDataModels.set(randomNum, new MyListDataModel(randomNum, true));
                        holder.relativeLayout.setBackgroundColor(activity.getResources().getColor(R.color.black));

                        countDownTimer = new CountDownTimer(200, 1000) {
                            public void onTick(long millisUntilFinished) {
                            }

                            public void onFinish() {
                                notifyDataSetChanged();
                                if (selectedNumbers.size() == listDataModels.size()) {
                                    Toast.makeText(activity, "Congratulations Game is Over \uD83E\uDD73\uD83C\uDF89....", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }.start();
                    }
                } else
                    Toast.makeText(activity, "Last Single Item Not Clickable \uD83D\uDE13 ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static int generateRandomNumber(int min, int max, List<Integer> skipNumbers) {
        Random random = new Random();
        int randomNum;
        do {
            randomNum = random.nextInt((max - min) + 1) + min;
        } while (skipNumbers.contains(randomNum));
        return randomNum;
    }


    @Override
    public int getItemCount() {
        return listDataModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
        }
    }
}  