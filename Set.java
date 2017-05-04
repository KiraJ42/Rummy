import java.util.*;

public class Set extends Lays{
    //CAN ONLY BE USED AFTER AN ISVALID CHECK OF C

    Set(ArrayList<Card> cards)
    {
        lay = new ArrayList<>();
        lay.addAll(cards);
        score = 0;
        
        for(Card c : lay)
        {
            score += c.getValue();
        }
    }
  
    public static boolean isValid(ArrayList<Card> cards)
    {
        String rank;
      
        if(cards.size() < 3)
            return false;
        
        rank = cards.get(0).getRank();
        
        for(Card c : cards)
        {
            if(!rank.equals(c.getRank()))   
              return false;
        }
      
        return true;
    }

    @Override
    public boolean addCard(Card c)
    {
        boolean valid = false;
        lay.add(c);
        
        if(isValid(lay))
            valid = true;
   
        
        lay.remove(lay.size() - 1);
        return valid;      
    }
    
    @Override
    public String toString() {
        Card c = lay.get(0);
        return  c.getRank() + ", ";
    }
}
