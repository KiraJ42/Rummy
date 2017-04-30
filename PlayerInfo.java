import sun.plugin2.message.transport.SerializingTransport;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Scanner;

public class PlayerInfo extends JPanel {

    protected JLabel SCORE;
    protected JLabel SETS;
    protected JLabel SERIES;
    protected Player player;
    protected Font bigger;
    public PlayerInfo(Player p){
        setBackground(new Color (250, 250, 210));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
        setBorder(border);

        player = p;
        setLayout(new GridLayout(0,1));
        JLabel name = new JLabel(" Player Name: " + p.name + " ");
        Font font = name.getFont();
        bigger = new Font(font.getFontName(), Font.PLAIN, 16);
        name.setFont(bigger);
        SCORE = new JLabel(" Score: " + p.getTotalScore() + " ");
        SCORE.setFont(bigger);
        SETS = new JLabel(" Sets: " +  " ");
        SETS.setFont(bigger);
        SERIES = new JLabel(" Series" + " ");
        SERIES.setFont(bigger);

        add(name);
        add(SCORE);
        add(SETS);
        add(SERIES);
    }

    public void updateScore(){
        System.out.println(player.getTotalScore());
        this.remove(SCORE);
        this.remove(SERIES);
        this.remove(SETS);
        SCORE = new JLabel(" Score: " + player.getTotalScore() + " ");
        SCORE.setFont(bigger);
        String sets = "";
        String series = "";
        for(Lays x : player.ScoredLays){
            if(x instanceof Set){
                sets = sets + x.toString();
            }
            else if(x instanceof Series){
                series = series + x.toString();
            }
        }
        SETS = new JLabel(" Sets: " +  sets + " ");
        SETS.setFont(bigger);
        SERIES = new JLabel(" Series" + series);
        SERIES.setFont(bigger);
        add(SCORE);
        add(SETS);
        add(SERIES);
        this.validate();
        this.repaint();
    }



}
