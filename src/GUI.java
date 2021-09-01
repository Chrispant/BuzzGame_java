import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;


public class  GUI extends JFrame {


    public final Game game;
    final private int maxNumOfPlayers;
    private int playersToPlay;
    private int roundIs = 1;
    private String gameType = " ";
    boolean playersHaveBet = false;

    private final ArrayList<Player> players;

    private JButton button1,button2,button3,button4, button5, button6, button7, button8 ;
    private JButton newGameButton, highScoreButton, readyButton, quitButton;
    private JButton[] playerButton;
    private JButton gameType1Button, gameType2Button, gameType3Button;
    private JButton button250, button500, button750, button1000;

    private JTextField gameTypeLabel, playerName;
    private JTextArea questionLabel;
    private JLabel[] answer;
    private JLabel time_label, numOfPlayersLabel, nameLabel;
    private JLabel playerTagLeft, playerTagRight;

    private JFrame frame, betFrame, messageFrame;

    private JLabel backgroundImage,backgroundImage1,backgroundImage2;

    public GUI(Game game) throws IOException {
        this.game = game;
        maxNumOfPlayers = 2;


        players = new ArrayList<Player>();
        initBasicComponents();
        initQandAComponents();

    }

    /**
     * initializes basic frame's components
     * @throws IOException
     */
    public void initBasicComponents() throws IOException {
        newGameButton = new JButton("New game");
        highScoreButton = new JButton("High Score");
        quitButton = new JButton("Exit");
        readyButton = new JButton("OK");

        numOfPlayersLabel = new JLabel();
        nameLabel = new JLabel();

        playerName = new JTextField();



        playerButton = new JButton[maxNumOfPlayers];
        for (int i = 0; i < maxNumOfPlayers; i++) {
            int j = i + 1;
            if (i == 0) {
                playerButton[i] = new JButton("1 Player  ");
            } else {
                playerButton[i] = new JButton(j + " Players");
            }
        }

        BufferedImage myPicture = ImageIO.read(new File("images/basic_frame_image.jpg"));
        backgroundImage = new JLabel(new ImageIcon(myPicture));
        backgroundImage1 = new JLabel(new ImageIcon(myPicture));
        backgroundImage2 = new JLabel(new ImageIcon(myPicture));

        //Starting frame's parameters
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setLocationRelativeTo(null);



        //buttons
        newGameButton.setBounds(900, 50, 300, 150);
        newGameButton.setFont(new Font("MV Boli", Font.BOLD, 35));
        newGameButton.setFocusable(false);
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGame();
            }
        });

        highScoreButton.setBounds(900, 250, 300, 150);
        highScoreButton.setFont(new Font("MV Boli", Font.BOLD, 35));
        highScoreButton.setFocusable(false);
        highScoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                highScoreScreen();
            }
        });

        quitButton.setBounds(900, 450, 300, 150);
        quitButton.setFont(new Font("Arial", Font.BOLD, 35));
        quitButton.setFocusable(false);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

        int y = 0;
        for (int i = 0; i < maxNumOfPlayers; i++) {
            int x = i + 1;
            playerButton[i].setBounds(520, 150 + y, 250, 75);
            playerButton[i].setFont(new Font("Arial", Font.BOLD, 25));
            playerButton[i].setFocusable(false);
            playerButton[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playersToPlay = x;
                    updateFrame("Add Names");
                    playerToName(1);

                }
            });
            y += 100;
        }

        nameLabel.setBounds(50, 120, 650, 100);
        nameLabel.setForeground(new Color(25, 25, 11));
        nameLabel.setFont(new Font("ARIAL", Font.BOLD, 25));
        nameLabel.setHorizontalAlignment(JTextField.LEFT);

        playerName.setBounds(50, 200, 350, 100);
        playerName.setFocusable(true);
        playerName.setBackground(new Color(55, 55, 55));
        playerName.setForeground(new Color(255, 255, 255));
        playerName.setFont(new Font("ARIAL", Font.BOLD, 30));
        playerName.setHorizontalAlignment(JTextField.LEFT);
        playerName.setBorder(BorderFactory.createBevelBorder(1));
        playerName.setText("Adam");
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

        numOfPlayersLabel.setBounds(300, 0, 650, 50);
        numOfPlayersLabel.setBackground(new Color(155, 155, 155));
        numOfPlayersLabel.setForeground(new Color(155, 155, 155));
        numOfPlayersLabel.setFont(new Font("ARIAL", Font.BOLD, 30));
        numOfPlayersLabel.setHorizontalAlignment(JTextField.CENTER);
        numOfPlayersLabel.setText("Choose Number Of Players");


        readyButton.setBounds(75,330,300,100);
        readyButton.setFont(new Font("ARIAL", Font.BOLD, 30));
        readyButton.setText("Ready");
        readyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameOfPlayer = playerName.getText();
                players.add(new Player(nameOfPlayer,0));
                if(players.size()==2 && playersToPlay == 2){
                    playerToName(3);
                }else if(players.size()==1 && playersToPlay == 2){
                    playerName.setText("Bella");
                    playerToName(2);
                }else if(players.size()==1 && playersToPlay == 1){
                    playerToName(2);
                }

            }
        });

        gameType1Button = new JButton();
        gameType2Button = new JButton();
        gameType3Button = new JButton();

        gameType1Button.setBounds(530,150,200,100);
        gameType1Button.setFont(new Font("Arial", Font.BOLD, 15));
        gameType1Button.setFocusable(false);
        gameType1Button.setText("Answer Correct");
        gameType1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameType = "Answer Correct";
                updateFrame("Update Question");
                frame.setVisible(true);
                gameType1Button.setEnabled(false);
            }
        });


        gameType2Button.setBounds(530,300,200,100);
        gameType2Button.setFont(new Font("Arial", Font.BOLD, 15));
        gameType2Button.setFocusable(false);
        gameType2Button.setText("Bet");
        gameType2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameType = "Bet";
                updateFrame("Update Question");
                initBetFrame();
                playerToBet(1);
                betFrame.setVisible(true);
                setVisible(false);
                gameType2Button.setEnabled(false);

            }
        });

        gameType3Button.setBounds(530,450,200,100);
        gameType3Button.setFont(new Font("Arial", Font.BOLD, 15));
        gameType3Button.setFocusable(false);
        gameType3Button.setText("Stop The Clock");
        gameType3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameType = "Stop The Clock";
                updateFrame("Update Question");
                time_label.setVisible(true);
            }
        });





        add(newGameButton);
        add(highScoreButton);
        add(quitButton);
        add(gameType1Button);
        add(gameType2Button);
        add(gameType3Button);
        add(backgroundImage);
        gameType1Button.setVisible(false);
        gameType2Button.setVisible(false);
        gameType3Button.setVisible(false);

    }

    /**
     * initializes frame for the bet type of game
     */
    public void initBetFrame(){
        betFrame = new JFrame();
        button250 = new JButton();
        button500 = new JButton();
        button750 = new JButton();
        button1000 = new JButton();

        betFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        betFrame.setSize(1280, 720);
        betFrame.setLayout(new BorderLayout());
        betFrame.setBackground(Color.WHITE);
        betFrame.setLocationRelativeTo(null);


        button250.setBounds(450,100, 300,100);
        button250.setFont(new Font("Arial", Font.BOLD, 15));
        button250.setFocusable(false);
        button250.setText("250");
        button250.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                betFrame.setVisible(false);
                if(players.size()== 1){
                    players.get(0).setBet(250);
                    //updateFrame("Update Question");
                    frame.setVisible(true);
                }else if(players.size() == 2 && !playersHaveBet){
                    players.get(0).setBet(250);
                    playerToBet(2);
                }else{
                    players.get(1).setBet(250);
                    playersHaveBet = false;
                    //updateFrame("Update Question");
                    frame.setVisible(true);
                }

            }
        });

        button500.setBounds(450,250, 300,100);
        button500.setFont(new Font("Arial", Font.BOLD, 15));
        button500.setFocusable(false);
        button500.setText("500");
        button500.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                betFrame.setVisible(false);
                if(players.size()== 1){
                    players.get(0).setBet(500);
                    //updateFrame("Update Question");
                    frame.setVisible(true);
                }else if(players.size() == 2 && !playersHaveBet){
                    players.get(0).setBet(500);
                    playerToBet(2);
                }else{
                    players.get(1).setBet(500);
                    playersHaveBet = false;
                    //updateFrame("Update Question");
                    frame.setVisible(true);
                }

            }
        });

        button750.setBounds(450,400, 300,100);
        button750.setFont(new Font("Arial", Font.BOLD, 15));
        button750.setFocusable(false);
        button750.setText("750");
        button750.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                betFrame.setVisible(false);
                if(players.size()== 1){
                    players.get(0).setBet(750);
                    //updateFrame("Update Question");
                    frame.setVisible(true);
                }else if(players.size() == 2 && !playersHaveBet){
                    players.get(0).setBet(750);
                    playerToBet(2);
                }else{
                    players.get(1).setBet(750);
                    playersHaveBet = false;
                    //updateFrame("Update Question");
                    frame.setVisible(true);
                }



            }
        });

        button1000.setBounds(450,550, 300,100);
        button1000.setFont(new Font("Arial", Font.BOLD, 15));
        button1000.setFocusable(false);
        button1000.setText("1000");
        button1000.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                betFrame.setVisible(false);
                if(players.size()== 1){
                    players.get(0).setBet(1000);
                    //updateFrame("Update Question");
                    frame.setVisible(true);
                }else if(players.size() == 2 && !playersHaveBet){
                    players.get(0).setBet(1000);
                    playerToBet(2);
                }else{
                    players.get(1).setBet(1000);
                    playersHaveBet = false;
                    //updateFrame("Update Question");
                    frame.setVisible(true);
                }



            }
        });



        betFrame.add(button250);
        betFrame.add(button500);
        betFrame.add(button750);
        betFrame.add(button1000);
        betFrame.add(numOfPlayersLabel);
        betFrame.add(backgroundImage1);

        betFrame.setVisible(true);

    }

    /**
     * initializes components for Q&A frame
     *
     */
    public void initQandAComponents() {
        //GUI Quiz components
        frame = new JFrame();

        gameTypeLabel = new JTextField();
        questionLabel = new JTextArea();

        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();

        answer = new JLabel[4];

        for(int i = 0; i<4; i++){
            answer[i] = new JLabel("Test");
        }

        time_label = new JLabel();
        playerTagLeft = new JLabel();

        messageFrame = new JFrame();



        //Quiz frame parameters
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280,720);
        frame.setLayout(new BorderLayout());
        frame.setBackground(Color.WHITE);
        frame.setLocation(getLocation());

        gameTypeLabel.setBounds(0,0,1280,50);
        gameTypeLabel.setBackground(new Color(25,25,25));
        gameTypeLabel.setForeground(new Color(125,155,0));
        gameTypeLabel.setFont(new Font("Ink Free",Font.BOLD,30));
        gameTypeLabel.setBorder(BorderFactory.createBevelBorder(1));
        gameTypeLabel.setHorizontalAlignment(JTextField.CENTER);
        gameTypeLabel.setEditable(false);
        gameTypeLabel.setText("Category - Game Type");///////////////////////////////////////////////////////////

        questionLabel.setBounds(0,50,1280,50);
        questionLabel.setLineWrap(true);
        questionLabel.setWrapStyleWord(true);
        questionLabel.setBackground(new Color(25,25,25));
        questionLabel.setForeground(new Color(125,155,25));
        questionLabel.setFont(new Font("MV Boli",Font.BOLD,20));
        questionLabel.setBorder(BorderFactory.createBevelBorder(1));
        questionLabel.setEditable(false);
        questionLabel.setText("Question");////////////////////////////////////////////////////////



        button1.setBounds(0, 100, 45, 80);
        button1.setFont(new Font("MV Boli", Font.BOLD, 15));
        button1.setMnemonic(KeyEvent.VK_Q);
        button1.setFocusable(false);
        button1.setText("Q");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                players.get(0).setAnswer(answer[0].getText());
                try {
                    disableButtons(1);
                    checkAnswer(0);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }

            }
        });

        button2.setBounds(0, 200, 45, 80);
        button2.setFont(new Font("MV Boli", Font.BOLD, 12));
        button2.setMnemonic(KeyEvent.VK_W);
        button2.setFocusable(false);
        button2.setText("W");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                players.get(0).setAnswer(answer[1].getText());
                try {
                    disableButtons(1);
                    checkAnswer(0);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }

            }
        });

        button3.setBounds(0, 300, 45, 80);
        button3.setFont(new Font("MV Boli", Font.BOLD, 15));
        button3.setMnemonic(KeyEvent.VK_E);
        button3.setFocusable(false);
        button3.setText("E");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                players.get(0).setAnswer(answer[2].getText());
                try {
                    disableButtons(1);
                    checkAnswer(0);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }

            }
        });

        button4.setBounds(0, 400, 45, 80);
        button4.setFont(new Font("MV Boli", Font.BOLD, 15));
        button4.setMnemonic(KeyEvent.VK_R);
        button4.setFocusable(false);
        button4.setText("R");
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                players.get(0).setAnswer(answer[3].getText());
                try {
                    disableButtons(1);
                    checkAnswer(0);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }


            }
        });

        time_label.setBounds(0,500,100,25);
        time_label.setBackground(new Color(50,50,50));
        time_label.setForeground(new Color(255,0,0));
        time_label.setFont(new Font("MV Boli",Font.PLAIN,16));
        time_label.setHorizontalAlignment(JTextField.CENTER);
        time_label.setText("timer 00:00");

        int x = 0;////////////////////////////////////////////////////////
        for(int i = 0; i<4; i++){
            answer[i].setBounds(125,100+x,800,100);
            answer[i].setBackground(new Color(50,50,50));
            answer[i].setForeground(new Color(55,55,55));
            answer[i].setFont(new Font("MV Boli",Font.PLAIN,25));
            answer[i].setText("Test");
            x += 100;
            frame.add(answer[i]);
        }

        frame.add(gameTypeLabel);
        frame.add(questionLabel);
        frame.add(playerTagLeft);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(time_label);
        time_label.setVisible(false);

    }

    /**
     * initializes components for a game of two players
     */
    private void twoPlayersFrame(){

        button5 = new JButton();
        button6 = new JButton();
        button7 = new JButton();
        button8 = new JButton();

        //player2NameLabel = new JLabel();


        playerTagRight = new JLabel();



//players.get(1).getPlayerName());


        //player2NameLabel.setBounds(0, 550, 400, 25);
        //player2NameLabel.setFont(new Font("MV Boli", Font.BOLD, 15));

        button5.setBounds(50, 100, 45, 80);
        button5.setFont(new Font("MV Boli", Font.BOLD, 15));
        button5.setMnemonic(KeyEvent.VK_U);
        button5.setFocusable(false);
        button5.setText("U");
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                players.get(1).setAnswer(answer[0].getText());
                try {
                    disableButtons(2);
                    checkAnswer(1);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }

            }
        });

        button6.setBounds(50, 200, 45, 80);
        button6.setFont(new Font("MV Boli", Font.BOLD, 15));
        button6.setMnemonic(KeyEvent.VK_I);
        button6.setFocusable(false);
        button6.setText("I");
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                players.get(1).setAnswer(answer[1].getText());
                try {
                    disableButtons(2);
                    checkAnswer(1);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }

            }
        });

        button7.setBounds(50, 300, 45, 80);
        button7.setFont(new Font("MV Boli", Font.BOLD, 15));
        button7.setMnemonic(KeyEvent.VK_O);
        button7.setFocusable(false);
        button7.setText("O");
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                players.get(1).setAnswer(answer[2].getText());
                try {
                    disableButtons(2);
                    checkAnswer(1);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }

            }
        });

        button8.setBounds(50, 400, 45, 80);
        button8.setFont(new Font("MV Boli", Font.BOLD, 15));
        button8.setMnemonic(KeyEvent.VK_P);
        button8.setFocusable(false);
        button8.setText("P");
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                players.get(1).setAnswer(answer[3].getText());
                try {
                    disableButtons(2);
                    checkAnswer(1);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }

            }
        });

        frame.add(button5);
        frame.add(button6);
        frame.add(button7);
        frame.add(button8);
        // frame.add(player2NameLabel);
        frame.add(playerTagRight);
        frame.add(backgroundImage1);
        //playerNameLabel.setVisible(true);
        //player2NameLabel.setVisible(true);
        //backgroundImage2.setVisible(false);
        //betButton.setVisible(false);
        //time_label.setVisible(false);
        //betTypeLabel.setVisible(false);


    }
    public void disableButtons(int whoAnswered){
        if(whoAnswered == 1){
            button1.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);

        }else{
            button5.setEnabled(false);
            button6.setEnabled(false);
            button7.setEnabled(false);
            button8.setEnabled(false);

        }

    }

    public void enableButtons(int player){
        if(player == 1){
            button1.setEnabled(true);
            button2.setEnabled(true);
            button3.setEnabled(true);
            button4.setEnabled(true);

        }else{
            button5.setEnabled(true);
            button6.setEnabled(true);
            button7.setEnabled(true);
            button8.setEnabled(true);

        }

    }


    /**
     * checks if player's buttons are enabled
     * @return true if somebody has not answered
     *         false if both have answered
     */
    public boolean buttonsAreEnabled(){
        return button1.isEnabled() || button5.isEnabled();
    }

    /**
     * Shows points for each player
     */
    public void showPoints(){
        playerTagLeft.setText("Player : "+players.get(0).getPlayerName()+", Points : "+ players.get(0).getPoints());
        if(players.size() == 2){
            playerTagRight.setText("Player : "+players.get(1).getPlayerName()+", Points : "+ players.get(1).getPoints());
        }


    }

    /**
     * Checks if a player answered correctly also checks the type of game to add points accordingly
     * @param player
     * @throws InterruptedException No need for error handling if the player  follows the game's flow
     */

    public void checkAnswer(int player) throws InterruptedException {
        if(gameType.equals("Answer Correct")){
            if(players.get(player).getAnswer().equals(game.currentQuestion().getAnswer())){
                players.get(player).addPoints(1000);
            }

            if(players.size() == 2 && playersHaveAnswered()){
                showPoints();
                roundIs++;
                updateFrame("Update Question");
                enableButtons(1);enableButtons(2);
            }

            if(players.size()==1){
                showPoints();
                roundIs++;
                updateFrame("Update Question");
                enableButtons(1);
            }
        }else if(gameType.equals("Stop The Clock")){


        }
        else if (gameType.equals("Bet")){
            if(players.get(player).getAnswer().equals(game.currentQuestion().getAnswer())){
                players.get(player).addPoints(players.get(player).getBet());
            }else{
                players.get(player).removePoints(players.get(player).getBet());
            }

            if(players.size() == 2 && playersHaveAnswered()){
                showPoints();
                roundIs++;
                frame.setVisible(false);
                playerToBet(1);
                betFrame.setVisible(true);
                updateFrame("Update Question");
                enableButtons(1);enableButtons(2);
            }

            if(players.size()==1){
                showPoints();
                roundIs++;
                frame.setVisible(false);
                betFrame.setVisible(true);
                updateFrame("Update Question");
                enableButtons(1);
            }
        }

    }

    /**
    Checks if Player buttons are enabled so that it can inform an other method to load the next question
     */
    public boolean playersHaveAnswered(){
        return !buttonsAreEnabled();
    }


    /**
     * Starts the basic frame
     */
    public void start(){
        setVisible(true);
    }

    //updates starting frame to player number choice and then starts the game's frame

    /**
     * updates frame with components to add players
     */
    public void newGame(){
        updateFrame("Add Players");

    }

    /**
     * Different types of components are added to the frame depending on whichFrame
     * @param whichFrame Has 3 different strings that manipulate a frame's components accordingly and/or add different frames to the interface
     */
    public void updateFrame(String whichFrame) {
        if(whichFrame.equals("Add Players")){
            remove(newGameButton);remove(highScoreButton);remove(quitButton);remove(backgroundImage);
            add(numOfPlayersLabel);

            //add(topPanel, BorderLayout.PAGE_START);
            //topPanel.add(numOfPlayersLabel);
            for(int i = 0; i<maxNumOfPlayers; i++){
                add(playerButton[i]);
            }
            add(backgroundImage1);
        }
        if(whichFrame.equals("Add Names")){
            numOfPlayersLabel.setText("Choose Nickname");
            for(int i =0; i<maxNumOfPlayers; i++){
                remove(playerButton[i]);
            }
            remove(backgroundImage1);

            add(nameLabel);
            add(playerName);
            add(readyButton);
            add(backgroundImage2);


        }
        if(whichFrame.equals("Update Question")){
            if(roundIs<=5) {
                try {
                    game.updateQuestion();
                    questionLabel.setText(game.currentQuestion().getQuestion());
                    for (int i = 0; i < 4; i++) {
                        answer[i].setText(game.currentQuestion().getChoices().get(i));
                    }
                    gameTypeLabel.setText(game.getCategory());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                int player1Points = players.get(0).getPoints();
                if(players.size()==2){
                    int player2Points = players.get(1).getPoints();
                    JOptionPane.showMessageDialog(messageFrame,players.get(1).getPlayerName()+" has " + player2Points+ " points");
                }
                JOptionPane.showMessageDialog(messageFrame,players.get(0).getPlayerName()+" has " + player1Points+ " points");
                roundIs=1;
                frame.setVisible(false);
                if(gameType.equals("Bet")){
                    betFrame.setVisible(false);
                    setVisible(true);
                }

                try {
                    gameOver();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        revalidate();
    }

    /**
     * Updates basic frame's components with game type buttons
     */
    private void selectGameType() {

        readyButton.setVisible(false);
        nameLabel.setVisible(false);
        playerName.setVisible(false);

        gameType1Button.setVisible(true);
        gameType2Button.setVisible(true);
        gameType3Button.setVisible(true);
        revalidate();

    }




    public void highScoreScreen(){
        //logic for high score
    }



    /**
     * Updates the name of each player and their tags
     * @param nextPlayer Shows which player is to be named
     */
    public void playerToName(int nextPlayer) {
        if (nextPlayer <= playersToPlay) {
            nameLabel.setText("Player " + nextPlayer + " enter your nickname");
            nameLabel.setForeground(Color.RED);
        } else {
            if(playersToPlay == 1){
                playerTagLeft.setBounds(50, 440, 100, 50);
                //frame.setVisible(true);

            }else{
                twoPlayersFrame();
                playerTagRight.setBounds(0, 600, 400, 50);
                playerTagRight.setFont(new Font("Arial", Font.ITALIC, 12));
                playerTagRight.setText(players.get(1).getPlayerName());
            }
            playerTagLeft.setBounds(0, 500, 400, 50);
            playerTagLeft.setFont(new Font("Arial", Font.ITALIC, 12));
            playerTagLeft.setText(players.get(0).getPlayerName());
            selectGameType();


        }
    }

    /**
     * Sets the bet for the players
     * @param nextPlayer Shows which player bets next
     */
    public void playerToBet(int nextPlayer){
        if(nextPlayer <= playersToPlay){
            numOfPlayersLabel.setText(players.get(nextPlayer-1).getPlayerName()+"'s bet");
            betFrame.setVisible(true);
            if(nextPlayer == playersToPlay){
                playersHaveBet = true;
            }
        }
    }

    private void gameOver() throws IOException {
        if(!gameType2Button.isEnabled() && !gameType1Button.isEnabled()  ) {
            String message;
            String printMessage;
            String name;
            if (players.size() > 1) {
                if (players.get(0).getPoints() > players.get(1).getPoints()) {
                    name = players.get(0).getPlayerName();
                    message = name + " has won with " + players.get(0).getPoints() + " points";
                    printMessage = name + ":"+ players.get(0).getPoints();

                } else {
                    name = players.get(1).getPlayerName();
                    message = name + " has won with " + players.get(1).getPoints() + " points";
                    printMessage = name + ":"+ players.get(1).getPoints();
                    // JOptionPane.showMessageDialog(messageFrame, players.get(1).getPlayerName() + " has won with "+ players.get(1).getPoints()+ " points");
                }

            } else {
                name = players.get(0).getPlayerName();
                message = name + " has collected  " + players.get(0).getPoints() + " points";
                printMessage = name + " :" + players.get(0).getPoints();
                //JOptionPane.showMessageDialog(messageFrame, players.get(0).getPlayerName() + " has collected  "+ players.get(0).getPoints()+ " points");
            }
            JOptionPane.showMessageDialog(messageFrame, message);

            try (FileWriter fw = new FileWriter("Records/records.txt", true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                FileReader fr = new FileReader("Records/records.txt");
                BufferedReader br = new BufferedReader(fr);
                StringBuffer sb = new StringBuffer();

                //now read the file line by line...
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.contains(name)) {
                        String newScore = printMessage.substring(printMessage.lastIndexOf(":") + 1);
                        String oldScore = line.substring(line.lastIndexOf(":") + 1);
                        System.out.println(newScore);
                        int a = Integer.parseInt(newScore);
                        int b = Integer.parseInt(oldScore);
                        if (a > b) {
                            String newContent = line.replaceAll(oldScore, newScore);
                            fw.write(newContent);

                        }
                    }
                }

                fr.close();
                System.out.println("Contents : ");
                System.out.println(sb.toString());


            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            System.exit(1);
        }
/**
            String str = "GAME FINISHED, total points gathered for player: " + game.getCurrentPlayer().getPlayerName() + " ,points: " + game.getCurrentPlayer().getPoints();
            FileOutputStream outputStream = new FileOutputStream(myObj);
            byte[] strToBytes = str.getBytes();
            outputStream.write(strToBytes);

            outputStream.close();

*/


    }


}
