public class Player {
    private String playerName;
    private int points;
    private int answer;

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

    public int getAnswer(){
        return answer;
    }

    public void setAnswer(int answer){
        this.answer = answer;
    }
}
