import java.util.ArrayList;

public class Round {
    private ArrayList<String> roundTypes;

    public Round(){
        roundTypes = new ArrayList<String>();
    }

    public void addRoundType(String type){
        roundTypes.add(type);
    }

    public ArrayList<String> getRoundTypes() {
        return roundTypes;
    }
}
