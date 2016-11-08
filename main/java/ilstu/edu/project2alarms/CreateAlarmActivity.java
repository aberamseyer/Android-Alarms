package ilstu.edu.project2alarms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TimePicker;

/**
 * Created by Abe on 11/8/2016.
 */

public class CreateAlarmActivity extends AppCompatActivity implements View.OnClickListener {

    private TimePicker timePicker;
    private CheckBox checkBoxSun, checkBoxMon, checkBoxTue, checkBoxWed, checkBoxThur, checkBoxFri,
                        checkBoxSat;
    private Button setTimerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alarm);

        timePicker = (TimePicker)findViewById(R.id.timePicker);
        checkBoxSun = (CheckBox)findViewById(R.id.checkBoxSun);
        checkBoxMon = (CheckBox)findViewById(R.id.checkBoxMon);
        checkBoxTue = (CheckBox)findViewById(R.id.checkBoxTue);
        checkBoxWed = (CheckBox)findViewById(R.id.checkBoxWed);
        checkBoxThur = (CheckBox)findViewById(R.id.checkBoxThur);
        checkBoxFri = (CheckBox)findViewById(R.id.checkBoxFri);
        checkBoxSat = (CheckBox)findViewById(R.id.checkBoxSat);
        setTimerButton = (Button)findViewById(R.id.setAlarmButton);

        checkBoxSun.setOnClickListener(this);
        checkBoxMon.setOnClickListener(this);
        checkBoxTue.setOnClickListener(this);
        checkBoxWed.setOnClickListener(this);
        checkBoxThur.setOnClickListener(this);
        checkBoxFri.setOnClickListener(this);
        checkBoxSat.setOnClickListener(this);
        setTimerButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.setAlarmButton:
                break;
//            case R.id.checkBoxSun:
//                break;
//            case R.id.checkBoxMon:
//                break;
//            case R.id.checkBoxTue:
//                break;
//            case R.id.checkBoxWed:
//                break;
//            case R.id.checkBoxThur:
//                break;
//            case R.id.checkBoxFri:
//                break;
//            case R.id.checkBoxSat:
//                break;
//            case R.id.setAlarmButton:
//                break;
        }
    }
}
