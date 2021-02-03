package stream.wortex;

import com.google.common.eventbus.Subscribe;
import stream.wortex.network.Connection;
import stream.wortex.network.Message;

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
    private void messageProcessor(Message message) throws InterruptedException, ExecutionException, IOException {


        switch (message.getType()) {
            case PING:

                // send empty message
                // expect pong response

                break;
            case PONG:

                // send known hosts

                // send ping to new hosts

                break;
            case NEW_BLOCK:

                //stop mining
                //verify
                // add to blockhhain
                // wait for transactions

                break;
        }

        System.out.println("stopping mining");
        System.out.println("Starting validation");

        if (eventsHandled%5==0) {
            miner.stop();
            connection.send("NEW BLOCK SENDING HACKING YOUR STUPID LIGHT BULP");
        } else {
            connection.send("yooooooooooooo bitch");
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
