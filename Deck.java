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

        int jokerSuit = 4;
        int jokerRank = 13;
        
        /*if(deckNum == 2)
        {
            deck.add(new Card(jokerSuit, jokerRank, 13));
            deck.add(new Card(jokerSuit, jokerRank, 13));
            deck.add(new Card(jokerSuit, jokerRank, 13));
            deck.add(new Card(jokerSuit, jokerRank, 13));
        }*/

        for(int i = 0; i < deckNum; i++) {
            for (int suit = 0; suit < 4; suit++) {
                for (int rank = 0; rank < 13; rank++) {

                    deck.add(new Card(suit, rank, /*front,*/ i));
                }
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
            
            //ADD DISCLAIMER THAT WE'RE SHUFFLING THE DECK
        }
        
        Card ret = deck.get(deck.size()-1);
        deck.remove(deck.size()-1);
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
        for(Player x : players) {
            int i = 0;
            while (i < hand) {
                x.hand.add(takeCard());
                i++;
            }
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
