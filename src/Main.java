import java.io.IOException;
import java.util.*;


public class Main {
    static int interval;
    static Timer timer;
    static int counter = 0;


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String userName;
        ArrayList<String> roundTypes = new ArrayList<>();
        roundTypes.add("Μουσική");
        roundTypes.add("Επιστίμη");
        Player p[] = new Player[2];
        String roundName1 = "Right Answer";
        String roundName2 = "Betting";

        System.out.println("Hello, let's play Buzz!");
        System.out.println("Press enter to continue...");try{System.in.read();}catch(Exception e){e.printStackTrace();}

        System.out.println("Enter Number of Players : ");
        int playerCount = scanner.nextInt();

        System.out.println("Game of "+playerCount+" players");

        for(int i=0; i<playerCount; i++) {
            counter++;
            System.out.print("Player "+counter+" Enter Username : ");
            userName = scanner.next();
            p[i]= new Player(userName, 0);
            p[i].addPoints(15);
        }
        p[1].addPoints(10);
        System.out.println("\n\nNext Round - ");
        System.out.println("\n Points - "+ p[1].getPoints());

        timer = new Timer();
        interval = 6;
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                System.out.println(+setInterval()+"...");
                counter = counter - 1;
            }
        }, 1000, 1000);


    }

    private static final int setInterval() {
        if (interval == 2)
            timer.cancel();
        interval = interval -1;
        return interval;

    }


}

