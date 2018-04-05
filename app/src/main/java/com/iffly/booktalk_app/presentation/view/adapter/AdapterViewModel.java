package com.iffly.booktalk_app.presentation.view.adapter;

import android.databinding.BaseObservable;

public class AdapterViewModel extends BaseObservable {
    protected int layoutID;
    protected int resID;

    public int getLayoutID() {
        return layoutID;
    }

    public void setLayoutID(int layoutID) {
        this.layoutID = layoutID;
    }

    public int getResID() {
        return resID;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }

    public AdapterViewModel(int layoutID, int resID) {
        this.layoutID = layoutID;
        this.resID = resID;
    }
}
