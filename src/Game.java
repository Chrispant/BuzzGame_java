
import java.util.*;
import java.util.concurrent.TimeUnit;


public class Game {
    private final ArrayList<Question> questionSet;
    private final ArrayList<Question> questionSet2;
    private final HashMap<String, ArrayList<Question>> questionsPerCategory;
    private final ArrayList<Player> players;
    private final ArrayList<Round> rounds;



    public Game(){
        //Method for future use(Make each question correspond to a single category) use ArrayList or HashSet?
        questionsPerCategory = new HashMap<String,ArrayList<Question>>();

        questionSet = new ArrayList<Question>();
        questionSet2 = new ArrayList<Question>();
        String question1 = "A = ?";
        String[] choices1 = {"A","B","C","D"};
        questionSet.add(new Question(question1,"A",choices1));
        String question2 = "What threat is trendy these days?";
        String[] choices2 = {"COVID-19","DNA-damaging vaccines","5G","Kanye West"};
        questionSet.add(new Question(question2,"COVID-19",choices2));
        String question3 = "Is blue red?";
        String[] choices3 = {"I will not dignify that with a response","are you stupid?","what?","no"};
        questionSet.add(new Question(question3,"I will not dignify that with a response",choices3));
        String question3b = "is red blue?";
        String[] choices3b = {"I will not dignify that with a response","Maybe, if you are colorblind","you know the answer","no"};
        questionSet.add(new Question(question3b,"no",choices3b));
        String question4 = "How many miles does a cow run when it is being chased?";
        String[] choices4 = {"2 miles","Cows don't run","Depends on the weather","~0.005"};
        questionSet2.add(new Question(question4,"~0.005",choices4));
        String question5 = "How many apples in a cookie jar?";
        String[] choices5 = {"5 cookies","who puts apples in a cookie jar?","0","As many as possible"};
        questionSet2.add(new Question(question5,"0",choices5));
        String question6 = "How does the sun look like in the morning?";
        String[] choices6 = {"Like it does in the evening","It uses glasses","This game is stupid","the first time you answer something you will be wrong"};
        questionSet2.add(new Question(question6,"the first time you answer something you will be wrong",choices6));
        String question7 = "How does the sun look like in the evening?";
        String[] choices7 = {"Like a boss","5x+24","Depends on where you are at","Clearly this is not a question you ask in a quiz game"};
        questionSet2.add(new Question(question7,"5x+24",choices7));
        Collections.shuffle(questionSet);
        Collections.shuffle(questionSet2);

        //put the set to category
        ArrayList<String> roundCategories = new ArrayList<>();
        roundCategories.add("Επιστήμη");
        roundCategories.add("Ιστορία");
        questionsPerCategory.put(roundCategories.get(0),questionSet);
        questionsPerCategory.put(roundCategories.get(1),questionSet2);





        players = new ArrayList<Player>();

        rounds = new ArrayList<Round>();
        rounds.add(new Round("Σωστή Απάντηση"));
        rounds.add(new Round("Ποντάρισμα"));
        Collections.shuffle(rounds);
    }

