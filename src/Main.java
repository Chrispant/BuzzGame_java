import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class Main {
    static int interval;
    static Timer timer;


    public static void main(String[] args) throws InterruptedException {
        ArrayList<String> roundTypes = new ArrayList<>();
        Game g = new Game();//Create the game

        int roundCount = 2;

        System.out.println("\nHello, let's play Buzz!");
        System.out.println("This is a quiz game. Get the most points to Win!");
        System.out.println("\nCurrently there are 2 types in this game\n");
        System.out.println("Σωστή Απάντηση : Win 1000 points each time you answer a question right");
        System.out.println("Ποντάρισμα : Bet a specific amount(250, 500, 750, 1000) of points and answer correctly to add them in your total point score. Answer wrong and you lose those points");
        System.out.println("Press enter to continue...");try{System.in.read();}catch(Exception e){e.printStackTrace();}




        // Add the players to the game
        g.addPlayers();
        System.out.println("First round!");
        System.out.println("To choose an answer simply type 1,2,3 or 4 depending on you decision and hit enter!");


        //Start the game.
        for(int i=0; i<roundCount; i++) {
            //System.out.println("\nRound Type - " + roundTypes.get(i));
            //System.out.println("Category - "+roundCategories.get(categoryNumber));
            System.out.println("");
            countDown();
            TimeUnit.SECONDS.sleep(6);

            try{
                g.startQuestion(i);
            }catch (InterruptedException e){
                System.out.println("Game failed. Continue to the next round!");
                e.printStackTrace();
            }

            System.out.println("Second round!");

        }
        g.gameOver();//Print the total points of the player after the game is ended


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

