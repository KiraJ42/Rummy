import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;

/**
 * Created by Owner on 3/28/2017.
 */
public class Game {

    public static String gameType;
    public static ArrayList<Player> players;
    public static Deck d;
    public static JFrame window;
    static playerHandGUI playerHand;

    Game() {
        window = new JFrame("Rummy");
        Rules rule;
     
        rummyGUI content = new rummyGUI(window, this);
        window.setContentPane(content);
        window.setResizable(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);


        String[] types = {"Rummy", "Gin Rummy"};
        String name = JOptionPane.showInputDialog(window, "Enter Your Name:", "Welcome to Rummy!", JOptionPane.PLAIN_MESSAGE);
        Player player = new Player(name);
        gameType = (String) JOptionPane.showInputDialog(window, "Which Rummy game would you like to play?", "Choose Game", JOptionPane.QUESTION_MESSAGE, null, types, types[0]);
        Integer[] playerNum = {2, 3, 4};
        Integer num = (Integer) JOptionPane.showInputDialog(window, "How many players in your game?", "Players",JOptionPane.QUESTION_MESSAGE, null, playerNum, playerNum[0]);


        JPanel center = new JPanel();
        JPanel scores = new JPanel();
        center.setBackground( new Color(130,50,40) );
        scores.setBackground( new Color(130,50,40) );

        Card.backImg = Card.getImage(Card.back);
        JLabel cardDeck = new JLabel(Card.backImg);

       // JLabel cd = new JLabel(Card.backImg);

        PlayerInfo play = new PlayerInfo();
        scores.add(play);
        center.add(cardDeck);
        window.add(scores, BorderLayout.NORTH);

        window.add(center, BorderLayout.CENTER);

        new Music();


        d = new Deck();

        d.shuffle();

        players= new ArrayList<>();
        players.add(player);

        for(int i = 0; i < (num-1); i++)
            players.add(new Player("Player" + (i+2)));

        d.Deal(7, players);

        playerHand = new playerHandGUI(player);

        window.getContentPane().add(playerHand, BorderLayout.SOUTH);

        JButton take = new JButton("Pick a Card");
        take.addActionListener(new pickCard());
        JButton discard = new JButton("Discard");
        discard.addActionListener(new checkCard());
        JButton checkLay = new JButton("Check Selection");
        checkLay.addActionListener(new checkCard());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(130,50,40));

        buttonPanel.add(take);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(discard);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(checkLay);

        scores.add(buttonPanel);

        ArrayList<Card> disc = d.getDiscard();
        Card r = disc.get(disc.size()-1);
        JLabel dis = new JLabel(r.frontImg);
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

    public class checkCard implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Player p = players.get(0);
            if(p.checkLay.size() == 1) {
                Card r = p.checkLay.get(0);
                playerHand.updateH(p.hand.indexOf(r));
                p.hand.remove(r);
                d.Discard(r);
                p.checkLay.clear();
                window.validate();
                window.repaint();
            }else{
                System.out.println("more");

            }
        }
    }

    public class pickCard implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Player p = players.get(0);
            Card newC = d.takeCard();
            p.hand.add(newC);
            playerHand.add(newC);
            window.validate();
            window.repaint();
        }
    }
}
