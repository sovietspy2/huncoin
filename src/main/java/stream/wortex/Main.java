package stream.wortex;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class Main {

    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String... args) throws IOException, InterruptedException, ExecutionException {
        Application application = new Application();
        application.run();
    }

}


