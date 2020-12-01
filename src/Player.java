public class Player {
    private String playerName;
    private int points;

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
}
