import java.util.ArrayList;

public class Player {

    protected String name;
    public ArrayList<Card> hand = new ArrayList<Card>();

    @Override
    public String toString() {
        return name + "\n" + "\t" + hand.toString();
    }
}
