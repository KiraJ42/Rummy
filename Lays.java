import java.util*

public abstract class Lays()
{
    ArrayList<Card> lay;
    int score;
    
    public abstract void addLay();
    public abstract void isValid();
    
    public int getScore(){return score;}
    
}
