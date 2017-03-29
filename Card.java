/**
 * Created by Owner on 3/28/2017.
 */
public class Card {

    //card ranks and
    public static final String[] RANKS = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Joker"};
    public static final String[] SUITS = {"Hearts", "Diamonds", "Spades", "Clubs", "Joker"};

    public String suit;
    public String rank;

    Card(int suit, int rank)
    {
        this.rank = RANKS[rank];
        this.suit = SUITS[suit];
    }

    public String toString()
    {
        if(suit.equals("Joker") && rank.equals("Joker"))
            return "Joker";
        return rank + " of " + suit;
    }

    public String getRank()
    {
        return rank;
    }

    public String getSuit()
    {
        return suit;
    }
}

