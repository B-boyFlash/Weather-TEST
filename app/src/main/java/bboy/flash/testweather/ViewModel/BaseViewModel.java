package bboy.flash.testweather.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import bboy.flash.testweather.App;

public class BaseViewModel extends AndroidViewModel {

    public BaseViewModel(Application application) {
        super(application);
    }

    public App getContext() {
        return getApplication();
    }
}
