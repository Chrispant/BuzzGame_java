import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;

public class GUI extends JFrame implements ActionListener {
    //newGame game;

    private ArrayList<Question> questionSet;
    private ArrayList<Question> questionSet2;
    private  HashMap<String, ArrayList<Question>> questionsPerCategory;
    private final ArrayList<Player> players;
    private final ArrayList<Round> rounds;

    private JButton button1,button2,button3,button4;
    private JButton newGameButton, highScoreButton, quitButton;

    private JTextField gameTypeLabel;
    private JTextArea questionLabel;
    private JLabel[] answer;
    private JLabel time_label;

    private JFrame frame;

    private JLabel backgroundImage;

    public GUI() throws IOException {

        initComponents();

        //game = new newGame(this);



        String text = "What is the number after the number 5";
        String category = "Science";

        //Hash map that pairs a set of questions to a category
        questionsPerCategory = new HashMap<String, ArrayList<Question>>();

        initQuestions();

        players = new ArrayList<Player>();

        rounds = new ArrayList<Round>();
        rounds.add(new Round("Right Answer"));
        rounds.add(new Round("Bet"));
        rounds.add(new Round("Thermometer"));
        rounds.add(new Round("Stop The Clock"));
        rounds.add(new Round("Fastest Answer"));
        Collections.shuffle(rounds);

    }
    public void initComponents() throws IOException {
        //GUI Quiz components
        frame = new JFrame();
        gameTypeLabel = new JTextField();
        questionLabel = new JTextArea();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        time_label = new JLabel();
        answer = new JLabel[4];
        for(int i = 0; i<4; i++){
            answer[i] = new JLabel("Test");
        }

        //Gui help components
        BufferedImage myPicture = ImageIO.read(new File("images/basic_frame_image.jpg"));
        backgroundImage = new JLabel(new ImageIcon(myPicture));

        //Gui Starting frame Components
        newGameButton = new JButton("New game");
        highScoreButton = new JButton("High Score");
        quitButton = new JButton("Exit");





        //Starting frame's parameters
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650,650);
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setLocationRelativeTo(null);


