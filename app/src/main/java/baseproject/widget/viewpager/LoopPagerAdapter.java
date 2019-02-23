package baseproject.widget.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import java.util.List;

/**
 * 修改时间 2018-7-25
 */
public abstract class LoopPagerAdapter<T> extends BasePagerAdapter<T>{
    private final String TAG = "LoopPagerAdapter";

    private ViewPager mViewPager;

    public LoopPagerAdapter(Context context, List<T> dataList, ViewPager pager) {
        super(context, dataList);
        mViewPager = pager;
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);
        mViewPager.setAdapter(this);
    }

    @Override
    public int getCount() {
        if(mDataList.isEmpty()){
            return 0;
        }
        return mDataList.size() + 2;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int dataPosition = getDataPosition(position);
        return super.instantiateItem(container, dataPosition);
    }

    private int getDataPosition(int position){
        int realPosition;
        if(position == 0){
            realPosition = mDataList.size() - 1;
        }else if(position == getCount() - 1){
            realPosition = 0;
        }else{
            realPosition = position - 1;
        }
        return realPosition;
    }

    /**
     * 返回数据项数
     * @return
     */
    public int getDataCount(){
        return mDataList.size();
    }

    /**
     * 解除与ViewPager的绑定
     * 可用在onDestory()上
     */
    public void detatch(){
        if(mViewPager != null){
            mViewPager.removeOnPageChangeListener(mOnPageChangeListener);
            mViewPager.setAdapter(null);
        }
        mViewPager = null;
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        private int mCurrentPosition = 0;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            mCurrentPosition = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if(state == ViewPager.SCROLL_STATE_IDLE){
                if(mCurrentPosition == 0){
                    mViewPager.setCurrentItem(getCount() - 2,false);
                }else if(mCurrentPosition == getCount() - 1){
                    mViewPager.setCurrentItem(1,false);
                }
            }
        }
    };

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
