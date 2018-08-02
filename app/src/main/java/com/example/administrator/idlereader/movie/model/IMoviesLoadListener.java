package com.example.administrator.idlereader.movie.model;


import com.example.administrator.idlereader.bean.MoviesBean;

/**
 * Created by Administrator on 2018/5/19.
 */

public interface IMoviesLoadListener {
    void success(MoviesBean moviesBean);
    void fail(Throwable throwable);
}
