import java.util.ArrayList;

public class Question {
    private String question;
    private String answer;
    private ArrayList<String> choices;

    public Question(String question, String answer, String[] choices) {
        this.question = question;
        this.answer = answer;
        this.choices = new ArrayList<String>();
        {
            for (int i = 0; i < choices.length; i++) {
                this.choices.add(choices[i]);
            }
        }
    }
}
