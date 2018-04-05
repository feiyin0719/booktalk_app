package com.iffly.booktalk_app.presentation.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.iffly.booktalk_app.R;
import com.iffly.booktalk_app.data.entity.Article;
import com.iffly.booktalk_app.di.ActivityScoped;
import com.iffly.booktalk_app.presentation.contract.ArticleContract;
import com.iffly.booktalk_app.presentation.view.adapter.ArticleViewModel;
import com.iffly.booktalk_app.presentation.view.adapter.RecyclerViewAdapter;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
@ActivityScoped
public class ArticleFragment extends DaggerFragment implements ArticleContract.View {
    @Inject
    ArticleContract.Presenter mPresenter;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter<ArticleViewModel> adapter;
    private List<ArticleViewModel> list;
    private WaveSwipeRefreshLayout refreshLayout;
    private boolean canLoad;
    @Inject
    public ArticleFragment() {
        // Required empty public constructor

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        list=new ArrayList<>();
        adapter=new RecyclerViewAdapter<>(list,context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_article, container, false);
        recyclerView=view.findViewById(R.id.articles);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        refreshLayout=view.findViewById(R.id.refresh);
        canLoad=true;
        refreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refreshArticles();
            }

            @Override
            public void onLoad() {
                mPresenter.loadArticles();
            }

            @Override
            public boolean canLoadMore() {
                return canLoad;
            }

            @Override
            public boolean canRefresh() {
                return true;
            }
        });
        adapter.setItemClickListener(new RecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.Adapter adapter, int position) {
                Toast.makeText(getContext(),"click"+position,Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    @Override
    public void showArticles(List<Article> articleList,boolean isRefresh) {
        int index;
        if(isRefresh){
            index=0;
            list.clear();
        }
        else
            index=list.size()-1;
        for(Article article:articleList){
            ArticleViewModel articleViewModel=ArticleViewModel.transfrom(article);
            list.add(index,articleViewModel);
            index++;
        }
        adapter.notifyDataSetChanged();


    }

    @Override
    public void showRefreshingView(boolean refresh) {
        refreshLayout.setRefreshing(refresh);
    }

    @Override
    public void showLoadingView(boolean loading) {
        refreshLayout.setLoading(loading);
    }

    @Override
    public void setCanLoad(boolean canLoad) {
        this.canLoad=canLoad;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.takeView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.dropView();  //prevent leaking activity in
        // case presenter is orchestrating a long running task
    }
}
