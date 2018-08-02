package com.example.administrator.idlereader.movie;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.idlereader.ADetailActivity;
import com.example.administrator.idlereader.BaseRecyclerViewAdapter;
import com.example.administrator.idlereader.R;
import com.example.administrator.idlereader.bean.MoviesBean;

import java.util.List;

public class MovieTopAdapter extends BaseRecyclerViewAdapter<MoviesBean.SubjectsBean> {
    public MovieTopAdapter(Context context, List<MoviesBean.SubjectsBean> list) {
        super(context, list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_movie_top250, parent, false);
        return new MovieTop250ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        final MoviesBean.SubjectsBean bean=mList.get(position);
            if (bean==null){
                return;
            }
            Glide.with(mContext)
                    .load(bean.getImages().getSmall())
                    .into(((MovieTop250ViewHolder)holder).ivMovieTop);
            ((MovieTop250ViewHolder)holder).tvMovieTopTitle.setText(bean.getTitle());
            ((MovieTop250ViewHolder)holder).rlMovieOn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ADetailActivity.class);
                    intent.putExtra("url",bean.getAlt());
                    intent.putExtra("title", bean.getTitle());
                    mContext.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    protected class MovieTop250ViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout rlMovieOn;
        private ImageView ivMovieTop;
        private TextView tvMovieTopTitle;


        public MovieTop250ViewHolder(View view) {
            super(view);
            rlMovieOn = (LinearLayout) view.findViewById(R.id.rl_movie_on);
            ivMovieTop = (ImageView) view.findViewById(R.id.iv_movie_top);
            tvMovieTopTitle = (TextView) view.findViewById(R.id.tv_movie_top_title);

        }
    }
}
