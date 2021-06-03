import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java. net. ServerSocket;
import java.io.IOException;
import java.net.InetAddress;
public class main {
    public static void main(String args[]) {
        Thread th = new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println(InetAddress.getLocalHost());
                    ServerSocket serverSocket = new ServerSocket(2020);
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("connected");
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    while (true) {
                        String input = in.readLine();
                        if(input != null) {
                            System.out.println(input);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("cant create server");
                }
            }
        });
        th.start();
        /*
        try {
            for (int i = 0; i<1000000; i++);
            Socket fg = new Socket("192.168.56.1", 2020);

        } catch(IOException e) {
            System.out.println("cant create server");
        }
*/
    }
}
