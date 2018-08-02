package com.example.administrator.idlereader.news;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.idlereader.ADetailActivity;
import com.example.administrator.idlereader.R;
import com.example.administrator.idlereader.bean.NewsBean;
import com.example.administrator.idlereader.untils.Resolution;

import java.util.ArrayList;
import java.util.List;

public class ItemNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<NewsBean.Bean> objects = new ArrayList<NewsBean.Bean>();

    private Context context;

    public ItemNewsAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<NewsBean.Bean> objects) {
        this.objects = objects;
    }

    public void addData(List<NewsBean.Bean> newObjects) {
        objects.addAll(newObjects);
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_news, parent, false);
            return new ItemNewsHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.footer, parent, false);
            return new FooterHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemNewsHolder) {
            final NewsBean.Bean bean = objects.get(position);
            if (bean == null) {
                return;
            }
            int widthPixels=context.getResources().getDisplayMetrics().widthPixels;
            int width = widthPixels;
            int height = Resolution.dipToPx(context, 200);
            Glide.with(context)
                    .load(bean.getImgsrc())
                    .error(R.drawable.img_error)
                    .placeholder(R.drawable.loads)
                    .override(width,height)
                    .centerCrop()
                    .into(((ItemNewsHolder) holder).ivNewsImg);
                if (position == 0) {
                    ((ItemNewsHolder) holder).tvNewsDigest.setVisibility(View.GONE);
                    ((ItemNewsHolder) holder).tvNewsTitle.setText("图片：" + bean.getTitle());
                } else {
                    ((ItemNewsHolder) holder).tvNewsTitle.setText(bean.getTitle());
                    ((ItemNewsHolder) holder).tvNewsDigest.setText(bean.getMtime() + " : " + bean.getDigest());
                    ((ItemNewsHolder) holder).cvNews.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, ADetailActivity.class);
                            intent.putExtra("url", bean.getUrl());
                            intent.putExtra("title", bean.getTitle());
                            context.startActivity(intent);
                        }
                    });
                }
        }
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    protected class ItemNewsHolder extends RecyclerView.ViewHolder {
        private ImageView ivNewsImg;
        private TextView tvNewsTitle;
        private TextView tvNewsDigest;
        private CardView cvNews;

        public ItemNewsHolder(View view) {
            super(view);
            ivNewsImg = (ImageView) view.findViewById(R.id.iv_news_img);
            tvNewsTitle = (TextView) view.findViewById(R.id.tv_news_title);
            tvNewsDigest = (TextView) view.findViewById(R.id.tv_news_digest);
            cvNews = (CardView) view.findViewById(R.id.cv_news);
        }
    }

    protected class FooterHolder extends RecyclerView.ViewHolder {

        public FooterHolder(View itemView) {
            super(itemView);
        }
    }
}
