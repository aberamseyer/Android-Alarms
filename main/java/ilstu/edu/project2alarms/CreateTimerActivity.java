package ilstu.edu.project2alarms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import ilstu.edu.project2alarms.objects.Alarm;

/**
 * Created by Abe on 11/7/2016.
 */

public class CreateTimerActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView minText, daysText;
    EditText message;
    private Button button0, button1, button2, button3, button4, button5, button6, button7, button8,
            button9, buttonDelete, setTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_timer);

        message = (EditText)findViewById(R.id.editText);
        minText = (TextView)findViewById(R.id.minutesText);
        daysText = (TextView)findViewById(R.id.daysText);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button5);
        button6 = (Button)findViewById(R.id.button6);
        button7 = (Button)findViewById(R.id.button7);
        button8 = (Button)findViewById(R.id.button8);
        button9 = (Button)findViewById(R.id.button9);
        buttonDelete = (Button)findViewById(R.id.buttonDelete);
        setTimer = (Button)findViewById(R.id.setTimerButton);


        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        setTimer.setOnClickListener(this);


    }

    private void updateTimer(int num) {
        int minTextAsInt = Integer.parseInt(minText.getText().toString());
        int daysTextAsInt = Integer.parseInt(daysText.getText().toString());
        boolean min2digits = (minTextAsInt > 9) ? true : false;
        boolean days2digits = (daysTextAsInt > 9) ? true : false;
        boolean daysHasADigit = (daysTextAsInt != 0) ? true : false;


        if(!days2digits) {
            if(daysHasADigit) {
                daysText.setText(daysText.getText().toString().charAt(1) + "" +
                                minText.getText().toString().charAt(0));
                minText.setText(minText.getText().toString().charAt(1) + "" + num);
            }
            else if(min2digits) {
                daysText.setText(0 + "" + minText.getText().toString().charAt(0));
                minText.setText(minText.getText().toString().charAt(1) + "" + num);
            } else {
                minText.setText(minText.getText().toString().charAt(1) + "" + num);
            }
        }
        min2digits = (minTextAsInt > 9) ? true : false;
        days2digits = (daysTextAsInt > 9) ? true : false;
        daysHasADigit = (daysTextAsInt != 0) ? true : false;
        Log.i("booleans", min2digits + " " + days2digits + " " + daysHasADigit);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button0:
                Log.i("timer area", "button 0 tapped");
                updateTimer(0);
                break;
            case R.id.button1:
                Log.i("timer area", "button 1 tapped");
                updateTimer(1);
                break;
            case R.id.button2:
                Log.i("timer area", "button 2 tapped");
                updateTimer(2);
                break;
            case R.id.button3:
                Log.i("timer area", "button 3 tapped");
                updateTimer(3);
                break;
            case R.id.button4:
                Log.i("timer area", "button 4 tapped");
                updateTimer(4);
                break;
            case R.id.button5:
                Log.i("timer area", "button 5 tapped");
                updateTimer(5);
                break;
            case R.id.button6:
                Log.i("timer area", "button 6 tapped");
                updateTimer(6);
                break;
            case R.id.button7:
                Log.i("timer area", "button 7 tapped");
                updateTimer(7);
                break;
            case R.id.button8:
                Log.i("timer area", "button 8 tapped");
                updateTimer(8);
                break;
            case R.id.button9:
                Log.i("timer area", "button 9 tapped");
                updateTimer(9);
                break;
            case R.id.buttonDelete:
                Log.i("timer area", "delete button tapped");
                daysText.setText("00");
                minText.setText("00");
                break;
            case R.id.setTimerButton:
                Log.i("timer area", "set timer button tapped");
                // TODO logic to set a timer
                // TODO createa  toast saying the time the timer was set for time

                MainActivity.addAlarm(new Alarm(2, message.toString(),Integer.parseInt(daysText.getText().toString()),
                        Integer.parseInt(minText.getText().toString()), 10, 20));
                Toast toast = Toast.makeText(this, "Timer set for " + daysText.getText() + " days" +
                        " and " + minText.getText() + " minutes", Toast.LENGTH_SHORT);
                toast.show();
                finish();
                break;
        }
    }
}
