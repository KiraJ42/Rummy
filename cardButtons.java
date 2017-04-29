import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cardButtons extends JPanel {

    static Game g;
    cardButtons(Game game) {
        g = game;
        JButton discard = new JButton("Discard");
        discard.addActionListener(new checkCard());
        JButton checkLay = new JButton("Check Selection");
        checkLay.addActionListener(new checkCard());

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(130, 50, 40));

        add(Box.createVerticalStrut(10));
        add(discard);
        add(Box.createVerticalStrut(10));
        add(checkLay);
    }

    public class checkCard implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Player p = g.players.get(0);
            if(p.checkLay.size() == 1) {
                Card r = p.checkLay.get(0);
                g.playerHand.updateH(p.hand.indexOf(r));
                p.hand.remove(r);
                g.d.Discard(r);
                p.checkLay.clear();
                g.updateDiscard();
                g.window.validate();
                g.window.repaint();
            }else{
                if(Set.isValid(p.checkLay)){
                    p.lays.add(new Set(p.checkLay));
                    for(Card x : p.checkLay){
                        g.playerHand.updateH(p.hand.indexOf(x));
                        p.hand.remove(x);
                    }
                    p.checkLay.clear();
                    g.window.validate();
                    g.window.repaint();
                }else if(Series.isValid(p.checkLay)){
                    p.lays.add(new Series(p.checkLay));
                    for(Card x : p.checkLay){
                        g.playerHand.updateH(p.hand.indexOf(x));
                        p.hand.remove(x);
                    }
                    p.checkLay.clear();
                    g.window.validate();
                    g.window.repaint();
                }else{
                    JOptionPane.showMessageDialog(g.window, "Not a valid Set or Series");
                }

            }
        }
    }
}
