package bboy.flash.testweather.Component;

import bboy.flash.testweather.Module.NetworkModule;
import bboy.flash.testweather.Network.NetworkRepository;
import bboy.flash.testweather.Scope.ApiScope;
import dagger.Component;

@ApiScope
@Component(modules = {NetworkModule.class})
public interface ApiComponent {

    NetworkRepository getNetworkRepository();
}
