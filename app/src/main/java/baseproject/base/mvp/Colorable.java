package baseproject.base.mvp;

/**
 * Created by Administrator on 2019/2/2.
 */

public interface Colorable {
    //用于Activity跳转时传递颜色参数
    public String EXTRA_COLOR = "extra_color";

    /**
     * 控件,ActionBar,状态栏等的颜色在这设置
     * @param color
     */
    void setColor(int color);

    /**
     * 获取主题颜色
     * @return
     */
    int getColor();
}
