import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import javax.swing.JOptionPane;

public class Computer extends Player{
    
    ArrayList<ArrayList<Card>> runs = new  ArrayList<ArrayList<Card>>();
    ArrayList<ArrayList<Card>> sets = new  ArrayList<ArrayList<Card>>();
    ArrayList<ArrayList<Card>> playLays = new  ArrayList<ArrayList<Card>>();
    ArrayList<Card> playedCards = new ArrayList<Card>();
    
    //ArrayList<ArrayList<Card>> playedLays = new  ArrayList<ArrayList<Card>>();
    //discard priority queue, sorts based on a cards value and whether it can be included in 
    //a set and or a run
    PriorityQueue<Card> disPrio = new PriorityQueue<Card>(7, new Comparator<Card>(){
                        public int compare(Card a, Card b){
                            Integer A = a.getValue();
                            Integer B = b.getValue();
                            if(makesSet(a, 3))
                               A +=20;
                            if(makesRun(a))
                               A +=20;
                            if(makesSet(b, 3))
                               B +=20;
                            if(makesRun(b))
                               B +=20;
                            
                            return A.compareTo(B);
                        }
        });
    
    Computer(int i, Game g)
    {
        super(("Player " + i), g);
        
        
        
        for(int j = 0; j < 14; j++)
        {
            if(j < 4)
                runs.add(new ArrayList<Card>(14));
            sets.add(new ArrayList<Card>(4));
        }
        
        
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
       
        
        //Section 1: choosing what to draw from: discard or deck
        for(Lays l : allLays)
        {
            if(l.addCard(discard))
            {
               addCard(d.takeDiscard());
               play(discard);
               ArrayList<Card> temmp = new ArrayList<Card>();
               temmp.add(discard);
               
               if(l instanceof Set)
                lays.add(new Set(temmp));
               
               else if(l instanceof Series)
                lays.add(new Series(temmp));
               
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
        
        //Section 2: finding all the lays in the computer's hand
        
        
        Collections.sort(hand, new Comparator<Card>(){
                        public int compare(Card a, Card b){
                            Integer A = a.getIntRank() * -1;
                            Integer B = b.getIntRank() * -1;
                            return A.compareTo(B);
                        }
        });
        
        
        for(Card c: hand)
        {
            System.out.print(c + " ");
        }
        
        System.out.println();
        
        sets.forEach((a) -> {
            System.out.print(sets.indexOf(a) + ": ");
            a.forEach((c) ->{              
                   System.out.print(c + " ");
            });
            System.out.println();
        });
        
        int sumLay;
        int sumSets;
        boolean played = false;
        Loop: for(Card c : hand)
        {
           played = false;
           lay.addAll(longestRun(c));
           sumLay = 0;
           sumSets = 0;
           
           if(playedCards.contains(c))
               continue Loop;
           
           
           
           
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
                playLays.add(new ArrayList<Card>(lay));
                lays.add(new Set(lay));
                for(Card j : lay)
                    playedCards.add(j);
                //allLays.add(new Set(lay));
                details.updateScore(this);
           }
           
           else if( !setWon && Series.isValid(lay))
           {
               playLays.add(new ArrayList<Card>(lay));
               lays.add(new Series(lay));
               //allLays.add(new Series(lay));
               for(Card j : lay)
                    playedCards.add(j);
               details.updateScore(this);

           }
            
           setWon = false;
           lay.clear();
           
           
        }
        
        playLays.forEach((a) -> {    
            a.forEach((x) -> 
            {   
                play(x);
            });
        });
        
        playLays.clear();
        
         
        //Section 3: choosing what to discard by taking the top card from the discard priority Q
        
        disPrio.clear();
        hand.forEach((c)-> {
            disPrio.add(c);
        });
        discard = disPrio.poll();
        
        if(discard != null)
        {
            d.Discard(discard);
            play(discard);
        }
        
        Player.turns++;
        
        
        
        if(hand.isEmpty())
        {
            JOptionPane.showMessageDialog(null, name + " has won! Please play again~~~");
            System.exit(0);
        }
        
        return hand.isEmpty();
    }
    
    public void addCard(Card c)
    {
        hand.add(c);
        sets.get(c.getIntRank()).add(c);
        runs.get(c.getIntSuit()).set(c.getIntRank(), c);
        //disPrio.add(c);
    }
    
    public Card play(Card c)
    {
        //totalScore += c.getValue();
        hand.remove(c);
        sets.get(c.getIntRank()).remove(c);
        runs.get(c.getIntSuit()).set(c.getIntRank(), new Card(4, 13, 14));
        //disPrio.remove(c);
        return c;       
    }
    
    //Will return true if the computer has a set of c.getIntRanks() of size >= i
    public boolean makesSet(Card c, int i)
    {   
        return sets.get(c.getIntRank()).size() + 1 >= i;     
    }
     
    //Will pick up the discard if there are cards in the run up to two values away from the discard
    //i.e. if the discard were a three of spades and i = 2 the computer would pick up the card if it had 
    //any of the following of spades: A,2,4,5
    //the if statements exists to prevent seg faulting, the i exists to make the method usable for checking
    //if the discard should be drawn and checking if a run is valid.
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
