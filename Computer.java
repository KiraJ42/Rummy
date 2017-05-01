import java.util.ArrayList;

public class Computer extends Player{
    
    ArrayList<ArrayList<Card>> runs = new  ArrayList<ArrayList<Card>>;
    ArrayList<ArrayList<Card>> sets = new  ArrayList<ArrayList<Card>>;

    Computer(int i)
    {
        name = "Player " + i;
        totalScore = 0;
        details = new PlayerInfo(this);
        sets.ensureCapacity(14);
        runs.ensureCapacity(4);
        
        //set all the run cards to jokers
        for(ArrayList<Card> a : runs)
        {
            for(int i = 0; i < 14; i++)
            {
                a.add(new Card(4, 13, 14));
            }
        }
    }
    
    
    public void TakeTurn(deck d)
    {
        Card discard = d.getDiscardCard();
        bool getDiscard = false;
        
        OrderHands();
        
        for(Lay l : allLays)
        {
            if(l.addCard(discard))
            {
               addCard(d.takeDiscard());
               getDiscard = true;
            }
        }
        
        if(!getDiscard)
        {
            if(makesSet(discard))
            {
               addCard(d.takeDiscard());
               getDiscard = true;
            }
            
            else if(makesRun(discard))
            {
               addCard(d.takeDiscard());
               getDiscard = true;
            }
        }
        
        if(!getDiscard)
            hand.add(d.takeCard());
        
        for(Card c : hand)
        {
             
        }
            
    }
    
    public void addCard(Card c)
    {
        hand.add(c);
        sets[c.getIntRank].add(c);
        runs[c.getIntSuit].set([c.getIntRank], c);
    }
    
    public void play(Card c)
    {
        totalScore += c.getValue();
        hand.remove(c);
        
        
    }
    
    public boolean makesSet(Card c)
    {   
        if(sets.[c.getIntRank].size() + 1 >= 3)
            return true;
        
        return false     
    }
    
    public boolean makesRun(Card c)
    {   
        boolean valid = false;
        if(c.getIntRank >= 2)
            valid = runs[c.getIntSuit][c.getIntRank - 1].getIntRank != 14;
        
        if(c.getIntRank >= 3 && !valid)
            valid = runs[c.getIntSuit][c.getIntRank - 2].getIntRank != 14;
        
        if(c.getIntRank <= 12 && !valid)
            valid = runs[c.getIntSuit][c.getIntRank + 1].getIntRank != 14;
        
        if(c.getIntRank <= 11 && !valid)
            valid = runs[c.getIntSuit][c.getIntRank + 2].getIntRank != 14;
        
        return valid;     
    }
    
}
