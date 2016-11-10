package ilstu.edu.project2alarms.objects;

import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.os.Bundle;

import java.util.Calendar;

/**
 * Created by Michael McHugh on 11/7/2016.
 */

public class Alarm {
    private String message;
    private double latitude;
    private double longitude;
    private String timeStamp;
    private Calendar calendar;
    private int id;

    public Alarm(String message, Calendar calendar, int id, Location location) {
        this.message = message;
        this.id = id;
        this.calendar = calendar;
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Location getLocation() {
        return location;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public int getID() {
        return id;
    }

    @Override
    public String toString() {
        String outString = "";
        if (id == 0 || id == 1) {
            outString = calendar.get(Calendar.MONTH) + " "
                    + calendar.get(Calendar.DATE) + " "
                    + calendar.get(Calendar.HOUR) + ":"
                    + calendar.get(Calendar.MINUTE);
            if (id == 1) {
                outString += ", Repeating";
            }
            else if (id == 2) {
                outString = "Timer Set for " + calendar.get(Calendar.DATE) + " days, " +
                        calendar.get(calendar.MINUTE) + " minutes from now.";
            } else {
                outString = "Location timer set for " + calendar.get(Calendar.DATE) + " days, " +
                        calendar.get(calendar.MINUTE) + " minutes from now.";
            }
        }
        return outString;
    }
}
