package com.iffly.booktalk_app.presentation.view.activity;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.iffly.booktalk_app.R;
import com.iffly.booktalk_app.data.entity.Article;
import com.iffly.booktalk_app.data.source.ArticleSource;
import com.iffly.booktalk_app.data.source.PageList;
import com.iffly.booktalk_app.data.source.Remote;
import com.iffly.booktalk_app.presentation.presenter.ArticlePresenter;
import com.iffly.booktalk_app.presentation.view.fragment.ArticleFragment;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.BackpressureStrategy;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {
    @Inject
    ArticlePresenter mArticlePresenter;
    @Inject
    Lazy<ArticleFragment> articleFragmentProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArticleFragment articleFragment=(ArticleFragment)getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(articleFragment==null){
            articleFragment=articleFragmentProvider.get();
            addFragment(R.id.contentFrame,articleFragment);
        }
        if (savedInstanceState != null) {
            int page = (int) savedInstanceState.getSerializable("ARTICLE_PAGE");
            mArticlePresenter.setPage(page);
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("ARTICLE_PAGE", mArticlePresenter.getPage());

        super.onSaveInstanceState(outState);
    }
}
