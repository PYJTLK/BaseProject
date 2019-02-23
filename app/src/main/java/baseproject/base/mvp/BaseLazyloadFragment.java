package baseproject.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by Administrator on 2019/2/3.
 */

/**
 * 懒加载碎片抽象类,支持碎片的懒加载
 * @param <P>
 */
public abstract class BaseLazyloadFragment<P extends BasePresenter> extends BaseColorableFragment<P>{
    private boolean isViewCreated;

    private boolean isFragmentVisiable;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            isFragmentVisiable = true;
            lazyLoad();
        }else{
            isFragmentVisiable = false;
        }
    }

    protected void lazyLoad(){
        if(isViewCreated && isFragmentVisiable){
            loadData();
            isViewCreated = false;
            isFragmentVisiable = false;
        }
    }

    @Override
    public void setColor(int color) {
    }

    protected abstract void loadData();
}
