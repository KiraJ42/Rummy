import java.util.ArrayList;

public class Computer extends Player{

    Computer(int i){
        name = "Player " + i;
        totalScore = 0;
        details = new PlayerInfo(this);
    }
    
    
}
