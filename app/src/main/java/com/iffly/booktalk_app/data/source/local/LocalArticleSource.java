package com.iffly.booktalk_app.data.source.local;

import com.iffly.booktalk_app.data.entity.Article;
import com.iffly.booktalk_app.data.source.ArticleSource;
import com.iffly.booktalk_app.data.source.PageList;
import com.iffly.booktalk_app.utils.ConstantUtil;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;

@Singleton
public class LocalArticleSource implements ArticleSource {
    private ArticleDao articleDao;
    @Inject
    public LocalArticleSource(ArticleDao articleDao){

        this.articleDao=articleDao;
    }
    @Override
    public Flowable<Article> getArticle(int id) {
        return articleDao.getById(id);
    }

    @Override
    public Flowable<List<Article>> getArticles() {
        return articleDao.getAll();
    }

    @Override
    public Flowable<List<Article>> getArticlePage(int page) {
        return articleDao.getByPage(page, ConstantUtil.PAGE_NUM);
    }

    @Override
    public void saveArticle(Article article) {
        articleDao.insert(article);
    }

    @Override
    public void deleteArticle(int id) {
        articleDao.deleteById(id);
    }

    @Override
    public void deleteArticles() {
        articleDao.deleteAll();
    }
}
