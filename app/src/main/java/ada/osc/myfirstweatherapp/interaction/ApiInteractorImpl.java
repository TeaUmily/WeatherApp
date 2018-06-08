package ada.osc.myfirstweatherapp.interaction;

import javax.security.auth.callback.Callback;

import ada.osc.myfirstweatherapp.model.WeatherResponse;
import ada.osc.myfirstweatherapp.networking.ApiService;


public class ApiInteractorImpl implements ApiInteractor {


    private final ApiService mApiService;

    public ApiInteractorImpl(ApiService apiService) {
        this.mApiService = apiService;
    }


    @Override
    public void getWeather(String appId, String cityToDisplay, retrofit2.Callback<WeatherResponse> callback) {
        mApiService.getWeather(appId,cityToDisplay).enqueue(callback);
    }
}
