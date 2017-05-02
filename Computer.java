import java.util.ArrayList;

public class Computer extends Player{
    
    ArrayList<ArrayList<Card>> runs = new  ArrayList<ArrayList<Card>>();
    ArrayList<ArrayList<Card>> sets = new  ArrayList<ArrayList<Card>>();

    Computer(int i)
    {
        super("Player " + i);
        
        sets.ensureCapacity(14);
        runs.ensureCapacity(4);
        
        //set all the run cards to jokers
        runs.forEach((a) -> {
            for(int j = 0; j < 14; j++)
            {
                a.add(new Card(4, 13, 14));
            }
        });
    }
    
    public boolean TakeTurn(Deck d)
    {
        Card discard = d.getDiscardCard();
        boolean getDiscard = false;
        ArrayList<Card> lay = new ArrayList<Card>();
        boolean setWon = false;
        
        for(Lays l : allLays)
        {
            if(l.addCard(discard))
            {
               addCard(d.takeDiscard());
               getDiscard = true;
            }
        }
        
        if(!getDiscard)
        {
            //send in 2 because we want the computer to build sets from 1 card
            if(makesSet(discard, 2))
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
            addCard(d.takeCard());
        
        //need to sort hand greatest to least for this to work correctly
        
        int sumLay;
        int sumSets;
        for(Card c : hand)
        {
           lay.addAll(longestRun(c));
           sumLay = 0;
           sumSets = 0;
           
           
           for(Card x : lay)
           {
               sumLay += x.getValue();
               if(sets.get(x.getIntRank()).size() >= 3)
               {
                   sumSets += x.getValue() * sets.get(x.getIntRank()).size();
               }
           }
           
           if(sumLay < sumSets)
           {
               setWon = true;
               lay.clear();
               
               if(sets.get(c.getIntRank()).size() >= 3)
                   lay.addAll(sets.get(c.getIntRank()));
           }
           
           if(setWon && Set.isValid(lay))
           {          
                lays.add(new Set(lay));
                allLays.add(new Set(lay));
           }
           
           else if( !setWon && Series.isValid(lay))
           {
               lays.add(new Series(lay));
               allLays.add(new Series(lay));
           }
           
           else
               lay.clear();
           
           lay.forEach((x) -> 
           {                    
                play(x);
           });
           
           details.updateScore();
           
           setWon = false;
           lay.clear();
        }
          
        return hand.isEmpty();
    }
    
    public void addCard(Card c)
    {
        hand.add(c);
        sets.get(c.getIntRank()).add(c);
        runs.get(c.getIntSuit()).set(c.getIntRank(), c);
    }
    
    public Card play(Card c)
    {
        //totalScore += c.getValue();
        hand.remove(c);
        sets.get(c.getIntRank()).remove(c);
        runs.get(c.getIntSuit()).set(c.getIntRank(), new Card(4, 13, 14));
        
        return c;       
    }
    
    //Will return true if the computer has a set of c.getIntRanks() of size >= i
    public boolean makesSet(Card c, int i)
    {   
        return sets.get(c.getIntRank()).size() + 1 >= i;     
    }
     
    //Will pick up the discard if there are cards in the run up to two values away from the discard
    //i.e. if the discard were a three of spades the computer would pick up the card if it had 
    //any of the following of spades: A,2,4,5
    public boolean makesRun(Card c)
    {   
        boolean valid = false;

        if(c.getIntRank() >= 2)
            valid = runs.get(c.getIntSuit()).get(c.getIntRank() - 1).getIntRank() != 14;
        
        if(c.getIntRank() >= 3 && !valid)
            valid = runs.get(c.getIntSuit()).get(c.getIntRank() - 2).getIntRank() != 14;
        
        if(c.getIntRank() <= 12 && !valid)
            valid = runs.get(c.getIntSuit()).get(c.getIntRank() + 1).getIntRank() != 14;
        
        if(c.getIntRank() <= 11 && !valid)
            valid = runs.get(c.getIntSuit()).get(c.getIntRank() + 2).getIntRank() != 14;
        
        return valid;     
    }
    
 ArrayList<Card> longestRun( Card c )
    {   
        ArrayList<Card> run = new ArrayList<>();
        ArrayList<Card> suit = runs.get(c.getIntSuit());
        Card temp;
        
        if(c.getIntRank() >= 2)
        {
            temp = suit.get(c.getIntRank() - 1) ;
        
            while(temp.getIntRank() != 14)
            {
                run.add(temp);
                
                if(temp.getIntRank() == 1)
                    break;
                
                temp = suit.get(temp.getIntRank() - 1);
            }
        }
        
        run.add(c);
        
        if(c.getIntRank() <= 12)
        {
            temp = suit.get(c.getIntRank() + 1) ;
        
            while(temp.getIntRank() != 14)
            {
                run.add(temp);
                
                if(temp.getIntRank() == 13)
                    break;
                
                temp = suit.get(temp.getIntRank() + 1);
            }
        }
        return run;
    }
    
}
