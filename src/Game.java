
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Class Game represents the whole logic of Buzz! Ouiz World. It contains a list of players that are currently playing. Also there are two ArrayList that contains the questions
 * that are going to be used by the game. Also there is a list that contains the rounds of game.
 */

public class Game {
    private ArrayList<Question> questionSet;
    private ArrayList<Question> questionSet2;
    private final HashMap<String, ArrayList<Question>> questionsPerCategory;
    private final ArrayList<Player> players;
    private final ArrayList<Round> rounds;


    /**
     * Constructor of class game. It initialises all properties of this class. It creates hard-coded questions with the corresponding answers. After it creates the questions, a new set of Questions objects
     * are created and saved in the corresponding properties questionSet and questionSet2.
     */
    public Game(){
        //Method for future use(Make each question correspond to a single category) use ArrayList or HashSet?
        questionsPerCategory = new HashMap<String,ArrayList<Question>>();

        initQuestions();
        initRoundCategories();

        players = new ArrayList<Player>();

        rounds = new ArrayList<Round>();
        rounds.add(new Round("Σωστή Απάντηση"));
        rounds.add(new Round("Ποντάρισμα"));
        Collections.shuffle(rounds);
    }


    /**
     * Private method initQuestions created all Questions for the game.
     */
    private void initQuestions(){
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
    }

    /**
     * Private method initRoundCategories creates the categories for each question set and saves them to HashMap questionsPerCategory
     */
    private void initRoundCategories(){
        //put the set to category
        ArrayList<String> roundCategories = new ArrayList<>();
        roundCategories.add("Επιστήμη");
        roundCategories.add("Ιστορία");
        questionsPerCategory.put(roundCategories.get(0),questionSet);
        questionsPerCategory.put(roundCategories.get(1),questionSet2);

    }

