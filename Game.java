import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


/**
 * Created by Owner on 3/28/2017.
 */
public class Game {

    public String gameType;
    public ArrayList<Player> players;
    public Deck d;
    public JFrame window;
    public playerHandGUI playerHand;
    public JLabel discard;
    public Player player;
    public JLabel cardDeck = new JLabel();
    public JPanel centerCard = new JPanel();
    //static JPanel center;

    Game() {
        window = new JFrame("Rummy");
        Rules rule;
     
        rummyGUI content = new rummyGUI(window, this);
        window.setContentPane(content);
        window.setResizable(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);

        String name = JOptionPane.showInputDialog(window, "Enter Your Name:", "Welcome to Rummy!", JOptionPane.PLAIN_MESSAGE);
        player = new Player(name);
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
            players.add(new Player("Player" + (i+2)));

        for(Player p : players) {
            scores.add(p.details);
        }
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
                Card cd = d.takeDiscard();
                player.hand.add(cd);
                playerHand.add(cd);
                updateDiscard();
                window.validate();
                window.repaint();
            }
        });

        centerBtns.add(buttonPanel);
        Card.backImg = Card.getImage(Card.back);
        cardDeck = new JLabel(Card.backImg);
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


     /*   if(gameType.equals("Rummy"))
            rule = new Rummy(num, name);
        else
            rule = new GinRummy(num, name);

       while(!rule.victoryCheck()) {
           rule.deal();

           while (!rule.nextTurn()) ;

           rule.endRound();
       }*/
    }
    public static void main(String[] args) {Game game = new Game();}

    public void updateDiscard(){
        if(d.getDiscard().size() > 0) {
            Card x = d.getDiscard().get(d.getDiscard().size() - 1);
            Container parent = discard.getParent();
            parent.remove(discard);
            discard = new JLabel(x.frontImg);
            discard.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Card cd = d.takeDiscard();
                    player.hand.add(cd);
                    playerHand.add(cd);
                    updateDiscard();
                    window.validate();
                    window.repaint();
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
        Player p = players.get(0);
        Card newC = d.takeCard();
        p.hand.add(newC);
        playerHand.add(newC);
        window.validate();
        window.repaint();
    }
}
