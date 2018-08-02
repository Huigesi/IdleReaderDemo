package com.example.administrator.idlereader.movie;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.idlereader.R;
import com.example.administrator.idlereader.bean.MoviesBean;
import com.example.administrator.idlereader.movie.presenter.MoviesPresenter;
import com.example.administrator.idlereader.movie.view.IMoviesView;

import java.util.ArrayList;
import java.util.List;


public class FgMovieFragment extends Fragment implements IMoviesView {

    private MoviesPresenter moviesPresenter;
    private RecyclerView rv_movie;
    private SwipeRefreshLayout srl_movie;
    private ItemMovieAdapter mItemMovieAdapter;
    private List<MoviesBean.SubjectsBean> mMovieOn = new ArrayList<MoviesBean.SubjectsBean>();
    private List<MoviesBean.SubjectsBean> mMovieTop250 = new ArrayList<MoviesBean.SubjectsBean>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_movie, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        moviesPresenter = new MoviesPresenter(this);
        srl_movie = view.findViewById(R.id.srl_movie);
        rv_movie = view.findViewById(R.id.rv_movie);
        mItemMovieAdapter = new ItemMovieAdapter(getActivity());
        srl_movie.setColorSchemeColors(Color.parseColor("#ffce3d3a"));
        moviesPresenter.loadMovies("in_theaters");
        moviesPresenter.loadMovies("top250");
        srl_movie.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                moviesPresenter.loadMovies("in_theaters");
                moviesPresenter.loadMovies("top250");
            }
        });
    }

    @Override
    public void showNews(MoviesBean moviesBean) {
        if (moviesBean.getTitle().equals("正在上映的电影-北京")) {
            mMovieOn.addAll(moviesBean.getSubjects());
        }
        if (moviesBean.getTotal() == 250) {
            mMovieTop250.addAll(moviesBean.getSubjects());
        }
        mItemMovieAdapter.setData(mMovieOn, mMovieTop250);
        rv_movie.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_movie.setAdapter(mItemMovieAdapter);
    }

    @Override
    public void hideDialog() {
        srl_movie.setRefreshing(false);
    }

    @Override
    public void showDialog() {
        srl_movie.setRefreshing(true);
    }

    @Override
    public void showErrorMsg(Throwable throwable) {
        Toast.makeText(getContext(), "加载出错:" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
