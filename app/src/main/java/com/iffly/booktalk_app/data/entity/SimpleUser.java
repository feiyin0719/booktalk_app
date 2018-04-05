package com.iffly.booktalk_app.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

@Entity(tableName = "simpleuser")
public class SimpleUser {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo
    @Nullable
    private String nickname;
    @ColumnInfo
    @Nullable
    private String username;
    @ColumnInfo
    @Nullable
    private String url;

    @NonNull
    public long getId() {
        return id;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    @Nullable
    public String getUrl() {
        return url;
    }

    public void setUrl(@Nullable String url) {
        this.url = url;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
