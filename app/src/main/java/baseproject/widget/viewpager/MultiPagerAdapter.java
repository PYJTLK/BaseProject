package baseproject.widget.viewpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

/**
 * Created by Administrator on 2018/7/25.
 */

public class MultiPagerAdapter extends BasePagerAdapter<MultiTypeSupport>{
    public MultiPagerAdapter(Context context, List<MultiTypeSupport> dataList) {
        super(context, dataList);
    }

    @Override
    public View getConvertView(LayoutInflater inflater, MultiTypeSupport multiTypeSupport, int position) {
        return multiTypeSupport.getConvertView(inflater,position);
    }
}
