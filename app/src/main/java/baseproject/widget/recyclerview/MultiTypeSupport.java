package baseproject.widget.recyclerview;

/**
 * Created by Administrator on 2018/7/2.
 */

/**
 * 多类型数据加载支持类,可以继承这个类实现RecyclerView加载多类型数据项
 */
public abstract class MultiTypeSupport<D>{
    private D mData;

    public MultiTypeSupport(D data){
        mData = data;
    }

    /**
     * 获取视图的ID
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 将视图和数据进行绑定
     * @param viewHolder
     * @param position
     */
    public abstract void convert(ViewHolder viewHolder,D data,int position);

    /**
     * 获取用于绑定视图的数据
     * @return
     */
    public D getData(){
        return mData;
    }
}
