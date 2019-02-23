package baseproject.widget.viewpager;

import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Administrator on 2018/7/2.
 */

/**
 * 多类型数据加载支持类,可以继承这个类实现ViewPager加载多种界面
 * 用在{@link baseproject.widget.viewpager.MultiPagerAdapter} 或 {@link baseproject.widget.viewpager.MultiLoopPagerAdapter}
 * 可以与{@link baseproject.base.mvp.BaseLazyloadFragment}结合使用,实现懒加载
 * @param <D>
 */
public abstract class MultiTypeSupport<D>{
    protected D mData;

    public MultiTypeSupport(D data){
        mData = data;
    }

    public View getConvertView(LayoutInflater inflater,int position){
        View convertView = inflater.inflate(getResourceId(),null);
        convert(convertView,mData,position);
        return convertView;
    }

    /**
     * 将视图和数据进行绑定
     * @param convertView
     * @param position
     */
    public abstract void convert(View convertView,D data,int position);

    /**
     * 获取视图的ID
     * @return
     */
    public abstract int getResourceId();

    /**
     * 获取用于绑定视图的数据
     * @return
     */
    public D getData(){
        return mData;
    }
}
