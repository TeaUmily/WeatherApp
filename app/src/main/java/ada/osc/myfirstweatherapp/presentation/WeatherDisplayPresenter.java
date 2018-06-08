package ada.osc.myfirstweatherapp.presentation;

import android.util.Log;

import ada.osc.myfirstweatherapp.helpers.Constants;
import ada.osc.myfirstweatherapp.interaction.ApiInteractor;
import ada.osc.myfirstweatherapp.locationRepository.AllLocations;
import ada.osc.myfirstweatherapp.model.WeatherResponse;
import ada.osc.myfirstweatherapp.networking.ApiService;
import ada.osc.myfirstweatherapp.networking.RetrofitUtil;
import ada.osc.myfirstweatherapp.ui.weatherDisplay.WeatherDisplayContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherDisplayPresenter implements WeatherDisplayContract.Presenter {

    private ApiInteractor mApiInteractor;
    private WeatherDisplayContract.View mWeatherDisplayView;
    private AllLocations mAllLocations;


    public WeatherDisplayPresenter(ApiInteractor mApiInteractor) {
        this.mApiInteractor = mApiInteractor;
        mAllLocations = AllLocations.getInstance();
    }


    public void setView(WeatherDisplayContract.View view){
        this.mWeatherDisplayView = view;
    }

    public void getWeather(String cityToDisplay){
        mApiInteractor.getWeather(Constants.APP_ID, cityToDisplay, getWeatherCallBack() );
    }


    private Callback<WeatherResponse> getWeatherCallBack() {
        return new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response != null && response.body() != null) {
                    WeatherResponse data = (WeatherResponse) response.body();
                    Log.i("TEREZIJA", ""+data.getCityName());
                    mWeatherDisplayView.displayWeather(data);

                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                mWeatherDisplayView.onFailure();
            }
        };

    }


}
