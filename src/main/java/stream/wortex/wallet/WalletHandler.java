package stream.wortex.wallet;

import stream.wortex.Wallet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class WalletHandler {



    public static void loadOrCreateWaller() throws FileNotFoundException {

        Wallet wallet = new Wallet();

        FileOutputStream fos = new FileOutputStream("t.tmp");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(yourObject);
        oos.close();
    }


}
