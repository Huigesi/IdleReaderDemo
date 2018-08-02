package com.example.administrator.idlereader.movie.view;


import com.example.administrator.idlereader.bean.MoviesBean;

/**
 * Created by Administrator on 2018/5/19.
 */

public interface IMoviesView {
    void showNews(MoviesBean moviesBean);
    void hideDialog();
    void showDialog();
    void showErrorMsg(Throwable throwable);
}
