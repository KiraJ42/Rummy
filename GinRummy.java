import java.util.*;

public class GinRummy extends Rules
{
    GinRummy(int numP, String n){
        super(numP, n);
    }
    public void deal(){

    }

    public boolean nextTurn()
    {
        return true;
    }

    @Override
    public boolean victoryCheck() {
        return false;
    }

    @Override
    public void endRound() {

    }
}
