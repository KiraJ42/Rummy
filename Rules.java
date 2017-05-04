import java.util.ArrayList;

public abstract class Rules 
{
    ArrayList<Player> players;
    int numCards;
    //int current;
    Deck deck;
    Game game;
    public abstract boolean victoryCheck();
    
    public abstract void deal();
    
    //tabulate scores and empty hands for a new round
    public abstract void endRound();
    
    //increases current
    public abstract int playRound();
    
    Rules(Game g)
    {
        players = g.players;
        deck = g.d;
        game = g;
        //current = 0;
    }
    
    public int getPlayerScore(int i)
    {
        return players.get(i).getTotalScore();
    }
    
  
}
