package com.example.administrator.idlereader.news.view;


import com.example.administrator.idlereader.bean.NewsBean;

/**
 * Created by Administrator on 2018/5/19.
 */

public interface INewsView {
    void showNews(NewsBean newsBean);

    void showMoreNews(NewsBean newsBean);

    void hideDialog();
    void showDialog();
    void showErrorMsg(Throwable throwable);
}
