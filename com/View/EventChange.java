package com.View;

import java.net.UnknownHostException;

public interface EventChange {
    void joystickChanged(double a, double e);
    void sideSeekerChanged(double a);
    void bottomSeekerChanged(double a);
    boolean connect(String a, int b) throws UnknownHostException;
    void disconnect();
}
