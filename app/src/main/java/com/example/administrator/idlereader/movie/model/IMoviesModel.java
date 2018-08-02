package com.example.administrator.idlereader.movie.model;

/**
 * Created by Administrator on 2018/5/19.
 */

public interface IMoviesModel {
    void loadMovies(String total, IMoviesLoadListener iMoviesLoadListener);
}
