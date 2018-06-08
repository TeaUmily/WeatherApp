package ada.osc.myfirstweatherapp.ui.addLocation;

import android.location.Location;

import ada.osc.myfirstweatherapp.LocationWrapper;

public interface NewLocationContract {

    interface View {

        void onSuccess();

        void onEmptyStringRequestError();

        void onLocationAlreadyExistsError();

    }

    interface Presenter{

        void setView(NewLocationContract.View view);

        void addNewLocation(LocationWrapper location);
    }

}
