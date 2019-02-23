package baseproject.image;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.baseproject.R;

/**
 * Created by Administrator on 2019/2/13.
 */

public class GlideImageLoader extends AbsImageLoader {
    public final static long memoryCacheSize = 1024 * 1024 * 10;//10MB

    public final static long diskCacheSize = 1024 * 1024 * 200;//200MB

    public final static long bitmapPoolSize = 1024 * 1024 * 10;//10MB

    public final static int logLevel = Log.DEBUG;

    public final static float thumbnailRate = 0.4f;

    public final static boolean isPreload = true;

    public final static DiskCacheStrategy diskCacheStrategy = DiskCacheStrategy.AUTOMATIC;

    public final static DecodeFormat decodeFormat = DecodeFormat.PREFER_RGB_565;

    public final static boolean isCacheExternal = true;

    public final static int defaultErrorImage = R.mipmap.ic_launcher;

    public final static int defalutHolderImage = R.mipmap.ic_launcher_round;

    public final static Priority defaultPriority = Priority.NORMAL;

    private static GlideImageLoader sImageLoader;

    public static GlideImageLoader getImageLoader(){
        if(sImageLoader == null){
            synchronized (GlideImageLoader.class){
                if(sImageLoader == null){
                    sImageLoader = new GlideImageLoader();
                }
            }
        }
        return sImageLoader;
    }

    private GlideImageLoader(){}

    @Override
    public void init(Context context) {
    }

    @Override
    public void pause(Context context) {
        Glide.with(context).pauseAllRequests();
    }

    @Override
    public void resume(Context context) {
        Glide.with(context).resumeRequests();
    }

    @Override
    public void clearDiskCache(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(context.getApplicationContext()).clearDiskCache();
            }
        }).start();
    }

    @Override
    public void clearMemoryCache(Context context) {
        Glide.get(context).clearMemory();
    }

    @Override
    public void clearViewImageCahce(View view) {
        Glide.with(view.getContext()).clear(view);
    }

    @Override
    public void trimMemory(Context context, int level) {
        Glide.get(context.getApplicationContext()).trimMemory(level);
    }
}
