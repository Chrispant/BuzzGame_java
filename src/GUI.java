import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class GUI extends JFrame implements ActionListener {
    //newGame game;
    final private int maxNumOfPlayers = 2;
    private int nextPlayer = 0;
    private int playersToPlay;
    private ArrayList<Question> currentQuestionSet;
    private Question currentQuestion;
    private final ArrayList<Player> players;

    private GameType gameType;

    private Game game;

    private JPanel playerPanel;

    private Timer timer;

    private JButton button1, button2, button3, button4;
    private JButton button5, button6, button7, button8;
    private JButton newGameButton, highScoreButton, quitButton;
    private JButton doneButton, betButton;
    private JButton gameType1Button, gameType2Button, gameType3Button;
    private JButton[] playerButton;

    private JTextField gameTypeLabel, playerName, betTypeLabel;
    private JTextArea questionLabel;
    private JLabel[] answer;
    private JLabel time_label;
    private JLabel playerTagLeft, playerTagRight;

    private JLabel playerNameLabel, player2NameLabel, numOfPlayersLabel, nameLabel;

    private JFrame frame, gameTypeFrame, frame2;

    private JLabel backgroundImage, backgroundImage1, backgroundImage2;

    private int currentBet;
    private long milliseconds;
    private boolean endOfTime;

    private LocalDateTime startTime;

    public GUI(Game game) throws IOException {
        this.game = game;

        players = new ArrayList<Player>();

        initComponents();
        init1PlayerGameFrame();
       // twoPlayersFrame();
        initGameTypeFrame();
        initListeners();

        //Hash map that pairs a set of questions to a category
        currentQuestionSet = new ArrayList<>();


    }

    private void initComponents() throws IOException {
        //GUI Quiz components
        frame = new JFrame();

        doneButton = new JButton("Done");


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
            playerButton[i].setBackground(new Color(60,60,160));
            playerButton[i].setForeground(new Color(255,255,255));
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


    }


    private void init1PlayerGameFrame() {

        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();

        betButton = new JButton("Bet");

        betTypeLabel = new JTextField();

        answer = new JLabel[4];
        for (int i = 0; i < 4; i++) {
            answer[i] = new JLabel("Test");
        }

        time_label = new JLabel();

        gameTypeLabel = new JTextField("\\u00C3");
        questionLabel = new JTextArea();


        //Quiz frame parameters
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 650);
        frame.setLayout(new BorderLayout());
        frame.setBackground(Color.WHITE);


        //betTypeLabel.setBounds(150, 200, 350, 100);
        betTypeLabel.setFocusable(true);
        betTypeLabel.setBackground(new Color(55, 55, 55));
        betTypeLabel.setForeground(new Color(255, 255, 255));
        betTypeLabel.setFont(new Font("ARIAL", Font.BOLD, 30));
        betTypeLabel.setHorizontalAlignment(JTextField.CENTER);
        betTypeLabel.setBorder(BorderFactory.createBevelBorder(1));
        betTypeLabel.setText("");
        betTypeLabel.setEditable(true);
        betTypeLabel.setPreferredSize(new Dimension(300, 150));

        betButton.setPreferredSize(new Dimension(200, 150));
        betButton.setFont(new Font("MV Boli", Font.BOLD, 20));

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

        button1.setBounds(0, 100, 45, 80);
        button1.setFont(new Font("MV Boli", Font.BOLD, 15));
        button1.setMnemonic(KeyEvent.VK_Q);
        button1.setFocusable(false);
        button1.setText("1");

        button2.setBounds(0, 200, 45, 80);
        button2.setFont(new Font("MV Boli", Font.BOLD, 12));
        button2.setMnemonic(KeyEvent.VK_W);
        button2.setFocusable(false);
        button2.setText("2");

        button3.setBounds(0, 300, 45, 80);
        button3.setFont(new Font("MV Boli", Font.BOLD, 15));
        button3.setMnemonic(KeyEvent.VK_E);
        button3.setFocusable(false);
        button3.setText("3");

        button4.setBounds(0, 400, 45, 80);
        button4.setFont(new Font("MV Boli", Font.BOLD, 15));
        button4.setMnemonic(KeyEvent.VK_R);
        button4.setFocusable(false);
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
            answer[i].setForeground(new Color(255, 0, 50));
            answer[i].setFont(new Font("ARIAL", Font.BOLD, 20));
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

        frame.add(betTypeLabel, BorderLayout.PAGE_START);
        frame.add(betButton, BorderLayout.PAGE_END);
    }


    private void twoPlayersFrame(){

        button5 = new JButton();
        button6 = new JButton();
        button7 = new JButton();
        button8 = new JButton();

        player2NameLabel = new JLabel();

        playerTagLeft = new JLabel();
        playerTagRight = new JLabel();


        playerTagLeft.setBounds(0, 470, 45, 50);
        playerTagLeft.setFont(new Font("Arial", Font.ITALIC, 10));
        playerTagLeft.setText("Player 1");
        playerTagRight.setBounds(50, 470, 45, 50);
        playerTagRight.setFont(new Font("Arial", Font.ITALIC, 10));
        playerTagRight.setText("Player 2");


        player2NameLabel.setBounds(0, 550, 400, 25);
        player2NameLabel.setFont(new Font("MV Boli", Font.BOLD, 15));

        button5.setBounds(50, 100, 45, 80);
        button5.setFont(new Font("MV Boli", Font.BOLD, 15));
        button5.setMnemonic(KeyEvent.VK_U);
        button5.setFocusable(false);
        button5.setText("U");

        button6.setBounds(50, 200, 45, 80);
        button6.setFont(new Font("MV Boli", Font.BOLD, 15));
        button6.setMnemonic(KeyEvent.VK_I);
        button6.setFocusable(false);
        button6.setText("I");

        button7.setBounds(50, 300, 45, 80);
        button7.setFont(new Font("MV Boli", Font.BOLD, 15));
        button7.setMnemonic(KeyEvent.VK_O);
        button7.setFocusable(false);
        button7.setText("O");

        button8.setBounds(50, 400, 45, 80);
        button8.setFont(new Font("MV Boli", Font.BOLD, 15));
        button8.setMnemonic(KeyEvent.VK_P);
        button8.setFocusable(false);
        button8.setText("P");

        frame.add(button5);
        frame.add(button6);
        frame.add(button7);
        frame.add(button8);
        frame.add(player2NameLabel);
        frame.add(playerTagLeft);
        frame.add(playerTagRight);
        frame.add(backgroundImage2);
        playerNameLabel.setVisible(true);
        player2NameLabel.setVisible(true);
        backgroundImage2.setVisible(false);
        betButton.setVisible(false);
        time_label.setVisible(false);
        betTypeLabel.setVisible(false);
        frame.setVisible(true);



    }

    private void initGameTypeFrame() throws IOException {
        BufferedImage myPicture = ImageIO.read(new File("images/basic_frame_image.jpg"));
        backgroundImage = new JLabel(new ImageIcon(myPicture));

        gameTypeFrame = new JFrame();

        gameTypeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameTypeFrame.setSize(650, 650);
        gameTypeFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
        gameTypeFrame.setBackground(Color.WHITE);
        gameTypeFrame.setLocationRelativeTo(null);


        gameType1Button = new JButton();
        gameType2Button = new JButton();
        gameType3Button = new JButton();

        gameType1Button.setPreferredSize(new Dimension(130, 50));
        gameType1Button.setFont(new Font("Arial", Font.BOLD, 12));
        gameType1Button.setFocusable(false);


        gameType2Button.setPreferredSize(new Dimension(130, 50));
        gameType2Button.setFont(new Font("Arial", Font.BOLD, 12));
        gameType2Button.setFocusable(false);

        gameType3Button.setPreferredSize(new Dimension(130, 50));
        gameType3Button.setFont(new Font("Arial", Font.BOLD, 12));
        gameType3Button.setFocusable(false);


        gameTypeFrame.add(backgroundImage);

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
                playerToName(nextPlayer + 1);
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



        gameType1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (playersToPlay == 1) {
                    gameType = GameType.CORRECT_ANSWER;
                    gameTypeFrame.setVisible(false);
                    frame.setVisible(true);


                    init1PlayerGame();

                } else {

                }
            }
        });

        gameType2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (playersToPlay == 1) {
                    gameType = GameType.STOP_ALARM;

                    gameTypeFrame.setVisible(false);
                    frame.setVisible(true);

                    init1PlayerGame();
                } else {

                }
            }
        });


        gameType3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameType = GameType.BETTING;

                gameTypeFrame.setVisible(false);
                frame.setVisible(true);

                init1PlayerGame();
            }
        });

        betButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                questionLabel.setText(currentQuestion.getQuestion());

                ArrayList<String> questionChoices = currentQuestion.getChoices();

                for (int i = 0; i < questionChoices.size(); i++) {
                    answer[i].setText(questionChoices.get(i));
                }

                Player player = game.getCurrentPlayer();

                playerNameLabel.setText("Current Player:" + player.getPlayerName() + ", Current Points: " + player.getPoints());


                questionLabel.setVisible(true);
                button1.setVisible(true);
                button2.setVisible(true);
                button3.setVisible(true);
                button4.setVisible(true);
                playerNameLabel.setVisible(true);
                playerNameLabel.setVisible(true);


                for (int i = 0; i < 4; i++) {
                    answer[i].setVisible(true);
                }


                betTypeLabel.setVisible(false);
                betButton.setVisible(false);

                try {
                    currentBet = Integer.parseInt(betTypeLabel.getText());
                } catch (NumberFormatException numberFormatException) {
                    numberFormatException.printStackTrace();
                    currentBet = 0;
                }

            }
        });


    }


    private void playerAnswers(int choice) {

        String correctAnswer = currentQuestion.getAnswer();
        if (gameType == GameType.CORRECT_ANSWER) {
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
                player1gameOver();
            }
        } else if (gameType == GameType.BETTING) {
            if (correctAnswer.equals(currentQuestion.getChoices().get(choice))) {
                game.getCurrentPlayer().addPoints(currentBet);
            } else {
                game.getCurrentPlayer().addPoints(-currentBet);
            }

            if (!currentQuestionSet.isEmpty()) {
                currentQuestion = currentQuestionSet.remove(0);

                time_label.setVisible(false);
                questionLabel.setVisible(false);
                button1.setVisible(false);
                button2.setVisible(false);
                button3.setVisible(false);
                button4.setVisible(false);
                playerNameLabel.setVisible(false);
                playerNameLabel.setVisible(false);


                for (int i = 0; i < 4; i++) {
                    answer[i].setVisible(false);
                }

                betTypeLabel.setVisible(true);
                betButton.setVisible(true);


            } else {
                player1gameOver();
            }


        } else {
            timer.stop();
            timer = null;
            time_label.setText("timer 00:00");
            if (correctAnswer.equals(currentQuestion.getChoices().get(choice))) {



                double points = milliseconds * 0.2;
                int playerPoints = (int) points;
                if(endOfTime){
                    playerPoints = 0;
                }

                game.getCurrentPlayer().addPoints(playerPoints);
            }

            if (!currentQuestionSet.isEmpty()) {
                endOfTime = false;
                currentQuestion = currentQuestionSet.remove(0);

                questionLabel.setText(currentQuestion.getQuestion());

                ArrayList<String> questionChoices = currentQuestion.getChoices();

                for (int i = 0; i < questionChoices.size(); i++) {
                    answer[i].setText(questionChoices.get(i));
                }
                Player player = game.getCurrentPlayer();
                playerNameLabel.setText("Current Player:" + player.getPlayerName() + ", Current Points: " + player.getPoints());


                startTime = LocalDateTime.now();

                timer = new Timer(1, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        LocalDateTime now = LocalDateTime.now();
                        Duration duration = Duration.between(startTime, now);

                        long seconds = duration.toSeconds();
                         milliseconds = duration.toMillis();
                        if (milliseconds == 5000) {
                            JOptionPane.showMessageDialog(frame, "End of time 0 points gained even if you answered correct");
                            endOfTime = true;
                        }

                        time_label.setText("Time: " + seconds + "");
                    }
                });


                timer.start();

            } else {
                player1gameOver();
            }


        }


    }

    private void init1PlayerGame() {

        if (gameType == GameType.CORRECT_ANSWER) {
            time_label.setVisible(false);
            betTypeLabel.setVisible(false);
            betButton.setVisible(false);

            start1PlayerGame();

        } else if (gameType == GameType.BETTING) {
            time_label.setVisible(false);
            questionLabel.setVisible(false);
            button1.setVisible(false);
            button2.setVisible(false);
            button3.setVisible(false);
            button4.setVisible(false);
            playerNameLabel.setVisible(false);
            playerNameLabel.setVisible(false);


            for (int i = 0; i < 4; i++) {
                answer[i].setVisible(false);
            }

            betTypeLabel.setVisible(true);
            betButton.setVisible(true);

            String category = game.getRandomCategory();
            gameTypeLabel.setText(category);
            currentQuestionSet = game.getQuestionsBasedOnCategory();

            currentQuestion = currentQuestionSet.remove(0);


        } else {
            time_label.setVisible(true);
            betTypeLabel.setVisible(false);
            betButton.setVisible(false);

            startTime = LocalDateTime.now();
            start1PlayerGame();


            timer = new Timer(1, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    LocalDateTime now = LocalDateTime.now();
                    Duration duration = Duration.between(startTime, now);

                    long seconds = duration.toSeconds();
                    milliseconds = duration.toMillis();
                    if (milliseconds == 5010) {
                        JOptionPane.showMessageDialog(frame, "End of time 0 points gained even if you answer correct...");
                        endOfTime = true;
                        time_label.setText("Time's up!");
                        return;
                    }
                    if(!endOfTime) {
                        time_label.setText("Time: " + seconds + "");
                    }
                }
            });


            timer.start();

        }


    }


    private void start1PlayerGame() {

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

    private void player1gameOver()  {
        System.out.println("GAME FINISHED, total points gathered " + game.getCurrentPlayer().getPoints());
        Player player = game.getCurrentPlayer();
        JOptionPane.showMessageDialog(frame, "Player " + player.getPlayerName() + " has won " + player.getPoints() + " points");


        try {
            File myObj = new File("score.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }

            String str = "GAME FINISHED, total points gathered for player: " + game.getCurrentPlayer().getPlayerName() + " ,points: " + game.getCurrentPlayer().getPoints();
            FileOutputStream outputStream = new FileOutputStream(myObj);
            byte[] strToBytes = str.getBytes();
            outputStream.write(strToBytes);

            outputStream.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }




    public void start() {
        setVisible(true);
    }

    //updates starting frame to player number choice and then starts the game's frame
    public void newGame() {
        updateFrame("Add Players");
    }

    private void selectGameType() {
        if (playersToPlay == 1) {

            gameType1Button.setText("Correct Answer");
            gameType2Button.setText("Stop Alarm");
            gameType3Button.setText("Betting");




        } else {
            gameType1Button.setText("Quick Answer");
            gameType2Button.setText("Thermometro");
            twoPlayersFrame();
            gameType3Button.setVisible(false);
        }

        gameTypeFrame.setVisible(true);
    }

    public void updateFrame(String whichFrame) {
        if (whichFrame.equals("Add Players")) {
            remove(newGameButton);
            remove(highScoreButton);
            remove(quitButton);
            remove(backgroundImage);
            add(numOfPlayersLabel);

            //add(topPanel, BorderLayout.PAGE_START);
            //add(gridPanel);
            //topPanel.add(numOfPlayersLabel);
            for (int i = 0; i < maxNumOfPlayers; i++) {
                add(playerButton[i]);
            }
            add(backgroundImage1);
            revalidate();
        }
        if (whichFrame.equals("Add Names")) {
            numOfPlayersLabel.setText("Choose Nickname");
            for (int i = 0; i < maxNumOfPlayers; i++) {
                remove(playerButton[i]);
                //playerButton[i].setVisible(false);
            }
            remove(backgroundImage1);

            add(nameLabel);
            add(playerName);
            numOfPlayersLabel.setVisible(true);
            doneButton.setVisible(true);
            add(backgroundImage2);
            repaint();
            revalidate();

        }

    }

    //playerToName takes a number depending on which player's turn it is to give name

    public void playerToName(int nextPlayer) {
        if (nextPlayer <= playersToPlay) {
            nameLabel.setText("Player " + nextPlayer + " enter your nickname");
            nameLabel.setForeground(Color.RED);
            nameLabel.setVisible(true);

        } else {
            setVisible(false);
            // frame.setVisible(true);
            selectGameType();


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



