package bboy.flash.testweather.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class WeatherResponse {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("weather")
    @Expose
    private List<Weather> weather;

    public WeatherResponse() {
        weather = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public List<Weather> getWeather() {
        return weather;
    }
}
