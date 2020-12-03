import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private ArrayList<Question> questionSet;

    public Game(){
        questionSet = new ArrayList<Question>();
        String question1 = "A = ?";
        String[] choices1 = {"A","B","C","D"};
        questionSet.add(new Question(question1,"A",choices1));
        String question2 = "Why are you geh?";
        String[] choices2 = {"cause why not","who says im geh","shut up!","noooo"};
        questionSet.add(new Question(question2,"who says im geh",choices2));

    }

    public void start(){
        Scanner scanner = new Scanner(System.in);

        for(int i=0; i<questionSet.size(); i++){
            System.out.println(questionSet.get(i).getQuestion());
            int numOfChoices = questionSet.get(i).getChoices().size();

            for(int j=0; j<numOfChoices; j++){
                System.out.println((j+1) +" : "+questionSet.get(i).getChoices().get(j));

            }
            int playerAnswer = scanner.nextInt();
            String correctAnswer = questionSet.get(i).getAnswer();
            //int correctAnswerNumber = questionSet.get(i).getChoices()
            //System.out.println("Correct answer is : "+ +correctAnswer);
        }
    }
}
