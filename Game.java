import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Game {

    public static Rules rule;
    public static ArrayList<Player> players;
    public static Deck d;
    public JFrame window;
    public playerHandGUI playerHand;
    public JLabel discard = new JLabel();
    public Player player;
    public JLabel cardDeck = new JLabel();
    public JPanel centerCard = new JPanel();
    static boolean drawn;
    static int pick;
    //static JPanel center;

    Game() {
        window = new JFrame("Rummy");
        //Rules rule;
        drawn = false;
        pick = 0;
        rummyGUI content = new rummyGUI(window, this);
        window.setContentPane(content);
        window.setResizable(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);

        String name = JOptionPane.showInputDialog(window, "Enter Your Name:", "Welcome to Rummy!", JOptionPane.PLAIN_MESSAGE);
        player = new Player(name, this);
        Integer[] playerNum = {2, 3, 4};
        Integer num = (Integer) JOptionPane.showInputDialog(window, "How many players in your game?", "Players",JOptionPane.QUESTION_MESSAGE, null, playerNum, playerNum[0]);

        JPanel scores = new JPanel();
        JPanel center = new JPanel();
        JPanel centerBtns = new JPanel();
        centerBtns.setBackground(new Color(130,50,40));
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        centerCard = new JPanel();
        centerCard.setBackground(new Color(130,50,40));
        center.setBackground( new Color(130,50,40) );
        scores.setBackground( new Color(130,50,40) );
 
        d = new Deck();

        d.shuffle();

        players= new ArrayList<>();
        players.add(player);

        for(int i = 0; i < (num-1); i++)
            players.add(new Computer(i + 2, this));

        for(Player p : players) {
            scores.add(p.details);
        }
        
        rule = new Rummy(this);
        window.add(scores, BorderLayout.NORTH);

        d.Deal(7, players);

        playerHand = new playerHandGUI(player);

        window.getContentPane().add(playerHand, BorderLayout.SOUTH);

        cardButtons buttonPanel = new cardButtons(this);

       // scores.add(buttonPanel);

        ArrayList<Card> disc = d.getDiscard();
        Card r = disc.get(disc.size()-1);
        discard = new JLabel(r.frontImg);
        discard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                if(player.drawn || pick > 0) {
                    JOptionPane.showMessageDialog(window, "You've already drawn this turn");
                    player.drawn = false;
                }
                else
                {
                    player.drawn = true;
                    Card cd = d.takeDiscard();
                    player.hand.add(cd);
                    playerHand.add(cd);
                    updateDiscard();
                    pick++;
                    window.validate();
                    window.repaint();
                }
            }
        });
        
        centerBtns.add(buttonPanel);
        Card.backImg = Card.getImage(Card.back);
        cardDeck.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    pickCard();
            }
        });

        centerCard.add(cardDeck);
        centerCard.add(discard);
        center.add(centerCard);
        center.add(centerBtns);

        window.add(center, BorderLayout.CENTER);

        window.validate();
        window.repaint();
    }
    
    public static void main(String[] args) 
    {
        Game game = new Game();
        game.player.drawn = false;
        int winner;
        while(!Game.rule.victoryCheck())
        {
            winner = Game.rule.playRound();
            
            if(winner != 0)
                JOptionPane.showMessageDialog(game.window, "Player " + winner + " has won the round!!");
            
            else
                JOptionPane.showMessageDialog(game.window, "You have won the round!!");
            
            d.Deal(7, players);
        }
        
    }

    public void updateDiscard(){
        if(d.getDiscard().size() > 0) {
            Card x = d.getDiscard().get(d.getDiscard().size() - 1);
            Container parent = discard.getParent();
            parent.remove(discard);
            discard = new JLabel(x.frontImg);
            discard.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    if (drawn || pick > 0) {
                        JOptionPane.showMessageDialog(window, "You've already drawn this turn");
                        drawn = false;
                    }
                    else {
                        Card cd = d.takeDiscard();
                        player.hand.add(cd);
                        playerHand.add(cd);
                        updateDiscard();
                        drawn = true;
                        window.validate();
                        window.repaint();
                    }
                }
            });
            parent.add(discard);
            window.validate();
            window.repaint();
        }else{
            Container parent = discard.getParent();
            parent.remove(discard);
            discard = new JLabel();
            discard.setBackground(new Color(130,50,40));
            parent.add(discard);
            window.validate();
            window.repaint();
        }
    }
    public void pickCard() {
        pick++;
        if(pick == 2) {
            pick = 1;
            JOptionPane.showMessageDialog(window, "You've already drawn this turn");
            drawn = false;
            return;
        }
        else if(drawn) {
            JOptionPane.showMessageDialog(window, "You've already drawn this turn");
            drawn = false;
        }
        else
        {
            Card newC = d.takeCard();
            player.hand.add(newC);
            playerHand.add(newC);
            drawn = true;
            window.validate();
            window.repaint();
        }
    }
}
