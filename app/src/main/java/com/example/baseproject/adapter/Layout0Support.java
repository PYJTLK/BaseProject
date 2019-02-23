package com.example.baseproject.adapter;

import android.view.View;
import android.widget.TextView;

import com.example.baseproject.R;

import baseproject.base.pager.MultiTypeSupport;

/**
 * Created by Administrator on 2019/2/23.
 */

public class Layout0Support extends MultiTypeSupport<String>{
    private TextView textView;

    public Layout0Support(String data) {
        super(data);
    }

    @Override
    public void convert(View convertView, String data, int position) {
        textView = convertView.findViewById(R.id.text);
        textView.setText(data);
    }

    @Override
    public int getResourceId() {
        return R.layout.layout0;
    }
}
