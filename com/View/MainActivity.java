package com.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.jackandphantom.joystickview.JoyStickView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventChange eventChange = new Changed();
        Joystick joyForRudder = new Joystick(eventChange, true);
        //set the rudder seekBar
        final SeekBar rudder =(SeekBar) findViewById(R.id.seekBar1);
        rudder.setOnSeekBarChangeListener(joyForRudder);
        //set the throttle's seekbar listener to a joystick
        Joystick joyForThrottle = new Joystick(eventChange, false);
        final SeekBar throttle = (SeekBar) findViewById(R.id.seekBar2);
        throttle.setOnSeekBarChangeListener(joyForThrottle);
        //create the joystick listener
        final JoyStickView userJoystick = findViewById(R.id.joystick);
        userJoystick.setOnMoveListener(joyForThrottle);
        //create the text boxes for IP and port
        EditText IP = (EditText)findViewById(R.id.editText);
        EditText port = (EditText)findViewById(R.id.editText2);
        //create a new connect and connect the listener to connect button
        final Connect con = new Connect(eventChange, IP, port);
        final Button but = (Button) findViewById(R.id.button);
        but.setOnClickListener(con);


        
    }
}
