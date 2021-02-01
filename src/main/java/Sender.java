import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class Sender {

    private final DatagramSocket clientSocket;
    private final InetAddress IPAddress;
    private final String name = UUID.randomUUID().toString();
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final static Logger LOGGER = Logger.getLogger(Sender.class.getName());

    public Sender() throws IOException {
         clientSocket = new DatagramSocket(9876);
         IPAddress = InetAddress.getByName("78.139.9.220");
    }

    void start() throws IOException {
       broadCastMessage("HELLO MY NAME IS "+name);
    }

    private void broadCastMessage(String message) throws IOException{

        AtomicInteger i = new AtomicInteger(0);
        Future future = executorService.scheduleAtFixedRate(()->{
            Integer time = i.getAndIncrement();
            byte[] sendData = String.valueOf(time).getBytes(StandardCharsets.UTF_8);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            try {
                clientSocket.send(sendPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            LOGGER.info("SENDING MESSAGE id: "+time);

            if(i.get()>20) {
                executorService.shutdown();
            }

        }, 0, 1, TimeUnit.SECONDS);


    }
}
