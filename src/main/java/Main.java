import java.io.IOException;
import java.util.logging.Logger;

public class Main {

    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String... args) throws IOException, InterruptedException {

        Connection connection = new Connection();

        Thread.sleep(2000);

        connection.send("HELL OTHERE ");



    }
}
