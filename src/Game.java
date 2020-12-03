import java.lang.reflect.Array;
import java.util.*;

public class Game {
    private ArrayList<Question> questionSet;
    private HashMap<String, HashSet<Question>> questionSett;
    private ArrayList<Player> players;
    private ArrayList<Round> rounds;
    private int typeCounter = 0;
    private boolean checkAnswer;

    public Game(){
        //Method for future use(Make each question correspond to a single category)
        questionSett = new HashMap<String,HashSet<Question>>();

        questionSet = new ArrayList<Question>();
        String question1 = "A = ?";
        String[] choices1 = {"A","B","C","D"};
        questionSet.add(new Question(question1,"A",choices1));
        String question2 = "Why are you geh?";
        String[] choices2 = {"cause why not","who says im geh","shut up!","noooo"};
        questionSet.add(new Question(question2,"who says im geh",choices2));
        String question3 = "Is blue red?";
        String[] choices3 = {"I will not dignify that with a response","is you stupid?","what?","no"};
        questionSet.add(new Question(question3,"I will not dignify that with a response",choices3));
        String question4 = "How many miles does a cow run when it is being chased?";
        String[] choices4 = {"2 miles","Cows don't run","Depends on the weather","~0.005"};
        questionSet.add(new Question(question4,"~0.005",choices4));
        String question5 = "How many apples in a cookie jar?";
        String[] choices5 = {"5 cookies","who puts apples in a cookie jar?","0","As many as possible"};
        questionSet.add(new Question(question5,"0",choices5));
        Collections.shuffle(questionSet);

        players = new ArrayList<Player>();

        rounds = new ArrayList<Round>();
        rounds.add(new Round("Σωστή Απάντηση"));
        rounds.add(new Round("Ποντάρισμα"));
        Collections.shuffle(rounds);
    }

    public void startQuestion(int roundType){
        Scanner scanner = new Scanner(System.in);

        //Prints type of round
        System.out.println("Game Type - "+rounds.get(roundType).getRoundType());
        //Prints a Question
        for(int counter = 0; counter<5; counter++) {
            System.out.println("Question " + counter + " : " + questionSet.get(counter).getQuestion());
            //Stores number of choices for a given question, this help us out later
            int numOfChoices = questionSet.get(counter).getChoices().size();

            //Prints the choices
            for (int choice = 0; choice < numOfChoices; choice++) {
                System.out.println((choice + 1) + " : " + questionSet.get(counter).getChoices().get(choice));
            }
            //Sets the right Answer
            ArrayList<String> choices = questionSet.get(counter).getChoices();
            String correctAnswer = questionSet.get(counter).getAnswer();
            int correctAnswerNumber = choices.indexOf(correctAnswer) + 1;

            // Sets each players answer
            for (int player = 0; player < players.size(); player++) {
                System.out.println("Player's " + player + " answer is : ");
                players.get(player).setAnswer(scanner.nextInt());
            }
            //Checks if player answer right or wrong
            for (int player = 0; player < players.size(); player++) {
                if (players.get(player).getAnswer() == correctAnswerNumber) {
                    checkAnswer = true;
                } else {
                    checkAnswer = false;
                }
                //checkRoundType();
            }
            //See what the correct answer is
            System.out.println("Correct answer is \n" + correctAnswerNumber + " : " + correctAnswer);
        }

    }
    /**
     * Counts how many players will play and what their names will be.
     */

    public void addPlayers(){
        int playerCounter = 0;
        String userName;
        System.out.println("Enter Number of Players : ");
        Scanner scanner = new Scanner(System.in);
        int playerCount = scanner.nextInt();
        System.out.println("Game of "+playerCount+" players");
        for(int i=0; i<playerCount; i++) {
            playerCounter++;
            System.out.print("Player "+playerCounter+" enter Nickname : ");
            userName = scanner.next();
            players.add(new Player(userName, 0));
        }
    }

    public void checkRoundType(int roundType){
        if(rounds.get(roundType).getRoundType() == "Σωστή Απάντηση"){

        }
    }


}
