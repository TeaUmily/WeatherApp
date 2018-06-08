package ada.osc.myfirstweatherapp;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by Filip on 10/02/2016.
 */
@RealmClass
public class LocationWrapper extends RealmObject {

    @Required
    @PrimaryKey
    private String location;



    public LocationWrapper(String location) {
        this.location = location;
    }

    public LocationWrapper() {
    }

    public String getLocation() {
        return location;
    }
}
