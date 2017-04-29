import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

public class playerHandGUI extends JPanel{


    static Player player;
    static ArrayList<JLabel> hand;
    playerHandGUI(ArrayList<Player> p) {
        super();
        hand = new ArrayList<>();
        player = p;
        setLayout(new FlowLayout());
        setBackground(new Color(130, 50, 40));

        for (int i = 0; i < player.hand.size(); i++) {
            Card a = player.hand.get(i);
            JLabel j = new JLabel(a.frontImg);
            hand.add(j);
            j.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    selectCard(x, a, j);
                }
            });
            add(j);
        }
    }

    protected void selectCard(Player px, Card pCard, JLabel jl){
        if(jl.getBorder() == null){
            px.checkLay.add(pCard);
            Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
            jl.setBorder(border);
        }else {
            px.checkLay.remove(pCard);
            jl.setBorder(null);
        }
        jl.validate();
        jl.repaint();
    }
    
    public void add(Card c){
        JLabel j = new JLabel(c.frontImg);
        j.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectCard(player, c, j);
            }
        });
        hand.add(j);
        this.add(j);
    }

    public void updateH(int index){
        JLabel L = hand.get(index);
        hand.remove(index);
        this.remove(L);
        this.validate();
        this.repaint();

    }
}
