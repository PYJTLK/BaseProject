package baseproject.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/2/2.
 */

public final class SharedPreferencesHelper {
    private Context mContext;

    private static SharedPreferencesHelper sInstance;

    /**
     * 获取SharedPreferencesHelper单例
     * @param context
     * @return
     */
    public static SharedPreferencesHelper getsInstance(Context context){
        if(sInstance == null){
            synchronized (SharedPreferences.class){
                if(sInstance == null){
                    sInstance = new SharedPreferencesHelper(context);
                }
            }
        }
        return sInstance;
    }

    private SharedPreferencesHelper(Context context){
        mContext = context;
    }

    /**
     * 保存多个数据,数据应转换为字符串
     * @param fileName
     * @param key
     * @param value
     */
    public void save(String fileName,String key,String value){
        Map<String,String> map = new HashMap<>();
        map.put(key,value);
        saveAll(fileName,map);
    }

    /**
     * 保存多个数据,所有数据都应转换为字符串
     * @param fileName
     * @param datas
     */
    public void saveAll(String fileName,Map<String,String> datas){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for(Map.Entry<String,String> entry : datas.entrySet()){
            editor.putString(entry.getKey(),entry.getValue());
        }
        editor.commit();
    }

    /**
     * 获取单个数据,返回的是字符串
     * @param fileName
     * @param key
     * @return
     */
    public String load(String fileName,String key){
        return loadAll(fileName, Arrays.asList(key)).get(key);
    }

    /**
     * 返回多个数据,所有数据都是字符串
     * @param fileName
     * @param keys
     * @return
     */
    public Map<String,String> loadAll(String fileName, List<String> keys){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        Map<String,String> map = new HashMap<>();
        for(int i = 0;i < keys.size();i++){
            map.put(keys.get(i),sharedPreferences.getString(keys.get(i),"null"));
        }
        return map;
    }
}
