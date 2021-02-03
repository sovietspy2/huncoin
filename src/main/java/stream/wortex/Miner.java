package stream.wortex;

public class Miner {

    private Thread thread;

    public Miner() {

    }

    public void start() {
        thread = new Thread(()->{
                System.out.println("Mining");
        });

        thread.start();
    }

    public void stop() {
        System.out.println("Stopping mining");
        thread.interrupt();
    }


}
