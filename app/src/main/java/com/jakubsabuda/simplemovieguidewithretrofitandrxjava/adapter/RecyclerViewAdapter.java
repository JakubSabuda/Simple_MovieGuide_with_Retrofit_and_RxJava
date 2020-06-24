/*
 *
 *  Created by Jakub Sabuda on 6/24/20 9:18 AM
 *  Copyright (c) 2020 . All rights reserved.
 *  Last modified 6/22/20 8:11 PM
 *  API key is my private key don't use it. API key is given by TMDB
 *  Icon is generated thanks by https://romannurik.github.io
 * /
 */

package com.jakubsabuda.simplemovieguidewithretrofitandrxjava.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.jakubsabuda.simplemovieguidewithretrofitandrxjava.MoiveDetailsActivity;
import com.jakubsabuda.simplemovieguidewithretrofitandrxjava.R;
import com.jakubsabuda.simplemovieguidewithretrofitandrxjava.model.Movie;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MovieViewHolder> {
    private Context context;
    private ArrayList<Movie> movieArrayList;

    public RecyclerViewAdapter(Context context, ArrayList<Movie> movieArrayList) {
        this.context = context;
        this.movieArrayList = movieArrayList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.movieTitle.setText(movieArrayList.get(position).getOriginalTitle());
        holder.movieRating.setText(Double.toString(movieArrayList.get(position).getVoteAverage()));

        String imageURI = "https://image.tmdb.org/t/p/w500" + movieArrayList.get(position).getPosterPath();
        Glide.with(context)
                .load(imageURI)
                .placeholder(R.drawable.loading)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{

        public TextView movieTitle, movieRating;
        public ImageView imageView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.tvTitle);
            movieRating = itemView.findViewById(R.id.tvRating);
            imageView = itemView.findViewById(R.id.ImageView);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Log.d("RecyclerViewAdapter", "POSITION IS : " + position);
                    if(position != RecyclerView.NO_POSITION) {
                        Movie selectedMovie = movieArrayList.get(position);

                        Intent intent = new Intent(context, MoiveDetailsActivity.class);
                        intent.putExtra("movie", selectedMovie);
                        context.startActivity(intent);
                    }

                }
            });

        }
    }
}