    public void startQuestion(int roundType) throws InterruptedException {

        //Prints type of round
        System.out.println("\n"+"Game Type - "+rounds.get(roundType).getRoundType());
        TimeUnit.SECONDS.sleep(2);
        //Prints a Question
        for(int counter = 0; counter<2; counter++) {
            int questionCounter = counter +1;
            int zeroOrOne = (int) Math.round(Math.random()*2);
            String category = roundCategory(zeroOrOne);
            TimeUnit.SECONDS.sleep(2);
            System.out.println("\n"+"Category - "+category);
            TimeUnit.SECONDS.sleep(4);
            System.out.println("\nQuestion " + questionCounter + " : " + questionsPerCategory.get(category).get(counter).getQuestion());
            //Stores number of choices for a given question, this help us out later
            int numOfChoices = questionsPerCategory.get(category).get(counter).getChoices().size();

            //Prints the choices
            for (int choice = 0; choice < numOfChoices; choice++) {
                TimeUnit.SECONDS.sleep(2);
                System.out.println((choice + 1) + " : " + questionsPerCategory.get(category).get(counter).getChoices().get(choice));
            }
            //Sets the right Answer
            ArrayList<String> choices = questionsPerCategory.get(category).get(counter).getChoices();
            String correctAnswer = questionsPerCategory.get(category).get(counter).getAnswer();
            int correctAnswerNumber = choices.indexOf(correctAnswer) + 1;
            // Sets each players answer
            for (int player = 0; player < players.size(); player++) {
                if(rounds.get(roundType).getRoundType().equals("Ποντάρισμα")) {
                    System.out.println("\n"+players.get(player).getPlayerName() + "'s bet is : ");
                    int bet = bet();
                    System.out.println("\n"+players.get(player).getPlayerName() + "'s answer is : ");
                    players.get(player).setAnswer(answer());
                    if (players.get(player).getAnswer() == correctAnswerNumber) {
                       // System.out.println("\n"+players.get(player).getPlayerName() + " got it right!");
                        players.get(player).addPoints(bet);
                    }else{
                        //System.out.println("\n"+players.get(player).getPlayerName()+" got it wrong!");
                        players.get(player).removePoints(bet);
                    }
                }else{
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("\n"+players.get(player).getPlayerName() + "'s answer is : ");
                    players.get(player).setAnswer(answer());
                    if (players.get(player).getAnswer() == correctAnswerNumber) {
                        //System.out.println("\n"+players.get(player).getPlayerName() + " got it right!");
                        players.get(player).addPoints(1000);
                    }//else{
                        //System.out.println("\n"+players.get(player).getPlayerName()+" got it wrong!");
                    //}

                }
            }
            //Checks if player answer right or wrong
            /**for (int player = 0; player < players.size(); player++) {
                if (players.get(player).getAnswer() == correctAnswerNumber) {
                    checkAnswer = true;
                    if(rounds.get(roundType).getRoundType() == "Ποντάρισμα") {
                        System.out.println(players.get(player).getPlayerName() + " got it right!");
                        players.get(player).addPoints(bet);
                    }else{
                        System.out.println(players.get(player).getPlayerName() + " got it right!");
                        players.get(player).addPoints(1000);
                    }
                } else {
                    checkAnswer = false;
                    System.out.println(players.get(player).getPlayerName()+" got it wrong!");
                }
                //checkRoundType();
            }*/
            //See what the correct answer is
            System.out.println("And the correct answer isssssss ");
            TimeUnit.SECONDS.sleep(3);
            System.out.println( + correctAnswerNumber + " : " + correctAnswer);
            //remove question from array
            if(category.equals("Επιστήμη")){
                if(questionSet.size()>1)
                questionSet.remove(counter);
                else{
                    questionSet.remove(0);
                }
            }else{
                if(questionSet2.size()>1){
                    questionSet2.remove(counter);
                }else{
                    questionSet2.remove(0);
                }

            }
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
    // returns string of category
    public String roundCategory(int category){
        if(category == 0){
            return "Επιστήμη";

        }else
            return "Ιστορία";
    }

    //prints points of each player
    public void gameOver(){
        System.out.println("\nGame is over!");
        for(int player=0; player<players.size(); player++){
            System.out.println(players.get(player).getPlayerName()+" has "+players.get(player).getPoints()+" points");
        }
    }

    //sees if users made a bet in a correct manner
    public int bet(){
        Scanner scanner = new Scanner(System.in);
        int bet = scanner.nextInt();
        switch (bet){
            case 250:
                System.out.println("Bet of 250 points!");
                return bet;
            case 500:
                System.out.println("Bet of 500 points!");
                return bet;
            case 750:
                System.out.println("Bet of 750 points!");
                return bet;
            case 1000:
                System.out.println("Bet of 1000 points!");
                return bet;
            default:
                System.out.println("Unavailable bet please try again");
                return bet();

        }
    }

    //sees if users answered in a correct manner
    public int answer(){
        Scanner scanner = new Scanner(System.in);
        int answer = scanner.nextInt();
        switch (answer){
            case 1:
            case 2:
            case 3:
            case 4:
                return answer;
            default:
                System.out.println("Unavailable answer please try again");
                return answer();

        }
    }



}
