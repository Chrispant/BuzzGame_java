import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class GUI extends JFrame implements ActionListener {
    //newGame game;
    final private int maxNumOfPlayers = 2;
    private  int nextPlayer = 0;
    private int playersToPlay;
    private ArrayList<Question> currentQuestionSet;
    private Question currentQuestion;
    private final ArrayList<Round> rounds;
    private final ArrayList<Player> players;

    private Game game;

    private JPanel playerPanel;

    private JButton button1, button2, button3, button4;
    private JButton newGameButton, highScoreButton, quitButton;
    private JButton doneButton;
    private JButton gameType1Button,gameType2Button,gameType3Button;
    private JButton[] playerButton;

    private JTextField gameTypeLabel, playerName;
    private JTextArea questionLabel;
    private JLabel[] answer;
    private JLabel time_label;

    private JLabel playerNameLabel, numOfPlayersLabel, nameLabel;

    private JFrame frame,gameTypeFrame;

    private JLabel backgroundImage,backgroundImage1,backgroundImage2;

    public GUI(Game game) throws IOException {
        this.game = game;

        players = new ArrayList<Player>();

        initComponents();
        initGameTypeFrame();
        initListeners();

        //Hash map that pairs a set of questions to a category
        currentQuestionSet = new ArrayList<>();

        rounds = new ArrayList<Round>();
        rounds.add(new Round("Right Answer"));
        rounds.add(new Round("Bet"));
        rounds.add(new Round("Thermometer"));
        rounds.add(new Round("Stop The Clock"));
        rounds.add(new Round("Fastest Answer"));
        Collections.shuffle(rounds);

    }

    private void initComponents() throws IOException {
        //GUI Quiz components
        frame = new JFrame();


        gameTypeLabel = new JTextField("\\u00C3");
        questionLabel = new JTextArea();

        doneButton = new JButton("Done");



        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();

        answer = new JLabel[4];
        for (int i = 0; i < 4; i++) {
            answer[i] = new JLabel("Test");
        }

        time_label = new JLabel();
        playerNameLabel = new JLabel();
        nameLabel = new JLabel();
        numOfPlayersLabel = new JLabel();

        playerName = new JTextField();
        playerPanel = new JPanel();
        playerButton = new JButton[maxNumOfPlayers];
        for (int i = 0; i < maxNumOfPlayers; i++) {
            int j = i + 1;
            if (i == 0) {
                playerButton[i] = new JButton("1 Player  ");
            } else {
                playerButton[i] = new JButton(j + " Players");
            }
        }

        //Gui image components
        BufferedImage myPicture = ImageIO.read(new File("images/basic_frame_image.jpg"));
        backgroundImage = new JLabel(new ImageIcon(myPicture));
        backgroundImage1 = new JLabel(new ImageIcon(myPicture));
        backgroundImage2 = new JLabel(new ImageIcon(myPicture));

        //Starting frame's parameters
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 650);
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setLocationRelativeTo(null);


        doneButton.setBounds(500, 500, 120, 50);
        doneButton.setFont(new Font("MV Boli", Font.BOLD, 30));
        doneButton.setFocusable(false);


        add(doneButton);

        doneButton.setVisible(false);



        //Gui Starting frame Components///////////////////////////////////////////////////////////
        newGameButton = new JButton("New game");
        highScoreButton = new JButton("High Score");
        quitButton = new JButton("Exit");

        //buttons
        newGameButton.setBounds(150, 50, 300, 150);
        newGameButton.setFont(new Font("MV Boli", Font.BOLD, 35));
        newGameButton.setFocusable(false);

        add(newGameButton);

        highScoreButton.setBounds(150, 250, 300, 150);
        highScoreButton.setFont(new Font("MV Boli", Font.BOLD, 35));
        highScoreButton.setFocusable(false);

        add(highScoreButton);

        quitButton.setBounds(150, 450, 300, 150);
        quitButton.setFont(new Font("MV Boli", Font.BOLD, 35));
        quitButton.setFocusable(false);

        add(quitButton);
        add(backgroundImage);


        //Choosing number of players components//////////////////////////////////////////////////////////
        BoxLayout boxlayout = new BoxLayout(playerPanel, BoxLayout.Y_AXIS);

        playerPanel.setLayout(boxlayout);


        int y = 0;
        for (int i = 0; i < maxNumOfPlayers; i++) {
            int x = i + 1;
            playerButton[i].setBounds(180, 100 + y, 250, 75);
            playerButton[i].setFont(new Font("Arial", Font.BOLD, 25));
            playerButton[i].setFocusable(false);
            y += 100;
        }

        nameLabel.setBounds(0, 50, 650, 100);
        nameLabel.setForeground(new Color(11, 85, 155));
        nameLabel.setFont(new Font("ARIAL", Font.BOLD, 25));
        nameLabel.setHorizontalAlignment(JTextField.CENTER);

        playerName.setBounds(150, 200, 350, 100);
        playerName.setFocusable(true);
        playerName.setBackground(new Color(55, 55, 55));
        playerName.setForeground(new Color(255, 255, 255));
        playerName.setFont(new Font("ARIAL", Font.BOLD, 30));
        playerName.setHorizontalAlignment(JTextField.CENTER);
        playerName.setBorder(BorderFactory.createBevelBorder(1));
        playerName.setText("");
        playerName.setEditable(true);
        playerName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                playerName.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        numOfPlayersLabel.setBounds(0, 0, 650, 50);
        numOfPlayersLabel.setBackground(new Color(155, 155, 155));
        numOfPlayersLabel.setForeground(new Color(155, 155, 155));
        numOfPlayersLabel.setFont(new Font("ARIAL", Font.BOLD, 30));
        numOfPlayersLabel.setHorizontalAlignment(JTextField.CENTER);
        numOfPlayersLabel.setText("Choose Number Of Players");

        //Quiz frame parameters
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 650);
        frame.setLayout(new BorderLayout());
        frame.setBackground(Color.WHITE);

        gameTypeLabel.setBounds(0, 0, 650, 50);
        gameTypeLabel.setBackground(new Color(25, 25, 25));
        gameTypeLabel.setForeground(new Color(125, 155, 0));
        gameTypeLabel.setFont(new Font("Ink Free", Font.BOLD, 30));
        gameTypeLabel.setBorder(BorderFactory.createBevelBorder(1));
        gameTypeLabel.setHorizontalAlignment(JTextField.CENTER);
        gameTypeLabel.setEditable(false);
        gameTypeLabel.setText("Category - Game Type");///////////////////////////////////////////////////////////

        questionLabel.setBounds(0, 50, 650, 50);
        questionLabel.setLineWrap(true);
        questionLabel.setWrapStyleWord(true);
        questionLabel.setBackground(new Color(25, 25, 25));
        questionLabel.setForeground(new Color(125, 155, 25));
        questionLabel.setFont(new Font("MV Boli", Font.BOLD, 25));
        questionLabel.setBorder(BorderFactory.createBevelBorder(1));
        questionLabel.setEditable(false);
        questionLabel.setText("Question");////////////////////////////////////////////////////////

        button1.setBounds(0, 100, 100, 80);
        button1.setFont(new Font("MV Boli", Font.BOLD, 35));
        button1.setMnemonic(KeyEvent.VK_Q);
        button1.setMnemonic(KeyEvent.VK_U);
        button1.setFocusable(false);
        button1.addActionListener(this);
        button1.setText("1");

        button2.setBounds(0, 200, 100, 80);
        button2.setFont(new Font("MV Boli", Font.BOLD, 35));
        button2.setMnemonic(KeyEvent.VK_W);
        button2.setMnemonic(KeyEvent.VK_I);
        button2.setFocusable(false);
        button2.addActionListener(this);
        button2.setText("2");

        button3.setBounds(0, 300, 100, 80);
        button3.setFont(new Font("MV Boli", Font.BOLD, 35));
        button3.setMnemonic(KeyEvent.VK_E);
        button3.setMnemonic(KeyEvent.VK_O);
        button3.setFocusable(false);
        button3.addActionListener(this);
        button3.setText("3");

        button4.setBounds(0, 400, 100, 80);
        button4.setFont(new Font("MV Boli", Font.BOLD, 35));
        button4.setMnemonic(KeyEvent.VK_R);
        button4.setMnemonic(KeyEvent.VK_P);
        button4.setFocusable(false);
        // button4.addActionListener(this);
        button4.setText("4");

        playerNameLabel.setBounds(0, 500, 400, 25);
        playerNameLabel.setFont(new Font("MV Boli", Font.BOLD, 15));


        time_label.setBounds(0, 500, 100, 25);
        time_label.setBackground(new Color(50, 50, 50));
        time_label.setForeground(new Color(155, 85, 85));
        time_label.setFont(new Font("MV Boli", Font.PLAIN, 16));
        time_label.setHorizontalAlignment(JTextField.CENTER);
        time_label.setText("timer 00:00");

        int x = 0;////////////////////////////////////////////////////////
        for (int i = 0; i < 4; i++) {
            answer[i].setBounds(125, 100 + x, 500, 100);
            answer[i].setBackground(new Color(50, 50, 50));
            answer[i].setForeground(new Color(55, 55, 55));
            answer[i].setFont(new Font("MV Boli", Font.PLAIN, 20));
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

        frame.add(playerNameLabel);
        frame.add(time_label);

    }


    private void initGameTypeFrame() throws IOException{
        //Gui image components
        BufferedImage myPicture = ImageIO.read(new File("images/basic_frame_image.jpg"));
        backgroundImage = new JLabel(new ImageIcon(myPicture));

        gameTypeFrame = new JFrame();

        gameTypeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameTypeFrame.setSize(650, 650);
        gameTypeFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        gameTypeFrame.setBackground(Color.WHITE);
        gameTypeFrame.setLocationRelativeTo(null);


        gameType1Button = new JButton();
        gameType2Button = new JButton();
        gameType3Button = new JButton();



       // gameType1Button.setBounds(150, 50, 300, 150);
        gameType1Button.setPreferredSize(new Dimension(150, 80));
        gameType1Button.setFont(new Font("MV Boli", Font.BOLD, 15));
        gameType1Button.setFocusable(false);


        gameType2Button.setPreferredSize(new Dimension(150, 80));
        gameType2Button.setFont(new Font("MV Boli", Font.BOLD, 15));
        gameType2Button.setFocusable(false);

        gameType3Button.setPreferredSize(new Dimension(150, 80));
        //gameType3Button.setBounds(150, 50, 300, 150);
        gameType3Button.setFont(new Font("MV Boli", Font.BOLD, 15));
        gameType3Button.setFocusable(false);

        gameTypeFrame.add(backgroundImage);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));

        jPanel.add(gameType1Button);
        jPanel.add(gameType2Button);
        jPanel.add(gameType3Button);

