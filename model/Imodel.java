package model;

import java.io.IOException;
import java.net.InetAddress;

public interface Imodel {
    //connect the remote
    void connect(final String ip, final int port);
    void setAileron(final double a) throws InterruptedException;
    void setElevator(final double a) throws InterruptedException;
    void setRudder(final double a) throws InterruptedException;
    void setThrottle(final double a) throws InterruptedException;
    void disconnect();
    boolean getSocketOpened();
}
