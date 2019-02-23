package baseproject.image.transformation;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

/**
 * Created by Administrator on 2019/2/12.
 */

/**
 * 填充转换类,实现图片填充效果(可能导致图片拉伸)
 */
public class FillTransformation extends BitmapTransformation {
    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        if(toTransform.getWidth() == outWidth && toTransform.getHeight() == outHeight){
            return toTransform;
        }

        //Glide会回收原来的Bitmap
        return Bitmap.createScaledBitmap(toTransform,outWidth,outHeight,true);
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {
    }

    @Override
    public int hashCode() {
        return getClass().getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof FillTransformation;
    }
}
