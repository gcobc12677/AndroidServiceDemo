package com.rytass.geeyang.servicedemo.Holder;

import android.app.Activity;

/**
 * Created by yangjiru on 2016/6/6.
 */
public abstract class BaseHolder<T> implements SDHD {
    public Activity activity;
    public int position = -1;

    public T data = null;

    public BaseHolder(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void setData(Object data, int position) {
        this.data = (T) data;
        this.position = position;
    }

    @Override
    public void release() {
        activity = null;
    }
}

