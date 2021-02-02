import com.google.common.eventbus.EventBus;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Connection {

    private EventBus eventBus;

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    private final DatagramSocket serverSocket = new DatagramSocket(9876);

    private List<String> listData = new ArrayList<>();

    private final List<String> knownHosts = Arrays.asList("192.168.0.214", "92.249.248.176");

    private void receive() throws IOException, ExecutionException, InterruptedException {
        Future<String> data = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("Waiting for message");
                byte[] receiveData = new byte[1024]; // important
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                try {
                    serverSocket.receive(receivePacket);
                    //eventBus.post(new String(receiveData));
                    System.out.println("Message received: "+new String(receiveData));
                    eventBus.post(new MessageEvent("IN",receivePacket.getAddress().toString(), new String(receiveData) ));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return new String(receiveData);
            }
        });
        //System.out.println(data);
    }

    public Connection(EventBus eventBus) throws IOException, ExecutionException, InterruptedException {
        this.eventBus= eventBus;

        sayHi();

        receive();
    }

    private void sayHi() throws UnknownHostException {
        byte[] sendData = "HELLO THERE".getBytes(StandardCharsets.UTF_8);

        knownHosts.forEach(host->{
            DatagramPacket sendPacket = null;
            try {
                sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(host), 9876);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            try {
                serverSocket.send(sendPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        System.out.println("PING sent");

    }

    public void send(String message) throws IOException, ExecutionException, InterruptedException {

        executorService.shutdownNow();

        executorService = Executors.newSingleThreadExecutor();

        executorService.execute(() -> {
            System.out.println("Sending message: "+message);
            byte[] sendData = message.getBytes(StandardCharsets.UTF_8);
            try {
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(knownHosts.get(1)), 9876);
                serverSocket.send(sendPacket);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        receive();

    }

    public List<String> getListData() {
        return listData;
    }

    public void setListData(List<String> listData) {
        this.listData = listData;
    }
}
