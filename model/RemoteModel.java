package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class RemoteModel implements Imodel {
    //socket and output for the flight gear
    private Socket fg;
    private PrintWriter out;
    //if the queue is open for business
    private boolean queueIsOpen;
    //queue to read commands from
    private BlockingQueue<Runnable> dispatchQueue
            = new LinkedBlockingQueue<Runnable>();
    //if to stay connected or not to the queue
    private boolean stayConnect;
    private Thread th;
    //if the socket is currently open
    private boolean socketOpened;

    public RemoteModel(){
        //the socket is not open yet
        this.socketOpened = false;
        //the queue is open for business
        this.queueIsOpen = true;
        //socket is nothing yet
        this.fg = null;
        //and we should stay connected
        this.stayConnect = true;
        //thread that reads and does from queue
        this.th = new Thread(new Runnable() {
            public void run() {
                while (stayConnect) {
                    try {
                        // take() blocks, so no busy waiting
                        dispatchQueue.take().run();
                    } catch (InterruptedException e) {
                        //just switch to the next task if there's an exception thrown
                        //if the user has a problem he'll click the button again
                    }
                }
            }
        });
        this.th.start();
    }
    @Override
    public void connect(final String ip, final int port) {
        try {
            //add the connect_display to the queue
            dispatchQueue.put(new Runnable() {
                public void run() {
                    try {
                        //if we dont have a socket, create one
                        if(fg == null) {
                            //create the socket and send the output to the fg
                            fg = new Socket(ip, port);
                            out = new PrintWriter(fg.getOutputStream(), true);
                            socketOpened = true;
                        }
                    } catch (IOException e) {
                        /*
                        if the socket couldn't open the too bad
                        (secretly the VM will check if the socket is open
                        and if not it will return an error message
                         */
                    }
                }
            });

        } catch (InterruptedException ex) {
            /*
               if the socket couldn't open the too bad
               (secretly the VM will check if the socket is open
               and if not it will return an error message
            */
        }
    }

    @Override
    public void setAileron(final double a) throws InterruptedException{
        //make sure the sockets opened before trying to send to the socket
        if (this.socketOpened) {
            //put the command into the queue
            dispatchQueue.put(new Runnable() {
                public void run() {
                    out.print("set /controls/flight/aileron " + a + "\r\n");
                    out.flush();
                }
            });
        }
    }

    @Override
    public void setElevator(final double a) throws InterruptedException{
        //make sure the sockets opened before trying to send to the socket
        if (this.socketOpened) {
            //put the command into the queue
            dispatchQueue.put(new Runnable() {
                public void run() {
                    out.print("set /controls/flight/elevator " + a + "\r\n");
                    out.flush();
                }
            });
        }
    }

    @Override
    public void setRudder(final double a) throws InterruptedException{
        //make sure the sockets opened before trying to send to the socket
        if (this.socketOpened) {
            //put the command into the queue
            dispatchQueue.put(new Runnable() {
                public void run() {
                    out.print("set /controls/flight/rudder " + a + "\r\n");
                    out.flush();
                }
            });
        }
    }

    @Override
    public void setThrottle(final double a) throws InterruptedException {
        //make sure the sockets opened before trying to send to the socket
        if (this.socketOpened) {
            //put the command into the queue
            dispatchQueue.put(new Runnable() {
                public void run() {
                    out.print("set /controls/engines/current-engine/throttle " + a + "\r\n");
                    out.flush();
                }
            });
        }
    }
    @Override
    public void disconnect() {
        //close the queue and stop the program from running
        this.stayConnect = false;
        this.th.interrupt();
        try {
            this.fg.close();
            this.socketOpened = false;
        } catch (IOException e) {

        };
    }
    @Override
    public boolean getSocketOpened() {
        return this.socketOpened;
    }
}