    /**
     * Clear from questionSet or questionSet2 one question based on position and the category.
     * @param counter the index of the question that is going to be removed
     * @param category the category that the question belongs
     */
    private void clearQuestionSet(int counter,String category){
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


    /**
     * Method startQuestion starts the game  Buzz! Ouiz World. After it receives the round type, gets category of the questions and print them to the user to inform him which type of question he will have
     * to answer.
     * @param roundType int value that represents the round type, possible values 0 -> Σωστή Απάντηση, 1 ->Ποντάρισμα
     * @throws InterruptedException
     */
    public void startQuestion(int roundType) throws InterruptedException {

        //Prints type of round
        System.out.println("\n"+"Game Type - "+rounds.get(roundType).getRoundType());
        TimeUnit.SECONDS.sleep(2);
        //Prints a Question
        for(int counter = 0; counter<2; counter++) {
            int questionCounter = counter +1;
            int zeroOrOne = (int) Math.round(Math.random()*2);
            String category = roundCategory(zeroOrOne);//Get the category of questions and display it to the user
            TimeUnit.SECONDS.sleep(2);//Pause the program for 2 seconds using native method sleep
            System.out.println("\n"+"Category - "+category);

            Question question = null;
            //Retrieve the question that is going to be used for the game
            //Check the questionsPerCategory arraylist of questions if the size is 1 because there is case that all 4 questions of the same category can be played. In that case the counter for the least question is going
            //to be 1 but the question is located at the 0 index.
            if(questionsPerCategory.get(category).size() == 1) {
                question = questionsPerCategory.get(category).get(0);
            }else{
                question = questionsPerCategory.get(category).get(counter);
            }

            TimeUnit.SECONDS.sleep(4);//Pause the program for 4 seconds using native method sleep
            System.out.println("\nQuestion " + questionCounter + " : " + question.getQuestion());//Print out the question for the specific category
            //Stores number of choices for a given question, this help us out later
            int numOfChoices = question.getChoices().size();//Number of possible answers

            //Prints the choices
            for (int choice = 0; choice < numOfChoices; choice++) {
                TimeUnit.SECONDS.sleep(2);
                System.out.println((choice + 1) + " : " + question.getChoices().get(choice));
            }
            //Sets the right Answer
            ArrayList<String> choices =question.getChoices();
            String correctAnswer = question.getAnswer();//Get the correct answer for the question that is chosen
            int correctAnswerNumber = choices.indexOf(correctAnswer) + 1;
            // Sets each players answer
            for (int player = 0; player < players.size(); player++) {
                //If the round type is Ποντάρισμα get the bet of the player
                if(rounds.get(roundType).getRoundType().equals("Ποντάρισμα")) {
                    System.out.println("\n"+players.get(player).getPlayerName() + "'s bet is : ");
                    int bet = bet();//Get the bet of the player
                    System.out.println("\n"+players.get(player).getPlayerName() + "'s answer is : ");
                    players.get(player).setAnswer(answer());//Set the answer of the player from the keyboard
                    //If the aswer that is provided is correct increase the points of the player to the corresponding bet.
                    if (players.get(player).getAnswer() == correctAnswerNumber) {
                       // System.out.println("\n"+players.get(player).getPlayerName() + " got it right!");
                        players.get(player).addPoints(bet);
                    }else{
                        //System.out.println("\n"+players.get(player).getPlayerName()+" got it wrong!");
                        //The player has aswered incorrectly, remove the points that he bet.
                        players.get(player).removePoints(bet);
                    }
                }else{
                    ////If the round type is Σωστή Απάντηση
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("\n"+players.get(player).getPlayerName() + "'s answer is : ");
                    players.get(player).setAnswer(answer());//Retrieve the answer of the player
                    if (players.get(player).getAnswer() == correctAnswerNumber) {
                        //System.out.println("\n"+players.get(player).getPlayerName() + " got it right!");
                        players.get(player).addPoints(1000);
                    }//else{
                        //System.out.println("\n"+players.get(player).getPlayerName()+" got it wrong!");
                    //}

                }
            }


            //See what the correct answer is
            System.out.println("And the correct answer isssssss ");
            TimeUnit.SECONDS.sleep(3);
            System.out.println( + correctAnswerNumber + " : " + correctAnswer);


            clearQuestionSet(counter,category);
        }


    }
    /**
     * Method addPlayer initialises the players for the game. It can work with a single player or multiple players.
     */
    public void addPlayers(){
        int playerCounter = 0;
        String userName;
        System.out.println("Enter Number of Players : ");

        boolean playersAddedSuccessfully = false;
        while (!playersAddedSuccessfully){
            try {
                Scanner scanner = new Scanner(System.in);
                int playerCount = scanner.nextInt();


                System.out.println("Game of "+playerCount+" players");
                for(int i=0; i<playerCount; i++) {
                    playerCounter++;
                    System.out.print("Player "+playerCounter+" enter Nickname : ");
                    userName = scanner.next();
                    players.add(new Player(userName, 0));
                }
                playersAddedSuccessfully = true;
            }catch (InputMismatchException e){
                System.out.println("Please provide a number for the total players of the game");
            }
        }



    }

    /**
     *
     * @param category index that is going to be used for returning the appropriate String category
     * @return
     */
    public String roundCategory(int category){
        if(category == 0){
            return "Επιστήμη";

        }else
            return "Ιστορία";
    }


    /**
     * Method gameOver prints to the console the points of each player after the game is completed
     */
    public void gameOver(){
        System.out.println("\nGame is over!");
        for(int player=0; player<players.size(); player++){
            System.out.println(players.get(player).getPlayerName()+" has "+players.get(player).getPoints()+" points");
        }
    }


    /**
     * Method bet represents the betting process of the game. The player can only bet 250,500,750,1000 points other option is Unavailable
     * @return the bet that the player has decided to play
     */
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
                return bet();//Unavailable option repeat the betting process.

        }
    }


    /**
     * Method answer represents the answer that the user gives from the keyboard. Possible answers are 1-4 other answer is unavailable.
     * @return the answer of the player
     */
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
