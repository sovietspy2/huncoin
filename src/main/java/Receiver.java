import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.logging.Logger;

class Receiver {

    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private DatagramSocket serverSocket;
    private final static Logger LOGGER = Logger.getLogger(Receiver.class.getName());

    public Receiver() throws SocketException {
        serverSocket = new DatagramSocket(9876);
    }

    public void start() throws IOException {

        byte[] receiveData = new byte[1024]; // important

        executorService.scheduleAtFixedRate(()->{
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            try {
                serverSocket.receive(receivePacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String sentence = new String(receivePacket.getData());
            String capitalizedSentence = sentence.toUpperCase();
            LOGGER.info("message id: " + capitalizedSentence);
            //return capitalizedSentence;
        }, 0, 10, TimeUnit.SECONDS);

        //System.out.println(resultFuture.isDone());
    }
}
