package ada.osc.myfirstweatherapp.presentation;

import android.support.annotation.NonNull;

import ada.osc.myfirstweatherapp.constants.Constants;
import ada.osc.myfirstweatherapp.interaction.ApiInteractor;
import ada.osc.myfirstweatherapp.locationRepository.AllLocations;
import ada.osc.myfirstweatherapp.pojo.Main;
import ada.osc.myfirstweatherapp.pojo.Weather;
import ada.osc.myfirstweatherapp.pojo.WeatherResponse;
import ada.osc.myfirstweatherapp.pojo.Wind;
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


    public void setView(WeatherDisplayContract.View view) {
        this.mWeatherDisplayView = view;
    }

    public void getWeather(String cityToDisplay) {
        mApiInteractor.getWeather(Constants.APP_ID, cityToDisplay, getWeatherCallBack());
    }


    private Callback<WeatherResponse> getWeatherCallBack() {
        return new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, Response<WeatherResponse> response) {

                final WeatherResponse data = response.body();
                if (data != null) {
                    createWeatherStringsForView(data);

                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                mWeatherDisplayView.onFailure();
            }
        };
    }

    private void createWeatherStringsForView(WeatherResponse data) {

        Weather weatherObject = data.getWeatherObject();

        createDescriptionValues(weatherObject);
        createWeatherIconValue(weatherObject.getMain());

        createTemperatureValues(data.getMain());
        createPressureValues(data.getMain());

        createWindValues(data.getWind());
    }


    private void createTemperatureValues(Main main) {
        mWeatherDisplayView.setMinTemperatureValues(fromKelvinToCelsius(main.getTemp_min()));
        mWeatherDisplayView.setMaxTemperatureValues(fromKelvinToCelsius(main.getTemp_max()));
        mWeatherDisplayView.setCurrentTemperatureValues(fromKelvinToCelsius(main.getTemp()));
    }

    private void createWindValues(Wind wind) {
        mWeatherDisplayView.setWindValues(wind.getSpeed());
    }

    private void createPressureValues(Main main) {
        mWeatherDisplayView.setPressureValues(main.getPressure());
    }

    private void createDescriptionValues(Weather weather) {
        mWeatherDisplayView.setDescriptionValues(weather.getDescription());
    }


    private void createWeatherIconValue(String description) {
        if (description != null)
            switch (description) {
                case Constants.SNOW_CASE: {
                    mWeatherDisplayView.setWeatherIcon(Constants.SNOW);
                    break;
                }
                case Constants.RAIN_CASE: {
                    mWeatherDisplayView.setWeatherIcon(Constants.RAIN);
                    break;
                }
                case Constants.CLEAR_CASE: {
                    mWeatherDisplayView.setWeatherIcon(Constants.SUN);
                    break;
                }
                case Constants.MIST_CASE: {
                    mWeatherDisplayView.setWeatherIcon(Constants.FOG);
                    break;
                }
                case Constants.FOG_CASE: {
                    mWeatherDisplayView.setWeatherIcon(Constants.FOG);
                    break;
                }
                case Constants.HAZE_CASE: {
                    mWeatherDisplayView.setWeatherIcon(Constants.FOG);
                    break;
                }

                case Constants.CLOUD_CASE: {
                    mWeatherDisplayView.setWeatherIcon(Constants.CLOUD);
                    break;
                }
            }
    }


    private double fromKelvinToCelsius(double temperature) {
        return temperature - 273;
    }



}
