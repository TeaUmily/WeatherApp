package ada.osc.myfirstweatherapp.ui.weatherDisplay;

public interface WeatherDisplayContract {

    interface View{

        void setCurrentTemperatureValues(double currentTemperatureValues);

        void setMinTemperatureValues(double minTemperatureValues);

        void setMaxTemperatureValues(double maxTemperatureValues);

        void setPressureValues(double pressureValues);

        void setWindValues(double windValues);

        void setDescriptionValues(String descriptionValues);

        void onFailure();

        void setWeatherIcon(String iconPath);
    }

    interface Presenter {

        void setView(WeatherDisplayContract.View view);

        void getWeather(String cityToDisplay);
    }

}
