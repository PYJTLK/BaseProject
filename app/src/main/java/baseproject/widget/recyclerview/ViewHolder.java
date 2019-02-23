package baseproject.widget.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;

/**
 * 修改时间:2018-7-8
 * 修改内容:增加了Item显示/隐藏方法
 */

public class ViewHolder extends RecyclerView.ViewHolder{
    private SparseArray<View> mViews;

    public ViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
    }

    /**
     * 获取子view
     * @param resourceId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int resourceId){
        View childView = mViews.get(resourceId);
        if(childView == null){
            childView = itemView.findViewById(resourceId);
            mViews.put(resourceId,childView);
        }
        return (T) childView;
    }

    public void setVisibility(int visibility){
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) itemView.getLayoutParams();
        switch(visibility){
            case View.INVISIBLE:
            case View.GONE:
                params.width = 0;
                params.height = 0;
                break;

            case View.VISIBLE:
                params.width = LinearLayout.LayoutParams.MATCH_PARENT;
                params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                break;
        }
        itemView.setLayoutParams(params);
    }
}
