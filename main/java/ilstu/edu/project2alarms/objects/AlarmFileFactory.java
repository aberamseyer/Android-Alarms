package ilstu.edu.project2alarms.objects;

import android.location.Location;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.TimeZone;

/**
 * Created by emolu on 11/9/2016.
 */

public class AlarmFileFactory {

    /*
     * Reads a line of a .csv file and puts each element into an array
     */
    public static void createAlarm(String inputData, ArrayList<Alarm> alarms) {
        ArrayList<String> data = new ArrayList<>();
        String[] alarmArray;
        int index = 0;

        while (!inputData.equals("")) {
            if (inputData.charAt(0) == '\"') {
                index = inputData.indexOf("\",", index);
                if (inputData.substring(index - 1, index + 2).equals("\"\",") && !inputData.substring(index - 2, index + 2).equals("\"\"\",")) {
                    index++;
                } else {
                    data.add(inputData.substring(1, index).replaceAll("[\"+\"]+", "\""));
                    if (inputData.contains(",")) {
                        inputData = inputData.substring(index + 2);
                    }
                }
            } else {
                if (!inputData.contains(",")) {
                    data.add(inputData.replaceAll("[\"+\"]+", "\""));
                    inputData = "";
                } else {
                    index = inputData.indexOf(",");
                    data.add(inputData.substring(0, index).replaceAll("[\"+\"]+", "\""));
                    inputData = inputData.substring(index + 1);
                }
            }
        }
        alarmArray = data.toArray(new String[data.size()]);
        switch (Integer.valueOf(alarmArray[0])) {
            case 0:
            case 1:
                alarms.add(createAlarmClock(alarmArray));
                break;
            case 2:
            case 3:
                alarms.add(createTimer(alarmArray));
                break;
        }
    }

    private static Alarm createAlarmClock(String[] alarmArray) {
        int id = Integer.valueOf(alarmArray[0]);
        String message = alarmArray[1];
        int year = Integer.valueOf(alarmArray[2]);
        int month = Integer.valueOf(alarmArray[3]);
        int date = Integer.valueOf(alarmArray[4]);
        int hour = Integer.valueOf(alarmArray[5]);
        int minute = Integer.valueOf(alarmArray[6]);
        TimeZone timeZone = TimeZone.getTimeZone(alarmArray[7]);

        Calendar c = Calendar.getInstance();
        c.set(year, month, date, hour, minute);
        c.setTimeZone(timeZone);

        double latitude = Double.valueOf(alarmArray[8]);
        double longitude = Double.valueOf(alarmArray[9]);

        return new Alarm(id, message, c, latitude, longitude);
    }

    private static Alarm createTimer(String[] alarmArray) {
        Alarm timer;
        int id = Integer.valueOf(alarmArray[0]);
        String message = alarmArray[1];
        int days = Integer.valueOf(alarmArray[2]);
        int minutes = Integer.valueOf(alarmArray[3]);
        double latitude = Double.valueOf(alarmArray[4]);
        double longitude = Double.valueOf(alarmArray[5]);

        return new Alarm(id, message, days, minutes, latitude, longitude);
    }
}
