package baseproject.image;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.executor.GlideExecutor;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by Administrator on 2019/2/13.
 */

@GlideModule
public class ImageModule extends AppGlideModule {
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        RequestOptions defaultRequestOptions = new RequestOptions()
                .format(GlideImageLoader.decodeFormat)
                .diskCacheStrategy(GlideImageLoader.diskCacheStrategy)
                .onlyRetrieveFromCache(false)
                .priority(GlideImageLoader.defaultPriority)
                .error(GlideImageLoader.defaultErrorImage)
                .placeholder(GlideImageLoader.defalutHolderImage)
                .disallowHardwareConfig();

        if(GlideImageLoader.memoryCacheSize <= 0){
            defaultRequestOptions.skipMemoryCache(true);
        }

        final GlideExecutor.UncaughtThrowableStrategy strategy = new GlideExecutor.UncaughtThrowableStrategy() {
            @Override
            public void handle(Throwable t) {
            }
        };

        builder.setMemoryCache(new LruResourceCache(GlideImageLoader.memoryCacheSize))
                .setBitmapPool(new LruBitmapPool(GlideImageLoader.bitmapPoolSize))
                .setDefaultRequestOptions(defaultRequestOptions)
                .setDiskCacheExecutor(GlideExecutor.newDiskCacheExecutor(strategy))
                .setLogLevel(GlideImageLoader.logLevel);

        if(GlideImageLoader.isCacheExternal){
            builder.setDiskCache(new InternalCacheDiskCacheFactory(context,GlideImageLoader.diskCacheSize));
        }else{
            builder.setDiskCache(new ExternalPreferredCacheDiskCacheFactory(context,GlideImageLoader.diskCacheSize));
        }
    }
}
