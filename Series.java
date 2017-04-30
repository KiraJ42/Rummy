import java.util.*;

public class Series extends Lays {
    //CAN ONLY BE USED AFTER AN ISVALID CHECK OF C

    Series(ArrayList<Card> cards)
    {
        lay = cards;
        score = 0;
        
        for(Card c : lay)
        {
            score += c.getValue();
        }
    }
  
    public static boolean isValid(ArrayList<Card> cards)
    {
        String suit;
        int next = 1 + cards.get(0).getIntRank();
      
        if(cards.size() < 3)
            return false;
        
        suit = cards.get(0).getSuit();
        
        Collections.sort(cards, new Comparator<Card>(){
                        public int compare(Card a, Card b){
                            Integer A = a.getIntRank();
                            Integer B = b.getIntRank();
                            return A.compareTo(B);
                        }
        });
      
        for(int i = 1; i < cards.size(); i++)
        {
            if(next != cards.get(i).getIntRank() || !suit.equals(cards.get(i).getSuit()))
                return false;
          
            next = 1 + cards.get(i).getIntRank();
        }
      
        return true;
    }

    @Override
    public String toString() {
        String print = "";
        String printSuit;
        String suit = lay.get(0).getSuit();
        if(suit.equals("Hearts"))
            printSuit = "\u2665";
        else if(suit.equals("Diamonds"))
            printSuit = "\u2666";
        else if(suit.equals("Spades"))
            printSuit = "\u2660";
        else
            printSuit = "\u2660";

        for( Card x : lay){
            print = x.getRank() + printSuit + ", ";
        }
        return print;
    }
}
