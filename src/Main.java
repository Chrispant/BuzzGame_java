import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class Main {
    static int interval;
    static Timer timer;
    static int counter = 0;


    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String userName;
        ArrayList<String> roundCategories = new ArrayList<>();
        roundCategories.add("Μουσική");
        roundCategories.add("Επιστίμη");
        roundCategories.add("Ιστορία");
        int categoryNumber =(int) (Math.random()*2);
        ArrayList<String> roundTypes = new ArrayList<>();
        roundTypes.add("Σωστή Απάντηση");
        roundTypes.add("Ποντάρισμα");
        Collections.shuffle(roundTypes);
        Game g = new Game();

        int roundCount = 2;

        System.out.println("Hello, let's play Buzz!");
        System.out.println("Press enter to continue...");try{System.in.read();}catch(Exception e){e.printStackTrace();}


        g.addPlayers();

        for(int i=0; i<roundCount; i++) {
            //System.out.println("\nRound Type - " + roundTypes.get(i));
            //System.out.println("Category - "+roundCategories.get(categoryNumber));
            countDown();
            TimeUnit.SECONDS.sleep(6);
            g.startQuestion(i);
        }


    }

    private static final int setInterval() {
        if (interval == 2)
            timer.cancel();
        interval = interval -1;
        return interval;

    }

    /**
     * Countdown to use after each round/question
     */
    public static void countDown(){
        timer = new Timer();
        interval = 6;
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println(+setInterval()+"...");
            }
        }, 1000, 1000);

    }


}

