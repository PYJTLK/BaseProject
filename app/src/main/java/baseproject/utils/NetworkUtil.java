package baseproject.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * 创建时间:2018-6-25
 */

/**
 * 网络工具类,使用前应申请 获取网络状态权限"android.permission.ACCESS_NETWORK_STATE"
 */
public class NetworkUtil {
    enum TYPE{
        NONE,
        WIFI,
        _2G,
        _3G,
        _4G,
        UNKNOWN
    }

    /**
     * 是否连接网络
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context){
        NetworkInfo networkInfo = getNetworkInfo(context);
        if(networkInfo != null){
            return networkInfo.isConnected();
        }
        return false;
    }

    /**
     * 是否连接Wifi
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context){
        NetworkInfo networkInfo = getNetworkInfo(context);
        if(networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI && networkInfo.isConnected()){
            return true;
        }
        return false;
    }

    /**
     * 是否连接移动数据
     * @param context
     * @return
     */
    public static boolean isMobileConnected(Context context){
        NetworkInfo networkInfo = getNetworkInfo(context);
        if(networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE && networkInfo.isConnected()){
            return true;
        }
        return false;
    }

    /**
     * 是否连接蓝牙
     * @param context
     * @return
     */
    public static boolean isBluetoothConnected(Context context){
        NetworkInfo networkInfo = getNetworkInfo(context);
        if(networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_BLUETOOTH && networkInfo.isConnected()){
            return true;
        }
        return false;
    }

    /**
     * 是否连接Vpn
     * @param context
     * @return
     */
    public static boolean isVpnConnected(Context context){
        NetworkInfo networkInfo = getNetworkInfo(context);
        if(networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_VPN && networkInfo.isConnected()){
            return true;
        }
        return false;
    }

    /**
     * 获取NetworkInfo
     * @param context
     * @return
     */
    public static NetworkInfo getNetworkInfo(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return networkInfo;
    }

    /**
     * 获取连接类型
     * @param context
     * @return
     */
    public static TYPE getAPNType(Context context){
        NetworkInfo networkInfo = getNetworkInfo(context);
        if(networkInfo == null)
            return TYPE.NONE;

        if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI)
            return TYPE.WIFI;

        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        boolean isRoaming = telephonyManager.isNetworkRoaming();
        if(!isRoaming){
            switch(telephonyManager.getNetworkType()){
                case TelephonyManager.NETWORK_TYPE_LTE:
                    return TYPE._4G;

                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                    return TYPE._3G;

                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_CDMA:
                    return TYPE._2G;
            }
        }

        return TYPE.UNKNOWN;
    }
}
