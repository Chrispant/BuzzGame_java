import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        Game g = new Game();//Create the game

        GUI gui = new GUI(g);
        gui.start();


    }





}

