import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cardButtons extends JPanel {

    static JButton discard;
    static JButton check;
    static Game g;
    cardButtons(Game game) {
        g = game;
        discard = new JButton("Discard");
        discard.addActionListener(new checkCard());
        check = new JButton("Check Selection");
        check.addActionListener(new checkCard());

        setBackground(new Color(130, 50, 40));

        add(Box.createVerticalStrut(10));
        add(discard);
        add(Box.createVerticalStrut(10));
        add(check);
    }

    public class checkCard implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Player p = g.player;
            if(p.checkLay.size() == 1 && e.getSource() == discard) {
                Card r = p.checkLay.get(0);
                g.playerHand.updateH(p.hand.indexOf(r));
                p.hand.remove(r);
                g.d.Discard(r);
                p.checkLay.clear();
                g.updateDiscard();
                g.window.validate();
                g.window.repaint();
            }else if(p.checkLay.size() > 1 && e.getSource() == check) {
                if (Set.isValid(p.checkLay)) {
                    p.lays.add(new Set(p.checkLay));
                    for (Card x : p.checkLay) {
                        g.playerHand.updateH(p.hand.indexOf(x));
                        p.hand.remove(x);
                    }
                    p.checkLay.clear();
                    p.details.updateScore();
                    g.window.validate();
                    g.window.repaint();
                } else if (Series.isValid(p.checkLay)) {
                    p.lays.add(new Series(p.checkLay));
                    for (Card x : p.checkLay) {
                        g.playerHand.updateH(p.hand.indexOf(x));
                        p.hand.remove(x);
                    }
                    p.checkLay.clear();
                    g.window.validate();
                    g.window.repaint();
                }
            }else if (p.checkLay.size() > 1 && e.getSource() == discard) {
                    JOptionPane.showMessageDialog(g.window, "You cannot discard multiple cards");
            }else{
                    JOptionPane.showMessageDialog(g.window, "Not a valid Set or Series");
            }

        }
    }
}
