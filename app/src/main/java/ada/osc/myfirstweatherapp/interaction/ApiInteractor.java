package ada.osc.myfirstweatherapp.interaction;

import ada.osc.myfirstweatherapp.pojo.WeatherResponse;
import retrofit2.Callback;

public interface ApiInteractor {

    void getWeather(String appId, String cityToDisplay, Callback<WeatherResponse> callback);
}
