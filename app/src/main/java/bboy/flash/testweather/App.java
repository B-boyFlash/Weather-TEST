package bboy.flash.testweather;

import androidx.multidex.MultiDexApplication;

import bboy.flash.testweather.Component.ApiComponent;
import bboy.flash.testweather.Component.DaggerApiComponent;
import bboy.flash.testweather.Component.DaggerViewModelComponent;
import bboy.flash.testweather.Component.ViewModelComponent;
import bboy.flash.testweather.Module.ViewModelModule;

public class App extends MultiDexApplication {

    private ViewModelComponent viewModelComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        ApiComponent apiComponent = DaggerApiComponent.builder().build();
        viewModelComponent = DaggerViewModelComponent.builder()
                .apiComponent(apiComponent)
                .viewModelModule(new ViewModelModule(this))
                .build();
    }

    public ViewModelComponent getViewModelComponent() {
        return viewModelComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
