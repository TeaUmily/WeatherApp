package ada.osc.myfirstweatherapp.ui.weatherDisplay;

import ada.osc.myfirstweatherapp.model.WeatherResponse;

public interface WeatherDisplayContract {

    interface View{

        
        void displayWeather(WeatherResponse data);

        void onFailure();
    }

    interface Presenter {

        void setView(WeatherDisplayContract.View view);


        void getWeather(String cityToDisplay);
    }

}
