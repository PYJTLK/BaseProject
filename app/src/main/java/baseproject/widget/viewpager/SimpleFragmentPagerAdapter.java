package baseproject.widget.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * Created by Administrator on 2019/2/14.
 */

public class SimpleFragmentPagerAdapter extends BaseFragmentPagerAdapter{
    public SimpleFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm, fragments);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
