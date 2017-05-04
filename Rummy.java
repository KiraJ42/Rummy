import java.util.*;

public class Rummy extends Rules
{

    public Rummy(Game g) {
        super(g);
    }


    public boolean victoryCheck()
    {
        for(Player p : players)
        {
            if(p.getTotalScore() >= 500)
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
                
        Player.allLays.clear();
        
        
        deck.discardClear();
    }
    
    //increases current
    public int playRound()
    {
        boolean winner = false;
        int i = 0;
        
        //deal();
        
        while(!winner)
        {
            for(i = 0; i < players.size() && !winner; i++)
            {                                            
                if(Player.turns == 0)
                {
                    winner = players.get(0).TakeTurn();
                    players.get(0).isTurn = false;
                }
                
                else{
                    winner = ((Computer)players.get(i)).TakeTurn(deck);
                    game.updateDiscard();
                }
                
                if(Player.turns == players.size())
                    Player.turns = 0;
                 
            }
            

        }
        
        endRound();
        return i;
    }


    
}
