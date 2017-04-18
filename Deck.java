import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck;
    private ArrayList<Card> discard;

    Deck(int deckNum)
    {
        deck = new ArrayList<Card>();
        discard = new ArrayList<Card>();

        int jokerSuit = 4;
        int jokerRank = 13;

        /*String bPath = "card back red.png";
        URL bURL = cldr.getResource(bPath);
        ImageIcon bImg = new ImageIcon(bURL);*/
        
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
                    
                    String fPath = Card.RANKS[rank] + Card.SUITS[deckNum] + ".png";
                    URL fURL = cldr.getResource(fPath);
                    ImageIcon front = new ImageIcon(fURL);
                    
                    deck.add(new Card(suit, rank, front, i));
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
            
            discard.add(deck.get(0));
            deck.remove(0);
            
            //ADD DISCLAIMER THAT WE'RE SHUFFLING THE DECK
        }
        
        Card ret = deck.get(0);
        deck.remove(0);
        return ret;
    }
    
    public Card takeDiscard()
    {
        Card ret = discard.get(0);
        discard.remove(0);
        return ret;
    }

    public void Deal(int hand, ArrayList<Player> players)
    {
        for(Player x : players)
        {
            int i = 0;
            while(i < hand)
            {
                x.hand.add(takeCard());
                i++;
            }
        }
        
        Discard(takeCard());
    }

    public void Discard(Card card)
    {
        discard.add(0, card);
    }

    public String toString()
    {
        return deck.toString();
    }
    
    public String endRound(){
        deck.addAll(discard);
        shuffle();
    }
}
