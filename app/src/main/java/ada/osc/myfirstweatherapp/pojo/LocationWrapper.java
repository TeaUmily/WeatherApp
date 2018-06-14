package ada.osc.myfirstweatherapp.pojo;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

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
