import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;

public class GUI extends JFrame {
    private ArrayList<Question> questionSet;
    private ArrayList<Question> questionSet2;
    private  HashMap<String, ArrayList<Question>> questionsPerCategory;
    private final ArrayList<Player> players;
    private final ArrayList<Round> rounds;

    private JTextField textField;
    private JTextArea textArea;

    private JLabel time_label;

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;

    private JLabel answer1;
    private JLabel answer2;
    private JLabel answer3;
    private JLabel answer4;

    private JFrame frame;

    public GUI(){
        String text = "What is the number after the number 5";
        String category = "Science";

        //Method for future use(Make each question correspond to a single category) use ArrayList or HashSet?
        questionsPerCategory = new HashMap<String, ArrayList<Question>>();

        initQuestions();
        initRoundCategories();

        players = new ArrayList<Player>();

        rounds = new ArrayList<Round>();
        rounds.add(new Round("Right Answer"));
        rounds.add(new Round("Bet"));
        Collections.shuffle(rounds);

        ArrayList<String> roundCategories = new ArrayList<>();
        roundCategories.add("Science");
        roundCategories.add("History");
        questionsPerCategory.put(roundCategories.get(0), questionSet);
        questionsPerCategory.put(roundCategories.get(1), questionSet2);

        frame = new JFrame();
        textField = new JTextField();
        textArea = new JTextArea();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        time_label = new JLabel();
        answer1 = new JLabel();
        answer2 = new JLabel();
        answer3 = new JLabel();
        answer4 = new JLabel();



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,600);
        frame.setLayout(new BorderLayout());
        frame.setBackground(Color.WHITE);

