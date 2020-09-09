package bboy.flash.testweather.Component;

import bboy.flash.testweather.Module.ViewModelModule;
import bboy.flash.testweather.Scope.ViewModelScope;
import bboy.flash.testweather.UI.Activity.MainActivity;
import dagger.Component;

@ViewModelScope
@Component(modules = {ViewModelModule.class}, dependencies = {ApiComponent.class})
public interface ViewModelComponent {

    void inject(MainActivity activity);
}
