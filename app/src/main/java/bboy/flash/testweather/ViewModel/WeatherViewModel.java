package bboy.flash.testweather.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import bboy.flash.testweather.Model.WeatherResponse;
import bboy.flash.testweather.Network.NetworkRepository;
import bboy.flash.testweather.R;
import bboy.flash.testweather.State.ErrorState;
import bboy.flash.testweather.State.LoadedState;
import bboy.flash.testweather.State.LoadingState;
import bboy.flash.testweather.State.State;
import retrofit2.HttpException;

public class WeatherViewModel extends BaseViewModel {

    private String TAG = "DaggerMVVM";

    private MutableLiveData<State> liveData = new MutableLiveData<>();

    protected NetworkRepository networkRepository;

    public WeatherViewModel(Application application, NetworkRepository repository) {
        super(application);
        this.networkRepository = repository;
    }

    public MutableLiveData<State> getLiveData() {
        return liveData;
    }

    public void getWeather(String query) {
        if (query.isEmpty()) {
            liveData.setValue(new ErrorState(getContext().getResources().getString(R.string.blank_error)));
            return;
        }
        liveData.setValue(new LoadingState());
        Map<String, String> params = new HashMap<>();
        params.put("q", query);
        params.put("appid", "1c9f3782a7802c3b0b63c2671816060d");
        this.networkRepository.getWeather(params)
                .subscribe(weatherResponse -> {
                    liveData.setValue(new LoadedState<WeatherResponse>(weatherResponse));
                }, throwable -> {
                    Log.w(TAG, throwable.getMessage());
                    try {
                        HttpException error = (HttpException) throwable;
                        String errorBody = error.response().errorBody().string();
                        Log.w(TAG, errorBody);
                        liveData.setValue(new ErrorState(errorBody));
                    } catch (IOException x) {
                        throwable.fillInStackTrace();
                    }
                    throwable.fillInStackTrace();
                });
    }
}
