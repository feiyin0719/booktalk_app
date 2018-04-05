package com.iffly.booktalk_app.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

@Entity(tableName = "article")
public  class Article {
    @PrimaryKey
    @NonNull
    @ColumnInfo
    private long id;
    @Nullable
    @ColumnInfo
    private String title;
    @Nullable
    @ColumnInfo
    private String content;
    @Nullable
    @ColumnInfo
    private String url;
    @Nullable
    @ColumnInfo
    private String cover;
    @Nullable
    @ColumnInfo
    private String createdate;
    @Nullable
    @ColumnInfo
    private int type;
    @Nullable
    @Embedded(prefix = "owner_")
    private SimpleUser owner;

    @NonNull
    public long getId() {
        return id;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Nullable
    public String getUrl() {
        return url;
    }

    public void setUrl(@Nullable String url) {
        this.url = url;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public SimpleUser getOwner() {
        return owner;
    }

    public void setOwner(SimpleUser owner) {
        this.owner = owner;
    }
}
