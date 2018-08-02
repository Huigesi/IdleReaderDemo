package com.example.administrator.idlereader;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ProgressBar;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class ADetailActivity extends SwipeBackActivity {

    private WebView wbNews;
    private String loadUrl, title;
    private WebViewClient webViewClient;
    private TextView tv_bar_title;
    private ImageView iv_back;
    private ProgressBar pb_load;
    private SwipeBackLayout swipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_detail);
        loadUrl = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        setSwipeBackEnable(true);
        swipeBackLayout=getSwipeBackLayout();
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        initView();
        setWebViewClient();
    }

    private void setWebViewClient() {
        webViewClient = new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pb_load.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                pb_load.setVisibility(View.GONE);
            }
        };
        wbNews.setWebViewClient(webViewClient);
    }

    private void initView() {
        wbNews = (WebView) findViewById(R.id.wb_news);
        wbNews.getSettings().setJavaScriptEnabled(true);
        wbNews.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wbNews.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        wbNews.canGoBack();
        wbNews.canGoForward();
        wbNews.loadUrl(loadUrl);
        tv_bar_title = (TextView) findViewById(R.id.tv_bar_title);
        tv_bar_title.setText(title);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        pb_load = (ProgressBar) findViewById(R.id.pb_load);
        int amp = 0;
//        int[] arr = {12, 45, 9, 67, 455};
//        for (int i = 0; i < arr.length - 1; i++) {
//            boolean flag = false;
//            for (int j = arr.length - 1; j > i; j--) {
//                if (arr[j] < arr[j - 1]) {
//                    int temp = arr[j];
//                    arr[j] = arr[j - 1];
//                    arr[j - 1] = temp;
//                    flag = true;
//                }
//            }
//            if (!flag) {
//                break;
//            }
//        }
//        for(int i=0;i<arr.length-1;i++){
//            int minIndex=i;
//            for(int j=i+1;j<arr.length;j++){
//                if (arr[j]>arr[minIndex]){
//                    minIndex=j;
//                }
//            }
//            if (minIndex!=i){
//                amp=arr[minIndex];
//                arr[minIndex]=arr[i];
//                arr[i]=amp;
//            }
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wbNews.destroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK && wbNews.canGoBack()) {
            wbNews.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