//        gameTypeFrame.add(jPanel);

       gameTypeFrame.add(gameType1Button);
       gameTypeFrame.add(gameType2Button);
       gameTypeFrame.add(gameType3Button);

    }

    private void initListeners() {
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGame();

            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

        highScoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                highScoreScreen();

            }
        });


        playerButton[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playersToPlay = 1;
                updateFrame("Add Names");
                playerToName(1);
            }
        });


        playerButton[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playersToPlay = 2;
                updateFrame("Add Names");
                playerToName(1);
            }
        });


        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {




                String name = playerName.getText();
                game.addPlayer(name);
                System.out.println(name);
                playerName.setText("");
                nextPlayer++;
                playerToName(nextPlayer+1);
            }
        });


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerAnswers(0);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerAnswers(1);
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerAnswers(2);
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerAnswers(3);
            }
        });

    }


    private void playerAnswers(int choice) {

        String correctAnswer = currentQuestion.getAnswer();

        //TODO prepei n bei diaforetiko logic gia kathe eidous paixnidiou, uparxon ulopoihsh einia gia tn tupos swstis apantisis
        //User found a correct answer
        if (correctAnswer.equals(currentQuestion.getChoices().get(choice))) {
            game.getCurrentPlayer().addPoints(1000);
        }

        if (!currentQuestionSet.isEmpty()) {
            currentQuestion = currentQuestionSet.remove(0);
            questionLabel.setText(currentQuestion.getQuestion());

            ArrayList<String> questionChoices = currentQuestion.getChoices();

            for (int i = 0; i < questionChoices.size(); i++) {
                answer[i].setText(questionChoices.get(i));
            }
            Player player = game.getCurrentPlayer();
            playerNameLabel.setText("Current Player:" + player.getPlayerName() + ", Current Points: " + player.getPoints());
        } else {
            System.out.println("GAME FINISHED, total points gathered " + game.getCurrentPlayer().getPoints());
            Player player = game.getCurrentPlayer();
            JOptionPane.showMessageDialog(frame, "Player " + player.getPlayerName() + " has won " + player.getPoints() + " points");
            //Na 3anapaei stn arxikh pou dialegei game
        }

    }

    private void initGame() {

        String category = game.getRandomCategory();
        gameTypeLabel.setText(category);
        currentQuestionSet = game.getQuestionsBasedOnCategory();

        currentQuestion = currentQuestionSet.remove(0);

        questionLabel.setText(currentQuestion.getQuestion());

        ArrayList<String> questionChoices = currentQuestion.getChoices();

        for (int i = 0; i < questionChoices.size(); i++) {
            answer[i].setText(questionChoices.get(i));
        }

        Player player = game.getCurrentPlayer();

        playerNameLabel.setText("Current Player:" + player.getPlayerName() + ", Current Points: " + player.getPoints());

    }


    public void start() {
        setVisible(true);
    }

    //updates starting frame to player number choice and then starts the game's frame
    public void newGame() {
        updateFrame("Add Players");
    }

    private void selectGameType(){
        if(playersToPlay == 1){

            gameType1Button.setText("Correct Answer");
            gameType2Button.setText("Stop Alarm");
            gameType3Button.setText("Betting");


        }else{
            gameType1Button.setText("Quick Answer");
            gameType2Button.setText("Thermometro");
            gameType3Button.setVisible(false);
        }

        gameTypeFrame.setVisible(true);
    }

    public void updateFrame(String whichFrame){
        if(whichFrame.equals("Add Players")){
            remove(newGameButton);remove(highScoreButton);remove(quitButton);remove(backgroundImage);
            add(numOfPlayersLabel);

            //add(topPanel, BorderLayout.PAGE_START);
            //add(gridPanel);
            //topPanel.add(numOfPlayersLabel);
            for(int i = 0; i<maxNumOfPlayers; i++){
                add(playerButton[i]);
            }
            add(backgroundImage1);
            revalidate();
        }
        if(whichFrame.equals("Add Names")){
            numOfPlayersLabel.setText("Choose Nickname");
            for(int i =0; i<maxNumOfPlayers; i++){
                remove(playerButton[i]);
            }
            remove(backgroundImage1);

            add(nameLabel);
            add(playerName);
            doneButton.setVisible(true);
            add(backgroundImage2);
            revalidate();

        }

    }

    //playerToName takes a number depending on which player's turn it is to give name

    public void playerToName(int nextPlayer) {
        if (nextPlayer <= playersToPlay) {
            nameLabel.setText("Player " + nextPlayer + " enter your nickname");
            nameLabel.setForeground(Color.RED);

        } else {
            setVisible(false);
           // frame.setVisible(true);


            selectGameType();


          //  initGame();

        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
//        try {
//            startQuestion(1);
//        } catch (InterruptedException interruptedException) {
//            interruptedException.printStackTrace();
//        }


    }

    public void highScoreScreen() {
        //logic for high score
    }

    public void addPlayers(int playerCount) {
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

