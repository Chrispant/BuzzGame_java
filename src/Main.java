import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;




public class Main {
    static int interval;
    static Timer timer;


    public static void main(String[] args) throws IOException {
        Scanner name = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String roundName1 = "Right Question";
        String roundName2 = "Betting";

        System.out.println("Hello, let's play Buzz!");
        System.out.println("Press enter to continue...");try{System.in.read();}catch(Exception e){e.printStackTrace();}

        System.out.print("Enter Username : ");
        String userNam = name.nextLine();
        String userName = name.nextLine();

        System.out.println("Hello " + userName + ", are you ready to play? \nPress Enter to start playing Buzz or press Esc to exit.\n\n"); try{System.in.read();}catch(Exception e){e.printStackTrace();}
        System.out.println("Alright! Lets Play\n" +
                "B          U           Z           Z!!!");
        System.out.println("\n\nNext Round - "+ roundName1);

        timer = new Timer();
        interval = 6;

        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                System.out.println(setInterval()+"...");

            }
        }, 1000, 1000);





    }
    private static final int setInterval() {
        if (interval == 1)
            timer.cancel();
        return --interval;
    }
}

