package bboy.flash.testweather.Module;

import android.app.Application;

import bboy.flash.testweather.App;
import bboy.flash.testweather.Network.NetworkRepository;
import bboy.flash.testweather.Scope.ViewModelScope;
import bboy.flash.testweather.ViewModel.WeatherViewModel;
import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelModule {

    Application application;

    public ViewModelModule(App app) {
        application = app;
    }

    @ViewModelScope
    @Provides
    WeatherViewModel provideWeatherViewModel(NetworkRepository repository) {
        return new WeatherViewModel(application, repository);
    }
}
