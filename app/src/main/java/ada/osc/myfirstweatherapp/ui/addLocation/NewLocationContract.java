package ada.osc.myfirstweatherapp.ui.addLocation;

import ada.osc.myfirstweatherapp.pojo.LocationWrapper;

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
