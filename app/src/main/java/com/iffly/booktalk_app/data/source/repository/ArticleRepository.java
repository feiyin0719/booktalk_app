package com.iffly.booktalk_app.data.source.repository;

import com.iffly.booktalk_app.data.entity.Article;
import com.iffly.booktalk_app.data.source.ArticleSource;
import com.iffly.booktalk_app.data.source.Local;
import com.iffly.booktalk_app.data.source.PageList;
import com.iffly.booktalk_app.data.source.Remote;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;

public class ArticleRepository implements ArticleSource {
    private ArticleSource localArticleSource;
    private ArticleSource remoteArticleSource;
    boolean mCacheIsDirty = false;
    Map<String, Article> mCachedTasks;
    @Inject
    public ArticleRepository(@Local ArticleSource localArticleSource, @Remote ArticleSource remoteArticleSource){
        this.localArticleSource=localArticleSource;
        this.remoteArticleSource=remoteArticleSource;
        mCachedTasks=new LinkedHashMap<>();
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
        return getAndSaveRemoteArticles(page);
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
    private Flowable<List<Article>> getAndSaveRemoteArticles(int page) {
        return remoteArticleSource
                .getArticlePage(page)
                .flatMap(articles -> Flowable.fromIterable(articles).doOnNext(article -> {
                    localArticleSource.saveArticle(article);
                    mCachedTasks.put(""+article.getId(), article);
                }).toList().toFlowable())
                .doOnComplete(() -> mCacheIsDirty = false);
    }

}
