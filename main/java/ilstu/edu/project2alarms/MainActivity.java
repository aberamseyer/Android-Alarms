package ilstu.edu.project2alarms;
// Abe wrote this

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import ilstu.edu.project2alarms.objects.Alarm;
import ilstu.edu.project2alarms.objects.AlarmFileFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Boolean isFabOpen = false;
    private FloatingActionButton fab, fab1, fab2, fab3;
    private Animation fab1_open, fab2_open, fab3_open, fab_close, rotate_forward, rotate_backward;
    private static ArrayList<Alarm> alarms = new ArrayList<>();

    public static LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Calendar c = Calendar.getInstance();

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab1 = (FloatingActionButton)findViewById(R.id.fab1);
        fab2 = (FloatingActionButton)findViewById(R.id.fab2);
        fab3 = (FloatingActionButton)findViewById(R.id.fab3);
        fab1_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab1_open);
        fab2_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab2_open);
        fab3_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab3_open);

        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);

        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // TODO needs to save the current list of alarms in the app to the alarms csv file
    }

    @Override
    protected void onStop() {
        super.onStop();
        // TODO will have same logic as onPause
    }

    @Override
    public void onClick(View v) {
        int tappedButton = v.getId();
        Intent i;
        switch (tappedButton) {
            case R.id.fab:
                animateFab();
                Log.d("aramsey", "Big fAB tapped");
                break;
            case R.id.fab1:
                Log.d("aramsey", "fab 1 tapped");
                animateFab();
                i = new Intent(this,CreateAlarmActivity.class);
                startActivity(i);
                break;
            case R.id.fab2:
                Log.d("aramsey", "fab 2 tapped");
                animateFab();
                i = new Intent(this,CreateTimerActivity.class);
                startActivity(i);
                break;
            case R.id.fab3:
                animateFab();
                i = new Intent(this,EditLocationActivity.class);
                startActivity(i);
                break;
        }
    }

    public void animateFab() {
        if(isFabOpen) {
            fab.startAnimation(rotate_backward);
            fab3.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            fab3.setClickable(false);
            isFabOpen = false;
        }
        else {
            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab1_open);
            fab2.startAnimation(fab2_open);
            fab3.startAnimation(fab3_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            fab3.setClickable(true);
            isFabOpen = true;
            System.out.println("opened the big one");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
//            case R.id.action_settings:
//                openSettings();
//                return true;
//            case R.id.action_googleCalendar:
//                syncWithCalendar();
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static Alarm[] getAlarms() {
        return alarms.toArray(new Alarm[alarms.size()]);
    }

    public static void addAlarm(Alarm alarm) {
        alarms.add(alarm);
    }

    public Alarm[] readAlarms() {
        ArrayList<Alarm> alarms = new ArrayList<>();
        Alarm[] alarmsArray;
        String fileData;
        String[] fileDataArray = null;

        String alarmString;
        FileReader input = null;
        try {
            input = new FileReader("alarms.csv");
            fileData = input.toString();
            input.close();
            fileDataArray = fileData.split("\n");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < fileDataArray.length; i++) {
            alarmString = fileDataArray[i];
            AlarmFileFactory.createAlarm(alarmString, alarms);
        }
        return alarms.toArray(new Alarm[alarms.size()]);
    }

    public void saveAlarms() {
        FileOutputStream outputStream;
        String outputString;
        String message;
        try {
            outputStream = openFileOutput("alarms.csv", Context.MODE_PRIVATE);
            for (Alarm alarm : alarms) {
                outputString = "";
                outputString += alarm.getID() + ",";

                message = alarm.getMessage();
                if (message.contains("\"")) {
                    message = message.replaceAll("\"", "\"\"");
                }
                if (message.contains(",")) {
                    message = "\"" + message + "\"";
                }
                outputString += message + ",";

                switch (alarm.getID()) {
                    case 0:
                    case 1:
                        outputString = alarm.getCalendar().get(Calendar.YEAR) + ","
                                + alarm.getCalendar().get(Calendar.MONTH) + ","
                                + alarm.getCalendar().get(Calendar.DATE) + ","
                                + alarm.getCalendar().get(Calendar.HOUR) + ","
                                + alarm.getCalendar().get(Calendar.MINUTE) + ","
                                + alarm.getCalendar().getTimeZone().getID() + ","
                                + alarm.getLatitude() + ","
                                + alarm.getLongitude();
                    case 2:
                    case 3:
                        outputString = alarm.getCalendar().get(Calendar.HOUR) + ","
                                + alarm.getCalendar().get(Calendar.MINUTE) + ","
                                + alarm.getLatitude() + ","
                                + alarm.getLongitude();
                        break;
                    default:
                        break;
                }
                outputString += "\n";
                outputStream.write(outputString.getBytes());
            }
            outputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
