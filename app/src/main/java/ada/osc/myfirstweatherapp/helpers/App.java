package ada.osc.myfirstweatherapp.helpers;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import ada.osc.myfirstweatherapp.interaction.ApiInteractor;
import ada.osc.myfirstweatherapp.interaction.ApiInteractorImpl;
import ada.osc.myfirstweatherapp.networking.ApiService;
import ada.osc.myfirstweatherapp.networking.RetrofitUtil;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Retrofit;

/**
 * Created by Filip on 01/04/2016.
 */
public class App extends Application {

    private static ApiInteractor apiInteractor;



    @Override
    public void onCreate() {
        super.onCreate();

            Realm.init(this);
            RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                    .name("weatherApp.realm")
                    .schemaVersion(0)
                    .build();
            Realm.setDefaultConfiguration(realmConfig);

        final Retrofit retrofit = RetrofitUtil.createRetrofit();
        final ApiService apiService = retrofit.create(ApiService.class);

        apiInteractor = new ApiInteractorImpl(apiService);

    }

    public static ApiInteractor getApiInteractor() {
        return apiInteractor;
    }

}
