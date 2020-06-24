/*
 *
 *  Created by Jakub Sabuda on 6/24/20 9:18 AM
 *  Copyright (c) 2020 . All rights reserved.
 *  Last modified 6/21/20 6:51 PM
 *  API key is my private key don't use it. API key is given by TMDB
 *  Icon is generated thanks by https://romannurik.github.io
 * /
 */

package com.jakubsabuda.simplemovieguidewithretrofitandrxjava.service;

import com.google.gson.TypeAdapter;
import com.jakubsabuda.simplemovieguidewithretrofitandrxjava.R;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitInstance {
    private static Retrofit retrofit = null;
    private static String BASE_URL = "https://api.themoviedb.org/3/";

    public static MovieDataService getService(){

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .build();

        if(retrofit == null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(MovieDataService.class);
    }
}
