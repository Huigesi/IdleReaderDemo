package com.example.administrator.idlereader.movie;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.idlereader.ADetailActivity;
import com.example.administrator.idlereader.R;
import com.example.administrator.idlereader.bean.MoviesBean;

import java.util.ArrayList;
import java.util.List;

public class ItemMovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_MOVIE_ON = 119;
    private static final int TYPE_MOVIE_TOP250 = 120;
    private MovieOnAdapter mMovieOnAdapter;
    private MovieTopAdapter mMovieTopAdapter;

    private List<MoviesBean.SubjectsBean> mMovieOn = new ArrayList<MoviesBean.SubjectsBean>();
    private List<MoviesBean.SubjectsBean> mMovieTop250 = new ArrayList<MoviesBean.SubjectsBean>();

    private Context context;

    public ItemMovieAdapter(Context context) {

        this.context = context;
    }
    public void setData(List<MoviesBean.SubjectsBean> mMovieOn,List<MoviesBean.SubjectsBean> mMovieTop250){
        this.mMovieOn = mMovieOn;
        this.mMovieTop250=mMovieTop250;
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(getLayout(viewType), parent, false);
        if (viewType==TYPE_MOVIE_ON){
            return new MovieOnViewHolder(view);
        }else {
            return new MovieTop250ViewHolder(view);
        }
    }

    public int getLayout(int viewType){
        if (viewType==TYPE_MOVIE_ON){
            return R.layout.item_movieon;
        }else if (viewType==TYPE_MOVIE_TOP250){
            return R.layout.item_movie250;
        }else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return TYPE_MOVIE_TOP250;
        }else if (position==1){
            return TYPE_MOVIE_ON;
        }else {
            return 0;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MovieOnViewHolder){
            mMovieOnAdapter = new MovieOnAdapter(context, mMovieOn);
            ((MovieOnViewHolder) holder).rvItemMovieOn.setLayoutManager(new LinearLayoutManager(context));
            ((MovieOnViewHolder) holder).rvItemMovieOn.setAdapter(mMovieOnAdapter);
        }else if(holder instanceof MovieTop250ViewHolder){
            mMovieTopAdapter = new MovieTopAdapter(context, mMovieTop250);
            ((MovieTop250ViewHolder) holder).rvItemMovieTop.setLayoutManager(
                    new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
            ((MovieTop250ViewHolder) holder).rvItemMovieTop.setAdapter(mMovieTopAdapter);
            ((MovieTop250ViewHolder) holder).mTvMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ADetailActivity.class);
                    intent.putExtra("url","https://m.douban.com/top250");
                    intent.putExtra("title", "豆瓣");
                    context.startActivity(intent);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    protected class MovieOnViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView rvItemMovieOn;
        public MovieOnViewHolder(View view) {
            super(view);
            rvItemMovieOn = (RecyclerView) view.findViewById(R.id.rv_item_movie);
        }
    }
    protected class MovieTop250ViewHolder extends RecyclerView.ViewHolder{
        private RecyclerView rvItemMovieTop;
        private TextView mTvMore;


        public MovieTop250ViewHolder(View view) {
            super(view);
            rvItemMovieTop = (RecyclerView) view.findViewById(R.id.rv_item_movie);
            mTvMore = (TextView) view.findViewById(R.id.tv_more);

        }
    }
}
