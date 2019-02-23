package baseproject.base.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2019/2/3.
 */

public abstract class BaseFragmentPagerAdapter extends FragmentPagerAdapter{
    protected List<Fragment> mFragmentList;

    public BaseFragmentPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        mFragmentList = fragments;
    }
}
