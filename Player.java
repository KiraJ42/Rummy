import java.util.ArrayList;

public class Player {

    protected String name;
    protected int totalScore;
    
    public ArrayList<Card> hand = new ArrayList<Card>();
    public ArrayList<Card> checkLay = new ArrayList<Card>();
    public ArrayList<Lays> lays = new ArrayList<Lays>();
    
    Player(String n){
        name = n;
        totalScore = 0;
    }
    
    //takes in an int and adds it to the player's total score
    public int addTotalScore(int i)
    {
        totalScore += i;
    }
    
    public int getTotalScore(){return totalScore;}
    
    public int getLaysScore()
    {
        int score = 0;
        
        for(Lays l : lays)
        {
            score += l.getScore(); 
        }
        
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
