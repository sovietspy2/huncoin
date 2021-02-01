import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.logging.Logger;

class Receiver {

    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private DatagramSocket serverSocket;
    private final static Logger LOGGER = Logger.getLogger(Receiver.class.getName());

    public Receiver() throws SocketException {

    }

    public void start() throws IOException {

        byte[] receiveData = new byte[1024]; // important

        executorService.scheduleAtFixedRate(() -> {
            try {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String sentence = new String(receivePacket.getData());
                String capitalizedSentence = sentence.toUpperCase();
                LOGGER.warning("message id: " + capitalizedSentence);
                //return capitalizedSentence;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 1, TimeUnit.SECONDS);

        //System.out.println(resultFuture.isDone());
    }
}
