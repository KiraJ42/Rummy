/**
 * Created by Owner on 3/28/2017.
 */
public class Card {

    //card ranks and
    public static final String[] RANKS = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Joker"};
    public static final String[] SUITS = {"Hearts", "Diamonds", "Spades", "Clubs", "Joker"};

    public String suit;
    public String rank;
    
    private ImageIcon front;
    private ImageIcon back;
    private int x;
    private int y;
    
    private boolean up;
    
    Card(int suit, int rank, ImageIcon f, ImageIcon b)
    {
        this.rank = RANKS[rank];
        this.suit = SUITS[suit];
        front = f;
        back = b;
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
    
    public void draw(Graphics g, Component c) 
    {
        if(up)
            front.paintIcon(c, g, x, y);
        else
            back.paintIcon(c, g, x, y);
    }
    
    public int getHeight(){return front.getIconHeight();}
    public int getWidth(){return front.getIconWidth();}
    
    public boolean contains(int a, int b)
    {
        return ( a > x && a < (x + getWidth()) && b > y && b < (y + getHeight()));
    }
    
    public int getY(){return y;}     
    public int getX(){return x;}
    
    public void moveTo(int a, int b)
    {
        x = a;
        y = b;
    }

    
}

