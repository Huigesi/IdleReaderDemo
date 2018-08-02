package com.example.administrator.idlereader.video.model;


import com.example.administrator.idlereader.bean.TodayContentBean;
import com.example.administrator.idlereader.bean.VideoUrlBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/19.
 */

public interface IVideoLoadListener {
    void videoUrlSuccess(List<VideoUrlBean> videoUrlBeans, List<TodayContentBean> contentBeans);
    void fail(Throwable throwable);
}
