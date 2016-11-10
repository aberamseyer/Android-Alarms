package ilstu.edu.project2alarms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Abe on 11/7/2016.
 */

public class CreateTimerActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView timerText;
    private Button button0, button1, button2, button3, button4, button5, button6, button7, button8,
            button9, buttonDelete, setTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_timer);

        timerText = (TextView)findViewById(R.id.timerTime);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button0:
                Log.i("timer area", "button 0 tapped");
                if(!(timerText.getText().length() > 5))
                    timerText.setText(timerText.getText() + "0");
                break;
            case R.id.button1:
                Log.i("timer area", "button 1 tapped");
                if(!(timerText.getText().length() > 5))
                    timerText.setText(timerText.getText() + "1");
                break;
            case R.id.button2:
                Log.i("timer area", "button 2 tapped");
                if(!(timerText.getText().length() > 5))
                    timerText.setText(timerText.getText() + "2");
                break;
            case R.id.button3:
                Log.i("timer area", "button 3 tapped");
                if(!(timerText.getText().length() > 5))
                    timerText.setText(timerText.getText() + "3");
                break;
            case R.id.button4:
                Log.i("timer area", "button 4 tapped");
                if(!(timerText.getText().length() > 5))
                    timerText.setText(timerText.getText() + "4");
                break;
            case R.id.button5:
                Log.i("timer area", "button 5 tapped");
                if(!(timerText.getText().length() > 5))
                    timerText.setText(timerText.getText() + "5");
                break;
            case R.id.button6:
                Log.i("timer area", "button 6 tapped");
                if(!(timerText.getText().length() > 5))
                    timerText.setText(timerText.getText() + "6");
                break;
            case R.id.button7:
                Log.i("timer area", "button 7 tapped");
                if(!(timerText.getText().length() > 5))
                    timerText.setText(timerText.getText() + "7");
                break;
            case R.id.button8:
                Log.i("timer area", "button 8 tapped");
                if(!(timerText.getText().length() > 5))
                    timerText.setText(timerText.getText() + "8");
                break;
            case R.id.button9:
                Log.i("timer area", "button 9 tapped");
                if(!(timerText.getText().length() > 5))
                    timerText.setText(timerText.getText() + "9");
                break;
            case R.id.buttonDelete:
                Log.i("timer area", "delete button tapped");
                if(!(timerText.getText().length() < 1))
                    timerText.setText(timerText.getText().subSequence(0, timerText.getText().length() - 1));
                break;
            case R.id.setTimerButton:
                Log.i("timer area", "set timer button tapped");
                // TODO logic to set a timer
                // TODO createa  toast saying the time the timer was set for time
                Toast toast = Toast.makeText(this, "Timer set for " + timerText.getText() + " seconds", Toast.LENGTH_SHORT);
                toast.show();
                finish();
                break;
        }
    }
}
