package baseproject.image;

import android.content.Context;
import android.view.View;

/**
 * Created by Administrator on 2019/2/13.
 */

public abstract class AbsImageLoader {
    public abstract void init(Context context);

    public abstract void pause(Context context);

    public abstract void resume(Context context);

    public abstract void clearDiskCache(Context context);

    public abstract void clearMemoryCache(Context context);

    public abstract void clearViewImageCahce(View view);

    public abstract void trimMemory(Context context, int level);
}
