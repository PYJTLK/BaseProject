package baseproject.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 修改时间:2018-6-11
 * 修改内容:删除质量压缩,添加内存压缩及尺寸压缩,添加Bitmap写入本地方法
 */
public class ImageUtil{
    public static final String TAG = "ImageUtil";

    /**
     * 从View中截取图片
     * @param view  待截取的View
     * @return View中截取的Bitmap
     */
    public static Bitmap getBitmapFromView(View view){
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap cacheBitmap = view.getDrawingCache();
        Bitmap result = Bitmap.createBitmap(cacheBitmap);
        view.setDrawingCacheEnabled(false);
        view.destroyDrawingCache();
        return result;
    }

    /**
     * 将BitmapDrawable 转换为 Bitmap
     * @param drawable 待转换的BitmapDrawable
     * @return  转换的Bitmap
     */
    public static Bitmap drawableToBitmap(BitmapDrawable drawable){
        return drawable.getBitmap();
    }

    /**
     * 将Drawable 转换为 Bitmap
     * @param drawable 待转换的Drawable
     * @return  转换的Bitmap
     */
    public static Bitmap drawableToBitmap(Drawable drawable){
        return drawableToBitmap(drawable,null);
    }

    /**
     * 将Drawable转换为Bitmap
     * @param drawable 待转换的Drawable
     * @param config bitmap设定
     * @return  转换的Bitmap
     */
    public static Bitmap drawableToBitmap(Drawable drawable,Bitmap.Config config){
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();

        if (config == null){
            config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                    : Bitmap.Config.RGB_565;
        }

        Bitmap bitmap = Bitmap.createBitmap(width,height,config);

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0,0,width,height);
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 缩放Bitmap
     * @param src   源Bitmap
     * @param newWidth  新的宽度
     * @param newHeight  新的高度
     * @return  缩放的Bitmap
     */
    public static Bitmap zoomBitmap(Bitmap src,int newWidth,int newHeight){
        float widthScale = 1f * newWidth / src.getWidth();
        float heightScale = 1f * newHeight / src.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(widthScale,heightScale);
        Bitmap bitmap = Bitmap.createBitmap(src,0,0,src.getWidth(),src.getHeight(),matrix,false);
        if(!bitmap.equals(src)){
            src.recycle();
        }
        return bitmap;
    }

    /**
     * 等比缩放Bitmap
     * @param src   源Bitmap
     * @param scale   缩放比例
     * @return  缩放的Bitmap
     */
    public static Bitmap zoomBitmap(Bitmap src,float scale){
        if(scale <= 0)
            throw new IllegalArgumentException("bitmap scale <= 0");

        Matrix matrix = new Matrix();
        matrix.preScale(scale,scale);
        Bitmap bitmap = Bitmap.createBitmap(src,0,0,src.getWidth(),src.getHeight(),matrix,false);
        return bitmap;
    }

    /**
     * 裁剪Bitmap
     * @param src   源Bitmap
     * @param leftUpX   裁剪的左上角X坐标
     * @param leftUpY   裁剪的左上角Y坐标
     * @param rightDownX    裁剪的右下角X坐标
     * @param rightDownY    裁剪的右下角Y坐标
     * @return  裁剪的Bitmap
     */
    public static Bitmap clipBitmap(Bitmap src, int leftUpX, int leftUpY, int rightDownX, int rightDownY){
        Bitmap bitmap = Bitmap.createBitmap(src,leftUpX,leftUpY,rightDownX - leftUpX,rightDownY - leftUpY);
        return bitmap;
    }

    /**
     * 旋转Bitmap
     * @param src   源Bitmap
     * @param degree    旋转角度
     * @return  旋转的Bitmap
     */
    public static Bitmap rotateBitmap(Bitmap src,float degree){
        Matrix matrix = new Matrix();
        matrix.setRotate(degree);
        Bitmap bitmap = Bitmap.createBitmap(src,0,0,src.getWidth(),src.getHeight(),matrix,false);
        return bitmap;
    }

    /**
     * 采样压缩Bitmap,会降低Bitmap质量
     * @param path  图片路径
     * @param sampleSize    采样值
     * @return  压缩后的Bitmap
     */
    public static Bitmap sampleCompress(String path,int sampleSize){
        Options options = new Options();
        options.inSampleSize = sampleSize;
        return BitmapFactory.decodeFile(path,options);
    }

    /**
     * 获取指定路径的图片,以RGB565格式写入Bitmap,注意此方法无法保存PNG图片的透明度
     * @param path  图片路径
     * @return  RGB565格式Bitmap
     * @throws IOException
     */
    public static Bitmap rgb565Compress(String path) throws IOException {
        Options options = new Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeFile(path,options);
    }

    /**
     * 将Bitmap压缩到指定尺寸
     * @param path  图片路径
     * @param maxWidth  最大宽度
     * @param maxHeight 最大高度
     * @return  压缩后的Bitmap
     */
    public static Bitmap compressTo(String path,int maxWidth,int maxHeight){
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path,options);
        options.inSampleSize = caculateSampleSize(options,maxWidth,maxHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path,options);
    }

    /**
     * 将Bitmap压缩到指定内存大小以下
     * @param path  图片路径
     * @param maxMemorySize 允许压缩Bitmap占用的最大内存
     * @return  压缩后的Bitmap
     */
    public static Bitmap compressTo(String path,int maxMemorySize){
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path,options);
        options.inSampleSize = caculateSampleSizeByMemory(options,maxMemorySize);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path,options);
    }

    private static int caculateSampleSizeByMemory(Options options, int maxMemorySize) {
        int sampleSize = 1;
        int memorySize = options.outWidth * options.outHeight;

        switch(options.inPreferredConfig){
            case RGB_565:
            case ARGB_4444:
                memorySize *= 2;
                break;

            case ARGB_8888:
                memorySize *= 4;
                break;
        }

        while(memorySize > maxMemorySize){
            memorySize /= 4;    //宽度和高度分别缩小一半
            sampleSize *= 2;
        }

        return sampleSize;
    }

    private static int caculateSampleSize(Options options, int reqWidth, int reqHeight){
        int sampleSize = 1;
        final int width = options.outWidth;
        final int height = options.outHeight;

        if(width > reqWidth || height > reqHeight){
            final int halfWidth = width / 2;
            final int halfHeight = height / 2;
            while((halfWidth / sampleSize) > reqWidth && (halfHeight / sampleSize) > reqHeight){
                sampleSize *= 2;
            }
        }

        return sampleSize;
    }

    /**
     * 将Bitmap写入本地
     * @param path  写入地址
     * @param bitmap
     * @throws IOException
     */
    public static void putBitmapToLocal(String path,Bitmap bitmap) throws IOException {
        File file = new File(path);
        FileOutputStream outputStream = new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        outputStream.flush();
        outputStream.close();
    }

    /**
     * 将Bitmap 转换为 Drawable
     * @param resources
     * @param bitmap
     * @return 转换的Drawable
     */
    public static Drawable bitmapToDrawable(Resources resources,Bitmap bitmap){
        return new BitmapDrawable(resources,bitmap);
    }

    /**
     * 缩放Drawable
     * @param resources
     * @param src   源Drawable
     * @param scale 缩放比例
     * @return  缩放后的Drawable
     */
    public static Drawable zoomDrawable(Resources resources,Drawable src,float scale){
        Bitmap oldBitmap = drawableToBitmap(src);
        Bitmap bitmap = zoomBitmap(oldBitmap,scale);
        Drawable drawable = bitmapToDrawable(resources,bitmap);
        return drawable;
    }
}