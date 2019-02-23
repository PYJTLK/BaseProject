package com.example.baseproject.ui;

import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.baseproject.R;
import com.example.baseproject.adapter.Layout0Support;
import com.example.baseproject.adapter.Layout1Support;

import java.util.ArrayList;
import java.util.List;

import baseproject.base.pager.MultiLoopPagerAdapter;
import baseproject.base.pager.MultiTypeSupport;
import baseproject.image.ImageLoader;
import baseproject.utils.ActivityUtil;

@Route(path = "/group/main")
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
