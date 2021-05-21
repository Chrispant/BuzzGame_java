
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Class Game represents the whole logic of Buzz! Ouiz World. It contains a list of players that are currently playing. Also there are two ArrayList that contains the questions
 * that are going to be used by the game. Also there is a list that contains the rounds of game.
 */

public class Game {
    private Question currentQ = null;
    private ArrayList<Question> questionSet;
    private ArrayList<Question> questionSet2;
    private ArrayList<Question> questionSet3, questionSet4, questionSet5;
    private final HashMap<String, ArrayList<Question>> questionsPerCategory;
    private String category;

    /**
     * Constructor of class game. It initialises all properties of this class. It creates hard-coded questions with the corresponding answers. After it creates the questions, a new set of Questions objects
     * are created and saved in the corresponding properties questionSet.
     */
    public Game() throws IOException {
        questionsPerCategory = new HashMap<String, ArrayList<Question>>();

        initQuestions();
        initRoundCategories();

    }


    /**
     * Private method initQuestions created all Questions for the game.
     */
    private void initQuestions() {
        questionSet = new ArrayList<Question>();
        questionSet2 = new ArrayList<Question>();
        questionSet3 = new ArrayList<Question>();
        questionSet4 = new ArrayList<Question>();
        questionSet5 = new ArrayList<Question>();

        String question1 = "A = ?";
        String[] choices1 = {"A", "B", "C", "D"};
        questionSet.add(new Question(question1, "A", choices1));
        String question2 = "What threat is trendy these days?";
        String[] choices2 = {"COVID-19", "DNA-damaging vaccines", "5G", "Kanye West"};
        questionSet.add(new Question(question2, "COVID-19", choices2));
        String question3 = "Is blue red?";
        String[] choices3 = {"I will not dignify that with a response", "are you stupid?", "what?", "no"};
        questionSet.add(new Question(question3, "I will not dignify that with a response", choices3));
        String question3b = "When did the noon landing happened";
        String[] choices3b = {"1969", "never happened", "1696", "1996"};
        questionSet.add(new Question(question3b, "never happened", choices3b));
        String question4 = "How many miles does a cow run when it is being chased?";
        String[] choices4 = {"2 miles", "Cows don't run", "Depends on the weather", "~0.005"};
        questionSet2.add(new Question(question4, "~0.005", choices4));
        String question5 = "How many apples in a cookie jar?";
        String[] choices5 = {"5 cookies", "who puts apples in a cookie jar?", "0", "As many as possible"};
        questionSet2.add(new Question(question5, "0", choices5));
        String question6 = "What is the earth's gravity pull";
        String[] choices6 = {"9.800 m/s²", "8.900 m/s²", "9.700 m/s²", "Don't google that"};
        questionSet2.add(new Question(question6, "9.800 m/s", choices6));
        String question7 = "Oranges are";
        String[] choices7 = {"Orange", "Full of vitamin C", "Filled with juice", "Not an apple"};
        questionSet2.add(new Question(question7, "Not an apple", choices7));
        String question11 = "B = ?";
        String[] choices11 = {"A", "B", "C", "B but better"};
        questionSet3.add(new Question(question11, "B", choices11));
        String question22 = "Which Jujitsu technique is the deadliest?";
        String[] choices22 = {"Rear-naked choke", "Arm bar", "Kimura", "Leg-lock"};
        questionSet3.add(new Question(question22, "Rear-naked choke", choices22));
        String question33 = "Yellow card in football means?";
        String[] choices33 = {"First offence", "Offside", "Goal", "Elimination"};
        questionSet3.add(new Question(question33, "First offence", choices33));
        String question44 = "1 = ?";
        String[] choices44 = {"1", "2", "3", "4"};
        questionSet3.add(new Question(question44, "1", choices44));
        String question444 = "Can crystals heal better than modern science?";
        String[] choices444 = {"Only on Mondays", "Yes", "Maybe", "Only they are blue"};
        questionSet4.add(new Question(question444, "Only on Mondays", choices444));
        String question55 = "Which organ is the smallest";
        String[] choices55 = {"Your penis", "The kidney", "The eye", "The heart"};
        questionSet4.add(new Question(question55, "Your penis", choices55));
        String question66 = "Are sodas healthy??";
        String[] choices66 = {"No", "Yes", "Only Coke", "Only sugarless sodas"};
        questionSet4.add(new Question(question66, "No", choices66));
        String question77 = "Should you drink blood?";
        String[] choices77 = {"no", "no", "no","no"};
        questionSet4.add(new Question(question77, "no", choices77));
        String question15 = "Who does not have an oscar?";
        String[] choices15 = {"Leonardo Dicaprio", "Meryl Strip", "Cristian Bale", "Johnny Depp"};
        questionSet5.add(new Question(question15, "Johnny Depp", choices15));
        String question25 = "Which movie is the longest?";
        String[] choices25 = {"Interstellar", "Tenet", "Inception", "The Martian"};
        questionSet5.add(new Question(question25, "Interstellar", choices25));
        String question35 = "Who is John Wick in the film John Wick?";
        String[] choices35 = {"The boogeyman", "The ghost", "The guy you send to kill the f*cking boogeyman", "A janitor"};
        questionSet5.add(new Question(question35, "The guy you send to kill the f*cking boogeyman", choices35));
        String question45 = "What is the name of the movie in which Terry Crews is the president ";
        String[] choices45 = {"Idiocracy", "Eternal sunshine of the spotless mind","Big Man Time!","From zero to president"};
        questionSet5.add(new Question(question45, "Idiocracy", choices45));
        Collections.shuffle(questionSet3);
        Collections.shuffle(questionSet4);
        Collections.shuffle(questionSet5);
    }

