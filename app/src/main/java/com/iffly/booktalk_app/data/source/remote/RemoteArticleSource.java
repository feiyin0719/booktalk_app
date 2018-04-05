package com.iffly.booktalk_app.data.source.remote;

import com.iffly.booktalk_app.data.entity.Article;
import com.iffly.booktalk_app.data.source.ArticleSource;
import com.iffly.booktalk_app.data.source.PageList;
import com.iffly.booktalk_app.data.source.remote.net.ArticleService;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;

@Singleton
public class RemoteArticleSource implements ArticleSource {
    private ArticleService articleServices;
    @Inject
    public RemoteArticleSource(ArticleService articleServices){
        this.articleServices=articleServices;
    }
    @Override
    public Flowable<Article> getArticle(int id) {
        return null;
    }

    @Override
    public Flowable<List<Article>> getArticles() {
      return null;
    }

    @Override
    public Flowable<List<Article>> getArticlePage(int page) {
       return articleServices.getList("json",page).map((pageList)->pageList.getResults());
    }

    @Override
    public void saveArticle(Article article) {

    }

    @Override
    public void deleteArticle(int id) {

    }

    @Override
    public void deleteArticles() {

    }
}
