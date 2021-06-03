package com.View;

import android.widget.SeekBar;
import com.jackandphantom.joystickview.JoyStickView;

public class Joystick implements SeekBar.OnSeekBarChangeListener, JoyStickView.OnMoveListener {
    private EventChange changeCaller;
    private boolean bottomSlider;

    public Joystick(EventChange e, boolean rudder) {
        this.changeCaller = e;
        this.bottomSlider = rudder;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(fromUser == true) {
            //if we're talking about seekbar 1, the bottom one (the rudder) set the bottom seekbar
            if (this.bottomSlider == true) {
                this.changeCaller.bottomSeekerChanged(seekBar.getProgress());
            } else {
                //were talking about the side seekbar (the throttle)
                this.changeCaller.sideSeekerChanged(seekBar.getProgress());
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onMove(double angle, float strength) {
        this.changeCaller.joystickChanged(angle, strength);
    }
}
