import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class Connection {

    private Thread thread;

    private final DatagramSocket serverSocket = new DatagramSocket(9876);

    private void receive() throws IOException {
        Runnable run = () -> {
            System.out.println("REC");
            byte[] receiveData = new byte[1024]; // important
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            try {
                serverSocket.receive(receivePacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
           System.out.println(new String(receiveData));

        };

        thread = new Thread(run);
        thread.start();
    }

    public Connection() throws IOException {
        receive();
    }

    public void send(String message) throws IOException {

        Runnable run = () -> {
            System.out.println("SEND");
            byte[] sendData = message.getBytes(StandardCharsets.UTF_8);
            try {
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), 9876);
                serverSocket.send(sendPacket);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        thread = new Thread(run);
        thread.start();

        receive();

    }

}
