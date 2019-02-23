package baseproject.base.pager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

/**
 * 修改时间 2018-7-25
 */

public class MultiLoopPagerAdapter extends LoopPagerAdapter<MultiTypeSupport> {
    public MultiLoopPagerAdapter(List<MultiTypeSupport> dataList, ViewPager viewPager) {
        super(viewPager.getContext(), dataList, viewPager);
    }

    @Override
    public View getConvertView(LayoutInflater inflater, MultiTypeSupport multiSupport, int position) {
        return multiSupport.getConvertView(inflater,position);
    }
}