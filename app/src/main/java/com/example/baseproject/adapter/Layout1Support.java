package com.example.baseproject.adapter;

import android.view.View;
import android.widget.ImageView;

import com.example.baseproject.R;

import baseproject.base.pager.MultiTypeSupport;
import baseproject.image.ImageLoader;

/**
 * Created by Administrator on 2019/2/23.
 */

public class Layout1Support extends MultiTypeSupport<String>{
    private ImageView imageView;

    public Layout1Support(String data) {
        super(data);
    }

    @Override
    public void convert(View convertView, String data, int position) {
        imageView  =convertView.findViewById(R.id.image);
        ImageLoader.with(convertView.getContext()).load(data).into(imageView);
    }

    @Override
    public int getResourceId() {
        return R.layout.layout1;
    }
}
