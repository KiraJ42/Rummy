import java.util.*;

public class Series extends Lays {
    //CAN ONLY BE USED AFTER AN ISVALID CHECK OF C

    Series(ArrayList<Card> cards)
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
    public boolean addCard(Card c)
    {
        //boolean valid = false;
        lay.add(c);
        
        if(isValid(lay))
            return true;
   
        
        lay.remove(c);
        return false;      
    }

    @Override
    public String toString() {
        String print = "";
        
        for( Card x : lay){
            print = print + x.getRank() + ", ";
        }
        return print;
    }
}
