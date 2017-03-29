import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck;
    private ArrayList<Card> discard;

    Deck()
    {
        deck = new ArrayList<Card>();
        discard = new ArrayList<Card>();

        //adding Jokers
        int jokerSuit = 4;
        int jokerRank = 13;

        deck.add(new Card(jokerSuit, jokerRank));
        deck.add(new Card(jokerSuit, jokerRank));

        for(int suit = 0; suit < 4; suit++)
        {
            for(int rank = 0; rank < 13; rank++)
            {
                deck.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle()
    {
        System.out.println("Shuffling Deck...");
        Collections.shuffle(deck);
    }

    public Card takeCard()
    {
        Card ret = deck.get(0);
        deck.remove(0);
        return ret;
    }

    public void Discard(Card card)
    {
        discard.add(card);
    }

    public String toString()
    {
        return deck.toString();
    }
}
