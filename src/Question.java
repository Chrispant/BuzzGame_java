import java.util.ArrayList;
import java.util.Collections;

public class Question {
    private String question;
    private String answer;
    private ArrayList<String> choices;

    public Question(String question, String answer, String[] choices) {
        this.question = question;
        this.answer = answer;
        this.choices = new ArrayList<String>();
            for (int i = 0; i < choices.length; i++) {
                this.choices.add(choices[i]);
            }
        Collections.shuffle(this.choices);
    }

    public String getAnswer() {
        return answer;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setChoices(ArrayList<String> choices) {
        this.choices = choices;
    }
}
