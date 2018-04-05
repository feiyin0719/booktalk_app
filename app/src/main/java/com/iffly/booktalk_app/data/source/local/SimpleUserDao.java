package com.iffly.booktalk_app.data.source.local;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import com.iffly.booktalk_app.data.entity.SimpleUser;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface SimpleUserDao {
    @Query("select * FROM simpleuser")
    Flowable<List<SimpleUser>> getAll();
    @Query("select * FROM simpleuser where id=:id")
    Flowable<SimpleUser> getById(int id);
    @Update
    int update(SimpleUser... simpleuser);
    @Query("DELETE FROM simpleuser")
    void deleteAll();
    @Query("DELETE FROM simpleuser WHERE id=:id")
    void deleteById(int id);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SimpleUser... simpleUser);

}
