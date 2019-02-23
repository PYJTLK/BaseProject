package baseproject.widget.recyclerview;

import android.content.Context;

import java.util.List;

import baseproject.widget.recyclerview.MultiTypeSupport;

/**
 * Created by Administrator on 2018/7/2.
 */

public class MultiRecyclerAdapter extends SingleRecyclerAdapter<MultiTypeSupport>{
    public MultiRecyclerAdapter(Context context, List<MultiTypeSupport> dataList) {
        super(context, dataList);
    }

    @Override
    public void convert(ViewHolder holder, MultiTypeSupport multiTypeSupport, int position) {
        multiTypeSupport.convert(holder,multiTypeSupport.getData(),position);
    }

    @Override
    public int getLayoutResource(int type) {
        //把layoutId 作为type
        return type;
    }

    @Override
    public int getItemViewType(int position) {
        //把layoutId 作为type
        return mDataList.get(position).getLayoutId();
    }
}
