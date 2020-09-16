package bboy.flash.testweather.Module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import bboy.flash.testweather.Network.IApiService;
import bboy.flash.testweather.Network.NetworkRepository;
import bboy.flash.testweather.Scope.ApiScope;
import dagger.Module;
import dagger.Provides;
import okhttp3.Dispatcher;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module()
public class NetworkModule {

    @ApiScope
    @Provides
    public OkHttpClient provideOkHttpClient() {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        JavaNetCookieJar javaNetCookieJar = new JavaNetCookieJar(cookieManager);
        //
        return new OkHttpClient.Builder()
                .cookieJar(javaNetCookieJar)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    @ApiScope
    @Provides
    public Gson provideGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
    }

    @ApiScope
    @Provides
    public GsonConverterFactory provideGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @ApiScope
    @Provides
    public Retrofit.Builder provideRetrofitBuild(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient);
    }

    @ApiScope
    @Provides
    public Retrofit provideRetrofit(Retrofit.Builder builder) {
        return builder.baseUrl(IApiService.BASE_URL).build();
    }

    @Provides
    @ApiScope
    public IApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(IApiService.class);
    }

    @ApiScope
    @Provides
    public NetworkRepository provideNetworkRepository(IApiService service) {
        return new NetworkRepository(service);
    }
}
