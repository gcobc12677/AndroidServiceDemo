
package com.rytass.geeyang.servicedemo.Holder;

import android.view.LayoutInflater;
import android.view.View;

public interface SDHD {

    public void reset();

    public View inflate(LayoutInflater inflater);

    public void setData(Object data, int position);

    public void release();

}


