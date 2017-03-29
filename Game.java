import java.util.Scanner;

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

        Deck d1 = new Deck();
        d1.shuffle();

        for(int i = 0; i < playerNum; i++){
            for(int h = 0; h < 11; h++)
            {
                Card hands = d1.takeCard();
                players[i].hand.add(hands);
            }
        }

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
