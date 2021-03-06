import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Application {


    private EventBus eventBus = new EventBus();

    private Connection connection;

    private Miner miner;

    public Application() throws InterruptedException, ExecutionException, IOException {
        connection = new Connection(eventBus);
        miner = new Miner();

        EventListener listener = new EventListener(connection, miner);
        eventBus.register(listener);

    }

    public void run() throws InterruptedException, IOException, ExecutionException {

        miner.start();

        Thread.sleep(2000);

        connection.send("HELL OTHERE ");

        Thread.sleep(2000);

        //System.out.println("IM IN MAIN" + connection.getListData().get(0));
    }

}
