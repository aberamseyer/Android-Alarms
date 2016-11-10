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

public class AlarmManager {
    public Alarm[] readAlarms() {
        ArrayList<Alarm> alarms = new ArrayList<>();
        Alarm[] alarmsArray;

        String alarmString;
        Scanner input = null;
        try {
            input = new Scanner(new File("alarms.csv"));
        } catch (FileNotFoundException e) {
            Log.d("mmc", "Could Not Open Input File");
        }
        while (input.hasNextLine()) {
            alarmString = input.nextLine();
            createAlarm(alarmString, alarms);
        }
        return alarms.toArray(new Alarm[alarms.size()]);
    }

    /*
     * Reads a line of a .csv file and puts each element into an array
     */
    private void createAlarm(String inputData, ArrayList<Alarm> alarms) {
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

    private Alarm createAlarmClock(String[] alarmArray) {
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

    private Alarm createTimer(String[] alarmArray) {
        Alarm timer;
        int id = Integer.valueOf(alarmArray[0]);
        String message = alarmArray[1];
        int hour = Integer.valueOf(alarmArray[2]);
        int minute = Integer.valueOf(alarmArray[3]);
        double latitude = Double.valueOf(alarmArray[4]);
        double longitude = Double.valueOf(alarmArray[5]);

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR, hour);
        c.set(Calendar.MINUTE, minute);

        return new Alarm(id, message, c, latitude, longitude);
    }

    public void saveAlarms(Alarm[] alarms) {
        PrintWriter output = null;
        String outputString;
        String message;
        try {
            output = new PrintWriter(new File("alarms.csv"));
        } catch (IOException e) {
            Log.d("mmc", "Could not Open Output File");
        }
        for (int i = 0; i < alarms.length; i++) {
            outputString = "";
            outputString += alarms[i].getID() + ",";

            message = alarms[i].getMessage();
            if (message.contains("\"")) {
                message = message.replaceAll("\"", "\"\"");
            }
            if (message.contains(",")) {
                message = "\"" + message + "\"";
            }
            outputString += message + ",";

            switch (alarms[i].getID()) {
                case 1:
                    outputString = alarms[i].getCalendar().get(Calendar.YEAR) + ","
                            + alarms[i].getCalendar().get(Calendar.MONTH) + ","
                            + alarms[i].getCalendar().get(Calendar.DATE) + ","
                            + alarms[i].getCalendar().get(Calendar.HOUR) + ","
                            + alarms[i].getCalendar().get(Calendar.MINUTE) + ","
                            + alarms[i].getCalendar().getTimeZone().getID() + ","
                            + alarms[i].getLatitude() + ","
                            + alarms[i].getLongitude();
                case 2:
                case 3:
                    outputString = alarms[i].getCalendar().get(Calendar.HOUR) + ","
                            + alarms[i].getCalendar().get(Calendar.MINUTE) + ","
                            + alarms[i].getLatitude() + ","
                            + alarms[i].getLongitude();
                    break;
                default:
                    break;
            }
            output.println(outputString);
        }
        output.close();
    }
}
