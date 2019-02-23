package baseproject.widget.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2018/7/2.
 */

public abstract class SingleRecyclerAdapter<T> extends RecyclerView.Adapter<ViewHolder>{
    protected List<T> mDataList;
    protected Context mContext;
    protected LayoutInflater mInflater;

    public SingleRecyclerAdapter(Context context, List<T> dataList){
        mContext = context;
        mDataList = dataList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = mInflater.inflate(getLayoutResource(viewType),parent,false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        convert(holder,mDataList.get(position),position);
    }

    public abstract void convert(ViewHolder holder, T data, int position);

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public abstract int getLayoutResource(int type);
}
