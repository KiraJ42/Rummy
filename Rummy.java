import java.util*

public class Rummy extends Rules
{ 
    Rummy(int numP, String n)
    {
        super(numP, n);
        
        deck = new Deck(1);
        
    }
    public boolean victoryCheck()
    {
        for(Player p : players)
        {
            if(p.getScore() >= 500)
              return true;
        }
        
        return false;
    }
    
    public void deal()
    {
        deck.Deal(7, players);  
    }
    
    //tabulate scores and empty hands for a new round
    public void endRound()
    {
        for(Player p : players)
        {
            p.addTotalScore(p.getLaysScore() - p.getHandScore());
            p.clear(deck);
        }    
    }
    
    //increases current
    public abstract void nextTurn();
    
}
