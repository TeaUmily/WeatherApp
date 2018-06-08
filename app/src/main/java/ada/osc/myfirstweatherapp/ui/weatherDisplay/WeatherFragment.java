package ada.osc.myfirstweatherapp.ui.weatherDisplay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import ada.osc.myfirstweatherapp.helpers.App;
import ada.osc.myfirstweatherapp.helpers.Constants;
import ada.osc.myfirstweatherapp.R;
import ada.osc.myfirstweatherapp.interaction.ApiInteractor;
import ada.osc.myfirstweatherapp.interaction.ApiInteractorImpl;
import ada.osc.myfirstweatherapp.locationRepository.AllLocations;
import ada.osc.myfirstweatherapp.networking.ApiService;
import ada.osc.myfirstweatherapp.model.WeatherResponse;
import ada.osc.myfirstweatherapp.networking.NetworkUtils;
import ada.osc.myfirstweatherapp.networking.RetrofitUtil;
import ada.osc.myfirstweatherapp.presentation.WeatherDisplayPresenter;
import ada.osc.myfirstweatherapp.ui.adapter.CustomViewPagerFragmentAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Filip on 26/03/2016.
 */
public class WeatherFragment extends Fragment implements WeatherDisplayContract.View {

    @BindView(R.id.weather_display_current_temperature_text_view) TextView mCurrentTemperature;
    @BindView(R.id.weather_fragment_min_temperature_text_view) TextView  mMinTemperature;
    @BindView(R.id.weather_fragment_max_temperature_text_view) TextView mMaxTemperature;
    @BindView(R.id.weather_display_pressure_text_view) TextView mPressure;
    @BindView(R.id.weather_display_wind_text_view) TextView mWind;
    @BindView(R.id.weather_display_detailed_description_text_view) TextView mDescription;
    @BindView(R.id.weather_display_weather_icon_image_view) ImageView mWeatherIcon;


    private WeatherDisplayContract.Presenter mPresenter;
    private Unbinder unbinder;

    public static WeatherFragment newInstance(String city) {
        Bundle data = new Bundle();
        data.putString(Constants.CITY_BUNDLE_KEY, city);
        WeatherFragment f = new WeatherFragment();
        f.setArguments(data);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = new WeatherDisplayPresenter(App.getApiInteractor());
        mPresenter.setView(this);



    }

    @Override
    public void onStart() {
        super.onStart();

        String cityToDisplay = getArguments().getString(Constants.CITY_BUNDLE_KEY);
        mPresenter.getWeather(cityToDisplay);

    }

    @Override
    public void displayWeather(WeatherResponse data) {
      /*   setCurrentTemperatureValues(data.getMain().getTemp_max());
        setPressureValues(data.getMain().getPressure());
        setDescriptionValues(data.getWeatherObject().getDescription());*/
    }

    public void setCurrentTemperatureValues(double temperatureValues) {
        mCurrentTemperature.setText(getString(R.string.current_temperature_message, temperatureValues));
    }


    public void setMinTemperatureValues(double minTemperatureValues) {
        mMinTemperature.setText(getString(R.string.minimum_temperature_message, minTemperatureValues));
    }

    public void setMaxTemperatureValues(double maxTemperatureValues) {
        mMaxTemperature.setText(getString(R.string.maximum_temperature_message, maxTemperatureValues));
    }

    public void setPressureValues(double pressureValues) {
        mPressure.setText(getString(R.string.pressure_message, pressureValues));

    }

    public void setWindValues(double windValues) {
        mWind.setText(getString(R.string.wind_speed_message, windValues));
    }

    public void setWeatherIcon(String iconPath) {
        Glide.with(getActivity().getApplicationContext()).load(Constants.IMAGE_BASE_URL + iconPath).into(mWeatherIcon);
    }

    public void setDescriptionValues(String descriptionValues) {
        mDescription.setText(descriptionValues);
    }


    public void onFailure() {
        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.weather_fragment_loading_failure_toast_message), Toast.LENGTH_SHORT).show();
    }

    private void refreshCurrentData() {
        if (NetworkUtils.checkIfInternetConnectionIsAvailable(getActivity())) {
        }
    }


}
