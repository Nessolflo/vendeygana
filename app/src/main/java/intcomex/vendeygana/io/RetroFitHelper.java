package intcomex.vendeygana.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nesto on 30/07/2016.
 */
public class RetroFitHelper {
    private static Urls API_SERVICE;
    private static Gson GSON;

    public static Urls getApiService()
    {
        if(API_SERVICE==null)
        {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(15, TimeUnit.SECONDS)
                    .connectTimeout(15, TimeUnit.SECONDS).addInterceptor(interceptor)
                    .build();
            Retrofit adapter = new Retrofit.Builder()
                    .baseUrl(UrlsConstants.IP)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
            API_SERVICE = adapter.create(Urls.class);
        }
        return API_SERVICE;
    }

    public static Gson getGson() {
        if (GSON == null) {
            GSON = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .serializeNulls()
                    .create();
        }
        return GSON;
    }
}
