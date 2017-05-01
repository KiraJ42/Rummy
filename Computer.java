import java.util.ArrayList;

public class Computer extends Player{

    Computer(int i)
    {
        name = "Player " + i;
        totalScore = 0;
        details = new PlayerInfo(this);
    }
    
    public void TakeTurn(deck d)
    {
        Card discard = d.getDiscardCard();
        bool getDiscard = false;
        
        for(Lay l : allLays)
        {
            if(l.addCard(discard))
            {
               hand.add(d.takeDiscard());
               getDiscard = true;
            }
        }
        
        if(!getDiscard)
        {
            for(Card c : hand)
            {
                //insert crying here
            }
        }
        
        if(!getDiscard)
            hend.add(d.takeCard());
        
        for(Card c : hand)
        {
             
        }
            
    }
    
    
    
}
