package ilstu.edu.project2alarms;

import android.location.Location;

/**
 * Created by kyle on 11/8/2016.
 */

// import location
public class alarmBase
{
    private Location location;
    private String AlarmTime;
    private String message;




    /**
     * @param location
     */
    public alarmBase() {
        super();
        updateLocation();
    }
    /**
     * @return the location
     */
    private Location getLocation() {
        return location;
    }
    /**
     * @param location the location to set
     */
    private void updateLocation() {
        location =" current location";
    }
    /**
     * @return the alarmTime
     */
    private String getAlarmTime() {
        return AlarmTime;
    }
    /**
     * @param alarmTime the alarmTime to set
     */
    private void setAlarmTime(String alarmTime) {
        AlarmTime = alarmTime;
    }
    /**
     * @return the message
     */
    private String getMessage() {
        return message;
    }
    /**
     * @param message the message to set
     */
    private void setMessage(String message) {
        this.message = message;
    }

}
