import java.util.ArrayList;
import java.util.Collections;
import java.net.URL;
import javax.swing.ImageIcon;

public class Deck {
    private ArrayList<Card> deck;
    private ArrayList<Card> discard;

    Deck(int deckNum)
    {
        deck = new ArrayList<Card>();
        discard = new ArrayList<Card>();

        for (int suit = 0; suit < 4; suit++) {
            for (int rank = 0; rank < 13; rank++) {
                 deck.add(new Card(suit, rank, rank+1));
            }
        }
    }

    public void shuffle()
    {
        //System.out.println("Shuffling Deck...");
        Collections.shuffle(deck);
    }

    public Card takeCard()
    {
        if(deck.isEmpty())
        {
            deck.addAll(discard);
            discard.clear();
            
            shuffle();
            
            discard.add(deck.get(deck.size()-1));
            deck.remove(deck.size()-1);
            
            System.out.println("Shuffling deck");
        }
        
        Card ret = deck.get(deck.size()-1);
        deck.remove(deck.size()-1);
        ret.makeImage();
        return ret;
    }
    
    public Card takeDiscard()
    {
        Card ret = discard.get(discard.size()-1);
        discard.remove(discard.size()-1);
        return ret;
    }

    public void Deal(int hand, ArrayList<Player> players)
    {
        int i = 0;
        while(i < hand){
            for(Player x : players) {
                x.hand.add(takeCard());
            }
            i++;
        }
        
        Discard(takeCard());
    }

    public void Discard(Card card)
    {
        discard.add(card);
    }

    public String toString()
    {
        return deck.toString();
    }
    
    public void endRound(){
        deck.addAll(discard);
        shuffle();
    }

    public ArrayList<Card> getDeck(){
        return deck;
    }

    public ArrayList<Card> getDiscard(){
        return discard;
    }
}
