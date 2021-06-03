package com.View;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.net.UnknownHostException;

public class Connect implements View.OnClickListener {
    private EventChange changeCaller;
    private EditText IP;
    private EditText port;
    public Connect(EventChange e, EditText IP, EditText port) {
        this.changeCaller = e;
        this.IP = IP;
        this.port = port;
    }

    @Override
    public void onClick(View v) {
        //if the boxes are empty, write to enter ip and port
        if(this.IP.getText().toString().isEmpty() || this.port.getText().toString().isEmpty()) {
            Toast errorToast = Toast.makeText(v.getContext(),"Please enter IP and port", Toast.LENGTH_SHORT);
            errorToast.show();
        } else {
            //try connecting to IP and port
            try {
                boolean success = this.changeCaller.connect(this.IP.getText().toString(),
                        (int) Double.parseDouble(this.port.getText().toString()));
                //if the socket couldnt open, print error message
                if(!success) {
                    Toast errorToast = Toast.makeText(v.getContext(),"Check IP and port are valid", Toast.LENGTH_SHORT);
                    errorToast.show();
                }
            } catch(UnknownHostException e) {
                //if you can't connect, write the IP and port are invalid
                Toast errorToast = Toast.makeText(v.getContext(),"Check IP and port are valid", Toast.LENGTH_SHORT);
                errorToast.show();
            }
        }
    }
}
