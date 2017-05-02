import java.util.*;

public abstract class Lays{

    ArrayList<Card> lay;
    int score;

    public static boolean isValid(){return true;}

    public int getScore(){return score;}
    public static ArrayList<Card> getCards(){return new ArrayList<Card>();}
    public abstract boolean addCard(Card c);
}
