package com.example.administrator.idlereader.movie.model;

import android.util.Log;

import com.example.administrator.idlereader.bean.MoviesBean;
import com.example.administrator.idlereader.http.Api;
import com.example.administrator.idlereader.http.RetrofitHelper;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/19.
 */

public class MoviesModel implements IMoviesModel {
    private static final String TAG = "MoviesModel";
    @Override
    public void loadMovies( String total, final IMoviesLoadListener iMoviesLoadListener) {
        RetrofitHelper retrofitHelper= new RetrofitHelper(Api.MOVIE_HOST);
        retrofitHelper.getMovies(total)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<MoviesBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iMoviesLoadListener.fail(e);
                    }

                    @Override
                    public void onNext(MoviesBean moviesBean) {
                        iMoviesLoadListener.success(moviesBean);
                        Log.i(TAG, "onNext: "+moviesBean.getTotal());
                    }
                });
    }

}
