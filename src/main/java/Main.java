import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Main {

    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String... args) throws IOException, InterruptedException {

        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        DatagramSocket sendSocket = new DatagramSocket(9876);
        DatagramSocket receiveSocket = new DatagramSocket(9875);

        executorService.scheduleAtFixedRate(() -> {

            LOGGER.info("started sending ");

            try {
                byte[] data = "wrong port".getBytes(StandardCharsets.UTF_8);

                DatagramPacket sendPacket = new DatagramPacket(data, data.length, InetAddress.getByName("78.139.9.220"), 9875);
                sendSocket.send(sendPacket);

                LOGGER.info(" sending finished");

                LOGGER.info("waiting for msg ");

                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                receiveSocket.receive(receivePacket);

                LOGGER.info(new String(receivePacket.getData()));

                LOGGER.info("recieved sending ");

            } catch (IOException e) {
                e.printStackTrace();
            }


        }, 0, 10, TimeUnit.SECONDS);
    }
}
