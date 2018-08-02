package com.example.administrator.idlereader.movie;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.idlereader.ADetailActivity;
import com.example.administrator.idlereader.BaseRecyclerViewAdapter;
import com.example.administrator.idlereader.R;
import com.example.administrator.idlereader.bean.MoviesBean;

import java.util.List;

public class MovieOnAdapter extends BaseRecyclerViewAdapter<MoviesBean.SubjectsBean> {
    public MovieOnAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_movie_on, parent, false);
        return new MovieOnViewHolder(view);
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
                .into(((MovieOnViewHolder)holder).ivMovieOn);
        ((MovieOnViewHolder)holder).tvMovieOnTitle.setText(bean.getTitle());
        String directors="";
        for(int i=0;i<bean.getDirectors().size();i++){
            if (i==bean.getDirectors().size()-1){
                directors+=bean.getDirectors().get(i).getName();
            }else{
                directors+=bean.getDirectors().get(i).getName()+"/";
            }
        }
        ((MovieOnViewHolder)holder).tvMovieOnDirectors.setText("导演："+directors);
        String casts="";

        if (bean.getCasts().size()!=0){
            for(int i=0;i<bean.getCasts().size();i++){
                if (i==bean.getCasts().size()-1){
                    casts+=bean.getCasts().get(i).getName();
                }else{
                    casts+=bean.getCasts().get(i).getName()+"/";
                }
            }
            ((MovieOnViewHolder)holder).tvMovieOnCasts.setText(casts);
        }else {
            ((MovieOnViewHolder)holder).tvMovieOnCasts.setText("主演：佚名");
        }

        String gen="";
        for(int i=0;i<bean.getGenres().size();i++){
            if (i==bean.getGenres().size()-1){
                gen+=bean.getGenres().get(i);
            }else{
                gen+=bean.getGenres().get(i)+"/";
            }
        }
        ((MovieOnViewHolder)holder).tvMovieOnGenres.setText("类型："+gen);
        ((MovieOnViewHolder)holder).tvMovieOnRating.setText("评分："+bean.getRating().getAverage());
        ((MovieOnViewHolder)holder).rvMovieOn.setOnClickListener(new View.OnClickListener() {
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
    protected class MovieOnViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivMovieOn;
        private TextView tvMovieOnTitle;
        private TextView tvMovieOnDirectors;
        private TextView tvMovieOnCasts;
        private TextView tvMovieOnGenres;
        private TextView tvMovieOnRating;
        private RelativeLayout rvMovieOn;

        public MovieOnViewHolder(View view) {
            super(view);
            ivMovieOn = (ImageView) view.findViewById(R.id.iv_movie_on);
            tvMovieOnTitle = (TextView) view.findViewById(R.id.tv_movie_on_title);
            tvMovieOnDirectors = (TextView) view.findViewById(R.id.tv_movie_on_directors);
            tvMovieOnCasts = (TextView) view.findViewById(R.id.tv_movie_on_casts);
            tvMovieOnGenres = (TextView) view.findViewById(R.id.tv_movie_on_genres);
            tvMovieOnRating = (TextView) view.findViewById(R.id.tv_movie_on_rating);
            rvMovieOn = (RelativeLayout) view.findViewById(R.id.rl_movie_on);
        }
    }
}
