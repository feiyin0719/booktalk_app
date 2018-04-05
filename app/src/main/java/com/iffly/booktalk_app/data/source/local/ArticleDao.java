package com.iffly.booktalk_app.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.iffly.booktalk_app.data.entity.Article;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface ArticleDao {
    @Query("select * FROM article")
    Flowable<List<Article>> getAll();
    @Query("select * FROM article where id=:id")
    Flowable<Article> getById(int id);
    @Update
    int update(Article... articles);
    @Query("DELETE FROM article")
    void deleteAll();
    @Query("DELETE FROM article WHERE id=:id")
    void deleteById(int id);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Article... articles);
    @Delete
    void delete(Article ...articles);
    @Query("SELECT * FROM article  ORDER BY createdate limit (:page-1)*:pagenum,:page*:pagenum")
    Flowable<List<Article>> getByPage(int page,int pagenum);



}
