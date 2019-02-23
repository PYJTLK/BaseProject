package baseproject.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by Administrator on 2019/2/3.
 */

public abstract class BaseColorableFragment<P extends BasePresenter> extends BaseFragment<P> implements Colorable{
    public final static String EXTRA_COLOR = "extra_color";

    protected int mColor;

    public void setViewsColor(int color){
        mColor = color;
        setColor(mColor);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void initViewsColor(){
        Bundle args = getArguments();
        if(args == null){
            return;
        }

        int color = args.getInt(EXTRA_COLOR,0);
        if(color > 0){
            mColor = color;
        }
        if(mColor > 0) {
            setViewsColor(mColor);
        }
    }

    @Override
    public void setColor(int color) {
    }

    @Override
    public int getColor() {
        return mColor;
    }
}
