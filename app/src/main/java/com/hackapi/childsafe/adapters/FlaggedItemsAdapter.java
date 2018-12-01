package com.hackapi.childsafe.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.hackapi.childsafe.R;
import com.hackapi.childsafe.pojos.FlaggedData;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

/**
 * Created by KayO on 15/09/2017.
 */

public class FlaggedItemsAdapter extends RecyclerView.Adapter<FlaggedItemsAdapter.ViewHolder> {
    private Context ctx;
    private ArrayList<FlaggedData> allFlaggedData;

    public FlaggedItemsAdapter(Context ctx, ArrayList<FlaggedData> allFlaggedData) {
        this.ctx = ctx;
        this.allFlaggedData = allFlaggedData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.custom_row_flagged_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        YoYo.with(Techniques.FadeIn).playOn(holder.cardView);
        final FlaggedData currentItem = allFlaggedData.get(position);
        holder.tvDateTime.setText(currentItem.getDateTime());
        holder.tvUrl.setText(currentItem.getImgUrl());
        Picasso.with(ctx)
                .load(currentItem.getImgUrl())
                .fit()
                .centerCrop()
                .error(R.drawable.childsafe)
                .placeholder(R.drawable.childsafelogo)
                .into(holder.ivNewsThumb);

//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent detailIntent = new Intent(ctx, NewsDetailActivity.class);
//                detailIntent.putExtra("item", currentItem);
//                ctx.startActivity(detailIntent);
//            }
//        });
    }

    @Override
    public long getItemId(int position) {
        //return allFlaggedData.get(position).getId();
        return 0;
    }

    @Override
    public int getItemCount() {
        return allFlaggedData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivNewsThumb;
        TextView tvDateTime, tvUrl;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            ivNewsThumb = (ImageView) itemView.findViewById(R.id.ivNewsThumb);
            tvUrl = (TextView) itemView.findViewById(R.id.tvContentLink);
            tvDateTime = (TextView) itemView.findViewById(R.id.tvDateTime);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
        }
    }

    public void setAllFlaggedData(ArrayList<FlaggedData> allFlaggedData) {
        this.allFlaggedData = allFlaggedData;
    }
}
