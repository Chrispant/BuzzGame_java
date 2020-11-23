import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner name = new Scanner(System.in);

        System.out.println("Hello, let's play Buzz!");
        System.out.println("Press enter to continue...");try{        System.in.read();}catch(Exception e){	e.printStackTrace();}
        System.out.println("Enter Username");

        String userName = name.nextLine();

        System.out.println("Hello " + userName + ", are you ready to play? \nPress Enter to start playing Buzz or press Esc to exit."); try{        System.in.read();}catch(Exception e){	e.printStackTrace();}

    }
}
