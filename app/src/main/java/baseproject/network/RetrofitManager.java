package baseproject.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/6/26.
 */

/**
    compile "io.reactivex.rxjava2:rxjava:2.1.1"
    compile 'io.reactivex.rxjava2:rxandroid:2.0.1'
    compile 'com.squareup.retrofit2:retrofit:2.3.0'
    compile 'com.squareup.retrofit2:converter-gson:2.3.0'
    compile 'com.squareup.retrofit2:adapter-rxjava2:2.3.0'
*/

public class RetrofitManager {
    private final String BASE_URL = "http://192.168.8.14:82/";
    private final long DEFAULT_TIMEOUT_S = 10;

    private Retrofit mRetrofit;
    private OkHttpClient mClient;
    private static RetrofitManager sInstance;

    private RetrofitManager(){
        mClient = buildClient();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(mClient)
                .addConverterFactory(GsonConverterFactory.create(buildGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private OkHttpClient buildClient(){
        return new OkHttpClient.Builder()
                .writeTimeout(DEFAULT_TIMEOUT_S, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT_S,TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_TIMEOUT_S,TimeUnit.SECONDS)
                .addInterceptor(buildInterceptor())
                .retryOnConnectionFailure(true)
                .build();
    }

    private Interceptor buildInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                return response;
            }
        };
    }

    private Gson buildGson() {
        return new GsonBuilder()
                .registerTypeAdapter(Double.class,new DoubleDefaultAdapter())
                .registerTypeAdapter(Integer.class,new IntegerDefaultAdapter())
                .registerTypeAdapter(Long.class,new LongDefaultAdapter())
                .registerTypeAdapter(int.class,new DoubleDefaultAdapter())
                .registerTypeAdapter(double.class,new IntegerDefaultAdapter())
                .registerTypeAdapter(long.class,new LongDefaultAdapter())
                .create();
    }

    public static RetrofitManager getManager() {
        if (sInstance == null) {
            synchronized (RetrofitManager.class) {
                if (sInstance == null) {
                    sInstance = new RetrofitManager();
                }
            }
        }
        return sInstance;
    }

    public static ApiService getService(){
        return getManager().mRetrofit.create(ApiService.class);
    }
}
