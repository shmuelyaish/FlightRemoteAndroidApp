package viewModel;

import model.Imodel;

import java.net.InetAddress;

public class VM_Remote {
    private Imodel model;
    public VM_Remote(Imodel model) {
        this.model = model;
    }
    //connect the model to the socket
    public boolean VM_connect(String ip, int port) {
        this.model.connect(ip, port);
        //then sleep so the socket could open and we can check if it's open.
        //the user will not feel these 30 milliseconds because it's so short
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            //if it didnt wait, not the end of the world
        } finally {
            //return if the socket was opened
            return this.model.getSocketOpened();
        }
    }
    public void VM_setAileron(double a) {
        try {
            this.model.setAileron(a);
        } catch(InterruptedException e){
            //if it didn't set it's not such a big deal
        };
    }
    public void VM_setElevator(double a) {
        try {
            this.model.setElevator(a);
        } catch(InterruptedException e){};
    }
    public void VM_setRudder(double a) {
        try {
            this.model.setRudder(a);
        } catch(InterruptedException e){};
    }
    public void VM_setThrottle(double a) {
        try {
            this.model.setThrottle(a);
        } catch(InterruptedException e){};
    }
    public void close() {
        this.model.disconnect();
    }
}
