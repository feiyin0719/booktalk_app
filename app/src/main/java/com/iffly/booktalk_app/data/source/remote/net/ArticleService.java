package com.iffly.booktalk_app.data.source.remote.net;

import com.iffly.booktalk_app.data.entity.Article;
import com.iffly.booktalk_app.data.source.PageList;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ArticleService {
    @GET("bookreads/")
    Flowable<PageList<Article>> getList(@Query("format")String format, @Query("page")int page);
}
