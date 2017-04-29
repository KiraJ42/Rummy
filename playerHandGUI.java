import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

/**
 * Created by Owner on 4/29/2017.
 */
public class playerHandGUI extends JPanel{


    static ArrayList<Player> players;
    playerHandGUI(ArrayList<Player> p) {
        super();
        setLayout(new FlowLayout());
        setBackground(new Color(130, 50, 40));

        Player x = p.get(0);

        for (int i = 0; i < 7; i++) {

            Card a = x.hand.get(i);
            JLabel j = new JLabel(a.frontImg);
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
}
