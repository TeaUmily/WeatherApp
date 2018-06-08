package ada.osc.myfirstweatherapp.locationRepository;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import ada.osc.myfirstweatherapp.LocationWrapper;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class AllLocations {

    private static AllLocations sRepository = null;

    private Realm mRealm;


    private AllLocations() {
        this.mRealm = Realm.getDefaultInstance();
    }

    public Realm getmRealm() {
        return mRealm;
    }

    public void setmRealm(Realm mRealm) {
        this.mRealm = mRealm;
    }


    public static synchronized AllLocations getInstance(){
        if(sRepository == null){
            sRepository = new AllLocations();
        }
        return sRepository;
    }

    public void addLocationToList(LocationWrapper location){
        mRealm.beginTransaction();
        mRealm.copyToRealm(location);
        mRealm.commitTransaction();
    }

    public List<LocationWrapper> getAllLocations(){
        RealmResults<LocationWrapper> locationWrappersQuery = mRealm.where(LocationWrapper.class).findAll();
        List<LocationWrapper> locationWrappers = locationWrappersQuery;
        return locationWrappers;
    }

    public boolean checkIfAlreadyExists(LocationWrapper locationWrapper){
        RealmQuery<LocationWrapper> query = mRealm.where(LocationWrapper.class);
        query.equalTo("location", locationWrapper.getLocation());
        RealmResults<LocationWrapper> location = query.findAll();
        return location.isEmpty();
    }

}