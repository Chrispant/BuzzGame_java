import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;

public class GUI extends JFrame {

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

        textArea.setBounds(0,50,650,50);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(25,25,25));
        textArea.setForeground(new Color(25,255,0));
        textArea.setFont(new Font("MV Boli",Font.BOLD,25));
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setEditable(false);

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
}


