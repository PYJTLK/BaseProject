package baseproject.base.pager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 修改时间:2018-7-25
 */
public abstract class BasePagerAdapter<T> extends PagerAdapter{
    protected List<T> mDataList;
    protected Context mContext;
    protected LayoutInflater mInflater;

    public BasePagerAdapter(Context context, List<T> dataList){
        mContext = context;
        mDataList = dataList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View convertView = getConvertView(mInflater,mDataList.get(position),position);
        container.addView(convertView);
        return convertView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    /**
     * 获取转换的View,在这个方法里可以将数据和视图进行绑定
     * @param inflater
     * @param data
     * @param position
     * @return
     */
    public abstract View getConvertView(LayoutInflater inflater,T data,int position);

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
