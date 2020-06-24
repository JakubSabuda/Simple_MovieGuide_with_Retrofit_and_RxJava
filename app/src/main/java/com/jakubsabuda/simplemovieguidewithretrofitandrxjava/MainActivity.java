/*
 *
 *  Created by Jakub Sabuda on 6/24/20 9:18 AM
 *  Copyright (c) 2020 . All rights reserved.
 *  Last modified 6/23/20 12:01 PM
 *  API key is my private key don't use it. API key is given by TMDB
 *  Icon is generated thanks by https://romannurik.github.io
 * /
 */

package com.jakubsabuda.simplemovieguidewithretrofitandrxjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.Configuration;
import android.os.Bundle;

import com.jakubsabuda.simplemovieguidewithretrofitandrxjava.adapter.RecyclerViewAdapter;
import com.jakubsabuda.simplemovieguidewithretrofitandrxjava.model.Movie;
import com.jakubsabuda.simplemovieguidewithretrofitandrxjava.model.MovieDBResponse;
import com.jakubsabuda.simplemovieguidewithretrofitandrxjava.service.MovieDataService;
import com.jakubsabuda.simplemovieguidewithretrofitandrxjava.service.RetrofitInstance;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Movie> movies;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Observable<MovieDBResponse> movieDBResponseObservable;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Popular movies list");

        getPopularMoviesWithRxJava();
        swipeLayoutToRefresh();

    }

    private void getPopularMoviesWithRxJava() {
        movies = new ArrayList<>();
        MovieDataService getMoviesDataService = RetrofitInstance.getService();
        movieDBResponseObservable = getMoviesDataService.getPopularMoviesWithRxJava(this.getString(R.string.api_key));

        compositeDisposable.add(movieDBResponseObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<MovieDBResponse, Observable<Movie>>() {
                    @Override
                    public Observable<Movie> apply(MovieDBResponse movieDBResponse) throws Exception {
                        return Observable.fromArray(movieDBResponse.getResults().toArray(new Movie[0]));
                    }
                })
                .subscribeWith(new DisposableObserver<Movie>() {
                    @Override
                    public void onNext(Movie movie) {
                        movies.add(movie);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        showOnRecyclerView();
                    }
                })
        );

    }





    private void showOnRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview);
        recyclerViewAdapter = new RecyclerViewAdapter(this, movies);

        //for portarit
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        }else
            //for landscape
        {
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    //Method for refreshing layout
    private void swipeLayoutToRefresh() {
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMoviesWithRxJava();
                recyclerViewAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
