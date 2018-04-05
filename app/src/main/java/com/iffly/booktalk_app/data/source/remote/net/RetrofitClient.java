package com.iffly.booktalk_app.data.source.remote.net;



import com.iffly.booktalk_app.utils.HttpUrlConstantUtil;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class RetrofitClient {
    private Retrofit retrofit;
    @Inject
    public RetrofitClient(){
    retrofit=new Retrofit.Builder()
            .baseUrl(HttpUrlConstantUtil.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
