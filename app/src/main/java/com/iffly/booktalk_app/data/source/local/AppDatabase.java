package com.iffly.booktalk_app.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.iffly.booktalk_app.data.entity.Article;
import com.iffly.booktalk_app.data.entity.SimpleUser;

@Database(entities = {Article.class, SimpleUser.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{
    public abstract ArticleDao articleDao();
    public abstract SimpleUserDao simpleUserDao();
}
