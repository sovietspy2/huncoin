import com.google.common.eventbus.Subscribe;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class EventListener {

    private static int eventsHandled = 0;

    private Connection connection;

    private Miner miner;

    public EventListener(Connection connection, Miner miner) {
        this.connection = connection;
        this.miner = miner;
    }

    @Subscribe
    public void stringEvent(String event) {
        eventsHandled++;
        System.out.println("Events handled: "+eventsHandled);
    }

    @Subscribe
    private void messageProcessor(MessageEvent messageEvent) throws InterruptedException, ExecutionException, IOException {

        System.out.println("stopping mining");
        System.out.println("Starting validation");

        if (eventsHandled%5==0) {
            miner.stop();
            connection.send("NEW BLOCK");
        } else {
            connection.send("fake message");
            System.out.println("not valid chaing continuing mining");
            miner.start();
        }

        eventsHandled++;
    }

    @Subscribe
    private void notUsedMessageProcessor(String stuff) {
        System.out.println("I RAN BUT I SHOULD NOT HAVE");
    }
}
