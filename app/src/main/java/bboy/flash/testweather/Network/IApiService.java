package bboy.flash.testweather.Network;

import java.util.Map;

import bboy.flash.testweather.Model.WeatherResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

public interface IApiService {

    String BASE_URL = "https://api.openweathermap.org";

    @Headers("Content-Type: application/json")
    @GET("/data/2.5/weather/")
    Observable<WeatherResponse> getWeather(@QueryMap Map<String, String> params);
}
