package com.iffly.booktalk_app.data.source;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.iffly.booktalk_app.data.source.local.AppDatabase;
import com.iffly.booktalk_app.data.source.local.ArticleDao;
import com.iffly.booktalk_app.data.source.local.LocalArticleSource;
import com.iffly.booktalk_app.data.source.local.SimpleUserDao;
import com.iffly.booktalk_app.data.source.remote.RemoteArticleSource;
import com.iffly.booktalk_app.data.source.remote.net.ArticleService;
import com.iffly.booktalk_app.data.source.remote.net.RetrofitClient;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public abstract class RepositoryModule {
    @Singleton
    @Binds
    @Local
    abstract ArticleSource provideTasksLocalDataSource(LocalArticleSource dataSource);

    @Singleton
    @Binds
    @Remote
    abstract ArticleSource provideTasksRemoteDataSource(RemoteArticleSource dataSource);

    @Singleton
    @Provides
    static AppDatabase provideDb(Application context) {
        return Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "data.db")
                .build();
    }

    @Singleton
    @Provides
    static ArticleDao provideTasksDao(AppDatabase db) {
        return db.articleDao();
    }
    @Singleton
    @Provides
    static SimpleUserDao provideSimpleUserDao(AppDatabase db) {
        return db.simpleUserDao();
    }
    @Singleton
    @Provides
    static Retrofit provideRetrofit(RetrofitClient retrofitClient){return retrofitClient.getRetrofit();}
    @Singleton
    @Provides
    static ArticleService provideArticleService(Retrofit retrofit){return retrofit.create(ArticleService.class);}
}
