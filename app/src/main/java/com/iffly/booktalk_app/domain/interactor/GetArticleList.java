package com.iffly.booktalk_app.domain.interactor;

import com.fernandocejas.arrow.checks.Preconditions;
import com.iffly.booktalk_app.data.entity.Article;
import com.iffly.booktalk_app.data.source.repository.ArticleRepository;
import com.iffly.booktalk_app.domain.executor.PostExecutionThread;
import com.iffly.booktalk_app.domain.executor.ThreadExecutor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public class GetArticleList extends UseCase<List<Article>,GetArticleList.Params> {
    private ArticleRepository articleRepository;
    @Inject
    public GetArticleList(ArticleRepository articleRepository,ThreadExecutor threadExecutor,
                          PostExecutionThread postExecutionThread){
        super(threadExecutor,postExecutionThread);
        this.articleRepository=articleRepository;
    }
    @Override
    Flowable<List<Article>> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params);
        return articleRepository.getArticlePage(params.page);
    }

    public static final class Params {

        private final int page;

        private Params(int page) {
            this.page = page;
        }

        public static Params forPage(int page) {
            return new Params(page);
        }
    }
}
