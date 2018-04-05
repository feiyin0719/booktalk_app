package com.iffly.booktalk_app.data.source;

import com.iffly.booktalk_app.data.entity.Article;

import java.util.List;


import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;

public interface ArticleSource {
    Flowable<Article> getArticle(int id);
    Flowable<List<Article>> getArticles();
    Flowable<List<Article>> getArticlePage(int page);
    void saveArticle(Article article);
    void deleteArticle(int id);
    void deleteArticles();

}
