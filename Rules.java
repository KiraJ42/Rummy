import java.util.ArrayList;

public abstract class Rules 
{
    ArrayList<Player> players;
    int numCards;
    int current;
    Deck deck;
    
    public abstract boolean victoryCheck();
    
    public abstract void deal();
    
    //tabulate scores and empty hands for a new round
    public abstract void endRound();
    
    //increases current
    public abstract boolean nextTurn();
    
    Rules(int numP, String n)
    {
        players.add(new Player(n));
        
        for(int i = 1; i < numP; i++)
        {
            players.add(new Player("Player " + i));
        }
        
        current = 0;
    }
    
    public int getPlayerScore(int i)
    {
        return players.get(i).getTotalScore();
    }
    
  
}
