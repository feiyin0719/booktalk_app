package com.iffly.booktalk_app.presentation.contract;

import com.iffly.booktalk_app.data.entity.Article;
import com.iffly.booktalk_app.presentation.presenter.BasePresenter;
import com.iffly.booktalk_app.presentation.view.BaseView;

import java.util.List;

public interface ArticleContract {
    public interface Presenter extends BasePresenter<View> {
        public void refreshArticles();
        public void loadArticles();
        public int getPage();
        public void setPage(int page);
    }
    public interface View extends BaseView<Presenter> {
        public void showArticles(List<Article> articleList,boolean isRefresh);
        public void showRefreshingView(boolean refresh);
        public void showLoadingView(boolean loading);
        public void setCanLoad(boolean canLoad);

    }
}