    /**
     * Private method initRoundCategories creates the categories for each question set and saves them to HashMap questionsPerCategory
     */
    private void initRoundCategories() {
        //put the set to category
        ArrayList<String> roundCategories = new ArrayList<>();
        roundCategories.add("History");
        roundCategories.add("Science");
        roundCategories.add("Sports");
        roundCategories.add("Health");
        roundCategories.add("Cinema");
        questionsPerCategory.put(roundCategories.get(0), questionSet);
        questionsPerCategory.put(roundCategories.get(1), questionSet2);
        questionsPerCategory.put(roundCategories.get(2), questionSet3);
        questionsPerCategory.put(roundCategories.get(3), questionSet4);
        questionsPerCategory.put(roundCategories.get(4), questionSet5);

    }



    public void updateQuestion(){
        Question newQuestion  = null;
        String category = pickCategory();

        if(!(questionsPerCategory.get(category).size() >=1)){
            category = getDifferentCategory(category);
        }
        newQuestion=questionsPerCategory.get(category).get(0);
        questionsPerCategory.get(category).remove(0);

        setCategory(category);
        currentQ = newQuestion;
    }

    public Question currentQuestion() throws InterruptedException {
        return currentQ;
    }

    public void setCategory(String category){
        this.category = category;

    }

    public String getCategory(){
        return this.category;
    }

    public String pickCategory(){
        int zeroToFive = (int) Math.round(Math.random() * 5);
        String category = roundCategory(zeroToFive);

        return category;
    }

    public String getDifferentCategory(String category){
        String newCategory = pickCategory();
        if(newCategory == category || questionsPerCategory.get(newCategory).size()==0){
            newCategory = getDifferentCategory(newCategory);
        }
        return newCategory;

    }

