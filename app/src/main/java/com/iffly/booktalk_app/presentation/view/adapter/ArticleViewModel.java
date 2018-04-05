package com.iffly.booktalk_app.presentation.view.adapter;

import com.iffly.booktalk_app.BR;
import com.iffly.booktalk_app.R;
import com.iffly.booktalk_app.data.entity.Article;

public class ArticleViewModel extends AdapterViewModel {
    private long id;
    private String title;
    private String content;
    private String cover;
    public ArticleViewModel(long id,String title,String content,String cover) {
        super(R.layout.item_article, BR.articleViewModel);
        this.id=id;
        this.title=title;
        this.content=content;
        this.cover=cover;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
    public static ArticleViewModel transfrom(Article article){
        return new ArticleViewModel(article.getId(),article.getTitle(),article.getContent(),article.getCover());
    }
}
