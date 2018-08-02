package com.example.administrator.idlereader.movie.presenter;


import com.example.administrator.idlereader.bean.MoviesBean;
import com.example.administrator.idlereader.movie.model.IMoviesLoadListener;
import com.example.administrator.idlereader.movie.model.IMoviesModel;
import com.example.administrator.idlereader.movie.model.MoviesModel;
import com.example.administrator.idlereader.movie.view.IMoviesView;

/**
 * Created by Administrator on 2018/5/19.
 */

public class MoviesPresenter implements IMoviesPresenter,IMoviesLoadListener {

    private IMoviesModel iMoviesModel;
    private IMoviesView iMoviesView;

    public MoviesPresenter(IMoviesView iMoviesView) {
        this.iMoviesView = iMoviesView;
        this.iMoviesModel =new MoviesModel();
    }


    @Override
    public void success(MoviesBean moviesBean) {
        iMoviesView.hideDialog();
        iMoviesView.showNews(moviesBean);
    }

    @Override
    public void fail(Throwable throwable) {
        iMoviesView.hideDialog();
        iMoviesView.showErrorMsg(throwable);
    }


    @Override
    public void loadMovies(String total) {
        iMoviesView.showDialog();
        iMoviesModel.loadMovies(total,this);
    }
}
