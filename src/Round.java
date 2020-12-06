/**
 * Class Round represents one round of the game Buzz! It contains only a String property that represents the round type. Possible values: Σωστή Απάντηση-Ποντάρισμα
 */
public class Round {
    private String roundType;

    public Round(String roundType){
        this.roundType = roundType;

    }

    public String getRoundType() {
        return roundType;
    }
}
