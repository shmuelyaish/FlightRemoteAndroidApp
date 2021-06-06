package com.View;

import model.Imodel;
import model.RemoteModel;
import viewModel.VM_Remote;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Changed implements EventChange {
    private VM_Remote remote;
    public Changed(){
        this.remote = new VM_Remote(new RemoteModel());
    }
    @Override
    public void joystickChanged(double angle, double strength) {
        //we want to move a certain percent of whats gonna come out for rudder and elevator
        strength = strength/100;
        //up and down is elevator, right left is Aileron
        this.remote.VM_setElevator(strength * Math.sin(Math.toRadians(angle)));
        this.remote.VM_setAileron(strength * Math.cos(Math.toRadians(angle)));
    }

    @Override
    public void sideSeekerChanged(double a) {
        //throttle goes from 0 to 1, a's range is from 0-100,
        // divide by 100 to get percent
        this.remote.VM_setThrottle(a /100);

    }

    @Override
    public void bottomSeekerChanged(double a) {
        //rudder goes from -1 to 1, a's range is from 0-100,
        //subtract 50 and divide by 50 to get percent
        this.remote.VM_setRudder((a - 50) / 50);
    }

    @Override
    public boolean connect(String addr, int port) throws UnknownHostException {
        //returns if was unable to connect
           return this.remote.VM_connect(addr, port);
    }

    @Override
    public void disconnect() {
        this.remote.close();
    }
}
