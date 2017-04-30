import java.util.ArrayList;

public class Player {

    public String name;
    protected int totalScore;
    protected boolean isTurn;

    public PlayerInfo details;
    
    public ArrayList<Card> hand = new ArrayList<Card>();
    public ArrayList<Card> checkLay = new ArrayList<Card>();
    public ArrayList<Lays> lays = new ArrayList<Lays>();
    public static ArrayList<Lays> allLays = new ArrayList<Lays>();
    public ArrayList<Lays> ScoredLays = new ArrayList<Lays>();
    
    Player(String n){
        name = n;
        totalScore = 0;
        details = new PlayerInfo(this);
    }
    
    //takes in an int and adds it to the player's total score
    public int addTotalScore(int i)
    {
        totalScore += i;
        return totalScore;
    }
    
    public int getTotalScore(){


        totalScore = totalScore + getLaysScore();
        return totalScore;
    }
    
    public int getLaysScore()
    {
        int score = 0;
        
        for(Lays l : lays)
        {
            score += l.getScore();
            ScoredLays.add(l);
        }

        lays.clear();
        return score;
    }
    public int getHandScore()
    {
        int score = 0;
        
        for(Card c : hand)
        {
            score -= c.getValue();
        }
        
        return score;
    }
    
    public void clear(Deck deck)
    {
        for(Card c : hand)
        {
            deck.Discard(c);
        }
        hand.clear();
        
        for(Lays l : lays)
        {
            for(Card c : l.getCards())
            {
                    deck.Discard(c);
            }
        }
        lays.clear();
    }

    
    @Override
    public String toString() {
        return name + "\n" + "\t" + hand.toString();
    }
}
