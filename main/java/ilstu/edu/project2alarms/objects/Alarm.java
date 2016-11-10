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
    private Location location;
    private Calendar calendar;
    private boolean repeat;
    private int id;

    public Alarm(String message, Calendar calendar, int id) {
        this.message = message;
        this.id = id;
        this.calendar = calendar;
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

    public int getId() {
        return id;
    }

    public boolean isRepeating() {
        return repeat;
    }
}
