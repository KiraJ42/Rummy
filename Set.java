import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Player extends Thread{

    public String name;
    protected int totalScore;
    public boolean isTurn;
    public boolean discarded;
    public boolean drawn;
    public PlayerInfo details;
    public static int turns;
    public static Game g;
    
    public ArrayList<Card> hand = new ArrayList<Card>();
    public ArrayList<Card> checkLay = new ArrayList<Card>();
    public ArrayList<Lays> lays = new ArrayList<Lays>();
    public static ArrayList<Lays> allLays = new ArrayList<Lays>();
    public ArrayList<Lays> ScoredLays = new ArrayList<Lays>();
    
    Player(String n, Game g){
        name = n;
        totalScore = 0;
        details = new PlayerInfo(this);
        isTurn = false;
        discarded = false;
        turns = 0;
        g = g;
    }
    
    //takes in an int and adds it to the player's total score
    public int addTotalScore(int i)
    {
        totalScore += i;
        return totalScore;
    }
    
    public int getTotalScore(){


        totalScore = totalScore + getLaysScore();
        return totalScore;
    }
    
    public int getLaysScore()
    {
        int score = 0;
        
        for(Lays l : lays)
        {
            /*for(Card c : l.lay)
            {
                for(Lays la : ScoredLays)
                {
                    if(!la.lay.contains(c))
                    {
                        score += l.getScore();
                        ScoredLays.add(l);
                        allLays.add(l);
                    }
                }
            }*/
            
            score += l.getScore();
            ScoredLays.add(l);
            
            if(!l.single)
                allLays.add(l);
            
        }

        lays.clear();
        return score;
    }
    public int getHandScore()
    {
        int score = 0;
        
        for(Card c : hand)
        {
            score -= c.getValue();
        }
        
        return score;
    }
    
    public void clear(Deck deck)
    {
        for(Card c : hand)
        {
            deck.Discard(c);
        }
        hand.clear();
        
        for(Lays l : ScoredLays)
        {
            for(Card c : l.getCards())
            {
                    deck.Discard(c);
            }
        }
        lays.clear();
    }

    public boolean TakeTurn()
    {
        isTurn = true;
        discarded = false;
        drawn = false;
      
        //while(turns == 0)
            
        while(!discarded)
        {
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        if(hand.isEmpty())
        {
            
            JOptionPane.showMessageDialog(null, "You have won! Congratulations!");
            System.exit(0);
            
           
        }
            
        return hand.isEmpty();
    }
    
    public void addCard(Card c)
    {
        hand.add(c);
    }
    @Override
    public String toString() {
        return name + "\n" + "\t" + hand.toString();
    }
}
