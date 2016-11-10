package ilstu.edu.project2alarms;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import ilstu.edu.project2alarms.objects.Alarm;

/**
 * Created by Abe on 11/8/2016.
 */

public class CreateAlarmActivity extends AppCompatActivity implements View.OnClickListener {

    //private TimePicker timePicker;
    private Button setDateButton, setTimeButton, setAlarmButton;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private int aYear, aMonth, aDay, aHour, aMinute;
    private Spinner timeZoneSpinner;
    private CheckBox timeZoneCheckBox;
    TextView timeText, dateText;
    EditText alarmMessage;
    Switch repeatSwitch;
    String stringToWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alarm);


        //timePicker = (TimePicker)findViewById(R.id.);
        stringToWrite = "";
        setDateButton = (Button)findViewById(R.id.setDateButton);
        setTimeButton = (Button)findViewById(R.id.setTimeButton);
        setAlarmButton = (Button)findViewById(R.id.setAlarmButton);
        timeText = (TextView)findViewById(R.id.timeText);
        dateText = (TextView)findViewById(R.id.dateText);
        alarmMessage = (EditText)findViewById(R.id.alarmMessage);
        repeatSwitch = (Switch)findViewById(R.id.repeatSwitch);
        timeZoneSpinner = (Spinner)findViewById(R.id.timeZoneSpinner);
        timeZoneCheckBox = (CheckBox)findViewById(R.id.timeZoneCheckBox);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.time_zones, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeZoneSpinner.setAdapter(adapter);

        setAlarmButton.setOnClickListener(this);
        setTimeButton.setOnClickListener(this);
        setDateButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        final Calendar c = Calendar.getInstance();
        switch(view.getId()) {
            case R.id.setTimeButton:
                // Get Current Time
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                String formatedMinute = "" + minute;
                                if (minute < 10)
                                    formatedMinute = "0" + minute;
                                if(hourOfDay > 12)
                                    timeText.setText((hourOfDay-12) + ":" + formatedMinute + " PM");
                                else
                                    timeText.setText(hourOfDay + ":" + formatedMinute + " AM");
                                aHour = hourOfDay;
                                aMinute = minute;
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
                break;

            case R.id.setDateButton:
                Log.i("aramsey", "clicked the date button");
                // Get Current Date
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                dateText.setText((monthOfYear + 1) + "-" + dayOfMonth  + "-" + year);
                                aMonth = monthOfYear;
                                aDay = dayOfMonth;
                                aYear = year;
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
            case R.id.setAlarmButton:
                c.set(aYear, aMonth, aDay, aHour, aMinute);
                if(timeZoneCheckBox.isChecked())
                    c.setTimeZone(TimeZone.getTimeZone("Etc/" + timeZoneSpinner.getSelectedItem().toString()));
                stringToWrite += "1," + alarmMessage.getText() + "," + c.get(Calendar.YEAR) + "," + c.get(Calendar.MONTH) +
                        "," + c.get(Calendar.DAY_OF_MONTH) + "," + c.get(Calendar.HOUR) + "," + c.get(Calendar.MINUTE) + ", timezone, "
                        + repeatSwitch.isActivated() + ", location"; //TODO timezone & location

                boolean repeat = repeatSwitch.isActivated();
                int id;
                if(repeat) {
                    id = 1;
                }
                else {
                    id = 0;
                }

                MainActivity.addAlarm(new Alarm(id, alarmMessage.toString(), c, 10, 20));

                Toast toast = Toast.makeText(this, "Alarm set for: " +
                        c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE) + " " + (c.get(Calendar.DAY_OF_MONTH)+1) +
                        "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.YEAR) + "\n in time zone: " + c.getTimeZone().getID(), Toast.LENGTH_LONG);
                toast.show();
                //TODO logic to get location
                finish();
                break;
        }
    }
}
