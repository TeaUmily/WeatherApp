package ada.osc.myfirstweatherapp.interaction;

import ada.osc.myfirstweatherapp.model.WeatherResponse;
import retrofit2.Callback;

public interface ApiInteractor {


    void getWeather(String appId, String cityToDisplay, Callback<WeatherResponse> callback);
}
