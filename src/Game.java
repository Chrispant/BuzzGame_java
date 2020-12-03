import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Game {
    private ArrayList<Question> questionSet;
    private int counter = 0;

    public Game(){
        questionSet = new ArrayList<Question>();
        String question1 = "A = ?";
        String[] choices1 = {"A","B","C","D"};
        questionSet.add(new Question(question1,"A",choices1));
        String question2 = "Why are you geh?";
        String[] choices2 = {"cause why not","who says im geh","shut up!","noooo"};
        questionSet.add(new Question(question2,"who says im geh",choices2));
        Collections.shuffle(questionSet);



    }

    public void start(){
        Scanner scanner = new Scanner(System.in);
        counter++;

        System.out.println("Question "+counter+" : "+questionSet.get(0).getQuestion());
        int numOfChoices = questionSet.get(0).getChoices().size();

        for(int i=0; i<numOfChoices; i++){
            System.out.println((i+1) +" : "+questionSet.get(0).getChoices().get(i));

        }
        System.out.println("To Choose an answer type 1-4 depending on your choice and hit enter");
        int playerAnswer = scanner.nextInt();
        ArrayList<String> choices = questionSet.get(0).getChoices();
        String correctAnswer = questionSet.get(0).getAnswer();
        int correctAnswerNumber = choices.indexOf(correctAnswer) + 1;
        System.out.println("Correct answer is \n"+correctAnswerNumber+" : "+correctAnswer);

    }

}
