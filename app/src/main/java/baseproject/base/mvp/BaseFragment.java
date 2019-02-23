package baseproject.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2019/2/3.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IBaseView{
    protected P mPresenter;

    protected abstract void initPresenter();

    protected abstract void initView(View contentView,Bundle savedInstanceState);

    protected abstract int getLayoutId();

    protected View mContentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(getLayoutId(),container,false);
        initView(mContentView,savedInstanceState);
        initPresenter();
        return mContentView;
    }
}
