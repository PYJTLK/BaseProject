package baseproject.base.mvp;

/**
 * Created by Administrator on 2019/2/2.
 */

public interface IBasePresenter<V extends IBaseView> {
    public void attachView(V view);
}
