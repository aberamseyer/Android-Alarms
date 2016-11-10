package ilstu.edu.project2alarms.objects;

import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.os.Bundle;

import java.util.Calendar;
import java.util.Timer;

/**
 * Created by Michael McHugh on 11/7/2016.
 */

public class Alarm {
    private String message;
    private double latitude;
    private double longitude;
    private String timeStamp;
    private Calendar calendar;
    int days;
    int minutes;
    private int id;

    public Alarm(int id, String message, Calendar calendar, Location location) {
        this.id = id;
        this.message = message;
        this.calendar = calendar;
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }
    public Alarm(int id, String message, Calendar calendar, double latitude, double longitude) {
        this.id = id;
        this.message = message;
        this.calendar = calendar;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public Alarm(int id, String message, int days, int minutes, Location location) {
        this.id = id;
        this.message = message;
        this.days = days;
        this.minutes = minutes;
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }
    public Alarm(int id, String message, int minutes, Location location) {
        this.id = id;
        this.message = message;
        this.minutes = minutes;
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }
    public Alarm(int id, String message, int days, int minutes, double latitude, double longitude) {
        this.id = id;
        this.message = message;
        this.days = days;
        this.minutes = minutes;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public Alarm(int id, String message, int minutes, double latitude, double longitude) {
        this.id = id;
        this.message = message;
        this.minutes = minutes;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
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

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    @Override
    public String toString() {
        String outString = "";
        Calendar today = Calendar.getInstance();
        if (id == 0 || id == 1) {
            outString = "Alarm set for " + (calendar.get(Calendar.MONTH) + 1) + "/"
                    + calendar.get(Calendar.DATE) + " at "
                    + calendar.get(Calendar.HOUR) + ":"
                    + calendar.get(Calendar.MINUTE);
            if (id == 1) {
                outString += ", Repeating";
            }
        } else if (id == 2) {
            outString = "Timer Set for " + days + " days, " +
                    minutes + " minutes from now.";
        } else {
            outString = "Location timer set for " + days + " days, " +
                    minutes + " minutes from now.";
        }
    return outString;
    }
}