        newGameButton.setBounds(150,50,300,150);
        newGameButton.setFont(new Font("MV Boli",Font.BOLD,35));
        newGameButton.setFocusable(false);
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startQuestionFrame();

            }
        });

        add(newGameButton);

        highScoreButton.setBounds(150,250, 300,150);
        highScoreButton.setFont(new Font("MV Boli",Font.BOLD,35));
        highScoreButton.setFocusable(false);
        highScoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                highScoreScreen();

            }
        });

        add(highScoreButton);

        quitButton.setBounds(150,450, 300,150);
        quitButton.setFont(new Font("MV Boli",Font.BOLD,35));
        quitButton.setFocusable(false);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);


            }
        });

        add(quitButton);
        add(backgroundImage);



        //Quiz frame parameters
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650,650);
        frame.setLayout(new BorderLayout());
        frame.setBackground(Color.WHITE);

        gameTypeLabel.setBounds(0,0,650,50);
        gameTypeLabel.setBackground(new Color(25,25,25));
        gameTypeLabel.setForeground(new Color(125,155,0));
        gameTypeLabel.setFont(new Font("Ink Free",Font.BOLD,30));
        gameTypeLabel.setBorder(BorderFactory.createBevelBorder(1));
        gameTypeLabel.setHorizontalAlignment(JTextField.CENTER);
        gameTypeLabel.setEditable(false);
        gameTypeLabel.setText("Category - Game Type");///////////////////////////////////////////////////////////

        questionLabel.setBounds(0,50,650,50);
        questionLabel.setLineWrap(true);
        questionLabel.setWrapStyleWord(true);
        questionLabel.setBackground(new Color(25,25,25));
        questionLabel.setForeground(new Color(125,155,25));
        questionLabel.setFont(new Font("MV Boli",Font.BOLD,25));
        questionLabel.setBorder(BorderFactory.createBevelBorder(1));
        questionLabel.setEditable(false);
        Question question = null;
        questionLabel.setText("Question");////////////////////////////////////////////////////////

        button1.setBounds(0,100,100,100);
        button1.setFont(new Font("MV Boli",Font.BOLD,35));
        button1.setFocusable(false);
        button1.addActionListener(this);
        button1.setText("1");

        button2.setBounds(0,200,100,100);
        button2.setFont(new Font("MV Boli",Font.BOLD,35));
        button2.setFocusable(false);
        button2.addActionListener(this);
        button2.setText("2");

        button3.setBounds(0,300,100,100);
        button3.setFont(new Font("MV Boli",Font.BOLD,35));
        button3.setFocusable(false);
        button3.addActionListener(this);
        button3.setText("3");

        button4.setBounds(0,400,100,100);
        button4.setFont(new Font("MV Boli",Font.BOLD,35));
        button4.setFocusable(false);
        button4.addActionListener(this);
        button4.setText("4");

        time_label.setBounds(0,500,100,25);
        time_label.setBackground(new Color(50,50,50));
        time_label.setForeground(new Color(255,0,0));
        time_label.setFont(new Font("MV Boli",Font.PLAIN,16));
        time_label.setHorizontalAlignment(JTextField.CENTER);
        time_label.setText("timer 00:00");

        int x = 0;
        for(int i = 0; i<4; i++){
            answer[i].setBounds(125,100+x,500,100);
            answer[i].setBackground(new Color(50,50,50));
            answer[i].setForeground(new Color(55,55,55));
            answer[i].setFont(new Font("MV Boli",Font.PLAIN,35));
            answer[i].setText("Test");
            x += 100;
            frame.add(answer[i]);
        }

        frame.add(gameTypeLabel);
        frame.add(questionLabel);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(time_label);
        

    }


    public void startQuestionFrame(){
        setVisible(false);
        frame.setVisible(true);
        frame.setLocation(getLocation());
    }
    public void stopQuestionFrame(){frame.setVisible(false);}

    public void newGame(){
        frame.setVisible(false);
        setVisible(true);
    }

    public void updateQuestionAnswer(){



    }



    public void initQuestions() {
        ArrayList<Question> questionSet = new ArrayList<Question>();
        ArrayList<Question> questionSet2 = new ArrayList<Question>();
        String question1 = "A = ?";
        String[] choices1 = {"A", "B", "C", "D"};
        questionSet.add(new Question(question1, "A", choices1));
        String question2 = "What is the Capital of Germany?";
        String[] choices2 = {"London", "Paris", "Berlin", "Athens"};
        questionSet.add(new Question(question2, "Berlin", choices2));
        String question3 = "How many is 'a dozen' eggs?";
        String[] choices3 = {"10", "5", "12", "8"};
        questionSet.add(new Question(question3, "12", choices3));
        String question3b = "15+15x2?";
        String[] choices3b = {"60", "30", "45", "75"};
        questionSet.add(new Question(question3b, "45", choices3b));
        String question4 = "Which one is a compression method?";
        String[] choices4 = {"Neural network", "Huffman", "Polio", "DFT"};
        questionSet2.add(new Question(question4, "Huffman", choices4));
        String question5 = "1 = ?";
        String[] choices5 = {"10", "11", "one", "01"};
        questionSet2.add(new Question(question5, "01", choices5));
        String question6 = "What is a 'kimura'";
        String[] choices6 = {"Hand-lock technique", "Bike stunt", "Disease", "TV brand"};
        questionSet2.add(new Question(question6, "Hand-lock technique", choices6));
        String question7 = "What object is the heaviest?";
        String[] choices7 = {"Earth", "Joe mama", "Mnt. Everest", "Andromeda"};
        questionSet2.add(new Question(question7, "Andromeda", choices7));
        Collections.shuffle(questionSet);
        Collections.shuffle(questionSet2);

        ArrayList<String> roundCategories = new ArrayList<>();
        roundCategories.add("Science");
        roundCategories.add("History");
        //roundCategories.add("Sports");
        //roundCategories.add("Cinema");
        //roundCategories.add("Business");
        //Collections.shuffle(roundCategories);
        questionsPerCategory.put(roundCategories.get(0), questionSet);
        questionsPerCategory.put(roundCategories.get(1), questionSet2);
    }

    public void startQuestion(int roundType) throws InterruptedException {


        //Prints a Question
        for (int counter = 0; counter < 2; counter++) {
            int questionCounter = counter + 1;
            int zeroOrOne = (int) Math.round(Math.random() * 2);
            String category = roundCategory(zeroOrOne);//Get the category of questions and display it to the user

            gameTypeLabel.setText( category +"-"+ rounds.get(roundType).getRoundType());

            Question question = null;
            //Retrieve the question that is going to be used for the game
            //Check the questionsPerCategory arraylist of questions if the size is 1 because there is case that all 4 questions of the same category can be played. In that case the counter for the least question is going
            //to be 1 but the question is located at the 0 index.
            if (questionsPerCategory.get(category).size() == 1) {
                question = questionsPerCategory.get(category).get(0);
            } else {
                question = questionsPerCategory.get(category).get(counter);
            }


            questionLabel.setText( questionCounter + " : " + question.getQuestion());//Print out the question for the specific category
            //Stores number of choices for a given question, this help us out later
            int numOfChoices = question.getChoices().size();//Number of possible answers
            for(int i = 0; i<numOfChoices; i++) {
                answer[i].setText(question.getChoices().get(i));
            }
            //Sets the right Answer
            ArrayList<String> choices = question.getChoices();
            String correctAnswer = question.getAnswer();//Get the correct answer for the question that is chosen
            int correctAnswerNumber = choices.indexOf(correctAnswer) + 1;
            // Sets each players answer
            for (int player = 0; player < players.size(); player++) {
                //If the round type is Ποντάρισμα get the bet of the player
                if (rounds.get(roundType).getRoundType().equals("Bet")) {
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
        if (category.equals("Science")) {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            startQuestion(1);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }


    }

    public void highScoreScreen(){
        //logic for high score
    }
}
        /*
            private JLabel answer2;
            private JLabel answer3;
            private JLabel answer4,answer5;
        answer1.setBounds(125,100,500,100);
        answer1.setBackground(new Color(50,50,50));
        answer1.setForeground(new Color(55,55,55));
        answer1.setFont(new Font("MV Boli",Font.PLAIN,35));
        answer1.setText("Test 1");

        answer2.setBounds(125,200,500,100);
        answer2.setBackground(new Color(50,50,50));
        answer2.setForeground(new Color(55,55,55));
        answer2.setFont(new Font("MV Boli",Font.PLAIN,35));
        answer2.setText("Test 2");

        answer3.setBounds(125,300,500,100);
        answer3.setBackground(new Color(50,50,50));
        answer3.setForeground(new Color(55,55,55));
        answer3.setFont(new Font("MV Boli",Font.PLAIN,35));
        answer3.setText("Test 3");

        answer4.setBounds(125,400,500,100);
        answer4.setBackground(new Color(50,50,50));
        answer4.setForeground(new Color(55,55,55));
        answer4.setFont(new Font("MV Boli",Font.PLAIN,35));
        answer4.setText("Test 4");

        frame.add(answer1);
        frame.add(answer2);
        frame.add(answer3);
        frame.add(answer4);
        frame.add(answer5);

         */

