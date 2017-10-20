package aladin.rx2retrofit2;

import android.content.Context;

import com.tientun.mockresponse.FakeInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiProvider {

    private static Retrofit retrofit;
    private static AirApi airApi;

    private static ApiProvider instance;

    public static ApiProvider getInstance(Context context){
        if(instance==null)
            instance = new ApiProvider(context);
        return instance;
    }
    private ApiProvider(Context context){
        OkHttpClient client = new OkHttpClient
                .Builder()
                .addInterceptor(new FakeInterceptor(context))
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://mock.api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        airApi = retrofit.create(AirApi.class);
    }

    public AirApi getAirApi(){
        return airApi;
    }
}
