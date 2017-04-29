import java.util.*;

public class Series extends Lays()
{
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
        String suit
        int next = 1 + cards.get(0).getIntRank();
      
        if(cards.size() < 3)
            return false;
        
        suit = cards.get(0).getSuit();
        
        Collections.sort(cards, new Comparator<Card>(){
                        public int compare(Card a, Card b){
                                        return compare(a.getIntRank(), b.getIntRank());
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
    
}
