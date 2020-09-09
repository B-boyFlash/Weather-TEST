package bboy.flash.testweather.UI.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import javax.inject.Inject;

import bboy.flash.testweather.App;
import bboy.flash.testweather.Model.WeatherResponse;
import bboy.flash.testweather.R;
import bboy.flash.testweather.State.ErrorState;
import bboy.flash.testweather.State.LoadedState;
import bboy.flash.testweather.State.LoadingState;
import bboy.flash.testweather.ViewModel.WeatherViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    private String TAG = "DaggerMVVM";

    @BindView(R.id.tv_weather)
    protected TextView tv_weather;

    @BindView(R.id.edt_city)
    protected EditText edt_city;

    @BindView(R.id.city_input_layout)
    protected TextInputLayout city_input_layout;

    @Inject
    protected WeatherViewModel weatherViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.w(TAG, "onCreate");
        //
        ((App) getApplication()).getViewModelComponent().inject(this);
        //
        weatherViewModel.getLiveData().observe(this, state -> {
            if (state instanceof LoadingState) {
                Log.w(TAG, "LoadingState");
                city_input_layout.setError("");
            }
            if (state instanceof LoadedState) {
                Log.w(TAG, "LoadedState");
                LoadedState<WeatherResponse> loadedState = (LoadedState<WeatherResponse>) state;
                tv_weather.setText(loadedState.getData().getName());
                tv_weather.append("\n");
                tv_weather.append(loadedState.getData().getWeather().get(0).getDescription());
            }
            if (state instanceof ErrorState) {
                Log.w(TAG, "ErrorState");
                ErrorState errorState = (ErrorState) state;
                Log.w(TAG, errorState.getError_message());
                city_input_layout.setError(errorState.getError_message());
            }
        });
        //
        edt_city.setOnTouchListener((v, event) -> {
            final int DRAWABLE_LEFT = 0;
            final int DRAWABLE_TOP = 1;
            final int DRAWABLE_RIGHT = 2;
            final int DRAWABLE_BOTTOM = 3;

            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (edt_city.getRight() - edt_city.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    closeKeyboard();
                    weatherViewModel.getWeather(edt_city.getText().toString());
                    return true;
                }
            }
            return false;
        });
    }
}