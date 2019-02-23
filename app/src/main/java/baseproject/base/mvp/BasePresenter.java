package baseproject.base.mvp;

/**
 * Created by Administrator on 2019/2/2.
 */

public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter<V>{

    protected V mView;

    @Override
    public void attachView(V view) {
        mView = view;
    }
}
