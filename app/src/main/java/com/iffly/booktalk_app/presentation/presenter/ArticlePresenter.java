package com.iffly.booktalk_app.presentation.presenter;

import com.iffly.booktalk_app.di.ActivityScoped;
import com.iffly.booktalk_app.domain.interactor.GetArticleList;
import com.iffly.booktalk_app.presentation.contract.ArticleContract;

import javax.inject.Inject;
@ActivityScoped
public class ArticlePresenter implements ArticleContract.Presenter {
    private ArticleContract.View mView;
    private GetArticleList getArticleList;
    private int mPage=0;
    @Inject
    public ArticlePresenter(GetArticleList getArticleList){
        this.getArticleList=getArticleList;
    }

    private void getArticles(final boolean isRefresh) {
        getArticleList.execute((list)->{
                                mView.showArticles(list,isRefresh);
                                mPage++;
                                if(isRefresh)
                                    mView.showRefreshingView(false);
                                else
                                    mView.showLoadingView(false);
                                        },
                (throwable)->{Exception e=new Exception(throwable);e.printStackTrace();
                    if(isRefresh)
                        mView.showRefreshingView(false);
                    else
                    {
                        mView.showLoadingView(false);
                        mView.setCanLoad(false);
                    }
                    },
                GetArticleList.Params.forPage(mPage));
    }

    @Override
    public void takeView(ArticleContract.View view) {
        this.mView=view;
        refreshArticles();
    }

    @Override
    public void dropView() {
        this.mView=null;
    }

    @Override
    public void refreshArticles() {
        mPage=1;
        mView.setCanLoad(true);
        getArticles(true);
    }

    @Override
    public void loadArticles() {
        getArticles(false);
    }

    @Override
    public int getPage() {
        return mPage;
    }

    @Override
    public void setPage(int page) {
        mPage=page;
    }
}
