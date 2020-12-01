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

        int roundCount = 6;



        System.out.println("Hello, let's play Buzz!");
        System.out.println("Press enter to continue...");try{System.in.read();}catch(Exception e){e.printStackTrace();}



        /**
         * Counts how many players will play and what their names will be.
         */
        System.out.println("Enter Number of Players : ");
        int playerCount = scanner.nextInt();
        Player p[] = new Player[playerCount];
        System.out.println("Game of "+playerCount+" players");

        for(int i=0; i<playerCount; i++) {
            counter++;
            System.out.print("Player "+counter+" Enter Username : ");
            userName = scanner.next();
            p[i]= new Player(userName, 0);
            p[i].addPoints(15);
        }


        for(int i=0; i<2; i++) {
            int roundCounter =0;
            System.out.println("\nRound Type - " + roundTypes.get(i));
            countDown();
            TimeUnit.SECONDS.sleep(6);
            for (int j = 0; j < roundCount; j++) {
                roundCounter++;
                System.out.println("\nRound "+roundCounter+" - "+roundTypes.get(i));
                System.out.println("Category - "+roundCategories.get(categoryNumber));
                String answer = scanner.next();
            }
            roundTypes.remove(i);
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

