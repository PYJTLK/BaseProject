package baseproject.image;

import android.content.Context;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

/**
 * Created by Administrator on 2019/2/13.
 */

public class ImageLoader extends AbsImageLoader {
    public static AbsImageLoader getImageLoader(){
        return GlideImageLoader.getImageLoader();
    }

    public static RequestManager with(Context context){
        return Glide.with(context);
    }

    @Override
    public void init(Context context) {
        getImageLoader().init(context);
    }

    @Override
    public void pause(Context context) {
        getImageLoader().pause(context);
    }

    @Override
    public void resume(Context context) {
        getImageLoader().resume(context);
    }

    @Override
    public void clearDiskCache(Context context) {
        getImageLoader().clearDiskCache(context);
    }

    @Override
    public void clearMemoryCache(Context context) {
        getImageLoader().clearMemoryCache(context);
    }

    @Override
    public void clearViewImageCahce(View view) {
        getImageLoader().clearViewImageCahce(view);
    }

    @Override
    public void trimMemory(Context context, int level) {
        getImageLoader().trimMemory(context,level);
    }
}
