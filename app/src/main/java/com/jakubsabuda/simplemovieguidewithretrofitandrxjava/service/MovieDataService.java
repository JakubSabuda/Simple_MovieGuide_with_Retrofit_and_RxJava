/*
 *
 *  Created by Jakub Sabuda on 6/24/20 9:18 AM
 *  Copyright (c) 2020 . All rights reserved.
 *  Last modified 6/21/20 8:02 PM
 *  API key is my private key don't use it. API key is given by TMDB
 *  Icon is generated thanks by https://romannurik.github.io
 * /
 */

package com.jakubsabuda.simplemovieguidewithretrofitandrxjava.service;

import com.jakubsabuda.simplemovieguidewithretrofitandrxjava.model.MovieDBResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDataService {

    @GET("movie/popular")
   Observable<MovieDBResponse> getPopularMoviesWithRxJava(@Query("api_key")String apiKey);


}