        //top panel's properties
        textField.setBounds(0,0,650,50);
        textField.setBackground(new Color(25,25,25));
        textField.setForeground(new Color(25,255,0));
        textField.setFont(new Font("Ink Free",Font.BOLD,30));
        textField.setBorder(BorderFactory.createBevelBorder(1));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);
        textField.setText("Category - Game Type");///////////////////////////////////////////////////////////

        textArea.setBounds(0,50,650,50);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(25,25,25));
        textArea.setForeground(new Color(25,255,0));
        textArea.setFont(new Font("MV Boli",Font.BOLD,25));
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setEditable(false);
        Question question = null;
        textArea.setText("Question");////////////////////////////////////////////////////////

        button1.setBounds(0,100,100,100);
        button1.setFont(new Font("MV Boli",Font.BOLD,35));
        button1.setFocusable(false);
        //button1.addActionListener(this);
        button1.setText("1");

        button2.setBounds(0,200,100,100);
        button2.setFont(new Font("MV Boli",Font.BOLD,35));
        button2.setFocusable(false);
        //button2.addActionListener(this);
        button2.setText("2");

        button3.setBounds(0,300,100,100);
        button3.setFont(new Font("MV Boli",Font.BOLD,35));
        button3.setFocusable(false);
        //button3.addActionListener(this);
        button3.setText("3");

        button4.setBounds(0,400,100,100);
        button4.setFont(new Font("MV Boli",Font.BOLD,35));
        button4.setFocusable(false);
        //button4.addActionListener(this);
        button4.setText("4");

        time_label.setBounds(535,475,100,25);
        time_label.setBackground(new Color(50,50,50));
        time_label.setForeground(new Color(255,0,0));
        time_label.setFont(new Font("MV Boli",Font.PLAIN,16));
        time_label.setHorizontalAlignment(JTextField.CENTER);
        time_label.setText("timer 00:00");

        answer1.setBounds(125,100,500,100);
        answer1.setBackground(new Color(50,50,50));
        answer1.setForeground(new Color(25,255,0));
        answer1.setFont(new Font("MV Boli",Font.PLAIN,35));

        answer2.setBounds(125,200,500,100);
        answer2.setBackground(new Color(50,50,50));
        answer2.setForeground(new Color(25,255,0));
        answer2.setFont(new Font("MV Boli",Font.PLAIN,35));

        answer3.setBounds(125,300,500,100);
        answer3.setBackground(new Color(50,50,50));
        answer3.setForeground(new Color(25,255,0));
        answer3.setFont(new Font("MV Boli",Font.PLAIN,35));

        answer4.setBounds(125,400,500,100);
        answer4.setBackground(new Color(50,50,50));
        answer4.setForeground(new Color(25,255,0));
        answer4.setFont(new Font("MV Boli",Font.PLAIN,35));

        frame.add(textField);
        frame.add(textArea);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(time_label);
        frame.add(answer1);
        frame.add(answer2);
        frame.add(answer3);
        frame.add(answer4);


    }


    public void startFrame(){
        frame.setVisible(true);
    }

    public void updateQA(){

    }

    public void initRoundCategories() {
        //put the set to category
        ArrayList<String> roundCategories = new ArrayList<>();
        roundCategories.add("Science");
        roundCategories.add("History");
        questionsPerCategory.put(roundCategories.get(0), questionSet);
        questionsPerCategory.put(roundCategories.get(1), questionSet2);

    }

    public void initQuestions() {
        ArrayList<Question> questionSet = new ArrayList<Question>();
        ArrayList<Question> questionSet2 = new ArrayList<Question>();
        String question1 = "A = ?";
        String[] choices1 = {"A", "B", "C", "D"};
        questionSet.add(new Question(question1, "A", choices1));
        String question2 = "What threat is trendy these days?";
        String[] choices2 = {"COVID-19", "DNA-damaging vaccines", "5G", "Kanye West"};
        questionSet.add(new Question(question2, "COVID-19", choices2));
        String question3 = "Is blue red?";
        String[] choices3 = {"I will not dignify that with a response", "are you stupid?", "what?", "no"};
        questionSet.add(new Question(question3, "I will not dignify that with a response", choices3));
        String question3b = "is red blue?";
        String[] choices3b = {"I will not dignify that with a response", "Maybe, if you are colorblind", "you know the answer", "no"};
        questionSet.add(new Question(question3b, "no", choices3b));
        String question4 = "How many miles does a cow run when it is being chased?";
        String[] choices4 = {"2 miles", "Cows don't run", "Depends on the weather", "~0.005"};
        questionSet2.add(new Question(question4, "~0.005", choices4));
        String question5 = "How many apples in a cookie jar?";
        String[] choices5 = {"5 cookies", "who puts apples in a cookie jar?", "0", "As many as possible"};
        questionSet2.add(new Question(question5, "0", choices5));
        String question6 = "How does the sun look like in the morning?";
        String[] choices6 = {"Like it does in the evening", "It uses glasses", "This game is stupid", "the first time you answer something you will be wrong"};
        questionSet2.add(new Question(question6, "the first time you answer something you will be wrong", choices6));
        String question7 = "How does the sun look like in the evening?";
        String[] choices7 = {"Like a boss", "5x+24", "Depends on where you are at", "Clearly this is not a question you ask in a quiz game"};
        questionSet2.add(new Question(question7, "5x+24", choices7));
        Collections.shuffle(questionSet);
        Collections.shuffle(questionSet2);
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
    public String roundCategory(int category) {
        if (category == 0) {
            return "Science";

        } else
            return "History";
    }

    public int answer() {
        Scanner scanner = new Scanner(System.in);
        int answer = scanner.nextInt();
        switch (answer) {
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

    public int bet() {
        Scanner scanner = new Scanner(System.in);
        int bet = scanner.nextInt();
        switch (bet) {
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

    private void clearQuestionSet(int counter, String category) {
        //remove question from array
        if (category.equals("Επιστήμη")) {
            if (questionSet.size() > 1)
                questionSet.remove(counter);
            else {
                questionSet.remove(0);
            }
        } else {
            if (questionSet2.size() > 1) {
                questionSet2.remove(counter);
            } else {
                questionSet2.remove(0);
            }

        }
    }
}


