import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class Main {
    static int interval;
    static Timer timer;


    public static void main(String[] args) throws InterruptedException, IOException {
        ArrayList<String> roundTypes = new ArrayList<>();
        Game g = new Game();
        GUI gui = new GUI(g);
        gui.start();

    }
}

