import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Owner on 3/28/2017.
 */
public class Game {

    Game() {
        JFrame window = new JFrame("Rummy");
        Rules rule;
        Deck d;
     
        rummyGUI content = new rummyGUI(window);
        window.setContentPane(content);

        window.setResizable(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel center = new JPanel();
        JPanel scores = new JPanel();
        center.setBackground( new Color(130,50,40) );
        scores.setBackground( new Color(130,50,40) );
        //scores.setSize(content.getWidth(), 200);
        Card c = new Card(0, 2, 4);
        Card dc = new Card(0, 1, 5);
        JLabel cardDeck = new JLabel(c.back);
        JLabel cd = new JLabel(dc.back);
        scores.add(cd);
        center.add(cardDeck);
        window.add(scores, BorderLayout.NORTH);
        window.add(center, BorderLayout.CENTER);
        //window.add(scores, BorderLayout.NORTH);
        new Music();
        window.pack();
        window.setVisible(true);


        String[] types = {"Rummy", "Gin Rummy"};
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

        d = new Deck(deckNum);
        d.shuffle();

        ArrayList<Player> players= new ArrayList<>();
        players.add(player);

        for(int i = 0; i < (num-1); i++)
            players.add(new Player("Player" + (i+2)));

        d.Deal(7, players);

        JPanel playerHand = new JPanel();
        playerHand.setLayout(new FlowLayout());
        playerHand.setBackground( new Color(130,50,40) );

        Player x = players.get(0);

        for(int i = 0; i < 7; i++){

            Card a = x.hand.get(i);
            JLabel j = new JLabel(a.front);
            playerHand.add(j);
        }

        window.getContentPane().add(playerHand, BorderLayout.SOUTH);
        ArrayList<Card> disc = d.getDiscard();
        Card r = disc.get(disc.size()-1);
        JLabel dis = new JLabel(r.front);
        center.add(dis);

        window.validate();
        window.repaint();

        
        /*if(gameType.equals("Rummy"))*/
            //rule = new Rummy(num, name);
        
        /*else if(gameType.equals("GinRummy"))
            rule = new GinRummy(num, name);*/
        
       /* while(!rule.victoryCheck())
        {
            rule.deal();
            
            while(!rule.nextTurn());

            rule.endRound();
        }*/
        //d1.Deal(11, p);
    }
    public static void main(String[] args)
    {
        Game game = new Game();
    }
}
