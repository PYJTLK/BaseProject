package baseproject.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import baseproject.image.ImageLoader;

/**
 * Created by Administrator on 2019/2/14.
 */

public abstract class BaseApplication extends Application{
    private boolean DEBUG = true;

    @Override
    public void onCreate() {
        super.onCreate();
        //图片加载器初始化
        ImageLoader.getImageLoader().init(this);

        //ARouter初始化
        if(DEBUG){
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);

        //dbFlow初始化
        FlowConfig flowConfig = new FlowConfig.Builder(this).build();
        FlowManager.init(flowConfig);


    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        ImageLoader.getImageLoader().clearMemoryCache(this);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        ImageLoader.getImageLoader().trimMemory(this,level);
    }
}
