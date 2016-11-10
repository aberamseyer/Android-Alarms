package ilstu.edu.project2alarms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Abe on 11/10/2016.
 */

public class EditLocationActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView minText;
    private Button button0, button1, button2, button3, button4, button5, button6, button7, button8,
            button9, buttonDelete, setTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_location);

        minText = (TextView)findViewById(R.id.minutesText);
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
        setTimer = (Button)findViewById(R.id.editLocationButton);
    }

    private void updateTimer(int num) {
        int minTextAsInt = Integer.parseInt(minText.getText().toString());
        boolean min2digits = (minTextAsInt > 9) ? true : false;
        boolean min1digit = (minTextAsInt != 0) ? true : false;

        if(!min2digits) {
            if(min1digit) {
                minText.setText(minText.getText().toString().charAt(1) + "" + num);
            } else {
                minText.setText(0 + "" + num);
            }
        }
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
                minText.setText("00");
                break;
            case R.id.setTimerButton:
                Log.i("timer area", "set timer button tapped");
                // TODO logic to set a timer
                // TODO createa  toast saying the time the timer was set for time
                Toast toast = Toast.makeText(this, "Location alarm set for " + minText.getText()
                        + " minutes", Toast.LENGTH_SHORT);
                toast.show();
                finish();
                break;
        }
    }
}
