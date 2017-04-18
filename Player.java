import java.util.ArrayList;

public class Player {

    protected String name;
    protected int score;
    
    public ArrayList<Card> hand = new ArrayList<Card>();

    Player(String n){
        this.name = n;
        score = 0;
    }
    
    //takes in an int and adds it to the player's total score
    public int addScore(int i)
    {
        score += i;
    }
    
    @Override
    public String toString() {
        return name + "\n" + "\t" + hand.toString();
    }
}
