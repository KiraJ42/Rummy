import java.util.Scanner;
/**
 * Created by Owner on 3/28/2017.
 */
public class Game {

    private static int playerNum;
    Game(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of players for your game: ");
        playerNum =  input.nextInt();

        Player[] players = new Player[playerNum];
        for(int p = 0; p < playerNum; p++)
        {
            players[p] = new Player();
        }

        for(int i = 0; i < playerNum; i++){
            System.out.print("Enter Player " + (i+1) + " name: ");
            players[i].name = input.next();
        }

        Deck d1 = new Deck(2);
        d1.shuffle();

        d1.Deal(11, players);

        for(int t = 0; t < playerNum; t++)
        {
            System.out.println(players[t]);
        }

        System.out.println(d1);
    }
    public static void main(String[] args)
    {
        Game game = new Game();
    }
}
