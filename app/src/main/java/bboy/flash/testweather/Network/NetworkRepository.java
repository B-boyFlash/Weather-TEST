package bboy.flash.testweather.Network;

import java.util.Map;

import bboy.flash.testweather.Model.WeatherResponse;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NetworkRepository {

    private IApiService iApiService;

    public NetworkRepository(IApiService iApiService) {
        this.iApiService = iApiService;
    }

    public Observable<WeatherResponse> getWeather(Map<String, String> query) {
        return iApiService.getWeather(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
