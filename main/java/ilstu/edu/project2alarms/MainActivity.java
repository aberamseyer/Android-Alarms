package ilstu.edu.project2alarms;
// Abe wrote this

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

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
    private AlarmManager alarmManager;

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

        if(alarms.isEmpty()) {
            alarms.add(new Alarm(3, "Get up and walk!", 0, 5, 10, 20));
        }


//        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
//
//        try {
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 1, new LocationListener() {
//
//            @Override
//            public void onLocationChanged(Location location) {
//
//                String msg = "New Latitude: " + location.getLatitude()
//                        + "New Longitude: " + location.getLongitude();
//
//                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onProviderDisabled(String provider) {
//
//                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                startActivity(intent);
//                Toast.makeText(getBaseContext(), "Gps is turned off!! ",
//                        Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onProviderEnabled(String provider) {
//                Toast.makeText(getBaseContext(), "Gps is turned on!! ",
//                        Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onStatusChanged(String provider, int status, Bundle extras) {
//                // TODO Auto-generated method stub
//
//                }
//            });
//        } catch (SecurityException se) {
//            finish();
//        }


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
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

//    public void createAlarm(Calendar c) {
//        Intent myIntent = new Intent(MainActivity.this, AlarmReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, 0);
//        alarmManager.set(AlarmManager.RTC, c.getTimeInMillis(), pendingIntent);
//    }

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

//    public void clearAlarms() {
//        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
//
//    }

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

    public static ArrayList<Alarm> getAlarmList() {
        return alarms;
    }

    public static void addAlarm(Alarm alarm) {
        alarms.add(alarm);
    }

    public void readAlarms() {
        String fileData;
        String[] fileDataArray = null;

        String alarmString;
        FileReader input = null;
        try {
            input = new FileReader("alarms.csv");
            fileData = input.toString();
            input.close();
            fileDataArray = fileData.split("\n");
            for (int i = 0; i < fileDataArray.length; i++) {
                alarmString = fileDataArray[i];
                AlarmFileFactory.createAlarm(alarmString, alarms);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
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
                        outputString += alarm.getCalendar().get(Calendar.YEAR) + ","
                                + alarm.getCalendar().get(Calendar.MONTH) + ","
                                + alarm.getCalendar().get(Calendar.DATE) + ","
                                + alarm.getCalendar().get(Calendar.HOUR) + ","
                                + alarm.getCalendar().get(Calendar.MINUTE) + ","
                                + alarm.getCalendar().getTimeZone().getID() + ","
                                + alarm.getLatitude() + ","
                                + alarm.getLongitude();
                    case 2:
                    case 3:
                        outputString += alarm.getDays() + ","
                                + alarm.getMinutes() + ","
                                + alarm.getLatitude() + ","
                                + alarm.getLongitude();
                        break;
                    default:
                        break;
                }
                outputString += "\n";
                Log.d("mmc", outputString);
                outputStream.write(outputString.getBytes());
            }
            outputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
