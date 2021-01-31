import java.io.IOException;
import java.util.logging.Logger;

public class Main {

        private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

        public static void main(String ...args) throws IOException, InterruptedException {

            LOGGER.info("LOL");


            Receiver server = new Receiver();
            Sender sender = new Sender();

            server.start();
            sender.start();


        }
}
