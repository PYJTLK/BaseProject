package baseproject.base.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2019/2/2.
 */

public abstract class BaseColorableActivity<P extends BasePresenter> extends BaseActivity<P> implements Colorable{
    public final static String EXTRA_COLOR = "extra_color";

    protected int mColor;

    public void setViewsColor(int color){
        mColor = color;
        setColor(mColor);
    }

    @Override
    public int getColor() {
        return mColor;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        mColor = intent.getIntExtra(EXTRA_COLOR,0);
        if (mColor > 0) {
            setViewsColor(mColor);
        }
    }

    @Override
    public void setColor(int color) {
    }
}
