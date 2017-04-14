import javax.swing.*;

/**
 * Created by Owner on 3/28/2017.
 */
public class Game {

    Game() {
        JFrame window = new JFrame("Rummy");

        rummyGUI content = new rummyGUI();
        window.setContentPane(content);
        window.pack();
        window.setResizable(true);
        window.setSize(500, 500);
        window.setLocation(100, 100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        String[] types = {"Rummy", "Gin Rummy", "Dummy Rummy"};
        String name = JOptionPane.showInputDialog(window, "Enter Your Name:", "Player Name", JOptionPane.PLAIN_MESSAGE);
        Player player = new Player(name);
        String gameType = (String) JOptionPane.showInputDialog(window, "Which Rummy game would you like to play?", "Choose Game", JOptionPane.QUESTION_MESSAGE, null, types, types[0]);

        Integer[] playerNum = new Integer[3];
        int deckNum;
        if (gameType.equals("Dummy Rummy")){
            deckNum = 2;
            for(int i = 0; i < 3; i++){
                playerNum[i] = i+3;
            }
        }
        else {
            deckNum = 1;
            for(int i = 0; i < 3; i++){
                playerNum[i] = i+2;
            }
        }


        Integer num = (Integer) JOptionPane.showInputDialog(window, "How many players in your game?", "Players",JOptionPane.QUESTION_MESSAGE, null, playerNum, playerNum[0]);

        Deck d1 = new Deck(deckNum);
        d1.shuffle();

        //d1.Deal(11, p);
    }
    public static void main(String[] args)
    {
        Game game = new Game();
    }
}
