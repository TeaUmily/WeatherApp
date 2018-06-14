package ada.osc.myfirstweatherapp.presentation;

import ada.osc.myfirstweatherapp.pojo.LocationWrapper;
import ada.osc.myfirstweatherapp.interaction.ApiInteractor;
import ada.osc.myfirstweatherapp.locationRepository.AllLocations;
import ada.osc.myfirstweatherapp.ui.addLocation.NewLocationContract;

public class AddNewLocationPresenter implements NewLocationContract.Presenter {


    private final ApiInteractor mApiInteractor;
    private final AllLocations mAllLocations;
    private NewLocationContract.View mNewLocationView;


    public AddNewLocationPresenter(ApiInteractor apiInteractor) {
        this.mApiInteractor = apiInteractor;
        this.mAllLocations = AllLocations.getInstance();
    }

    @Override
    public void setView(NewLocationContract.View newTaskView) {
        this.mNewLocationView = newTaskView;
    }

    @Override
    public void addNewLocation(LocationWrapper location) {
        if (location == null || location.getLocation().isEmpty()) {
            mNewLocationView.onEmptyStringRequestError();

        }
        if (!mAllLocations.checkIfAlreadyExists(location)) {
            mNewLocationView.onLocationAlreadyExistsError();

        }
        else if(!(location == null) && !location.getLocation().isEmpty()
                && mAllLocations.checkIfAlreadyExists(location)){
            mAllLocations.addLocationToList(location);
            mNewLocationView.onSuccess();
        }



    }




}
