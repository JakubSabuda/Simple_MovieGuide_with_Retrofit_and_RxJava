/*
 *
 *  Created by Jakub Sabuda on 6/24/20 9:18 AM
 *  Copyright (c) 2020 . All rights reserved.
 *  Last modified 6/23/20 11:59 AM
 *  API key is my private key don't use it. API key is given by TMDB
 *  Icon is generated thanks by https://romannurik.github.io
 * /
 */

package com.jakubsabuda.simplemovieguidewithretrofitandrxjava;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jakubsabuda.simplemovieguidewithretrofitandrxjava.model.Movie;

import jp.wasabeef.glide.transformations.BlurTransformation;


public class MoiveDetailsActivity extends AppCompatActivity {

    private Movie movie;
    private TextView recivedTitle, recivedRating, recivedReleaseDate, recivedOverview;
    private ImageView recivedImage, backgroundPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moive_details);

        recivedTitle = findViewById(R.id.recived_title);
        recivedRating = findViewById(R.id.recived_rating);
        recivedReleaseDate = findViewById(R.id.recived_released_date);
        recivedOverview = findViewById(R.id.recived_overview);

        recivedImage = findViewById(R.id.recived_photo);
        backgroundPhoto = findViewById(R.id.background_image);


        //Getting intent
        if (getIntent().hasExtra("movie")) {
            movie = getIntent().getParcelableExtra("movie");
            Log.d("RecyclerViewAdapter", "MOVIE FROM RECYCLERVIEW : " + movie);

            recivedTitle.setText(movie.getTitle());
            recivedRating.setText("Rating: " + movie.getVoteAverage().toString().trim());
            recivedReleaseDate.setText("Released Date:   " + movie.getReleaseDate());
            recivedOverview.setText(movie.getOverview());

            //Glide first image
            String uri_path = "https://image.tmdb.org/t/p/w500" + movie.getPosterPath();
            Glide.with(this)
                    .load(uri_path)
                    .placeholder(R.drawable.loading)
                    .into(recivedImage);

            //Blur imageView Background
            String uri_path_background = "https://image.tmdb.org/t/p/original" + movie.getPosterPath();
            Glide.with(this)
                    .load(uri_path_background)
                    .apply(RequestOptions.bitmapTransform(new BlurTransformation(35, 2)))
                    .into(backgroundPhoto);

        } else {
            Toast.makeText(this, "Something went wrong while loading", Toast.LENGTH_LONG).show();
        }

    }


}
