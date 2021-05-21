/**
 * Class Player represents one player of the game Buzz! Ouiz World. It contains the player Name, the total points that he has gained so far and one property that is the answer he provided at a given question.
 */

public class Player {
    private String playerName;
    private int points;
    private String answer;
    private int bet;

    public Player(String playerName, int points){
        this.playerName = playerName;
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void addPoints(int newPoints){
        points += newPoints;
    }

    public void removePoints(int newPoints){
        points = points - newPoints;
    }

    public String getAnswer(){
        return answer;
    }

    public void setAnswer(String answer){
        this.answer = answer;
    }

    public int getBet(){
        return bet;
    }

    public void setBet(int bet){
        this.bet = bet;
    }
}
