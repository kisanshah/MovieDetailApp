package com.example.moviedetail;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<MovieData> mData;

    public RecyclerAdapter(Context context, ArrayList<MovieData> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((RecyclerHolder) holder).title.setText(mData.get(position).getTitle());
        ((RecyclerHolder) holder).rating.setText(mData.get(position).getRating());
        Picasso.with(context).load(mData.get(position).getImg_url()).into(((RecyclerHolder) holder).img);
        ((RecyclerHolder) holder).cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, DetailActivity.class);
                intent.putExtra("URL",mData.get(position).getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView title, rating;
        ImageView img;
        CardView cardView;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            rating = itemView.findViewById(R.id.rating);
            img = itemView.findViewById(R.id.img);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

}