    public String roundCategory(int category) {
        if (category == 0) {
            return "History";
        } else if (category == 1){
            return "Science";
        } else if (category == 2){
            return "Sports";
        }else if (category == 3){
            return "Health";
        }else{
            return "Cinema";
        }
    }

}
/*
        //Prints type of round
        System.out.println("\n" + "Game Type - " + rounds.get(roundType).getRoundType());
        TimeUnit.SECONDS.sleep(2);
        //Prints a Question
        for (int counter = 0; counter < 2; counter++) {
            int questionCounter = counter + 1;
            int zeroOrOne = (int) Math.round(Math.random() * 2);
            String category = roundCategory(zeroOrOne);//Get the category of questions and display it to the user
            TimeUnit.SECONDS.sleep(2);//Pause the program for 2 seconds using native method sleep
            System.out.println("\n" + "Category - " + category);

            Question question = null;
            //Retrieve the question that is going to be used for the game
            //Check the questionsPerCategory arraylist of questions if the size is 1 because there is case that all 4 questions of the same category can be played. In that case the counter for the least question is going
            //to be 1 but the question is located at the 0 index.
            if (questionsPerCategory.get(category).size() == 1) {
                question = questionsPerCategory.get(category).get(0);
            } else {
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
            ArrayList<String> choices = question.getChoices();
            String correctAnswer = question.getAnswer();//Get the correct answer for the question that is chosen
            int correctAnswerNumber = choices.indexOf(correctAnswer) + 1;
            // Sets each players answer
            for (int player = 0; player < players.size(); player++) {
                //If the round type is Ποντάρισμα get the bet of the player
                if (rounds.get(roundType).getRoundType().equals("Ποντάρισμα")) {
                    System.out.println("\n" + players.get(player).getPlayerName() + "'s bet is : ");
                    int bet = bet();//Get the bet of the player
                    System.out.println("\n" + players.get(player).getPlayerName() + "'s answer is : ");
                    players.get(player).setAnswer(answer());//Set the answer of the player from the keyboard
                    //If the aswer that is provided is correct increase the points of the player to the corresponding bet.
                    if (players.get(player).getAnswer() == correctAnswerNumber) {
                        // System.out.println("\n"+players.get(player).getPlayerName() + " got it right!");
                        players.get(player).addPoints(bet);
                    } else {
                        //System.out.println("\n"+players.get(player).getPlayerName()+" got it wrong!");
                        //The player has aswered incorrectly, remove the points that he bet.
                        players.get(player).removePoints(bet);
                    }
                } else {
                    ////If the round type is Σωστή Απάντηση
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("\n" + players.get(player).getPlayerName() + "'s answer is : ");
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
            System.out.println(+correctAnswerNumber + " : " + correctAnswer);


            clearQuestionSet(counter, category);
        }

         public void startQuestion(int roundType) throws InterruptedException {

        //Prints type of round
        System.out.println("\n" + "Game Type - " + rounds.get(roundType).getRoundType());
        TimeUnit.SECONDS.sleep(2);
        //Prints a Question
        for (int counter = 0; counter < 2; counter++) {
            int questionCounter = counter + 1;
            int zeroOrOne = (int) Math.round(Math.random() * 2);
            String category = roundCategory(zeroOrOne);//Get the category of questions and display it to the user
            TimeUnit.SECONDS.sleep(2);//Pause the program for 2 seconds using native method sleep
            System.out.println("\n" + "Category - " + category);

            Question question = null;
            //Retrieve the question that is going to be used for the game
            //Check the questionsPerCategory arraylist of questions if the size is 1 because there is case that all 4 questions of the same category can be played. In that case the counter for the least question is going
            //to be 1 but the question is located at the 0 index.
            if (questionsPerCategory.get(category).size() == 1) {
                question = questionsPerCategory.get(category).get(0);
            } else {
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
            ArrayList<String> choices = question.getChoices();
            String correctAnswer = question.getAnswer();//Get the correct answer for the question that is chosen
            int correctAnswerNumber = choices.indexOf(correctAnswer) + 1;
            // Sets each players answer
            for (int player = 0; player < players.size(); player++) {
                //If the round type is Ποντάρισμα get the bet of the player
                if (rounds.get(roundType).getRoundType().equals("Ποντάρισμα")) {
                    System.out.println("\n" + players.get(player).getPlayerName() + "'s bet is : ");
                    int bet = bet();//Get the bet of the player
                    System.out.println("\n" + players.get(player).getPlayerName() + "'s answer is : ");
                    players.get(player).setAnswer(answer());//Set the answer of the player from the keyboard
                    //If the aswer that is provided is correct increase the points of the player to the corresponding bet.
                    if (players.get(player).getAnswer() == correctAnswerNumber) {
                        // System.out.println("\n"+players.get(player).getPlayerName() + " got it right!");
                        players.get(player).addPoints(bet);
                    } else {
                        //System.out.println("\n"+players.get(player).getPlayerName()+" got it wrong!");
                        //The player has aswered incorrectly, remove the points that he bet.
                        players.get(player).removePoints(bet);
                    }
                } else {
                    ////If the round type is Σωστή Απάντηση
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("\n" + players.get(player).getPlayerName() + "'s answer is : ");
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
            System.out.println(+correctAnswerNumber + " : " + correctAnswer);


            clearQuestionSet(counter, category);
        }


    }
 */