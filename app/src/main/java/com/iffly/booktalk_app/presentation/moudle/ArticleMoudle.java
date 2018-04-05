package com.iffly.booktalk_app.presentation.moudle;

import com.iffly.booktalk_app.data.entity.Article;
import com.iffly.booktalk_app.di.ActivityScoped;
import com.iffly.booktalk_app.di.FragmentScoped;
import com.iffly.booktalk_app.presentation.contract.ArticleContract;
import com.iffly.booktalk_app.presentation.presenter.ArticlePresenter;
import com.iffly.booktalk_app.presentation.view.fragment.ArticleFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ArticleMoudle {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract ArticleFragment articleFragment();

    @ActivityScoped
    @Binds
    abstract ArticleContract.Presenter articlePresenter(ArticlePresenter presenter);
}
