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
    ArrayList<Alarm> alarms = new ArrayList<>();

    public void readAlarms() {
        String alarmString;
        Scanner input = null;
        try {
            input = new Scanner(new File("alarms.csv"));
        } catch (FileNotFoundException e) {
            Log.d("mmc", "Could Not Open Input File");
        }
        while (input.hasNextLine()) {
            alarmString = input.nextLine();
            createAlarm(alarmString);
        }
    }

    /*
     * Reads a line of a .csv file and puts each element into an array
     */
    private void createAlarm(String inputData) {
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
                createAlarmClock(alarmArray);
                break;
            case 2:
            case 3:
                createTimer(alarmArray);
                break;
        }
    }

    private void createAlarmClock(String[] alarmArray) {
        Alarm alarmClock;
        int id = Integer.valueOf(alarmArray[0]);
        String message = alarmArray[1];
        int year = Integer.valueOf(alarmArray[2]);
        int month = Integer.valueOf(alarmArray[3]);
        int date = Integer.valueOf(alarmArray[4]);
        int hour = Integer.valueOf(alarmArray[5]);
        int minute = Integer.valueOf(alarmArray[6]);
        TimeZone timeZone = TimeZone.getTimeZone(alarmArray[7]);
        boolean repeat = false;
        Location location;
    }

    private void createTimer(String[] alarmArray) {
        Alarm timer;
        int id = Integer.valueOf(alarmArray[0]);
        String message = alarmArray[1];
        int hour = Integer.valueOf(alarmArray[2]);
        int minute = Integer.valueOf(alarmArray[3]);
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
                    outputString += alarms[i].getID() + ",";
                    outputString += alarms[i].getCalendar().get(Calendar.YEAR) + ",";
                    outputString += alarms[i].getCalendar().get(Calendar.MONTH) + ",";
                    outputString += alarms[i].getCalendar().get(Calendar.DATE) + ",";
                    outputString += alarms[i].getCalendar().get(Calendar.HOUR) + ",";
                    outputString += alarms[i].getCalendar().get(Calendar.MINUTE) + ",";
                    outputString += alarms[i].getCalendar().getTimeZone().getID() + ",";
                    outputString += alarms[i].getLocation().toString();
                case 2:
                case 3:
                    outputString += alarms[i].getID() + ",";
                    outputString += alarms[i].getCalendar().get(Calendar.HOUR) + ",";
                    outputString += alarms[i].getCalendar().get(Calendar.MINUTE) + ",";
                    outputString += alarms[i].getLocation().toString();
                    break;
                default:
                    break;
            }
            output.println(outputString);
        }
        output.close();
    }
}
